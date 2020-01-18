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
import com.example.oil.model.Oil;
import com.example.oil.utils.Interfaces;

import java.util.ArrayList;
import java.util.List;

public class OilAdapter extends RecyclerView.Adapter<OilAdapter.MyViewHolder> {

    Context context;
    Interfaces.OnItemClickListener onItemClickListener;
    static List<Oil> oils = new ArrayList<>();

    public OilAdapter(Context context, List<Oil> oils, Interfaces.OnItemClickListener onItemClickListener) {
        this.context = context;
        this.oils = oils;
        this.onItemClickListener = onItemClickListener;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.oil_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bind(position);

    }

    @Override
    public int getItemCount() {
        return oils.size();
    }

    public void setOils(List<Oil> oils) {
        this.oils = oils;
        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView producer_tv;
        TextView composition_tv;
        TextView viscosity_tv;
        TextView class_oil_tv;
        TextView engine_tv;
        TextView turbo_charged_tv;
        TextView warehouse_tv;
        TextView came_warehouse_tv;
        TextView sold_tv;
        Button edit_btn;

        public MyViewHolder(@NonNull final View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image_oil_item);
            producer_tv = itemView.findViewById(R.id.title_tv_oil_item);
            composition_tv = itemView.findViewById(R.id.composition_tv_oil_item);
            viscosity_tv = itemView.findViewById(R.id.viscosity_tv_oil_item);
            class_oil_tv = itemView.findViewById(R.id.class_tv_oil_item);
            engine_tv = itemView.findViewById(R.id.engine_tv_oil_item);
            turbo_charged_tv = itemView.findViewById(R.id.turbo_charged_tv_oil_item);
            warehouse_tv = itemView.findViewById(R.id.warehouse_tv_oil_item);
            came_warehouse_tv = itemView.findViewById(R.id.came_warehouse_tv_oil_item);
            sold_tv = itemView.findViewById(R.id.sold_tv_oil_item);
            edit_btn = itemView.findViewById(R.id.edit_btn_oil_item);

        }

        public void bind(final int position) {

            producer_tv.setText(oils.get(position).getTitle());
            composition_tv.setText(Html.fromHtml("<b>Состав: </b>" + oils.get(position).getComposition()));
            viscosity_tv.setText(Html.fromHtml("<b>Вязкость: </b>" + oils.get(position).getViscosity()));
            class_oil_tv.setText(Html.fromHtml("<b>Класс: </b>" + oils.get(position).getOilClass()));
//            engine_tv.setText(context.getResources().getString(R.string.engine_bold)+ propertiesFiltered.get(position).getEngine());
            engine_tv.setText(Html.fromHtml("<b>Двигатель: </b>" + context.getResources().getString(R.string.engine_bold)));
            turbo_charged_tv.setText(oils.get(position).isOkForTurboEngine() ? "Подходит для турбированных двигателей " : "Не подходит для турбированных двигателей ");
            warehouse_tv.setText(oils.get(position).getInWarehouse());
            came_warehouse_tv.setText(oils.get(position).getCameToWarehouse());
            sold_tv.setText(oils.get(position).getSold());

        }

    }

}
