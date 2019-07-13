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

import com.maulanaabdulsiddiq.myuas2019.model.DataHaji;
import com.maulanaabdulsiddiq.myuas2019.model.DataUas;
import com.maulanaabdulsiddiq.myuas2019.retrofit.RetrofitConfig;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HajiActivity extends AppCompatActivity {

    EditText mNama;
    EditText mNohp;
    Spinner mJK;
    EditText mAlamat;
    Button btn_simpan;
    Button btn_tampil;

    String[] jk = {"Laki-Laki","Perempuan"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_haji);

        mNama = findViewById(R.id.et_nama);
        mNohp = findViewById(R.id.et_nohp);
        mJK = findViewById(R.id.et_jk);
        mAlamat = findViewById(R.id.et_alamat);

        btn_simpan = findViewById(R.id.simpan_uas);
        btn_tampil = findViewById(R.id.tampil_uas);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, jk);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mJK.setAdapter(adapter);

        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpanData();
            }
        });

        btn_tampil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HajiActivity.this, TampilHajiActivity.class);
                startActivity(intent);
            }
        });
    }

    private void simpanData() {
        String nama = mNama.getText().toString().trim();
        String no_hp = mNohp.getText().toString().trim();
        String jk = mJK.getSelectedItem().toString().trim();
        String alamat = mAlamat.getText().toString().trim();

        if (nama.isEmpty()){
            mNama.setError("NIK masih kosong");
        } else if (no_hp.isEmpty()){
            mNohp.setError("Nama masih kosong");
        } else if (jk.isEmpty()){
            Toast.makeText(this, "Jenis Kelamin belum dipilih", Toast.LENGTH_SHORT).show();
        } else if (alamat.isEmpty()){
            mAlamat.setError("Jam masih kosong");
        } else {

            final ProgressDialog progress = new ProgressDialog(HajiActivity.this);
            progress.setMessage("saving...");
            progress.show();

            Call<DataHaji> request = RetrofitConfig.getApiService().tambahDataHaji(nama,no_hp,alamat,jk);
            request.enqueue(new Callback<DataHaji>() {
                @Override
                public void onResponse(Call<DataHaji> call, Response<DataHaji> response) {
                    progress.dismiss();
                    if (response.isSuccessful()){
                        Toast.makeText(HajiActivity.this, "Berhasil disimpan", Toast.LENGTH_SHORT).show();
                        mNama.setText("");
                        mNohp.setText("");
                        mAlamat.setText("");
                    } else {
                        Toast.makeText(HajiActivity.this, "Gagal disimpan", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<DataHaji> call, Throwable t) {
                    Toast.makeText(HajiActivity.this, "Periksa koneksi internet", Toast.LENGTH_SHORT).show();
                    progress.dismiss();
                }
            });
        }
    }
}
