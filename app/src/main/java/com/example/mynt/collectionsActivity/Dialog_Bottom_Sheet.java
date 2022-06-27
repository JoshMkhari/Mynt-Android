package com.example.mynt.collectionsActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mynt.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class Dialog_Bottom_Sheet extends BottomSheetDialogFragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View bottom = inflater.inflate(R.layout.bottom_sheet_layout,container,false);

        ImageView currentCoinImage = bottom.findViewById(R.id.imageview_constraint_current_coin);
        TextView currentCoinTitle = bottom.findViewById(R.id.textview_coin_title_constraint);
        TextView currentCoinSubTitle = bottom.findViewById(R.id.textview_coin_sub_title_constraint);
        return bottom;
    }
}
