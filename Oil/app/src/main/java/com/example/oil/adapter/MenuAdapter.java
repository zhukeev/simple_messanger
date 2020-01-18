package com.example.oil.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oil.R;
import com.example.oil.model.Menu;
import com.example.oil.utils.Interfaces;

import java.util.ArrayList;
import java.util.List;


public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MyViewHolder> {

    private static final String TAG = "ListEventAdapter";

    Context context;
    int counter = 0;
    List<Menu> menus = new ArrayList<>();
    Interfaces.OnItemClickListener onItemClickListener;
    private Interfaces.OnItemCreatedListener onItemCreatedListener;
    private int width = 0;


    public MenuAdapter(Context context, List<Menu> menus, Interfaces.OnItemClickListener onItemClickListener) {
        this.context = context;
        this.menus = menus;
        this.onItemClickListener = onItemClickListener;
    }

    public MenuAdapter(Context context, List<Menu> menus, Interfaces.OnItemClickListener onItemClickListener, Interfaces.OnItemCreatedListener listener) {
        this.context = context;
        this.onItemCreatedListener = listener;
        this.menus = menus;
        this.onItemClickListener = onItemClickListener;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_menu_item, parent, false);


        if (onItemCreatedListener != null) {

            this.onItemCreatedListener.getItemWidth(view.getLayoutParams().width);
        }


        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.imageView.setImageResource(menus.get(position).getIcon());
        holder.textViewTitle.setText(menus.get(position).getTitle());



        holder.cardView.setOnClickListener(view -> onItemClickListener.onItemClickListener(position));

        if (position == 3 && counter > 0) {
            holder.linearLayoutCounterHolder.setVisibility(View.VISIBLE);
            holder.textViewCounter.setText(String.valueOf(counter));
        }

    }

    @Override
    public int getItemCount() {
        return menus.size();
    }

    public void OnItemCreateListener(Interfaces.OnItemCreatedListener listener) {
        onItemCreatedListener = listener;
    }

    public void setOrderCount(int count) {
        counter = count;
        notifyDataSetChanged();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textViewTitle;
        LinearLayout linearLayoutCounterHolder;
        TextView textViewCounter;
        CardView cardView;

        public MyViewHolder(@NonNull final View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.main_menu_image_menu_item);
            textViewTitle = itemView.findViewById(R.id.main_menu_text_tv_menu_item);

            textViewCounter = itemView.findViewById(R.id.counter_tv_main_menu);
            linearLayoutCounterHolder = itemView.findViewById(R.id.counter_holder_ll);

            cardView = itemView.findViewById(R.id.main_menu_card_item);

        }

    }

}
