package com.example.oil.ui.login;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.oil.R;
import com.example.oil.ui.home.HomeActivity;
import com.example.oil.ui.login.view.ILoginView;
import com.example.oil.ui.login.presenter.ILoginPresenter;
import com.example.oil.ui.login.presenter.LoginPresenter;

public class LoginActivity extends AppCompatActivity implements ILoginView {

    ILoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();

        loginPresenter = new LoginPresenter(this);

    }

    private void init() {

        EditText login_et = findViewById(R.id.login_et_login);
        EditText password_et = findViewById(R.id.password_et_login);


        findViewById(R.id.login_btn_login).setOnClickListener(view -> {

//            loginPresenter.onLogin(login_et.getText().toString(), password_et.getText().toString());

            startActivity(new Intent(LoginActivity.this, HomeActivity.class));


        });
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int) event.getRawX(), (int) event.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }

            }
        }

        return super.dispatchTouchEvent(event);
    }

    @Override
    public void onLoginSuccess(String message) {
        Toast.makeText(this, message + "", Toast.LENGTH_SHORT).show();

        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
        finish();

    }

    @Override
    public void onLoginError(String message) {
        Toast.makeText(this, message + "", Toast.LENGTH_SHORT).show();

    }
}
