package com.example.oil.ui.manual.oil.add_oil.pick_viscosity.create_oil_type;

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

public class CreateOilViscosityActivity extends AppCompatActivity {


    final int EDIT_REQUEST_CODE = 100;

    private RecyclerView oil_viscosity_rv;
    private CarDetailsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_oil_viscosity);

        setupActionBar();

        init();
    }

    private void init() {


        EditText oil_class_et = findViewById(R.id.oil_viscosity_et_create_oil_viscosity);
        oil_viscosity_rv = findViewById(R.id.oil_viscosity_rv_create_oil_viscosity);
        oil_viscosity_rv.setLayoutManager(new LinearLayoutManager(this));
        oil_viscosity_rv.setHasFixedSize(true);
        oil_viscosity_rv.addItemDecoration(new VerticalSpaceItemDecoration(this, 8));
        adapter = new CarDetailsAdapter(getApplicationContext(), (position, model) -> {
            Intent intent = new Intent(CreateOilViscosityActivity.this, EditEngineActivity.class);

            intent.putExtra("oil_viscosity", model);
            intent.putExtra("position", position);

            startActivityForResult(intent, EDIT_REQUEST_CODE);
        });


        oil_viscosity_rv.setAdapter(adapter);


        findViewById(R.id.add_btn_create_oil_viscosity).setOnClickListener(view -> {

            if (oil_class_et.getText().toString().trim().isEmpty()) {
                Toast.makeText(this, "Пожалуйста введите название класса вязкости", Toast.LENGTH_SHORT).show();
                return;
            }

            adapter.putProperty(oil_class_et.getText().toString());
            oil_viscosity_rv.scrollToPosition(adapter.getItemCount() - 1);
            closeKeyboard();
            oil_class_et.setText("");

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
        textView.setText(R.string.oil_viscosity_adding);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        onBackPressed();

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent();

        intent.putStringArrayListExtra("oil_viscosity", new ArrayList<>(adapter.getPropertiesFiltered()));
        setResult(Activity.RESULT_OK, intent);
        super.onBackPressed();
    }
}
