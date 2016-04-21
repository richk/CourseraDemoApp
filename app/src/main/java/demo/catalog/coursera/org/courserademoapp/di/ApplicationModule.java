package demo.catalog.coursera.org.courserademoapp.di;

import android.content.Context;
import android.view.LayoutInflater;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import demo.catalog.coursera.org.courserademoapp.CourseraDemoApplication;
import demo.catalog.coursera.org.courserademoapp.flowcontroller.FlowController;
import demo.catalog.coursera.org.courserademoapp.view.CatalogActivity;


@Module
public final class ApplicationModule {
    private final Context context;

    public ApplicationModule(Context context) {
        this.context = context;
    }

    @Provides @Named("application")
    @Singleton
    Context provideApplicationContext() {
        return context;
    }
}
