package com.example.mynt.collectionsActivity.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mynt.R;
import com.example.mynt.Interface_RecyclerView;
import com.example.mynt.collectionsActivity.models.Model_Coin;

import java.io.FileInputStream;
import java.util.ArrayList;

public class Adapter_Coins extends RecyclerView.Adapter<Adapter_Coins.CoinViewHolder>{

    private final Interface_RecyclerView interfaceRecyclerView;
    ArrayList<Model_Coin> coinsList;
    Context context;

    public Adapter_Coins(ArrayList<Model_Coin> coinsList, Context context, Interface_RecyclerView interfaceRecyclerView) {
        this.coinsList = coinsList;
        this.context = context;
        this.interfaceRecyclerView = interfaceRecyclerView;
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
        holder.name.setText(coinsList.get(position).getValue());

        //Need to get actual year here
        holder.year.setText(String.valueOf(coinsList.get(position).getYear()));
        //glide for internet images???
        //holder.coinImage.setBackgroundResource(coinsList.get(position).getImageId());
        String name = coinsList.get(position).getCoinID() +".jpg";
        try{
            FileInputStream fis = context.openFileInput(name);
            Bitmap b = BitmapFactory.decodeStream(fis);
            holder.coinImage.setImageBitmap(b);
            //holder.coinImage.setImageDrawable(Drawable.createFromPath(file.toString()));
            fis.close();
        }
        catch(Exception e){
            ;
        }
        holder.date.setText(String.valueOf(coinsList.get(position).getDateTaken()));
        holder.country.setText("South Africa");
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

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(interfaceRecyclerView != null)
                    {
                        int pos = getAbsoluteAdapterPosition();

                        if(pos != RecyclerView.NO_POSITION){
                            interfaceRecyclerView.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }


}
