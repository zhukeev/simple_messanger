package com.example.bookinglane.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.bookinglane.Constants;
import com.example.bookinglane.R;
import com.example.bookinglane.model.RequestNotification;
import com.example.bookinglane.model.SendNotificationModel;
import com.example.bookinglane.retrofit.NetworkService;
import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;
import com.rm.rmswitch.RMSwitch;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    RMSwitch rmSwitchFingerprint, rmSwitchRemeberMe;
    RelativeLayout relativeLayout;
    LottieAnimationView imageViewLogo;
    EditText editTextLogin;
    EditText editTextPassword;
    TextView textViewRegistration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        FirebaseApp.initializeApp(this);
        init();
        startAnimation();

    }

    private void startAnimation() {

        Display display = getWindowManager().getDefaultDisplay();
        final Point size = new Point();
        display.getSize(size);

        new Thread(() -> {

            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            relativeLayout.animate()
                    .alpha(1.0f)
                    .setDuration(1000)
                    .setListener(null);

            runOnUiThread(() -> {
                relativeLayout.setVisibility(View.VISIBLE);
            });


        }).start();

        new Thread(() -> {

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            moveImageView(imageViewLogo, (-size.y / 2) + 400, 500);

        }).start();
    }

    private void init() {


        //region Initialize all components

        relativeLayout = findViewById(R.id.login_fields_layout);
        imageViewLogo = findViewById(R.id.animated_logo_lottie_view_login);
        editTextLogin = findViewById(R.id.login_et_login);
        editTextPassword = findViewById(R.id.password_et_login);

        textViewRegistration = findViewById(R.id.registration_tv_login);


        relativeLayout.setVisibility(View.INVISIBLE);
        relativeLayout.setAlpha(0.0f);
        //endregion


        textViewRegistration.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);

            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            PendingIntent pendingIntent =
                    PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);
            try {
                pendingIntent.send();
            } catch (PendingIntent.CanceledException e) {
                e.printStackTrace();
            }


//            intent.putExtra(Constants.REGISTRATION,1);
//            startActivity(intent);
        });

        editTextLogin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0)
                    editTextLogin.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
                else
                    editTextLogin.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        editTextPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0)
                    editTextPassword.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
                else
                    editTextPassword.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        findViewById(R.id.login_btn_login).setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));


            FirebaseInstanceId.getInstance().getInstanceId()
                    .addOnCompleteListener(task -> {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "getInstanceId failed", task.getException());
                            return;
                        }

                        // Get new Instance ID token
                        String token = task.getResult().getToken();
                        Log.e(TAG, "onComplete: TOKEN " + token);

                        SendNotificationModel sendNotificationModel = new SendNotificationModel("Title Using Retrofit","For Firebase Cloud Functions","default");
                        RequestNotification requestNotification = new RequestNotification();
                        requestNotification.setSendNotificationModel(sendNotificationModel);
                        requestNotification.setToken(token);
                        NetworkService.getInstance().getApi().sendNotificationTo(requestNotification).enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                Log.e(TAG, "onResponse: "+response.raw() );
                                try {
                                    Log.e(TAG, "onResponse: "+response.body().string() );
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {
                                Log.e(TAG, "onFailure: "+t.getMessage() );
                            }
                        });


                    });


        });

    }


    public void moveImageView(View view, float toY, int duration) {
        view.animate()
                .setInterpolator(new AccelerateDecelerateInterpolator())
                .translationY(toY)
                .setDuration(duration);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        if (getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }

        return super.dispatchTouchEvent(ev);
    }
}
