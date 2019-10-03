package com.example.bookinglaneadmin.ui.company_profile;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.bookinglaneadmin.R;
import com.willy.ratingbar.BaseRatingBar;

public class CompanyProfileFragment extends Fragment {

    private CompanyProfileViewModel addVehicleViewModel;
    private EditText address_et, phone_number_et, email_et, bank_account_et, company_name_et;
    private TextView address_tv, phone_number_tv, email_tv, bank_account_tv, company_name_tv, fleet_tv;
//    private ImageView exit_btn;
    private Button edit_save_button;
    private BaseRatingBar ratingBar;
    private final int VIEW_MODE = 0;
    private final int EDIT_MODE = 1;
    private int MODE = 0;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        addVehicleViewModel =
                ViewModelProviders.of(this).get(CompanyProfileViewModel.class);
        View root = inflater.inflate(R.layout.fragment_company_profile, container, false);

        init(root);

        return root;
    }

    private void init(View root) {

        address_et = root.findViewById(R.id.address_et_company_profile);
        phone_number_et = root.findViewById(R.id.phone_number_et_company_profile);
        email_et = root.findViewById(R.id.email_et_company_profile);
        bank_account_et = root.findViewById(R.id.bank_account_et_company_profile);
        company_name_et = root.findViewById(R.id.company_name_et_company_profile);

        //exit_btn = getActivity().findViewById(R.id.logout_actionbar);

//        exit_btn.setVisibility(View.VISIBLE);


        fleet_tv = root.findViewById(R.id.fleet_number_tv_company_profile);
        address_tv = root.findViewById(R.id.address_tv_company_profile);
        phone_number_tv = root.findViewById(R.id.phone_tv_company_profile);
        email_tv = root.findViewById(R.id.email_tv_company_profile);
        bank_account_tv = root.findViewById(R.id.bank_account_tv_company_profile);
        company_name_tv = root.findViewById(R.id.company_name_tv_company_profile);

        edit_save_button = root.findViewById(R.id.edit_btn_company_profile);

        ratingBar = root.findViewById(R.id.rating_bar_company_profile);

        address_et.setVisibility(View.GONE);
        phone_number_et.setVisibility(View.GONE);
        email_et.setVisibility(View.GONE);
        bank_account_et.setVisibility(View.GONE);
        company_name_et.setVisibility(View.INVISIBLE);


        edit_save_button.setOnClickListener(view -> {

            if (MODE == VIEW_MODE) {

                ratingBar.setVisibility(View.VISIBLE);

                company_name_tv.setVisibility(View.VISIBLE);
                address_tv.setVisibility(View.VISIBLE);
                phone_number_tv.setVisibility(View.VISIBLE);
                email_tv.setVisibility(View.VISIBLE);
                bank_account_tv.setVisibility(View.VISIBLE);
                fleet_tv.setVisibility(View.VISIBLE);
                edit_save_button.setText(R.string.edit_company_profile);

                if (MODE == VIEW_MODE) {
                    bank_account_tv.setText(bank_account_et.getText().toString());
                    email_tv.setText(email_et.getText().toString());
                    phone_number_tv.setText(phone_number_et.getText().toString());
                    company_name_tv.setText(company_name_et.getText());
                    address_tv.setText(address_et.getText().toString());
                }

                address_et.setVisibility(View.GONE);
                phone_number_et.setVisibility(View.GONE);
                email_et.setVisibility(View.GONE);
                bank_account_et.setVisibility(View.GONE);
                company_name_et.setVisibility(View.INVISIBLE);

//                exit_btn.setVisibility(View.VISIBLE);

                MODE = EDIT_MODE;


            } else if (MODE == EDIT_MODE) {

                company_name_et.setVisibility(View.VISIBLE);
                address_et.setVisibility(View.VISIBLE);
                phone_number_et.setVisibility(View.VISIBLE);
                email_et.setVisibility(View.VISIBLE);
                bank_account_et.setVisibility(View.VISIBLE);

                bank_account_et.setText(bank_account_tv.getText().toString());
                email_et.setText(email_tv.getText().toString());
                phone_number_et.setText(phone_number_tv.getText().toString());
                company_name_et.setText(company_name_tv.getText());
                address_et.setText(address_tv.getText().toString());

                ratingBar.setVisibility(View.INVISIBLE);
                fleet_tv.setVisibility(View.INVISIBLE);

                address_tv.setVisibility(View.GONE);
                phone_number_tv.setVisibility(View.GONE);
                email_tv.setVisibility(View.GONE);
                bank_account_tv.setVisibility(View.GONE);
                company_name_tv.setVisibility(View.INVISIBLE);

//                exit_btn.setVisibility(View.INVISIBLE);
                edit_save_button.setText(R.string.save);

                MODE = VIEW_MODE;
            }
        });

    }


    @Override
    public void onStart() {
        Log.e("TAG", "onStart: " );
        getActivity().findViewById(R.id.toolbar1).setVisibility(View.GONE);
        getActivity().findViewById(R.id.logout_actionbar).setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.chevron_back_actionbar).setVisibility(View.VISIBLE);
        super.onStart();
    }

    @Override
    public void onStop() {
        getActivity().findViewById(R.id.toolbar1).setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.logout_actionbar).setVisibility(View.GONE);
        Log.e("TAG", "onStop: " );
        super.onStop();
    }
}