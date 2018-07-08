package com.serviceexchange.www.serviceexchangeandroid.apimodels.retrofit;

import android.content.Context;
import android.util.Log;

import com.serviceexchange.www.serviceexchangeandroid.sharedprefrencesmodel.SharedPreferencesModel;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static final String BASE_URL = "http://192.168.43.175:8085"; //"http://192.168.1.5:8080";//"http://172.16.5.186:8080";
//            public static final String BASE_URL = "https://giveand.herokuapp.com"; //"http://192.168.1.5:8080";//"http://172.16.5.186:8080";

//    public static final String BASE_URL = "http://192.168.43.175:8085"; //"http://192.168.1.5:8080";//"http://172.16.5.186:8080";
//    public static final String BASE_URL = "https://giveand.herokuapp.com"; //"http://192.168.1.5:8080";//"http://172.16.5.186:8080";

    private static HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

    private static Retrofit retrofit;

    public static Retrofit getClient(Context context) {
        if (retrofit == null) {
            Interceptor interceptor = new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request = chain.request();
                    String token = SharedPreferencesModel.getInstance(context).getToken();
                    if (token == null || token.length() == 0) {
                        return chain.proceed(request);
                    }
                    Request requestPlusHeader = request
                            .newBuilder()
                            .addHeader("token", token)
                            .build();
                    return chain.proceed(requestPlusHeader);
                }
            };

            OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .addInterceptor(loggingInterceptor);


            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();
        }
        return retrofit;
    }
}