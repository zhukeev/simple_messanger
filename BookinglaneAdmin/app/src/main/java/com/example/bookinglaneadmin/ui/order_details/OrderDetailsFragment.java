package com.example.bookinglaneadmin.ui.order_details;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.bookinglaneadmin.R;
import com.example.bookinglaneadmin.model.Order;

public class OrderDetailsFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "OrderDetailsFragment";

    private OrderDetailsViewModel orderDetailsViewModel;
    private Order order;
    private LinearLayout driver_details_rl,buttons_ll;
    private ImageView add_driver_iv;
    private TextView total_cost_tv;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        orderDetailsViewModel =
                ViewModelProviders.of(this).get(OrderDetailsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_order_details, container, false);

        init(root);

        return root;
    }

    private void init(View root) {

        driver_details_rl = root.findViewById(R.id.driver_details_holder_rl);
        add_driver_iv = root.findViewById(R.id.add_driver_iv_reservation_details);
        total_cost_tv = root.findViewById(R.id.total_cost_tv_order_details);
        buttons_ll = root.findViewById(R.id.buttons_order_details_ll);


        driver_details_rl.setVisibility(View.INVISIBLE);
        buttons_ll.setVisibility(View.INVISIBLE);
        add_driver_iv.setVisibility(View.INVISIBLE);
        total_cost_tv.setVisibility(View.INVISIBLE);
        add_driver_iv.setOnClickListener(this);


        getExtras();

    }

    private void getExtras() {

        if (getArguments() != null && !getArguments().isEmpty()) {
            order = (Order) getArguments().getSerializable("order");

            updateUI(order.getStatus());
        }
    }

    private void updateUI(int status) {

        switch (status) {
            case Order.CANCELED_LIST:
                add_driver_iv.setVisibility(View.VISIBLE);
                add_driver_iv.setColorFilter(ContextCompat.getColor(getContext(), R.color.driver_box));
                add_driver_iv.setOnClickListener(null);
                break;
            case Order.COME_LIST:
                add_driver_iv.setVisibility(View.VISIBLE);
                buttons_ll.setVisibility(View.VISIBLE);
                break;
            case Order.HISTORY_LIST:
                driver_details_rl.setVisibility(View.VISIBLE);
                total_cost_tv.setVisibility(View.VISIBLE);
                break;
            case Order.PERFORMED_LIST:
                driver_details_rl.setVisibility(View.VISIBLE);
                break;

        }
    }

    @Override
    public void onClick(View view) {

    }
}