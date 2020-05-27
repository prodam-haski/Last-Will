package com.prodadimhaski.lastwill.ui.startActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.prodadimhaski.lastwill.R;
import com.prodadimhaski.lastwill.Room.Dao.LoginDao;
import com.prodadimhaski.lastwill.Room.Database;
import com.prodadimhaski.lastwill.Room.entities.Login;
import com.prodadimhaski.lastwill.ui.mainActivities.UserActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Database db;
    private LoginDao loginDao;

    Button enter;
    Button forgotPassword;
    EditText passwordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initButton();
    }


    private void initButton() {
        enter = findViewById(R.id.buttonEnter);
        forgotPassword = findViewById(R.id.buttonForgotPassword);
        passwordField = findViewById(R.id.editPassword);

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!passwordField.getText().toString().trim().equals("")) {
                    db = Database.getInstance(getApplicationContext());
                    loginDao = db.loginDao();
                    List<Login> logins = loginDao.getAll();
                    if (passwordField.getText().toString().trim().equals(logins.get(0).getPassword())) {
                        Intent intent = new Intent(MainActivity.this, UserActivity.class);
                        startActivity(intent);
                        finish();
                    } else
                        Toast.makeText(MainActivity.this, R.string.wrongPassword, Toast.LENGTH_SHORT).show();

                } else
                    Toast.makeText(MainActivity.this, R.string.enterPassword, Toast.LENGTH_SHORT).show();

            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RecoveryPasswordActivity.class);
                startActivity(intent);
            }
        });
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
