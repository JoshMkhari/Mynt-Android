package com.example.mynt.collectionsActivity;

import static androidx.navigation.fragment.NavHostFragment.findNavController;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.mynt.R;
import com.example.mynt.collectionsActivity.models.Model_User;
import com.example.mynt.collectionsActivity.models.User_Data;
import com.example.mynt.dataAccessLayer.Database_Lite;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Calendar;
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
    private Model_User model_user;//(Section, 2021)
    private Intent i;

    private FirebaseAuth mAuth;
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

        OnBackPressedCallback callback = new OnBackPressedCallback(true) {//(Анатолий К.,2020)
            @Override
            public void handleOnBackPressed() {
                backActivity();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(),callback);//(Анатолий К.,2020)


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
                       model_user = new Model_User(email.getText().toString(),password.getText().toString(),1);
                       mAuth = FirebaseAuth.getInstance();
                       mAuth.createUserWithEmailAndPassword(model_user.getEmail(), model_user.getPassword()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                           @Override
                           public void onComplete(@NonNull Task<AuthResult> task) {
                               if (task.isSuccessful()) {
                                   // Sign in success, update UI with the signed-in user's information
                                   //Log.d(TAG, "createUserWithEmail:success");
                                   User_Data.firebaseUser = mAuth.getCurrentUser();
                                   User_Data.currentUser = model_user;
                                   Database_Lite db = new Database_Lite(getContext());//(freecodecamp,2020)
                                   db.addUser(model_user);
                                   User_Data.uploadAllLocalData(getContext());
                                   i = new Intent(getContext(), Activity_Collections.class);
                                   i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                   startActivity(i);
                               } else {
                                   // If sign in fails, display a message to the user.
                                   //Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                   Toast.makeText(getContext(), "Authentication failed.",
                                           Toast.LENGTH_SHORT).show();
                               }
                           }
                       });
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
        Bundle bundle = new Bundle();//(valerybodak,2020)
        bundle.putInt("StartPage",0);
        findNavController(Objects.requireNonNull(getParentFragmentManager().findFragmentById(R.id.fragmentContainerView2))).
                setGraph(R.navigation.collection_navigation,bundle);//(developer Android NavController, n.d)
    }

}