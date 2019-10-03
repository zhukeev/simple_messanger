package com.example.bookinglaneadmin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.bookinglaneadmin.R;
import com.smarteist.autoimageslider.SliderViewAdapter;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.SliderAdapterVH> {

    private Context context;

    public SliderAdapter(Context context) {
        this.context = context;
    }

    private String[] urls = new String[]{
            "https://www.bellaluxurylimo.com/wp-content/uploads/2018/12/bella-super-stretch-limo-4.jpg"
            ,"https://www.bellaluxurylimo.com/wp-content/uploads/2018/12/bella-super-stretch-limo-3.jpg"
            ,"https://www.bellaluxurylimo.com/wp-content/uploads/2018/12/bella-super-stretch-limo-1.jpg"
            ,"https://www.bellaluxurylimo.com/wp-content/uploads/2018/12/bella-super-stretch-limo-2.jpg"
    };

    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.car_details_item, null);
        return new SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, int position) {

        Glide.with(viewHolder.itemView)
                .load(urls [position])

                .into(viewHolder.imageViewBackground);

    }

    @Override
    public int getCount() {
        //slider view count could be dynamic size
        return urls.length;
    }

    class SliderAdapterVH extends com.smarteist.autoimageslider.SliderViewAdapter.ViewHolder {

        View itemView;
        ImageView imageViewBackground;

        public SliderAdapterVH(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.iv_auto_image_slider);
            this.itemView = itemView;
        }
    }
}