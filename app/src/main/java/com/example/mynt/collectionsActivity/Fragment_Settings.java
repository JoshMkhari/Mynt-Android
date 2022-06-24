package com.example.mynt.collectionsActivity;

import static androidx.navigation.fragment.NavHostFragment.findNavController;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.service.autofill.UserData;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mynt.R;
import com.example.mynt.collectionsActivity.models.Model_User;
import com.example.mynt.collectionsActivity.models.Model_User_Data;
import com.example.mynt.dataAccessLayer.Database_Lite;

import java.util.ArrayList;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_Settings} factory method to
 * create an instance of this fragment.
 */
public class Fragment_Settings extends Fragment {
    private ImageButton back_imageButton;
    private int collectionID,task;
    private ArrayList<Integer> coinIDs;
    private TextView collectionName_textView,pageTitle_textView, progress_textview, goalTitle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View settings = inflater.inflate(R.layout.fragment_settings, container, false);

        EditText userName = settings.findViewById(R.id.editText_username);
        TextView userEmail = settings.findViewById(R.id.text_view_user_email);
        back_imageButton = settings.findViewById(R.id.image_button_back_settings);
        ImageButton editUsernameButton = settings.findViewById(R.id.image_button_updateUsername);
        ImageButton logoutButton = settings.findViewById(R.id.LoginOut_Button);
        ImageView userProfilePic = settings.findViewById(R.id.imageview_user_profile);

        userName.setText(Model_User_Data.currentUser.getUserName());
        userEmail.setText(Model_User_Data.currentUser.getEmail());
        Bitmap bmp = BitmapFactory.decodeByteArray(Model_User_Data.currentUser.getImageId(), 0, Model_User_Data.currentUser.getImageId().length);
        userProfilePic.setImageBitmap(bmp);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Database_Lite db = new Database_Lite(getContext());
                //Delete all data within all Tables
                db.removeUserData();
                Model_User model_user = new Model_User("DefaultUser",null,1);
                model_user.setLastSync(null);
                Model_User_Data.currentUser = model_user;
                db.addUser(model_user);
                backActivity();
            }
        });

        back_imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backActivity();
            }
        });

        OnBackPressedCallback callback = new OnBackPressedCallback(true) {//(Анатолий К.,2020)
            @Override
            public void handleOnBackPressed() {
                backActivity();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(),callback);//(Анатолий К.,2020)
        return settings;
    }

    private void backActivity() {
        Bundle bundle = new Bundle();//(valerybodak,2020)
        bundle.putInt("StartPage",0);
        findNavController(Objects.requireNonNull(getParentFragmentManager().findFragmentById(R.id.fragmentContainerView2))).
                setGraph(R.navigation.collection_navigation,bundle);//(developer Android NavController, n.d)
    }
}