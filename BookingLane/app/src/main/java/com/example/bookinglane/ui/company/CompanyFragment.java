package com.example.bookinglane.ui.company;

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
import com.example.bookinglane.adapter.CategoryAdapter;

public class CompanyFragment extends Fragment {

    private CompanyViewModel galleryViewModel;

    private RecyclerView rv_car_type,rv_list_car;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(CompanyViewModel.class);
        View root = inflater.inflate(R.layout.fragment_company, container, false);
        init(root);
        return root;
    }

    private void init(View view) {

        rv_car_type = view.findViewById(R.id.car_type_recycler_view_company);
        rv_list_car = view.findViewById(R.id.car_list_recycler_view_company);

        rv_car_type.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));
        rv_car_type.setHasFixedSize(true);
        rv_car_type.setAdapter(new CategoryAdapter(getContext(), position -> {

        }));

        rv_list_car.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
        rv_list_car.setHasFixedSize(true);
        rv_list_car.setAdapter(new CarListAdapter(getContext(), position -> {
            Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.nav_car_details);
        }));
    }
}