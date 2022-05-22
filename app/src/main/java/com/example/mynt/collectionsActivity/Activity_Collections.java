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
import android.widget.Toast;

import com.example.mynt.R;
import com.example.mynt.RecyclerViewInterface;
import com.example.mynt.goalsActivity.Activity_Goals;
import com.example.mynt.goalsActivity.Model_Goals;

import java.util.ArrayList;

public class Activity_Collections extends AppCompatActivity {
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
    }

}