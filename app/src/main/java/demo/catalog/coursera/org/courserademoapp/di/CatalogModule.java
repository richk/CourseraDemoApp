package demo.catalog.coursera.org.courserademoapp.di;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import demo.catalog.coursera.org.courserademoapp.domain.CatalogInteractor;
import demo.catalog.coursera.org.courserademoapp.network.CourseraNetworkService;
import demo.catalog.coursera.org.courserademoapp.view.CatalogActivity;
import demo.catalog.coursera.org.courserademoapp.view.CatalogAdapter;
import demo.catalog.coursera.org.courserademoapp.view.CatalogPresenter;

@Module
public class CatalogModule {
    private final AppCompatActivity activity;

    public CatalogModule(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Provides @Named("activity")
    @ActivityScope
    Context provideActivityContext() {
        return activity;
    }

    @Provides
    @ActivityScope
    CatalogPresenter providePresenter(CatalogInteractor interactor) {
        return new CatalogPresenter(interactor);
    }

    @Provides
    @ActivityScope
    CatalogInteractor provideInteractor(CourseraNetworkService networkService) {
        return new CatalogInteractor(networkService);
    }

    @Provides
    @ActivityScope
    LayoutInflater provideLayoutInflater() {
        return activity.getLayoutInflater();
    }

    @Provides
    @ActivityScope
    CatalogAdapter provideCatalogAdapter(LayoutInflater inflater) {
        return new CatalogAdapter(activity, inflater);
    }

}
