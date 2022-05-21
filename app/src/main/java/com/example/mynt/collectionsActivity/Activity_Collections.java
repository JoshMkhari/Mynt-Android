package com.example.mynt.collectionsActivity;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.mynt.R;
import com.example.mynt.RecyclerViewInterface;
import com.example.mynt.goalsActivity.Activity_Goals;
import com.example.mynt.goalsActivity.Model_Goals;

import java.util.ArrayList;

public class Activity_Collections extends AppCompatActivity implements RecyclerViewInterface {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ImageButton createCollection;
    private ActivityResultLauncher<Intent> activityResultLauncher_Goals;
    private EditText collectionName;
    private Model_Goals model_goals;
    private Boolean subActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collections);

        Bundle extras = getIntent().getExtras();
        if(extras != null)
        {
            subActivity = true;
        }
        createCollection = findViewById(R.id.imageview_blockTitle_collections);

        collectionName = findViewById(R.id.CollectionNameEditText);

        /*
        ArrayList<Model_Collections> collectionsList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            collectionsList.add(new Model_Collections("20th Century",9,55,R.drawable.img_two_rand));
        }

        model_goals = new Model_Goals(collectionName.getText().toString(),0,0);

        recyclerView = (RecyclerView) findViewById(R.id.all_collectionsList);

        recyclerView.setHasFixedSize(true);

        layoutManager = new StaggeredGridLayoutManager(1,1);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new Adapter_Collections(collectionsList, this, this);
        recyclerView.setAdapter(mAdapter);


         */
        createCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Activity_Goals.class);
                i.putExtra("collectionName",model_goals.getCollectionName());

                if(subActivity)
                {
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
            }

        });

        activityResultLauncher_Goals = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode()==14)
                {
                    Intent i = result.getData();
                    if(i != null)
                    {
                        String target = i.getStringExtra("target");
                        model_goals.setTarget(Integer.parseInt(target));

                        if(subActivity)
                        {
                            Intent complete = new Intent();
                            i.putExtra("collectionName",model_goals.getCollectionName());
                            i.putExtra("target",target);
                            setResult(20,i);

                            Activity_Collections.super.onBackPressed();
                        }
                    }
                }
            }
        });
    }

    //implementing RecyclerViewInterface
    @Override
    public void onItemClick(int position) {
        if(collectionName.getText().length()>3)
        {
            Intent setGoal = new Intent(getApplicationContext(), Activity_Goals.class);
            setGoal.putExtra("collectionName",model_goals.getCollectionName());
            setGoal.putExtra("coins",model_goals.getNumCoins());
            setGoal.putExtra("target",model_goals.getTarget());
            activityResultLauncher_Goals.launch(setGoal);
        }
    }
}