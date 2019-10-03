package com.example.bookinglane.ui.profile;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookinglane.R;
import com.example.bookinglane.activity.RegistrationActivity;
import com.github.pinball83.maskededittext.MaskedEditText;

import static android.content.Context.INPUT_METHOD_SERVICE;

public class EditProfileFragment extends Fragment {

    private ProfileViewModel galleryViewModel;

    private Button buttonEdit, buttonChange;
    private EditText editTextNumber;
    private EditText editTextFullname;

    private EditText et1, et2, et3, et4;
    private EditText[] editTexts;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(ProfileViewModel.class);
        View root = inflater.inflate(R.layout.fragment_edit_profile, container, false);
        init(root);
        return root;
    }

    private void init(View view) {

        editTextFullname = view.findViewById(R.id.fullname_et_profile_edit);
        editTextNumber = view.findViewById(R.id.phone_et_profile_edit);

        if (getArguments() != null) {

            String phone = getArguments().getString("number");
            String fullname = getArguments().getString("fullname");
            editTextNumber.setText(phone);
            editTextFullname.setText(fullname);
        }

        buttonEdit = view.findViewById(R.id.edit_btn_profile_edit);
        buttonChange = view.findViewById(R.id.change_number_btn_profile_edit);


        et1 = view.findViewById(R.id.n1_et_sms_ep);
        et2 = view.findViewById(R.id.n2_et_sms_ep);
        et3 = view.findViewById(R.id.n3_et_sms_ep);
        et4 = view.findViewById(R.id.n4_et_sms_ep);
        editTexts = new EditText[]{et1, et2, et3, et4};


        et1.addTextChangedListener(new PinTextWatcher(0));
        et2.addTextChangedListener(new PinTextWatcher(1));
        et3.addTextChangedListener(new PinTextWatcher(2));
        et4.addTextChangedListener(new PinTextWatcher(3));

        et1.setOnKeyListener(new PinOnKeyListener(0));
        et2.setOnKeyListener(new PinOnKeyListener(1));
        et3.setOnKeyListener(new PinOnKeyListener(2));
        et4.setOnKeyListener(new PinOnKeyListener(3));


        buttonEdit.setOnClickListener(view12 -> {

            Bundle bundle = new Bundle();
            bundle.putString("fullname",editTextFullname.getText().toString());
            bundle.putString("number",editTextNumber.getText().toString());

            Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.nav_profile,bundle);
        });
        buttonChange.setOnClickListener(view1 -> {

            view.findViewById(R.id.sms_code__tv_edit_profile).setVisibility(View.VISIBLE);
            view.findViewById(R.id.resend_tv_edit_profile).setVisibility(View.VISIBLE);
            view.findViewById(R.id.et_holder_edit_profile).setVisibility(View.VISIBLE);
            view.findViewById(R.id.countdown_tv_edit_profile).setVisibility(View.VISIBLE);

        });


        editTextNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() > 8) {
                    buttonChange.setEnabled(true);
                    buttonChange.setBackgroundColor(Color.parseColor("#577DFB"));
                }
            }
        });


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
            if (getActivity().getCurrentFocus() != null) {
                InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
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
}