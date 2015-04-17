package demo.catalog.coursera.org.courserademoapp.di;

import android.content.Context;
import android.view.LayoutInflater;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import demo.catalog.coursera.org.courserademoapp.CourseraDemoApplication;
import demo.catalog.coursera.org.courserademoapp.view.CatalogActivity;


@Module(
        injects = {
                CourseraDemoApplication.class
        }, library = true)
public final class ApplicationModule {
    private final Context context;

    public ApplicationModule(Context context) {
        this.context = context;
    }

    @Provides @Named("application")
    Context provideApplicationContext() {
        return context;
    }

    @Provides
    LayoutInflater provideLayoutInflater() {
        return LayoutInflater.from(context);
    }
}
