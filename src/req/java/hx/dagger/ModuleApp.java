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
@Singleton
public class ModuleApp {

    private Context mCtx;
    private Application mApp;

    public ModuleApp(Application app) {
        this.mApp = app;
        this.mCtx = app.getApplicationContext();
    }

    @Provides
    public Context getApplicationContext() {
        return mCtx;
    }

    @Provides
    public Application getApplication(){
        return mApp;
    }
}