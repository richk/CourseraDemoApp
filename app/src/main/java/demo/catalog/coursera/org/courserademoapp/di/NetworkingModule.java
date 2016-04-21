package demo.catalog.coursera.org.courserademoapp.di;

import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import demo.catalog.coursera.org.courserademoapp.BuildConfig;
import demo.catalog.coursera.org.courserademoapp.network.CatalogAPIService;
import demo.catalog.coursera.org.courserademoapp.network.CourseraNetworkService;
import demo.catalog.coursera.org.courserademoapp.network.CourseraNetworkServiceImpl;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

@Module
public class NetworkingModule {

    private final String mBaseUrl;

    public NetworkingModule(String baseUrl) {
        mBaseUrl = baseUrl;
    }

    @Provides @Singleton
    public CourseraNetworkService getNetworkService(CatalogAPIService apiService) {
        return new CourseraNetworkServiceImpl(apiService);
    }

    @Provides @Singleton
    public RestAdapter provideRestAdapter() {
        return new RestAdapter.Builder()
                .setEndpoint(mBaseUrl)
                .setConverter(new GsonConverter(new Gson()))
                .setLogLevel((BuildConfig.DEBUG ?
                        RestAdapter.LogLevel.FULL : RestAdapter.LogLevel.NONE))
                .build();
    }

    @Provides @Singleton
    public CatalogAPIService provideCatalogAPIService(RestAdapter restAdapter) {
        return restAdapter.create(CatalogAPIService.class);
    }
}
