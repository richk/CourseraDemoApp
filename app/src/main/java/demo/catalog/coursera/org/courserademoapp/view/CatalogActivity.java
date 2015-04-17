package demo.catalog.coursera.org.courserademoapp.view;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
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
import demo.catalog.coursera.org.courserademoapp.viewmodel.CoursesViewModel;
import rx.Subscription;
import rx.functions.Action1;


public class CatalogActivity extends BaseActivity {

    private ListView mCatalogList;

    private CatalogAdapter mAdapter;
    @Inject
    CatalogPresenter mPresenter;
    private CoursesViewModel mViewModel;
    private Subscription mCoursesSubscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);
        mCatalogList = (ListView) findViewById(android.R.id.list);
        mAdapter = new CatalogAdapter(getApplicationContext());
        mCatalogList.setAdapter(mAdapter);
        mViewModel = mPresenter.getViewModel();
        mPresenter.refresh();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mCoursesSubscription.unsubscribe();

    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.refresh();
        mCoursesSubscription = mViewModel.subscribeToCourseList(new Action1<List<Course>>() {
            @Override
            public void call(List<Course> courses) {
                mAdapter.clear();
                mAdapter.addAll(courses);
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
