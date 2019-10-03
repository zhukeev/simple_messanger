package com.example.bookinglane.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookinglane.OnItemClickListener;
import com.example.bookinglane.R;


public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";
    Context context;
    private int[] car_image_ids = new int[]{R.drawable.sedan, R.drawable.suv, R.drawable.bus, R.drawable.limousine};
    private String[] items = new String[]{"Sedan", "SUV", "Bus", "Limo"};
    private OnItemClickListener onItemClickListener;
    private int chosenElement = 0;

    public CategoryAdapter(Context context, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.onItemClickListener = onItemClickListener;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(context).inflate(R.layout.category_item, parent, false);


        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        Resources r = context.getResources();
        //80 - margins
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 80, r.getDisplayMetrics());


        DisplayMetrics displaymetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);

        RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) holder.linearLayout.getLayoutParams();

        params.width = (displaymetrics.widthPixels - px) / 4;

        holder.linearLayout.setLayoutParams(params);


        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return items.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        LinearLayout linearLayout;

        public MyViewHolder(@NonNull final View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.category_image_category_item);
            textView = itemView.findViewById(R.id.car_type_tv_category_item);
            linearLayout = itemView.findViewById(R.id.item__holder_ll);
        }

        public void bind(final int position) {

            imageView.setImageResource(car_image_ids[position]);
            textView.setText(items[position]);

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT );

            if (position==3){
                layoutParams.setMargins(0, 0, 0,0);
                imageView.setLayoutParams(layoutParams);
            }else {
                int px = convertDpToPx(3,context.getResources().getDisplayMetrics());
                layoutParams.setMargins(px, 0, px,0);

                imageView.setLayoutParams(layoutParams);
            }

            if (chosenElement == getAdapterPosition()) {
                linearLayout.setBackground(context.getDrawable(R.drawable.gradient_btn));
            } else {
                linearLayout.setBackground(context.getDrawable(R.drawable.car_box_bg));
            }

            linearLayout.setOnClickListener(v -> {

                onItemClickListener.onItemClickListener(position);

                linearLayout.setBackground(context.getDrawable(R.drawable.gradient_btn));

                if (chosenElement != getAdapterPosition()) {
                    notifyItemChanged(chosenElement);
                    chosenElement = getAdapterPosition();
                }

            });

        }
    }
    private int convertDpToPx(int dp, DisplayMetrics displayMetrics) {
        float pixels = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, displayMetrics);
        return Math.round(pixels);
    }
}
