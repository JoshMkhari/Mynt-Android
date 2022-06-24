package com.example.mynt.collectionsActivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.mynt.R;
import com.example.mynt.collectionsActivity.models.Model_User;
import com.example.mynt.collectionsActivity.models.Model_User_Data;
import com.example.mynt.dataAccessLayer.Database_Lite;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class Fragment_Main extends Fragment {

    private ImageButton addButton;
    private Model_User user;//(Section, 2021)
    private View main;
    private Bundle bundle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        main = inflater.inflate(R.layout.fragment_main, container, false);
        user = Model_User_Data.currentUser;
        assert getArguments() != null;
        user.setUserID(getArguments().getInt("User"));
        addButton = main.findViewById(R.id.image_button_add_coin_main);
        TextView userName,userTitle;

        userName = main.findViewById(R.id.text_view_user_main);

        userTitle = main.findViewById(R.id.text_view_user_current);

        Database_Lite db =new Database_Lite(getContext());//(freecodecamp,2020)
        ArrayList<Model_User> allUsers = db.getAllUsers();

        if(user.getEmail().equals("DefaultUser"))
        {
            userTitle.setVisibility(View.INVISIBLE);
            userName.setVisibility(View.INVISIBLE);
        }else
        {
            userName.setText(user.getEmail());
        }

        ImageButton navigateToLibrary;

        navigateToLibrary = main.findViewById(R.id.image_button_main_library);

        navigateToLibrary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Change view to Library

                //Option 1
                Intent switchToLibrary = new Intent(getContext(), Activity_Collections.class);
                Bundle bundle = new Bundle();
                bundle.putInt("StartPage",0);
                switchToLibrary.putExtras(bundle);
                switchToLibrary.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(switchToLibrary);

                //Option 2
                //Slide to the right
            }
        });
        AddCoin();

        return main;
    }

    private void AddCoin(){

        addButton.setOnClickListener(v -> {
            bundle = new Bundle();//(valerybodak,2020)
            bundle.putInt("User", user.getUserID());
            Navigation.findNavController(main).navigate(R.id.action_fragment_home_main_to_fragment_Add, bundle);
        });
    }


    }



