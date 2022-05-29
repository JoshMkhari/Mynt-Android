package com.example.mynt.collectionsActivity.adapters;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mynt.collectionsActivity.models.Model_Coin;
import com.example.mynt.R;
import com.example.mynt.Interface_RecyclerView;

import java.io.FileInputStream;
import java.util.ArrayList;

public class Adapter_Coin extends RecyclerView.Adapter<Adapter_Coin.CoinViewHolder>{
    private final Interface_RecyclerView interfaceRecyclerView;
    ArrayList<Model_Coin> coinsList;
    Context context;
    int selected_position = 0;

    public Adapter_Coin(ArrayList<Model_Coin> coinsList, Context context, Interface_RecyclerView interfaceRecyclerView) {
        this.coinsList = coinsList;
        this.context = context;
        this.interfaceRecyclerView = interfaceRecyclerView;
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
        holder.name.setText(coinsList.get(position).getValue());
        holder.year.setText(String.valueOf(coinsList.get(position).getYear()));
        //glide for internet images???

        //Image
        ContextWrapper cw = new ContextWrapper(context);

        // THIS IS THE PROBLEM
        String name = coinsList.get(position).getCoinID() +".jpg";
        try{
            FileInputStream fis = context.openFileInput(name);
            Bitmap b = BitmapFactory.decodeStream(fis);
            holder.coinImage.setImageBitmap(b);
            //holder.coinImage.setImageDrawable(Drawable.createFromPath(file.toString()));
            fis.close();
        }
        catch(Exception e){
        }

        //File directory = cw.getDir("files",Context.MODE_PRIVATE);

        //String imageID = coinsList.get(position).getImageId()+".jpg";
       // File file = new File(directory,imageID);

        //holder.coinImage.setImageDrawable(Drawable.createFromPath(file.toString()));

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
            coinImage = itemView.findViewById(R.id.imageview_current_coin);
            year = itemView.findViewById(R.id.textview_coin_year);
            name = itemView.findViewById(R.id.textview_coin_name);

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
