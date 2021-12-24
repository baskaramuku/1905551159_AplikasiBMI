package com.example.aplikasibmi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class History extends AppCompatActivity {


    RecyclerView rv;
    MyAdapter adapter;
    ArrayList<Pengguna> penggunas = new ArrayList<>();
    public  static History hi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        hi=this;
        //recycler
        rv = (RecyclerView) findViewById(R.id.recyclerView);

        //SET PROPS
        rv.setLayoutManager(new LinearLayoutManager(this));

        rv.setItemAnimator(new DefaultItemAnimator());

        //ADAPTER
        adapter = new MyAdapter(this, penggunas);
        try {
            //RETRIEVE
            retrieve();
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),""+e,Toast.LENGTH_LONG).show();
        }
    }


    //RETREIEV
    private void retrieve()
    {
        penggunas.clear();

        DBAdapter db=new DBAdapter(this);
        db.openDB();

        //RETRIEVE
        Cursor c=db.getAllPlayers();

        //LOOP AND ADD TO ARRAYLIST
        while (c.moveToNext())
        {
            int id=c.getInt(0);
            String nama=c.getString(1);
            String jk=c.getString(2);
            String hobi=c.getString(3);
            int tinggi=c.getInt(4);
            int berat=c.getInt(5);
            String hasil=c.getString(6);

            Pengguna p=new Pengguna(id,tinggi, berat, nama, jk, hobi, hasil);
            //ADD TO ARRAYLIS
            penggunas.add(p);
        }

        //CHECK IF ARRAYLIST ISNT EMPTY
        if(!(penggunas.size()<1))
        {
            rv.setAdapter(adapter);
        }

        db.closeDB();;

    }

}










