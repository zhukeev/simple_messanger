package com.example.oil.ui.manual.kinda_service.pick_class;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Parcelable;
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
import com.example.oil.adapter.KindOfServiceAdapter;
import com.example.oil.ui.manual.kinda_service.pick_class.create_oil_type.CreateKindaServiceActivity;
import com.example.oil.ui.manual.kinda_service.pick_class.edit_oil_type.EditKindaServiceActivity;
import com.example.oil.ui.manual.kinda_service.pick_class.model.CarService;
import com.example.oil.ui.manual.kinda_service.pick_class.presenter.IKindaServicePresenter;
import com.example.oil.ui.manual.kinda_service.pick_class.presenter.KindaServicePresenter;
import com.example.oil.ui.manual.kinda_service.pick_class.view.IKindaServiceView;
import com.example.oil.utils.Interfaces;
import com.example.oil.utils.VerticalSpaceItemDecoration;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

public class KindaServiceActivity extends AppCompatActivity implements IKindaServiceView {

    final int CREATE_REQUEST_CODE = 200;
    final int EDIT_REQUEST_CODE = 100;
    private KindOfServiceAdapter adapter;
    private IKindaServicePresenter pickModelPresenter;
    private RecyclerView services_rv;
    private List<CarService> carServices = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kinda_service);

        setupActionBar();
        init();

        pickModelPresenter = new KindaServicePresenter(this);
        pickModelPresenter.getOilClasses();
    }


    private void init() {

        EditText searchEditText = findViewById(R.id.search_et_kinda_services);

        services_rv = findViewById(R.id.services_rv_kindaservices);
        services_rv.setLayoutManager(new LinearLayoutManager(this));
        services_rv.setHasFixedSize(true);
        services_rv.addItemDecoration(new VerticalSpaceItemDecoration(this, 8));


        adapter = new KindOfServiceAdapter(this, carServices, new Interfaces.OnItemServiceClickListener() {
            @Override
            public void onItemClickListener(int position, CarService carService) {
                Intent intent = new Intent(KindaServiceActivity.this, EditKindaServiceActivity.class);

                Parcelable wrapped = Parcels.wrap(carService);

                intent.putExtra("service", wrapped);
                intent.putExtra("position", position);

                startActivityForResult(intent, EDIT_REQUEST_CODE);
            }
        });

        services_rv.setAdapter(adapter);

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
        textView.setText(R.string.classes_string);
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
                startActivityForResult(new Intent(this, CreateKindaServiceActivity.class), CREATE_REQUEST_CODE);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == EDIT_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {

                CarService model = (CarService) Parcels.unwrap( data.getParcelableExtra("service"));

                int position = data.getIntExtra("position", 0);

                adapter.addService(model, position);


            }
        } else if (CREATE_REQUEST_CODE == requestCode && resultCode == Activity.RESULT_OK) {

            ArrayList<CarService> model = (ArrayList<CarService>) data.getSerializableExtra("service");
            ArrayList<CarService> a = new ArrayList<>(adapter.getFilteredCars());
            a.addAll(model);
            services_rv.scrollToPosition(adapter.getItemCount() - 1);
            adapter.setService(a);


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
    public void getKindaServices(List<CarService> carServices) {
//        List<CarService> stringModels = new ArrayList<>();
//        for (CarService service : carServices) {
//            stringModels.add(service);
//        }

        adapter.setService(carServices);
    }
}
