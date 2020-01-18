package com.example.oil.ui.manual.oil.add_oil.pick_type.create_oil_type;

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

public class CreateOilTypeActivity extends AppCompatActivity {


    final int EDIT_REQUEST_CODE = 100;

    private RecyclerView oil_type_rv;
    private CarDetailsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_oil_type);

        setupActionBar();

        init();
    }

    private void init() {


        EditText oil_type_et = findViewById(R.id.oil_type_et_create_oil_type);
        oil_type_rv = findViewById(R.id.oil_types_rv_create_oil_type);
        oil_type_rv.setLayoutManager(new LinearLayoutManager(this));
        oil_type_rv.setHasFixedSize(true);
        oil_type_rv.addItemDecoration(new VerticalSpaceItemDecoration(this, 8));
        adapter = new CarDetailsAdapter(getApplicationContext(), (position, model) -> {
            Intent intent = new Intent(CreateOilTypeActivity.this, EditEngineActivity.class);

            intent.putExtra("oil_type", model);
            intent.putExtra("position", position);

            startActivityForResult(intent, EDIT_REQUEST_CODE);
        });


        oil_type_rv.setAdapter(adapter);


        findViewById(R.id.add_btn_create_oil_type).setOnClickListener(view -> {

            if (oil_type_et.getText().toString().trim().isEmpty()) {
                Toast.makeText(this, "Пожалуйста введите название типа модели", Toast.LENGTH_SHORT).show();
                return;
            }


            adapter.putProperty(oil_type_et.getText().toString());
            oil_type_rv.scrollToPosition(adapter.getItemCount() - 1);
            closeKeyboard();
            oil_type_et.setText("");

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
        textView.setText(R.string.oil_type_adding);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


        onBackPressed();

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent();

        intent.putStringArrayListExtra("oil_type", new ArrayList<>(adapter.getPropertiesFiltered()));
        setResult(Activity.RESULT_OK, intent);
        super.onBackPressed();
    }
}
