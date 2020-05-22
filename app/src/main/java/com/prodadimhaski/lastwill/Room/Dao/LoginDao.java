package com.prodadimhaski.lastwill.Room.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.prodadimhaski.lastwill.Room.entities.Login;

import java.util.List;

@Dao
public interface LoginDao {

    @Insert
    void create(Login login);

    @Delete
    void delete(Login login);

    @Query("SELECT * FROM login")
    List<Login> getAll();

}
