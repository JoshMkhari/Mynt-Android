package com.example.mynt.collectionsActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
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

import com.example.mynt.R;
import com.example.mynt.collectionsActivity.models.Model_Coin;
import com.example.mynt.collectionsActivity.models.Model_UserCoin;
import com.example.mynt.dataAccessLayer.Database_Lite;
import com.example.mynt.collectionsActivity.models.Model_Goals;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

public class Fragment_Add extends Fragment {
    private Spinner spinnerValue, spinnerMaterial, spinnerVariant, spinnerCollection;
    private SeekBar yearBar;
    private EditText year_Textview, alternate_Textview, mintage_Textview, observe_Textview, reverse_Textview;
    private ImageButton add_Button, changeImage;
    private ImageView userImage;
    private ActivityResultLauncher<Intent> activityResultLauncher_Camera;
    private ActivityResultLauncher<Intent> activityResultLauncher_Collection;
    private Bitmap imageBitmap;
    private Model_Goals model_goals;
    private Boolean imageSet;
    private Database_Lite localDB;
    private Model_Collections model_collections;
    private int coinID;
    private Button datePicker;
    private DatePickerDialog dateAcquired;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View add = inflater.inflate(R.layout.fragment_add, container, false);

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
        add_Button = add.findViewById(R.id.imageview_blockTitle_addCoin);
        userImage = add.findViewById(R.id.userImage);
        changeImage = add.findViewById((R.id.changePicture));

        //Button
        datePicker = add.findViewById(R.id.datePickerButton);
        datePicker.setText(getTodaysDate());


        //Listeners
        setUpListeners();

        //Database
        localDB = new Database_Lite(getContext());
        coinID = localDB.getAllCoins().size();
        ;

        year_Textview.setText("2010");

        ArrayList<String> userCollections = new ArrayList<>();
        userCollections.add("Create New Collection");

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


    public void setUpListeners() {
        //Result for Collection
        activityResultLauncher_Collection = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == 20) {
                    Intent i = result.getData();
                    if (i != null) {
                        String collectionName = i.getStringExtra("collectionName");
                        String target = i.getStringExtra("target");
                        model_goals.setCollectionName(collectionName);
                        model_goals.setTarget(Integer.parseInt(target));
                        model_collections = new Model_Collections(model_goals.getCollectionName(), 0, model_goals.getTarget(), coinID);
                        storeCoin();
                    }
                }
            }
        });

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
                ;
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

        //Adding a coin to the database
        add_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Check if picture is taken?
                if (imageSet) {
                    //check if a mintage was placed
                    if (mintage_Textview.getText().length() > 0) {
                        //Check if a collection has to be made
                        if (spinnerCollection.getSelectedItemPosition() == 0) {
                            createCollection();

                        } else {
                            storeCoin();
                        }
                        //Get coin ID

                    }
                    Toast.makeText(getContext(), "Set Mintage", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Set image nest time", Toast.LENGTH_SHORT).show();
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
        if (savePhotoToInternalStorage(coinID)) {
            Model_Coin model_coin = new Model_Coin(Integer.parseInt(year_Textview.getText().toString()),
                    Integer.parseInt(mintage_Textview.getText().toString()),
                    spinnerMaterial.getSelectedItemPosition(),
                    alternate_Textview.getText().toString(),
                    observe_Textview.getText().toString(),
                    reverse_Textview.getText().toString(),
                    spinnerVariant.getSelectedItemPosition(),
                    spinnerValue.getSelectedItemPosition(), coinID + "");
            Model_UserCoin users_coins = new Model_UserCoin(datePicker.getText().toString(), "here", model_coin);
            model_collections.getModel_userArrayList().add(users_coins);
            Toast.makeText(getContext(), "database add", Toast.LENGTH_SHORT).show();
            localDB.addCoin(model_collections);
        }
    }

    private void createCollection() {
        model_goals = new Model_Goals("tobeDecided", 0, 0);
        Intent createCollection = new Intent(getContext(), Activity_Collections.class);
        createCollection.putExtra("create", "toBeDecided");
        activityResultLauncher_Collection.launch(createCollection);


    }

    private boolean savePhotoToInternalStorage(int imageNumber) {
        //Get image number

        FileOutputStream out;
        try {
            Context context = getContext();
            out = context.openFileOutput(imageNumber + ".jpg", Context.MODE_PRIVATE);
            imageBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
            return true;
        } catch (IOException e) {

            return false;
        }
    }
}

