package com.example.oil.ui.manual.oil.add_oil.pick_class.edit_oil_type;

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

public class EditOilClassActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_oil_class);

        setupActionBar();
        init();
    }

    private void init() {


        int position = 0;
        EditText oil_class_et = findViewById(R.id.oil_class_et_edit_oil_class);
        if (getIntent().hasExtra("oil_class")) {
            oil_class_et.setText(getIntent().getStringExtra("oil_class"));
            position = getIntent().getIntExtra("position", 0);
        }

        int finalPosition = position;
        findViewById(R.id.save_btn_edit_oil_class).setOnClickListener(view -> {

            if (oil_class_et.getText().toString().trim().isEmpty()) {
                Toast.makeText(this, "Пожалуйста введите название класс масла", Toast.LENGTH_SHORT).show();
                return;
            }
            Intent intent = new Intent();

            intent.putExtra("oil_class", oil_class_et.getText().toString());
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
        textView.setText(R.string.oil_class_editing);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        onBackPressed();

        return super.onOptionsItemSelected(item);
    }
}
