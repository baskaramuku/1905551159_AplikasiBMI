package com.example.aplikasibmi;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView txtnama, txtjk, txthobi, txttinggi, txtberat, txthasil;
    ItemClickListener itemClickListener;

    public MyHolder(View itemView) {
        super(itemView);

        txtnama= (TextView) itemView.findViewById(R.id.txtnamahistory);
        txtjk= (TextView) itemView.findViewById(R.id.txtjkhistory);
        txthobi= (TextView) itemView.findViewById(R.id.txthobihistory);
        txttinggi= (TextView) itemView.findViewById(R.id.txttinggihistory);
        txtberat= (TextView) itemView.findViewById(R.id.txtberathistory);
        txthasil= (TextView) itemView.findViewById(R.id.txthasilhistory);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        this.itemClickListener.onItemClick(v,getLayoutPosition());
    }

    public void setItemClickListener(ItemClickListener ic)
    {
        this.itemClickListener=ic;

    }
}
