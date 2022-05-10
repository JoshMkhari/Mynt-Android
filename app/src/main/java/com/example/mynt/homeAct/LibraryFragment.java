package com.example.mynt.homeAct;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.mynt.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LibraryFragment#} factory method to
 * create an instance of this fragment.
 */
public class LibraryFragment extends Fragment {
    ListView optionListView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View libraryView = inflater.inflate(R.layout.fragment_library, container, false);
        optionListView = (ListView) libraryView.findViewById(R.id.libraryOptionsListView);

        ArrayList<Library_Options_Model> libraryOptionsList = new ArrayList<>();

        libraryOptionsList.add(new Library_Options_Model( R.drawable.app_logo,
                getResources().getString(R.string.library_option_coins),
                0,
                905));

        libraryOptionsList.add(new Library_Options_Model( R.drawable.collection_icon,
                getResources().getString(R.string.library_option_collections),
                0,
                0));

        libraryOptionsList.add(new Library_Options_Model( R.drawable.goal_icon,
                getResources().getString(R.string.library_option_goals),
                62,
                62));
        //libraryOptionsList.add(library_options_model);
        //libraryOptionsList.add(library_options_model);

        //Library_Options_Model library_options_model2 = new Library_Options_Model( R.drawable.collection_icon,
         //       getResources().getString(R.string.library_option_collections),
         //       0,
         //       0);
       // libraryOptionsList.add(library_options_model2);

       // Library_Options_Model library_options_model3 = new Library_Options_Model( R.drawable.goal_icon,
        //        getResources().getString(R.string.library_option_goals),
         //       62,
         //       62);
        //libraryOptionsList.add(library_options_model3);

        Library_Options_ListAdapter optionsListAdapter = new Library_Options_ListAdapter(getContext(),libraryOptionsList);
        optionListView.setAdapter(optionsListAdapter);
        return libraryView;
    }
}