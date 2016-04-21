package demo.catalog.coursera.org.courserademoapp;

import android.app.Application;

import demo.catalog.coursera.org.courserademoapp.di.ApplicationComponent;
import demo.catalog.coursera.org.courserademoapp.di.ApplicationModule;
import demo.catalog.coursera.org.courserademoapp.di.DaggerApplicationComponent;
import demo.catalog.coursera.org.courserademoapp.di.NetworkingModule;

public class CourseraDemoApplication extends Application {

    ApplicationComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .networkingModule(new NetworkingModule("https://api.coursera.org"))
                .build();
    }

    public ApplicationComponent getAppComponent() {
        return mAppComponent;
    }
}
