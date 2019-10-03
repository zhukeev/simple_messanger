package com.example.bookinglaneadmin.ui.driver_trace;

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

import com.example.bookinglaneadmin.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;

public class DriverTraceFragment extends Fragment implements OnMapReadyCallback {

    private DriverTraceViewModel driverTraceViewModel;
    private GoogleMap mMap;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        driverTraceViewModel =
                ViewModelProviders.of(this).get(DriverTraceViewModel.class);
        View root = inflater.inflate(R.layout.fragment_driver_trace, container, false);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        return root;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(getContext(), R.raw.dark_mode_google_map));

        // Add a marker in Sydney and move the camera
        LatLng sanjose = new LatLng(37.3300396, -121.8979276);

        mMap.addMarker(new MarkerOptions().position(sanjose).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sanjose, 19));
    }
}