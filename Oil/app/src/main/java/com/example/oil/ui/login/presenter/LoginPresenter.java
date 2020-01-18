package com.example.oil.ui.login.presenter;

import com.example.oil.ui.login.view.ILoginView;
import com.example.oil.ui.login.model.User;

public class LoginPresenter implements ILoginPresenter {

    private ILoginView loginView;

    public LoginPresenter(ILoginView loginView) {
        this.loginView = loginView;
    }

    @Override
    public void onLogin(String login, String password) {

        User user = new User(login, password);

        boolean isLoginSuccess = user.isValidData();
        if (isLoginSuccess) {
            loginView.onLoginSuccess("Авториация успешно проведена");
        } else {
            loginView.onLoginError("Не удалось провести авторизацию");
        }

    }
}
