package com.example.oil.ui.manual.car_brand;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oil.R;
import com.example.oil.adapter.CarAdapter;
import com.example.oil.adapter.HintSpinnerAdapter;
import com.example.oil.custom_view.HintSpinner;
import com.example.oil.ui.manual.car_brand.create_car.CreateCarActivity;
import com.example.oil.ui.manual.car_brand.model.Car;
import com.example.oil.ui.manual.car_brand.presenter.CarBrandPresenter;
import com.example.oil.ui.manual.car_brand.presenter.ICarBrandPresenter;
import com.example.oil.ui.manual.car_brand.view.ICarBrandView;
import com.example.oil.utils.VerticalSpaceItemDecoration;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import io.realm.Realm;

public class CarBrandActivity extends AppCompatActivity implements ICarBrandView, AdapterView.OnItemSelectedListener {

    private ICarBrandPresenter carBrandPresenter;
    private CarAdapter adapter = new CarAdapter(this);
    private HintSpinnerAdapter manufactureAdapter;
    private HintSpinnerAdapter modelAdapter;
    private HintSpinnerAdapter generationAdapter;
    private HintSpinnerAdapter carBodyAdapter;
    private HintSpinnerAdapter yearOfManufactureAdapter;
    private HintSpinner manufacturerSpinner;
    private HintSpinner modelSpinner;
    private HintSpinner generationSpinner;
    private HintSpinner carBodySpinner;
    private HintSpinner yearOfManufactureSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_brand);

        Realm.init(this);

        carBrandPresenter = new CarBrandPresenter(this);


        carBrandPresenter.getCars();
        init();

        setupActionBar();


    }

    private void init() {
        manufacturerSpinner = findViewById(R.id.manufacturer_spinner_car_brand);
        modelSpinner = findViewById(R.id.model_spinner_car_brand);
        generationSpinner = findViewById(R.id.generation_spinner_car_brand);
        carBodySpinner = findViewById(R.id.car_body_spinner_car_brand);
        yearOfManufactureSpinner = findViewById(R.id.year_of_manufacture_spinner_car_brand);


        modelSpinner.setEnabled(false);
        generationSpinner.setEnabled(false);

        manufacturerSpinner.setAdapter(manufactureAdapter);
        modelSpinner.setAdapter(modelAdapter);
        generationSpinner.setAdapter(generationAdapter);
        carBodySpinner.setAdapter(carBodyAdapter);
        yearOfManufactureSpinner.setAdapter(yearOfManufactureAdapter);

        manufacturerSpinner.setOnItemSelectedListener(this);
        modelSpinner.setOnItemSelectedListener(this);
        generationSpinner.setOnItemSelectedListener(this);
        carBodySpinner.setOnItemSelectedListener(this);
        yearOfManufactureSpinner.setOnItemSelectedListener(this);

        RecyclerView oil_rv = findViewById(R.id.cars_rv_car_brand);
        oil_rv.setLayoutManager(new LinearLayoutManager(this));
        oil_rv.setHasFixedSize(true);
        oil_rv.addItemDecoration(new VerticalSpaceItemDecoration(this, 8));

       /* adapter = new CarAdapter(getApplicationContext(), position -> {

        });*/
        oil_rv.setAdapter(adapter);


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
                startActivity(new Intent(CarBrandActivity.this, CreateCarActivity.class));
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void updateCarList(List<Car> carList) {


        adapter.setCars(carList);

    }

    @Override
    public void setCarList(List<Car> carList) {

        List<String> manufacturers = new ArrayList<>();
        List<String> models = new ArrayList<>();
        List<String> generations = new ArrayList<>();
        List<String> carBody = new ArrayList<>();
        List<String> yearsOfManufacture = new ArrayList<>();

        for (Car car : carList) {
            manufacturers.add(car.getManufacturer());
            models.add(car.getModel());
            generations.add(car.getGeneration());
            carBody.add(car.getCarBody());
            yearsOfManufacture.add(car.getYearOfManufacture());
        }

        Set<String> manufacturersSet = new HashSet<>(manufacturers);
        Set<String> modelsSet = new HashSet<>(models);
        Set<String> generationsSet = new HashSet<>(generations);
        Set<String> carBodySet = new HashSet<>(carBody);
        Set<String> yearsOfManufactureSet = new HashSet<>(yearsOfManufacture);

        manufacturers.clear();
        manufacturers.addAll(manufacturersSet);
        models.clear();
        models.addAll(modelsSet);
        generations.clear();
        generations.addAll(generationsSet);
        carBody.clear();
        carBody.addAll(carBodySet);
        yearsOfManufacture.clear();
        yearsOfManufacture.addAll(yearsOfManufactureSet);

        manufactureAdapter = new HintSpinnerAdapter<>(this, manufacturers, R.string.producer);

        modelAdapter = new HintSpinnerAdapter<>(this, models, R.string.model);

        generationAdapter = new HintSpinnerAdapter<>(this, generations, R.string.generation);

        carBodyAdapter = new HintSpinnerAdapter<>(this, carBody, R.string.car_body);

        yearOfManufactureAdapter = new HintSpinnerAdapter<>(this, yearsOfManufacture, R.string.yearOfManufacture);


        adapter.setCars(carList);

    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        switch (adapterView.getId()) {
            case R.id.manufacturer_spinner_car_brand:
                carBrandPresenter.setManufacturer(adapterView.getItemAtPosition(i).toString());
                generationSpinner.setSelection(0);
                modelSpinner.setSelection(0);
                modelSpinner.setEnabled(true);
                generationSpinner.setEnabled(false);
                break;
            case R.id.model_spinner_car_brand:
                carBrandPresenter.setModel(adapterView.getItemAtPosition(i).toString());
                generationSpinner.setEnabled(true);
                break;
            case R.id.generation_spinner_car_brand:
                carBrandPresenter.setGeneration(adapterView.getItemAtPosition(i).toString());
                break;
            case R.id.car_body_spinner_car_brand:
                if (manufacturerSpinner.getSelectedItemPosition() > 0) {
                    carBrandPresenter.setCarBody(adapterView.getItemAtPosition(i).toString(), adapter.getCars());
                } else
                    carBrandPresenter.setCarBody(adapterView.getItemAtPosition(i).toString());
                break;
            case R.id.year_of_manufacture_spinner_car_brand:
                carBrandPresenter.setYearOfManufacture(adapterView.getItemAtPosition(i).toString());
                break;
        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
