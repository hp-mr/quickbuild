package com.mr.quickbuild.network;

import android.util.Log;

import java.io.IOException;
import java.util.Locale;
import java.util.Set;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * TODO Log拦截器代码
 */
public class LogInterceptor implements Interceptor {
    private String TAG = "okhttp";
    private static final String METHOD_GET = "GET";
    private static final String METHOD_POST = "POST";

    @Override
    public okhttp3.Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Log.e(TAG, "header:" + request.headers().toString());
        Log.e(TAG, "request:" + request.toString());
        if (METHOD_GET.equals(request.method())) { // GET方法
            okhttp3.HttpUrl httpUrl = request.url();
            // 打印所有get参数
            Set<String> paramKeys = httpUrl.queryParameterNames();
            for (String key : paramKeys) {
                String value = httpUrl.queryParameter(key);
                Log.e(TAG, "params:" + key + " ->" + value);
            }
        } else if (METHOD_POST.equals(request.method())) { // POST方法
            // FormBody和url不太一样，若需添加公共参数，需要新建一个构造器
            FormBody.Builder bodyBuilder = new FormBody.Builder();
            MultipartBody.Builder multyBuilder = new MultipartBody.Builder();
            // 把已有的post参数添加到新的构造器
            if (request.body() instanceof FormBody) {
                FormBody formBody = (FormBody) request.body();
                for (int i = 0; i < formBody.size(); i++) {
                    bodyBuilder.addEncoded(formBody.encodedName(i), formBody.encodedValue(i));
                    Log.e(TAG, "params:" + formBody.encodedName(i) + " ->" + formBody.encodedValue(i));
                }
            } else if (request.body() instanceof MultipartBody) {
                MultipartBody body = (MultipartBody) request.body();
                for (MultipartBody.Part part : body.parts()) {
                    RequestBody body1 = part.body();
                }
            }
        }
        long t1 = System.nanoTime();
        okhttp3.Response response = chain.proceed(chain.request());
        long t2 = System.nanoTime();
        Log.e(TAG, String.format(Locale.getDefault(), "Received response for %s in %.1fms%n%s",
                response.request().url(), (t2 - t1) / 1e6d, response.headers()));
        okhttp3.MediaType mediaType = response.body().contentType();
        String content = response.body().string();
        Log.e(TAG, "response body:" + content);
        return response.newBuilder()
                .body(okhttp3.ResponseBody.create(mediaType, content))
                .build();
    }
}