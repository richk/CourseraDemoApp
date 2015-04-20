package demo.catalog.coursera.org.courserademoapp.network;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

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
