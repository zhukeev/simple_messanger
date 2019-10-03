package com.example.bookinglane.adapter;

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
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookinglane.OnItemClickListener;
import com.example.bookinglane.R;
import com.example.bookinglane.model.Car;
import com.willy.ratingbar.BaseRatingBar;

import java.util.ArrayList;
import java.util.List;

public class CarListAdapter extends RecyclerView.Adapter<CarListAdapter.MyViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";
    static List<Car> car;
    final String url = "https://krista.fashion/uploads/product_images/";
    static Context context;
    final int TYPE_USUAL = 0;
    final int TYPE_RECOMMEND = 1;
    private OnItemClickListener onItemClickListener;


    public CarListAdapter(Context context, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.onItemClickListener = onItemClickListener;

        List<Car> cars = new ArrayList<>();

        cars.add(new Car("Limousine 1", "comfortable 2 limo", 4, "$80", true));
        cars.add(new Car("Limousine 2", "comfortable 3 limo", 3, "$45"));
        cars.add(new Car("Limousine 3", "comfortable 4 limo", 4.5f, "$20"));
        cars.add(new Car("Limousine 4 ", "comfortable 5 limo", 2.2f, "$87", true));
        cars.add(new Car("Limousine 4 ", "comfortable 5 limo", 2.2f, "$37"));
        cars.add(new Car("Limousine 4 ", "comfortable 5 limo", 2.2f, "$87"));
        cars.add(new Car("Ham 4 ", "comfortable 5 limo", 2.2f, "$87"));
        cars.add(new Car("Hamasd 4 ", "comfortable 5 limo", 2.2f, "$87"));
        cars.add(new Car("Ham das4 ", "comfortable 5 limo", 2.2f, "$87"));
        cars.add(new Car("Ham as4 ", "comfortable 5 limo", 2.2f, "$87"));
        cars.add(new Car("Ham 4 ", "comfortable 5 limo", 2.2f, "$87"));
        cars.add(new Car("Ham ada4 ", "comfortable 5 limo", 2.2f, "$87"));
        cars.add(new Car("Ham 4 ", "comfortable 5 limo", 2.2f, "$87"));
        cars.add(new Car("Ham 4 ", "comfortable 5 limo", 2.2f, "$87"));
        cars.add(new Car("Hama 4 ", "comfortable 5 limo", 2.2f, "$87"));
        cars.add(new Car("Ham 4 ", "comfortable 5 limo", 2.2f, "$87"));
        cars.add(new Car("Ham 4 ", "comfortable 5 limo", 2.2f, "$87"));
        cars.add(new Car("Ham 4 ", "comfortable 5 limo", 2.2f, "$87"));
        cars.add(new Car("Ham 4 ", "comfortable 5 limo", 2.2f, "$87"));
        cars.add(new Car("Ham 55", "comfortable limo", 5, "$60"));

        this.car = cars;


    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(context).inflate(R.layout.car_list_item, parent, false);


        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.setMargins(8, 16, 8, 16);
        view.setLayoutParams(lp);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        holder.setData(position);

        holder.constraintLayout.setOnClickListener(v -> {
            onItemClickListener.onItemClickListener(position);
        });

    }

    @Override
    public int getItemCount() {
        return car.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title_textView;
        TextView price_textView;
        ImageView imageView;
        ConstraintLayout constraintLayout;
        BaseRatingBar ratingBar;
        FrameLayout price_holder;
//        CardView layoutItem;

        public MyViewHolder(@NonNull final View itemView) {
            super(itemView);

            title_textView = itemView.findViewById(R.id.title_tv);
            price_textView = itemView.findViewById(R.id.price_tv);
            imageView = itemView.findViewById(R.id.car_icon);
            ratingBar = itemView.findViewById(R.id.rating_bar);
            constraintLayout = itemView.findViewById(R.id.car_list_item_c_layout);
            price_holder = itemView.findViewById(R.id.price_holder_frame_layout_list_item);
        }

        void setData(int position) {
            //region Set text and pic
            title_textView.setText(car.get(position).getTitle());
            price_textView.setText(car.get(position).getPrice());
            ratingBar.setRating(car.get(position).getRating());

            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.suv_toyota));

            if (car.get(position).isRecommend()) {
                price_holder.setBackgroundColor(Color.WHITE);
                constraintLayout.setBackground(context.getDrawable(R.drawable.gradient_btn));
                price_textView.setTextColor(Color.BLACK);
                ratingBar.setEmptyDrawable(context.getDrawable(R.drawable.ic_star_white));
            }

            //endregion
        }


    }


}
