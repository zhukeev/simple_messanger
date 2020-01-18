package com.example.oil.ui.manual.kinda_service.pick_class.create_oil_type;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oil.R;
import com.example.oil.adapter.KindOfServiceAdapter;
import com.example.oil.ui.manual.engines.EditEngineActivity;
import com.example.oil.ui.manual.kinda_service.pick_class.model.CarService;
import com.example.oil.utils.VerticalSpaceItemDecoration;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

public class CreateKindaServiceActivity extends AppCompatActivity {


    final int EDIT_REQUEST_CODE = 100;

    private RecyclerView kinda_service_rv;
    private KindOfServiceAdapter adapter;
    private List<CarService> carServices = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_kinda_service);

        setupActionBar();

        init();
    }

    private void init() {


        EditText service_name_et = findViewById(R.id.kinda_service_et_create_kinda_service);
        EditText service_price_et = findViewById(R.id.price_et_create_kinda_service);
        kinda_service_rv = findViewById(R.id.kinda_service_rv_create_kinda_service);
        kinda_service_rv.setLayoutManager(new LinearLayoutManager(this));
        kinda_service_rv.setHasFixedSize(true);
        kinda_service_rv.addItemDecoration(new VerticalSpaceItemDecoration(this, 8));

        adapter = new KindOfServiceAdapter(this, carServices, (position, carService) -> {

            Intent intent = new Intent(CreateKindaServiceActivity.this, EditEngineActivity.class);

            Parcelable wrapped = Parcels.wrap(carService);

            intent.putExtra("service", wrapped);
            intent.putExtra("position", position);

            startActivityForResult(intent, EDIT_REQUEST_CODE);
        });


        kinda_service_rv.setAdapter(adapter);


        findViewById(R.id.add_btn_create_kinda_service).setOnClickListener(view -> {

            if (service_name_et.getText().toString().trim().isEmpty() || service_price_et.getText().toString().trim().isEmpty() ) {
                Toast.makeText(this, "Пожалуйста заполните все поля", Toast.LENGTH_SHORT).show();
                return;
            }

            adapter.addService(new CarService(service_name_et.getText().toString(),service_price_et.getText().toString()) );
            kinda_service_rv.scrollToPosition(adapter.getItemCount() - 1);
            closeKeyboard();
            service_name_et.setText("");
            service_price_et.setText("");

        });


    }

    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private void setupActionBar() {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView textView = findViewById(R.id.actionbar_text);
        textView.setText(R.string.kinda_operation_adding);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


        onBackPressed();

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent();

        intent.putExtra("service", new ArrayList<>(adapter.getFilteredCars()));
        setResult(Activity.RESULT_OK, intent);
        super.onBackPressed();
    }
}
