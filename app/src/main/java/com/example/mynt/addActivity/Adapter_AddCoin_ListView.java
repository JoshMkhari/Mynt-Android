package com.example.mynt.addActivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;

import com.example.mynt.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class Adapter_AddCoin_ListView extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;

    private ArrayList<String> arrayList_values;
    private ArrayList<String> arrayList_Material;
    private ArrayList<String> arrayList_Variety;
    private ArrayList<String> arrayList_Collection;

    public Adapter_AddCoin_ListView(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        populateValues();
        populateCollections();
        populateMaterial();
        populateVariety();
    }

    @Override
    public int getCount() {
        return 8;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //convertView = inflater.inflate(R.layout.listview_add_coin, null);

        //TextInputLayout dropdown = convertView.findViewById(R.id.dropdown_list);
        //AutoCompleteTextView textView_forDropdown = convertView.findViewById(R.id.textView_forDropdown);

        switch(position)
        {
            //Value
            case 0:
                //ArrayAdapter arrayAdapter = new ArrayAdapter(context,R.layout.dropdown_item,arrayList_values);
                //textView_forDropdown.setAdapter(arrayAdapter);
                break;
            //Material
            case 1:
                //ArrayAdapter arrayMaterialAdapter = new ArrayAdapter(context,R.layout.dropdown_item,arrayList_Material);
                //textView_forDropdown.setAdapter(arrayMaterialAdapter);
                break;
            //Variety
            case 2:
                //ArrayAdapter arrayVarietyAdapter = new ArrayAdapter(context,R.layout.dropdown_item,arrayList_Variety);
               // textView_forDropdown.setAdapter(arrayVarietyAdapter);
                break;
            //Collection
            case 3:
               // ArrayAdapter arrayCollectionAdapter = new ArrayAdapter(context,R.layout.dropdown_item,arrayList_Collection);
               // textView_forDropdown.setAdapter(arrayCollectionAdapter);
                break;
        }
        return null;
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
}
