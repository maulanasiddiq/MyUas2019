package com.maulanaabdulsiddiq.myuas2019;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class UasActivity extends AppCompatActivity {

    EditText mNik;
    EditText mNama;
    Spinner mKelas;
    EditText mJam;
    Button btn_simpan;
    Button btn_tampil;

    String[] kelas = {"6A","6B","6C","6D"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uas);

        mNik = findViewById(R.id.et_nik);
        mNama = findViewById(R.id.et_nama);
        mKelas = findViewById(R.id.et_kelas);
        mJam = findViewById(R.id.et_jam);

        btn_simpan = findViewById(R.id.btn_uas);
        btn_tampil = findViewById(R.id.btn_haji);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, kelas);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpanData();
            }
        });
    }

    private void simpanData() {
        String nik = mNik.getText().toString().trim();
        String nama = mNama.getText().toString().trim();
        String kelas = mKelas.getSelectedItem().toString().trim();
        String jam = mJam.getText().toString().trim();

        if (nik.isEmpty()){
            mNik.setError("NIK masih kosong");
        } else if (nama.isEmpty()){
            mNama.setError("Nama masih kosong");
        } else if (kelas.isEmpty()){
            Toast.makeText(this, "Kelas belum dipilih", Toast.LENGTH_SHORT).show();
        } else if (jam.isEmpty()){
            mJam.setError("Jam masih kosong");
        } else {
            //
        }
    }
}
