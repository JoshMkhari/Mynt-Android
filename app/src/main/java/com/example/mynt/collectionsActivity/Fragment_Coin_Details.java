package com.example.mynt.collectionsActivity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mynt.R;

public class Fragment_Coin_Details extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View details = inflater.inflate(R.layout.fragment_coin_details, container, false);
        return details;
    }
}