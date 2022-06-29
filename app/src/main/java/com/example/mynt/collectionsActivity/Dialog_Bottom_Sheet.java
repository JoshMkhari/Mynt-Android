package com.example.mynt.collectionsActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.mynt.R;
import com.example.mynt.collectionsActivity.adapters.Adapter_Modal;
import com.example.mynt.collectionsActivity.interfaces.Interface_BottomSheet;
import com.example.mynt.collectionsActivity.adapters.Adapter_Leaderboard;
import com.example.mynt.collectionsActivity.interfaces.Interface_RecyclerView;
import com.example.mynt.collectionsActivity.library.Fragment_Coin_Details;
import com.example.mynt.collectionsActivity.models.Model_Leaderboard;
import com.example.mynt.collectionsActivity.models.Model_User_Data;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;

public class Dialog_Bottom_Sheet extends BottomSheetDialogFragment implements Interface_RecyclerView {
    private Interface_BottomSheet bottomSheetListener;
    RecyclerView recycler_view_modal;
    RecyclerView.Adapter<Adapter_Modal.Card_View_Holder> rv_modal_adapter;
    View bottom;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        bottom = inflater.inflate(R.layout.bottom_sheet_layout,container,false);

        ImageButton close = bottom.findViewById(R.id.bottom_sheet_close);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        //Passing data to list recycler view
        recycler_view_modal = bottom.findViewById(R.id.recycler_view_modal);
        recycler_view_modal.setHasFixedSize(true);

        RecyclerView.LayoutManager layout_manager_leaderboard = new StaggeredGridLayoutManager(1, 1);//(Professor Sluiter, 2020).
        recycler_view_modal.setLayoutManager(layout_manager_leaderboard);

        //Setting up adapter
        rv_modal_adapter = new Adapter_Modal(Model_User_Data.array_list_bottomSheet,Model_User_Data.mode,getContext(),this);//(Professor Sluiter, 2020).
        recycler_view_modal.setAdapter(rv_modal_adapter);

        ImageView currentCoinImage = bottom.findViewById(R.id.imageview_constraint_current_coin);
        TextView currentCoinTitle = bottom.findViewById(R.id.textview_coin_title_constraint);
        TextView currentCoinSubTitle = bottom.findViewById(R.id.textview_coin_sub_title_constraint);

        try{
            Bitmap bmp = BitmapFactory.decodeByteArray(Model_User_Data.model_coin.getImageId(), 0, Model_User_Data.model_coin.getImageId().length);
            currentCoinImage.setImageBitmap(bmp);
        }
        catch(Exception ignored){
        }

        String title = Model_User_Data.model_coin.getValue() + ", " + Model_User_Data.model_coin.getYear();

        currentCoinTitle.setText(title);
        currentCoinSubTitle.setText("South Africa");



        return bottom;
    }

    @Override
    public void onItemClick(int position, ImageView coinImage) {

        Model_User_Data.sheetModal = true;
        Model_User_Data.position = position;
        bottomSheetListener.onButtonClicked(position);
        dismiss();
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            bottomSheetListener = (Interface_BottomSheet) context;
        } catch (ClassCastException e)
        {
            throw new ClassCastException((context.toString() + " must implement bottom sheet listener"));
        }
    }







}
