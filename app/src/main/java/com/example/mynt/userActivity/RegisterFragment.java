package com.example.mynt.userActivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.mynt.MainActivity;
import com.example.mynt.R;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class RegisterFragment extends Fragment {
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

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //intent take me to library
                Intent i = new Intent(getContext(), MainActivity.class);
                startActivity(i);
                //Navigation.findNavController(registerView).navigate(R.id.action_login);
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Signing Up Code here",Toast.LENGTH_LONG).show();
            }
        });

        loginWithEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(registerView).navigate(R.id.action_login);
            }
        });
        return registerView;
    }
}