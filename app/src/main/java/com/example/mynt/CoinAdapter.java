package com.example.mynt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CoinAdapter  extends RecyclerView.Adapter<CoinAdapter.CoinViewHolder>{
    ArrayList<Coin_Model> coinsList;
    Context context;

    public CoinAdapter(ArrayList<Coin_Model> coinsList, Context context) {
        this.coinsList = coinsList;
        this.context = context;
    }

    @NonNull
    @Override
    public CoinViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.library_recent_coins_listview,parent,false);
        CoinViewHolder holder = new CoinViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CoinViewHolder holder, int position) {
        holder.name.setText(coinsList.get(position).coinName);
        holder.year.setText(String.valueOf(coinsList.get(position).year));
        //glide for internet images???
        holder.coinImage.setBackgroundResource(coinsList.get(position).imageId);
    }

    @Override
    public int getItemCount() {
        return coinsList.size();
    }

    public class CoinViewHolder extends RecyclerView.ViewHolder{
        ImageView coinImage;
        TextView year;
        TextView name;


        public CoinViewHolder(@NonNull View itemView) {
            super(itemView);
            coinImage = itemView.findViewById(R.id.current_coin_imageview);
            year = itemView.findViewById(R.id.coin_year_textview);
            name = itemView.findViewById(R.id.coin_name_textview);
        }
    }
}
