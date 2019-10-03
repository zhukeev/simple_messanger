package com.example.bookinglane.ui.invoice;

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
import com.example.bookinglane.adapter.InvoiceAdapter;

public class InvoiceFragment extends Fragment {

    private InvoiceViewModel shareViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        shareViewModel =
                ViewModelProviders.of(this).get(InvoiceViewModel.class);
        View root = inflater.inflate(R.layout.fragment_invoices, container, false);
       init(root);
        return root;
    }

    private void init(View root) {
        RecyclerView recyclerView = root.findViewById(R.id.invoices_recycler_view_invoices);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new InvoiceAdapter(getContext(), position -> {
//            Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.nav_car_details);

        }));
    }
}