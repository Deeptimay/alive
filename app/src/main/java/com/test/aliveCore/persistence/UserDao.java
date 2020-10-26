package com.quick.buku.persistence;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.quick.buku.models.Datum;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM users")
    LiveData<List<Datum>> getAll();

    @Query("SELECT * FROM users WHERE firstName LIKE :name LIMIT 1")
    Datum findByName(String name);

    @Insert
    void insertAll(List<Datum> users);

    @Query("SELECT  COUNT(DISTINCT id) FROM users")
    Integer getTotalNumberOfColumns();

    @Insert
    void insert(Datum user);

    @Update
    void update(Datum user);

    @Delete
    void delete(Datum user);
}
