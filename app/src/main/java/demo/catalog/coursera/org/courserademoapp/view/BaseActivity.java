package demo.catalog.coursera.org.courserademoapp.view;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import java.util.List;

import dagger.ObjectGraph;
import demo.catalog.coursera.org.courserademoapp.CourseraDemoApplication;
import demo.catalog.coursera.org.courserademoapp.di.ActivityModule;
import demo.catalog.coursera.org.courserademoapp.di.CatalogModule;

public abstract class BaseActivity extends ActionBarActivity {

    private ObjectGraph activityScopeGraph;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependencies();
    }

    /**
     * Method used to resolve dependencies provided by Dagger modules. Inject an object to provide
     * every @Inject annotation contained.
     *
     * @param object to inject.
     */
    public void inject(Object object) {
        activityScopeGraph.inject(object);
    }

    /**
     * Get a list of Dagger modules with Activity scope needed to this Activity.
     *
     * @return modules with new dependencies to provide.
     */
    protected abstract List<Object> getModules();

    /**
     * Create a new Dagger ObjectGraph to add new dependencies using a plus operation and inject the
     * declared one in the activity. This new graph will be destroyed once the activity lifecycle
     * finish.
     *
     * This is the key of how to use Activity scope dependency injection.
     */
    private void injectDependencies() {
        CourseraDemoApplication courseraApplication = (CourseraDemoApplication) getApplication();
        List<Object> activityScopeModules = getModules();
        activityScopeModules.add(new ActivityModule(this));
        activityScopeGraph = courseraApplication.plus(activityScopeModules);
        inject(this);
    }
}