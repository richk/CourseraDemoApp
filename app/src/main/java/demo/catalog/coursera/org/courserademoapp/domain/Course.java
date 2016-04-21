package demo.catalog.coursera.org.courserademoapp.domain;

import android.os.Parcelable;

import auto.parcel.AutoParcel;

@AutoParcel
public abstract class Course implements Parcelable {
    public abstract String id();
    public abstract String name();
    public abstract String shortName();
    public abstract String smallIcon();

    static Course create(String id, String name, String shortName, String smallIcon) {
        return new AutoParcel_Course(id, name, shortName, smallIcon);
    }
}
