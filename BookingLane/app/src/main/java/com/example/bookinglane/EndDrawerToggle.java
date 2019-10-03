package com.example.bookinglane;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.balysv.materialmenu.MaterialMenuDrawable;


public class EndDrawerToggle implements DrawerLayout.DrawerListener {

    private DrawerLayout drawerLayout;
    //    private DrawerArrowDrawable arrowDrawable;
    private AppCompatImageButton toggleButton;
    private MaterialMenuDrawable arrowDrawableMaterial;
    private String openDrawerContentDesc;
    private String closeDrawerContentDesc;
    private boolean isDrawerOpened = false;

    public EndDrawerToggle(Activity activity, DrawerLayout drawerLayout, Toolbar toolbar,
                           int openDrawerContentDescRes, int closeDrawerContentDescRes) {
        this.drawerLayout = drawerLayout;
        this.openDrawerContentDesc = activity.getString(openDrawerContentDescRes);
        this.closeDrawerContentDesc = activity.getString(closeDrawerContentDescRes);

        arrowDrawableMaterial = new MaterialMenuDrawable(toolbar.getContext(), Color.WHITE, MaterialMenuDrawable.Stroke.THIN);

        toggleButton = new AppCompatImageButton(toolbar.getContext(), null, R.attr.toolbarNavigationButtonStyle);

        toolbar.addView(toggleButton, new Toolbar.LayoutParams(GravityCompat.END));
        toggleButton.setImageDrawable(arrowDrawableMaterial);

        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggle();
            }
        });
    }



    private void toggle() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);
        else drawerLayout.openDrawer(GravityCompat.START);
    }

    @Override
    public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
        arrowDrawableMaterial.setTransformationOffset(MaterialMenuDrawable.AnimationState.BURGER_X,
                drawerLayout.isDrawerOpen(GravityCompat.START) ? 2 - slideOffset : slideOffset);
    }

    @Override
    public void onDrawerOpened(@NonNull View drawerView) {

    }

    @Override
    public void onDrawerClosed(@NonNull View drawerView) {

    }

    @Override
    public void onDrawerStateChanged(int newState) {
        if (newState == DrawerLayout.STATE_IDLE) {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)){
                arrowDrawableMaterial.setIconState(MaterialMenuDrawable.IconState.X);
                toggleButton.setContentDescription(closeDrawerContentDesc);
            }
            else {
                arrowDrawableMaterial.setIconState(MaterialMenuDrawable.IconState.BURGER);
                toggleButton.setContentDescription(openDrawerContentDesc);
            }
        }
    }
}
