package com.example.bookinglane.ui.car_details;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookinglane.R;
import com.example.bookinglane.adapter.CarListAdapter;
import com.example.bookinglane.adapter.PriceAdapter;
import com.example.bookinglane.adapter.SliderAdapter;
import com.smarteist.autoimageslider.SliderView;

public class CarDetailsFragment extends Fragment {

    private CarDetailsViewModel slideshowViewModel;
    private RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of(this).get(CarDetailsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_car_details, container, false);

        init(root);

        return root;
    }

    private void init(View view) {

        SliderView sliderView = view.findViewById(R.id.imageSlider);
        sliderView.setSliderAdapter(new SliderAdapter(getContext()));

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);

        recyclerView = view.findViewById(R.id.prices_rv_car_details);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new PriceAdapter(getContext()));
        new LinearSnapHelper().attachToRecyclerView(recyclerView);

        view.findViewById(R.id.choose_btn_car_details).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.nav_book_car);

            }
        });

    }
}