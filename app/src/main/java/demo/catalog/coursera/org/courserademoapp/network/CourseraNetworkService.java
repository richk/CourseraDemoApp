package demo.catalog.coursera.org.courserademoapp.network;

import java.util.List;

import rx.Observable;

public interface CourseraNetworkService {
    public Observable<JSCourseResponse> getCourses();
}
