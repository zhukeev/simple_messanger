package com.example.bookinglane.ui.car_list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookinglane.R;
import com.example.bookinglane.adapter.CarListAdapter;

public class ResultCarListFragment extends Fragment {

    private ResultCarListViewModel shareViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        shareViewModel =
                ViewModelProviders.of(this).get(ResultCarListViewModel.class);
        View root = inflater.inflate(R.layout.fragment_result_carlist, container, false);
       init(root);
        return root;
    }

    private void init(View root) {
        RecyclerView recyclerView = root.findViewById(R.id.car_list_recycler_view_result);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new CarListAdapter(getContext(), position -> {

            Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.nav_car_details);

        }));
    }
}