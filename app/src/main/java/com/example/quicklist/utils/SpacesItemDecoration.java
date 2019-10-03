package com.example.quicklist.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
    private int space;

    public SpacesItemDecoration(Context context, int space) {
        Resources r = context.getResources();
        //80 - margins
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, space, r.getDisplayMetrics());
        this.space = px;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view,
                               RecyclerView parent, RecyclerView.State state) {
        int spanCount = getTotalSpanCount(parent);

        int position = parent.getChildAdapterPosition(view);

        if (position % spanCount == 1) {
            Log.e("TAG", "getItemOffsets: "+spanCount );
            outRect.right = space;
        }

        outRect.left = space;
        outRect.bottom = space;


        // Add top margin only for the first (and the second if it's two span) item to avoid double space between items
        if (parent.getChildLayoutPosition(view) == 0 || parent.getChildLayoutPosition(view) == 1) {
            outRect.top = space;
        } else {
            outRect.top = 0;
        }
    }

    private int getTotalSpanCount(RecyclerView parent) {
        final RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        return layoutManager instanceof GridLayoutManager
                ? ((GridLayoutManager) layoutManager).getSpanCount()
                : 1;
    }
}
