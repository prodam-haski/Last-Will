package com.prodadimhaski.lastwill.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.prodadimhaski.lastwill.R;

public class MainActivity extends AppCompatActivity {

    SharedPreferences preferences;
    Button enter;
    EditText passwordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preferences = getSharedPreferences("com.prodadimhaski.lastwill", MODE_PRIVATE);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (preferences.getBoolean("firstrun", true)) {

            Intent intent = new Intent(MainActivity.this,LoginActivity.class);
            startActivity(intent);
            preferences.edit().putBoolean("firstrun", false).apply();
        }
    }

    private void initButton(){
        enter = findViewById(R.id.buttonEnter);
        passwordField = findViewById(R.id.editPassword);

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!passwordField.getText().toString().trim().equals("")) {

                }
                else Toast.makeText(MainActivity.this, R.string.enterPassword, Toast.LENGTH_SHORT).show();

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
