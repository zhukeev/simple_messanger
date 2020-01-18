package com.example.oil.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.util.TypedValue;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class VerticalSpaceItemDecoration extends RecyclerView.ItemDecoration {

    private final int verticalSpaceHeight;

    public VerticalSpaceItemDecoration(Context context, int verticalSpaceHeightDP) {
        Resources r = context.getResources();
        //80 - margins
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, verticalSpaceHeightDP, r.getDisplayMetrics());
        verticalSpaceHeight = px;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        outRect.bottom = verticalSpaceHeight;
        outRect.right = verticalSpaceHeight;
        outRect.left = verticalSpaceHeight;

    }

}
