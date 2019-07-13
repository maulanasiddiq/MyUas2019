package com.maulanaabdulsiddiq.myuas2019.retrofit;

import com.maulanaabdulsiddiq.myuas2019.model.DataHaji;
import com.maulanaabdulsiddiq.myuas2019.model.DataUas;
import com.maulanaabdulsiddiq.myuas2019.model.ResponseHaji;
import com.maulanaabdulsiddiq.myuas2019.model.ResponseUas;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    @GET("uas")
    Call<ResponseUas> ambilData();

    @FormUrlEncoded
    @POST("uas")
    Call<DataUas> tambahData(
            @Field("nik") String nik, @Field("nama") String nama, @Field("kelas") String kelas,
            @Field("jam") String jam
    );

    @GET("haji")
    Call<ResponseHaji> getDataHaji();

    @FormUrlEncoded
    @POST("haji")
    Call<DataHaji> tambahDataHaji(
            @Field("nama") String nama, @Field("no_hp") String no_hp, @Field("alamat") String alamat,
            @Field("jenis_kelamin") String jenis_kelamin
    );
}
