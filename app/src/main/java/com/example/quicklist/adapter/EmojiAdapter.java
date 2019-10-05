package com.example.quicklist.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quicklist.R;
import com.example.quicklist.utils.OnItemClickListener;

import java.io.File;
import java.util.List;

public class EmojiAdapter extends RecyclerView.Adapter<EmojiAdapter.MyViewHolder> {

    Context context;
    List<File> emojis;

    OnItemClickListener onItemClickListener;
    private int chosenElement;

    public EmojiAdapter(Context context, List<File> emojis, OnItemClickListener onItemClickListener) {
        this.emojis = emojis;
        this.context = context;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.emoji_list_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bind(position);
    }


    @Override
    public int getItemCount() {
        return emojis.size();
    }


    public void seteEvenList(List<File> emojis) {
        this.emojis = emojis;
        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        FrameLayout select_bg;

        public MyViewHolder(@NonNull final View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.emoji_iv_emoji_item);
            select_bg = itemView.findViewById(R.id.selection_bg_emoji_list);

        }

        public void bind(final int position) {
            imageView.setImageURI(Uri.fromFile(emojis.get(position)));

            if (chosenElement == getAdapterPosition()) {
                select_bg.setBackground(context.getDrawable(R.drawable.round_with_blue_stroke));
            } else {
                select_bg.setBackground(null);
            }

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Toast.makeText(context, position + "", Toast.LENGTH_SHORT).show();
                    onItemClickListener.onItemClickListener(position);
                    chosenElement = getAdapterPosition();
                    notifyDataSetChanged();

                }
            });

        }
    }

}
