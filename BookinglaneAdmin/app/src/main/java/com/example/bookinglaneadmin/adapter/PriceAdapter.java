package com.example.bookinglaneadmin.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookinglaneadmin.R;


public class PriceAdapter extends RecyclerView.Adapter<PriceAdapter.MyViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";
    Context context;
    private String[] priceType = new String[]{"Per min", "Per hour", "Per mile", "Per point"};
    private String[] price = new String[]{"10$", "60$", "50$", "20$"};

    public PriceAdapter(Context context) {
        this.context = context;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.price_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        Resources r = context.getResources();
        //80 - margins
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 46, r.getDisplayMetrics());


        DisplayMetrics displaymetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);

        RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) holder.holder_ll.getLayoutParams();

        Log.e(TAG, "onBindViewHolder: " + params.width);

        params.setMargins(16,16,16,16);
        params.width = (displaymetrics.widthPixels - px) / 3;

        holder.holder_ll.setLayoutParams(params);

        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return price.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView price_tv;
        TextView price_type_tv;
        LinearLayout holder_ll;

        public MyViewHolder(@NonNull final View itemView) {
            super(itemView);
            price_tv = itemView.findViewById(R.id.price_tv_price_item);
            price_type_tv = itemView.findViewById(R.id.price_type_tv_price_item);
            holder_ll = itemView.findViewById(R.id.price_item_holder_ll);
        }

        public void bind(final int position) {

            price_tv.setText(price[position]);
            price_type_tv.setText(priceType[position]);

        }
    }

}
