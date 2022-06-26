package com.example.mynt.collectionsActivity.main;

import static androidx.navigation.fragment.NavHostFragment.findNavController;

import android.media.Image;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.Navigation;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.mynt.R;
import com.example.mynt.collectionsActivity.adapters.Adapter_HomeActFragment;
import com.example.mynt.collectionsActivity.main.search.Adapter_Search;
import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

public class Fragment_Search extends Fragment {

    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    private ImageButton backButton;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View search = inflater.inflate(R.layout.fragment_search, container, false);

        //Comment
        tabLayout = search.findViewById(R.id.search_tab_layout);
        viewPager2 = search.findViewById(R.id.search_viewPager2);
        backButton = search.findViewById(R.id.image_button_back_search);

        //Comment
        FragmentManager fragmentManager = getParentFragmentManager();
        Adapter_Search fragmentAdapter = new Adapter_Search(fragmentManager, getLifecycle());
        viewPager2.setAdapter((fragmentAdapter));

        //Adding Tabs
        tabLayout.addTab((tabLayout.newTab().setText("Value")));
        tabLayout.addTab((tabLayout.newTab().setText("Year")));
        tabLayout.addTab((tabLayout.newTab().setText("Coin")));

        returnToMainPage();
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {//(Анатолий К.,2020)
            @Override
            public void handleOnBackPressed() {
                backActivity();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(),callback);//(Анатолий К.,2020)
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
         @Override
        public void onTabSelected(TabLayout.Tab tab) {
             viewPager2.setCurrentItem(tab.getPosition());
         }

         @Override
         public void onTabUnselected(TabLayout.Tab tab) {

         }

        @Override
         public void onTabReselected(TabLayout.Tab tab) {

        }
        });

        return search;
    }

    private void returnToMainPage()
    {
        backButton.setOnClickListener(v -> backActivity());
    }
    private void backActivity() {
        Bundle bundle = new Bundle();//(valerybodak,2020)
        bundle.putInt("StartPage",1);
        findNavController(Objects.requireNonNull(getParentFragmentManager().findFragmentById(R.id.fragmentContainerView2))).
                setGraph(R.navigation.collection_navigation,bundle);//(developer Android NavController, n.d)
    }
}