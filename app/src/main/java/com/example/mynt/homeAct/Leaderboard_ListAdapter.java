package com.example.mynt.homeAct;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mynt.R;

import java.util.ArrayList;

public class Leaderboard_ListAdapter extends BaseAdapter {
    public Leaderboard_ListAdapter(Context context, ArrayList<Leaderboard_Model> leaderboardArrayList) {
        this.context = context;
        this.leaderboardArrayList = leaderboardArrayList;
        inflater = LayoutInflater.from(context);
    }

    Context context;
    ArrayList<Leaderboard_Model> leaderboardArrayList;
    LayoutInflater inflater;

    @Override
    public int getCount() {
        return leaderboardArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        convertView = inflater.inflate(R.layout.leaderboard_listview, null);

        ImageView userProfileIcon = convertView.findViewById(R.id.leaderboard_user_profile_icon);
        TextView userName = convertView.findViewById(R.id.leaderboard_user_name);
        TextView userPoints = convertView.findViewById(R.id.leaderboard_user_points);
        TextView userRank = convertView.findViewById(R.id.leaderboard_user_rank);
        ImageView userRankIcon = convertView.findViewById(R.id.leaderboard_user_rank_icon);


        //userProfileIcon.setBackgroundResource(leaderboardArrayList.get(position).imageID);
        //userName.setText(leaderboardArrayList.get(position).userName);
        //userPoints.setText("Score: "+leaderboardArrayList.get(position).userScore);
        //userRank.setText("Rank" +leaderboardArrayList.get(position).userRank);

        userProfileIcon.setBackgroundResource(R.drawable.default_user_profile_icon);
        userRankIcon.setBackgroundResource(R.drawable.leaderboard_icon_rank_1);
        userName.setText("iHasShoulders");
        userPoints.setText("Score: 4396");
        userRank.setText("Rank 1");

        return convertView;
    }

}
