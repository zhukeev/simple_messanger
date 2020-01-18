package com.example.oil.ui.manual.oil;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oil.R;
import com.example.oil.adapter.HintSpinnerAdapter;
import com.example.oil.adapter.OilAdapter;
import com.example.oil.custom_view.HintSpinner;
import com.example.oil.model.Oil;
import com.example.oil.ui.manual.oil.add_oil.AddOilActivity;
import com.example.oil.utils.VerticalSpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class OilManualActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oil_manual);

        setupActionBar();

        init();

    }

    private void init() {
        HintSpinner producerSpinner = findViewById(R.id.oil_producer);
        HintSpinner compositionSpinner = findViewById(R.id.oil_composition);
        HintSpinner viscositySpinner = findViewById(R.id.oil_viscosity);
        HintSpinner classSpinner = findViewById(R.id.oil_class);
        HintSpinner engineSpinner = findViewById(R.id.oil_engine);

        String[] producers = new String[]{"Mannol", "Mobil 1"};

        producerSpinner.setAdapter(new HintSpinnerAdapter<>(this, producers, R.string.producer));
        compositionSpinner.setAdapter(new HintSpinnerAdapter<>(this, producers, R.string.composition));
        viscositySpinner.setAdapter(new HintSpinnerAdapter<>(this, producers, R.string.viscosity));
        classSpinner.setAdapter(new HintSpinnerAdapter<>(this, producers, R.string.class_string));
        engineSpinner.setAdapter(new HintSpinnerAdapter<>(this, producers, R.string.engines));

        RecyclerView oil_rv = findViewById(R.id.oil_manual_rv_manual);
        oil_rv.setLayoutManager(new LinearLayoutManager(this));
        oil_rv.setHasFixedSize(true);
        oil_rv.addItemDecoration(new VerticalSpaceItemDecoration(this, 8));
        oil_rv.setAdapter(new OilAdapter(getApplicationContext(), getOil(), position -> {

        }));


    }

    private List<Oil> getOil() {

        List<Oil> oils = new ArrayList<>();

        oils.add(new Oil("Mannol Molibden Benzin 10W-40", "полусинтетическое моторное масло",
                " 10W-40", "класс API SL / ACEA A3/B3", "бензиновый или дизельный",
                true, "На складе: 20 000 .л", "Приход: 120с/л", "Реализация: 150с/л", ""));
        oils.add(new Oil("Mannol Molibden Benzin 10W-40", "полусинтетическое моторное масло",
                " 10W-40", "класс API SL / ACEA A3/B3", "бензиновый или дизельный",
                true, "На складе: 20 000 .л", "Приход: 120с/л", "Реализация: 150с/л", ""));
        oils.add(new Oil("Mannol Molibden Benzin 10W-40", "полусинтетическое моторное масло",
                " 10W-40", "класс API SL / ACEA A3/B3", "бензиновый или дизельный",
                true, "На складе: 20 000 .л", "Приход: 120с/л", "Реализация: 150с/л", ""));
        oils.add(new Oil("Mannol Molibden Benzin 10W-40", "полусинтетическое моторное масло",
                " 10W-40", "класс API SL / ACEA A3/B3", "бензиновый или дизельный",
                true, "На складе: 20 000 .л", "Приход: 120с/л", "Реализация: 150с/л", ""));
        oils.add(new Oil("Mannol Molibden Benzin 10W-40", "полусинтетическое моторное масло",
                " 10W-40", "класс API SL / ACEA A3/B3", "бензиновый или дизельный",
                true, "На складе: 20 000 .л", "Приход: 120с/л", "Реализация: 150с/л", ""));
        oils.add(new Oil("Mannol Molibden Benzin 10W-40", "полусинтетическое моторное масло",
                " 10W-40", "класс API SL / ACEA A3/B3", "бензиновый или дизельный",
                true, "На складе: 20 000 .л", "Приход: 120с/л", "Реализация: 150с/л", ""));
        oils.add(new Oil("Mannol Molibden Benzin 10W-40", "полусинтетическое моторное масло",
                " 10W-40", "класс API SL / ACEA A3/B3", "бензиновый или дизельный",
                true, "На складе: 20 000 .л", "Приход: 120с/л", "Реализация: 150с/л", ""));
        oils.add(new Oil("Mannol Molibden Benzin 10W-40", "полусинтетическое моторное масло",
                " 10W-40", "класс API SL / ACEA A3/B3", "бензиновый или дизельный",
                true, "На складе: 20 000 .л", "Приход: 120с/л", "Реализация: 150с/л", ""));
        oils.add(new Oil("Mannol Molibden Benzin 10W-40", "полусинтетическое моторное масло",
                " 10W-40", "класс API SL / ACEA A3/B3", "бензиновый или дизельный",
                true, "На складе: 20 000 .л", "Приход: 120с/л", "Реализация: 150с/л", ""));
        oils.add(new Oil("Mannol Molibden Benzin 10W-40", "полусинтетическое моторное масло",
                " 10W-40", "класс API SL / ACEA A3/B3", "бензиновый или дизельный",
                true, "На складе: 20 000 .л", "Приход: 120с/л", "Реализация: 150с/л", ""));
        oils.add(new Oil("Mannol Molibden Benzin 10W-40", "полусинтетическое моторное масло",
                " 10W-40", "класс API SL / ACEA A3/B3", "бензиновый или дизельный",
                true, "На складе: 20 000 .л", "Приход: 120с/л", "Реализация: 150с/л", ""));
        oils.add(new Oil("Mannol Molibden Benzin 10W-40", "полусинтетическое моторное масло",
                " 10W-40", "класс API SL / ACEA A3/B3", "бензиновый или дизельный",
                true, "На складе: 20 000 .л", "Приход: 120с/л", "Реализация: 150с/л", ""));


        return oils;
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
                break;
            case R.id.create_menu_item:
                startActivity(new Intent(OilManualActivity.this, AddOilActivity.class));
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
