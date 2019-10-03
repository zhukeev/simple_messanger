package com.example.bookinglaneadmin.ui.add_driver;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookinglaneadmin.R;
import com.example.bookinglaneadmin.adapter.CategoryAdapter;
import com.example.bookinglaneadmin.utils.PasswordGenerator;
import com.google.android.gms.maps.GoogleMap;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddDriverFragment extends Fragment  {

    private AddDriverViewModel addVehicleViewModel;
    private GoogleMap mMap;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        addVehicleViewModel =
                ViewModelProviders.of(this).get(AddDriverViewModel.class);
        View root = inflater.inflate(R.layout.fragment_add_driver, container, false);

        init(root);

        return root;
    }

    private void init(View root) {

        EditText date_et = root.findViewById(R.id.end_date_et_edit);




        final Calendar myCalendar = Calendar.getInstance();

        DatePickerDialog.OnDateSetListener date1 = (view, year, monthOfYear, dayOfMonth) -> {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            String myFormat = "MM/dd/yyyy";
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

            date_et.setText(sdf.format(myCalendar.getTime()));
        };

        date_et.setOnClickListener(v -> {

            new DatePickerDialog(getContext(), date1, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)).show();
        });

        TextView textViewGeneratePassword = root.findViewById(R.id.generate_new_password_tv_add_driver);
        TextView textViewPassword = root.findViewById(R.id.password_tv_add_driver);

        textViewGeneratePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PasswordGenerator passwordGenerator = new PasswordGenerator.PasswordGeneratorBuilder()
                        .useDigits(true)
                        .useLower(true)
                        .useUpper(true)
                        .build();
                String password = passwordGenerator.generate(8);

                textViewPassword.setText("");
                textViewPassword.setText("Password: "+password);
            }
        });


    }

}