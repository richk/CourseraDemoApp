package demo.catalog.coursera.org.courserademoapp.view;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import java.util.List;

import demo.catalog.coursera.org.courserademoapp.domain.CatalogInteractor;
import demo.catalog.coursera.org.courserademoapp.domain.Course;
import demo.catalog.coursera.org.courserademoapp.viewmodel.CoursesViewModel;
import demo.catalog.coursera.org.courserademoapp.viewmodel.CoursesViewModelImpl;
import rx.functions.Action1;

public class CatalogPresenter extends FragmentPresenter<CoursesViewModel, CoursesViewModelImpl> {

    public CatalogPresenter(FragmentActivity activity,
                            Bundle arguments,
                            boolean isExisted) {
        super(activity, arguments, new CoursesViewModelImpl(), isExisted);
    }

    public void refresh() {
        new CatalogInteractor().getCourseObservable()
                .subscribe(new Action1<List<Course>>() {
                    @Override
                    public void call(List<Course> courses) {
                        getData().mCourseList.onNext(courses);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.e("CatalogPresenter","Error while getting data", throwable);
                    }
                });
    }
}
