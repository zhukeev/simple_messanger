package com.example.oil.ui.manual.car_brand.create_car.pick_car_body.create_model;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import com.example.oil.adapter.CarDetailsAdapter;
import com.example.oil.ui.manual.engines.EditEngineActivity;
import com.example.oil.utils.VerticalSpaceItemDecoration;

import java.util.ArrayList;

public class CreateCarBodyActivity extends AppCompatActivity {


    final int EDIT_REQUEST_CODE = 100;

    private RecyclerView car_bodies_rv;
    private CarDetailsAdapter adapter;
    private ArrayList<String> models = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_car_body);

        setupActionBar();

        init();
    }

    private void init() {


        EditText car_body_et = findViewById(R.id.car_bodies_et_create_car_body);
        car_bodies_rv = findViewById(R.id.car_body_rv_create_car_body);
        car_bodies_rv.setLayoutManager(new LinearLayoutManager(this));
        car_bodies_rv.setHasFixedSize(true);
        car_bodies_rv.addItemDecoration(new VerticalSpaceItemDecoration(this, 8));
        adapter = new CarDetailsAdapter(getApplicationContext(), (position, model) -> {
            Intent intent = new Intent(CreateCarBodyActivity.this, EditEngineActivity.class);

            intent.putExtra("car_body", model);
            intent.putExtra("position", position);

            startActivityForResult(intent, EDIT_REQUEST_CODE);
        });


        car_bodies_rv.setAdapter(adapter);


        findViewById(R.id.add_btn_create_car_body).setOnClickListener(view -> {

            if (car_body_et.getText().toString().trim().isEmpty()) {
                Toast.makeText(this, "Пожалуйста введите название кузова", Toast.LENGTH_SHORT).show();
                return;
            }


            adapter.putProperty(car_body_et.getText().toString());
            car_bodies_rv.scrollToPosition(adapter.getItemCount() - 1);
            closeKeyboard();
            car_body_et.setText("");

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
        textView.setText(R.string.car_body_adding);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


        onBackPressed();

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent();

        intent.putStringArrayListExtra("car_body", new ArrayList<>(adapter.getPropertiesFiltered()));
        setResult(Activity.RESULT_OK, intent);
        super.onBackPressed();
    }
}
