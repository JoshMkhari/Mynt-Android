package com.example.mynt.collectionsActivity.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mynt.R;
import com.example.mynt.collectionsActivity.models.Model_Leaderboard;

import java.util.ArrayList;

public class Adapter_Leaderboard extends RecyclerView.Adapter<Adapter_Leaderboard.Card_View_Holder> {//(Professor Sluiter, 2020).

    private final ArrayList<Model_Leaderboard> arrayList_Leaderboard;

    public Adapter_Leaderboard(ArrayList<Model_Leaderboard> arrayList_Leaderboard) {
        this.arrayList_Leaderboard = arrayList_Leaderboard;
        //Variable Declarations
    }

    @NonNull
    @Override
    public Adapter_Leaderboard.Card_View_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_leaderboard,parent,false);
        return new Card_View_Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_Leaderboard.Card_View_Holder holder, int position) {
        holder.userName.setText(arrayList_Leaderboard.get(position).getUserName());
        holder.score.setText("Score: "+ arrayList_Leaderboard.get(position).getUserScore());
        holder.profileImage.setBackgroundResource(arrayList_Leaderboard.get(position).getImageID());
        //glide for internet images???
        switch (position)
        {
            case 0:
                holder.rankImage.setBackgroundResource(R.drawable.img_leaderboard_icon_rank_1);
                break;
            case 1:
                holder.rankImage.setBackgroundResource(R.drawable.img_leaderboard_icon_rank_2);
                break;
            case 2:
                holder.rankImage.setBackgroundResource(R.drawable.img_leaderboard_icon_rank_3);
                break;
            default:
                holder.rankImage.setVisibility(View.INVISIBLE);
        }
        // Here I am just highlighting the background
        //holder.itemView.setBackgroundColor(selected_position == position ? Color.GREEN : Color.TRANSPARENT);
    }

    @Override
    public int getItemCount() {
        return arrayList_Leaderboard.size();
    }

    public static class Card_View_Holder extends RecyclerView.ViewHolder{
        final ImageView profileImage;
        final TextView userName;
        final TextView score;
        final ImageView rankImage;

        public Card_View_Holder(@NonNull View itemView) {
            super(itemView);
            profileImage = itemView.findViewById(R.id.imageview_leaderboard_userProfile_icon);
            userName = itemView.findViewById(R.id.textview_leaderboard_user_name);
            score = itemView.findViewById(R.id.textview_leaderboard_user_points);
            rankImage = itemView.findViewById(R.id.imageview_leaderboard_user_rank_icon);

            /* In event of need for item click on leaderboard
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(recyclerView_Interface != null)
                    {
                        int pos = getAbsoluteAdapterPosition();

                        if(pos != RecyclerView.NO_POSITION){
                            recyclerView_Interface.onItemClick(pos);
                        }
                    }
                }
            });

             */
        }
    }
}
