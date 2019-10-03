package com.example.bookinglaneadmin.customView;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.animation.Interpolator;

import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.lang.reflect.Field;

public class ViewPagerCustomDuration extends ViewPager {


    private static final String TAG = "ViewPagerCustomDuration";

    public ViewPagerCustomDuration(Context context) {
        super(context);
        postInitViewPager();
    }


    public ViewPagerCustomDuration(Context context, AttributeSet attrs) {
        super(context, attrs);

        postInitViewPager();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        setScrollDurationFactor(2);
        return super.onInterceptTouchEvent(ev);
    }

    private ScrollerCustomDuration mScroller = null;

    /**
     * Override the Scroller instance with our own class so we can change the
     * duration
     */
    private void postInitViewPager() {
        try {
            Field scroller = ViewPager.class.getDeclaredField("mScroller");
            scroller.setAccessible(true);
            Field interpolator = ViewPager.class.getDeclaredField("sInterpolator");
            interpolator.setAccessible(true);

            mScroller = new ScrollerCustomDuration(getContext(),
                    (Interpolator) interpolator.get(null));
            scroller.set(this, mScroller);
        } catch (Exception e) {
        }
    }

    /**
     * Set the factor by which the duration will change
     */
    public void setScrollDurationFactor(double scrollFactor) {
        mScroller.setScrollDurationFactor(scrollFactor);
    }

    @Override
    protected void onPageScrolled(int position, float offset, int offsetPixels) {

        Log.e(TAG, "onPageScrolled: " );

        super.onPageScrolled(position, offset, offsetPixels);
    }

   public TabLayout.OnTabSelectedListener tabSelectedListener = new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            setScrollDurationFactor(5);
            Log.e(TAG, "onTabSelected: " );
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    };
}
