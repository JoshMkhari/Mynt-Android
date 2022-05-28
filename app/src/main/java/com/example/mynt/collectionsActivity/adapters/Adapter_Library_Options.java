package com.example.mynt.collectionsActivity.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mynt.R;
import com.example.mynt.collectionsActivity.models.Model_Collections;
import com.example.mynt.collectionsActivity.models.Model_Library_Options;
import com.example.mynt.collectionsActivity.models.Model_User;
import com.example.mynt.dataAccessLayer.Database_Lite;

import java.util.ArrayList;

public class Adapter_Library_Options extends BaseAdapter {

    Context context;
    LayoutInflater inflater;
    ArrayList<Model_Library_Options> libraryOptionsList;
    Model_User model_user;

    public Adapter_Library_Options(Context context, ArrayList<Model_Library_Options> libraryOptionsList, Model_User model_user) {
        this.context = context;
        this.libraryOptionsList = libraryOptionsList;
        inflater = LayoutInflater.from(context);
        this.model_user = model_user;
    }

    @Override
    public int getCount() {
        return libraryOptionsList.size();
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

        //Change to recycler view
        convertView = inflater.inflate(R.layout.listview_library_navigation, null);

        ImageView optionIcon = convertView.findViewById(R.id.library_nav_icon);
        TextView optionName = convertView.findViewById(R.id.library_nav_optionName);
        ProgressBar optionProgress = convertView.findViewById(R.id.progressBar);
        TextView optionValue = convertView.findViewById(R.id.library_nav_value);

        optionName.setText(this.libraryOptionsList.get(position).getOptionName());
        String OptionValue;
        OptionValue = this.libraryOptionsList.get(position).getOptionValue()+"";

        Database_Lite db = new Database_Lite(context);

        ArrayList<Integer> userCollectionIDs = db.getAllCollectionsForUser(model_user);
        ArrayList<Model_Collections> allCollections = db.getAllCollections();

        ArrayList<Model_Collections> allUserCollections = new ArrayList<>();

        for (int i=0; i<allCollections.size(); i++)
        {
            if(userCollectionIDs.contains(allCollections.get(i).getCollectionID()))
            allUserCollections.add(allCollections.get(i));
        }

        int goalTotalProgress = 0;
        for (int i=0; i<allUserCollections.size(); i++)
        {
            ArrayList<Integer> coinsInCollection  = db.getAllCoinsInCollection(allUserCollections.get(i).getCollectionID());

            float coins = (float)coinsInCollection.size();
            float target = (float)allUserCollections.get(i).getGoal();
            float progress =  coins /target *100;

            if(progress>99)
            {
                goalTotalProgress = goalTotalProgress + 100;
            }else
                goalTotalProgress = goalTotalProgress + Math.round(progress);
        }

        float forProgressBar = (float)goalTotalProgress/allUserCollections.size();
        //Get all Collections and goals
        //Get all coins within each collection using collectionsCoin
        switch (position)
        {

            case 0:
                optionIcon.setBackgroundResource(R.drawable.img_app_logo);
                optionValue.setText(OptionValue);
                optionProgress.setVisibility(View.INVISIBLE);
                break;
            case 1:
                optionIcon.setBackgroundResource(R.drawable.ic_collection_icon);
                optionValue.setVisibility(View.INVISIBLE);
                optionProgress.setVisibility(View.INVISIBLE);
                break;
            default:
                optionIcon.setBackgroundResource(R.drawable.ic_goal_icon);
                String progressPercent = String.valueOf(Math.round(forProgressBar)) + '%';
                optionValue.setText(progressPercent);
                optionProgress.setProgress(Math.round(forProgressBar));
                break;
        }

        return convertView;
    }


}
