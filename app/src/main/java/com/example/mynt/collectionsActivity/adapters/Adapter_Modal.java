package com.example.mynt.collectionsActivity.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mynt.R;
import com.example.mynt.collectionsActivity.models.Model_Leaderboard;

import java.util.ArrayList;

public class Adapter_Modal extends RecyclerView.Adapter<Adapter_Modal.Card_View_Holder> {
    private final ArrayList<String> arrayListOptions;
    private final int mode;

    public Adapter_Modal(ArrayList<String> arrayListOptions, int mode) {
        this.arrayListOptions = arrayListOptions;
        this.mode = mode;
    }

    @NonNull
    @Override
    public Adapter_Modal.Card_View_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_bottom_sheet,parent,false);
        return new Adapter_Modal.Card_View_Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_Modal.Card_View_Holder holder, int position) {
        String modeText;

        if(mode==1)
        {
            modeText = "coin";
        }else
        {
            modeText="collection";
        }
        String Title = "Remove " + modeText + " from Library";
        String View = "View " + modeText;

        switch (position)
        {

            case 0:
                holder.optionImage.setBackgroundResource(R.drawable.img_delete_button);
                holder.optionName.setText(Title);
                break;
            case 1:
                holder.optionImage.setBackgroundResource(R.drawable.img_share_button);
                String share = "Share";
                holder.optionName.setText(share);
                break;
            case 2:
                holder.optionImage.setBackgroundResource(R.drawable.img_edit_username_button);
                holder.optionName.setText(View);
                break;
            case 3:
                if(mode==2)
                {
                    holder.optionImage.setBackgroundResource(R.drawable.ic_collection_icon);
                    String collectionText = "Collection: " + arrayListOptions.get(position);
                    holder.optionName.setText(collectionText);
                }else
                {
                    holder.optionImage.setVisibility(android.view.View.GONE);
                    holder.optionName.setVisibility(android.view.View.GONE);
                }
                break;
        }

    }

    @Override
    public int getItemCount() {
        return arrayListOptions.size();
    }

    public static class Card_View_Holder extends RecyclerView.ViewHolder
    {
        final ImageButton optionImage;
        final TextView optionName;

        public Card_View_Holder(@NonNull View itemView)
        {
            super((itemView));

            optionImage = itemView.findViewById(R.id.bottom_sheet_remove);
            optionName = itemView.findViewById(R.id.bottom_sheet_remove_text);
        }
    }
}
