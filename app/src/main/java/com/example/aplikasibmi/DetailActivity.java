package com.example.aplikasibmi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class DetailActivity extends AppCompatActivity {

    TextView txtnama, txtjk, txthobi, txttinggi, txtberat, txtketerangan;
    Button btnkembali, btnupdate, btndelete;
    RadioGroup radioGroupid;
    CheckBox ck1, ck2, ck3;
    SeekBar stinggi, sberat;
    int t,b,id;
    String namabaru, jkbaru, hobibaru, keterangan;
    RadioButton rm, rf;
    CheckBox c1, c2, c3;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        txtnama = findViewById(R.id.txtnamadetail);
        //txtjk = findViewById(R.id.txtjkdetail);
        //txthobi = findViewById(R.id.txthobidetail);
        txttinggi = findViewById(R.id.tulisantinggidetail);
        txtberat = findViewById(R.id.tulisanberatdetail);
        txtketerangan = findViewById(R.id.txtketerangandetail);
        btnkembali=findViewById(R.id.btnkembalidetail);
        btnupdate=findViewById(R.id.btnupdatedetail);
        btndelete=findViewById(R.id.btndeletedetail);
        stinggi=findViewById(R.id.edittinggi);
        sberat=findViewById(R.id.editberat);
        radioGroupid=findViewById(R.id.rgg);
        rm=findViewById(R.id.rm);
        rf=findViewById(R.id.rf);
        c1=findViewById(R.id.c1);
        c2=findViewById(R.id.c2);
        c3=findViewById(R.id.c3);

        id=getIntent().getIntExtra("id",0);
        String nama = getIntent().getStringExtra("nama");
        String jeniskelamin = getIntent().getStringExtra("jeniskelamin");
        String hobi = getIntent().getStringExtra("hobi");
        String tinggi = getIntent().getStringExtra("tinggi");
        String berat = getIntent().getStringExtra("berat");
        String keterangan1 = getIntent().getStringExtra("keterangan");
        t=Integer.parseInt(tinggi);
        b=Integer.parseInt(berat);



        txtnama.setText(""+nama);
        //txtjk.setText(""+jeniskelamin);
        //txthobi.setText(""+hobi);
        txttinggi.setText("Tinggi (CM) = "+tinggi+" CM");
        txtberat.setText("Berat (KG) = "+berat+" KG");
        txtketerangan.setText(""+keterangan1);
        keterangan=keterangan1;

        //JENIS KELAMIN
        if(jeniskelamin.equals("Perempuan")){
            rf.setChecked(true);
        }else{
            rm.setChecked(true);
        }
        //

        //hobi
        if(hobi.contains("GYM")){
            c1.setChecked(true);
        }
        if(hobi.contains("Lari")){
            c2.setChecked(true);
        }
        if(hobi.contains("Sepeda")){
            c3.setChecked(true);
        }
        //

        //SEEKBAR
        //SEEKBAR TINGGI
        stinggi.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int nilaiawal=0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                nilaiawal=progress;
                txttinggi.setText("Tinggi (CM) = "+nilaiawal+" CM");
                t=nilaiawal;
                cekbmi();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //SEEKBAR BERAT
        sberat.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int nilaiawal=0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                nilaiawal=progress;
                txtberat.setText("Berat (KG) = "+nilaiawal+" KG");
                b=nilaiawal;
                cekbmi();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //

        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder adb = new AlertDialog.Builder(DetailActivity.this);

                adb.setTitle("Apakah anda sudah yakin menghapus?");
                adb.setIcon(android.R.drawable.ic_dialog_alert);
                adb.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        delete(id);
                    }
                });
                adb.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                adb.show();
            }
        });

        //KEMBALI
        btnkembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //button update
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                namabaru=txtnama.getText().toString();
                int idrb = radioGroupid.getCheckedRadioButtonId();
                hobibaru="";
                if(c1.isChecked()){
                    hobibaru=hobibaru+"GYM, ";
                }
                if(c2.isChecked()){
                    hobibaru=hobibaru+"Lari, ";
                }
                if(c3.isChecked()){
                    hobibaru=hobibaru+"Sepeda ";
                }
                switch (idrb){
                    case R.id.rm :
                        jkbaru="Laki-laki";
                        break;

                    case R.id.rf:
                        jkbaru="Perempuan";
                        break;
                }


                update(id,namabaru,jkbaru,hobibaru,t,b,keterangan);
            }
        });
        //
    }

    //UPDATE
    private void update(int id, String nama, String jk, String hobi, int tinggi, int berat, String hasil)
    {
        DBAdapter db=new DBAdapter(this);
        db.openDB();
        long result=db.UPDATE(id,nama, jk, hobi, tinggi, berat, hasil);

        if(result>0)
        {
            Toast.makeText(getApplicationContext(),"berhasil diupdate", Toast.LENGTH_SHORT).show();
            History.hi.finish();
            Intent i=new Intent(getApplicationContext(), History.class);
            startActivity(i);
            this.finish();
        }else
        {
            Toast.makeText(getApplicationContext(),"gagal diupdate", Toast.LENGTH_SHORT).show();
        }

        db.closeDB();
    }
    //

    //DELETE
    private void delete(int id)
    {
        DBAdapter db=new DBAdapter(this);
        db.openDB();
        long result=db.Delete(id);

        if(result>0)
        {
            Toast.makeText(getApplicationContext(),"berhasil dihapus", Toast.LENGTH_SHORT).show();
            History.hi.finish();
            Intent i=new Intent(getApplicationContext(), History.class);
            startActivity(i);
            this.finish();
        }else
        {
            Toast.makeText(getApplicationContext(),"gagal dihapus", Toast.LENGTH_SHORT).show();
        }

        db.closeDB();
    }

    //CHECKBMI
    public void cekbmi(){
        float bmi;

        float faktor=(((float)t * (float)t) / 10000);
        bmi = (float)b / faktor;

        if ( bmi < 18.1 ){
            keterangan = "Under Weight";
        }
        else if( bmi >= 18.1 && bmi <= 23.1 ){
            keterangan = "Normal";
        }
        else if( bmi > 23.1 && bmi <= 28.1 ){
            keterangan = "Over Weight";
        }
        else{
            keterangan = "Obesitas";
        }
        txtketerangan.setText(""+keterangan);
    }
}