package com.example.mynt.coinsAct;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mynt.Coin_Model;
import com.example.mynt.R;
import com.example.mynt.RecyclerViewInterface;

import java.util.ArrayList;

public class CoinAdapter  extends RecyclerView.Adapter<CoinAdapter.CoinViewHolder>{
    private final RecyclerViewInterface recyclerViewInterface;
    ArrayList<Coin_Model> coinsList;
    Context context;
    int selected_position = 0;

    public CoinAdapter(ArrayList<Coin_Model> coinsList, Context context,RecyclerViewInterface recyclerViewInterface) {
        this.coinsList = coinsList;
        this.context = context;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public CoinViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_library_recent_coins,parent,false);
        CoinViewHolder holder = new CoinViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CoinViewHolder holder, int position) {
        holder.name.setText(coinsList.get(position).getCoinName());
        holder.year.setText(String.valueOf(coinsList.get(position).getYear()));
        //glide for internet images???
        holder.coinImage.setBackgroundResource(coinsList.get(position).getImageId());

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


        public CoinViewHolder(@NonNull View itemView) {
            super(itemView);
            coinImage = itemView.findViewById(R.id.current_coin_imageview);
            year = itemView.findViewById(R.id.coin_year_textview);
            name = itemView.findViewById(R.id.coin_name_textview);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(recyclerViewInterface != null)
                    {
                        int pos = getAbsoluteAdapterPosition();

                        if(pos != RecyclerView.NO_POSITION){
                            recyclerViewInterface.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }


}
