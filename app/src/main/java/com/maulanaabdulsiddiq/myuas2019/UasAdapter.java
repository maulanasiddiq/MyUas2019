package com.maulanaabdulsiddiq.myuas2019;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.maulanaabdulsiddiq.myuas2019.model.DataUas;

import java.util.ArrayList;
import java.util.List;

public class UasAdapter extends RecyclerView.Adapter<UasAdapter.MyHolder> {

    private Context context;
    private List<DataUas> data = new ArrayList<>();

    public UasAdapter(Context context, List<DataUas> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.uas_list,viewGroup, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
        myHolder.txtNik.setText(data.get(i).getNik());
        myHolder.txtNama.setText(data.get(i).getNama());
        myHolder.txtKelas.setText(data.get(i).getKelas());
        myHolder.txtJam.setText(data.get(i).getJam());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        TextView txtNik;
        TextView txtNama;
        TextView txtKelas;
        TextView txtJam;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            txtNik = itemView.findViewById(R.id.et_nik);
            txtNama = itemView.findViewById(R.id.et_nama);
            txtKelas = itemView.findViewById(R.id.et_kelas);
            txtJam = itemView.findViewById(R.id.et_jam);
        }
    }
}
