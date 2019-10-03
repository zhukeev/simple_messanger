package com.example.bookinglaneadmin.ui.home;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookinglaneadmin.R;


public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_upcoming_trip);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        init(root);

        return root;
    }

    private void init(View view) {

        //region RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.addItemDecoration(new VerticalSpaceItemDecoration(getContext(), 8));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerView.setAdapter(new CarListAdapter(getContext(), position -> {
            NavController controller = Navigation.findNavController((getActivity()), R.id.nav_host_fragment);
            controller.popBackStack(R.id.nav_home, true);
            controller.navigate(R.id.nav_car_details);
        }));
        //endregion

        //region Setup Seekbar
        CustomSeekBar seekBarSedan = view.findViewById(R.id.seekbarSedan);
        CustomSeekBar seekBarSuv = view.findViewById(R.id.seekbarSuv);
        CustomSeekBar seekBarBus = view.findViewById(R.id.seekbarBus);
        SeekBar seekBarLimo = view.findViewById(R.id.seekbarLimo);

        seekBarLimo.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Log.e(TAG, "onProgressChanged: " + i);
                Log.e(TAG, "onProgressChanged: thumb " + seekBar.getThumb().getIntrinsicWidth());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        setPercentToSeekbar(seekBarSedan, 0);
        setPercentToSeekbar(seekBarSuv, 0);
        setPercentToSeekbar(seekBarBus, 0);
        setPercentToSeekbar(seekBarLimo, 0);
        //endregion

    }

    private void setPercentToSeekbar(SeekBar seekBar, int progress) {

        float dip = 8f;
        Resources r = getResources();
        float px = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dip,
                r.getDisplayMetrics()
        );

//        Log.e("TAG", "setPercentToSeekbar: " + px);

        int percent = 0;
        int marginImage = 0;
        Drawable thumb = null;
        float tp = (100.0f - 10.0f) / 100.0f;
        switch (seekBar.getId()) {
            case R.id.seekbarSedan:
                thumb = getResources().getDrawable(R.drawable.ic_sedan_thumb);
                percent = Math.round(10.0f + tp * (float) progress);
                break;
            case R.id.seekbarSuv:
                percent = Math.round(10.0f + tp * (float) progress);
                thumb = getResources().getDrawable(R.drawable.ic_suv_thumb);
                break;
            case R.id.seekbarBus:
                percent = Math.round(10.0f + tp * (float) progress);
                thumb = getResources().getDrawable(R.drawable.ic_bus_thumb);
                break;
            case R.id.seekbarLimo:
                percent = Math.round(18.0f + (100.0f - 18.0f) / 100.0f * (float) progress);
                thumb = getResources().getDrawable(R.drawable.ic_limo_thumb);
                break;
        }
//        float tp2 = (100.0f - (thumb.getIntrinsicWidth() >> 1)) / 100.0f;

//        Log.e(TAG, "setPercentToSeekbar: tp2 "+tp2 );
//        Log.e(TAG, "setPercentToSeekbar: w/2 "+(thumb.getMinimumWidth()));
//        percent = Math.round((thumb.getIntrinsicWidth()>>1) + tp2 * (float) progress);

        Log.e(TAG, "setPercentToSeekbar: progress "+progress );



        OutsetDrawable outsetDrawable = new OutsetDrawable(thumb,
                0, 0, thumb.getIntrinsicWidth() + Math.round(px), 0);

//        Log.e(TAG, "setPercentToSeekbar: w " + ((thumb.getIntrinsicWidth() * 2) + Math.round(px)));
        Log.e(TAG, "setPercentToSeekbar: w " + ((outsetDrawable.getIntrinsicWidth() )));
//        Log.e(TAG, "setPercentToSeekbar: px " + Math.round(px));
//        Log.e(TAG, "setPercentToSeekbar: pc " + percent);
        seekBar.setThumb(outsetDrawable);
//        Log.e(TAG, "setPercentToSeekbar: after set " + seekBar.getThumb().getIntrinsicWidth());

//        ProgressBarAnimation animation = new ProgressBarAnimation(seekBar,percent,0);
//        animation.setDuration(1000);
//        seekBar.startAnimation(animation);


        seekBar.setProgress(percent);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e(TAG, "onStart: ");
        getActivity().findViewById(R.id.chevron_back_actionbar).setVisibility(View.INVISIBLE);
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e(TAG, "onStop: ");
        getActivity().findViewById(R.id.chevron_back_actionbar).setVisibility(View.VISIBLE);

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e(TAG, "onResume: ");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e(TAG, "onPause: ");
    }
}