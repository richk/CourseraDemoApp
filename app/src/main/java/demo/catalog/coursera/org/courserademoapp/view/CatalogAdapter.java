package demo.catalog.coursera.org.courserademoapp.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import demo.catalog.coursera.org.courserademoapp.R;
import demo.catalog.coursera.org.courserademoapp.domain.Course;
import demo.catalog.coursera.org.courserademoapp.network.CourseraNetworkServiceImpl;

public class CatalogAdapter extends ArrayAdapter<Course> {

    List<Course> mCourses;

    @Inject
    LayoutInflater mInflater;

    @Inject
    public CatalogAdapter(@Named("application") Context context) {
        super(context, R.layout.catalog_list_item);
        Log.d("CatalogAdapter", "CatalogAdapter");
    }

    @Override
    public int getCount() {
        if (mCourses == null) {
            return 0;
        } else {
            return mCourses.size();
        }
    }

    public void setCourses(List<Course> courses) {
        clear();
        mCourses = courses;
        addAll(courses);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d("CatalogAdapter", "getView");
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.catalog_list_item, parent,
                    false);
            CourseItemHolder courseItemHolder = new CourseItemHolder();
            courseItemHolder.tvCourse = (TextView) convertView.findViewById(R.id.course_name);
            courseItemHolder.imgCourse = (ImageView) convertView.findViewById(R.id.course_image);
            convertView.setTag(courseItemHolder);
        }
        Course course = getItem(position);
        Log.d("CatalogAdapter", "Course:" + course.shortName);
        CourseItemHolder holder = (CourseItemHolder)convertView.getTag();
        holder.tvCourse.setText(course.name);
        Picasso.with(getContext()).load(course.smallIcon).into(holder.imgCourse);
        return convertView;
    }

    static class CourseItemHolder {
        public TextView tvCourse;
        public ImageView imgCourse;
    }
}
