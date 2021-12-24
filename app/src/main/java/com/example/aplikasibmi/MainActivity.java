package com.example.aplikasibmi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    SeekBar tinggi, berat;
    TextView txttinggi,txtberat;
    Button btnhitung, btnhistori;
    int t,b;
    String nama, jk,hb;
    EditText txtnama;
    RadioGroup radioGroupid;
    RadioButton radioButton;
    CheckBox ck1, ck2, ck3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tinggi=findViewById(R.id.inputtinggi);
        berat=findViewById(R.id.inputberat);
        txttinggi=findViewById(R.id.tulisantinggi);
        txtberat=findViewById(R.id.tulisanberat);
        btnhitung=findViewById(R.id.btnhitung);
        txtnama=findViewById(R.id.inputnama);
        ck1=findViewById(R.id.checkbox1);
        ck2=findViewById(R.id.checkbox2);
        ck3=findViewById(R.id.checkbox3);
        btnhistori=findViewById(R.id.btnhistorymain);

        //radiobutton
        radioGroupid=findViewById(R.id.radioGenderGroup);


        //seekbar tinggi
        tinggi.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int nilaiawal=0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                nilaiawal=progress;
                txttinggi.setText("Tinggi (CM) = "+nilaiawal+" CM");
                t=nilaiawal;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //seekbar berat
        berat.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int nilaiawal=0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                nilaiawal=progress;
                txtberat.setText("Berat (KG) = "+nilaiawal+" KG");
                b=nilaiawal;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        btnhistori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this, History.class);
                startActivity(i);
            }
        });

        //hitung BMI
        btnhitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    float bmi;
                    String keterangan;
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

                                nama=txtnama.getText().toString();
                                int idrb = radioGroupid.getCheckedRadioButtonId();
                                switch (idrb){
                                    case R.id.radio_male :
                                        jk="Laki-laki";
                                        break;

                                    case R.id.radio_female:
                                        jk="Perempuan";
                                        break;
                                }
                                hb="";
                                if(ck1.isChecked()){
                                    hb=hb+"GYM, ";
                                }
                                if(ck2.isChecked()){
                                    hb=hb+"Lari, ";
                                }
                                if(ck3.isChecked()){
                                    hb=hb+"Sepeda ";
                                }

                                //String pesan="Nama = "+nama+"\n"+"Jenis Kelamin = "+jk+"\n"+"Hobi = "+hb+"\nHasil BMI Tes Anda adalah = "+keterangan;
                               // showDialog(pesan);



                    AlertDialog.Builder adb = new AlertDialog.Builder(MainActivity.this);

                    adb.setTitle("Apakah anda sudah yakin dengan data tersebut?");
                    adb.setIcon(android.R.drawable.ic_dialog_alert);
                    adb.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            //SIMPAN DB
                            save(nama, jk,hb,t,b,keterangan);

                            //kirim data ke activity baru
                            Intent intent = new Intent(getApplicationContext(),Hasil.class);
                            intent.putExtra("nama",nama);
                            intent.putExtra("jeniskelamin", jk);
                            intent.putExtra("hobi", hb);
                            intent.putExtra("tinggi", ""+t);
                            intent.putExtra("berat", ""+b);
                            intent.putExtra("keterangan", keterangan);
                            startActivity(intent);



                        }
                    });
                    adb.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    adb.show();



                }catch (Exception e){
                    showDialog("Data Kurang Lengkap"+e);
                }
            }
        });


    }


    private void showDialog(String pesan){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);

        // set title dialog
        alertDialogBuilder.setTitle("Hasil Hitung BMI");

        // set pesan dari dialog
        alertDialogBuilder
                .setMessage(""+pesan)
                .setIcon(R.mipmap.ic_launcher)
                .setCancelable(false)
                .setPositiveButton("Next",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // jika tombol diklik, maka akan menutup activity ini
                        dialog.cancel();
                    }
                });


        // membuat alert dialog dari builder
        AlertDialog alertDialog = alertDialogBuilder.create();

        // menampilkan alert dialog
        alertDialog.show();
    }

    //MENYIMPAN DB
    private void save(String nama, String jk, String hobi, int tinggi, int berat, String hasil)
    {
        DBAdapter db=new DBAdapter(this);

        //OPEN DB
        db.openDB();

        //COMMIT
        long result=db.add(nama, jk, hobi, tinggi, berat, hasil);

        if(result>0)
        {
            Toast.makeText(getApplicationContext(),"Data Berhasil Disimpan", Toast.LENGTH_LONG).show();
        }else
        {
            Toast.makeText(getApplicationContext(),"Data Gagal Disimpan", Toast.LENGTH_LONG).show();
        }

        db.closeDB();
    }


    //method onStart()
    @Override
    protected void onStart() {

        super.onStart();
        Toast.makeText(getApplicationContext(),"aplikasi distart", Toast.LENGTH_SHORT).show();
    }

    //method onDestroy()
    @Override
    protected void onDestroy() {

        super.onDestroy();
       Toast.makeText(getApplicationContext(),"aplikasi ditutup", Toast.LENGTH_SHORT).show();
    }

    //method onResume()
    @Override
    protected void onResume() {

        super.onResume();
        Toast.makeText(getApplicationContext(),"aplikasi dimulai kembali", Toast.LENGTH_SHORT).show();
    }

    //method onPause()
    @Override
    protected void onPause() {

        super.onPause();
       Toast.makeText(getApplicationContext(),"aplikasi dipause", Toast.LENGTH_SHORT).show();
    }


}