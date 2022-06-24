package com.example.mynt.collectionsActivity.models;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.mynt.R;
import com.example.mynt.dataAccessLayer.Database_Lite;
import com.example.mynt.dataAccessLayer.Model_Database_Lite;
import com.example.mynt.dataAccessLayer.Model_Firebase;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class Model_User_Data {

    // Will Hold static once off data
    public static Model_Coin model_coin;
    public static Bitmap coinBitmap;
    public static FirebaseUser firebaseUser;
    public static Model_User currentUser;
    public static boolean sync =false;
    static FirebaseDatabase database = FirebaseDatabase.getInstance();
    static DatabaseReference mDatabase = database.getReference();
    public static String pass;

    public static void uploadAllLocalData(Context context)
    {
        //First check if user is authorized
        //Adding collections
        boolean changeSyc = false;
        float points = 0;
        Database_Lite db = new Database_Lite(context);
        ArrayList<Model_Collections> allCollections = db.getAllCollections();
        ArrayList<Model_Collections> userCollections = new ArrayList<>();
        Model_Database_Lite mdl = new Model_Database_Lite();

        for (int i=0; i<allCollections.size(); i++)
        {
            ArrayList<Model_Coin> coins = mdl.allCoinsAndCollections(context,1,allCollections.get(i).getCollectionID());
            ArrayList<Model_Fire_Base_Coin> modelFireBaseCoinArrayList = new ArrayList<>();
            for (int s=0; s<coins.size(); s++)
            {
                String Value_Year = coins.get(s).getValue() +"_"+ coins.get(s).getYear();
                Model_Fire_Base_Coin modelFireBaseCoin = new Model_Fire_Base_Coin(Value_Year,coins.get(s).getDateAcquired());
                uploadImage(Value_Year, coins.get(s).getImageId(),allCollections.get(i).getCollectionName(),false);
                float coinPoint = 700000000-((coins.get(s).getMintage()/2)-coins.get(s).getYear());
                coinPoint = coinPoint/100000;
                points+=coinPoint;
                modelFireBaseCoinArrayList.add(modelFireBaseCoin);
                Log.d("changeSync", "uploadAllLocalData: " + changeSyc);
                changeSyc = true;
            }
            Model_Collections model_collections = new Model_Collections(allCollections.get(i).getCollectionName()
                    ,allCollections.get(i).getGoal(),
                    modelFireBaseCoinArrayList);
            userCollections.add(model_collections);
        }

        //Adding coins to collections
        currentUser.setPoints(points);
        currentUser.setCollections(userCollections);
        if(changeSyc || currentUser.getLastSync().equals(""))
        {
            Calendar cal = Calendar.getInstance();
            String lastSync = cal.getTime().toString();
            currentUser.setLastSync(lastSync);
            db.updateUserLastSync(currentUser);
        }
        Model_Fire_Base_User currentFireUser = new Model_Fire_Base_User(currentUser.getEmail(), currentUser.getUserName(),
                currentUser.getState(), currentUser.getCollections(), currentUser.getLastSync(), currentUser.getPoints());
        mDatabase.child("users").child(firebaseUser.getUid()).setValue(currentFireUser);
        uploadImage(currentUser.getEmail(), currentUser.getImageId(), "ProfilePicture",true);
    }

    private static void uploadImage(String ImageID, byte[] data, String CollectionName,boolean type)
    {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();
        // Create a reference to "ImageID.jpg"
        String fileName = ImageID + ".jpg";
        StorageReference mountainsRef = storageRef.child(fileName);

        // Create a reference to 'images/mountains.jpg'
        String directory;
        if(type)
        {
            directory = CollectionName+"/" + fileName;
        }else
            directory = firebaseUser.getUid() + "/"+CollectionName+"/" + fileName;

        UploadTask uploadTask = storageRef.child(directory).putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                // ...
            }
        });
    }

    public static void mergeData(Context context)
    {
        Query myUsersQuery = mDatabase.child("users").child(firebaseUser.getUid()).orderByChild("lastSync");
        myUsersQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Database_Lite db = new Database_Lite(context);
                ArrayList<Model_User> usersList = db.getAllUsers();
                pass = Model_User_Data.currentUser.getPassword();
                Model_User_Data.currentUser =usersList.get(0);
                if (Objects.equals(snapshot.child("lastSync").getValue(), usersList.get(0).getLastSync())) {

                    Log.d("theChanges", "They are the same: ");

                }else
                {
                    Log.d("theChanges", "We in else ");
                    //String change = snapshot.child("lastSync").getValue(String.class);
                    //Compare Dates
                    String firebaseSync = snapshot.child("lastSync").getValue(String.class);
                    Calendar fireCal = Calendar.getInstance();
                    Calendar sqlCal = Calendar.getInstance();
                    SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
                    try {
                        assert firebaseSync != null;
                        fireCal.setTime(Objects.requireNonNull(sdf.parse(firebaseSync)));// all done https://stackoverflow.com/questions/39800547/read-data-from-firebase-database
                        if(currentUser.getLastSync() == null)//In event login
                        {
                            Log.d("theChanges", "lastSync is null");
                            downloadData(snapshot,context,firebaseSync);
                        }
                        else
                        {
                            sqlCal.setTime(Objects.requireNonNull(sdf.parse(currentUser.getLastSync())));

                            if(fireCal.compareTo(sqlCal) < 0)
                            {
                                //FireCal is older
                                //Upload SqlCal to replace old stuff
                                Log.d("theChanges", "FireCal is older: ");
                                uploadAllLocalData(context);
                            }else
                            {
                                //sql cal is older
                                //replace sql database with firebase data
                                downloadData(snapshot,context,firebaseSync);
                            }
                        }
                    } catch (ParseException e) {
                       // e.printStackTrace();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private static void downloadData(DataSnapshot snapshot, Context context, String fireBaseSync) {
        //sql cal is older
        //replace sql database with firebase data
        Model_User model_user = new Model_User(snapshot.child("email").getValue(String.class), snapshot.child("password").getValue(String.class), snapshot.child("state").getValue(int.class));
        model_user.setUserName(snapshot.child("userName").getValue(String.class));
        model_user.setLastSync(fireBaseSync);
        model_user.setPoints(snapshot.child("points").getValue(float.class));

        List<Model_Collections> model_collectionsList = new ArrayList<>();//https://stackoverflow.com/questions/38652007/how-to-retrieve-specific-list-of-data-from-firebase
        for (DataSnapshot postSnapshot : snapshot.child("collections").getChildren()) {
            Model_Collections model_collections = new Model_Collections(postSnapshot.child("collectionName").getValue(String.class), postSnapshot.child("goal").getValue(int.class));

            List<Model_Fire_Base_Coin> modelFireBaseCoinList = new ArrayList<>();
            for (DataSnapshot postSnapshotChild : postSnapshot.child("fireBaseCoinscoins").getChildren()) {
                Model_Fire_Base_Coin modelFireBaseCoin = new Model_Fire_Base_Coin(postSnapshotChild.child("valueYear").getValue(String.class), postSnapshotChild.child("dateTaken").getValue(String.class));
                modelFireBaseCoinList.add(modelFireBaseCoin);
            }
            //Now Add Coin to FireBaseCoinsList
            model_collections.setFireBaseCoinscoins((ArrayList<Model_Fire_Base_Coin>) modelFireBaseCoinList);
            model_collectionsList.add(model_collections);

            model_user.setCollections(model_collectionsList);
        }

        //Retrieving User Profile Picture
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();
        String fileName = model_user.getEmail() + ".jpg";
        String directory = "ProfilePicture"+"/" + fileName;
        StorageReference mountainsRef = storageRef.child(directory);
        final long ONE_MEGABYTE = 1024 * 1024;
        mountainsRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                model_user.setImageId(bytes);
                currentUser = model_user;
                Model_Database_Lite model_database_lite = new Model_Database_Lite();
                model_database_lite.replaceSqlDatabase(context);
            }
        });
    }
}
