package com.example.oil.ui.manual.car_brand.create_car.pick_model.create_model;

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

public class CreateModelActivity extends AppCompatActivity {


    final int EDIT_REQUEST_CODE = 100;

    private RecyclerView model_rv;
    private CarDetailsAdapter adapter;
    private ArrayList<String> models = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_model);

        setupActionBar();

        init();
    }

    private void init() {


        EditText modelEt = findViewById(R.id.model_et_create_model);
        model_rv = findViewById(R.id.models_rv_create_model);
        model_rv.setLayoutManager(new LinearLayoutManager(this));
        model_rv.setHasFixedSize(true);
        model_rv.addItemDecoration(new VerticalSpaceItemDecoration(this, 8));
        adapter = new CarDetailsAdapter(getApplicationContext(), (position, model) -> {
            Intent intent = new Intent(CreateModelActivity.this, EditEngineActivity.class);

            intent.putExtra("model", model);
            intent.putExtra("position", position);

            startActivityForResult(intent, EDIT_REQUEST_CODE);
        });


        model_rv.setAdapter(adapter);


        findViewById(R.id.add_btn_create_model).setOnClickListener(view -> {

            if (modelEt.getText().toString().trim().isEmpty()) {
                Toast.makeText(this, "Пожалуйста введите название модели", Toast.LENGTH_SHORT).show();
                return;
            }


            adapter.putProperty(modelEt.getText().toString());
            model_rv.scrollToPosition(adapter.getItemCount() - 1);
            closeKeyboard();
            modelEt.setText("");

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
        textView.setText(R.string.model_adding);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


        onBackPressed();

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent();

        intent.putStringArrayListExtra("model", new ArrayList<>(adapter.getPropertiesFiltered()));
        setResult(Activity.RESULT_OK, intent);
        super.onBackPressed();
    }
}
