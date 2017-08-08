package cuongvo.mvp_example.model.api;

import java.util.concurrent.TimeUnit;

import cuongvo.mvp_example.BuildConfig;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by cuongvo.
 */

public class ApiClient {
    private static String mApiBaseUrl = BuildConfig.HOST;

    private static ApiClient mApiClient;
    private static OkHttpClient mClient;
    private static ApiRequest mApiRequest;

    private ApiClient() {
    }

    public static ApiClient getInstance() {
        if (mApiClient == null) {
            mApiClient = new ApiClient();

            return mApiClient;
        }

        return mApiClient;
    }

    public void create() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        mClient = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(mClient)
                .baseUrl(mApiBaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        mApiRequest = retrofit.create(ApiRequest.class);
    }

    public void setApiRequest(ApiRequest apiRequest) {
        this.mApiRequest = apiRequest;
    }

    public OkHttpClient getClient() {
        return this.mClient;
    }

    public ApiRequest getApiRequest() {
        return mApiRequest;
    }

    public void setApiBaseUrl(String url) {
        this.mApiBaseUrl = url;
    }

}
