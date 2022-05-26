package com.example.mynt.collectionsActivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.mynt.Interface_Back;
import com.example.mynt.R;
import com.example.mynt.Interface_RecyclerView;
import com.example.mynt.collectionsActivity.adapters.Adapter_Collections;
import com.example.mynt.collectionsActivity.models.Model_Collections;
import com.example.mynt.collectionsActivity.models.Model_User;
import com.example.mynt.dataAccessLayer.Database_Lite;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class Fragment_Collections extends Fragment implements Interface_RecyclerView, Interface_Back {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ImageButton createCollection, back;
    private EditText collectionName;
    //private Model_Goals model_goals;
    private Boolean subActivity;
    private ArrayList<Model_Collections> collectionsList;
    private View collectionsView;
    private int task;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        collectionsView = inflater.inflate(R.layout.fragment_collections, container, false);
        Database_Lite db = new Database_Lite(getContext());

        createCollection = collectionsView.findViewById(R.id.imageview_blockTitle_collections);
        back = collectionsView.findViewById(R.id.collections_back);
        collectionName = collectionsView.findViewById(R.id.CollectionNameEditText);


        task = getArguments().getInt("Task");
        Model_User model_user = new Model_User();
        model_user.setUserID(getArguments().getInt("User"));

        String userID = model_user.getUserID() + " this";
        Log.d("collections", userID);

        ArrayList<Integer> userCollectionIDs = db.getAllCollectionsForUser(model_user);
        ArrayList<Model_Collections> allCollections = db.getAllCollections();

        ArrayList<Model_Collections> allUserCollections = new ArrayList<>();

        for (int i=0; i<allCollections.size(); i++)
        {
            if(userCollectionIDs.contains(allCollections.get(i).getCollectionID()))
                allUserCollections.add(allCollections.get(i));
        }
        String sizee = allUserCollections.size() + " this";
        Log.d("allUserCollections", sizee);

        collectionsList = allUserCollections;
        int imageID = getArguments().getInt("ImageID");

       // model_goals = new Model_Goals(collectionName.getText().toString(),0,0);
        recyclerView = collectionsView.findViewById(R.id.all_collectionsList);

        recyclerView.setHasFixedSize(true);

        layoutManager = new StaggeredGridLayoutManager(1,1);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new Adapter_Collections(collectionsList, getContext(), this);
        recyclerView.setAdapter(mAdapter);

        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                backActivity();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(),callback);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backActivity();
            }

        });

        createCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(collectionName.getText().toString().length()>3)
                {
                    Bundle bundle = new Bundle();
                    bundle.putString("Collection Name", collectionName.getText().toString());
                    bundle.putInt("Coins", 0);;
                    bundle.putInt("Goal", 0);;
                    bundle.putInt("Task",task);
                    bundle.putInt("User",model_user.getUserID());
                    Navigation.findNavController(collectionsView).navigate(R.id.action_fragment_Collections_to_fragment_Goal,bundle);
                }
                else
                {
                    Toast.makeText(getContext(),"Set a collection name",Toast.LENGTH_SHORT).show();
                }

                /*
                Intent i = new Intent(getContext(), Activity_Collections.class);
                i.putExtra("collectionName",model_goals.getCollectionName());

                if(subActivity)
                {
                    Toast.makeText(getContext(),"we trying yo movr on",Toast.LENGTH_SHORT).show();
                    i.putExtra("coins",0);
                    i.putExtra("target",0);
                    activityResultLauncher_Goals.launch(i);
                }
                else
                {
                    i.putExtra("coins",model_goals.getNumCoins());
                    i.putExtra("target",model_goals.getTarget());
                    startActivity(i);
                }

                 */
            }

        });

        return collectionsView;
    }
    //implementing RecyclerViewInterface
    @Override
    public void onItemClick(int position) {

        Bundle bundle = new Bundle();
        bundle.putString("Collection Name",collectionsList.get(position).getCollectionName());
        bundle.putInt("Task", 1);;
        bundle.putInt("CollectionID", position+1);;
        Navigation.findNavController(collectionsView).navigate(R.id.action_fragment_Collections_to_fragment_Coins,bundle);

    }


    @Override
    public void backActivity() {
        if(task==1)// Creating new Collection and assigning it to a coin
        {
            Navigation.findNavController(collectionsView).navigateUp();

        }else
        {
            Intent home = new Intent(getContext(),Activity_Collections.class);
            //home.putExtra("view","library");
            home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(home);
        }
    }
}
