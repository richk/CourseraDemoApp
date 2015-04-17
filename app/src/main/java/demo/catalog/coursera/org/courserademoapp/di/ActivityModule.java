package demo.catalog.coursera.org.courserademoapp.di;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import demo.catalog.coursera.org.courserademoapp.view.BaseActivity;
import demo.catalog.coursera.org.courserademoapp.view.CatalogActivity;
import demo.catalog.coursera.org.courserademoapp.view.CatalogPresenter;
import demo.catalog.coursera.org.courserademoapp.view.FragmentPresenter;

@Module(
        injects = {
        }, library = true)

public final class ActivityModule {

    private final FragmentActivity activity;

    public ActivityModule(FragmentActivity activity) {
        this.activity = activity;
    }

    @Provides @Named("activity")
    Context provideActivityContext() {
        return activity;
    }

    @Provides
    FragmentManager provideFragmentManager() {
        return activity.getSupportFragmentManager();
    }
}