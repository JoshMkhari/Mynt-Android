package com.example.mynt.collectionsActivity;

import static androidx.navigation.fragment.NavHostFragment.findNavController;

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
import android.widget.Toast;

import com.example.mynt.R;
import com.example.mynt.collectionsActivity.models.Model_User;
import com.example.mynt.dataAccessLayer.Database_Lite;

import java.util.ArrayList;
import java.util.Objects;

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
    private View registerView;
    private Model_User model_user;
    private Intent i;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        registerView = inflater.inflate(R.layout.fragment_register, container, false);

        email = registerView.findViewById(R.id.RegisterEmail_EditText);
        password = registerView.findViewById(R.id.RegisterPassword_EditText);
        confirmPassword = registerView.findViewById(R.id.RegisterConfirmPassword_EditText);

        loginWithEmail = registerView.findViewById(R.id.RegisterLoginEmail_Button);
        signUp = registerView.findViewById(R.id.SignEmail_Button);
        close = registerView.findViewById(R.id.RegisterClose_button);

        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                backActivity();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(),callback);


        SignUp();
        LoginWithEmail();
        ReturnToLibrary();
        return registerView;
    }

    private void ReturnToLibrary(){

        close.setOnClickListener(v -> {
            //intent take me to library
            backActivity();
        });

    }

    private void SignUp(){
       signUp.setOnClickListener(v -> {
           if (email.getText().toString().length() > 3) {
               if (password.getText().toString().length() > 7) {
                   if (confirmPassword.getText().toString().equals(password.getText().toString())) {
                       // Additional User Feedback
                       ArrayList<Model_User> users = new ArrayList<>();
                       Database_Lite db = new Database_Lite(getContext());
                       users = db.getAllUsers();

                       model_user = new Model_User();
                       model_user.setEmail(email.getText().toString());
                       model_user.setPassword(password.getText().toString());

                       boolean emailFound = false;
                       for (int i = 0; i < users.size(); i++) {
                           if (users.get(i).getEmail().equals(model_user.getEmail())) {
                               Toast.makeText(getContext(), "Email already registered", Toast.LENGTH_SHORT).show();//(Alexander, 2016).
                               emailFound = true;
                           }
                       }
                       if (!emailFound)
                           if (db.addUser(model_user)) {
                               db.updateState(model_user);
                               Toast.makeText(getContext(), "An account has been created successfully for " + email.getText().toString() + ".", Toast.LENGTH_SHORT).show();//(Alexander, 2016).
                               i = new Intent(getContext(), Activity_Collections.class);
                               i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                               startActivity(i);
                           }
                       //Additional User Feedback
                   }else
                       Toast.makeText(getContext(), "A password length must be 8 characters", Toast.LENGTH_LONG).show();//(Alexander, 2016).
               }else
                   Toast.makeText(getContext(), "A email address has not been entered.", Toast.LENGTH_LONG).show();//(Alexander, 2016).
           }else
               Toast.makeText(getContext(), "ERROR: This account has not been created successfully.", Toast.LENGTH_SHORT).show();//(Alexander, 2016).
       });
    }

    private void LoginWithEmail(){

    loginWithEmail.setOnClickListener(v -> Navigation.findNavController(registerView).navigate(R.id.action_fragment_Register_to_fragment_Login));


    }

    private void backActivity() {
        Bundle bundle = new Bundle();
        bundle.putInt("StartPage",0);
        findNavController(Objects.requireNonNull(getParentFragmentManager().findFragmentById(R.id.fragmentContainerView2))).
                setGraph(R.navigation.collection_navigation,bundle);
    }

}