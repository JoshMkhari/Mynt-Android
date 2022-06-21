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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.example.mynt.R;
import com.example.mynt.collectionsActivity.models.ModelFireBaseCoin;
import com.example.mynt.collectionsActivity.models.Model_Coin;
import com.example.mynt.collectionsActivity.models.Model_Collections;
import com.example.mynt.collectionsActivity.models.Model_Date;
import com.example.mynt.collectionsActivity.models.User_Data;
import com.example.mynt.dataAccessLayer.Database_Lite;
import com.example.mynt.collectionsActivity.models.Model_User;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class Fragment_Add extends Fragment {

    private Spinner spinnerValue, spinnerCollection;
    //private Spinner spinnerMaterial, spinnerVariant;
    private SeekBar yearBar;
    private EditText year_Textview;
    //private EditText alternate_Textview, mintage_Textview, observe_Textview, reverse_Textview;
    private ImageButton changeImage;
    private ImageView userImage;
    private byte[] ImageByteArray;
    private ActivityResultLauncher<Intent> activityResultLauncher_Camera;
    private Bitmap imageBitmap;
    private Boolean imageSet = false;
    private Database_Lite localDB;
    private int coinID;
    private Button datePicker;
    private DatePickerDialog dateAcquired;
    private Model_User model_user;//(Section, 2021)
    private ArrayList<Model_Collections> allUserCollections;
    private boolean done;
    private String dateAq;
    private Model_Date model_date;//(Shabbir Dhangot,2016)

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View add = inflater.inflate(R.layout.fragment_add, container, false);
        //Retrieve bundles
        model_user = User_Data.currentUser; //(Section, 2021)
        model_date = new Model_Date();//(Shabbir Dhangot,2016)
        assert getArguments() != null;
        model_user.setUserID(getArguments().getInt("User"));//(valerybodak,2020)

        //Assigning views to variables
        //spinners
        spinnerValue = add.findViewById(R.id.spinner_Values);
        //spinnerMaterial = add.findViewById(R.id.spinner_Material);
        //spinnerVariant = add.findViewById(R.id.spinner_Variant);
        spinnerCollection = add.findViewById(R.id.spinner_collection);

        //SeekBar
        yearBar = add.findViewById(R.id.seekBar_year);

        //EditTexts
        year_Textview = add.findViewById(R.id.yearValueEditText);
        //alternate_Textview = add.findViewById(R.id.alternateName_EditText);
        //mintage_Textview = add.findViewById(R.id.mintageNum_editText);
        //observe_Textview = add.findViewById(R.id.observe_EditText);
        //reverse_Textview = add.findViewById(R.id.reverse_EditText);


        //ImageButton
        ImageButton add_Button = add.findViewById(R.id.imageview_blockTitle_addCoin);
        ImageButton back = add.findViewById(R.id.image_button_back_coins);
        changeImage = add.findViewById((R.id.changePicture));

        //ImageView
        userImage = add.findViewById(R.id.userImage);
        //Button
        datePicker = add.findViewById(R.id.datePickerButton);
        datePicker.setText(getTodaysDate());
        dateAq = datePicker.getText().toString();
        setupDateAq();

        //Listeners
        setUpListeners();

        //Database
        localDB = new Database_Lite(getContext());//(freecodecamp,2020)

        allUserCollections = localDB.getAllCollections();

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

        ArrayAdapter<CharSequence> adapterValue = ArrayAdapter.createFromResource(getContext(), R.array.Value, R.layout.spinner_item); //(Coding in flow, 2017)
        adapterValue.setDropDownViewResource(R.layout.spinner_item);//(Coding in flow, 2017)
        spinnerValue.setAdapter(adapterValue);

        ArrayAdapter<CharSequence> adapterMaterial = ArrayAdapter.createFromResource(getContext(), R.array.Material, R.layout.spinner_item);//(Coding in flow, 2017)
        adapterMaterial.setDropDownViewResource(R.layout.spinner_item);//(Coding in flow, 2017)
        //spinnerMaterial.setAdapter(adapterMaterial);

        ArrayAdapter<CharSequence> adapterVariant = ArrayAdapter.createFromResource(getContext(), R.array.Variants, R.layout.spinner_item);//(Coding in flow, 2017)
        adapterVariant.setDropDownViewResource(R.layout.spinner_item);//(Coding in flow, 2017)
        //spinnerVariant.setAdapter(adapterVariant);

        ArrayAdapter<String> adapterCollection = new ArrayAdapter<>(getContext(), R.layout.spinner_item, userCollections);//(Coding in flow, 2017)
        adapterCollection.setDropDownViewResource(R.layout.spinner_item);//(Coding in flow, 2017)
        spinnerCollection.setAdapter(adapterCollection);

        OnBackPressedCallback callback = new OnBackPressedCallback(true) {//(Анатолий К.,2020)
            @Override
            public void handleOnBackPressed() {
                backActivity();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(),callback);//(Анатолий К.,2020)
        //Activity_Collections.OnB
        back.setOnClickListener(v -> backActivity());

        add_Button.setOnClickListener(v -> {

            //Check if picture is taken?
            if (imageSet) {
                    storeCoin();
                    if (spinnerCollection.getSelectedItemPosition() == 0) {//A new collection needs to be made
                        Bundle bundle = new Bundle();
                        bundle.putInt("User", model_user.getUserID());
                        bundle.putInt("Task", 1);
                        bundle.putInt("ImageID",coinID);
                        Navigation.findNavController(add).navigate(R.id.action_fragment_Add_to_fragment_Collections2,bundle);
                    }else
                    {
                        Toast.makeText(getContext(), "Storing collectionCoin", Toast.LENGTH_SHORT).show();//(Alexander, 2016).
                        Intent home = new Intent(getContext(),Activity_Collections.class);
                        home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(home);
                    }

            } else {
                Toast.makeText(getContext(), "Please take a image", Toast.LENGTH_SHORT).show();//(Alexander, 2016).
            }
        });

        return add;
    }

    private void setupDateAq() {//(Code With Cal, 2020)

        ArrayList<Integer> spaces = new ArrayList<>();
        for (int i = 0; i < dateAq.length(); i++) {
            if(dateAq.charAt(i) == ' ')
            {
                spaces.add(i);
            }
        }


        String monthInt = model_date.setupMonthAq(dateAq.substring(0,spaces.get(0)));//(Code With Cal, 2020)
        int subStringDistance;
        subStringDistance = spaces.get(1)-spaces.get(0)-1;
        int start;
        start = spaces.get(0)+1;
        String dayInt = dateAq.substring(start,start+subStringDistance);

        start = spaces.get(1)+1;
        String yearInt = dateAq.substring(start);

        dateAq = monthInt + '/' + dayInt + '/' + yearInt;
    }

    private String getTodaysDate() {//(Code With Cal, 2020)

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        return model_date.makeDateString(day, month, year,true);

    }
    //Result for Collection


    public void setUpListeners() {

        DatePickerDialog.OnDateSetListener dateSetListener = (view, year, month, day) -> {
            month = month + 1;
            String date = model_date.makeDateString(day, month, year,false);//(Code With Cal, 2020)
            dateAq = month+"/"+day+"/"+year;
            datePicker.setText(date);
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_DARK;
        dateAcquired = new DatePickerDialog(getContext(), style, dateSetListener, year, month, day);
        dateAcquired.getDatePicker().setMaxDate(new Date().getTime());

        datePicker.setOnClickListener(v -> dateAcquired.show());



        //To upload and Change an Image
        changeImage.setOnClickListener(v -> {//(Android Coiding, 2019)
            Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            activityResultLauncher_Camera.launch(takePicture);
        });

        //Result for Camera
        activityResultLauncher_Camera = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {//(Android Coiding, 2019)
            assert result.getData() != null;
                Bundle extras = result.getData().getExtras();
            if (extras != null) {
                imageBitmap = (Bitmap) extras.get("data");//(Android Coiding, 2019)
                userImage.setImageBitmap(imageBitmap);//(Android Coiding, 2019)
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                ImageByteArray = stream.toByteArray();
                imageSet = true;
            }
        });


        year_Textview.setOnFocusChangeListener((v, hasFocus) -> {
            if (year_Textview.length() == 0) {
                year_Textview.setText("2010");
                yearBar.setProgress(2010);
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

    private void storeCoin() {
        Thread coinStore = new Thread(){
            public void run()
            {
                try {
                    Model_Coin model_coin = new Model_Coin(Integer.parseInt(year_Textview.getText().toString()),//(Section, 2021)
                            0,
                            "POE",
                            "POE",
                            "POE",
                            "POE",
                            "POE",
                            spinnerValue.getSelectedItem().toString(),
                            ImageByteArray,
                            dateAq);
                    model_coin.setCoinID(coinID);
                    int selectedPosition = spinnerCollection.getSelectedItemPosition()-1;
                    if(selectedPosition == -1)
                    {
                        localDB.addCoin(model_coin,0);
                        Calendar cal = Calendar.getInstance();
                        String lastSync = cal.getTime().toString();
                        User_Data.currentUser.setLastSync(lastSync);
                        localDB.updateUserLastSync(User_Data.currentUser);
                    }
                    else
                    {
                        localDB.addCoin(model_coin,allUserCollections.get(selectedPosition).getCollectionID());
                    }
                }catch (Exception e)
                {
                    Toast.makeText(getContext(), "database add", Toast.LENGTH_SHORT).show();//(Alexander, 2016).
                }
            }
        };
        coinStore.start();
    }


    private void retrieveImage(int imageID) //( Philipp Lackner, 2021)
    {
        //Get all coins
        ArrayList<Model_Coin> allCoins = localDB.getAllCoins();
        Model_Coin foundCoin = null;
        boolean coinFound = false;
        for (int i = 0; i < allCoins.size(); i++) {
            if(allCoins.get(i).getCoinID() == imageID)
            {
                foundCoin = allCoins.get(i);
                coinFound = true;
                break;
            }
        }
        if(coinFound)
        {
            assert foundCoin != null;
            Bitmap bmp = BitmapFactory.decodeByteArray(foundCoin.getImageId(), 0, foundCoin.getImageId().length);

            userImage.setImageBitmap(bmp);
            localDB.deleteCoin(coinID);
        }

    }

    private void backActivity() {
        Bundle bundle = new Bundle();//(valerybodak,2020)
        bundle.putInt("StartPage",1);
        findNavController(Objects.requireNonNull(getParentFragmentManager().findFragmentById(R.id.fragmentContainerView2))).
                setGraph(R.navigation.collection_navigation,bundle);//(developer Android NavController, n.d)

    }
}

