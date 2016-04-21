package demo.catalog.coursera.org.courserademoapp.network;

import com.google.gson.Gson;

import rx.Observable;

public class CourseraNetworkServiceImpl implements CourseraNetworkService {

    public CatalogAPIService mCatalogAPIService;

    public CourseraNetworkServiceImpl(CatalogAPIService apiService) {
        mCatalogAPIService = apiService;
    }

    @Override
    public Observable<JSCourseResponse> getCourses() {
        return mCatalogAPIService.getCourses();
    }
}
