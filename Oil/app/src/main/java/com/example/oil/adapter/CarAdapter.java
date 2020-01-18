package com.example.oil.adapter;

import android.content.Context;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oil.R;
import com.example.oil.ui.manual.car_brand.CarBrandActivity;
import com.example.oil.ui.manual.car_brand.model.Car;
import com.example.oil.utils.Interfaces;

import java.util.ArrayList;
import java.util.List;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.MyViewHolder> {

    private Context context;
    private Interfaces.OnItemClickListener onItemClickListener;
    private List<Car> cars = new ArrayList<>();

    public CarAdapter() {
    }

    public CarAdapter(Context context, Interfaces.OnItemClickListener onItemClickListener) {
        this.context = context;
        this.onItemClickListener = onItemClickListener;

    }

    public CarAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.car_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bind(position);

    }

    @Override
    public int getItemCount() {
        return cars.size();
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
        notifyDataSetChanged();
    }

    public List<Car> getCars() {
        return cars;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView title_tv;
        TextView oils_tv;
        TextView volumes_tv;
        ImageView edit_btn;

        public MyViewHolder(@NonNull final View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image_car_item);
            title_tv = itemView.findViewById(R.id.title_tv_car_item);
            oils_tv = itemView.findViewById(R.id.oils_car_tv_car_item);
            volumes_tv = itemView.findViewById(R.id.volumes_tv_car_item);
            edit_btn = itemView.findViewById(R.id.edit_car_item);

        }

        public void bind(final int position) {

            title_tv.setText(
                    cars.get(position).getManufacturer() + " "
                            + cars.get(position).getModel() + " "
                            + cars.get(position).getGeneration()+" "
                            + cars.get(position).getCarBody() + " "
                            + cars.get(position).getYearOfManufacture());
            oils_tv.setText(Html.fromHtml("<b>Масло:\n</b>" +
                    "Mannol Molibden Benzin 10W-40\n" +
                    "SHELL 0W-30 Professional AV-L\n" +
                    "Mobil 1 ESP Formula 5W-30\n" +
                    "Castrol Edge 0w30 502-505"));
            volumes_tv.setText(Html.fromHtml("<b>Заправочные объемы:\n </b>" + "\n" +
                    "1.8 TFSI (CYGA) — 4,5 л\n" +
                    "2.0 TFSI (CDNB) — 4.6 л\n" +
                    "2.0 TDI (CNHA, CZJA, CGLD)\n" +
                    "2.0 TFSI (CDNB, CAEB, CAED,CYNB, CYPA ) — 4.6 л\n" +
                    "2.8 FSI (CCDA) — 6,8 л\n" +
                    "3.0 TDI — 6.4 л "));

        }

    }

}
