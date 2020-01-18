package com.example.oil.ui.manual.car_brand.create_car.pick_gen;

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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oil.R;
import com.example.oil.adapter.CarDetailsAdapter;
import com.example.oil.ui.manual.car_brand.create_car.pick_gen.create_generation.CreateGenerationActivity;
import com.example.oil.ui.manual.car_brand.create_car.pick_gen.edit_generation.EditGenerationActivity;
import com.example.oil.ui.manual.car_brand.create_car.pick_gen.model.CarGeneration;
import com.example.oil.ui.manual.car_brand.create_car.pick_gen.presenter.IPickGenerationPresenter;
import com.example.oil.ui.manual.car_brand.create_car.pick_gen.presenter.PickGenerationPresenter;
import com.example.oil.ui.manual.car_brand.create_car.pick_gen.view.IPickGenerationView;
import com.example.oil.utils.VerticalSpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class PickGenerationActivity extends AppCompatActivity implements IPickGenerationView {

    final int CREATE_REQUEST_CODE = 200;
    final int EDIT_REQUEST_CODE = 100;
    private List<String> engines;
    private CarDetailsAdapter adapter;
    private Realm realm;
    private IPickGenerationPresenter pickModelPresenter;
    private RecyclerView generations_rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_generation);

        setupActionBar();
        init();

        pickModelPresenter = new PickGenerationPresenter(this);
        pickModelPresenter.getGenerations();
    }


    private void init() {

        EditText searchEditText = findViewById(R.id.search_et_pick_generation);

        generations_rv = findViewById(R.id.generations_rv_pick_generation);
        generations_rv.setLayoutManager(new LinearLayoutManager(this));
        generations_rv.setHasFixedSize(true);
        generations_rv.addItemDecoration(new VerticalSpaceItemDecoration(this, 8));
        adapter = new CarDetailsAdapter(getApplicationContext(), (position, model) -> {

            Intent intent = new Intent(PickGenerationActivity.this, EditGenerationActivity.class);

            intent.putExtra("generation", model);
            intent.putExtra("position", position);

            PickGenerationActivity.this.startActivityForResult(intent, EDIT_REQUEST_CODE);

        });
        generations_rv.setAdapter(adapter);

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

    private void setupActionBar() {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView textView = findViewById(R.id.actionbar_text);
        textView.setText(R.string.searching_generation);
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
                break;
            case R.id.create_menu_item:
                startActivityForResult(new Intent(this, CreateGenerationActivity.class), CREATE_REQUEST_CODE);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == EDIT_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {

                String model = data.getStringExtra("generation");
                int position = data.getIntExtra("position", 0);

//                engines.set(position, model);
                adapter.putProperty(model, position);


            }
        } else if (CREATE_REQUEST_CODE == requestCode && resultCode == Activity.RESULT_OK) {

            ArrayList<String> model = data.getStringArrayListExtra("generation");
            ArrayList<String> a = new ArrayList<>(adapter.getPropertiesFiltered());
            a.addAll(model);
            generations_rv.scrollToPosition(adapter.getItemCount() - 1);
            adapter.setProperties(a);


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

    @Override
    public void getGeneration(List<CarGeneration> generations) {
        List<String> stringModels = new ArrayList<>();
        for (CarGeneration model : generations) {
            stringModels.add(model.getGeneration());
        }

        adapter.setProperties(stringModels);
    }
}
