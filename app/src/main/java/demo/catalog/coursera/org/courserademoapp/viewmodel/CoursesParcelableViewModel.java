package demo.catalog.coursera.org.courserademoapp.viewmodel;

import android.os.Parcelable;

import java.util.List;

import auto.parcel.AutoParcel;
import demo.catalog.coursera.org.courserademoapp.domain.Course;

@AutoParcel
public abstract class CoursesParcelableViewModel implements Parcelable {
    public abstract List<Course> courseList();

    public static CoursesParcelableViewModel create(List<Course> courses) {
        return new AutoParcel_CoursesParcelableViewModel(courses);
    }
}
