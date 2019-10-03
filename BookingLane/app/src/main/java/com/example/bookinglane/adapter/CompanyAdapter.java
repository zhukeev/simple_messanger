package com.example.bookinglane.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookinglane.R;

import java.util.List;

public class CompanyAdapter extends RecyclerView.Adapter<CompanyAdapter.MyViewHolder> {


    private static final String TAG = "RecyclerViewAdapter";
    static List<Integer> car;
    final String url = "https://krista.fashion/uploads/product_images/";
    Context context;

    public CompanyAdapter(Context context, List<Integer> car) {
        this.context = context;
        this.car = car;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(context).inflate(R.layout.companies_cardview_item, parent, false);
        RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams) view.getLayoutParams();

        lp.setMargins(16, 16, 16, 16);


        view.setLayoutParams(lp);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        holder.imageView.setImageResource(car.get(position));

        Resources r = context.getResources();
        //56 - margins
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 56, r.getDisplayMetrics());


        DisplayMetrics displaymetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);

        RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) holder.layoutItem.getLayoutParams();

//        params.width = (displaymetrics.widthPixels-px) / 2;

        holder.layoutItem.setLayoutParams(params);

        holder.layoutItem.setOnClickListener(v -> {
            Navigation.findNavController(((Activity)context), R.id.nav_host_fragment).navigate(R.id.nav_company);

        });


    }

    @Override
    public int getItemCount() {
        return car.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        CardView layoutItem;

        public MyViewHolder(@NonNull final View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.company_icon_iv);
            layoutItem = itemView.findViewById(R.id.company_cardView);


        }
    }


}
