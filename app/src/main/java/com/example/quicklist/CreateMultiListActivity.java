package com.example.quicklist;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quicklist.model.EmojiIcon;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CreateMultiListActivity extends AppCompatActivity {

    private static final String TAG = "CreateMultiListActivity";

    private StorageReference mStorageRef;
    private ImageView imageViewIcon;
    private LinearLayout color_ll;
    static List<EmojiIcon> emojiIcons = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_multi_list);

        mStorageRef = FirebaseStorage.getInstance().getReference();

        init();

        getExtras();
    }

    private void getExtras() {
        if (getIntent().hasExtra("icon")) {
            imageViewIcon.setImageURI(Uri.fromFile((File) getIntent().getSerializableExtra("icon")));
        }
        if (getIntent().hasExtra("color")) {
            color_ll.getBackground().setColorFilter(Color.parseColor(getIntent().getStringExtra("color")), PorterDuff.Mode.SRC_ATOP);
        }
    }

    private void init() {


        imageViewIcon = findViewById(R.id.category_icon_multi_list);
        color_ll = findViewById(R.id.color_ll_multi_list);

        StorageReference riversRef = mStorageRef.child("Emojis");

        final ProgressDialog progressDialog = new ProgressDialog(CreateMultiListActivity.this);
        progressDialog.setTitle("Downloading");


        riversRef.listAll().addOnSuccessListener(listResult -> {

            for (final StorageReference item : listResult.getItems()) {

                final File localFile = new File(getCacheDir() + "/" + item.getName());

                    if (localFile.exists()) {
                        return;
                    }

                    item.getFile(localFile).addOnSuccessListener(taskSnapshot -> {

                    emojiIcons.add(new EmojiIcon(item.getPath(),localFile.getAbsolutePath()));

                    Log.e(TAG, "onSuccess: path to file "+item.getName() );


                    if (listResult.getItems().indexOf(item) == listResult.getItems().size()) {
                    }
                    Log.e(TAG, "onSuccess: " + localFile.getName());

                });


            }
        }).addOnFailureListener(e -> Log.e(TAG, "onFailure: " + e.getLocalizedMessage()));


        findViewById(R.id.round_holder_fl).setOnClickListener(view -> startActivity(new Intent(CreateMultiListActivity.this, IconChooserActivity.class)));

        color_ll.setOnClickListener(view -> startActivity(new Intent(CreateMultiListActivity.this, ColorChooserActivity.class)));


    }
}
