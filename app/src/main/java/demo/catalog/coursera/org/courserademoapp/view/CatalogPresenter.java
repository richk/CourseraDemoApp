package demo.catalog.coursera.org.courserademoapp.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import demo.catalog.coursera.org.courserademoapp.domain.CatalogInteractor;
import demo.catalog.coursera.org.courserademoapp.domain.Course;
import demo.catalog.coursera.org.courserademoapp.viewmodel.CoursesViewModel;
import demo.catalog.coursera.org.courserademoapp.viewmodel.CoursesViewModelImpl;
import rx.functions.Action1;

public class CatalogPresenter  {

    @Inject
    CatalogInteractor mInteractor;

    @Inject
    CoursesViewModelImpl mViewModel;

    @Inject
    public CatalogPresenter(@Named("activity") Context context) {
        Log.d("CatalogPresenter", context.getCacheDir().getAbsolutePath());
    }

    public void refresh() {
        mInteractor.getCourseObservable()
                .subscribe(new Action1<List<Course>>() {
                    @Override
                    public void call(List<Course> courses) {
                        mViewModel.mCourseList.onNext(courses);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.e("CatalogPresenter","Error while getting data", throwable);
                    }
                });
    }

    public CoursesViewModel getViewModel() {
        return mViewModel;
    }
}
