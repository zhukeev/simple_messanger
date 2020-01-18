package com.example.oil.ui.manual.oil.add_oil.pick_type.edit_oil_type;

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

public class EditOilTypeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_oil_type);

        setupActionBar();
        init();
    }

    private void init() {


        int position = 0;
        EditText oil_type_et = findViewById(R.id.oil_type_et_edit_oil_type);
        if (getIntent().hasExtra("oil_type")) {
            oil_type_et.setText(getIntent().getStringExtra("oil_type"));
            position = getIntent().getIntExtra("position", 0);
        }

        int finalPosition = position;
        findViewById(R.id.save_btn_edit_oil_type).setOnClickListener(view -> {

            if (oil_type_et.getText().toString().trim().isEmpty()) {
                Toast.makeText(this, "Пожалуйста введите название типа масла", Toast.LENGTH_SHORT).show();
                return;
            }
            Intent intent = new Intent();

            intent.putExtra("oil_type", oil_type_et.getText().toString());
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
        textView.setText(R.string.oil_type_editing);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        onBackPressed();

        return super.onOptionsItemSelected(item);
    }
}
