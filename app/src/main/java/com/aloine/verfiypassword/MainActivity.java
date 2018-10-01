package com.aloine.verfiypassword;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AppPinContract.View {
    private Button mButton;
    private AppPinContract.Presenter presenter;
    private EditText pin1, pin2, pin3, pin4, con_pin1, con_pin2, con_pin3, con_pin4;
    private String password1, password2;
    ImageView positive1, positive2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("");
        init();
        presenter = new AppPinPresenter(this);
        presenter.defaultSettings();
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.loadNextScreen();
            }
        });


    }

    private void init() {
        mButton = findViewById(R.id.btn_choose_pin);
        pin1 = findViewById(R.id.newpin_one);
        pin2 = findViewById(R.id.newpin_two);
        pin3  = findViewById(R.id.newpin_three);
        pin4 = findViewById(R.id.newpin_four);
        con_pin1 = findViewById(R.id.confirmpin_one);
        con_pin2 = findViewById(R.id.confirmpin_two);
        con_pin3 = findViewById(R.id.confirmpin_three);
        con_pin4 = findViewById(R.id.confirmpin_four);
        pin1.addTextChangedListener(watcher);
        pin2.addTextChangedListener(watcher);
        pin3.addTextChangedListener(watcher);
        pin4.addTextChangedListener(watcher);
        con_pin1.addTextChangedListener(watcher);
        con_pin2.addTextChangedListener(watcher);
        con_pin3.addTextChangedListener(watcher);
        con_pin4.addTextChangedListener(watcher);
        positive1 = findViewById(R.id.positivechecked1);
        positive2 = findViewById(R.id.positivechecked2);

    }

    @Override
    public void showButtonClick(boolean b) {
        mButton.setEnabled(b);

    }

    @Override
    public void setButtonColor(int color) {
        mButton.setBackground(getResources().getDrawable(color));

    }

    @Override
    public void navigateNextScreen() {
        Toast.makeText(this, "Your intent goes here", Toast.LENGTH_SHORT).show();


    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void showTickVisibility(int value) {
        positive1.setVisibility(value);
        positive2.setVisibility(value);
    }

    TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            EditText editText = (EditText) getCurrentFocus();
            if (editText != null && editText.length() > 0) {
                View next = editText.focusSearch(View.FOCUS_RIGHT);
                if (next != null) {
                    next.requestFocus();
                }

            }
            password1 = presenter.appendIndvidualPassword( pin1.getText().toString(),pin2.getText().toString(),pin3.getText().toString(),pin4.getText().toString());
            password2 = presenter.appendIndvidualPassword(con_pin1.getText().toString(),con_pin2.getText().toString(),con_pin3.getText().toString(),con_pin4.getText().toString());

            if (password1.equals(password2))  {
                presenter.verifyEntries();
                presenter.savePassword(password1);
                return;
            }
            if (!(password1.equals(password2))) {
                presenter.defaultSettings();
                return;
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
