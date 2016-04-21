package demo.catalog.coursera.org.courserademoapp.network;

import retrofit.http.GET;
import rx.Observable;

public interface CatalogAPIService {
    @GET("/api/catalog.v1/courses?fields=smallIcon")
    Observable<JSCourseResponse> getCourses();
}
