package com.example.mynt.collectionsActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import com.example.mynt.dataAccessLayer.Database_Lite;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Objects;

public class Fragment_Coin_Details extends Fragment {

    private View details;
    private ImageView coinImage;
    private TextView mintage;
    private TextView observe;
    private TextView reverse;
    private TextView circulation;
    private TextView type;
    private TextView points;
    private TextView pageTitle;
    private ImageButton back;
    private Database_Lite db;
    private Model_Coin model_coin;
    private ArrayList<Model_Coin> dbCoin;
    private ArrayList<Model_Coin> dbCoins;
    private String coinTitle;
    private String name;
    private int task;

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        details = inflater.inflate(R.layout.fragment_coin_details, container, false);
        coinImage = details.findViewById(R.id.CoinDetails_Image);
        mintage = details.findViewById(R.id.CoinDetails_Mintage_TextView);
        observe = details.findViewById(R.id.CoinDetails_Observe_TextView);
        reverse = details.findViewById(R.id.CoinDetails_Reverse_TextView);
        circulation = details.findViewById(R.id.CoinDetails_Circulation_TextView);
        type = details.findViewById(R.id.CoinDetails_Type_TextView);
        points = details.findViewById(R.id.CoinDetails_Points);
        pageTitle = details.findViewById(R.id.CoinDetails_PageTitle);


        back = details.findViewById(R.id.CoinDetails_back);

        assert getArguments() != null;
        task = getArguments().getInt("Task");
        int coinID = getArguments().getInt("CoinID");

        db = new Database_Lite(getContext());
        model_coin = null;
        dbCoins = db.getAllCoins();

        for (int i=0; i<dbCoins.size(); i++)
        {
            if(dbCoins.get(i).getCoinID()==coinID)
            {
                model_coin = dbCoins.get(i);
                break;
            }
        }

        assert model_coin != null;
        mintage.setText(String.valueOf(model_coin.getMintage()));
        observe.setText(model_coin.getObserve());
        reverse.setText(model_coin.getReverse());
        circulation.setText(model_coin.getReverse());
        type.setText(model_coin.getMaterial());
        coinTitle = model_coin.getValue()+", " + model_coin.getYear();
        pageTitle.setText(coinTitle);
        points.setText(String.valueOf(1000));

        name = model_coin.getCoinID() +".jpg";
        try{
            FileInputStream fis = requireContext().openFileInput(name);
            Bitmap b = BitmapFactory.decodeStream(fis);
            coinImage.setImageBitmap(b);
            //holder.coinImage.setImageDrawable(Drawable.createFromPath(file.toString()));
            fis.close();
        }
        catch(Exception e){
            ;
        }


        ReturnToMainDetailsPage();
        return details;
    }
    private void ReturnToMainDetailsPage(){

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


    }


}