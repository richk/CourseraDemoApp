package demo.catalog.coursera.org.courserademoapp.domain;

import android.os.Parcel;
import android.os.Parcelable;

public class Course implements Parcelable {
    public String id;
    public String name;
    public String shortName;
    public String smallIcon;

    public Course() {}

    protected Course(Parcel in) {
        id = in.readString();
        name = in.readString();
        shortName = in.readString();
        smallIcon = in.readString();
    }

    public static final Creator<Course> CREATOR = new Creator<Course>() {
        @Override
        public Course createFromParcel(Parcel in) {
            return new Course(in);
        }

        @Override
        public Course[] newArray(int size) {
            return new Course[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(shortName);
        dest.writeString(smallIcon);
    }
}
