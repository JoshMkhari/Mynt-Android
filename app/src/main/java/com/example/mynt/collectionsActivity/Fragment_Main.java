package com.example.mynt.collectionsActivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.mynt.R;
import com.example.mynt.collectionsActivity.models.Model_User;
import com.example.mynt.dataAccessLayer.Database_Lite;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class Fragment_Main extends Fragment {

    private ImageButton addButton;
    private Model_User user;
    private View main;
    private Bundle bundle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        main = inflater.inflate(R.layout.fragment_main, container, false);
        user = new Model_User();
        assert getArguments() != null;
        user.setUserID(getArguments().getInt("User"));
        addButton = main.findViewById(R.id.image_button_add_coin_main);

        AddCoin();

        return main;
    }

    private void AddCoin(){

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle = new Bundle();
                bundle.putInt("User", user.getUserID());
                Navigation.findNavController(main).navigate(R.id.action_fragment_home_main_to_fragment_Add, bundle);
            }
        });
    }


    }



