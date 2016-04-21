package demo.catalog.coursera.org.courserademoapp.view;

import android.os.Bundle;

import rx.Subscription;
import rx.functions.Action1;

public interface Presenter {
    public void onCreate(Bundle savedInstanceState);
    public Subscription onResume(Action1 loadAction);
    public void onPause(Bundle savedInstanceState);
}
