package com.example.bookinglaneadmin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookinglaneadmin.R;
import com.example.bookinglaneadmin.utils.OnItemClickListener;

public class CarDetailsPriceAdapter extends RecyclerView.Adapter<CarDetailsPriceAdapter.MyViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";
    Context context;
    private String[] itemsDescr = new String[]{"Per hour", "Per min", "Per mile", "Per point"};
    private String[] itemsPrice = new String[]{"$80", "$5", "$1", "$20"};
    private OnItemClickListener onItemClickListener;
    private int chosenElement = -1;

    public CarDetailsPriceAdapter(Context context) {
        this.context = context;
        this.onItemClickListener = onItemClickListener;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(context).inflate(R.layout.car_detail_price_item, parent, false);
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();

        layoutParams.setMargins(8, 8, 8, 8);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return itemsPrice.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textViewDescr;
        TextView textViewPrice;

        public MyViewHolder(@NonNull final View itemView) {
            super(itemView);
            textViewDescr = itemView.findViewById(R.id.desc_tv_details_item);
            textViewPrice = itemView.findViewById(R.id.price_tv_details_item);

        }

        public void bind(final int position) {

            textViewPrice.setText(itemsPrice[position]);
            textViewDescr.setText(itemsDescr[position]);

        }
    }

}
