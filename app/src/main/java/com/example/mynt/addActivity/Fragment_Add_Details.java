package com.example.mynt.addActivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Spinner;

import com.example.mynt.Activity_Main;
import com.example.mynt.R;
import com.example.mynt.coinsActivity.models.Model_Coin;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class Fragment_Add_Details extends Fragment {
    private Spinner spinnerValue, spinnerMaterial, spinnerVariant, spinnerCollection;
    private SeekBar yearBar;
    private EditText year_Textview, alternate_Textview, mintage_Textview, observe_Textview, reverse_Textview;
    private ImageButton add_Button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View addDetails = inflater.inflate(R.layout.fragment_add_details, container, false);

        //Assigning views to variables
        //spinners
        spinnerValue = addDetails.findViewById(R.id.spinner_Values);
        spinnerMaterial = addDetails.findViewById(R.id.spinner_Material);
        spinnerVariant = addDetails.findViewById(R.id.spinner_Variant);
        spinnerCollection = addDetails.findViewById(R.id.spinner_collection);

        //SeekBar
        yearBar = addDetails.findViewById(R.id.seekBar_year);

        //EditTexts
        year_Textview = addDetails.findViewById(R.id.yearValueEditText);
        alternate_Textview = addDetails.findViewById(R.id.alternateName_EditText);
        mintage_Textview = addDetails.findViewById(R.id.mintageNum_editText);
        observe_Textview = addDetails.findViewById(R.id.observe_EditText);
        reverse_Textview = addDetails.findViewById(R.id.reverse_EditText);

        //ImageButton
        add_Button = addDetails.findViewById(R.id.imageview_blockTitle_addCoin);

        //Listeners
        setUpListeners();

        year_Textview.setText("2010");

        ArrayList<String> userCollections = new ArrayList<>();
        userCollections.add("hello");
        userCollections.add("from");
        //Array Adapters
        //Value
        ArrayAdapter<CharSequence> adapterValue = ArrayAdapter.createFromResource(getContext(),R.array.Value, android.R.layout.simple_spinner_item);
        adapterValue.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerValue.setAdapter(adapterValue);

        ArrayAdapter<CharSequence> adapterMaterial = ArrayAdapter.createFromResource(getContext(),R.array.Material, android.R.layout.simple_spinner_item);
        adapterMaterial.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMaterial.setAdapter(adapterMaterial);

        ArrayAdapter<CharSequence> adapterVariant = ArrayAdapter.createFromResource(getContext(),R.array.Variants, android.R.layout.simple_spinner_item);
        adapterVariant.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerVariant.setAdapter(adapterVariant);

        ArrayAdapter<String> adapterCollection = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,userCollections);
        adapterCollection.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCollection.setAdapter(adapterCollection);



        return addDetails;
    }



    public void setUpListeners()
    {
        add_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Database stuff here XD
                Model_Coin coin = new Model_Coin(Integer.parseInt(year_Textview.getText().toString()),Integer.parseInt(mintage_Textview.getText().toString()),spinnerMaterial.getSelectedItemPosition(),alternate_Textview.getText().toString(),observe_Textview.getText().toString(),reverse_Textview.getText().toString(),spinnerVariant.getSelectedItemPosition(),spinnerValue.getSelectedItemPosition());
                //if Successful
                Intent i = new Intent(getContext(), Activity_Main.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });

        year_Textview.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(year_Textview.length()==0)
                {
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
                if(year_Textview.length()>3)
                {
                    int year =  Integer.parseInt(year_Textview.getText()+"");
                    if (year<1874)
                    {
                        year_Textview.setText("1874");
                        yearBar.setProgress(1874);
                    }
                    if(year>2022)
                    {
                        year_Textview.setText("2022");
                        yearBar.setProgress(2022);
                    }
                    if(year>1873 && year<2023)
                    {
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