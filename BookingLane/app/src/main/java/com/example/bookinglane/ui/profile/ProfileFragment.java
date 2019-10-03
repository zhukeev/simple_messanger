package com.example.bookinglane.ui.profile;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookinglane.R;
import com.example.bookinglane.adapter.CarListAdapter;
import com.example.bookinglane.adapter.CategoryAdapter;

public class ProfileFragment extends Fragment {

    private ProfileViewModel galleryViewModel;

    private RecyclerView rv_car_type, rv_list_car;
    private TextView name_tv, date_tv, phone_tv;
    private Button buttonEdit;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(ProfileViewModel.class);
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        init(root);

        getArgs();

        return root;
    }

    private void getArgs() {
        if (getArguments()!=null && !getArguments().isEmpty()){

            String number = getArguments().getString("number");
            String fullname = getArguments().getString("fullname");

            phone_tv.setText(number);
            name_tv.setText(fullname);
        }
    }

    private void init(View view) {

        name_tv = view.findViewById(R.id.fullname_tv_profile);
        date_tv = view.findViewById(R.id.date_tv_profile);
        phone_tv = view.findViewById(R.id.phone_tv_profile);

        buttonEdit = view.findViewById(R.id.edit_btn_profile);

        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Bundle bundle=new Bundle();

                bundle.putString("number", phone_tv.getText().toString().substring(7));
                bundle.putString("fullname", name_tv.getText().toString());

                Navigation.findNavController(getActivity(), R.id.nav_host_fragment)
                        .navigate(R.id.nav_profile_edit,bundle);

            }
        });


    }
}