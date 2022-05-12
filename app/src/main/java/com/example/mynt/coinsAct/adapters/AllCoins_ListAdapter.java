package com.example.mynt.coinsAct.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mynt.R;
import com.example.mynt.coinsAct.models.coins_list_model;

import java.util.ArrayList;

public class AllCoins_ListAdapter extends RecyclerView.Adapter<AllCoins_ListAdapter.CoinViewHolder>{
    ArrayList<coins_list_model> coinsList;
    Context context;
    int selected_position = 0;

    public AllCoins_ListAdapter(ArrayList<coins_list_model> coinsList, Context context) {
        this.coinsList = coinsList;
        this.context = context;
    }

    @NonNull
    @Override
    public CoinViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_coins,parent,false);
        CoinViewHolder holder = new CoinViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CoinViewHolder holder, int position) {
        holder.name.setText(coinsList.get(position).getCoinName());
        holder.year.setText(String.valueOf(coinsList.get(position).getYear()));
        //glide for internet images???
        holder.coinImage.setBackgroundResource(coinsList.get(position).getImageId());
        holder.date.setText(String.valueOf(coinsList.get(position).getCoinDate()));
        holder.country.setText(String.valueOf(coinsList.get(position).getCountry()));

        // Here I am just highlighting the background
        //holder.itemView.setBackgroundColor(selected_position == position ? Color.GREEN : Color.TRANSPARENT);
    }

    @Override
    public int getItemCount() {
        return coinsList.size();
    }

    public class CoinViewHolder extends RecyclerView.ViewHolder{
        ImageView coinImage;
        TextView year;
        TextView name;
        TextView date;
        TextView country;


        public CoinViewHolder(@NonNull View itemView) {
            super(itemView);
            coinImage = itemView.findViewById(R.id.current_coin_imageview);
            year = itemView.findViewById(R.id.coin_year_textview);
            name = itemView.findViewById(R.id.coin_name_textview);
            country = itemView.findViewById(R.id.coin_country_textview);
            date = itemView.findViewById(R.id.coin_acquired_date_textview);
        }
    }


}
