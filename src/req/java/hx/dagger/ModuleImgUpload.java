package hx.dagger;

import java.util.concurrent.TimeUnit;
import javax.inject.Named;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Module
public class ModuleImgUpload {

    /*@Provides
    @Named("upload")
    public Retrofit getRetrofit(@Named("upload") okhttp3.OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addConverterFactory(JacksonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient)
                .baseUrl(ModuleReq.getEndPoint())
                .build();
    }

    @Named("upload")
    public okhttp3.OkHttpClient getOkHttpClient(){
        return new okhttp3.OkHttpClient.Builder()
                .readTimeout(2, TimeUnit.MINUTES)
                .writeTimeout(2, TimeUnit.MINUTES)
                .connectTimeout(2, TimeUnit.MINUTES)
                *//*.addInterceptor(chain -> {
                    Request req = chain.request();
                    req.newBuilder().addHeader("", "");
                    return chain.proceed(req);
                })*//*
                .build();
    }*/
}
