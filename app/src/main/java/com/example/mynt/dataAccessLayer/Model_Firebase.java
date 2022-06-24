package com.example.mynt.dataAccessLayer;

import android.content.Context;
import android.service.autofill.UserData;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.mynt.collectionsActivity.models.Model_Coin;
import com.example.mynt.collectionsActivity.models.Model_User_Data;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Model_Firebase {

    Model_Coin currentCoin;
    Context context;
    ArrayList<String> coinInfoList;
    public Model_Firebase(Model_Coin currentCoin, Context context) {
        this.currentCoin = currentCoin;
        this.context = context;
    }

    public void downloadCoinData()
    {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference mDatabase = database.getReference();
        coinInfoList = new ArrayList<>();
        String ValueYear = currentCoin.getValue() + "_"+currentCoin.getYear();
        mDatabase.child("Value_Year").child(ValueYear).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful())
                {
                    int count =0;
                    for (DataSnapshot coinData:task.getResult().getChildren()
                         ) {
                        if(count==0)
                        {
                            int mint = coinData.getValue(int.class);
                            coinInfoList.add(String.valueOf(mint));
                            count++;
                            continue;
                        }
                        coinInfoList.add(coinData.getValue(String.class));
                    }
                    Log.d("TAG", "onComplete: got data" );
                    if(coinInfoList.size()>0)
                    {
                        currentCoin.setMintage(Integer.parseInt(coinInfoList.get(0)));
                        currentCoin.setObserve(coinInfoList.get(1));
                        currentCoin.setReverse(coinInfoList.get(2));
                        currentCoin.setMaterial(coinInfoList.get(3));
                        Database_Lite localDB = new Database_Lite(context);
                        localDB.updateCoin(currentCoin);
                        float points = 700000000-((currentCoin.getMintage()/2)-currentCoin.getYear());
                        points = points/100000;
                        //Add to user points
                        Model_User_Data.currentUser.setPoints(points);
                    }
                }else
                    Log.d("TAG", "onComplete: failed to get data" );
            }
        });

    }

}
