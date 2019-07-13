package com.maulanaabdulsiddiq.myuas2019;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.maulanaabdulsiddiq.myuas2019.model.DataUas;
import com.maulanaabdulsiddiq.myuas2019.retrofit.RetrofitConfig;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

        btn_simpan = findViewById(R.id.simpan_uas);
        btn_tampil = findViewById(R.id.tampil_uas);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, kelas);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mKelas.setAdapter(adapter);

        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpanData();
            }
        });

        btn_tampil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UasActivity.this, TampilUasActivity.class);
                startActivity(intent);
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

            final ProgressDialog progress = new ProgressDialog(UasActivity.this);
            progress.setMessage("saving...");
            progress.show();

            Call<DataUas> request = RetrofitConfig.getApiService().tambahData(nik,nama,kelas,jam);
            request.enqueue(new Callback<DataUas>() {
                @Override
                public void onResponse(Call<DataUas> call, Response<DataUas> response) {
                    progress.dismiss();
                    if (response.isSuccessful()){
                        Toast.makeText(UasActivity.this, "Berhasil disimpan", Toast.LENGTH_SHORT).show();
                        mNik.setText("");
                        mNama.setText("");
                        mJam.setText("");
                    } else {
                        Toast.makeText(UasActivity.this, "Gagal disimpan", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<DataUas> call, Throwable t) {
                    Toast.makeText(UasActivity.this, "Periksa koneksi internet", Toast.LENGTH_SHORT).show();
                    progress.dismiss();
                }
            });
        }
    }
}
