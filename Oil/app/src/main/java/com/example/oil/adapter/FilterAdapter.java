package com.example.oil.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oil.R;
import com.example.oil.model.Filter;
import com.example.oil.utils.Interfaces;

import java.util.ArrayList;
import java.util.List;

public class FilterAdapter extends RecyclerView.Adapter<FilterAdapter.MyViewHolder> {

    Context context;
    Interfaces.OnItemClickListener onItemClickListener;
    static List<Filter> filters = new ArrayList<>();

    public FilterAdapter(Context context, List<Filter> filters, Interfaces.OnItemClickListener onItemClickListener) {
        this.context = context;
        this.filters = filters;
        this.onItemClickListener = onItemClickListener;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.filter_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bind(position);

    }

    @Override
    public int getItemCount() {
        return filters.size();
    }

    public static void setFilters(List<Filter> filters) {
        FilterAdapter.filters = filters;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView title_tv;
        TextView models_tv;
        TextView engines_tv;
        Button edit_btn;

        public MyViewHolder(@NonNull final View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image_filter_item);
            title_tv = itemView.findViewById(R.id.title_tv_filter_item);
            models_tv = itemView.findViewById(R.id.models_tv_filter_item);
            engines_tv = itemView.findViewById(R.id.engines_tv_filter_item);
            edit_btn = itemView.findViewById(R.id.edit_btn_filter_item);

        }

        public void bind(final int position) {

            title_tv.setText(filters.get(position).getTitle());
            models_tv.setText(Html.fromHtml("<b>Для моделей: </b>" + filters.get(position).getModels()));
            engines_tv.setText(Html.fromHtml("<b>Для двигателей: </b>" + filters.get(position).getEngines()));

        }

    }

}
