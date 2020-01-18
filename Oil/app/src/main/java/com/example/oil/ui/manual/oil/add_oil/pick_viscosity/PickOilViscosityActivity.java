package com.example.oil.ui.manual.oil.add_oil.pick_viscosity;

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
import com.example.oil.ui.manual.oil.add_oil.pick_viscosity.create_oil_type.CreateOilViscosityActivity;
import com.example.oil.ui.manual.oil.add_oil.pick_viscosity.edit_oil_type.EditOilViscosityActivity;
import com.example.oil.ui.manual.oil.add_oil.pick_viscosity.model.OilViscosity;
import com.example.oil.ui.manual.oil.add_oil.pick_viscosity.presenter.IPickOilViscosityPresenter;
import com.example.oil.ui.manual.oil.add_oil.pick_viscosity.presenter.PickOilViscosityPresenter;
import com.example.oil.ui.manual.oil.add_oil.pick_viscosity.view.IPickOilViscosityView;
import com.example.oil.utils.VerticalSpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class PickOilViscosityActivity extends AppCompatActivity implements IPickOilViscosityView {

    final int CREATE_REQUEST_CODE = 200;
    final int EDIT_REQUEST_CODE = 100;
    private CarDetailsAdapter adapter;
    private IPickOilViscosityPresenter pickModelPresenter;
    private RecyclerView oil_viscosity_rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_oil_viscosity);

        setupActionBar();
        init();

        pickModelPresenter = new PickOilViscosityPresenter(this);
        pickModelPresenter.getOilClasses();
    }


    private void init() {

        EditText searchEditText = findViewById(R.id.search_et_pick_oil_viscosity);

        oil_viscosity_rv = findViewById(R.id.oil_viscosity_rv_pick_oil_viscosity);
        oil_viscosity_rv.setLayoutManager(new LinearLayoutManager(this));
        oil_viscosity_rv.setHasFixedSize(true);
        oil_viscosity_rv.addItemDecoration(new VerticalSpaceItemDecoration(this, 8));
        adapter = new CarDetailsAdapter(getApplicationContext(), (position, model) -> {

            Intent intent = new Intent(PickOilViscosityActivity.this, EditOilViscosityActivity.class);

            intent.putExtra("oil_viscosity", model);
            intent.putExtra("position", position);

            PickOilViscosityActivity.this.startActivityForResult(intent, EDIT_REQUEST_CODE);

        });

        oil_viscosity_rv.setAdapter(adapter);

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
        textView.setText(R.string.viscosity);
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
                startActivityForResult(new Intent(this, CreateOilViscosityActivity.class), CREATE_REQUEST_CODE);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == EDIT_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {

                String model = data.getStringExtra("oil_viscosity");
                int position = data.getIntExtra("position", 0);

                adapter.putProperty(model, position);


            }
        } else if (CREATE_REQUEST_CODE == requestCode && resultCode == Activity.RESULT_OK) {

            ArrayList<String> model = data.getStringArrayListExtra("oil_viscosity");
            ArrayList<String> a = new ArrayList<>(adapter.getPropertiesFiltered());
            a.addAll(model);
            oil_viscosity_rv.scrollToPosition(adapter.getItemCount() - 1);
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
    public void getOilViscosity(List<OilViscosity> models) {
        List<String> stringModels = new ArrayList<>();
        for (OilViscosity model : models) {
            stringModels.add(model.getOil_viscosity());
        }

        adapter.setProperties(stringModels);
    }
}
