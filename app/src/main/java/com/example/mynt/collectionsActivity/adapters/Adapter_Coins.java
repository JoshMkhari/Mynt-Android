package com.example.mynt.collectionsActivity.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
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
import com.example.mynt.collectionsActivity.models.Model_Date;

import org.w3c.dom.Text;

import java.io.FileInputStream;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Objects;

public class Adapter_Coins extends RecyclerView.Adapter<Adapter_Coins.CoinViewHolder>{

    private final Interface_RecyclerView interfaceRecyclerView;
    final ArrayList<Model_Coin> coinsList;
    final Context context;
    static String dateAcquired, dateValue;
    static String current = "No";

    public Adapter_Coins(ArrayList<Model_Coin> coinsList, Context context, Interface_RecyclerView interfaceRecyclerView) {
        this.coinsList = coinsList;
        this.context = context;
        current = "No";
        this.interfaceRecyclerView = interfaceRecyclerView;
    }

    @NonNull
    @Override
    public CoinViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_coins,parent,false);
        return new CoinViewHolder(view);
    }

    public String returnDay(String date)
    {
        Calendar calToday = Calendar.getInstance();
        Calendar calDay = Calendar.getInstance();


        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        try {
            calToday.set(Calendar.HOUR_OF_DAY,0);
            calDay.setTime(Objects.requireNonNull(sdf.parse(date)));

            //Log.d("callThing", "onBindViewHolder: " + calDay.get(Calendar.YEAR));
            //Log.d("callThing", "onBindViewHolder: " + calToday);
            //Log.d("callThing", "onBindViewHolder: " + calDay);

            Log.d("order","this " + date);
            if(calDay.get(Calendar.YEAR) == calToday.get(Calendar.YEAR))
            {
                if(calDay.get(Calendar.DAY_OF_YEAR) == calToday.get(Calendar.DAY_OF_YEAR))
                {
                    return "TODAY";
                }
                int yesterday = calToday.get(Calendar.DAY_OF_YEAR)-1;
                if(calDay.get(Calendar.DAY_OF_YEAR) == yesterday )
                {
                    return "YESTERDAY";
                }
                int thisWeek = calToday.get(Calendar.WEEK_OF_YEAR);
                if(calDay.get(Calendar.WEEK_OF_YEAR) == thisWeek)
                {
                    return "THIS WEEK";
                }
                Log.d("callThing", "onBindViewHolder: comparing " + calDay.get(Calendar.YEAR) + " and " + calToday.get(Calendar.YEAR) );
                if(calDay.get(Calendar.WEEK_OF_YEAR) == (thisWeek-1))
                {
                    return "LAST WEEK";
                }
                int thisMonth = calToday.get(Calendar.MONTH);
                if(calDay.get(Calendar.MONTH) == thisMonth)
                {
                    return "THIS MONTH";
                }else
                {
                    Model_Date model_date = new Model_Date();
                    return model_date.getMonthFormat(calDay.get(Calendar.MONTH),true);
                }
            }
            return String.valueOf(calDay.get(Calendar.YEAR));
            //Log.d("callThing", "onBindViewHolder: " + dayOfWeek);
        } catch (ParseException e) {
            e.printStackTrace();
            return "Error";
        }
    }
    @Override
    public void onBindViewHolder(@NonNull CoinViewHolder holder, int position) {
        holder.name.setText(coinsList.get(position).getValue());

        //Need to get actual year here

        holder.year.setText(String.valueOf(coinsList.get(position).getYear()));
        //holder.coinImage.setBackgroundResource(coinsList.get(position).getImageId());
        String name = coinsList.get(position).getCoinID() +".jpg";
        try{
            FileInputStream fis = context.openFileInput(name);
            Bitmap b = BitmapFactory.decodeStream(fis);
            holder.coinImage.setImageBitmap(b);
            //holder.coinImage.setImageDrawable(Drawable.createFromPath(file.toString()));
            fis.close();
        }
        catch(Exception ignored){
        }
        Model_Date model_date = new Model_Date();
        String convertedDate = model_date.convertDateString(coinsList.get(position).getDateAcquired(),true);
        holder.date.setText(convertedDate);
        holder.country.setText("South Africa");

        dateValue = coinsList.get(position).getDateAcquired();

        Log.d("thisisCurr", "onBindViewHolder: " + current);
        if(current.equals("No"))
        {
            dateAcquired = returnDay(dateValue);
            current = dateAcquired;
            holder.acquired.setText(dateAcquired);
        }else
        {
            dateAcquired = returnDay(coinsList.get(position).getDateAcquired());
            if(current.equals(dateAcquired))//Same returnDay as previous
            {
                holder.acquired.setVisibility(View.GONE);

                holder.daySeparator.setVisibility(View.GONE);
            }else
            {
                current = dateAcquired;
                holder.acquired.setText(dateAcquired);
            }
        }

    }

    @Override
    public int getItemCount() {
        return coinsList.size();
    }

    public class CoinViewHolder extends RecyclerView.ViewHolder{
        final ImageView coinImage;
        final TextView year;
        final TextView name;
        final TextView date;
        final TextView country;
        final TextView acquired;
        final ImageView daySeparator;


        public CoinViewHolder(@NonNull View itemView) {
            super(itemView);
            coinImage = itemView.findViewById(R.id.imageview_current_coin);
            year = itemView.findViewById(R.id.textview_coin_year);
            name = itemView.findViewById(R.id.textview_coin_name);
            country = itemView.findViewById(R.id.textview_coin_country);
            date = itemView.findViewById(R.id.textview_coin_acquired_date);
            acquired = itemView.findViewById(R.id.textview_coinDate);
            daySeparator = itemView.findViewById(R.id.coin_date_separator);

            itemView.setOnClickListener(v -> {
                if(interfaceRecyclerView != null)
                {
                    int pos = getAbsoluteAdapterPosition();

                    if(pos != RecyclerView.NO_POSITION){
                        interfaceRecyclerView.onItemClick(pos,coinImage);
                    }
                }
            });
        }
    }


}
