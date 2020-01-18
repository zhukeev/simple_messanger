package com.example.oil.utils;

import com.example.oil.ui.manual.kinda_service.pick_class.model.CarService;
import com.example.oil.model.Engine;

public class Interfaces {

    public interface OnItemClickListener {
        void onItemClickListener(int position);
    }

    public interface OnItemServiceClickListener {
        void onItemClickListener(int position, CarService carService);
    }

    public interface OnEngineClickListener {
        void onItemClickListener(int position, Engine engine);
    }

    public interface OnPropertyClickListener {
        void onItemClickListener(int position, String model);
    }

    public interface OnItemCreatedListener {
        void getItemWidth(int width);
    }

}
