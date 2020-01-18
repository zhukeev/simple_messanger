package com.example.oil.ui.manual.kinda_service.pick_class.edit_oil_type;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.oil.R;
import com.example.oil.ui.manual.kinda_service.pick_class.model.CarService;

import org.parceler.Parcels;

public class EditKindaServiceActivity extends AppCompatActivity {

    CarService carService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_kinda_service);

        setupActionBar();
        init();
    }

    private void init() {


        int position = 0;
        EditText service_name_et = findViewById(R.id.kinda_service_et_edit_kinda_service);
        EditText service_price_et = findViewById(R.id.price_et_edit_kinda_service);
        if (getIntent().hasExtra("service")) {

            carService = (CarService) Parcels.unwrap(getIntent().getParcelableExtra("service"));

            service_name_et.setText(carService.getTitle());
            service_price_et.setText(carService.getPrice());
            position = getIntent().getIntExtra("position", 0);
        }

        int finalPosition = position;
        findViewById(R.id.save_btn_edit_kinda_service).setOnClickListener(view -> {

            if (service_name_et.getText().toString().trim().isEmpty() || service_price_et.getText().toString().trim().isEmpty()) {
                Toast.makeText(this, "Пожалуйста заполните все поля", Toast.LENGTH_SHORT).show();
                return;
            }
            Intent intent = new Intent();

            Parcelable serviceParcel = Parcels.wrap(carService);

            carService.setPrice(service_price_et.getText().toString());
            carService.setTitle(service_name_et.getText().toString());

            intent.putExtra("service", serviceParcel);
            intent.putExtra("position", finalPosition);
            setResult(Activity.RESULT_OK, intent);
            finish();

        });


    }


    private void setupActionBar() {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView textView = findViewById(R.id.actionbar_text);
        textView.setText(R.string.kinda_service_editing);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        onBackPressed();

        return super.onOptionsItemSelected(item);
    }
}
