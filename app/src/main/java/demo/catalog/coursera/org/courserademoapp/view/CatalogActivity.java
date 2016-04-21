package demo.catalog.coursera.org.courserademoapp.view;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import demo.catalog.coursera.org.courserademoapp.R;
import demo.catalog.coursera.org.courserademoapp.di.CatalogModule;
import demo.catalog.coursera.org.courserademoapp.domain.Course;
import demo.catalog.coursera.org.courserademoapp.viewmodel.CoursesParcelableViewModel;
import rx.Subscription;
import rx.functions.Action1;


public class CatalogActivity extends BaseActivity {

    private ListView mCatalogList;

    @Inject
    CatalogAdapter mAdapter;

    @Inject
    CatalogPresenter mPresenter;

    private Subscription mViewModelSubscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);
        mCatalogList = (ListView) findViewById(android.R.id.list);
        mCatalogList.setAdapter(mAdapter);
        mPresenter.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mViewModelSubscription = mPresenter.onResume(new Action1<CoursesParcelableViewModel>() {
            @Override
            public void call(CoursesParcelableViewModel viewModel) {
                final List<Course> courses = viewModel.courseList();
                mAdapter.setCourses(courses);
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        mViewModelSubscription.unsubscribe();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mPresenter.onPause(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_catalog, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected List<Object> getModules() {
        LinkedList<Object> modules = new LinkedList<>();
        modules.add(new CatalogModule());
        return modules;
    }
}
