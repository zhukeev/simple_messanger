package com.example.oil.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oil.R;
import com.example.oil.adapter.MenuAdapter;
import com.example.oil.model.Menu;
import com.example.oil.ui.deal.DealActivity;
import com.example.oil.ui.manual.ManualActivity;
import com.example.oil.utils.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Realm.init(this);

        setupActionBar();

        init();

    }

    private void init() {

        RecyclerView menu_rv = findViewById(R.id.menu_rv_home);

        menu_rv.setLayoutManager(new GridLayoutManager(HomeActivity.this, 2));
        menu_rv.addItemDecoration(new SpacesItemDecoration(HomeActivity.this, 8));
        menu_rv.setHasFixedSize(true);

        MenuAdapter adapter = new MenuAdapter(HomeActivity.this, getHomeMenu(), item -> {
            switch (item) {
                case 0:
                    startActivity(new Intent(HomeActivity.this, DealActivity.class));
                    break;
                case 1:
                    startActivity(new Intent(HomeActivity.this, ManualActivity.class));
                    break;
            }
        });
        menu_rv.setAdapter(adapter);
        adapter.setOrderCount(2);

    }

    private List<Menu> getHomeMenu() {

        List<Menu> menus = new ArrayList<>();
        menus.add(new Menu(R.drawable.ic_create_order, R.string.create_order));
        menus.add(new Menu(R.drawable.ic_info, R.string.info));
        menus.add(new Menu(R.drawable.ic_reports, R.string.reports));
        menus.add(new Menu(R.drawable.ic_orders, R.string.orders));
        menus.add(new Menu(R.drawable.ic_wharehouse, R.string.warehouse));
        menus.add(new Menu(R.drawable.ic_profile, R.string.profile));

        return menus;
    }

    private void setupActionBar() {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar);
        TextView textView = findViewById(R.id.actionbar_text);
        textView.setText(R.string.home);
    }
}
