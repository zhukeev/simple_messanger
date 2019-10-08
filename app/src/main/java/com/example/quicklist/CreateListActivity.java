package com.example.quicklist;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quicklist.model.EmojiIcon;
import com.example.quicklist.model.Event;
import com.example.quicklist.utils.SharedPreferenceHelper;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.Serializable;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CreateListActivity extends AppCompatActivity {

    private static final String TAG = "CreateListActivity";

    private StorageReference mStorageRef;
    private ImageView imageViewIcon;
    private LinearLayout color_ll;
    private EditText editTextName;
    static List<EmojiIcon> emojiIcons = new ArrayList<>();
    private Serializable icon_serializable;
    private String color = "";
    private DatabaseReference listsRef;
    private Button createBtn;
    private String iconPath;
    private EditText date_time_et;
    private FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        mStorageRef = FirebaseStorage.getInstance().getReference();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();


        init();

        getExtras();

        downloadEmojis();
    }

    private void downloadEmojis() {
        StorageReference riversRef = mStorageRef.child("Emojis");

        riversRef.listAll().addOnSuccessListener(listResult -> {

            for (final StorageReference item : listResult.getItems()) {

                final File localFile = new File(getCacheDir() + "/" + item.getName());

                if (localFile.exists()) {
                    return;
                }

                item.getFile(localFile).addOnSuccessListener(taskSnapshot -> {

                    emojiIcons.add(new EmojiIcon(item.getPath(), localFile.getAbsolutePath()));

                    Log.e(TAG, "onSuccess: path to file " + item.getName());


                    if (listResult.getItems().indexOf(item) == listResult.getItems().size()) {
                    }
                    Log.e(TAG, "onSuccess: " + localFile.getName());

                });


            }
        }).addOnFailureListener(e -> Log.e(TAG, "onFailure: " + e.getLocalizedMessage()));


    }

    private void getExtras() {
        if (getIntent().hasExtra("icon")) {
            icon_serializable = getIntent().getSerializableExtra("icon");
            imageViewIcon.setImageURI(Uri.fromFile((File) icon_serializable));
            SharedPreferenceHelper.setString(this, "icon", Uri.fromFile((File) icon_serializable).toString());
        }
        if (getIntent().hasExtra("color")) {

            color = getIntent().getStringExtra("color");
            SharedPreferenceHelper.setString(this, "color", color);
            color_ll.getBackground().setColorFilter(Color.parseColor(color), PorterDuff.Mode.SRC_ATOP);

        }


        if (!SharedPreferenceHelper.getString(this, "icon", "no").equals("no")) {
            iconPath = SharedPreferenceHelper.getString(this, "icon", "");
            imageViewIcon.setImageURI(Uri.parse(iconPath));
        }
        if (!SharedPreferenceHelper.getString(this, "color", "no").equals("no")) {
            color = SharedPreferenceHelper.getString(this, "color", "");
            color_ll.getBackground().setColorFilter(Color.parseColor(color), PorterDuff.Mode.SRC_ATOP);
        }
    }


    private void init() {


        imageViewIcon = findViewById(R.id.category_icon_list);
        editTextName = findViewById(R.id.name_et_list);
        color_ll = findViewById(R.id.color_ll_list);
        createBtn = findViewById(R.id.create_btn_create_list);
        date_time_et = findViewById(R.id.datetime_et_list);

        date_time_et.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateTimePicker();
            }
        });

        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listsRef = FirebaseDatabase.getInstance().getReference().child("lists").child(currentUser.getUid());
                Event event = new Event(editTextName.getText().toString(),
                        date_time_et.getText().toString(),
                        iconPath.substring(iconPath.lastIndexOf('/')),
                        color);

                String multiListKey = listsRef.push().getKey();

                listsRef.child(multiListKey).updateChildren(event.toMap()).addOnFailureListener(e -> {
                    Log.e(TAG, "onFailure: " + e.getLocalizedMessage());
                    Toast.makeText(CreateListActivity.this, getString(R.string.couldnt_create_list), Toast.LENGTH_SHORT).show();
                }).addOnSuccessListener(aVoid -> {
                    Toast.makeText(CreateListActivity.this, getString(R.string.list_created_successfully), Toast.LENGTH_SHORT).show();
                });

                startActivity(new Intent(CreateListActivity.this, MainActivity.class));
            }
        });


        findViewById(R.id.round_holder_fl).setOnClickListener(view -> {

            Intent intent = new Intent(CreateListActivity.this, IconChooserActivity.class);
            intent.putExtra("from", "list");
            startActivity(intent);
        });

        color_ll.setOnClickListener(view -> {
            Intent intent = new Intent(CreateListActivity.this, ColorChooserActivity.class);
            intent.putExtra("from", "list");
            startActivity(intent);

        });


        editTextName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                editTextName.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);

            }
        });


    }

    private void showDateTimePicker() {
        final Calendar currentDate = Calendar.getInstance();
        Calendar date;
        date = Calendar.getInstance();
        new DatePickerDialog(this, (view, year, monthOfYear, dayOfMonth) -> {

            date.set(year, monthOfYear, dayOfMonth);
            new TimePickerDialog(this, (view1, hourOfDay, minute) -> {
                date.set(Calendar.HOUR_OF_DAY, hourOfDay);
                date.set(Calendar.MINUTE, minute);

                DateFormat df = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.SHORT);
                df.format(date.getTime());

                date_time_et.setText(df.format(date.getTime()));


            }, currentDate.get(Calendar.HOUR_OF_DAY), currentDate.get(Calendar.MINUTE), false).show();
        }, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DATE)).show();

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
