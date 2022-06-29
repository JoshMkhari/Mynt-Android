package com.example.mynt.collectionsActivity.adapters;

import android.content.Context;
import android.service.autofill.UserData;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mynt.R;
import com.example.mynt.collectionsActivity.interfaces.Interface_RecyclerView;
import com.example.mynt.collectionsActivity.models.Model_Collection_Coin;
import com.example.mynt.collectionsActivity.models.Model_Collections;
import com.example.mynt.collectionsActivity.models.Model_Leaderboard;
import com.example.mynt.collectionsActivity.models.Model_User_Data;
import com.example.mynt.dataAccessLayer.Database_Lite;

import java.util.ArrayList;

public class Adapter_Modal extends RecyclerView.Adapter<Adapter_Modal.Card_View_Holder> {
    private final ArrayList<String> arrayListOptions;
    private final Interface_RecyclerView interfaceRecyclerView;//(Practical Coding, 2021)
    private final int mode;
    private final Context context;

    public Adapter_Modal(ArrayList<String> arrayListOptions, int mode, Context context,Interface_RecyclerView interfaceRecyclerView ) {
        this.arrayListOptions = arrayListOptions;
        this.mode = mode;
        this.context = context;
        this.interfaceRecyclerView = interfaceRecyclerView;
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
            modeText = "collection";
            Model_User_Data.task = 3;
        }else
        {
            modeText="coin";
            Model_User_Data.task = 0;
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
                    Database_Lite db = new Database_Lite(context);
                    ArrayList<Model_Collections> allUserCollections = db.getAllCollections();
                    int collectionID = 0;
                    ArrayList<Model_Collection_Coin> allUserCollectionCoin = db.getAllCollectionCoin();
                    for (Model_Collection_Coin collectionCoin:allUserCollectionCoin
                         ) {
                        if(collectionCoin.getCoinID()== Model_User_Data.model_coin.getCoinID())
                        {
                            collectionID = collectionCoin.getCollectionID();
                            break;
                        }
                    }
                    for (Model_Collections collections:allUserCollections
                         ) {
                        if(collections.getCollectionID()==collectionID)
                        {
                            Model_User_Data.model_collections = collections;
                        }
                    }
                    String collectionText = "Collection: " + Model_User_Data.model_collections.getCollectionName();
                    holder.optionName.setText(collectionText);

                    //Search Collection Coin ID for coin id to find collection it belongs to
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

    public class Card_View_Holder extends RecyclerView.ViewHolder
    {
        final ImageButton optionImage;
        final TextView optionName;

        public Card_View_Holder(@NonNull View itemView)
        {
            super((itemView));

            optionImage = itemView.findViewById(R.id.bottom_sheet_remove);
            optionName = itemView.findViewById(R.id.bottom_sheet_remove_text);

            itemView.setOnClickListener(v -> {
                if(interfaceRecyclerView != null)
                {
                    int pos = getAbsoluteAdapterPosition();

                    if(pos != RecyclerView.NO_POSITION){
                        interfaceRecyclerView.onItemClick(pos,optionImage);//(Practical Coding, 2021)
                    }
                }
            });
        }
    }
}
