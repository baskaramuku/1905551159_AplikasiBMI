package com.example.aplikasibmi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Hasil extends AppCompatActivity {

    TextView txtnama, txtjk, txthobi, txttinggi, txtberat, txtketerangan;
    Button btnkembali, btnkehsitory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil);
        txtnama = findViewById(R.id.txtnamahasil);
        txtjk = findViewById(R.id.txtjkhasil);
        txthobi = findViewById(R.id.txthobihasil);
        txttinggi = findViewById(R.id.tulisantinggihasil);
        txtberat = findViewById(R.id.tulisanberathasil);
        txtketerangan = findViewById(R.id.txtketeranganhasil);
        btnkembali=findViewById(R.id.btnkembali);
        btnkehsitory=findViewById(R.id.btnhistory);


        String nama = getIntent().getStringExtra("nama");
        String jeniskelamin = getIntent().getStringExtra("jeniskelamin");
        String hobi = getIntent().getStringExtra("hobi");
        String tinggi = getIntent().getStringExtra("tinggi");
        String berat = getIntent().getStringExtra("berat");
        String keterangan = getIntent().getStringExtra("keterangan");


        txtnama.setText(""+nama);
        txtjk.setText(""+jeniskelamin);
        txthobi.setText(""+hobi);
        txttinggi.setText("Tinggi (CM) = "+tinggi+" CM");
        txtberat.setText("Berat (KG) = "+berat+" KG");
        txtketerangan.setText(""+keterangan);

        btnkembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnkehsitory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(), History.class);
                startActivity(i);
            }
        });
    }



    //method onDestroy()
    @Override
    protected void onDestroy() {

        super.onDestroy();
        //Toast.makeText(getApplicationContext(),"activity hasil ditutup", Toast.LENGTH_SHORT).show();
    }
}