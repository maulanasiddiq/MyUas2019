package com.maulanaabdulsiddiq.myuas2019.retrofit;

import com.maulanaabdulsiddiq.myuas2019.model.DataItem;
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
    Call<DataItem> tambahData(
            @Field("nik") String nik, @Field("nama") String nama, @Field("kelas") String kelas,
            @Field("jam") String jam
    );
}
