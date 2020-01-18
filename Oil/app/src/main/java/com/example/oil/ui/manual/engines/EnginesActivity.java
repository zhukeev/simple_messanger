package com.example.oil.ui.manual.engines;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oil.R;
import com.example.oil.adapter.EngineAdapter;
import com.example.oil.model.Engine;
import com.example.oil.utils.VerticalSpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class EnginesActivity extends AppCompatActivity {

    final int CREATE_REQUEST_CODE = 200;
    final int EDIT_REQUEST_CODE = 100;
    private List<Engine> engines;
    private EngineAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_engines);

        setupActionBar();

        init();

    }

    private void init() {

        EditText searchEditText = findViewById(R.id.search_et_engines);

        RecyclerView oil_rv = findViewById(R.id.engines_rv_engines);
        oil_rv.setLayoutManager(new LinearLayoutManager(this));
        oil_rv.setHasFixedSize(true);
        oil_rv.addItemDecoration(new VerticalSpaceItemDecoration(this, 8));
        adapter = new EngineAdapter(getApplicationContext(), getEngines(), (position, engine) -> {

            Intent intent = new Intent(EnginesActivity.this, EditEngineActivity.class);

            intent.putExtra("engine", engine);
            intent.putExtra("position", position);

            EnginesActivity.this.startActivityForResult(intent, EDIT_REQUEST_CODE);


        });
        oil_rv.setAdapter(adapter);

        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                adapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private List<Engine> getEngines() {

        engines = new ArrayList<>();
        engines.add(new Engine("FXJA ", "Бензин"));
        engines.add(new Engine("FXJA ", "Бензин"));
        engines.add(new Engine("FXJA ", "Бензин"));
        return engines;

    }

    private void setupActionBar() {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView textView = findViewById(R.id.actionbar_text);
        textView.setText(R.string.manual);
    }


    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                Toast.makeText(this, "home", Toast.LENGTH_SHORT).show();
                break;
            case R.id.create_menu_item:
                startActivityForResult(new Intent(this, CreateEngineActivity.class), CREATE_REQUEST_CODE);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == EDIT_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {

                Engine engine = data.getParcelableExtra("engine");
                int position = data.getIntExtra("position", 0);

                engines.set(position, engine);
                adapter.notifyDataSetChanged();


            }
        } else if (CREATE_REQUEST_CODE == requestCode && resultCode == Activity.RESULT_OK) {


            Bundle bundle = data.getExtras();

            ArrayList<Engine> engine = bundle.getParcelableArrayList("engines");

            ArrayList e = new ArrayList();

            e.addAll(engine);
            e.addAll(engines);

            adapter.setEnginesFiltered(e);

            adapter.notifyDataSetChanged();

        }

        super.onActivityResult(requestCode, resultCode, data);
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
