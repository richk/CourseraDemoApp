package demo.catalog.coursera.org.courserademoapp.view;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import demo.catalog.coursera.org.courserademoapp.R;
import demo.catalog.coursera.org.courserademoapp.di.CatalogModule;
import demo.catalog.coursera.org.courserademoapp.domain.Course;
import demo.catalog.coursera.org.courserademoapp.viewmodel.CoursesParcelableViewModel;
import demo.catalog.coursera.org.courserademoapp.viewmodel.CoursesViewModel;
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
        mPresenter.load(savedInstanceState);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mViewModelSubscription.unsubscribe();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mPresenter.onSave(outState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mViewModelSubscription = mPresenter.subscribeToViewModel(new Action1<CoursesParcelableViewModel>() {
            @Override
            public void call(CoursesParcelableViewModel viewModel) {
                List<Course> courses = viewModel.courseList();
                Log.d("CatalogActivity", "Number of courses:" + courses.size());
                Log.d("CatalogActivity", "First Course:" + courses.get(0).shortName);
                mAdapter.setCourses(courses);
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_catalog, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected List<Object> getModules() {
        LinkedList<Object> modules = new LinkedList<>();
        modules.add(new CatalogModule());
        return modules;
    }
}
