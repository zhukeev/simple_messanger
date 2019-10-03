package com.example.bookinglaneadmin.ui.add_vehicle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookinglaneadmin.R;
import com.example.bookinglaneadmin.adapter.CategoryAdapter;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;

public class AddVehicleFragment extends Fragment  {

    private AddVehicleViewModel addVehicleViewModel;
    private GoogleMap mMap;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        addVehicleViewModel =
                ViewModelProviders.of(this).get(AddVehicleViewModel.class);
        View root = inflater.inflate(R.layout.fragment_add_vehicle, container, false);

        init(root);

        return root;
    }

    private void init(View root) {
        RecyclerView rv_car_type = root.findViewById(R.id.car_type_RecyclerView_add_vehicle);

        rv_car_type.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        rv_car_type.setHasFixedSize(true);
        rv_car_type.setAdapter(new CategoryAdapter(getContext(), position -> {

        }));
    }

}