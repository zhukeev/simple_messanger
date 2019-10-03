package com.example.bookinglaneadmin.ui.fleet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookinglaneadmin.R;
import com.example.bookinglaneadmin.adapter.CarListAdapter;
import com.example.bookinglaneadmin.adapter.CategoryAdapter;
import com.example.bookinglaneadmin.utils.VerticalSpaceItemDecoration;

public class YourFleetFragment extends Fragment {

    private YourFleetViewModel yourFleetViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        yourFleetViewModel =
                ViewModelProviders.of(this).get(YourFleetViewModel.class);
        View root = inflater.inflate(R.layout.fragment_fleet, container, false);
        init(root);
        return root;
    }

    private void init(View view) {

        RecyclerView rv_car_type = view.findViewById(R.id.car_type_recycler_view_fleet);
        RecyclerView rv_list_car = view.findViewById(R.id.car_list_recycler_view_fleet);

        rv_car_type.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        rv_car_type.setHasFixedSize(true);
        rv_car_type.setAdapter(new CategoryAdapter(getContext(), position -> {

        }));

        rv_list_car.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        rv_list_car.setHasFixedSize(true);
        rv_list_car.addItemDecoration(new VerticalSpaceItemDecoration(getContext(),8));
        rv_list_car.setAdapter(new CarListAdapter(getContext(), position -> {
            NavController controller = Navigation.findNavController((getActivity()), R.id.nav_host_fragment);
            controller.popBackStack(R.id.nav_your_fleet,true);
            controller.navigate(R.id.nav_car_details);
        }));

        view.findViewById(R.id.add_vehicle_btn_fleet).setOnClickListener(view1 -> {
            Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.nav_add_vehicle);
        });

    }

}