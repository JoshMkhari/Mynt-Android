package com.example.mynt.collectionAct.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mynt.R;
import com.example.mynt.collectionAct.models.coins_baselist_model;

import java.util.ArrayList;

public class Coins_Base_List_Adapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    ArrayList<coins_baselist_model> coinsBaseList;

    public Coins_Base_List_Adapter(Context context, ArrayList<coins_baselist_model> coinsBaseList) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.coinsBaseList = coinsBaseList;
    }
    @Override
    public int getCount() {
        return coinsBaseList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    ListView me;

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        convertView =View.inflate(context.getApplicationContext(), me, null);



        TextView coinName = convertView.findViewById(R.id.coin_name_textview);

        currentCoinPic.setBackgroundResource(R.drawable.two_rand);
        coinName.setText(this.coinsList.get(position).getCoinName());
        coinCountry.setText(this.coinsList.get(position).getCountry());
        coinAcquiredDate.setText(this.coinsList.get(position).getCoinDate());
        coinYear.setText(this.coinsList.get(position).getYear()+"");


        return convertView;
}
