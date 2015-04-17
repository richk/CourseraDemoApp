package demo.catalog.coursera.org.courserademoapp.di;

import dagger.Module;
import demo.catalog.coursera.org.courserademoapp.domain.CatalogInteractor;
import demo.catalog.coursera.org.courserademoapp.view.CatalogActivity;
import demo.catalog.coursera.org.courserademoapp.view.CatalogPresenter;
import demo.catalog.coursera.org.courserademoapp.viewmodel.CoursesViewModelImpl;

@Module(
        injects = {
                CatalogActivity.class
        }, library = true, complete = false)

public class CatalogModule {
    public CatalogModule() {

    }
}
