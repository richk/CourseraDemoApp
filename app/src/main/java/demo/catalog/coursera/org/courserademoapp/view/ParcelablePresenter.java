package demo.catalog.coursera.org.courserademoapp.view;

import android.os.Bundle;

public interface ParcelablePresenter<P, T extends P> {
    boolean isExisted();

    Bundle getArguments();

    T getData();

    P getViewModel();

    void onSave(Bundle savedInstanceState);
}