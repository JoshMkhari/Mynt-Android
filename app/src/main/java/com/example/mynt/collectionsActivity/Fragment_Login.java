package com.example.mynt.collectionsActivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.mynt.Activity_Main;
import com.example.mynt.R;
import com.example.mynt.collectionsActivity.models.User_Data;
import com.example.mynt.dataAccessLayer.Database_Lite;
import com.example.mynt.collectionsActivity.models.Model_User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.concurrent.Executor;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class Fragment_Login extends Fragment {

    private EditText email;
    private EditText password;
    private ImageButton login;
    private ImageButton close;
    private View loginView;
    private Database_Lite db;
    private Model_User model_user;//(Section, 2021)
    private ArrayList<Model_User> users;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        loginView = inflater.inflate(R.layout.fragment_login, container, false);
        email = loginView.findViewById(R.id.LoginEmail_EditText);
        password = loginView.findViewById(R.id.LoginPassword_EditText);
        login = loginView.findViewById(R.id.LoginEmail_Button);
        close = loginView.findViewById(R.id.LoginClose_button);
        Login();
        ReturnToRegister();
        return loginView;
    }

    private void Login(){
        login.setOnClickListener(v -> {
            db = new Database_Lite(getContext());//(freecodecamp,2020)
            model_user = new Model_User("","",0);
            model_user.setEmail(email.getText().toString());
            model_user.setPassword(password.getText().toString());
            users = new ArrayList<>();
            users = db.getAllUsers();

            if(email.getText().toString().length()<3){
                //Additional User Feedback
                Toast.makeText(getContext(),"ERROR: A email address has not been entered.",Toast.LENGTH_LONG).show();//(Alexander, 2016).
                Toast.makeText(getContext(),"Please enter a email address to proceed.",Toast.LENGTH_SHORT).show();//(Alexander, 2016).
            }else
                if(password.getText().toString().length()<8){

                    //Additional User Feedback
                    Toast.makeText(getContext(),"ERROR: A password has not been entered",Toast.LENGTH_LONG).show();//(Alexander, 2016).
                    Toast.makeText(getContext(),"Please enter a password to proceed.",Toast.LENGTH_SHORT).show();//(Alexander, 2016).
                }else
                {
                    FirebaseAuth mAuth = FirebaseAuth.getInstance();
                    mAuth.signInWithEmailAndPassword(model_user.getEmail(),model_user.getPassword()).addOnCompleteListener(
                            getActivity(), new OnCompleteListener<AuthResult>() { //https://github.com/oemilk/firebase/blob/master/app/src/main/java/com/sh/firebase/authentication/AuthenticationFragment.java
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    User_Data.firebaseUser = mAuth.getCurrentUser();
                                    if(User_Data.firebaseUser != null)
                                    {
                                        User_Data.currentUser = model_user;
                                        //db.addUser(User_Data.currentUser);
                                        User_Data.mergeData(getContext());
                                        //User_Data.mergeData(getContext());
                                        Intent login = new Intent(getContext(), Activity_Collections.class);
                                        login.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        startActivity(login);
                                        //Additional User Feedback
                                        Toast.makeText(getContext(),model_user.getEmail()+ " has logged in successfully.",Toast.LENGTH_LONG).show();//(Alexander, 2016).
                                    }
                                }
                            }
                    );
                }
            //sign in
                });
    }

    private void ReturnToRegister(){
        close.setOnClickListener(v -> Navigation.findNavController(loginView).navigate(R.id.action_fragment_Login_to_fragment_Register));


    }

}