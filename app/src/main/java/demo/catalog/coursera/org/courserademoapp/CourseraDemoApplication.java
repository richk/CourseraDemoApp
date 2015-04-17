package demo.catalog.coursera.org.courserademoapp;

import android.app.Application;

import java.util.List;

import dagger.ObjectGraph;
import demo.catalog.coursera.org.courserademoapp.di.ApplicationModule;

public class CourseraDemoApplication extends Application {
    private ObjectGraph objectGraph;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeDependencyInjector();
    }

    /**
     * Inject every dependency declared in the object with the @Inject annotation if the dependency
     * has been already declared in a module and already initialized by Dagger.
     *
     * @param object to inject.
     */
    public void inject(Object object) {
        objectGraph.inject(object);
    }

    public ObjectGraph plus(List<Object> modules) {
        if (modules == null) {
            throw new IllegalArgumentException(
                    "You can't plus a null module, review your getModules() implementation");
        }
        return objectGraph.plus(modules.toArray());
    }

    private void initializeDependencyInjector() {
        objectGraph = ObjectGraph.create(new ApplicationModule(this));
        objectGraph.inject(this);
        objectGraph.injectStatics();
    }
}
