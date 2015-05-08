package demo.catalog.coursera.org.courserademoapp.di;

import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import demo.catalog.coursera.org.courserademoapp.BuildConfig;
import demo.catalog.coursera.org.courserademoapp.network.CourseraNetworkService;
import demo.catalog.coursera.org.courserademoapp.network.CourseraNetworkServiceImpl;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

@Module(
        injects = {
                CourseraNetworkServiceImpl.class
        }, library = true)
public class NetworkingModule {

    @Provides @Singleton
    public CourseraNetworkService getNetworkService() {
        return new CourseraNetworkServiceImpl();
    }
}
