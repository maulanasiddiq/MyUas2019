package com.maulanaabdulsiddiq.myuas2019;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.maulanaabdulsiddiq.myuas2019.model.DataUas;
import com.maulanaabdulsiddiq.myuas2019.model.ResponseUas;
import com.maulanaabdulsiddiq.myuas2019.retrofit.RetrofitConfig;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TampilUasActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<DataUas> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_uas);

        recyclerView = findViewById(R.id.recyclerView);
        ambilData();

        recyclerView.setAdapter(new UasAdapter(TampilUasActivity.this, data));
        recyclerView.setLayoutManager(new LinearLayoutManager(TampilUasActivity.this));
    }

    private void ambilData() {
        final ProgressDialog progress = new ProgressDialog(TampilUasActivity.this);
        progress.setMessage("loading...");
        progress.show();

        Call<ResponseUas> request = RetrofitConfig.getApiService().ambilData();
        request.enqueue(new Callback<ResponseUas>() {
            @Override
            public void onResponse(Call<ResponseUas> call, Response<ResponseUas> response) {
                progress.dismiss();
                if (response.isSuccessful()){
                    data = response.body().getData();
                    recyclerView.setAdapter(new UasAdapter(TampilUasActivity.this, data));
                } else {
                    Toast.makeText(TampilUasActivity.this, "Gagal load data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseUas> call, Throwable t) {
                Toast.makeText(TampilUasActivity.this, "Periksa koneksi internet", Toast.LENGTH_SHORT).show();
                progress.dismiss();
            }
        });
    }
}
