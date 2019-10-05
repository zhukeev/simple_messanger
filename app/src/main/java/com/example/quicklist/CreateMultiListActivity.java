package com.example.quicklist;

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

import androidx.appcompat.app.AppCompatActivity;

import com.example.quicklist.model.EmojiIcon;
import com.example.quicklist.model.MultiTask;
import com.example.quicklist.utils.SharedPreferenceHelper;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateMultiListActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "CreateMultiListActivity";

    private StorageReference mStorageRef;
    private ImageView imageViewIcon;
    private LinearLayout color_ll;
    private EditText editTextName;
    static List<EmojiIcon> emojiIcons = new ArrayList<>();
    private Serializable icon_serializable;
    private String color = "";
    private DatabaseReference userRef;
    private Button createBtn;
    private String iconPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_multi_list);

        mStorageRef = FirebaseStorage.getInstance().getReference();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        userRef = FirebaseDatabase.getInstance().getReference().child("users").child(currentUser.getUid());
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


        imageViewIcon = findViewById(R.id.category_icon_multi_list);
        editTextName = findViewById(R.id.name_et_multilist);
        color_ll = findViewById(R.id.color_ll_multi_list);
        createBtn = findViewById(R.id.create_btn_create_multilist);

        createBtn.setOnClickListener(this);


        findViewById(R.id.round_holder_fl).setOnClickListener(view -> {

            Intent intent = new Intent(CreateMultiListActivity.this, IconChooserActivity.class);


//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });

        color_ll.setOnClickListener(view -> startActivity(new Intent(CreateMultiListActivity.this, ColorChooserActivity.class)));


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

    @Override
    public void onClick(View view) {

//        Map<String,Object> multiTask = new HashMap<>();

        MultiTask multiTask1 = new MultiTask(editTextName.getText().toString(),iconPath.substring(iconPath.lastIndexOf('/')+1),color);


        String multiTaskKey = userRef.child("multiTasks").push().getKey();
//        userRef.updateChildren(multiTask1.toMap());
        userRef.child("multiTasks").child(multiTaskKey).updateChildren(multiTask1.toMap());
    }
}
