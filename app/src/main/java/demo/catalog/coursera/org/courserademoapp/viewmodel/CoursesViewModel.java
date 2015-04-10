package demo.catalog.coursera.org.courserademoapp.viewmodel;

import java.util.List;

import demo.catalog.coursera.org.courserademoapp.domain.Course;
import rx.Subscription;
import rx.functions.Action1;

public interface CoursesViewModel {
    public Subscription subscribeToCourseList(Action1<List<Course>> courseListAction);
}
