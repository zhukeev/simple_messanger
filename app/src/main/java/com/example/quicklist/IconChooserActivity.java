package com.example.quicklist;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quicklist.adapter.EmojiAdapter;
import com.example.quicklist.utils.OnItemClickListener;
import com.example.quicklist.utils.SpacesItemDecoration;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;

public class IconChooserActivity extends AppCompatActivity {

    private static final String TAG = "IconChooserActivity";
    static ArrayList<File> inFiles = new ArrayList<File>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_icon_chooser);

        init();
    }

    private void init() {
        ContextWrapper cw = new ContextWrapper(IconChooserActivity.this);
        File path = cw.getDir("emojis", Context.MODE_PRIVATE);

        RecyclerView category_rv = findViewById(R.id.icon_choose_rv_icon_chooser);
        category_rv.addItemDecoration(new SpacesItemDecoration(IconChooserActivity.this, 8));
        category_rv.setLayoutManager(new GridLayoutManager(IconChooserActivity.this, 6));
        category_rv.setAdapter(new EmojiAdapter(this, getListFiles(getCacheDir()), new OnItemClickListener() {
            @Override
            public void onItemClickListener(int position) {
                Intent intent = new Intent(IconChooserActivity.this,CreateMultiListActivity.class);
                intent.putExtra("icon",inFiles.get(position));
                startActivity(intent);

            }
        }));




    }

    private List<File> getListFiles(File parentDir) {

        if (!inFiles.isEmpty()){
            return inFiles;
        }

        Queue<File> files = new LinkedList<>(Arrays.asList(Objects.requireNonNull(parentDir.listFiles())));
        while (!files.isEmpty()) {
            File file = files.remove();
            if (file.isDirectory()) {
                files.addAll(Arrays.asList(file.listFiles()));
            } else if (file.getName().endsWith(".png")) {
                inFiles.add(file);
            }
        }
        return inFiles;
    }
}
