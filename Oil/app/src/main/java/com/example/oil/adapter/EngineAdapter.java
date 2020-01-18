package com.example.oil.adapter;

import android.content.Context;
import android.util.Log;
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
import com.example.oil.model.Engine;
import com.example.oil.utils.Interfaces;

import java.util.ArrayList;
import java.util.List;

public class EngineAdapter extends RecyclerView.Adapter<EngineAdapter.MyViewHolder> implements Filterable {

    Context context;
    Interfaces.OnEngineClickListener engineClickListener;
    static List<Engine> enginesFiltered = new ArrayList<>();
    static List<Engine> engines = new ArrayList<>();

    public EngineAdapter(Context context, List<Engine> engines, Interfaces.OnEngineClickListener engineClickListener) {
        this.context = context;
        this.enginesFiltered = engines;
        this.engines = engines;
        this.engineClickListener = engineClickListener;

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

        holder.edit_btn.setOnClickListener(view -> engineClickListener.onItemClickListener(position, enginesFiltered.get(position)));

    }

    @Override
    public int getItemCount() {
        return enginesFiltered.size();
    }

    public static void setEnginesFiltered(List<Engine> enginesFiltered) {
        EngineAdapter.enginesFiltered = enginesFiltered;
        EngineAdapter.engines = enginesFiltered;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();

                if (charString.isEmpty()) {
                    enginesFiltered = engines;
                } else {
                    List<Engine> filteredList = new ArrayList<>();
                    for (Engine engine : engines) {
                        if (engine.getType().toLowerCase().contains(charString.toLowerCase())){
                            filteredList.add(engine);
                        }
                    }

                    enginesFiltered = filteredList;
                }

                FilterResults filterResults  = new FilterResults();
                filterResults.values = enginesFiltered;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                enginesFiltered  = (List<Engine>) filterResults.values;
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

            Log.e("TAG", "bind:" + enginesFiltered.get(position).toString());

            type_fuel_tv.setText(
                    enginesFiltered.get(position).getType() + " / " + enginesFiltered.get(position).getFuel());


        }

    }

}
