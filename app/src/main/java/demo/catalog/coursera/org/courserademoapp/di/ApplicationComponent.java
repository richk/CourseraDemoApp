package demo.catalog.coursera.org.courserademoapp.di;

import android.support.v7.app.AppCompatActivity;

import javax.inject.Singleton;

import dagger.Component;
import demo.catalog.coursera.org.courserademoapp.network.CourseraNetworkService;

@Singleton
@Component(modules={ApplicationModule.class, NetworkingModule.class})
public interface ApplicationComponent {
    CourseraNetworkService networkService();
}
