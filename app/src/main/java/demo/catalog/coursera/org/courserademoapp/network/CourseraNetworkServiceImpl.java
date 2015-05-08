package demo.catalog.coursera.org.courserademoapp.network;

import com.google.gson.Gson;

import java.util.List;

import javax.inject.Inject;

import demo.catalog.coursera.org.courserademoapp.BuildConfig;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;
import retrofit.http.GET;
import rx.Observable;

public class CourseraNetworkServiceImpl implements CourseraNetworkService {

    public CatalogAPIService mCatalogAPIService;

    RestAdapter catalogRestAdapter;

    public CourseraNetworkServiceImpl() {
        catalogRestAdapter = new RestAdapter.Builder()
                .setEndpoint("https://api.coursera.org")
                .setConverter(new GsonConverter(new Gson()))
                .setLogLevel((BuildConfig.DEBUG ?
                        RestAdapter.LogLevel.FULL : RestAdapter.LogLevel.NONE))
                .build();
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
