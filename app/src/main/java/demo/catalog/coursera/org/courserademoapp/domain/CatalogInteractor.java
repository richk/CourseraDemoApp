package demo.catalog.coursera.org.courserademoapp.domain;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import demo.catalog.coursera.org.courserademoapp.network.CourseraNetworkService;
import demo.catalog.coursera.org.courserademoapp.network.JSCourse;
import demo.catalog.coursera.org.courserademoapp.network.JSCourseResponse;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

public class CatalogInteractor implements Interactor {

    @Inject
    CourseraNetworkService mNetworkService;

    @Inject
    public CatalogInteractor() {

    }

    @Override
    public Observable<List<Course>> getCourseObservable() {
        Observable<List<Course>> courseListObservable = mNetworkService.getCourses()
                .map(new Func1<JSCourseResponse, List<Course>>() {
                    @Override
                    public List<Course> call(JSCourseResponse jsCourseResponse) {
                        JSCourse[] jsCourses = jsCourseResponse.elements;
                        List<Course> courses = new ArrayList<Course>();
                        for (JSCourse jsCourse : jsCourses) {
                            Course course = new Course();
                            course.id = jsCourse.id;
                            course.name = jsCourse.name;
                            course.shortName = jsCourse.shortName;
                            course.smallIcon = jsCourse.smallIcon;
                            courses.add(course);
                        }
                        return courses;
                    }
                }).doOnError(new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.e("CatalogInteractor", "Error while getting data", throwable);
                    }
                });
        return courseListObservable;
    }
}
