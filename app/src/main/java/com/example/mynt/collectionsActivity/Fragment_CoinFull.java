package com.example.mynt.collectionsActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mynt.R;
import com.example.mynt.collectionsActivity.models.User_Data;

import java.io.FileInputStream;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class Fragment_CoinFull extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View coinFull = inflater.inflate(R.layout.fragment_coin_full, container, false);

        ImageButton back;
        ImageView userImage;
        TextView titleCoin;
        userImage = coinFull.findViewById(R.id.coin_full_imageView);
        back = coinFull.findViewById(R.id.coin_full_back);
        titleCoin = coinFull.findViewById(R.id.coin_full_textTitle);
        assert getArguments() != null;
        String title = getArguments().getString("Title");
        try{
            userImage.setImageBitmap(User_Data.coinBitmap);
        }
        catch(Exception ignored){
        }


        titleCoin.setText(title);
        setSharedElementEnterTransition(TransitionInflater.from(getContext()).inflateTransition(android.R.transition.move));//(android developer blog, 2018)
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(coinFull).navigateUp();//(JHowzer,2018)
            }
        });
        return coinFull;
    }
}