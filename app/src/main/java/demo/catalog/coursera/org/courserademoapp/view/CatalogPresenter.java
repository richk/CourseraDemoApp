package demo.catalog.coursera.org.courserademoapp.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import auto.parcel.AutoParcel;
import demo.catalog.coursera.org.courserademoapp.domain.CatalogInteractor;
import demo.catalog.coursera.org.courserademoapp.domain.Course;
import demo.catalog.coursera.org.courserademoapp.viewmodel.Address;
import demo.catalog.coursera.org.courserademoapp.viewmodel.CoursesParcelableViewModel;
import demo.catalog.coursera.org.courserademoapp.viewmodel.CoursesViewModel;
import demo.catalog.coursera.org.courserademoapp.viewmodel.CoursesViewModelImpl;
import rx.Subscription;
import rx.functions.Action1;
import rx.subjects.BehaviorSubject;

public class CatalogPresenter {

    @Inject
    CatalogInteractor mInteractor;

    CoursesParcelableViewModel mViewModel;

    BehaviorSubject<CoursesParcelableViewModel> mViewModelSubject = BehaviorSubject.create();

    @Inject
    public CatalogPresenter(@Named("activity") Context context) {
    }

    public void load(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            mViewModel = savedInstanceState.getParcelable(CoursesViewModel.class.getSimpleName());
            mViewModelSubject.onNext(mViewModel);
        } else {
            refresh();
        }
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

    public void onSave(Bundle savedInstanceState) {
        savedInstanceState.putParcelable(CoursesViewModel.class.getSimpleName(), mViewModel);
    }

    public Subscription subscribeToViewModel(Action1<CoursesParcelableViewModel> viewModelAction) {
        return mViewModelSubject.subscribe(viewModelAction);
    }

    public Subscription subscribeToCourseList(Action1<List<Course>> courseListAction) {
        return mViewModel.subscribeToCourseList(courseListAction);
    }
}
