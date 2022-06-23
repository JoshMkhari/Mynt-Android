package com.example.mynt.collectionsActivity.models;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.example.mynt.dataAccessLayer.Database_Lite;
import com.example.mynt.dataAccessLayer.Model_Database_Lite;
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

public class User_Data {

    // Will Hold static once off data
    public static Model_Coin model_coin;
    public static Bitmap coinBitmap;
    public static FirebaseUser firebaseUser;
    public static Model_User currentUser;
    public static boolean sync =false;
    static FirebaseDatabase database = FirebaseDatabase.getInstance();
    static DatabaseReference mDatabase = database.getReference();

    public static void uploadAllLocalData(Context context)
    {
        //First check if user is authorized
        //Adding collections
        boolean changeSyc = false;

        Database_Lite db = new Database_Lite(context);
        ArrayList<Model_Collections> allCollections = db.getAllCollections();
        ArrayList<Model_Collections> userCollections = new ArrayList<>();
        Model_Database_Lite mdl = new Model_Database_Lite();

        for (int i=0; i<allCollections.size(); i++)
        {
            ArrayList<Model_Coin> coins = mdl.allCoinsAndCollections(context,1,allCollections.get(i).getCollectionID());
            ArrayList<ModelFireBaseCoin> modelFireBaseCoinArrayList = new ArrayList<>();
            for (int s=0; s<coins.size(); s++)
            {
                String Value_Year = coins.get(s).getValue() +"_"+ coins.get(s).getYear();
                ModelFireBaseCoin modelFireBaseCoin = new ModelFireBaseCoin(Value_Year,coins.get(s).getDateAcquired());
                uploadImage(Value_Year, coins.get(s).getImageId(),allCollections.get(i).getCollectionName());
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
        currentUser.setCollections(userCollections);
        if(changeSyc || currentUser.getLastSync().equals(""))
        {
            Calendar cal = Calendar.getInstance();
            String lastSync = cal.getTime().toString();
            currentUser.setLastSync(lastSync);
            db.updateUserLastSync(currentUser);
        }
        mDatabase.child("users").child(firebaseUser.getUid()).setValue(currentUser);
        //Merge online data with offline data
    }

    private static void uploadImage(String ImageID, byte[] data, String CollectionName)
    {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();
        // Create a reference to "ImageID.jpg"
        String fileName = ImageID + ".jpg";
        StorageReference mountainsRef = storageRef.child(fileName);

        // Create a reference to 'images/mountains.jpg'
        String directory = firebaseUser.getUid() + "/"+CollectionName+"/" + fileName;

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

                if (Objects.equals(snapshot.child("lastSync").getValue(), currentUser.getLastSync())) {
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

                        if(currentUser.getLastSync().equals(""))//In event login
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
                                Log.d("theChanges", "sql cal is older: ");
                                Model_User model_user = new Model_User(snapshot.child("email").getValue(String.class),snapshot.child("password").getValue(String.class),snapshot.child("state").getValue(int.class));
                                model_user.setUserID(snapshot.child("userID").getValue(int.class));

                                List<Model_Collections> model_collectionsList = new ArrayList<>();//https://stackoverflow.com/questions/38652007/how-to-retrieve-specific-list-of-data-from-firebase
                                for (DataSnapshot postSnapshot: snapshot.child("collections").getChildren()) {
                                    Model_Collections model_collections = new Model_Collections(postSnapshot.child("collectionName").getValue(String.class),postSnapshot.child("goal").getValue(int.class));
                                    model_collections.setCollectionID(postSnapshot.child("collectionID").getValue(int.class));

                                    List<ModelFireBaseCoin> modelFireBaseCoinList = new ArrayList<>();
                                    for (DataSnapshot postSnapshotChild: postSnapshot.child("fireBaseCoinscoins").getChildren()) {
                                        ModelFireBaseCoin modelFireBaseCoin = new ModelFireBaseCoin(postSnapshotChild.child("valueYear").getValue(String.class),postSnapshotChild.child("dateTaken").getValue(String.class));
                                        modelFireBaseCoinList.add(modelFireBaseCoin);
                                    }
                                    //Now Add Coin to FireBaseCoinsList
                                    model_collections.setFireBaseCoinscoins((ArrayList<ModelFireBaseCoin>) modelFireBaseCoinList);
                                    model_collectionsList.add(model_collections);

                                    model_user.setCollections(model_collectionsList);
                                    currentUser = model_user;
                                    Model_Database_Lite model_database_lite = new Model_Database_Lite();
                                    model_database_lite.replaceSqlDatabase(context);
                                }

                            }
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
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
        model_user.setUserID(snapshot.child("userID").getValue(int.class));
        model_user.setLastSync(fireBaseSync);

        List<Model_Collections> model_collectionsList = new ArrayList<>();//https://stackoverflow.com/questions/38652007/how-to-retrieve-specific-list-of-data-from-firebase
        for (DataSnapshot postSnapshot : snapshot.child("collections").getChildren()) {
            Model_Collections model_collections = new Model_Collections(postSnapshot.child("collectionName").getValue(String.class), postSnapshot.child("goal").getValue(int.class));

            List<ModelFireBaseCoin> modelFireBaseCoinList = new ArrayList<>();
            for (DataSnapshot postSnapshotChild : postSnapshot.child("fireBaseCoinscoins").getChildren()) {
                ModelFireBaseCoin modelFireBaseCoin = new ModelFireBaseCoin(postSnapshotChild.child("valueYear").getValue(String.class), postSnapshotChild.child("dateTaken").getValue(String.class));
                modelFireBaseCoinList.add(modelFireBaseCoin);
            }
            //Now Add Coin to FireBaseCoinsList
            model_collections.setFireBaseCoinscoins((ArrayList<ModelFireBaseCoin>) modelFireBaseCoinList);
            model_collectionsList.add(model_collections);

            model_user.setCollections(model_collectionsList);
        }
        currentUser = model_user;

        Log.d("theSync", "downloadData: last sync " + currentUser.getLastSync());
        Log.d("theSync", "downloadData: collections num " + currentUser.getCollections().size());
        Model_Database_Lite model_database_lite = new Model_Database_Lite();
        model_database_lite.replaceSqlDatabase(context);

    }
}
