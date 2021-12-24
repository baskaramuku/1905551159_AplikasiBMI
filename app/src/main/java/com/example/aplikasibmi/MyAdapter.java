package com.example.aplikasibmi;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyHolder> {
    Context c;
    ArrayList<Pengguna> penggunas;

    public MyAdapter(Context c, ArrayList<Pengguna> penggunas) {
        this.c = c;
        this.penggunas = penggunas;
    }

    //INITIALIZE VIEWHODER
    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //VIEW OBJ
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_history,null);

        //HOLDER
        MyHolder holder=new MyHolder(v);

        return holder;
    }

    //BIND VIEW TO DATA
    @Override
    public void onBindViewHolder(MyHolder holder, int position) {


        holder.txtnama.setText("Nama : "+penggunas.get(position).getNama());
        holder.txtjk.setText("Jenis Kelamin : "+penggunas.get(position).getJk());
        holder.txthobi.setText("Hobi : "+penggunas.get(position).getHobi());
        holder.txttinggi.setText("Tinggi : "+penggunas.get(position).getTinggi()+" Cm");
        holder.txtberat.setText("Berat : "+penggunas.get(position).getBerat() +" Kg");
        holder.txthasil.setText("Hasil : "+penggunas.get(position).getHasil());

        //CLICKED
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {

                //Toast.makeText(v.getContext(),""+penggunas.get(pos).getId()+" "+penggunas.get(pos).getNama(), Toast.LENGTH_LONG).show();
                //OPEN DETAIL ACTIVITY
                //PASS DATA
                //kirim data ke activity baru
                Intent intent = new Intent(c,DetailActivity.class);
                intent.putExtra("id",penggunas.get(pos).getId());
                intent.putExtra("nama",penggunas.get(pos).getNama());
                intent.putExtra("jeniskelamin", penggunas.get(pos).getJk());
                intent.putExtra("hobi", penggunas.get(pos).getHobi());
                intent.putExtra("tinggi", ""+penggunas.get(pos).getTinggi());
                intent.putExtra("berat", ""+penggunas.get(pos).getBerat());
                intent.putExtra("keterangan", penggunas.get(pos).getHasil());
                c.startActivity(intent);

                /*
                //CREATE INTENT
                Intent i=new Intent(c,DetailActivity.class);

                //LOAD DATA
                i.putExtra("NAME",players.get(pos).getName());
                i.putExtra("POSITION",players.get(pos).getPosition());
                i.putExtra("ID",players.get(pos).getId());

                //START ACTIVITY
                c.startActivity(i);
                */

            }
        });

    }

    @Override
    public int getItemCount() {
        return penggunas.size();
    }
}
