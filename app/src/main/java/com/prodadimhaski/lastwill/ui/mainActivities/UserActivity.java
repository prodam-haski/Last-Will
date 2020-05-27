package com.prodadimhaski.lastwill.ui.mainActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.Toast;

import com.prodadimhaski.lastwill.R;
import com.prodadimhaski.lastwill.Room.Dao.WillsDao;
import com.prodadimhaski.lastwill.Room.Database;
import com.prodadimhaski.lastwill.Room.entities.Wills;
import com.prodadimhaski.lastwill.rvadapters.WillsAdapter;

import java.util.List;

public class UserActivity extends AppCompatActivity {

    private Database db;
    private WillsDao willsDao;

    RecyclerView recyclerView;
    List<Wills> willsList;

    ImageButton buttonAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        initButton();

        db = Database.getInstance(getApplicationContext());
        willsDao = db.willsDao();
        willsList = willsDao.getAll();

        recyclerView = findViewById(R.id.wills);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        final WillsAdapter recyclerAdapter = new WillsAdapter(getApplicationContext(), willsList);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerAdapter.notifyDataSetChanged();

    }

    private void initButton(){
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserActivity.this,NewWillActivity.class);
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
