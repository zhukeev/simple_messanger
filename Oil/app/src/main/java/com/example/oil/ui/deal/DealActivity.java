package com.example.oil.ui.deal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.oil.R;

public class DealActivity extends AppCompatActivity {

    private int numberOfLines = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deal);

        setupActionbar();

        init();
    }

    private void setupActionbar() {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar);
        TextView textView = findViewById(R.id.actionbar_text);
        textView.setText(R.string.deal);

    }

    @SuppressLint("ClickableViewAccessibility")
    private void init() {
        final LinearLayout linearLayout = findViewById(R.id.address_container_ll);


        final EditText editTextAddress = findViewById(R.id.address_et);

        editTextAddress.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (editTextAddress.getRight() - editTextAddress.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        // Add EditText to LinearLayout
                        if (linearLayout != null) {
//                            linearLayout.addView(editText);
                        }

                        Add_Line();

                        return true;
                    }
                }
                return false;
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

//        NavUtils.navigateUpFromSameTask(this);

        onBackPressed();

        return super.onOptionsItemSelected(item);
    }

    public void Add_Line() {
        LinearLayout ll = (LinearLayout) findViewById(R.id.address_container_ll);
        // add edittext
        EditText et = new EditText(this);
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        et.setLayoutParams(p);
        et.setHint(R.string.address);
        et.setId(numberOfLines + 1);
        ll.addView(et);
        numberOfLines++;
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int) event.getRawX(), (int) event.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }

            }
        }

        return super.dispatchTouchEvent(event);
    }
}
