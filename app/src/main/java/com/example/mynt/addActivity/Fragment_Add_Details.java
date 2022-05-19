package com.example.mynt.addActivity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.example.mynt.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class Fragment_Add_Details extends Fragment {
    private ArrayList<String> arrayList_values;
    private AutoCompleteTextView textView_forDropdown;

    @Override
    public void onResume() {
        super.onResume();
        textView_forDropdown.findViewById(R.id.textView_forDropdown);

        ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(),R.layout.dropdown_item,arrayList_values);
        textView_forDropdown.setAdapter(arrayAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View addDetails = inflater.inflate(R.layout.fragment_add_details, container, false);

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

        //Values

        return addDetails;
    }
}