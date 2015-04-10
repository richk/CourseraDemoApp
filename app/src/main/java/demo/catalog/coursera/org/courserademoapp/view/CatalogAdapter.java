package demo.catalog.coursera.org.courserademoapp.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import demo.catalog.coursera.org.courserademoapp.R;
import demo.catalog.coursera.org.courserademoapp.domain.Course;
import demo.catalog.coursera.org.courserademoapp.network.CourseraNetworkServiceImpl;

public class CatalogAdapter extends ArrayAdapter<Course> {

    public CatalogAdapter(Context context) {
        super(context, R.layout.catalog_list_item);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.catalog_list_item, parent,
                    false);
            CourseItemHolder courseItemHolder = new CourseItemHolder();
            courseItemHolder.tvCourse = (TextView) convertView.findViewById(R.id.course_name);
            courseItemHolder.imgCourse = (ImageView) convertView.findViewById(R.id.course_image);
            convertView.setTag(courseItemHolder);
        }
        Course course = getItem(position);
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
