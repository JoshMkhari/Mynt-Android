package com.example.mynt.collectionsActivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.mynt.R;
import com.example.mynt.dataAccessLayer.Database_Lite;
import com.example.mynt.collectionsActivity.models.Model_User;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class Fragment_Register extends Fragment {
    private EditText email;
    private EditText password;
    private EditText confirmPassword;
    private ImageButton signUp;
    private ImageButton loginWithEmail;
    private ImageButton close;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View registerView = inflater.inflate(R.layout.fragment_register, container, false);

        email = registerView.findViewById(R.id.RegisterEmail_EditText);
        password = registerView.findViewById(R.id.RegisterPassword_EditText);
        confirmPassword = registerView.findViewById(R.id.RegisterConfirmPassword_EditText);

        loginWithEmail = registerView.findViewById(R.id.RegisterLoginEmail_Button);
        signUp = registerView.findViewById(R.id.SignEmail_Button);
        close = registerView.findViewById(R.id.RegisterClose_button);

        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Intent home = new Intent(getContext(),Activity_Collections.class);
                home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(home);
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(),callback);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //intent take me to library
                Intent i = new Intent(getContext(), Activity_Collections.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Database_Lite db = new Database_Lite(getContext());
                Model_User model_user = new Model_User();
                model_user.setEmail(email.getText().toString());
                model_user.setPassword(password.getText().toString());
                if(db.addUser(model_user))
                {
                    db.updateState(model_user);
                    Intent i = new Intent(getContext(), Activity_Collections.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                }
            }
        });

        loginWithEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(registerView).navigate(R.id.action_fragment_Register_to_fragment_Login);
            }
        });

        return registerView;
    }
}