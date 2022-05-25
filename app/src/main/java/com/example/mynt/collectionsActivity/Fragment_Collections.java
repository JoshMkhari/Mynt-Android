package com.example.mynt.collectionsActivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.mynt.R;
import com.example.mynt.RecyclerViewInterface;
import com.example.mynt.collectionsActivity.adapters.Adapter_Collections;
import com.example.mynt.collectionsActivity.models.Model_Coin;
import com.example.mynt.collectionsActivity.models.Model_Collections;
import com.example.mynt.collectionsActivity.models.Model_Goals;
import com.example.mynt.dataAccessLayer.Database_Lite;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class Fragment_Collections extends Fragment implements RecyclerViewInterface {
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

        collectionsList = new ArrayList<>();
        collectionsList = db.getAllCollections();
        task = getArguments().getInt("Task");
        int imageID = getArguments().getInt("ImageID");

       // model_goals = new Model_Goals(collectionName.getText().toString(),0,0);
        recyclerView = collectionsView.findViewById(R.id.all_collectionsList);

        recyclerView.setHasFixedSize(true);

        layoutManager = new StaggeredGridLayoutManager(1,1);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new Adapter_Collections(collectionsList, getContext(), this);
        recyclerView.setAdapter(mAdapter);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get task
                assert getArguments() != null;

                if(task==1)// Creating new Collection and assigning it to a coin
                {
                    Bundle bundle = new Bundle();
                    bundle.putBoolean("Back",true);
                    bundle.putInt("ImageID",imageID);
                    Navigation.findNavController(collectionsView).navigateUp();

                }else
                {
                    Intent home = new Intent(getContext(),Activity_Collections.class);
                    //home.putExtra("view","library");
                    home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(home);
                }
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
                    assert getArguments() != null;
                    bundle.putInt("Task",task);
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


}
