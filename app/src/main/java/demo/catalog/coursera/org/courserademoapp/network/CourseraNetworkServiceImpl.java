package demo.catalog.coursera.org.courserademoapp.network;

import com.google.gson.Gson;

import java.util.List;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;
import retrofit.http.GET;
import rx.Observable;

public class CourseraNetworkServiceImpl implements CourseraNetworkService {

    private static CourseraNetworkServiceImpl mCourseraNetworkService;

    public CatalogAPIService mCatalogAPIService;

    private RestAdapter catalogRestAdapter = new RestAdapter.Builder()
            .setEndpoint("https://api.coursera.org")
            .setConverter(new GsonConverter(new Gson()))
            .build();

    public static CourseraNetworkServiceImpl getInstance() {
        if (mCourseraNetworkService == null) {
            mCourseraNetworkService = new CourseraNetworkServiceImpl();
        }
        return mCourseraNetworkService;
    }

    private CourseraNetworkServiceImpl() {
        mCatalogAPIService = catalogRestAdapter.create(CatalogAPIService.class);

    }

    @Override
    public Observable<JSCourseResponse> getCourses() {
        return mCatalogAPIService.getCourses();
    }

    public interface CatalogAPIService {
        @GET("/api/catalog.v1/courses?fields=smallIcon")
        Observable<JSCourseResponse> getCourses();
    }
}
