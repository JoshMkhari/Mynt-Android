package com.example.mynt.collectionsActivity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mynt.R;
import com.example.mynt.collectionsActivity.adapters.Adapter_HomeActFragment;
import com.example.mynt.collectionsActivity.models.Model_User;
import com.example.mynt.dataAccessLayer.Database_Lite;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.

 * create an instance of this fragment.
 */
public class Fragment_home_main extends Fragment {
    //private TabLayout tabLayout_main;
    private ViewPager2 viewPager2_main;
    private Adapter_HomeActFragment fragmentAdapter;
    private Model_User user;
    private View home;
    private FragmentManager fragmentManager;
    private ArrayList<Model_User> users;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        home = inflater.inflate(R.layout.fragment_home, container, false);
        viewPager2_main = home.findViewById(R.id.main_act_viewPager2);
        user = new Model_User();

        Database_Lite db = new Database_Lite(getContext());

        users = db.getAllUsers();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getState() == 1) {
                user = users.get(i);
            }
        }

        //assert getArguments() != null;
        //String testuser = getArguments().getString("User");
        //Toast.makeText(getContext(), testuser, Toast.LENGTH_SHORT).show();

        //Comment
        fragmentManager = getParentFragmentManager();
        fragmentAdapter = new Adapter_HomeActFragment(fragmentManager, getLifecycle(), user.getUserID());
        viewPager2_main.setAdapter((fragmentAdapter));


        //Bundle extras = getIntent().getExtras();
        //if (extras != null) {
           // int page = extras.getInt("page");
           // viewPager2_main.setCurrentItem(1);
            //The key argument here must match that used in the other activity
        //}


        return home;
    }
}