package com.test.aliveCore.persistence;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.test.aliveCore.models.Datum;

@Database(entities = {Datum.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class UserDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "user_db";
    private static UserDatabase instance;

    public static UserDatabase getInstance(final Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    UserDatabase.class,
                    DATABASE_NAME
            ).build();
        }
        return instance;
    }

    public abstract UserDao getUserDao();
}






