package com.example.mynt.addActivity;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.os.Build.VERSION.SDK_INT;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
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
import com.example.mynt.Activity_Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class Activity_Add extends AppCompatActivity {
    private Spinner spinnerValue, spinnerMaterial, spinnerVariant, spinnerCollection;
    private SeekBar yearBar;
    private EditText year_Textview, alternate_Textview, mintage_Textview, observe_Textview, reverse_Textview;
    private ImageButton add_Button, changeImage;
    private ImageView userImage;
    private ActivityResultLauncher<Intent> activityResultLauncher;
    private  Bitmap imageBitmap;

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
        userCollections.add("hello");
        userCollections.add("from");
        //Array Adapters
        //Value
        ArrayAdapter<CharSequence> adapterValue = ArrayAdapter.createFromResource(getApplicationContext(),R.array.Value, android.R.layout.simple_spinner_item);
        adapterValue.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerValue.setAdapter(adapterValue);

        ArrayAdapter<CharSequence> adapterMaterial = ArrayAdapter.createFromResource(getApplicationContext(),R.array.Material, android.R.layout.simple_spinner_item);
        adapterMaterial.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMaterial.setAdapter(adapterMaterial);

        ArrayAdapter<CharSequence> adapterVariant = ArrayAdapter.createFromResource(getApplicationContext(),R.array.Variants, android.R.layout.simple_spinner_item);
        adapterVariant.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerVariant.setAdapter(adapterVariant);

        ArrayAdapter<String> adapterCollection = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,userCollections);
        adapterCollection.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCollection.setAdapter(adapterCollection);

    }


   private boolean savePhotoToInternalStorage()
    {
        //Get image number
        int imageNumber = 0;//Check last coin ID then +1

        FileOutputStream out;
        try {
            Context context = Activity_Add.this;
            out = context.openFileOutput(imageNumber+".jpg",context.MODE_PRIVATE);
            imageBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
            return true;
        }catch (IOException e)
        {

            return false;
        }
    }

    private Uri saveImage(Bitmap image, Context context) {
        File imagesFolder = new File(context.getCacheDir(),"images");
        Uri uri = null;
        if(checkPermission())
        {
            try{
                imagesFolder.mkdirs();
                File file = new File(imagesFolder,"captured_image.jpg");
                FileOutputStream stream = new FileOutputStream(file);
                image.compress(Bitmap.CompressFormat.JPEG,100,stream);
                stream.flush();
                stream.close();
                uri = FileProvider.getUriForFile(context.getApplicationContext(),"com.example.mynt"+".provider",file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        return uri;
    }

    private boolean checkPermission() {
        if (SDK_INT >= Build.VERSION_CODES.R) {
            return Environment.isExternalStorageManager();
        } else {
            int result = ContextCompat.checkSelfPermission(Activity_Add.this, READ_EXTERNAL_STORAGE);
            int result1 = ContextCompat.checkSelfPermission(Activity_Add.this, WRITE_EXTERNAL_STORAGE);
            return result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED;
        }
    }


    public void setUpListeners() {
        changeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                activityResultLauncher.launch(takePicture);
            }
        });

        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                Bundle extras = result.getData().getExtras();
                if (extras != null) {
                    Uri imageUri;
                    imageBitmap = (Bitmap) extras.get("data");

                    userImage.setImageBitmap(imageBitmap);
                    /*
                    WeakReference<Bitmap> highQualityBitmap = new WeakReference<>(Bitmap.createScaledBitmap(imageBitmap,
                            imageBitmap.getHeight(),imageBitmap.getWidth(),false).copy(
                            Bitmap.Config.RGB_565,true));

                    Bitmap bm = highQualityBitmap.get();
                    imageUri = saveImage(bm, getApplicationContext());

                    //this uri is what we need for the images
                    userImage.setImageURI(imageUri);

                     */
                }

            }
        });

        add_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //storing image
                if(savePhotoToInternalStorage())
                {
                    Toast.makeText(getApplicationContext(),"Saved",Toast.LENGTH_SHORT).show();
                }
                savePhotoToInternalStorage();
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

}