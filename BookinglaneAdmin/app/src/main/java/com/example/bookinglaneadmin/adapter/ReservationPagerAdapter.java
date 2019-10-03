package com.example.bookinglaneadmin.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.bookinglaneadmin.ui.reservations_pro_subs.CanceledFragment;
import com.example.bookinglaneadmin.ui.reservations_pro_subs.HistoryFragment;
import com.example.bookinglaneadmin.ui.reservations_pro_subs.allFragment;
import com.example.bookinglaneadmin.ui.reservations_pro_subs.comeFragment;
import com.example.bookinglaneadmin.ui.reservations_pro_subs.performFragment;

import java.util.ArrayList;

public class ReservationPagerAdapter extends FragmentPagerAdapter {


    private int tabCount = 0;

    ArrayList<Fragment> fragments = new ArrayList<>();

    public ReservationPagerAdapter(@NonNull FragmentManager fm, int tabCount) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.tabCount = tabCount;

        fragments.add(new allFragment());
        fragments.add(new comeFragment());
        fragments.add(new performFragment());
        fragments.add(new CanceledFragment());
        fragments.add(new HistoryFragment());
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {

        Fragment fragment;

        switch (position) {
            case 0:
                fragment = new allFragment();
                break;
            case 1:
                fragment = new comeFragment();
                break;
            case 2:
                fragment = new performFragment();
                break;
            case 3:
                fragment = new CanceledFragment();
                break;
            case 4:
                fragment = new HistoryFragment();
                break;
            default:
                fragment = new allFragment();

        }


        return fragment;
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
