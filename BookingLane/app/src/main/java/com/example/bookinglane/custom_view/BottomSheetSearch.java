package com.example.bookinglane.custom_view;

import android.app.Dialog;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.bookinglane.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BottomSheetSearch extends BottomSheetDialogFragment {

    private BottomSheetBehavior behavior;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        BottomSheetDialog dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);

        View view = View.inflate(getContext(), R.layout.bottom_sheet_search, null);

        LinearLayout linearLayout = view.findViewById(R.id.bottom_sheet);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,500);
        params.height = getScreenHeight();
        linearLayout.setLayoutParams(params);


        dialog.setContentView(view);
        behavior = BottomSheetBehavior.from((View) view.getParent());
        behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View view, int i) {
                Log.e("TAG", "onStateChanged: "+i );
                if (i == BottomSheetBehavior.STATE_HALF_EXPANDED || i == BottomSheetBehavior.STATE_COLLAPSED ){
                    behavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                }
            }
            @Override
            public void onSlide(@NonNull View view, float v) {

            }
        });
        return dialog;
    }

    public int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }


    @Override
    public void onStart() {
        super.onStart();
        behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }
}
