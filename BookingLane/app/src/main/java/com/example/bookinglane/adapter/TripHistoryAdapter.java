package com.example.bookinglane.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookinglane.OnItemClickListener;
import com.example.bookinglane.R;
import com.example.bookinglane.model.TripHistory;

import java.util.ArrayList;
import java.util.List;

public class TripHistoryAdapter extends RecyclerView.Adapter<TripHistoryAdapter.MyViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";
    static List<TripHistory> histories;
    final String url = "https://krista.fashion/uploads/product_images/";
    static Context context;
    final int TYPE_USUAL = 0;
    final int TYPE_RECOMMEND = 1;
    private OnItemClickListener onItemClickListener;


    public TripHistoryAdapter(Context context, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.onItemClickListener = onItemClickListener;

        List<TripHistory> histories = new ArrayList<>();

        histories.add(new TripHistory("12.09.19 / 17:00-18:00", "From: 3605 Parker Rd Corpus Christi", "To: 6358 Lone Wolf Trail Gresham", "$176", "url"));
        histories.add(new TripHistory("12.09.19 / 17:00-18:00", "From: 3605 Parker Rd Corpus Christi", "To: 6358 Lone Wolf Trail Gresham", "$176", "url"));
        histories.add(new TripHistory("12.09.19 / 17:00-18:00", "From: 3605 Parker Rd Corpus Christi", "To: 6358 Lone Wolf Trail Gresham", "$176", "url"));
        histories.add(new TripHistory("12.09.39 / 17:00-18:00", "From: 3605 Parker Rd Corpus Christi", "To: 6358 Lone Wolf Trail Gresham", "$176", "url"));
        histories.add(new TripHistory("12.09.119 / 17:00-18:00", "From: 3605 Parker Rd Corpus Christi", "To: 6358 Lone Wolf Trail Gresham", "$176", "url"));
        histories.add(new TripHistory("12.09.19 / 17:00-18:00", "From: 3605 Parker Rd Corpus Christi", "To: 6358 Lone Wolf Trail Gresham", "$176", "url"));
        histories.add(new TripHistory("12.09.39 / 17:00-18:00", "From: 3605 Parker Rd Corpus Christi", "To: 6358 Lone Wolf Trail Gresham", "$176", "url"));
        histories.add(new TripHistory("12.09.19 / 17:00-18:00", "From: 3605 Parker Rd Corpus Christi", "To: 6358 Lone Wolf Trail Gresham", "$176", "url"));
        histories.add(new TripHistory("12.09.129 / 17:00-18:00", "From: 3605 Parker Rd Corpus Christi", "To: 6358 Lone Wolf Trail Gresham", "$176", "url"));
        histories.add(new TripHistory("12.09.149 / 17:00-18:00", "From: 3605 Parker Rd Corpus Christi", "To: 6358 Lone Wolf Trail Gresham", "$176", "url"));
        histories.add(new TripHistory("12.09.129 / 17:00-18:00", "From: 3605 Parker Rd Corpus Christi", "To: 6358 Lone Wolf Trail Gresham", "$176", "url"));
        histories.add(new TripHistory("12.09.19 / 17:00-18:00", "From: 3605 Parker Rd Corpus Christi", "To: 6358 Lone Wolf Trail Gresham", "$176", "url"));
        histories.add(new TripHistory("12.09.29 / 17:00-18:00", "From: 3605 Parker Rd Corpus Christi", "To: 6358 Lone Wolf Trail Gresham", "$176", "url"));
        histories.add(new TripHistory("12.09.19 / 17:00-18:00", "From: 3605 Parker Rd Corpus Christi", "To: 6358 Lone Wolf Trail Gresham", "$176", "url"));
        histories.add(new TripHistory("12.09.19 / 17:00-18:00", "From: 3605 Parker Rd Corpus Christi", "To: 6358 Lone Wolf Trail Gresham", "$176", "url"));
        histories.add(new TripHistory("12.09.19 / 17:00-18:00", "From: 3605 Parker Rd Corpus Christi", "To: 6358 Lone Wolf Trail Gresham", "$176", "url"));
        histories.add(new TripHistory("12.09.19 / 17:00-18:00", "From: 3605 Parker Rd Corpus Christi", "To: 6358 Lone Wolf Trail Gresham", "$176", "url"));
        histories.add(new TripHistory("12.09.19 / 17:00-18:00", "From: 3605 Parker Rd Corpus Christi", "To: 6358 Lone Wolf Trail Gresham", "$176", "url"));
        histories.add(new TripHistory("12.09.19 / 17:00-18:00", "From: 3605 Parker Rd Corpus Christi", "To: 6358 Lone Wolf Trail Gresham", "$176", "url"));


        this.histories = histories;


    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(context).inflate(R.layout.trip_history_list_item, parent, false);


        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.setMargins(16, 16, 16, 16);
        view.setLayoutParams(lp);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        holder.setData(position);

        holder.relativeLayout.setOnClickListener(v -> {
            onItemClickListener.onItemClickListener(position);
        });

    }

    @Override
    public int getItemCount() {
        return histories.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView datetime_textView;
        TextView price_textView;
        TextView from_textView;
        TextView to_textView;
        ImageView imageView;
        RelativeLayout relativeLayout;
//        CardView layoutItem;

        public MyViewHolder(@NonNull final View itemView) {
            super(itemView);

            from_textView = itemView.findViewById(R.id.from_tv_history);
            to_textView = itemView.findViewById(R.id.to_tv_history);
            datetime_textView = itemView.findViewById(R.id.date_tv_history);
            price_textView = itemView.findViewById(R.id.price_tv_history);
            imageView = itemView.findViewById(R.id.trip_image);
            relativeLayout = itemView.findViewById(R.id.trip_item_layout);
        }

        void setData(int position) {
            //region Set text and pic
            datetime_textView.setText(histories.get(position).getDate_time());
            imageView.setImageResource(R.drawable.trip_history_map);
            price_textView.setText(histories.get(position).getPrice());
            to_textView.setText(histories.get(position).getTo());
            from_textView.setText(histories.get(position).getFrom());


            //endregion
        }


    }


}
