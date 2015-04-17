package demo.catalog.coursera.org.courserademoapp.viewmodel;

import java.util.List;

import javax.inject.Inject;

import demo.catalog.coursera.org.courserademoapp.domain.Course;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.subjects.BehaviorSubject;

public class CoursesViewModelImpl implements CoursesViewModel {
    public final BehaviorSubject<List<Course>> mCourseList = BehaviorSubject.create();

    @Inject
    public CoursesViewModelImpl() {

    }

    @Override
    public Subscription subscribeToCourseList(Action1<List<Course>> courseListAction) {
        return mCourseList.observeOn(AndroidSchedulers.mainThread()).subscribe(courseListAction);
    }
}
