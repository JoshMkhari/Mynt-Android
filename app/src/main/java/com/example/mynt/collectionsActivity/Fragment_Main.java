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

    private ActivityResultLauncher<Intent> activityResultLauncher;
    private ImageButton addButton;
    private Model_User user;
    private View main;
    private Database_Lite db;
    private ArrayList<Model_User> users;
    private String userID;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        main = inflater.inflate(R.layout.fragment_main, container, false);


        AddCoin();
        ViewLoggedInUser();
        
        user = new Model_User();
        addButton = main.findViewById(R.id.image_button_add_coin_main);



        return main;
    }

    private void AddCoin(){

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("User", user.getUserID());
                Navigation.findNavController(main).navigate(R.id.action_fragment_home_main_to_fragment_Add, bundle);
            }
        });
    }

    private void ViewLoggedInUser() {

            db = new Database_Lite(getContext());

            users = db.getAllUsers();
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getState() == 1) {
                    user = users.get(i);
                }
            }

            userID = user.getUserID() + " this";
            Log.d("main", userID);
        }

    }



