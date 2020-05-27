package com.prodadimhaski.lastwill.ui.startActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.prodadimhaski.lastwill.R;
import com.prodadimhaski.lastwill.Room.Dao.LoginDao;
import com.prodadimhaski.lastwill.Room.Database;
import com.prodadimhaski.lastwill.Room.entities.Login;

import java.util.List;

public class RecoveryPasswordActivity extends AppCompatActivity {

    Button buttonConfirm;
    EditText newPasswordField;
    EditText answerField;
    TextView questionField;

    private Database db;
    private LoginDao loginDao;
    List<Login> loginList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recovery_password);

        db = Database.getInstance(getApplicationContext());
        loginDao = db.loginDao();
        loginList = loginDao.getAll();

        initButton();
    }


    private void initButton() {
        newPasswordField = findViewById(R.id.editNewPassword);
        answerField = findViewById(R.id.editAnswerQuestion);
        questionField = findViewById(R.id.textQuestion);
        buttonConfirm = findViewById(R.id.buttonConfirmPassword);

        questionField.setText(loginList.get(0).getQuestion());

        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answerField.getText().toString().trim().equals(loginList.get(0).getAnswer())) {
                    if (!newPasswordField.getText().toString().trim().equals("")) {
                        changePassword(newPasswordField.getText().toString().trim());
                        finish();
                    } else
                        Toast.makeText(RecoveryPasswordActivity.this, R.string.newPassword, Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(RecoveryPasswordActivity.this, R.string.wrongAnswer, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void changePassword(String newPassword) {
        loginDao.delete(loginList.get(0));
        Login login = new Login(1, newPassword, loginList.get(0).getQuestion(), loginList.get(0).getAnswer());
        loginDao.create(login);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
