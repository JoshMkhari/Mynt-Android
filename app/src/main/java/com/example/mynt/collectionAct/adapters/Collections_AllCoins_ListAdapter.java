package com.example.mynt.collectionAct.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mynt.R;
import com.example.mynt.collectionAct.models.coins_superlist_model;

import java.util.ArrayList;

public class Collections_AllCoins_ListAdapter extends BaseAdapter {

    Context context;
    LayoutInflater inflater;
    ArrayList<coins_superlist_model> coinsSuperList;

    public Collections_AllCoins_ListAdapter(Context context, ArrayList<coins_superlist_model> coinsSuperList) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.coinsSuperList = coinsSuperList;
    }
    @Override
    public int getCount() {
        return coinsSuperList.size();
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

        convertView = inflater.inflate(R.layout.coin_super_list, null);


        View coinsList = inflater.inflate(R.layout.coins_list, null);
        ImageView currentCoinPic = coinsList.findViewById(R.id.current_coin_imageview);
        TextView coinName = coinsList.findViewById(R.id.coin_name_textview);
        TextView coinCountry = coinsList.findViewById(R.id.coin_country_textview);
        TextView coinAcquiredDate = coinsList.findViewById(R.id.coin_acquired_date_textview);
        TextView coinYear = coinsList.findViewById(R.id.coin_year_textview);

        //View coinsBaseList = inflater.inflate(R.layout.coins_base_list, null);

        View coinsBaseList =  View.inflate(context.getApplicationContext(), coinsList, null);

        return convertView;
    }
}
