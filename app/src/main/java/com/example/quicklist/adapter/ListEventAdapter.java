package com.example.quicklist.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quicklist.R;
import com.example.quicklist.model.Event;

import java.util.List;

public class ListEventAdapter extends RecyclerView.Adapter<ListEventAdapter.MyViewHolder> {

    Context context;
    List<Event> events;

    public ListEventAdapter(Context context, List<Event> events) {
        this.events = events;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.event_list_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bind(position);
    }



    @Override
    public int getItemCount() {
        return events.size();
    }




    public void seteEvenList(List<Event> evenList) {
        this.events = evenList;
        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textViewTitle;
        TextView textViewDateTime;
        CardView cardView;
        View roundView;
        FrameLayout front_fl;

        public MyViewHolder(@NonNull final View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.event_icon);
            textViewDateTime = itemView.findViewById(R.id.datetime_event_item);
            textViewTitle = itemView.findViewById(R.id.title_event_item);
            cardView = itemView.findViewById(R.id.event_card);
            roundView = itemView.findViewById(R.id.round_holder_fl);
            front_fl = itemView.findViewById(R.id.front);

        }

        public void bind(final int position) {
            textViewTitle.setText(events.get(position).getTitle());
            textViewDateTime.setText(events.get(position).getDatetime());
            imageView.setImageResource(events.get(position).getImage_url());
            front_fl.setBackgroundColor(Color.parseColor(events.get(position).getBg_color()));

        }
    }

}
