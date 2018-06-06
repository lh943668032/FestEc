package com.lh.core.net.rx;

import android.content.Context;

import com.lh.core.net.HttpMethod;
import com.lh.core.net.RestCreator;
import com.lh.core.ui.loader.LhLoader;
import com.lh.core.ui.loader.LoaderStyle;

import java.io.File;
import java.util.WeakHashMap;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * @quthor lh
 * @date 2018/5/27 20:10
 */
public class RxRestClient {

    private final String URL;
    private final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private final RequestBody BODY;
    private final File FILE;
    private final LoaderStyle LOADER_STYLE;
    private final Context CONTEXT;

    public RxRestClient(String url,
                        WeakHashMap<String, Object> params,
                        RequestBody body,
                        File file,
                        Context context,
                        LoaderStyle loaderStyle) {
        this.URL = url;
        PARAMS.putAll(params);
        this.BODY = body;
        this.FILE = file;
        this.CONTEXT = context;
        this.LOADER_STYLE = loaderStyle;
    }

    public static RxRestClientBuilder builder() {
        return new RxRestClientBuilder();
    }

    private Observable<Response<ResponseBody>> request(HttpMethod method) {
        final RxRestService service = RestCreator.getRxRestService();
        Observable<Response<ResponseBody>> observable = null;

        if (LOADER_STYLE != null) {
            LhLoader.showLoading(CONTEXT, LOADER_STYLE);
        }

        switch (method) {
            case GET:
                observable = service.get(URL, PARAMS);
                break;
            case POST:
                observable = service.post(URL, PARAMS);
                break;
            case POST_RAW:
                observable = service.postRaw(URL, BODY);
                break;
            case PUT:
                observable = service.put(URL, PARAMS);
                break;
            case PUT_RAW:
                observable = service.putRaw(URL, BODY);
                break;
            case DELETE:
                observable = service.delete(URL, PARAMS);
                break;
            case UPLOAD:
                final RequestBody requestBody = RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()),FILE);
                final MultipartBody.Part body = MultipartBody.Part.createFormData("file",FILE.getName(),requestBody);
                observable = service.upload(URL,body);
                break;
            default:
                break;
        }

        return  observable;
    }

    public final Observable<Response<ResponseBody>> get() {
        return request(HttpMethod.GET);
    }

    public final Observable<Response<ResponseBody>> post() {
        if (BODY == null) {
            return request(HttpMethod.POST);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null!");
            } else {
                return request(HttpMethod.POST_RAW);
            }
        }
    }

    public final Observable<Response<ResponseBody>> put() {
        if (BODY == null) {
            return request(HttpMethod.PUT);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null!");
            } else {
                return request(HttpMethod.PUT_RAW);
            }
        }
    }

    public final Observable<Response<ResponseBody>> delete() {
        return request(HttpMethod.DELETE);
    }

    public final Observable<Response<ResponseBody>> upload(){
        return request(HttpMethod.UPLOAD);
    }

    public final Observable<Response<ResponseBody>> download(){
        final Observable<Response<ResponseBody>> responseBodyObservable = RestCreator.getRxRestService().download(URL,PARAMS);
        return responseBodyObservable;
    }
}
