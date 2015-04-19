package demo.catalog.coursera.org.courserademoapp.di;

import java.util.List;

import dagger.Module;
import dagger.Provides;
import demo.catalog.coursera.org.courserademoapp.domain.CatalogInteractor;
import demo.catalog.coursera.org.courserademoapp.domain.Course;
import demo.catalog.coursera.org.courserademoapp.view.CatalogActivity;
import demo.catalog.coursera.org.courserademoapp.view.CatalogPresenter;
import demo.catalog.coursera.org.courserademoapp.viewmodel.CoursesParcelableViewModel;
import demo.catalog.coursera.org.courserademoapp.viewmodel.CoursesViewModelImpl;

@Module(
        injects = {
                CatalogActivity.class
        }, library = true, complete = false)

public class CatalogModule {
    public CatalogModule() {

    }
}
