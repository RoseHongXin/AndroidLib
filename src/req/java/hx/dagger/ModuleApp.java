package hx.dagger;

import android.app.Application;
import android.content.Context;

import java.io.Serializable;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hx.dagger.helper.RequestScope;
import hx.warehouse.StoreScope;


@Module
public abstract class ModuleApp<T extends Application> {

    /*private Context mCtx;
    private T mApp;

    public ModuleApp(T app) {
        this.mApp = app;
        this.mCtx = app.getApplicationContext();
    }

    @Provides
    public Context getApplicationContext() {
        return mCtx;
    }

    @Provides
    public T getApplication(){
        return mApp;
    }*/
}