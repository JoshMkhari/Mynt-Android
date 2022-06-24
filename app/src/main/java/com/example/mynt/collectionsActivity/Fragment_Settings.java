package com.example.mynt.collectionsActivity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mynt.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_Settings} factory method to
 * create an instance of this fragment.
 */
public class Fragment_Settings extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View settings = inflater.inflate(R.layout.fragment_settings, container, false);


        return settings;
    }
}