package com.example.quicklist.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quicklist.R;
import com.example.quicklist.utils.OnItemClickListener;

import java.io.File;
import java.util.List;

public class ColorListAdapter extends RecyclerView.Adapter<ColorListAdapter.MyViewHolder> {

    Context context;
    List<String> colors;

    OnItemClickListener onItemClickListener;
    private int chosenElement;

    public ColorListAdapter(Context context, List<String> colors, OnItemClickListener onItemClickListener) {
        this.colors = colors;
        this.context = context;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.color_list_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bind(position);
    }


    @Override
    public int getItemCount() {
        return colors.size();
    }


    public void seteEvenList(List<String> emojis) {
        this.colors = emojis;
        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        LinearLayout linearLayout;

        public MyViewHolder(@NonNull final View itemView) {
            super(itemView);

            linearLayout = itemView.findViewById(R.id.color_ll_color_list);

        }

        public void bind(final int position) {

            linearLayout.getBackground().setColorFilter(Color.parseColor(colors.get(position)), PorterDuff.Mode.SRC_ATOP);

            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClickListener(position);
                }
            });

        }
    }

}
