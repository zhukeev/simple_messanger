package com.example.oil;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private int numberOfLines = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void init() {
        final LinearLayout linearLayout = findViewById(R.id.address_container_ll);



        final EditText editTextAddress = findViewById( R.id.address_et);

        editTextAddress.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if(event.getAction() == MotionEvent.ACTION_UP) {
                    if(event.getRawX() >= (editTextAddress.getRight() - editTextAddress.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
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

    public void Add_Line() {
        LinearLayout ll = (LinearLayout)findViewById(R.id.address_container_ll);
        // add edittext
        EditText et = new EditText(this);
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        et.setLayoutParams(p);
        et.setHint(R.string.address);
        et.setId(numberOfLines + 1);
        ll.addView(et);
        numberOfLines++;
    }
}
