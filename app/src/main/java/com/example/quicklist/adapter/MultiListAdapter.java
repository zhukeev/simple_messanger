package com.example.quicklist.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quicklist.R;
import com.example.quicklist.model.MultiTask;
import com.example.quicklist.utils.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class MultiListAdapter extends RecyclerView.Adapter<MultiListAdapter.MyViewHolder> {

    Context context;
    OnItemClickListener onItemClickListener;

    private List<MultiTask> multiTasks = new ArrayList<>();
    public MultiListAdapter(Context context, List<MultiTask> multiTasks, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.onItemClickListener = onItemClickListener;
        this.multiTasks = multiTasks;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.category_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return multiTasks.size() + 1;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        ImageView imageViewAdd;
        TextView textView;
        CardView cardView;
        View roundView;
        public MyViewHolder(@NonNull final View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.category_icon);
            imageViewAdd = itemView.findViewById(R.id.add_item_plus);
            textView = itemView.findViewById(R.id.category_text);
            cardView = itemView.findViewById(R.id.category_card);
            roundView = itemView.findViewById(R.id.round_holder_fl);

        }

        public void bind(final int position) {

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClickListener(position);
                }
            });

            if (position==getItemCount()-1){
                imageViewAdd.setVisibility(View.VISIBLE);
                imageView.setVisibility(View.INVISIBLE);
                textView.setVisibility(View.INVISIBLE);
                roundView.setVisibility(View.INVISIBLE);
                return;
            }
            imageView.setImageResource(multiTasks.get(position).getRes_url_image());
            textView.setText(multiTasks.get(position).getTitle());
        }
    }

}
