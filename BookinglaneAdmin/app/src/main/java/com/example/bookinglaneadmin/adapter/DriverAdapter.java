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
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookinglaneadmin.R;
import com.example.bookinglaneadmin.model.Driver;

import java.util.ArrayList;
import java.util.List;


public class DriverAdapter extends RecyclerView.Adapter<DriverAdapter.MyViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";
    Context context;
    List<Driver> drivers = new ArrayList<>();

    public DriverAdapter(Context context) {
        this.context = context;

        drivers.add(new Driver("Isaac Newton","+(1) 464 684","ritthy.hoffman@example.com","2099 Walnut Hill Ln Lafayette, California 55262"));
        drivers.add(new Driver("Arthur Conan Doyle","+(1) 464 684","ritthy.hoffman@example.com","2099 Walnut Hill Ln Lafayette, California 55262"));
        drivers.add(new Driver("Albert Einstein","+(1) 464 684","ritthy.hoffman@example.com","2099 Walnut Hill Ln Lafayette, California 55262"));
        drivers.add(new Driver("Richard Feynman","+(1) 464 684","ritthy.hoffman@example.com","2099 Walnut Hill Ln Lafayette, California 55262"));
        drivers.add(new Driver("Ernest Rutherford","+(1) 464 684","ritthy.hoffman@example.com","2099 Walnut Hill Ln Lafayette, California 55262"));
        drivers.add(new Driver("Erwin Schrödinger","+(1) 464 684","ritthy.hoffman@example.com","2099 Walnut Hill Ln Lafayette, California 55262"));
        drivers.add(new Driver("Niels Bohr","+(1) 464 684","ritthy.hoffman@example.com","2099 Walnut Hill Ln Lafayette, California 55262"));
        drivers.add(new Driver("Marie Curie","+(1) 464 684","ritthy.hoffman@example.com","2099 Walnut Hill Ln Lafayette, California 55262"));
        drivers.add(new Driver("Michael Faraday","+(1) 464 684","ritthy.hoffman@example.com","2099 Walnut Hill Ln Lafayette, California 55262"));
        drivers.add(new Driver("Nicola Tesla","+(1) 464 684","ritthy.hoffman@example.com","2099 Walnut Hill Ln Lafayette, California 55262"));

    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Resources r = context.getResources();
        //80 - margins
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, r.getDisplayMetrics());

        View view = LayoutInflater.from(context).inflate(R.layout.driver_item, parent, false);


        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return drivers.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView fullname_tv;
        TextView phone_tv;
        TextView email_tv;
        TextView address_tv;
        RelativeLayout holder_rl;

        public MyViewHolder(@NonNull final View itemView) {
            super(itemView);
            fullname_tv = itemView.findViewById(R.id.fullname_driver_item);
            phone_tv = itemView.findViewById(R.id.phone_number_driver_item);
            email_tv = itemView.findViewById(R.id.email_driver_item);
            address_tv = itemView.findViewById(R.id.address_driver_item);

            holder_rl = itemView.findViewById(R.id.driver_item_holder_rl);
        }

        public void bind(final int position) {

            fullname_tv.setText(drivers.get(position).getFullname());
            phone_tv.setText(drivers.get(position).getPhone());
            email_tv.setText(drivers.get(position).getEmail());
            address_tv.setText(drivers.get(position).getAddress());

        }
    }

}
