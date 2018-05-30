package com.lh.core.net;

import java.util.HashMap;
import java.util.WeakHashMap;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface RestService {

    @GET
    Call<ResponseBody> get(@Url String url,@QueryMap WeakHashMap<String,Object> params);

    @FormUrlEncoded
    @POST
    Call<ResponseBody> post(@Url String url, @FieldMap WeakHashMap<String,Object> params);

    @POST
    Call<ResponseBody> postRaw(@Url String url, @Body RequestBody body);

    @FormUrlEncoded
    @PUT
    Call<ResponseBody> put(@Url String url, @FieldMap WeakHashMap<String,Object> params);

    @PUT
    Call<ResponseBody> putRaw(@Url String url, @Body RequestBody body);

    @DELETE
    Call<ResponseBody> delete(@Url String url,@QueryMap WeakHashMap<String,Object> params);

    @Streaming
    @GET
    Call<ResponseBody> download(@Url String url,@QueryMap WeakHashMap<String,Object> params);

    @Multipart
    @POST
    Call<ResponseBody> upload(@Url String url, @Part MultipartBody.Part file);

}
