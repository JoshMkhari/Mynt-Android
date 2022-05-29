package com.example.mynt.collectionsActivity.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mynt.R;
import com.example.mynt.Interface_RecyclerView;
import com.example.mynt.collectionsActivity.models.Model_Collections;
import com.example.mynt.dataAccessLayer.Database_Lite;

import java.util.ArrayList;

public class Adapter_Collections extends RecyclerView.Adapter<Adapter_Collections.CollectionsViewHolder>{
    private final Interface_RecyclerView interfaceRecyclerView;
    private final ArrayList<Model_Collections> collectionsList;
    private final Context context;

    public Adapter_Collections(ArrayList<Model_Collections> collectionsList, Context context, Interface_RecyclerView interfaceRecyclerView) {
        this.collectionsList = collectionsList;
        this.context = context;
        this.interfaceRecyclerView = interfaceRecyclerView;
    }

    @NonNull
    @Override
    public CollectionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_all_collections,parent,false);
        return new CollectionsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CollectionsViewHolder holder, int position) {

        holder.collectionName.setText(collectionsList.get(position).getCollectionName());
        int collectionID = collectionsList.get(position).getCollectionID();

        Database_Lite db = new Database_Lite(context);

        ArrayList<Integer> collectionCoins;
        collectionCoins = db.getAllCoinsInCollection(collectionID);

        String coinAmount = collectionCoins.size()+" Coins";
        holder.collectionCoinAmount.setText(coinAmount);
        //glide for internet images???
        holder.coinImage.setBackgroundResource(R.drawable.img_two_rand);

        float collectionSize = (float)collectionCoins.size();
        float target = (float)collectionsList.get(position).getGoal();
        float goal = collectionSize/target*100;
        holder.progressBar.setProgress(Math.round(goal));

        // Here I am just highlighting the background
        //holder.itemView.setBackgroundColor(selected_position == position ? Color.GREEN : Color.TRANSPARENT);
    }


    @Override
    public int getItemCount() {
        return collectionsList.size();
    }

    public class CollectionsViewHolder extends RecyclerView.ViewHolder{
        final ImageView coinImage;
        final TextView collectionCoinAmount;
        final TextView collectionName;
        final ProgressBar progressBar;


        public CollectionsViewHolder(@NonNull View itemView) {
            super(itemView);
            coinImage = itemView.findViewById(R.id.current_coin_imageview_collections);
            collectionCoinAmount = itemView.findViewById(R.id.collections_amount_TextView);
            collectionName = itemView.findViewById(R.id.collectionNameTextView);
            progressBar = itemView.findViewById(R.id.progressBar_collections);

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