package com.example.bookinglane.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.bookinglane.R;
import com.github.pinball83.maskededittext.MaskedEditText;

public class RegistrationActivity extends AppCompatActivity {

    private MaskedEditText phone_number_et;
    private TextView next_tv;

    private EditText et1, et2, et3, et4;
    private EditText[] editTexts;
    private Button buttonNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        init();
    }

    private void showKeyboard() {

        InputMethodManager imgr = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imgr.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    private void init() {

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        buttonNext = findViewById(R.id.next_btn_registration);
        phone_number_et = findViewById(R.id.phone_number_et_registration);

        buttonNext.setOnClickListener(view1 -> {
            startActivity(new Intent(this,NamePaymentMethodActivity.class));
        });

        phone_number_et.post(() -> {
            phone_number_et.requestFocus();
            showKeyboard();
        });


        et1 = findViewById(R.id.n1_et_sms);
        et2 = findViewById(R.id.n2_et_sms);
        et3 = findViewById(R.id.n3_et_sms);
        et4 = findViewById(R.id.n4_et_sms);

        editTexts = new EditText[]{et1, et2, et3, et4};


        et1.addTextChangedListener(new PinTextWatcher(0));
        et2.addTextChangedListener(new PinTextWatcher(1));
        et3.addTextChangedListener(new PinTextWatcher(2));
        et4.addTextChangedListener(new PinTextWatcher(3));

        et1.setOnKeyListener(new PinOnKeyListener(0));
        et2.setOnKeyListener(new PinOnKeyListener(1));
        et3.setOnKeyListener(new PinOnKeyListener(2));
        et4.setOnKeyListener(new PinOnKeyListener(3));

    }

    public class PinTextWatcher implements TextWatcher {

        private int currentIndex;
        private boolean isFirst = false, isLast = false;
        private String newTypedString = "";

        PinTextWatcher(int currentIndex) {
            this.currentIndex = currentIndex;

            if (currentIndex == 0)
                this.isFirst = true;
            else if (currentIndex == editTexts.length - 1)
                this.isLast = true;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            newTypedString = s.subSequence(start, start + count).toString().trim();
        }

        @Override
        public void afterTextChanged(Editable s) {

            String text = newTypedString;

            /* Detect paste event and set first char */
            if (text.length() > 1)
                text = String.valueOf(text.charAt(0)); // TODO: We can fill out other EditTexts

            editTexts[currentIndex].removeTextChangedListener(this);
            editTexts[currentIndex].setText(text);
            editTexts[currentIndex].setSelection(text.length());
            editTexts[currentIndex].addTextChangedListener(this);

            if (text.length() == 1)
                moveToNext();
            else if (text.length() == 0)
                moveToPrevious();
        }

        private void moveToNext() {
            if (!isLast)
                editTexts[currentIndex + 1].requestFocus();

            if (isAllEditTextsFilled() && isLast) { // isLast is optional
                editTexts[currentIndex].clearFocus();
                hideKeyboard();
//                startActivity(new Intent(SmsCodeActivity.this,PaymentMethodActivity.class));
            }
        }

        private void moveToPrevious() {
            if (!isFirst)
                editTexts[currentIndex - 1].requestFocus();
        }

        private boolean isAllEditTextsFilled() {
            for (EditText editText : editTexts)
                if (editText.getText().toString().trim().length() == 0)
                    return false;
            return true;
        }

        private void hideKeyboard() {
            if (getCurrentFocus() != null) {
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            }
        }

    }
    public class PinOnKeyListener implements View.OnKeyListener {

        private int currentIndex;

        PinOnKeyListener(int currentIndex) {
            this.currentIndex = currentIndex;
        }

        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            if (keyCode == KeyEvent.KEYCODE_DEL && event.getAction() == KeyEvent.ACTION_DOWN) {
                if (editTexts[currentIndex].getText().toString().isEmpty() && currentIndex != 0)
                    editTexts[currentIndex - 1].requestFocus();
            }
            return false;
        }

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
