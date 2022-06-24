package com.example.mynt.collectionsActivity;

import static androidx.navigation.fragment.NavHostFragment.findNavController;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.FragmentNavigator;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mynt.R;
import com.example.mynt.collectionsActivity.models.Model_Coin;
import com.example.mynt.collectionsActivity.models.Model_User_Data;
import com.example.mynt.dataAccessLayer.Database_Lite;

import java.util.ArrayList;
import java.util.Objects;

public class Fragment_Coin_Details extends Fragment{

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
        TextView acquiredDate = details.findViewById(R.id.textViewAcquiredDate);

        back = details.findViewById(R.id.CoinDetails_back);

        task = 0;
        assert getArguments() != null;
        task = getArguments().getInt("Task");
        int coinID = getArguments().getInt("CoinID");

        Database_Lite db = new Database_Lite(getContext());//(freecodecamp,2020)
        ArrayList<Model_Coin> dbCoins = db.getAllCoins();
        for (int i = 0; i< dbCoins.size(); i++)
        {
            if(dbCoins.get(i).getCoinID()==coinID)
            {
                Model_User_Data.model_coin = dbCoins.get(i);
                break;
            }
        }
        ReturnToMainDetailsPage();

        mintage.setText(String.valueOf(Model_User_Data.model_coin.getMintage()));
        observe.setText(Model_User_Data.model_coin.getObserve());
        reverse.setText(Model_User_Data.model_coin.getReverse());
        circulation.setText(Model_User_Data.model_coin.getReverse());
        type.setText(Model_User_Data.model_coin.getMaterial());
        String coinTitle = Model_User_Data.model_coin.getValue() + ", " + Model_User_Data.model_coin.getYear();
        pageTitle.setText(coinTitle);
        points.setText(String.valueOf(1000));
        acquiredDate.setText(Model_User_Data.model_coin.getDateAcquired());
        try{
            Bitmap bmp = BitmapFactory.decodeByteArray(Model_User_Data.model_coin.getImageId(), 0, Model_User_Data.model_coin.getImageId().length);
            Model_User_Data.coinBitmap = bmp;
            coinImage.setImageBitmap(bmp);
            //holder.coinImage.setImageDrawable(Drawable.createFromPath(file.toString()));
        }
        catch(Exception ignored){
        }
        OnBackPressedCallback callback = new OnBackPressedCallback(true) { //(Анатолий К.,2020)
            @Override
            public void handleOnBackPressed() {
                backActivity();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(),callback);//(Анатолий К.,2020)

        coinImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("CoinID", coinID);
                bundle.putString("Title", coinTitle);

                FragmentNavigator.Extras extras = new FragmentNavigator.Extras.Builder()
                        .addSharedElement(coinImage, "secondTransitionName")
                        .build();//(android developer blog, 2018)
                Navigation.findNavController(details).navigate(R.id.action_fragment_Coin_Details_to_fragment_CoinFull, bundle,null,extras);
            }
        });
        return details;
    }

    private void ReturnToMainDetailsPage(){
        back.setOnClickListener(v -> backActivity());
    }

    private void backActivity() {
        if(task==0)// Fragment was accessed home screen
        {
            Bundle bundle = new Bundle();//(valerybodak,2020)
            bundle.putInt("StartPage",0);
            findNavController(Objects.requireNonNull(getParentFragmentManager().findFragmentById(R.id.fragmentContainerView2))).
                    setGraph(R.navigation.collection_navigation,bundle);//(developer Android NavController, n.d)

        }else
        {
            Navigation.findNavController(details).navigateUp();//(JHowzer,2018)
        }
    }

}