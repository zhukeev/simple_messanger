package com.example.bookinglaneadmin.ui.reservations_pro_subs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.example.bookinglaneadmin.R;
import com.example.bookinglaneadmin.adapter.ReservationPagerAdapter;
import com.example.bookinglaneadmin.customView.ViewPagerCustomDuration;
import com.google.android.material.tabs.TabLayout;

public class ReservationsProSubsFragment extends Fragment {

    private ReservationsProSubsViewModel reservationsProSubsViewModel;
    private TabLayout tabLayout;
    private ViewPagerCustomDuration viewPager;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        reservationsProSubsViewModel =
                ViewModelProviders.of(this).get(ReservationsProSubsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_reservation_pro_subs, container, false);

        init(root);

        return root;
    }

    private void init(View view) {

        tabLayout = view.findViewById(R.id.tabLayoutReservation);
        viewPager = view.findViewById(R.id.viewpager_reservation);

        tabLayout.addTab(tabLayout.newTab().setText("All").setIcon(R.drawable.ic_car_all), true);
        tabLayout.addTab(tabLayout.newTab().setText("Came").setIcon(R.drawable.ic_lightbulb));
        tabLayout.addTab(tabLayout.newTab().setText("Perform").setIcon(R.drawable.ic_lightbulb_check_mark));
        tabLayout.addTab(tabLayout.newTab().setText("Canceled").setIcon(R.drawable.ic_lightbulb_x));
        tabLayout.addTab(tabLayout.newTab().setText("History").setIcon(R.drawable.ic_history));

        tabLayout.addOnTabSelectedListener(viewPager.tabSelectedListener);


        viewPager.setAdapter(new ReservationPagerAdapter(getChildFragmentManager(), 5));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        viewPager.setScrollDurationFactor(2);

        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));



    }
}