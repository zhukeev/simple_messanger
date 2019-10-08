package com.example.quicklist;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quicklist.adapter.ListEventAdapter;
import com.example.quicklist.adapter.MultiListAdapter;
import com.example.quicklist.model.Event;
import com.example.quicklist.model.HexColors;
import com.example.quicklist.model.MultiTask;
import com.example.quicklist.utils.OnItemClickListener;
import com.example.quicklist.utils.SpacesItemDecoration;
import com.example.quicklist.utils.VerticalSpaceItemDecoration;
import com.example.recyclerviewenhanced.OnActivityTouchListener;
import com.example.recyclerviewenhanced.RecyclerTouchListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerTouchListener.RecyclerTouchListenerHelper {

    private static final String TAG = "MainActivity";

    RecyclerView list_rv;
    RecyclerTouchListener onTouchListener;
    private OnActivityTouchListener touchListener;
    private ListEventAdapter listEventAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        init();

//        listChildEventListener();
    }

    private void listChildEventListener() {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        DatabaseReference listsRef = FirebaseDatabase.getInstance().getReference().child("lists").child(currentUser.getUid());

        listsRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Event event = dataSnapshot.getValue(Event.class);

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void init() {


        //region setup MultiList RecyclerView
        RecyclerView multiList_rv = findViewById(R.id.category_recycler_view_main);

        multiList_rv.setNestedScrollingEnabled(false);
        multiList_rv.addItemDecoration(new SpacesItemDecoration(MainActivity.this, 8));
        multiList_rv.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
        multiList_rv.setAdapter(new MultiListAdapter(MainActivity.this, getMultiList(), new OnItemClickListener() {
            @Override
            public void onItemClickListener(int position) {

                if (position == getMultiList().size()) {
                    startActivity(new Intent(MainActivity.this, CreateMultiListActivity.class));
                }

                Log.e(TAG, "onItemClickListener: " + getMultiList().size());
                Log.e(TAG, "onItemClickListener: " + position);
            }
        }));
        //endregion


        //region List RecyclerView
        list_rv = findViewById(R.id.list_recycler_view_main);

        list_rv.setNestedScrollingEnabled(false);
        list_rv.addItemDecoration(new VerticalSpaceItemDecoration(MainActivity.this, 8));
        list_rv.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        list_rv.setHasFixedSize(true);

        list_rv.setAdapter(new ListEventAdapter(MainActivity.this, getEvents()));


        onTouchListener = new RecyclerTouchListener(MainActivity.this, list_rv);

        onTouchListener.setSwipeOptionViews(R.id.share_item, R.id.remove_item)
                .setSwipeable(R.id.front, R.id.back, (viewID, position) -> {

                    if (viewID == R.id.share_item) {
                        Toast.makeText(MainActivity.this, "Share", Toast.LENGTH_SHORT).show();
                    } else{

//                        Log.e(TAG, "init: "+position );

                        listEventAdapter.removeList(position);
//                        Toast.makeText(MainActivity.this, "Remove", Toast.LENGTH_SHORT).show();
                    }
                });
        //endregion


        FloatingActionButton fab = findViewById(R.id.create_list_fab);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, CreateListActivity.class);
            startActivity(intent);
        });

    }

    private List<MultiTask> getMultiList() {

        List<MultiTask> lists = new ArrayList<>();

        lists.add(new MultiTask("Дни рождения", R.drawable.celeb));
        lists.add(new MultiTask("Рецепты", R.drawable.soup));
        lists.add(new MultiTask("Поездки", R.drawable.beer));

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

        list_rv.addOnItemTouchListener(onTouchListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        list_rv.removeOnItemTouchListener(onTouchListener);
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
