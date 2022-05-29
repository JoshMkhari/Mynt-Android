package com.example.mynt.collectionsActivity;

import static androidx.navigation.fragment.NavHostFragment.findNavController;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mynt.Interface_Back;
import com.example.mynt.R;
import com.example.mynt.collectionsActivity.models.Model_Coin;
import com.example.mynt.collectionsActivity.models.Model_Collections;
import com.example.mynt.dataAccessLayer.Database_Lite;
import com.example.mynt.collectionsActivity.models.Model_User;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;

public class Fragment_Add extends Fragment implements Interface_Back {

    private Spinner spinnerValue, spinnerMaterial, spinnerVariant, spinnerCollection;
    private SeekBar yearBar;
    private EditText year_Textview, alternate_Textview, mintage_Textview, observe_Textview, reverse_Textview;
    private ImageButton changeImage;
    private ImageView userImage;
    private ActivityResultLauncher<Intent> activityResultLauncher_Camera;
    private Bitmap imageBitmap;
    private Boolean imageSet;
    private Database_Lite localDB;
    private int coinID;
    private Button datePicker;
    private DatePickerDialog dateAcquired;
    private Model_User model_user;
    private ArrayList<Model_Collections> allUserCollections;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View add = inflater.inflate(R.layout.fragment_add, container, false);
        Log.d("distance", "We make it this far");
        //Retrieve bundles
        model_user = new Model_User();
        assert getArguments() != null;
        model_user.setUserID(getArguments().getInt("User"));

        String userID = model_user.getUserID() + " this";
        Log.d("add", userID);
        //Database methods


        //Toast.makeText(getApplicationContext(),localDB.getAllCoins().size()+ " That is the size",Toast.LENGTH_SHORT).show();
        //Assigning views to variables
        //spinners
        spinnerValue = add.findViewById(R.id.spinner_Values);
        spinnerMaterial = add.findViewById(R.id.spinner_Material);
        spinnerVariant = add.findViewById(R.id.spinner_Variant);
        spinnerCollection = add.findViewById(R.id.spinner_collection);

        //SeekBar
        yearBar = add.findViewById(R.id.seekBar_year);

        //EditTexts
        year_Textview = add.findViewById(R.id.yearValueEditText);
        alternate_Textview = add.findViewById(R.id.alternateName_EditText);
        mintage_Textview = add.findViewById(R.id.mintageNum_editText);
        observe_Textview = add.findViewById(R.id.observe_EditText);
        reverse_Textview = add.findViewById(R.id.reverse_EditText);


        //ImageButton
        ImageButton add_Button = add.findViewById(R.id.imageview_blockTitle_addCoin);
        ImageButton back = add.findViewById(R.id.image_button_back_coins);
        changeImage = add.findViewById((R.id.changePicture));

        //ImageView
        userImage = add.findViewById(R.id.userImage);
        //Button
        datePicker = add.findViewById(R.id.datePickerButton);

        datePicker.setText(getTodaysDate());

        //Listeners
        setUpListeners();


        //Database
        localDB = new Database_Lite(getContext());
        ArrayList<Integer> userCollectionIDs = localDB.getAllCollectionsForUser(model_user);
        ArrayList<Model_Collections> allCollections = localDB.getAllCollections();

        allUserCollections = new ArrayList<>();

        for (int i = 0; i< allCollections.size(); i++)
        {
            if(userCollectionIDs.contains(allCollections.get(i).getCollectionID()))
                allUserCollections.add(allCollections.get(i));
        }

        ArrayList<Integer> allCoinsWithCollection = localDB.getAllCoinsWithACollection();
        ArrayList<Model_Coin> allCoinsInDatabase = localDB.getAllCoins();

        if(allCoinsWithCollection.size() == 0 )
        {
            coinID = 1;
            retrieveImage(coinID);
        }
        else
        {
            if(allCoinsWithCollection.size() == allCoinsInDatabase.size() )
            {
                coinID = allCoinsInDatabase.get(allCoinsInDatabase.size()-1).getCoinID()+1;
            }
            else
            {
                retrieveImage(allCoinsWithCollection.get(allCoinsWithCollection.size()-1)+1);
            }
        }


        year_Textview.setText("2010");

        ArrayList<String> userCollections = new ArrayList<>();
        userCollections.add("Create New Collection");



        for (int i=0; i<allUserCollections.size(); i++)
        {
            userCollections.add(allUserCollections.get(i).getCollectionName());

        }

        //for loop to add user collections here

        //Populate User Collections now


        //Array Adapters
        //Value

        ArrayAdapter<CharSequence> adapterValue = ArrayAdapter.createFromResource(getContext(), R.array.Value, R.layout.spinner_item);
        adapterValue.setDropDownViewResource(R.layout.spinner_item);
        spinnerValue.setAdapter(adapterValue);

        ArrayAdapter<CharSequence> adapterMaterial = ArrayAdapter.createFromResource(getContext(), R.array.Material, R.layout.spinner_item);
        adapterMaterial.setDropDownViewResource(R.layout.spinner_item);
        spinnerMaterial.setAdapter(adapterMaterial);

        ArrayAdapter<CharSequence> adapterVariant = ArrayAdapter.createFromResource(getContext(), R.array.Variants, R.layout.spinner_item);
        adapterVariant.setDropDownViewResource(R.layout.spinner_item);
        spinnerVariant.setAdapter(adapterVariant);

        ArrayAdapter<String> adapterCollection = new ArrayAdapter<>(getContext(), R.layout.spinner_item, userCollections);
        adapterCollection.setDropDownViewResource(R.layout.spinner_item);
        spinnerCollection.setAdapter(adapterCollection);

        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                backActivity();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(),callback);
        //Activity_Collections.OnB
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backActivity();
            }
        });

        add_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Check if picture is taken?
                if (imageSet) {
                    //check if a mintage was placed
                    if (mintage_Textview.getText().length() > 0) {
                        //Check if a collection has to be made
                        if(savePhotoToInternalStorage())
                        {
                            storeCoin();
                            if (spinnerCollection.getSelectedItemPosition() == 0) {//A new collection needs to be made
                                Bundle bundle = new Bundle();
                                bundle.putInt("User", model_user.getUserID());
                                bundle.putInt("Task", 1);
                                bundle.putInt("ImageID",coinID);
                                Navigation.findNavController(add).navigate(R.id.action_fragment_Add_to_fragment_Collections2,bundle);
                            }else
                            {
                                Toast.makeText(getContext(), "Storing collectionCoin", Toast.LENGTH_SHORT).show();
                                Intent home = new Intent(getContext(),Activity_Collections.class);
                                home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(home);
                            }
                        }
                    }
                    else
                    {
                        Toast.makeText(getContext(), "Set Mintage", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "Set image nest time", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return add;
    }



    private String getTodaysDate() {

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);

        return makeDateString(day, month, year);

    }
    //Result for Collection


    public void setUpListeners() {

        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month = month + 1;
                String date = makeDateString(day, month, year);
                datePicker.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_DARK;
        dateAcquired = new DatePickerDialog(getContext(), style, dateSetListener, year, month, day);

        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dateAcquired.show();
            }
        });
        //To upload and Change an Image
        changeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                activityResultLauncher_Camera.launch(takePicture);
            }
        });

        //Result for Camera
        activityResultLauncher_Camera = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                Bundle extras = result.getData().getExtras();
                if (extras != null) {
                    Uri imageUri;
                    imageBitmap = (Bitmap) extras.get("data");

                    userImage.setImageBitmap(imageBitmap);
                    imageSet = true;
                }

            }
        });


        year_Textview.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (year_Textview.length() == 0) {
                    year_Textview.setText("2010");
                    yearBar.setProgress(2010);
                }
            }
        });

        year_Textview.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (year_Textview.length() > 3) {
                    int year = Integer.parseInt(year_Textview.getText() + "");
                    if (year < 1874) {
                        year_Textview.setText("1874");
                        yearBar.setProgress(1874);
                    }
                    if (year > 2022) {
                        year_Textview.setText("2022");
                        yearBar.setProgress(2022);
                    }
                    if (year > 1873 && year < 2023) {
                        yearBar.setProgress(year);
                    }
                }
            }
        });

        yearBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                year_Textview.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private String makeDateString(int day, int month, int year) {
        return getMonthFormat(month) + " " + day + " " + year;
    }

    private String getMonthFormat(int month) {
        switch (month) {
            case 1:
                return "JAN";
            case 2:
                return "FEB";
            case 3:
                return "MAR";
            case 4:
                return "APR";
            case 5:
                return "MAY";
            case 6:
                return "JUN";
            case 7:
                return "JUL";
            case 8:
                return "AUG";
            case 9:
                return "SEP";
            case 10:
                return "OCT";
            case 11:
                return "NOV";
            default:
                return "DEC";
        }
    }
    private void storeCoin() {
        try {
            Model_Coin model_coin = new Model_Coin(Integer.parseInt(year_Textview.getText().toString()),
                    Integer.parseInt(mintage_Textview.getText().toString()),
                    spinnerMaterial.getSelectedItem().toString(),
                    alternate_Textview.getText().toString(),
                    observe_Textview.getText().toString(),
                    reverse_Textview.getText().toString(),
                    spinnerVariant.getSelectedItem().toString(),
                    spinnerValue.getSelectedItem().toString(),
                    String.valueOf(coinID),
                    datePicker.getText().toString());
            model_coin.setCoinID(coinID);
            int selectedPosition = spinnerCollection.getSelectedItemPosition()-1;
            if(selectedPosition == -1)
            {
                localDB.addCoin(model_coin,0);
            }
            else
            {
                localDB.addCoin(model_coin,allUserCollections.get(selectedPosition).getCollectionID());
            }

        }catch (Exception e)
        {
            Toast.makeText(getContext(), "database add", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean savePhotoToInternalStorage() {
        //Get image number
        FileOutputStream out;
        try {
            Context context = getContext();
            assert context != null;
            out = context.openFileOutput(coinID + ".jpg", Context.MODE_PRIVATE);
            imageBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
            return true;
        } catch (IOException e) {

            return false;
        }
    }

    private void retrieveImage(int imageID)
    {
        String name = imageID +".jpg";
        try{
            Context context = getContext();
            FileInputStream fis = context.openFileInput(name);
            Bitmap b = BitmapFactory.decodeStream(fis);
            userImage.setImageBitmap(b);
            fis.close();

        }
        catch(Exception e){
        }

        //Delete coin
        localDB.deleteCoin(coinID);

        requireContext().deleteFile(name);
    }

    private void backActivity() {
        Bundle bundle = new Bundle();
        bundle.putInt("StartPage",1);
        findNavController(Objects.requireNonNull(getParentFragmentManager().findFragmentById(R.id.fragmentContainerView2))).
                setGraph(R.navigation.collection_navigation,bundle);

    }
}

