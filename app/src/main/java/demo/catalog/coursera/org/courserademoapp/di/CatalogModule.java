package demo.catalog.coursera.org.courserademoapp.di;

import dagger.Module;
import demo.catalog.coursera.org.courserademoapp.view.CatalogActivity;

@Module(
        injects = {
                CatalogActivity.class
        }, library = true, complete = false)

public class CatalogModule {
    public CatalogModule() {

    }
}
