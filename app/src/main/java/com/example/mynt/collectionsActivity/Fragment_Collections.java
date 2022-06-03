package com.example.mynt.collectionsActivity;

import static androidx.navigation.fragment.NavHostFragment.findNavController;

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
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mynt.R;
import com.example.mynt.Interface_RecyclerView;
import com.example.mynt.collectionsActivity.adapters.Adapter_Collections;
import com.example.mynt.collectionsActivity.models.Model_Collections;
import com.example.mynt.collectionsActivity.models.Model_User;
import com.example.mynt.dataAccessLayer.Database_Lite;

import java.util.ArrayList;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class Fragment_Collections extends Fragment implements Interface_RecyclerView {
    private ImageButton createCollection, back;
    private EditText collectionName;
    private View collectionsView;
    private int task;
    private Database_Lite db;
    private Model_User model_user;//(Section, 2021)
    private ArrayList<Model_Collections> allUserCollections;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        collectionsView = inflater.inflate(R.layout.fragment_collections, container, false);
        db = new Database_Lite(getContext());

        createCollection = collectionsView.findViewById(R.id.imageview_blockTitle_collections);
        back = collectionsView.findViewById(R.id.collections_back);
        collectionName = collectionsView.findViewById(R.id.CollectionNameEditText);


        assert getArguments() != null;
        task = getArguments().getInt("Task");
        model_user = new Model_User();
        model_user.setUserID(getArguments().getInt("User"));

        String userID = model_user.getUserID() + " this";
        Log.d("collections", userID);

        DisplayAllLocalCollections();
        CreateCollection();
        ReturnToHomePage();


        //model_goals = new Model_Goals(collectionName.getText().toString(),0,0);
        RecyclerView recyclerView = collectionsView.findViewById(R.id.all_collectionsList);

        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(1, 1);
        recyclerView.setLayoutManager(layoutManager);

        RecyclerView.Adapter<Adapter_Collections.CollectionsViewHolder> mAdapter = new Adapter_Collections(allUserCollections, getContext(), this,model_user);//(Professor Sluiter, 2020).
        recyclerView.setAdapter(mAdapter);



        return collectionsView;
    }

    private void DisplayAllLocalCollections(){

        ArrayList<Integer> userCollectionIDs = db.getAllCollectionsForUser(model_user);
        ArrayList<Model_Collections> allCollections = db.getAllCollections();

        allUserCollections = new ArrayList<>();

        for (int i = 0; i< allCollections.size(); i++)
        {
            if(userCollectionIDs.contains(allCollections.get(i).getCollectionID()))
                allUserCollections.add(allCollections.get(i));
        }
    }

    private void ReturnToHomePage(){

        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                backActivity();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(),callback);

        back.setOnClickListener(v -> backActivity());

    }

    private void CreateCollection(){

        createCollection.setOnClickListener(v -> {
            if(collectionName.getText().toString().length()>3)
            {
                Bundle bundle = new Bundle();
                bundle.putString("Collection Name", collectionName.getText().toString());
                bundle.putInt("Coins", 0);
                bundle.putInt("Goal", 0);
                bundle.putInt("Task",task);
                bundle.putInt("User",model_user.getUserID());
                Navigation.findNavController(collectionsView).navigate(R.id.action_fragment_Collections_to_fragment_Goal,bundle);

            }
            else
            {
                //Additional User Feedback
                Toast.makeText(getContext(),"Error: Your collection has not been created successfully.",Toast.LENGTH_SHORT).show();//(Alexander, 2016).
                Toast.makeText(getContext(),"A collection name has not been set.",Toast.LENGTH_SHORT).show();//(Alexander, 2016).
                Toast.makeText(getContext(),"Please enter a name for your collection.",Toast.LENGTH_SHORT).show();//(Alexander, 2016).
            }

        });



    }
    //implementing RecyclerViewInterface
    @Override
    public void onItemClick(int position, ImageView coinImage) {

        Bundle bundle = new Bundle();
        bundle.putString("Collection Name",allUserCollections.get(position).getCollectionName());
        bundle.putInt("Task", 1);
        bundle.putInt("CollectionID", allUserCollections.get(position).getCollectionID());
        bundle.putInt("User", model_user.getUserID());
        Navigation.findNavController(collectionsView).navigate(R.id.action_fragment_Collections_to_fragment_Coins,bundle);

    }


    private void backActivity() {
        if(task==1)// Creating new Collection and assigning it to a coin
        {
            Navigation.findNavController(collectionsView).navigateUp();

        }else
        {
            Bundle bundle = new Bundle();
            bundle.putInt("StartPage",0);
            findNavController(Objects.requireNonNull(getParentFragmentManager().findFragmentById(R.id.fragmentContainerView2))).
                    setGraph(R.navigation.collection_navigation,bundle);//(developer Android NavController, n.d)
        }
    }
}
