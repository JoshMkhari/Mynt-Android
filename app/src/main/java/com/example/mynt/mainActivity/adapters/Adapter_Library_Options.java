package com.example.mynt.mainActivity.adapters;

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
import com.example.mynt.mainActivity.models.Model_Library_Options;

import java.util.ArrayList;

public class Adapter_Library_Options extends BaseAdapter {

    Context context;
    LayoutInflater inflater;
    ArrayList<Model_Library_Options> libraryOptionsList;

    public Adapter_Library_Options(Context context, ArrayList<Model_Library_Options> libraryOptionsList) {
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

        convertView = inflater.inflate(R.layout.listview_library_navigation, null);

        ImageView optionIcon = convertView.findViewById(R.id.library_nav_icon);
        TextView optionName = convertView.findViewById(R.id.library_nav_optionName);
        ProgressBar optionProgress = convertView.findViewById(R.id.progressBar);
        TextView optionValue = convertView.findViewById(R.id.library_nav_value);

        optionName.setText(this.libraryOptionsList.get(position).getOptionName());
        switch (position)
        {
            case 0:
                optionIcon.setBackgroundResource(R.drawable.img_app_logo);
                optionValue.setText(this.libraryOptionsList.get(position).getOptionValue()+"");
                optionProgress.setVisibility(View.INVISIBLE);
                break;
            case 1:
                optionIcon.setBackgroundResource(R.drawable.ic_collection_icon);
                optionValue.setVisibility(View.INVISIBLE);
                optionProgress.setVisibility(View.INVISIBLE);
                break;
            default:
                optionIcon.setBackgroundResource(R.drawable.ic_goal_icon);
                optionValue.setText(this.libraryOptionsList.get(position).getOptionValue()+"");
                optionProgress.setProgress(this.libraryOptionsList.get(position).getProgress());
                break;
        }

        return convertView;
    }


}
