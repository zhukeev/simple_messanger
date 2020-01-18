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
import com.example.oil.ui.manual.kinda_service.pick_class.model.CarService;
import com.example.oil.utils.Interfaces;

import java.util.ArrayList;
import java.util.List;

public class KindOfServiceAdapter extends RecyclerView.Adapter<KindOfServiceAdapter.MyViewHolder> implements Filterable {

    Context context;
    Interfaces.OnItemServiceClickListener onItemClickListener;
    List<CarService> cars = new ArrayList<>();
    List<CarService> filteredCars = new ArrayList<>();

    public KindOfServiceAdapter(Context context, List<CarService> cars, Interfaces.OnItemServiceClickListener onItemClickListener) {
        this.context = context;
        this.cars = cars;
        this.filteredCars = cars;
        this.onItemClickListener = onItemClickListener;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.service_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bind(position);

        holder.edit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClickListener(position, cars.get(position));
            }
        });

    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();

                if (charString.isEmpty()) {
                    filteredCars = cars;
                } else {
                    List<CarService> filteredList = new ArrayList<>();
                    for (CarService engine : cars) {
                        if (engine.getTitle().contains(charString.toLowerCase())) {
                            filteredList.add(engine);
                        }
                    }

                    filteredCars = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredCars;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filteredCars = (List<CarService>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public List<CarService> getFilteredCars() {
        return filteredCars;
    }

    @Override
    public int getItemCount() {
        return filteredCars.size();
    }

    public void setService(List<CarService> cars) {
        this.cars = cars;
        this.filteredCars = cars;
    }

    public void addService(CarService service) {
        filteredCars.add(service);
        notifyDataSetChanged();
    }

    public void addService(CarService service, int position) {
        filteredCars.set(position, service);
        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title_price_tv;
        ImageView edit_btn;

        public MyViewHolder(@NonNull final View itemView) {
            super(itemView);

            title_price_tv = itemView.findViewById(R.id.name_price_tv_service_item);
            edit_btn = itemView.findViewById(R.id.edit_service_item);


        }

        public void bind(final int position) {

            if (cars.get(position).getTitle() == null){

            }else
            title_price_tv.setText(
                    cars.get(position).getTitle() + " / " + cars.get(position).getPrice());


        }

    }

}
