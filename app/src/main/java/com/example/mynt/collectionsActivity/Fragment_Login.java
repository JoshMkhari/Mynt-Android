package com.example.mynt.collectionsActivity;

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

import com.example.mynt.R;
import com.example.mynt.dataAccessLayer.Database_Lite;
import com.example.mynt.collectionsActivity.models.Model_User;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class Fragment_Login extends Fragment {

    private EditText email;
    private EditText password;
    private ImageButton login;
    private ImageButton close;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View loginView = inflater.inflate(R.layout.fragment_login, container, false);

        email = loginView.findViewById(R.id.LoginEmail_EditText);
        password = loginView.findViewById(R.id.LoginPassword_EditText);
        login = loginView.findViewById(R.id.LoginEmail_Button);
        close = loginView.findViewById(R.id.LoginClose_button);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Database_Lite db = new Database_Lite(getContext());
                Model_User model_user = new Model_User();
                model_user.setEmail(email.getText().toString());
                model_user.setPassword(password.getText().toString());
                ArrayList<Model_User> users = new ArrayList<>();
                users = db.getAllUsers();

                for (int i=0; i<users.size(); i++) {
                    if(users.get(i).getUserName().equals(model_user.getUserName()))
                    {
                        if(users.get(i).getPassword().equals(model_user.getPassword()))
                        {
                            //update user state
                            db.updateState(model_user);
                            Intent login = new Intent(getContext(), Activity_Collections.class);
                            login.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(login);
                        }
                    }
                }
                    Toast.makeText(getContext(),"Email and password combination false or email not registered",Toast.LENGTH_LONG).show();
            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(loginView).navigate(R.id.action_fragment_Login_to_fragment_Register);
            }
        });
        return loginView;
    }

}