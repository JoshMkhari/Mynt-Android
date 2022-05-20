package com.example.mynt.mainActivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.mynt.R;
import com.example.mynt.addActivity.Activity_Add;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class Fragment_Home extends Fragment {

    private ActivityResultLauncher<Intent> activityResultLauncher;
    private ImageButton addButton;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View main = inflater.inflate(R.layout.fragment_main, container, false);

        addButton = main.findViewById(R.id.image_button_add_coin_main);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), Activity_Add.class);
                startActivity(i);
            }
        });


        return main;
    }


}