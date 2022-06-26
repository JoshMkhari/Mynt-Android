package com.example.mynt.collectionsActivity.main.search.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mynt.Interface_RecyclerView;
import com.example.mynt.Interface_RecyclerView_One;
import com.example.mynt.R;
import com.example.mynt.collectionsActivity.models.Model_Coin;

import java.util.ArrayList;


public class Adapter_Search_Value extends RecyclerView.Adapter<Adapter_Search_Value.Card_View_Holder>{

    private final ArrayList<Model_Coin> arrayList_Values;
    private final Interface_RecyclerView_One interfaceRecyclerView;
    public Adapter_Search_Value(ArrayList<Model_Coin> arrayList_values, Interface_RecyclerView_One interfaceRecyclerView) {
        ArrayList<Model_Coin> coinWithoutDuplicates = new ArrayList<>();
        ArrayList<String> values = new ArrayList<>();
        for (Model_Coin coins: arrayList_values
             ) {
            if(!values.contains(coins.getValue()))
            {
                coinWithoutDuplicates.add(coins);
                values.add(coins.getValue());
            }
        }

        arrayList_Values = coinWithoutDuplicates;
        this.interfaceRecyclerView = interfaceRecyclerView;
    }

    @NonNull
    @Override
    public Adapter_Search_Value.Card_View_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_search_values,parent,false);
        return new Adapter_Search_Value.Card_View_Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_Search_Value.Card_View_Holder holder, int position) {
        holder.search_value_item_name.setText(arrayList_Values.get(position).getValue());
    }

    @Override
    public int getItemCount() {
        return arrayList_Values.size();
    }

    public class Card_View_Holder extends RecyclerView.ViewHolder{
        final TextView search_value_item_name;

        public Card_View_Holder(@NonNull View itemView) {
            super(itemView);
            search_value_item_name = itemView.findViewById(R.id.search_value_item_name);
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
