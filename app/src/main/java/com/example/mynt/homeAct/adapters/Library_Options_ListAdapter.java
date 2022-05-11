package com.example.mynt.homeAct.adapters;

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
import com.example.mynt.homeAct.models.Library_Options_Model;

import java.util.ArrayList;

public class Library_Options_ListAdapter extends BaseAdapter {

    Context context;
    LayoutInflater inflater;
    ArrayList<Library_Options_Model> libraryOptionsList;

    public Library_Options_ListAdapter(Context context,ArrayList<Library_Options_Model> libraryOptionsList) {
        this.context = context;
        this.libraryOptionsList = libraryOptionsList;
        inflater = LayoutInflater.from(context);
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

        convertView = inflater.inflate(R.layout.library_navigation_listview, null);

        ImageView optionIcon = convertView.findViewById(R.id.library_nav_icon);
        TextView optionName = convertView.findViewById(R.id.library_nav_optionName);
        ProgressBar optionProgress = convertView.findViewById(R.id.progressBar);
        TextView optionValue = convertView.findViewById(R.id.library_nav_value);

        optionName.setText(this.libraryOptionsList.get(position).getOptionName());
        switch (position)
        {
            case 0:
                optionIcon.setBackgroundResource(R.drawable.app_logo);
                optionValue.setText(this.libraryOptionsList.get(position).getOptionValue()+"");
                optionProgress.setVisibility(View.INVISIBLE);
                break;
            case 1:
                optionIcon.setBackgroundResource(R.drawable.collection_icon);
                optionValue.setVisibility(View.INVISIBLE);
                optionProgress.setVisibility(View.INVISIBLE);
                break;
            default:
                optionIcon.setBackgroundResource(R.drawable.goal_icon);
                optionValue.setText(this.libraryOptionsList.get(position).getOptionValue()+"");
                optionProgress.setProgress(this.libraryOptionsList.get(position).getProgress());
                break;
        }

        return convertView;
    }


}
