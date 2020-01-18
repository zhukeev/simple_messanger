package com.example.oil.ui.manual.car_brand.create_car.pick_engine_mark.create_generation;

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

public class CreateEngineMarkActivity extends AppCompatActivity {


    final int EDIT_REQUEST_CODE = 100;

    private RecyclerView engine_marks_rv;
    private CarDetailsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_engine_mark);

        setupActionBar();

        init();
    }

    private void init() {


        EditText engine_mark_et = findViewById(R.id.engine_mark_et_create_engine_mark);
        engine_marks_rv = findViewById(R.id.engine_marks_rv_create_engine_mark);
        engine_marks_rv.setLayoutManager(new LinearLayoutManager(this));
        engine_marks_rv.setHasFixedSize(true);
        engine_marks_rv.addItemDecoration(new VerticalSpaceItemDecoration(this, 8));
        adapter = new CarDetailsAdapter(getApplicationContext(), (position, model) -> {
            Intent intent = new Intent(CreateEngineMarkActivity.this, EditEngineActivity.class);

            intent.putExtra("engine_mark", model);
            intent.putExtra("position", position);

            startActivityForResult(intent, EDIT_REQUEST_CODE);
        });


        engine_marks_rv.setAdapter(adapter);


        findViewById(R.id.add_btn_create_engine_mark).setOnClickListener(view -> {

            if (engine_mark_et.getText().toString().trim().isEmpty()) {
                Toast.makeText(this, "Пожалуйста введите название марки двигателя", Toast.LENGTH_SHORT).show();
                return;
            }


            adapter.putProperty(engine_mark_et.getText().toString());
            engine_marks_rv.scrollToPosition(adapter.getItemCount() - 1);
            closeKeyboard();
            engine_mark_et.setText("");

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
        textView.setText(R.string.engine_mark_adding);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


        onBackPressed();

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent();

        intent.putStringArrayListExtra("engine_mark", new ArrayList<>(adapter.getPropertiesFiltered()));
        setResult(Activity.RESULT_OK, intent);
        super.onBackPressed();
    }
}
