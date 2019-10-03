package com.example.bookinglaneadmin.ui.drivers;

import android.app.Activity;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookinglaneadmin.R;
import com.example.bookinglaneadmin.adapter.DriverAdapter;
import com.example.bookinglaneadmin.utils.VerticalSpaceItemDecoration;

public class DriversFragment extends Fragment {

    private DriversViewModel driversViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        driversViewModel =
                ViewModelProviders.of(this).get(DriversViewModel.class);
        View root = inflater.inflate(R.layout.fragment_drivers, container, false);
        init(root);
        return root;
    }

    private void init(View view) {
        RecyclerView driverListRV = view.findViewById(R.id.drivers_recyclerview_drivers);

        driverListRV.setLayoutManager(new LinearLayoutManager(getContext()));
        driverListRV.setHasFixedSize(true);
        driverListRV.setAdapter(new DriverAdapter(getContext()));
        driverListRV.addItemDecoration(new VerticalSpaceItemDecoration(getContext(),8));

        view.findViewById(R.id.add_driver_btn_drivers).setOnClickListener(view1 -> {
            Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.nav_add_driver);
        });

    }
}