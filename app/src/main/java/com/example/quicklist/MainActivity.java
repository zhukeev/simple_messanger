package com.example.quicklist;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quicklist.adapter.MultiListAdapter;
import com.example.quicklist.adapter.ListEventAdapter;
import com.example.quicklist.model.Event;
import com.example.quicklist.model.HexColors;
import com.example.quicklist.model.MultiList;
import com.example.quicklist.utils.OnItemClickListener;
import com.example.quicklist.utils.SpacesItemDecoration;
import com.example.quicklist.utils.VerticalSpaceItemDecoration;
import com.example.recyclerviewenhanced.OnActivityTouchListener;
import com.example.recyclerviewenhanced.RecyclerTouchListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerTouchListener.RecyclerTouchListenerHelper {

    private static final String TAG = "MainActivity";

    RecyclerView event_list_rv;
    RecyclerTouchListener onTouchListener;
    private OnActivityTouchListener touchListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {

        RecyclerView category_rv = findViewById(R.id.category_recycler_view_main);

        category_rv.setNestedScrollingEnabled(false);
        category_rv.addItemDecoration(new SpacesItemDecoration(MainActivity.this, 8));
        category_rv.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
        category_rv.setAdapter(new MultiListAdapter(MainActivity.this,getMultiList() ,new OnItemClickListener() {
            @Override
            public void onItemClickListener(int position) {

                if (position==getMultiList().size()){
                    startActivity(new Intent(MainActivity.this,CreateMultiListActivity.class));
                }

                Log.e(TAG, "onItemClickListener: "+getMultiList().size() );
                Log.e(TAG, "onItemClickListener: "+position );
            }
        }));


        event_list_rv = findViewById(R.id.list_recycler_view_main);

        event_list_rv.setNestedScrollingEnabled(false);
        event_list_rv.addItemDecoration(new VerticalSpaceItemDecoration(MainActivity.this, 8));
        event_list_rv.setLayoutManager(new GridLayoutManager(MainActivity.this, 1));
        event_list_rv.setAdapter(new ListEventAdapter(MainActivity.this, getEvents()));

        onTouchListener = new RecyclerTouchListener(MainActivity.this, event_list_rv);

        onTouchListener.setSwipeOptionViews(R.id.share_item, R.id.remove_item)
                .setSwipeable(R.id.front, R.id.back, new RecyclerTouchListener.OnSwipeOptionsClickListener() {
                    @Override
                    public void onSwipeOptionClicked(int viewID, int position) {
                        if (viewID == R.id.share_item) {
                            Toast.makeText(MainActivity.this, "Share", Toast.LENGTH_SHORT).show();
                        } else
                            Toast.makeText(MainActivity.this, "Remove", Toast.LENGTH_SHORT).show();
                    }
                });


    }

    private List<MultiList> getMultiList() {

        List<MultiList> lists = new ArrayList<>();

        lists.add(new MultiList("Дни рождения",R.drawable.celeb));
        lists.add(new MultiList("Рецепты",R.drawable.soup));
        lists.add(new MultiList("Поездки",R.drawable.beer));

        return lists;

    }

    private List<Event> getEvents() {

        List<Event> events = new ArrayList<>();

        events.add(new Event("День рождения жены", "14:00 12.11.2019", R.drawable.corn, HexColors.BlUE_2));
        events.add(new Event("Новый год", "14:00 12.11.2019", R.drawable.bone, HexColors.GREEN_1));
        events.add(new Event("Поездка на шашлыки", "14:00 12.11.2019", R.drawable.cake, HexColors.GREEN_4));
        events.add(new Event("Поездка на озеро", "14:00 12.11.2019", R.drawable.cookie, HexColors.RED_1));
        events.add(new Event("Домой", "14:00 12.11.2019", R.drawable.chocolate, HexColors.VIOLET_3));
        events.add(new Event("Родителям", "14:00 12.11.2019", R.drawable.burger, HexColors.RED_3));


        return events;
    }

    @Override
    protected void onResume() {
        super.onResume();

        event_list_rv.addOnItemTouchListener(onTouchListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        event_list_rv.removeOnItemTouchListener(onTouchListener);
    }

    @Override
    public void setOnActivityTouchListener(OnActivityTouchListener listener) {
        this.touchListener = listener;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (touchListener != null) touchListener.getTouchCoordinates(ev);
        return super.dispatchTouchEvent(ev);
    }

}
