package com.example.bookinglaneadmin.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookinglaneadmin.R;
import com.example.bookinglaneadmin.model.Order;
import com.example.bookinglaneadmin.utils.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.MyViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";
    public List<Order> orders = new ArrayList<>();
    final String url = "https://krista.fashion/uploads/product_images/";
    static Context context;
    final int TYPE_USUAL = 0;
    final int TYPE_RECOMMEND = 1;
    private OnItemClickListener onItemClickListener;

    public static int ALL_LIST = 0;

    public int type = 0;

    public OrderAdapter(Context context, List<Order> orders, int type) {
        this.context = context;
        this.onItemClickListener = onItemClickListener;
        this.type = type;
        this.orders.clear();

        List<Order> orders1 = new ArrayList();
        if (type != ALL_LIST) {
            for (Order p : orders) {
                if (p.getStatus() == type) {
                    orders1.add(p);
                }
            }
        } else orders1 = new ArrayList<>(orders);

//        Log.e(TAG, "OrderAdapter: " + Arrays.toString(orders.toArray()));
//        Log.e(TAG, "OrderAdapter: " + Arrays.toString(orders1.toArray()));

        Log.e(TAG, "OrderAdapter: size " + orders1.size() + " " + type);


        this.orders.addAll(orders1);
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(context).inflate(R.layout.order_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {


        holder.from_tv.setText(orders.get(position).getFrom());
        holder.to_tv.setText(orders.get(position).getTo());
        holder.people_tv.setText(orders.get(position).getPeople());
        holder.time_tv.setText(orders.get(position).getTime());
        holder.time_of_receipt_tv.setText(orders.get(position).getTimeOfReceipt());
        holder.car_type_tv.setText(orders.get(position).getCarType());

        holder.setData(orders.get(position).getStatus());

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putSerializable("order",orders.get(position));

                Navigation.findNavController(((Activity)context), R.id.nav_host_fragment).navigate(R.id.nav_order_details,bundle);


            }
        });

    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView from_tv;
        TextView to_tv;
        TextView people_tv;
        TextView time_tv;
        TextView car_type_tv;
        TextView time_of_receipt_tv;
        ConstraintLayout layout;
//        CardView layoutItem;

        public MyViewHolder(@NonNull final View itemView) {
            super(itemView);

            from_tv = itemView.findViewById(R.id.from_tv_order_item);
            to_tv = itemView.findViewById(R.id.to_tv_order_item);
            people_tv = itemView.findViewById(R.id.people_tv_order_item);
            time_of_receipt_tv = itemView.findViewById(R.id.time_of_receipt_tv_order_item);
            time_tv = itemView.findViewById(R.id.time_tv_order_item);
            car_type_tv = itemView.findViewById(R.id.type_car_tv_order_item);
            layout = itemView.findViewById(R.id.orderLayout_order_item);
        }

        void setData(int status) {
            //region Set text and pic
            switch (status) {
                case Order.CANCELED_LIST:
                    layout.setBackgroundTintList(context.getResources().getColorStateList(R.color.cancel_color));
                    break;
                case Order.COME_LIST:
                    layout.setBackgroundTintList(context.getResources().getColorStateList(R.color.come_color));

                    break;
                case Order.HISTORY_LIST:
                    layout.setBackgroundTintList(context.getResources().getColorStateList(R.color.history_color));
                    break;
                default:
                    layout.setBackgroundTintList(context.getResources().getColorStateList(R.color.white));
                    break;

            }

            //endregion
        }


    }


}
