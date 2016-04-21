package demo.catalog.coursera.org.courserademoapp.di;

import dagger.Component;
import demo.catalog.coursera.org.courserademoapp.view.CatalogActivity;

@ActivityScope
@Component(dependencies = ApplicationComponent.class, modules = {CatalogModule.class})
public interface CatalogComponent {
    void inject(CatalogActivity activity);
}
