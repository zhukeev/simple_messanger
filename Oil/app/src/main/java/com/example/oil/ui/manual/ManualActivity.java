package com.example.oil.ui.manual;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oil.R;
import com.example.oil.adapter.MenuAdapter;
import com.example.oil.model.Menu;
import com.example.oil.ui.manual.car_brand.CarBrandActivity;
import com.example.oil.ui.manual.engines.EnginesActivity;
import com.example.oil.ui.manual.filter.FilterManualActivity;
import com.example.oil.ui.manual.kinda_service.pick_class.KindaServiceActivity;
import com.example.oil.ui.manual.oil.OilManualActivity;
import com.example.oil.utils.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class ManualActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual);

        setupActionBar();

        init();
    }

    private void init() {

        RecyclerView menu_rv = findViewById(R.id.menu_rv_manuals);
        menu_rv.setLayoutManager(new GridLayoutManager(ManualActivity.this, 2));
        menu_rv.addItemDecoration(new SpacesItemDecoration(ManualActivity.this, 8));
        menu_rv.setHasFixedSize(true);
        MenuAdapter adapter = new MenuAdapter(ManualActivity.this, getManualMenu(), item -> {
            switch (item) {
                case 0:
                    startActivity(new Intent(ManualActivity.this, OilManualActivity.class));
                    break;
                case 1:
                    startActivity(new Intent(ManualActivity.this, FilterManualActivity.class));
                    break;
                case 2:
                    startActivity(new Intent(ManualActivity.this, KindaServiceActivity.class));
                    break;
                case 3:
                    startActivity(new Intent(ManualActivity.this, CarBrandActivity.class));
                    break;
                case 4:
                    startActivity(new Intent(ManualActivity.this, EnginesActivity.class));
                    break;
            }
        });
        menu_rv.setAdapter(adapter);

    }

    private List<Menu> getManualMenu() {

        List<Menu> menus = new ArrayList<>();
        menus.add(new Menu(R.drawable.ic_oil_100, R.string.oil));
        menus.add(new Menu(R.drawable.ic_filter, R.string.filters));
        menus.add(new Menu(R.drawable.ic_kinda_service, R.string.kinda_service));
        menus.add(new Menu(R.drawable.ic_car_brands, R.string.car_brands));
        menus.add(new Menu(R.drawable.ic_engine, R.string.engines));


        return menus;
    }

    private void setupActionBar() {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView textView = findViewById(R.id.actionbar_text);
        textView.setText(R.string.manual);
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
