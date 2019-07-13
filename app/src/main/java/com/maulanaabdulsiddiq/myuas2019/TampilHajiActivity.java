package com.maulanaabdulsiddiq.myuas2019;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.maulanaabdulsiddiq.myuas2019.model.DataHaji;
import com.maulanaabdulsiddiq.myuas2019.model.DataUas;
import com.maulanaabdulsiddiq.myuas2019.model.ResponseHaji;
import com.maulanaabdulsiddiq.myuas2019.model.ResponseUas;
import com.maulanaabdulsiddiq.myuas2019.retrofit.RetrofitConfig;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TampilHajiActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<DataHaji> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_haji);

        recyclerView = findViewById(R.id.recyclerView2);
        ambilData();

        recyclerView.setAdapter(new HajiAdapter(TampilHajiActivity.this, data));
        recyclerView.setLayoutManager(new LinearLayoutManager(TampilHajiActivity.this));
    }

    private void ambilData() {
        final ProgressDialog progress = new ProgressDialog(TampilHajiActivity.this);
        progress.setMessage("loading...");
        progress.show();

        Call<ResponseHaji> request = RetrofitConfig.getApiService().getDataHaji();
        request.enqueue(new Callback<ResponseHaji>() {
            @Override
            public void onResponse(Call<ResponseHaji> call, Response<ResponseHaji> response) {
                progress.dismiss();
                if (response.isSuccessful()){
                    data = response.body().getData();
                    recyclerView.setAdapter(new HajiAdapter(TampilHajiActivity.this, data));
                } else {
                    Toast.makeText(TampilHajiActivity.this, "Gagal load data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseHaji> call, Throwable t) {
                Toast.makeText(TampilHajiActivity.this, "Periksa koneksi internet", Toast.LENGTH_SHORT).show();
                progress.dismiss();
            }
        });
    }
}
