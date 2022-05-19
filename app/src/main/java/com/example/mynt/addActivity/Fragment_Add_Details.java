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
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),R.array.Value, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        optionsValue.setAdapter(adapter);
        optionsValue.setOnItemSelectedListener(this);


        return addDetails;
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}