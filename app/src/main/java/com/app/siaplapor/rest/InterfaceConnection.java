package com.app.siaplapor.rest;

import com.app.siaplapor.response.DataResponse;
import com.app.siaplapor.response.UserResponse;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface InterfaceConnection {
    @GET("report/admin")
    Call<DataResponse> get_all_report();

    @GET("user/role/{email}")
    Call<UserResponse> checkrole(@Path("email") String email);

    @GET("report/{id}")
    Call<DataResponse> get_detail_report(@Path("id") String id);

    @DELETE("report/delete/{id}")
    Call<DataResponse> delete_data_report(@Path("id") String id);

    @GET("report/user/{userId}")
    Call<DataResponse> get_user_report(@Path("userId") String userId);

    @FormUrlEncoded
    @POST("report/insert")
    Call<DataResponse> insert_data_report(
            @Field("nik") String nik,
            @Field("nama") String name,
            @Field("telepon") String phone,
            @Field("isi_laporan") String report,
            @Field("user_id") String user_id
    );

    @FormUrlEncoded
    @POST("register")
    Call<DataResponse> register(
            @Field("name") String name,
            @Field("username") String username,
            @Field("email") String email,
            @Field("password") String password,
            @Field("address") String address
    );
}
