package com.maulanaabdulsiddiq.myuas2019;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.maulanaabdulsiddiq.myuas2019.model.DataHaji;
import com.maulanaabdulsiddiq.myuas2019.model.DataUas;

import java.util.ArrayList;
import java.util.List;

public class HajiAdapter extends RecyclerView.Adapter<HajiAdapter.MyHolder> {

    private Context context;
    private List<DataHaji> data = new ArrayList<>();

    public HajiAdapter(Context context, List<DataHaji> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.haji_list,viewGroup, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
        myHolder.txtNama.setText(data.get(i).getNama());
        myHolder.txtNoHp.setText(data.get(i).getNoHp());
        myHolder.txtJk.setText(data.get(i).getJenisKelamin());
        myHolder.txtAlamat.setText(data.get(i).getAlamat());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        TextView txtNama;
        TextView txtNoHp;
        TextView txtJk;
        TextView txtAlamat;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            txtNama = itemView.findViewById(R.id.et_nama);
            txtNoHp = itemView.findViewById(R.id.et_nohp);
            txtJk = itemView.findViewById(R.id.et_jk);
            txtAlamat = itemView.findViewById(R.id.et_alamat);
        }
    }
}

