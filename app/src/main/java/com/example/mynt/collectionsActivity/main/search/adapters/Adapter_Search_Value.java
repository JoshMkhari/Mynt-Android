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

import java.util.ArrayList;
import java.util.List;


public class Adapter_Search_Value extends RecyclerView.Adapter<Adapter_Search_Value.Card_View_Holder>{

    private final List<String> arrayList_Values;
    private final Interface_RecyclerView_One interfaceRecyclerView;
    public Adapter_Search_Value(List<String> arrayList_values, Interface_RecyclerView_One interfaceRecyclerView) {
        arrayList_Values = arrayList_values;
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
        holder.search_value_item_name.setText(arrayList_Values.get(position));
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
