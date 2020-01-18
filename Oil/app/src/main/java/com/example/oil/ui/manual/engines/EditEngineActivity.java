package com.example.oil.ui.manual.engines;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.oil.R;
import com.example.oil.adapter.HintSpinnerAdapter;
import com.example.oil.custom_view.HintSpinner;
import com.example.oil.model.Engine;

import java.util.ArrayList;
import java.util.List;

public class EditEngineActivity extends AppCompatActivity {

    EditText engineTypeEt;
    HintSpinner fuelSpinner;
    List<Engine> engineList = new ArrayList<>();
    private int positionInList = 0;
    private HintSpinnerAdapter spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_engine);

        init();
        setupActionBar();
        getExtras();

    }

    private void getExtras() {
        if (getIntent().hasExtra("engine")) {
            Engine engine = getIntent().getParcelableExtra("engine");
            engineTypeEt.setText(engine.getType());
            fuelSpinner.setSelection(spinner.getPosition(engine.getFuel())+1);

        }
        if (getIntent().hasExtra("position")) {
            positionInList = getIntent().getIntExtra("position", 0);
        }
    }

    private void init() {

        engineTypeEt = findViewById(R.id.type_et_engines);
        fuelSpinner = findViewById(R.id.fuel_spinner_edit_engine);


        String[] producers = new String[]{"Бензин", "Газ", "Дизель"};

        spinner = new HintSpinnerAdapter(this, producers, R.string.fuel);

        fuelSpinner.setAdapter(spinner);

        findViewById(R.id.save_btn_edit_engine).setOnClickListener(view -> {

            Intent intent = new Intent();

            Engine engine = new Engine(engineTypeEt.getText().toString(), producers[fuelSpinner.getSelectedItemPosition() - 1]);
            intent.putExtra("position", positionInList);
            intent.putExtra("engine", engine);
            setResult(Activity.RESULT_OK, intent);
            finish();

        });

    }

    private void setupActionBar() {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView textView = findViewById(R.id.actionbar_text);
        textView.setText(R.string.engines);
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}
