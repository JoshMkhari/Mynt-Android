package com.example.mynt.collectionAct.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mynt.R;
import com.example.mynt.collectionAct.models.coins_list;
import com.example.mynt.collectionAct.models.coins_superlist_model;

import java.util.ArrayList;

public class Coins_List_Adapters extends BaseAdapter {

    Context context;
    LayoutInflater inflater;
    ArrayList<coins_list> coinsList;

    public Coins_List_Adapters(Context context, ArrayList<coins_list> coinsList) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.coinsList = coinsList;
    }
    @Override
    public int getCount() {
        return coinsList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        convertView = inflater.inflate(R.layout.coins_list, null);

        ImageView currentCoinPic = convertView.findViewById(R.id.current_coin_imageview);
        TextView coinName = convertView.findViewById(R.id.coin_name_textview);
        TextView coinCountry = convertView.findViewById(R.id.coin_country_textview);
        TextView coinAcquiredDate = convertView.findViewById(R.id.coin_acquired_date_textview);
        TextView coinYear = convertView.findViewById(R.id.coin_year_textview);

        currentCoinPic.setBackgroundResource(R.drawable.two_rand);
        coinName.setText(this.coinsList.get(position).getCoinName());
        coinCountry.setText(this.coinsList.get(position).getCountry());
        coinAcquiredDate.setText(this.coinsList.get(position).getCoinDate());
        coinYear.setText(this.coinsList.get(position).getYear()+"");


        return convertView;
    }

}
