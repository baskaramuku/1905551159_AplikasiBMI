package com.example.aplikasibmi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBAdapter {
    Context c;

    SQLiteDatabase db;
    DBHelper helper;

    public DBAdapter(Context c) {
        this.c = c;
        helper=new DBHelper(c);
    }

    //OPEN DATABASE
    public DBAdapter openDB()
    {
        try {
            db=helper.getWritableDatabase();

        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return this;
    }

    //CLOSE DATABASE
    public void closeDB()
    {
        try {
            helper.close();

        }catch (SQLException e)
        {
            e.printStackTrace();
        }

    }

    //INSERT
    public long add(String nama, String jk, String hobi, int tinggi, int berat, String hasil)
    {
        try
        {
            ContentValues cv=new ContentValues();
            cv.put(Constans.NAMA,nama);
            cv.put(Constans.JK, jk);
            cv.put(Constans.HOBI, hobi);
            cv.put(Constans.TINGGI, tinggi);
            cv.put(Constans.BERAT, berat);
            cv.put(Constans.HASIL, hasil);

            return db.insert(Constans.TB_NAME,Constans.ROW_ID,cv);
        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return 0;
    }

    public Cursor getAllPlayers()
    {
        String[] columns={Constans.ROW_ID,Constans.NAMA,Constans.JK,Constans.HOBI,Constans.TINGGI,Constans.BERAT,Constans.HASIL};

        return db.query(Constans.TB_NAME,columns,null,null,null,null,null);

    }

    //UPDATE
    public long UPDATE(int id, String nama, String jk, String hobi, int tinggi, int berat, String hasil)
    {
        try
        {
            ContentValues cv=new ContentValues();
            cv.put(Constans.NAMA,nama);
            cv.put(Constans.JK, jk);
            cv.put(Constans.HOBI, hobi);
            cv.put(Constans.TINGGI, tinggi);
            cv.put(Constans.BERAT, berat);
            cv.put(Constans.HASIL, hasil);

            return db.update(Constans.TB_NAME,cv,Constans.ROW_ID+" =?",new String[]{String.valueOf(id)});

        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return 0;
    }

    //DELETE
    public long Delete(int id)
    {
        try
        {

            return db.delete(Constans.TB_NAME,Constans.ROW_ID+" =?",new String[]{String.valueOf(id)});

        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return 0;
    }


}
