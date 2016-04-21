package demo.catalog.coursera.org.courserademoapp.view;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import demo.catalog.coursera.org.courserademoapp.domain.CatalogInteractor;
import demo.catalog.coursera.org.courserademoapp.domain.Course;
import demo.catalog.coursera.org.courserademoapp.viewmodel.CoursesParcelableViewModel;
import demo.catalog.coursera.org.courserademoapp.viewmodel.CoursesViewModel;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.subjects.BehaviorSubject;

public class CatalogPresenter implements Presenter {

    @Inject
    CatalogInteractor mInteractor;

    CoursesParcelableViewModel mViewModel;

    BehaviorSubject<CoursesParcelableViewModel> mViewModelSubject = BehaviorSubject.create();

    @Inject
    public CatalogPresenter(@Named("activity") Context context) {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            mViewModel = savedInstanceState.getParcelable(CoursesViewModel.class.getSimpleName());
        }
    }

    @Override
    public Subscription onResume(Action1 loadAction) {
        if (mViewModel != null) {
            mViewModelSubject.onNext(mViewModel);
        } else {
            refresh();
        }
        return mViewModelSubject
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(loadAction);
    }

    @Override
    public void onPause(Bundle savedInstanceState) {
        savedInstanceState.putParcelable(CoursesViewModel.class.getSimpleName(), mViewModel);
    }

    private void refresh() {
        mInteractor.getCourseObservable()
                .subscribe(new Action1<List<Course>>() {
                    @Override
                    public void call(List<Course> courses) {
                        mViewModel = CoursesParcelableViewModel.create(courses);
                        mViewModelSubject.onNext(mViewModel);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.e("CatalogPresenter", "Error while getting data", throwable);
                    }
                });
    }
}
