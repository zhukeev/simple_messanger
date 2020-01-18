package com.example.oil.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oil.R;
import com.example.oil.utils.Interfaces;

import java.util.ArrayList;
import java.util.List;

public class CarDetailsAdapter extends RecyclerView.Adapter<CarDetailsAdapter.MyViewHolder> implements Filterable {

    Context context;
    Interfaces.OnPropertyClickListener engineClickListener;
    List<String> propertiesFiltered = new ArrayList<>();
    List<String> properties = new ArrayList<>();

    public CarDetailsAdapter(Context context, Interfaces.OnPropertyClickListener clickListener) {
        this.context = context;
        this.engineClickListener = clickListener;

    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.engines_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bind(position);

        holder.edit_btn.setOnClickListener(view -> engineClickListener.onItemClickListener(position, propertiesFiltered.get(position)));

    }

    @Override
    public int getItemCount() {
        return propertiesFiltered.size();
    }

    public void setProperties(List<String> properties) {
        propertiesFiltered = properties;
        this.properties = properties;
        notifyDataSetChanged();
    }

    public List<String> getPropertiesFiltered() {
        return this.propertiesFiltered;
    }

    public void putProperty(String property, int pos) {
        propertiesFiltered.set(pos, property);
        notifyDataSetChanged();
    }

    public void putProperty(String property) {
        propertiesFiltered.add(property);
        notifyDataSetChanged();
    }


    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();

                if (charString.isEmpty()) {
                    propertiesFiltered = properties;
                } else {
                    List<String> filteredList = new ArrayList<>();
                    for (String engine : properties) {
                        if (engine.toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(engine);
                        }
                    }

                    propertiesFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = propertiesFiltered;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                propertiesFiltered = (List<String>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView type_fuel_tv;
        ImageView edit_btn;

        public MyViewHolder(@NonNull final View itemView) {
            super(itemView);

            type_fuel_tv = itemView.findViewById(R.id.type_fuel_tv_engine_item);
            edit_btn = itemView.findViewById(R.id.edit_engine_item);


        }

        public void bind(final int position) {

            type_fuel_tv.setText(propertiesFiltered.get(position));

        }

    }

}
