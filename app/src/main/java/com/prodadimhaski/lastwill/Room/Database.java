package com.prodadimhaski.lastwill.Room;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;


import com.prodadimhaski.lastwill.Room.Dao.LoginDao;
import com.prodadimhaski.lastwill.Room.Dao.WillsDao;
import com.prodadimhaski.lastwill.Room.entities.Login;
import com.prodadimhaski.lastwill.Room.entities.Wills;
import com.prodadimhaski.lastwill.dbhelper.DatabaseHelper;

import java.io.File;

@androidx.room.Database(entities = {Login.class, Wills.class}, version = 1, exportSchema = false)
public abstract class Database extends RoomDatabase {
    private static Database instance;

    public abstract LoginDao loginDao();
    public abstract WillsDao willsDao();

    public static synchronized Database getInstance(Context context)  {
        if (instance == null) {
            try {
                DatabaseHelper databaseHelper = new DatabaseHelper(context);
                databaseHelper.create_db();

                instance = Room.databaseBuilder(context.getApplicationContext(),
                        Database.class,
                        "AppDB.db")
                        .createFromFile(new File(DatabaseHelper.DB_PATH))
                        .allowMainThreadQueries()
                        .build();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return instance;
    }
}
