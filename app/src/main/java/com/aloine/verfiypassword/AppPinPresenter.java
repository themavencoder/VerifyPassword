package com.aloine.verfiypassword;

import android.view.View;

public class AppPinPresenter implements AppPinContract.Presenter {
    private AppPinContract.View view;
    private AppPinModel model;

    public AppPinPresenter(AppPinContract.View view) {
        this.view = view;
        model = new AppPinModel();

    }

    @Override
    public void loadNextScreen() {
        view.navigateNextScreen();
    }

    @Override
    public void defaultSettings() {
        view.setButtonColor(R.drawable.btn_ash);
        view.showButtonClick(false);
        view.showTickVisibility(View.INVISIBLE);

    }

    @Override
    public void verifyEntries() {
        view.setButtonColor(R.drawable.btn_red);
        view.showButtonClick(true);
        view.showTickVisibility(View.VISIBLE);

    }




    @Override
    public void savePassword(String password) {
        model.setPassword(password);

    }

    @Override
    public String appendIndvidualPassword(String first, String second, String third, String fourth) {
        StringBuilder sb = new StringBuilder();
        sb.append(first).append(second).append(third).append(fourth);
        return sb.toString();
    }
}
