package com.example.mynt.addActivity;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mynt.R;
import com.example.mynt.coinsActivity.models.Model_Coin;
import com.example.mynt.collectionsActivity.Activity_Collections;
import com.example.mynt.goalsActivity.Model_Goals;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

public class Activity_Add extends AppCompatActivity {
    private Spinner spinnerValue, spinnerMaterial, spinnerVariant, spinnerCollection;
    private SeekBar yearBar;
    private EditText year_Textview, alternate_Textview, mintage_Textview, observe_Textview, reverse_Textview;
    private ImageButton add_Button, changeImage;
    private ImageView userImage;
    private ActivityResultLauncher<Intent> activityResultLauncher_Camera;
    private ActivityResultLauncher<Intent> activityResultLauncher_Collection;
    private  Bitmap imageBitmap;
    private Model_Goals model_goals;

    private Boolean imageSet;
    private OutputStream outputStream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        //Assigning views to variables
        //spinners
        spinnerValue = findViewById(R.id.spinner_Values);
        spinnerMaterial = findViewById(R.id.spinner_Material);
        spinnerVariant = findViewById(R.id.spinner_Variant);
        spinnerCollection = findViewById(R.id.spinner_collection);

        //SeekBar
        yearBar = findViewById(R.id.seekBar_year);

        //EditTexts
        year_Textview = findViewById(R.id.yearValueEditText);
        alternate_Textview = findViewById(R.id.alternateName_EditText);
        mintage_Textview = findViewById(R.id.mintageNum_editText);
        observe_Textview = findViewById(R.id.observe_EditText);
        reverse_Textview = findViewById(R.id.reverse_EditText);

        //ImageButton
        add_Button = findViewById(R.id.imageview_blockTitle_addCoin);
        userImage = findViewById(R.id.userImage);
        changeImage = findViewById((R.id.changePicture));

        //Listeners
        setUpListeners();

        year_Textview.setText("2010");

        ArrayList<String> userCollections = new ArrayList<>();
        userCollections.add("Create New Collection");

        //Populate User Collections now


        //Array Adapters
        //Value

        ArrayAdapter<CharSequence> adapterValue = ArrayAdapter.createFromResource(getApplicationContext(),R.array.Value, R.layout.spinner_item);
        adapterValue.setDropDownViewResource(R.layout.spinner_item);
        spinnerValue.setAdapter(adapterValue);

        ArrayAdapter<CharSequence> adapterMaterial = ArrayAdapter.createFromResource(getApplicationContext(),R.array.Material, R.layout.spinner_item);
        adapterMaterial.setDropDownViewResource(R.layout.spinner_item);
        spinnerMaterial.setAdapter(adapterMaterial);

        ArrayAdapter<CharSequence> adapterVariant = ArrayAdapter.createFromResource(getApplicationContext(),R.array.Variants, R.layout.spinner_item);
        adapterVariant.setDropDownViewResource(R.layout.spinner_item);
        spinnerVariant.setAdapter(adapterVariant);

        ArrayAdapter<String> adapterCollection = new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_item, userCollections);
        adapterCollection.setDropDownViewResource(R.layout.spinner_item);
        spinnerCollection.setAdapter(adapterCollection);

    }


    public void setUpListeners() {

        //To upload and Change an Image
        changeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                activityResultLauncher_Camera.launch(takePicture);
            }
        });

        //Result for Collection
        activityResultLauncher_Collection = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode()==20)
                {
                    Intent i = result.getData();
                    if(i != null)
                    {
                        String collectionName = i.getStringExtra("collectionName");
                        String target = i.getStringExtra("target");
                        model_goals.setCollectionName(collectionName);
                        model_goals.setTarget(Integer.parseInt(target));
                    }
                }
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

        //Adding a coin to the database
        add_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Check if picture is taken?
                if(imageSet)
                {
                    //Check if a collection has to be made
                    if(spinnerCollection.getSelectedItemPosition()==0)
                    {
                        model_goals = new Model_Goals("tobeDecided",0,0);
                        Intent createCollection = new Intent(getApplicationContext(), Activity_Collections.class);
                        activityResultLauncher_Collection.launch(createCollection);
                    }
                    //check if a mintage was placed
                    if(mintage_Textview.getText().length()>0)
                    {
                        //Get coin ID
                        int coinID = 0;
                        if(savePhotoToInternalStorage(coinID))
                        {
                            Model_Coin model_coin = new Model_Coin(Integer.parseInt(year_Textview.getText().toString()),
                                    Integer.parseInt(mintage_Textview.getText().toString()),
                                    spinnerMaterial.getSelectedItemPosition(),
                                    alternate_Textview.getText().toString(),
                                    observe_Textview.getText().toString(),
                                    reverse_Textview.getText().toString(),
                                    spinnerVariant.getSelectedItemPosition(),
                                    spinnerValue.getSelectedItemPosition(),coinID+"");
                        }

                    }
                    Toast.makeText(getApplicationContext(),"Set Mintage",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Set image nest time",Toast.LENGTH_SHORT).show();
                }


               // File dir = new File(filepath.getAbsolutePath() + "/Images/");
                //dir.mkdir();
                //File file = new File(dir, System.currentTimeMillis() + ".jpg");

                //Database stuff here XD
                //Model_Coin coin = new Model_Coin(Integer.parseInt(year_Textview.getText().toString()),Integer.parseInt(mintage_Textview.getText().toString()),spinnerMaterial.getSelectedItemPosition(),alternate_Textview.getText().toString(),observe_Textview.getText().toString(),reverse_Textview.getText().toString(),spinnerVariant.getSelectedItemPosition(),spinnerValue.getSelectedItemPosition(),);
                //if Successful
                //Intent i = new Intent(getApplicationContext(), Activity_Main.class);
                // i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                //startActivity(i);
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

    private boolean savePhotoToInternalStorage(int imageNumber)
    {
        //Get image number

        FileOutputStream out;
        try {
            Context context = Activity_Add.this;
            out = context.openFileOutput(imageNumber+".jpg", MODE_PRIVATE);
            imageBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
            return true;
        }catch (IOException e)
        {

            return false;
        }
    }

}