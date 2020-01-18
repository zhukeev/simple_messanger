package com.example.oil.ui.manual.oil.add_oil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oil.R;
import com.example.oil.adapter.MenuAdapter;
import com.example.oil.model.Menu;
import com.example.oil.ui.manual.oil.add_oil.pick_class.PickOilClassActivity;
import com.example.oil.ui.manual.oil.add_oil.pick_type.PickOilTypeActivity;
import com.example.oil.ui.manual.oil.add_oil.pick_viscosity.PickOilViscosityActivity;
import com.example.oil.utils.Interfaces;
import com.example.oil.utils.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class AddOilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_oil);

        setupActionBar();

        init();
    }

    private void init() {

        RecyclerView menu_rv = findViewById(R.id.parameters_rv_add_oil);
        menu_rv.setLayoutManager(new GridLayoutManager(AddOilActivity.this, 2));
        menu_rv.addItemDecoration(new SpacesItemDecoration(AddOilActivity.this, 8));
        menu_rv.setHasFixedSize(true);

        CardView cardViewOil = findViewById(R.id.add_oil_to_list_cv_add_oil);


        MenuAdapter adapter = new MenuAdapter(AddOilActivity.this, getManualMenu(), item -> {
            switch (item) {
                case 0:
                    AddOilActivity.this.startActivity(new Intent(AddOilActivity.this, PickOilTypeActivity.class));
                    break;
                case 1:
                    AddOilActivity.this.startActivity(new Intent(AddOilActivity.this, PickOilClassActivity.class));
                    break;
                case 2:
                    AddOilActivity.this.startActivity(new Intent(AddOilActivity.this, PickOilViscosityActivity.class));
                    break;

            }
        }, minWidth -> {
//            cardViewOil.setMinimumWidth(minWidth);
            Log.e("TAG", "init: " + minWidth);
        });
        menu_rv.setAdapter(adapter);

        adapter.OnItemCreateListener(new Interfaces.OnItemCreatedListener() {
            @Override
            public void getItemWidth(int width) {
                Log.e("TAG", "getItemWidth: " + width);
//                cardViewOil.setMinimumWidth(width);
//                ViewGroup.LayoutParams layoutParams = cardViewOil.getLayoutParams();
//                layoutParams.width = width;
//                cardViewOil.setLayoutParams(layoutParams);
//                Log.e("TAG", "getItemWidth: " + cardViewOil.getMinimumWidth());

            }
        });

        cardViewOil.setOnClickListener(view -> {

        });

    }

    private List<Menu> getManualMenu() {

        List<Menu> menus = new ArrayList<>();
        menus.add(new Menu(R.drawable.ic_oil_100, R.string.oil_types));
        menus.add(new Menu(R.drawable.ic_oil_100, R.string.oil_class));
        menus.add(new Menu(R.drawable.ic_oil_100, R.string.oil_viscosity));


        return menus;
    }

    private void setupActionBar() {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView textView = findViewById(R.id.actionbar_text);
        textView.setText(R.string.add_oil2);
    }
}
