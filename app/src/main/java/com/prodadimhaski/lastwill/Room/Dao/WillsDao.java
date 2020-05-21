package com.prodadimhaski.lastwill.Room.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;


import com.prodadimhaski.lastwill.Room.entities.Wills;

import java.util.List;

@Dao
public interface WillsDao {

    @Query("SELECT * FROM wills")
    List<Wills> getAll();

    @Insert
    void insert(Wills value);

    @Delete
    void delete(Wills value);

}
