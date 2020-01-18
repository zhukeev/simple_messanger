package com.example.oil.ui.manual.car_brand.create_car.pick_model.edit_model;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.oil.R;

public class EditModelActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_model);

        setupActionBar();
        init();
    }

    private void init() {


        int position = 0;
        EditText modelEt = findViewById(R.id.model_et_edit_model);
        if (getIntent().hasExtra("model")) {
            modelEt.setText(getIntent().getStringExtra("model"));
            position = getIntent().getIntExtra("position", 0);
        }

        int finalPosition = position;
        findViewById(R.id.save_btn_edit_model).setOnClickListener(view -> {

            if (modelEt.getText().toString().trim().isEmpty()) {
                Toast.makeText(this, "Пожалуйста введите название модели", Toast.LENGTH_SHORT).show();
                return;
            }
            Intent intent = new Intent();

            intent.putExtra("model", modelEt.getText().toString());
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
        textView.setText(R.string.model_editing);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        onBackPressed();

        return super.onOptionsItemSelected(item);
    }
}
