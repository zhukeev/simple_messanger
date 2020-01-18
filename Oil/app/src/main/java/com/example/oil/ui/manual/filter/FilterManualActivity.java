package com.example.oil.ui.manual.filter;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oil.R;
import com.example.oil.adapter.FilterAdapter;
import com.example.oil.adapter.HintSpinnerAdapter;
import com.example.oil.custom_view.HintSpinner;
import com.example.oil.model.Filter;
import com.example.oil.utils.VerticalSpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class FilterManualActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_manual);

        setupActionBar();

        init();

    }

    private void init() {
        HintSpinner modelSpinner = findViewById(R.id.model_spinner_filter_manual);
        HintSpinner engineSpinner = findViewById(R.id.engine_spinner_filter_manual);

        String[] producers = new String[]{"Mannol", "Mobil 1"};

        modelSpinner.setAdapter(new HintSpinnerAdapter<>(this, producers, R.string.for_model));
        engineSpinner.setAdapter(new HintSpinnerAdapter<>(this, producers, R.string.for_engine));

        RecyclerView oil_rv = findViewById(R.id.filters_rv_filter_manual);
        oil_rv.setLayoutManager(new LinearLayoutManager(this));
        oil_rv.setHasFixedSize(true);
        oil_rv.addItemDecoration(new VerticalSpaceItemDecoration(this, 8));
        oil_rv.setAdapter(new FilterAdapter(getApplicationContext(), getFilters(), position -> {

        }));


    }

    private List<Filter> getFilters() {

        List<Filter> filters = new ArrayList<>();

        filters.add(new Filter("FORD, Mazda, Volvo Generic France",
                " Ford B-MAX / Ford C-MAX / Ford Fiesta / Ford Focus, BM, DB / Ford Galaxy / Ford Kuga / Ford Tourneo Connect / Mazda Mazda2, DE, DY /" +
                        " Mazda Scrum / Volvo S40, MS / Volvo S80 / Volvo V40, MV / Volvo V50 / Volvo V70 /  Volvo XC60, DZ", "FUJA, FXJA, ASDA, ASDB, " +
                "ECOBOOST, HWDA, HWDB, HXDA, HXDB, IQDB, JQDA, JQDB, JTDA, JTDB, MUDA, PNDA, SHDA, SHDB, SHDC, SIDA, XTDA, IQDA, JQMA, JQMB, JTMA, IQJA, SNJA, SNJB", ""));
        filters.add(new Filter("FORD, Mazda, Volvo Generic France",
                " Ford B-MAX / Ford C-MAX / Ford Fiesta / Ford Focus, BM, DB / Ford Galaxy / Ford Kuga / Ford Tourneo Connect / Mazda Mazda2, DE, DY /" +
                        " Mazda Scrum / Volvo S40, MS / Volvo S80 / Volvo V40, MV / Volvo V50 / Volvo V70 /  Volvo XC60, DZ", "FUJA, FXJA, ASDA, ASDB, " +
                "ECOBOOST, HWDA, HWDB, HXDA, HXDB, IQDB, JQDA, JQDB, JTDA, JTDB, MUDA, PNDA, SHDA, SHDB, SHDC, SIDA, XTDA, IQDA, JQMA, JQMB, JTMA, IQJA, SNJA, SNJB", ""));

        return filters;
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
                Toast.makeText(this, "Create", Toast.LENGTH_SHORT).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
