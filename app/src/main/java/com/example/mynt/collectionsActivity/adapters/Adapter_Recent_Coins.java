package com.example.mynt.collectionsActivity.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mynt.collectionsActivity.Dialog_Bottom_Sheet;
import com.example.mynt.collectionsActivity.models.Model_Coin;
import com.example.mynt.R;
import com.example.mynt.collectionsActivity.interfaces.Interface_RecyclerView;
import com.example.mynt.collectionsActivity.models.Model_User_Data;

import java.util.ArrayList;

public class Adapter_Recent_Coins extends RecyclerView.Adapter<Adapter_Recent_Coins.CoinViewHolder>{//(Professor Sluiter, 2020).
    private final Interface_RecyclerView interfaceRecyclerView;//(Practical Coding, 2021)
    final ArrayList<Model_Coin> coinsList;
    final Context context;
    final FragmentManager manager;

    public Adapter_Recent_Coins(ArrayList<Model_Coin> coinsList, Context context, Interface_RecyclerView interfaceRecyclerView, FragmentManager manager) {
        this.manager = manager;
        this.coinsList = new ArrayList<>();
        if (coinsList.size()!=0)
        for (int i = 0; i < 6; i++) {
            try
            {
                this.coinsList.add(coinsList.get(i));
            }catch(Exception e)
            {
                //There aren't anymore items
            }

        }
        this.context = context;
        this.interfaceRecyclerView = interfaceRecyclerView;
    }

    @NonNull
    @Override
    public CoinViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_library_recent_coins,parent,false);
        return new CoinViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CoinViewHolder holder, int position) {
        holder.name.setText(coinsList.get(position).getValue());
        holder.year.setText(String.valueOf(coinsList.get(position).getYear()));
        //glide for internet images???

        // THIS IS THE PROBLEM
        try{
            Bitmap bmp = BitmapFactory.decodeByteArray(coinsList.get(position).getImageId(), 0, coinsList.get(position).getImageId().length);
            holder.coinImage.setImageBitmap(bmp);
            //holder.coinImage.setImageDrawable(Drawable.createFromPath(file.toString()));
        }
        catch(Exception ignored){
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
        final ImageView coinImage;
        final TextView year;
        final TextView name;
        final ImageButton moreOptions;


        public CoinViewHolder(@NonNull View itemView) {
            super(itemView);
            coinImage = itemView.findViewById(R.id.imageview_current_coin);
            year = itemView.findViewById(R.id.textview_coin_year);
            name = itemView.findViewById(R.id.textview_coin_name);
            moreOptions = itemView.findViewById(R.id.meatball_recentCoin);

            moreOptions.setOnClickListener(v -> {
                int pos = getAbsoluteAdapterPosition();
                //coinsList.get(pos).getValue();
                Log.d("meatClicked", "onClick: "+coinsList.get(pos).getValue());
                Log.d("meatClicked", "onClick: "+coinsList.get(pos).getYear());
                Model_User_Data.array_list_bottomSheet = new ArrayList<>();
                for (int i = 0; i<4;i++)
                    Model_User_Data.array_list_bottomSheet.add("1");

                Model_User_Data.mode = 2;
                Model_User_Data.model_coin = coinsList.get(pos);
                Dialog_Bottom_Sheet bottom_sheet = new Dialog_Bottom_Sheet();
                bottom_sheet.show(manager,"mySheet");
            });

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
