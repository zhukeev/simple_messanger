package com.example.bookinglaneadmin.ui.reservations_pro_subs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookinglaneadmin.R;
import com.example.bookinglaneadmin.adapter.OrderAdapter;
import com.example.bookinglaneadmin.model.Order;
import com.example.bookinglaneadmin.utils.VerticalSpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * interface.
 */
public class allFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public allFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static allFragment newInstance(int columnCount) {
        allFragment fragment = new allFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all, container, false);


        init(view);

        return view;
    }

    private void init(View view) {


        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list_all_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new VerticalSpaceItemDecoration(getContext(), 8));
        OrderAdapter adapter = new OrderAdapter(getContext(),getOrders(),OrderAdapter.ALL_LIST);
        recyclerView.getRecycledViewPool().clear();
        recyclerView.setAdapter(adapter);
        recyclerView.stopScroll();
        adapter.notifyDataSetChanged();

    }

    private List<Order> getOrders() {
        List<Order> orders = new ArrayList<>();

        orders.add(new Order("From: Asanbai 21", "To: Osipenko 113", 12, "15/08/2019 15:00", "Limousine", "Time of receipt: 24:00", Order.COME_LIST));
        orders.add(new Order("From: Asanbai 21", "To: Osipenko 113", 12, "15/08/2019 15:00", "Limousine", "Time of receipt: 24:00", Order.COME_LIST));
        orders.add(new Order("From: Asanbai 21", "To: Osipenko 113", 12, "15/08/2019 15:00", "Limousine", "Time of receipt: 24:00", Order.CANCELED_LIST));
        orders.add(new Order("From: Asanbai 21", "To: Osipenko 113", 12, "15/08/2019 15:00", "Limousine", "Time of receipt: 24:00", Order.CANCELED_LIST));
        orders.add(new Order("From: Asanbai 21", "To: Osipenko 113", 12, "15/08/2019 15:00", "Limousine", "Time of receipt: 24:00", Order.CANCELED_LIST));
        orders.add(new Order("From: Asanbai 21", "To: Osipenko 113", 12, "15/08/2019 15:00", "Limousine", "Time of receipt: 24:00", Order.CANCELED_LIST));
        orders.add(new Order("From: Asanbai 21", "To: Osipenko 113", 12, "15/08/2019 15:00", "Limousine", "Time of receipt: 24:00", Order.CANCELED_LIST));
        orders.add(new Order("From: Asanbai 21", "To: Osipenko 113", 12, "15/08/2019 15:00", "Limousine", "Time of receipt: 24:00", Order.COME_LIST));
        orders.add(new Order("From: Asanbai 21", "To: Osipenko 113", 12, "15/08/2019 15:00", "Limousine", "Time of receipt: 24:00", Order.COME_LIST));
        orders.add(new Order("From: Asanbai 21", "To: Osipenko 113", 12, "15/08/2019 15:00", "Limousine", "Time of receipt: 24:00", Order.COME_LIST));
        orders.add(new Order("From: Asanbai 21", "To: Osipenko 113", 12, "15/08/2019 15:00", "Limousine", "Time of receipt: 24:00", Order.COME_LIST));
        orders.add(new Order("From: Asanbai 21", "To: Osipenko 113", 12, "15/08/2019 15:00", "Limousine", "Time of receipt: 24:00", Order.COME_LIST));
        orders.add(new Order("From: Asanbai 21", "To: Osipenko 113", 12, "15/08/2019 15:00", "Limousine", "Time of receipt: 24:00", Order.PERFORMED_LIST));
        orders.add(new Order("From: Asanbai 21", "To: Osipenko 113", 12, "15/08/2019 15:00", "Limousine", "Time of receipt: 24:00", Order.COME_LIST));
        orders.add(new Order("From: Asanbai 21", "To: Osipenko 113", 12, "15/08/2019 15:00", "Limousine", "Time of receipt: 24:00", Order.COME_LIST));
        orders.add(new Order("From: Asanbai 21", "To: Osipenko 113", 12, "15/08/2019 15:00", "Limousine", "Time of receipt: 24:00", Order.COME_LIST));
        orders.add(new Order("From: Asanbai 21", "To: Osipenko 113", 12, "15/08/2019 15:00", "Limousine", "Time of receipt: 24:00", Order.COME_LIST));
        orders.add(new Order("From: Asanbai 21", "To: Osipenko 113", 12, "15/08/2019 15:00", "Limousine", "Time of receipt: 24:00", Order.COME_LIST));
        orders.add(new Order("From: Asanbai 21", "To: Osipenko 113", 12, "15/08/2019 15:00", "Limousine", "Time of receipt: 24:00", Order.HISTORY_LIST));
        orders.add(new Order("From: Asanbai 21", "To: Osipenko 113", 12, "15/08/2019 15:00", "Limousine", "Time of receipt: 24:00", Order.HISTORY_LIST));
        orders.add(new Order("From: Asanbai 21", "To: Osipenko 113", 12, "15/08/2019 15:00", "Limousine", "Time of receipt: 24:00", Order.HISTORY_LIST));
        orders.add(new Order("From: Asanbai 21", "To: Osipenko 113", 12, "15/08/2019 15:00", "Limousine", "Time of receipt: 24:00", Order.HISTORY_LIST));
        orders.add(new Order("From: Asanbai 21", "To: Osipenko 113", 12, "15/08/2019 15:00", "Limousine", "Time of receipt: 24:00", Order.HISTORY_LIST));
        orders.add(new Order("From: Asanbai 21", "To: Osipenko 113", 12, "15/08/2019 15:00", "Limousine", "Time of receipt: 24:00", Order.HISTORY_LIST));
        orders.add(new Order("From: Asanbai 21", "To: Osipenko 113", 12, "15/08/2019 15:00", "Limousine", "Time of receipt: 24:00", Order.PERFORMED_LIST));
        orders.add(new Order("From: Asanbai 21", "To: Osipenko 113", 12, "15/08/2019 15:00", "Limousine", "Time of receipt: 24:00", Order.PERFORMED_LIST));
        orders.add(new Order("From: Asanbai 21", "To: Osipenko 113", 12, "15/08/2019 15:00", "Limousine", "Time of receipt: 24:00", Order.PERFORMED_LIST));
        orders.add(new Order("From: Asanbai 21", "To: Osipenko 113", 12, "15/08/2019 15:00", "Limousine", "Time of receipt: 24:00", Order.PERFORMED_LIST));
        orders.add(new Order("From: Asanbai 21", "To: Osipenko 113", 12, "15/08/2019 15:00", "Limousine", "Time of receipt: 24:00", Order.PERFORMED_LIST));
        orders.add(new Order("From: Asanbai 21", "To: Osipenko 113", 12, "15/08/2019 15:00", "Limousine", "Time of receipt: 24:00", Order.PERFORMED_LIST));
        orders.add(new Order("From: Asanbai 21", "To: Osipenko 113", 12, "15/08/2019 15:00", "Limousine", "Time of receipt: 24:00", Order.PERFORMED_LIST));

        return orders;
    }

}
