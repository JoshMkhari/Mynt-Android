package com.example.mynt.collectionsActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mynt.R;
import com.example.mynt.collectionsActivity.models.Model_Coin;
import com.example.mynt.collectionsActivity.models.Model_Coins_List;
import com.example.mynt.dataAccessLayer.Database_Lite;

import java.util.ArrayList;

public class Fragment_Coin_Details extends Fragment {

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View details = inflater.inflate(R.layout.fragment_coin_details, container, false);
        ImageView coinImage = details.findViewById(R.id.CoinDetails_Image);
        TextView mintage = details.findViewById(R.id.CoinDetails_Mintage_TextView);
        TextView observe = details.findViewById(R.id.CoinDetails_Observe_TextView);
        TextView reverse = details.findViewById(R.id.CoinDetails_Reverse_TextView);
        TextView circulation = details.findViewById(R.id.CoinDetails_Circulation_TextView);
        TextView type = details.findViewById(R.id.CoinDetails_Type_TextView);
        TextView points = details.findViewById(R.id.CoinDetails_Points);
        TextView pageTitle = details.findViewById(R.id.CoinDetails_PageTitle);

        ImageButton back;
        back = details.findViewById(R.id.CoinDetails_back);

        assert getArguments() != null;
        int task = getArguments().getInt("Task");
        int coinID = getArguments().getInt("CoinID");

        Database_Lite db = new Database_Lite(getContext());
        Model_Coin model_coin = null;
        ArrayList<Model_Coin> dbCoins;
        dbCoins = db.getAllCoins();

        for (int i=0; i<dbCoins.size(); i++)
        {
            if(dbCoins.get(i).getCoinID()==coinID)
            {
                model_coin = dbCoins.get(i);
                break;
            }
        }
        coinImage.setBackgroundResource(R.drawable.img_two_rand);
        assert model_coin != null;
        mintage.setText(String.valueOf(model_coin.getMintage()));
        observe.setText(model_coin.getObserve());
        reverse.setText(model_coin.getReverse());
        circulation.setText(model_coin.getReverse());
        type.setText(model_coin.getMaterial());
        String coinTitle = model_coin.getValue()+", " + model_coin.getYear();
        pageTitle.setText(coinTitle);
        points.setText(String.valueOf(1000));

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(task==0)// Fragment was accessed home screen
                {
                    Intent home = new Intent(getContext(),Activity_Collections.class);
                    //home.putExtra("View","library");
                    home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(home);

                }else
                {
                    Navigation.findNavController(details).navigateUp();
                }
            }
        });
        return details;
    }
}