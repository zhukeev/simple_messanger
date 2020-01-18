package com.example.oil.ui.manual.engines;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.MenuItem;
import android.view.MotionEvent;
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
import com.example.oil.adapter.EngineAdapter;
import com.example.oil.adapter.HintSpinnerAdapter;
import com.example.oil.custom_view.HintSpinner;
import com.example.oil.model.Engine;
import com.example.oil.utils.VerticalSpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class CreateEngineActivity extends AppCompatActivity {

    final int EDIT_REQUEST_CODE = 100;

    private RecyclerView oil_rv;
    private EngineAdapter adapter;
    private List<Engine> engineList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_engine);

        init();
        setupActionBar();
    }

    private void init() {


        EditText engineTypeEt = findViewById(R.id.type_et_create_engine);
        HintSpinner fuelSpinner = findViewById(R.id.fuel_spinner_edit_create_engine);
        oil_rv = findViewById(R.id.engines_rv_create_engine);
        oil_rv.setLayoutManager(new LinearLayoutManager(this));
        oil_rv.setHasFixedSize(true);
        oil_rv.addItemDecoration(new VerticalSpaceItemDecoration(this, 8));
        adapter = new EngineAdapter(getApplicationContext(), engineList, (position, engine) -> {

            Intent intent = new Intent(CreateEngineActivity.this, EditEngineActivity.class);

            intent.putExtra("engine", engine);
            intent.putExtra("position", position);

            startActivityForResult(intent, EDIT_REQUEST_CODE);


        });
        oil_rv.setAdapter(adapter);


        String[] producers = new String[]{"Бензин", "Газ", "Дизель"};

        fuelSpinner.setAdapter(new HintSpinnerAdapter<>(this, producers, R.string.fuel));

        findViewById(R.id.add_btn_edit_create_engine).setOnClickListener(view -> {


            if (fuelSpinner.getSelectedItemPosition()==0){
                Toast.makeText(this, "Пожалуйста выберите тип топлива", Toast.LENGTH_SHORT).show();
                return;
            }if (engineTypeEt.getText().toString().trim().isEmpty()){
                Toast.makeText(this, "Пожалуйста введите тип двигателя", Toast.LENGTH_SHORT).show();
                return;
            }

            Engine engine = new Engine(engineTypeEt.getText().toString(), producers[fuelSpinner.getSelectedItemPosition() - 1]);

            engineTypeEt.setText("");

            engineList.add(engine);
            adapter.notifyDataSetChanged();

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
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent();

        intent.putParcelableArrayListExtra("engines", (ArrayList<? extends Parcelable>) engineList);

        setResult(Activity.RESULT_OK, intent);

        super.onBackPressed();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int) event.getRawX(), (int) event.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }

            }
        }

        return super.dispatchTouchEvent(event);
    }
}
