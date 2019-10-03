package com.example.bookinglaneadmin.utils;

import android.view.animation.Animation;
import android.view.animation.Transformation;

import com.example.bookinglaneadmin.customView.CustomSeekBar;

public class ProgressBarAnimation extends Animation {

    CustomSeekBar seekBar;
    float to;
    float from;

    public ProgressBarAnimation(CustomSeekBar seekBar, float to, float from) {
        this.seekBar = seekBar;
        this.to = to;
        this.from = from;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
        float value = 0 + (to+0) * interpolatedTime;

        seekBar.setProgress((int)value);
    }
}
