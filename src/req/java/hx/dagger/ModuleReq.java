package hx.dagger;

import android.util.Log;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.inject.Named;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import hx.dagger.helper.RequestScope;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;
import timber.log.Timber;

@Module
public class ModuleReq {

    /*private final String TAG = "--ModuleReq--:";

    private String mEndPoint;
    private CookieJar mCookieJar;

    public ModuleReq(String mEndPoint){
        this.mEndPoint = mEndPoint;
    }

    @Provides
    @Named("req")
    public Retrofit getRetrofit(@Named("req") OkHttpClient okHttpClient) {
        ObjectMapper mapper = new ObjectMapper();
        //SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //mapper.setDateFormat(fmt);
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        //mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        return new Retrofit.Builder()
                //.addConverterFactory(JacksonConverterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create(mapper))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient)
                .baseUrl(mEndPoint)
                .build();
    }

    @Provides
    @Named("req")
    public OkHttpClient getOkHttpClient(){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(message -> {
            //Timber.d(message);
            Log.d(TAG, message);
        });
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
                .readTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .connectTimeout(1, TimeUnit.MINUTES)
                //.cache(new Cache(new File(""), 128))
                .addInterceptor(logging)
                .cookieJar(new CookieJar() {
                    @Override
                    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {

                    }

                    @Override
                    public List<Cookie> loadForRequest(HttpUrl url) {
                        return null;
                    }
                })
                .addNetworkInterceptor(chain -> {
                    Request.Builder builder = chain.request().newBuilder();
                    builder.addHeader("X-Requested-With", "XMLHttpRequest");
                    //Response res = chain.proceed(builder.build());
                    return chain.proceed(builder.build());
                })
                *//*.cookieJar(new CookieJar() {
                    @Ovchainerride
                    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                        for(Cookie c: cookies){
                            c.domain();
                        }
                    }
                    @Override
                    public List<Cookie> loadForRequest(HttpUrl url) {
                        return new ArrayList<Cookie>();
                    }
                })*//*
                .build();
    }*/
}
