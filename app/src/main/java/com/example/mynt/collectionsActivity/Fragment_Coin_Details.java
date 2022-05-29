package com.example.mynt.collectionsActivity;

import static androidx.navigation.fragment.NavHostFragment.findNavController;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
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
    private ImageButton back;
    private int task;

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        details = inflater.inflate(R.layout.fragment_coin_details, container, false);
        ImageView coinImage = details.findViewById(R.id.CoinDetails_Image);
        TextView mintage = details.findViewById(R.id.CoinDetails_Mintage_TextView);
        TextView observe = details.findViewById(R.id.CoinDetails_Observe_TextView);
        TextView reverse = details.findViewById(R.id.CoinDetails_Reverse_TextView);
        TextView circulation = details.findViewById(R.id.CoinDetails_Circulation_TextView);
        TextView type = details.findViewById(R.id.CoinDetails_Type_TextView);
        TextView points = details.findViewById(R.id.CoinDetails_Points);
        TextView pageTitle = details.findViewById(R.id.CoinDetails_PageTitle);
        back = details.findViewById(R.id.CoinDetails_back);

        assert getArguments() != null;
        task = getArguments().getInt("Task");
        int coinID = getArguments().getInt("CoinID");

        Database_Lite db = new Database_Lite(getContext());
        Model_Coin model_coin = null;
        ArrayList<Model_Coin> dbCoins = db.getAllCoins();

        for (int i = 0; i< dbCoins.size(); i++)
        {
            if(dbCoins.get(i).getCoinID()==coinID)
            {
                model_coin = dbCoins.get(i);
                break;
            }
        }

        ReturnToMainDetailsPage();

        assert model_coin != null;
        mintage.setText(String.valueOf(model_coin.getMintage()));
        observe.setText(model_coin.getObserve());
        reverse.setText(model_coin.getReverse());
        circulation.setText(model_coin.getReverse());
        type.setText(model_coin.getMaterial());
        String coinTitle = model_coin.getValue() + ", " + model_coin.getYear();
        pageTitle.setText(coinTitle);
        points.setText(String.valueOf(1000));

        String name = model_coin.getCoinID() + ".jpg";
        try{
            FileInputStream fis = requireContext().openFileInput(name);
            Bitmap b = BitmapFactory.decodeStream(fis);
            coinImage.setImageBitmap(b);
            //holder.coinImage.setImageDrawable(Drawable.createFromPath(file.toString()));
            fis.close();
        }
        catch(Exception ignored){
        }
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                backActivity();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(),callback);



        return details;
    }
    private void ReturnToMainDetailsPage(){

        back.setOnClickListener(v -> backActivity());


    }

    private void backActivity() {
        if(task==0)// Fragment was accessed home screen
        {
            Bundle bundle = new Bundle();
            bundle.putInt("StartPage",0);
            findNavController(Objects.requireNonNull(getParentFragmentManager().findFragmentById(R.id.fragmentContainerView2))).
                    setGraph(R.navigation.collection_navigation,bundle);

        }else
        {
            Navigation.findNavController(details).navigateUp();
        }
    }

}