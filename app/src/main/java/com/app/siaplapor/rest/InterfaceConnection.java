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
    Call<UserResponse> checkRole(@Path("email") String email);

    @GET("report/{id}")
    Call<DataResponse> get_detail_report(@Path("id") String id);

    @DELETE("report/delete/{id}")
    Call<DataResponse> delete_data_report(@Path("id") String id);

    @GET("report/user/{email}")
    Call<DataResponse> get_user_report(@Path("email") String email);

    @FormUrlEncoded
    @POST("report/insert")
    Call<DataResponse> insert_data_report(
            @Field("nik") String nik,
            @Field("name") String name,
            @Field("phone") String phone,
            @Field("report") String report,
            @Field("email") String user_id
    );

    @FormUrlEncoded
    @POST("user/register")
    Call<UserResponse> register(
            @Field("name") String name,
            @Field("username") String username,
            @Field("email") String email,
            @Field("password") String password,
            @Field("address") String address
    );
}
