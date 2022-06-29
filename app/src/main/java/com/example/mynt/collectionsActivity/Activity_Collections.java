package com.example.mynt.collectionsActivity;

import static androidx.navigation.fragment.NavHostFragment.findNavController;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;

import android.os.Bundle;

import com.example.mynt.R;
import com.example.mynt.collectionsActivity.interfaces.Interface_BottomSheet;
import com.example.mynt.collectionsActivity.models.Model_User_Data;

import java.util.Objects;

public class Activity_Collections extends AppCompatActivity implements Interface_BottomSheet {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_collections);

        Bundle bundle = getIntent().getExtras();//(valerybodak,2020)
        int page = 1;
        if(bundle == null)
        {
            bundle = new Bundle();
            bundle.putInt("StartPage",1);
        }
        else
        {
            page = bundle.getInt("StartPage");
            bundle.putInt("StartPage",page);
        }

        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/

        findNavController(Objects.requireNonNull(getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView2))).
                setGraph(R.navigation.collection_navigation,bundle);//(developer Android NavController, n.d)

    }

    @Override
    public void onButtonClicked(int buttonID) {

        Bundle bundle = new Bundle();//(valerybodak,2020)
        bundle.putInt("Task", Model_User_Data.task);
        bundle.putInt("CoinID", Model_User_Data.model_coin.getCoinID());

        //ViewCompat.setTransitionName(coinImage, "recentTransaction");
        findNavController(Objects.requireNonNull(getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView2))).navigate(
                R.id.action_fragment_home_main_to_fragment_Coin_Details,
                bundle);
    }
}