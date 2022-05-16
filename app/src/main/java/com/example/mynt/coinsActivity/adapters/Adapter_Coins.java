package com.example.mynt.coinsActivity.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mynt.R;
import com.example.mynt.coinsActivity.models.Model_Coins_List;

import java.util.ArrayList;

public class Adapter_Coins extends RecyclerView.Adapter<Adapter_Coins.CoinViewHolder>{
    ArrayList<Model_Coins_List> coinsList;
    Context context;

    public Adapter_Coins(ArrayList<Model_Coins_List> coinsList, Context context) {
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
            coinImage = itemView.findViewById(R.id.imageview_current_coin);
            year = itemView.findViewById(R.id.textview_coin_year);
            name = itemView.findViewById(R.id.textview_coin_name);
            country = itemView.findViewById(R.id.textview_coin_country);
            date = itemView.findViewById(R.id.textview_coin_acquired_date);
        }
    }


}
