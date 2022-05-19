package com.example.mynt.addActivity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.mynt.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class Fragment_Add_Details extends Fragment implements AdapterView.OnItemSelectedListener {
private Adapter_AddCoin_ListView adapter_addCoin_listView;
private Spinner optionsValue;
    private ArrayList<String> arrayList_values;
    private ArrayList<String> arrayList_Material;
    private ArrayList<String> arrayList_Variety;
    private ArrayList<String> arrayList_Collection;

    @Override
    public void onResume() {
        super.onResume();
      //  adapter_addCoin_listView = new Adapter_AddCoin_ListView(getContext());

        //options.findViewById(R.id.add_coin_listView);

       // options.setAdapter(adapter_addCoin_listView);
        ///

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View addDetails = inflater.inflate(R.layout.fragment_add_details, container, false);

        optionsValue = addDetails.findViewById(R.id.spinner_Values);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),R.array.Collections, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        optionsValue.setAdapter(adapter);
        optionsValue.setOnItemSelectedListener(this);


        return addDetails;
    }

    private void populateMaterial()
    {
        arrayList_Material.add("Bimetallic");
        arrayList_Material.add("Silver");
        arrayList_Material.add("Nickel-plated copper");
        arrayList_Material.add("Nickel");
        arrayList_Material.add("Bronze-plated Steel");
        arrayList_Material.add("Copper-plated Steel");
        arrayList_Material.add("Bronze");
        arrayList_Material.add("Brass");
        arrayList_Material.add("Aluminium-Bronze");
        arrayList_Material.add("Gold");
        arrayList_Material.add("Platinum");

    }
    private void populateValues()
    {
        arrayList_values.add("Half Cent");
        arrayList_values.add("One Cent");
        arrayList_values.add("Two Cent");
        arrayList_values.add("Two and a Half Cent");
        arrayList_values.add("Five Cent");
        arrayList_values.add("Ten Cent");
        arrayList_values.add("Twenty Cent");
        arrayList_values.add("Fifty Cent");
        arrayList_values.add("One Rand");
        arrayList_values.add("Two Rand");
        arrayList_values.add("Five Rand");
        arrayList_values.add("Fifty Rand");
        arrayList_values.add("Tenth Ounce");
        arrayList_values.add("Twentieth Ounce");
        arrayList_values.add("Quarter Ounce");
        arrayList_values.add("Fiftieth Ounce");
        arrayList_values.add("Half Ounce");
        arrayList_values.add("Ounce");
        arrayList_values.add("Two Ounce");
        arrayList_values.add("Five Ounce");
        arrayList_values.add("Kilo");
        arrayList_values.add("Penny");
        arrayList_values.add("Three Pence");
        arrayList_values.add("Six Pence");
        arrayList_values.add("Shilling");
    }

    private void populateVariety()
    {
        arrayList_Variety.add("Proof");
        arrayList_Variety.add("Brilliant Uncirculated");
        arrayList_Variety.add("Brilliant CW privy mark");
    }

    private void populateCollections()
    {
        //Retrieve this users collections list
        arrayList_Collection.add("20th Century");
        arrayList_Collection.add("Roaring 80s");
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}