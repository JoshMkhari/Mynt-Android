package com.example.mynt.collectionsActivity;

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

import java.util.ArrayList;

public class Adapter_Collections extends RecyclerView.Adapter<Adapter_Collections.CollectionsViewHolder>{
    ArrayList<Model_Collections> collectionsList;
    Context context;
    int selected_position = 0;

    public Adapter_Collections(ArrayList<Model_Collections> collectionsList, Context context) {
        this.collectionsList = collectionsList;
        this.context = context;
    }

    @NonNull
    @Override
    public CollectionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_all_collections,parent,false);
        CollectionsViewHolder holder = new CollectionsViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CollectionsViewHolder holder, int position) {
        holder.collectionName.setText(collectionsList.get(position).getCollectionName());

        holder.collectionCoinAmount.setText(collectionsList.get(position).getCollectionCoinAmount()+" Coins");
        //glide for internet images???
        holder.coinImage.setBackgroundResource(R.drawable.img_two_rand);

        holder.progressBar.setProgress(collectionsList.get(position).getCollectionProgressBar());

        // Here I am just highlighting the background
        //holder.itemView.setBackgroundColor(selected_position == position ? Color.GREEN : Color.TRANSPARENT);
    }

    @Override
    public int getItemCount() {
        return collectionsList.size();
    }

    public class CollectionsViewHolder extends RecyclerView.ViewHolder{
        ImageView coinImage;
        TextView collectionCoinAmount;
        TextView collectionName;
        ProgressBar progressBar;


        public CollectionsViewHolder(@NonNull View itemView) {
            super(itemView);
            coinImage = itemView.findViewById(R.id.current_coin_imageview_collections);
            collectionCoinAmount = itemView.findViewById(R.id.collections_amount_TextView);
            collectionName = itemView.findViewById(R.id.collectionNameTextView);
            progressBar = itemView.findViewById(R.id.progressBar_collections);
        }
    }


}