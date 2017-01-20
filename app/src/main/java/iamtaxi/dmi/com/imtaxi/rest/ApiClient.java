package iamtaxi.dmi.com.imtaxi.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiClient {

    public static final String BASE_URL = "http://192.168.1.49:8080/Spring/";
    private static Retrofit retrofit = null;

    public static ApiInterface createClient() {
        return getClient().create(ApiInterface.class);
    }

    public static Retrofit getClient() {
        if (retrofit == null) {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            HashMap<String, String> headers = new HashMap<>();
            headers.put("version", "3");
            HttpLoggingInterceptorWithHeader interceptor = new HttpLoggingInterceptorWithHeader(headers);
//            interceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptorWithHeader.Level.BODY : HttpLoggingInterceptorWithHeader.Level.NONE);
            interceptor.setLevel(HttpLoggingInterceptorWithHeader.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).connectTimeout(90, TimeUnit.SECONDS).readTimeout(90, TimeUnit.SECONDS).build();
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL).client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }
}
