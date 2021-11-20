package fr.snooker4real.playlistyoutube.dao;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DAO {

    protected SQLiteDatabase db = null;
    private SQLiteOpenHelper baseHelper = null;

    public DAO(SQLiteOpenHelper baseHelper) {
        this.baseHelper = baseHelper;
    }

    public SQLiteDatabase open() {
        db = baseHelper.getWritableDatabase();

        return db;
    }

    public void close() {
        db.close();
    }

    public SQLiteDatabase getDb(){
        return db;
    }
}
