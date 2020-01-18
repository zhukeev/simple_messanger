package com.example.oil.ui.manual.car_brand.create_car.pick_gen.edit_generation;

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

public class EditGenerationActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_generation);

        setupActionBar();
        init();
    }

    private void init() {


        int position = 0;
        EditText generation_et = findViewById(R.id.generation_et_edit_generation);
        if (getIntent().hasExtra("generation")) {
            generation_et.setText(getIntent().getStringExtra("generation"));
            position = getIntent().getIntExtra("position", 0);
        }

        int finalPosition = position;
        findViewById(R.id.save_btn_edit_generation).setOnClickListener(view -> {

            if (generation_et.getText().toString().trim().isEmpty()) {
                Toast.makeText(this, "Пожалуйста введите название поколения", Toast.LENGTH_SHORT).show();
                return;
            }
            Intent intent = new Intent();

            intent.putExtra("generation", generation_et.getText().toString());
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
        textView.setText(R.string.generation_editing);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        onBackPressed();

        return super.onOptionsItemSelected(item);
    }
}
