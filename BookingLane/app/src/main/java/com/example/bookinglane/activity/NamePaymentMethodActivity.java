package com.example.bookinglane.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;

import com.airbnb.lottie.LottieAnimationView;
import com.example.bookinglane.R;

import douglasspgyn.com.github.circularcountdown.CircularCountdown;
import douglasspgyn.com.github.circularcountdown.listener.CircularListener;

public class NamePaymentMethodActivity extends AppCompatActivity {

    Button buttonNext;
    CircularCountdown countdown;
    LottieAnimationView lottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_payment_method);

        init();
    }

    private void init() {
        buttonNext = findViewById(R.id.next_btn_payment);
        countdown = findViewById(R.id.circularCountdown);
        findViewById(R.id.thank_you_layout_payment).setVisibility(View.GONE);
        findViewById(R.id.payment_method_name_content_layout).setVisibility(View.VISIBLE);

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findViewById(R.id.payment_method_name_content_layout).setVisibility(View.GONE);
                findViewById(R.id.thank_you_layout_payment).setVisibility(View.VISIBLE);


                countdown.create(0,3, CircularCountdown.TYPE_SECOND)
                        .listener(new CircularListener() {
                            @Override
                            public void onTick(int i) {

                            }

                            @Override
                            public void onFinish(boolean b, int i) {
                                Intent intent =new Intent(NamePaymentMethodActivity.this,MainActivity.class);

                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                                finishAndRemoveTask();
                            }
                        }).start();





            }
        });
    }


    @Override
    protected void onStop() {
        super.onStop();
        countdown.stop();

    }
}
