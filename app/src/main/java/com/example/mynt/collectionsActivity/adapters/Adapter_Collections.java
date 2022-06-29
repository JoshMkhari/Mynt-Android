package com.example.mynt.collectionsActivity.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mynt.R;
import com.example.mynt.collectionsActivity.interfaces.Interface_RecyclerView;
import com.example.mynt.collectionsActivity.models.Model_Coin;
import com.example.mynt.collectionsActivity.models.Model_Coin_Comparator_Date;
import com.example.mynt.collectionsActivity.models.Model_Collections;
import com.example.mynt.collectionsActivity.models.Model_User;
import com.example.mynt.dataAccessLayer.Database_Lite;
import com.example.mynt.dataAccessLayer.Model_Database_Lite;

import java.util.ArrayList;
import java.util.Collections;

public class Adapter_Collections extends RecyclerView.Adapter<Adapter_Collections.CollectionsViewHolder>{//(Professor Sluiter, 2020).
    private final Interface_RecyclerView interfaceRecyclerView; //(Practical Coding, 2021)
    private final ArrayList<Model_Collections> collectionsList;
    private final Context context;
    private  final Model_User model_user;

    public Adapter_Collections(ArrayList<Model_Collections> collectionsList, Context context, Interface_RecyclerView interfaceRecyclerView,Model_User model_user) {
        this.collectionsList = collectionsList;
        this.context = context;
        this.interfaceRecyclerView = interfaceRecyclerView;
        this.model_user = model_user;
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

        //Open each collection and select the first image
        Model_Database_Lite mdl = new Model_Database_Lite();

        ArrayList<Model_Coin>coinsList = mdl.allCoinsAndCollections(context,1,collectionID);

        Collections.sort(coinsList, new Model_Coin_Comparator_Date());//(GeeksForGeeks,2020)

        try{
            Bitmap bmp = BitmapFactory.decodeByteArray(coinsList.get(0).getImageId(), 0, coinsList.get(0).getImageId().length);
            holder.coinImage.setImageBitmap(bmp);
        }
        catch(Exception ignored){
            holder.coinImage.setBackgroundResource(R.drawable.img_two_rand);
        }


        float collectionSize = (float)collectionCoins.size();
        float target = (float)collectionsList.get(position).getGoal();
        float goal = collectionSize/target*100;
        holder.progressBar.setProgress(Math.round(goal));

        if(position==0)
        {
            holder.collectionSeparator.setVisibility(View.GONE);
        }

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
        final ImageView collectionSeparator;



        public CollectionsViewHolder(@NonNull View itemView) {
            super(itemView);
            coinImage = itemView.findViewById(R.id.current_coin_imageview_collections);
            collectionCoinAmount = itemView.findViewById(R.id.collections_amount_TextView);
            collectionName = itemView.findViewById(R.id.collectionNameTextView);
            progressBar = itemView.findViewById(R.id.progressBar_collections);
            collectionSeparator = itemView.findViewById(R.id.collection_separator);

            itemView.setOnClickListener(v -> {

                if(interfaceRecyclerView != null)
                {
                    int pos = getAbsoluteAdapterPosition();

                    if(pos != RecyclerView.NO_POSITION){
                        interfaceRecyclerView.onItemClick(pos,coinImage);//(Practical Coding, 2021)
                    }
                }
            });
        }
    }


}