package com.example.quicklist;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quicklist.adapter.ColorListAdapter;
import com.example.quicklist.model.HexColors;
import com.example.quicklist.utils.OnItemClickListener;
import com.example.quicklist.utils.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class ColorChooserActivity extends AppCompatActivity {


    static List<String > colors = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_chooser);


        init();

    }

    private void init() {
        RecyclerView category_rv = findViewById(R.id.color_choose_rv_color_chooser);

        category_rv.addItemDecoration(new SpacesItemDecoration(this, 8));
        category_rv.setLayoutManager(new GridLayoutManager(this, 5));
        category_rv.setAdapter(new ColorListAdapter(this, getColors(), new OnItemClickListener() {
            @Override
            public void onItemClickListener(int position) {
                Intent intent = new Intent(ColorChooserActivity.this,CreateMultiListActivity.class);
                intent.putExtra("color",colors.get(position));
                startActivity(intent);

            }
        }));
    }

    private List<String> getColors() {

        if (!colors.isEmpty()){
            return colors;
        }

        colors.add(HexColors.RED_0);
        colors.add(HexColors.RED_1);
        colors.add(HexColors.RED_2);
        colors.add(HexColors.RED_3);
        colors.add(HexColors.RED_4);

        colors.add(HexColors.ORANGE_0);
        colors.add(HexColors.ORANGE_1);
        colors.add(HexColors.ORANGE_2);
        colors.add(HexColors.ORANGE_3);
        colors.add(HexColors.ORANGE_4);

        colors.add(HexColors.YELLOW_0);
        colors.add(HexColors.YELLOW_1);
        colors.add(HexColors.YELLOW_2);
        colors.add(HexColors.YELLOW_3);
        colors.add(HexColors.YELLOW_4);

        colors.add(HexColors.GREEN_0);
        colors.add(HexColors.GREEN_1);
        colors.add(HexColors.GREEN_2);
        colors.add(HexColors.GREEN_3);
        colors.add(HexColors.GREEN_4);

        colors.add(HexColors.CYAN_0);
        colors.add(HexColors.CYAN_1);
        colors.add(HexColors.CYAN_2);
        colors.add(HexColors.CYAN_3);
        colors.add(HexColors.CYAN_4);

        colors.add(HexColors.BlUE_0);
        colors.add(HexColors.BlUE_1);
        colors.add(HexColors.BlUE_2);
        colors.add(HexColors.BlUE_3);
        colors.add(HexColors.BlUE_4);

        colors.add(HexColors.VIOLET_0);
        colors.add(HexColors.VIOLET_1);
        colors.add(HexColors.VIOLET_2);
        colors.add(HexColors.VIOLET_3);
        colors.add(HexColors.VIOLET_4);

        colors.add(HexColors.PINK_0);
        colors.add(HexColors.PINK_1);
        colors.add(HexColors.PINK_2);
        colors.add(HexColors.PINK_3);
        colors.add(HexColors.PINK_4);

        colors.add(HexColors.ELECTRIC_VIOLET_0);
        colors.add(HexColors.ELECTRIC_VIOLET_1);
        colors.add(HexColors.ELECTRIC_VIOLET_2);
        colors.add(HexColors.ELECTRIC_VIOLET_3);
        colors.add(HexColors.ELECTRIC_VIOLET_4);


        return colors;
    }


}
