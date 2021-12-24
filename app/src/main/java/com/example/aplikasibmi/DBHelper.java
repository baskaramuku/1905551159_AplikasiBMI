package com.example.aplikasibmi;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, Constans.DB_NAME, null, Constans.DB_VERSION);
    }

    //CREATE TB
    @Override
    public void onCreate(SQLiteDatabase db) {
        try
        {
            db.execSQL(Constans.CREATE_TB);

        }catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //UPGRADE TB
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS "+Constans.TB_NAME);
            onCreate(db);
    }
}
