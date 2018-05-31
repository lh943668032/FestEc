package com.lh.core.net.rx;

import java.util.WeakHashMap;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
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

public interface RxRestService {

    @GET
    Observable<Response<ResponseBody>> get(@Url String url, @QueryMap WeakHashMap<String, Object> params);

    @FormUrlEncoded
    @POST
    Observable<Response<ResponseBody>> post(@Url String url, @FieldMap WeakHashMap<String, Object> params);

    @POST
    Observable<Response<ResponseBody>> postRaw(@Url String url, @Body RequestBody body);

    @FormUrlEncoded
    @PUT
    Observable<Response<ResponseBody>> put(@Url String url, @FieldMap WeakHashMap<String, Object> params);

    @PUT
    Observable<Response<ResponseBody>> putRaw(@Url String url, @Body RequestBody body);

    @DELETE
    Observable<Response<ResponseBody>> delete(@Url String url, @QueryMap WeakHashMap<String, Object> params);

    @Streaming
    @GET
    Observable<Response<ResponseBody>> download(@Url String url, @QueryMap WeakHashMap<String, Object> params);

    @Multipart
    @POST
    Observable<Response<ResponseBody>> upload(@Url String url, @Part MultipartBody.Part file);

}
