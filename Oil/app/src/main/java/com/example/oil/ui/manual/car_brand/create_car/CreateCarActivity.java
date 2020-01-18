package com.example.oil.ui.manual.car_brand.create_car;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oil.R;
import com.example.oil.adapter.CarAdapter;
import com.example.oil.ui.manual.car_brand.create_car.pick_car_body.PickCarBodyActivity;
import com.example.oil.ui.manual.car_brand.create_car.pick_engine_mark.PickEngineMarkActivity;
import com.example.oil.ui.manual.car_brand.create_car.pick_engine_mark.presenter.PickEngineMarksPresenter;
import com.example.oil.ui.manual.car_brand.create_car.pick_gen.PickGenerationActivity;
import com.example.oil.ui.manual.car_brand.create_car.pick_model.PickModelActivity;
import com.example.oil.ui.manual.car_brand.model.Car;
import com.example.oil.utils.VerticalSpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class CreateCarActivity extends AppCompatActivity implements View.OnTouchListener {

    private List<Car> carList = new ArrayList<>();
    private TextView model_tv, generation_tv, car_body, engine_brand;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_car);

        init();

        setupActionBar();

    }

    @SuppressLint("ClickableViewAccessibility")
    private void init() {

        //<editor-fold desc="RECYCLER VIEW">
        RecyclerView oil_rv = findViewById(R.id.cars_rv_create_car);
        oil_rv.setLayoutManager(new LinearLayoutManager(this));
        oil_rv.setHasFixedSize(true);
        oil_rv.addItemDecoration(new VerticalSpaceItemDecoration(this, 8));
        oil_rv.setAdapter(new CarAdapter(getApplicationContext(),  position -> {

        }));
        //</editor-fold>

        model_tv = findViewById(R.id.model_tv_create_car);
        generation_tv = findViewById(R.id.generation_tv_create_car);
        car_body = findViewById(R.id.car_body_tv_create_car);
        engine_brand = findViewById(R.id.engine_brand_tv_create_car);

        model_tv.setOnTouchListener(this);
        generation_tv.setOnTouchListener(this);
        car_body.setOnTouchListener(this);
        engine_brand.setOnTouchListener(this);

        ImageView imageView = findViewById(R.id.car_iv_create_car);


    }

    private List<Car> getCars() {

        List<Car> cars = new ArrayList<>();

        cars.add(new Car("Audi", "A6", "IV", "(C7) Рестайлинг", "(2014-2019)"));
        cars.add(new Car("Audi", "A6", "IV", "(C7) Рестайлинг", "(2014-2019)"));
        cars.add(new Car("Audi", "A6", "IV", "(C7) Рестайлинг", "(2014-2019)"));
        cars.add(new Car("Audi", "A6", "IV", "(C7) Рестайлинг", "(2014-2019)"));
        cars.add(new Car("Audi", "A6", "IV", "(C7) Рестайлинг", "(2014-2019)"));


        return cars;
    }


    private void setupActionBar() {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView textView = findViewById(R.id.actionbar_text);
        textView.setText(R.string.add_car);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                Toast.makeText(this, "home", Toast.LENGTH_SHORT).show();
                break;
            case R.id.create_menu_item:
                Toast.makeText(this, "Create", Toast.LENGTH_SHORT).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {


        final int DRAWABLE_LEFT = 0;
        final int DRAWABLE_TOP = 1;
        final int DRAWABLE_RIGHT = 2;
        final int DRAWABLE_BOTTOM = 3;


        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (event.getRawX() >= (view.getRight() - ((TextView) view).getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                // your action here

                switch (view.getId()) {
                    case R.id.model_tv_create_car:
                        startActivityForResult(new Intent(CreateCarActivity.this, PickModelActivity.class),100);
                        break;
                    case R.id.car_body_tv_create_car:
                        startActivityForResult(new Intent(CreateCarActivity.this, PickCarBodyActivity.class),200);
                        break;
                    case R.id.generation_tv_create_car:
                        startActivityForResult(new Intent(CreateCarActivity.this, PickGenerationActivity.class),300);
                        break;
                    case R.id.engine_brand_tv_create_car:
                        startActivityForResult(new Intent(CreateCarActivity.this, PickEngineMarkActivity.class),400);
                        break;
                }

                return true;
            }
        }

        return false;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
