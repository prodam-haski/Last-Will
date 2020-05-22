package com.prodadimhaski.lastwill.ui.startActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.prodadimhaski.lastwill.R;
import com.prodadimhaski.lastwill.Room.Dao.LoginDao;
import com.prodadimhaski.lastwill.Room.Database;
import com.prodadimhaski.lastwill.Room.entities.Login;
import com.prodadimhaski.lastwill.ui.UserActivity;

public class LoginActivity extends AppCompatActivity {

    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferences = getSharedPreferences("com.prodadimhaski.lastwill", MODE_PRIVATE);
        setContentView(R.layout.activity_login);

    }

    @Override
    protected void onResume() {
        super.onResume();

        if (preferences.getBoolean("firstrun", true)) {
            initButton();

        } else {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    Button login;
    EditText passwordField;
    EditText questionField;
    EditText answerField;

    private void initButton() {
        login = findViewById(R.id.buttonLogin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (createAccount()) {
                    Intent intent = new Intent(LoginActivity.this, UserActivity.class);
                    startActivity(intent);
                    preferences.edit().putBoolean("firstrun", false).apply();
                    finish();
                }
            }
        });
    }

    private Database db;
    private LoginDao loginDao;

    private boolean createAccount() {
        passwordField = findViewById(R.id.editLoginPassword);
        questionField = findViewById(R.id.editQuestion);
        answerField = findViewById(R.id.editAnswer);

        if (passwordField.getText().toString().trim().equals("")) {
            Toast.makeText(this, R.string.enterPassword, Toast.LENGTH_SHORT).show();
            return false;
        }
        if (questionField.getText().toString().trim().equals("")) {
            Toast.makeText(this, R.string.specialQuestion, Toast.LENGTH_SHORT).show();
            return false;
        }
        if (answerField.getText().toString().trim().equals("")) {
            Toast.makeText(this, R.string.specialAnswer, Toast.LENGTH_SHORT).show();
            return false;
        } else {
            Login account = new Login(1, passwordField.getText().toString().trim(), questionField.getText().toString().trim(), answerField.getText().toString().trim());
            db = Database.getInstance(getApplicationContext());
            loginDao = db.loginDao();
            loginDao.create(account);
            return true;
        }
    }

    private long backPressedTime;

    @Override
    public void onBackPressed() {

        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            super.onBackPressed();
            return;
        } else {
            Toast.makeText(this, R.string.exit, Toast.LENGTH_SHORT).show();
        }
        backPressedTime = System.currentTimeMillis();
    }
}
