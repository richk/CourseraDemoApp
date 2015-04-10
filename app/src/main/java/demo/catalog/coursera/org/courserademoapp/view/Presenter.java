package demo.catalog.coursera.org.courserademoapp.view;

import android.os.Bundle;

public interface Presenter<P, T extends P> {
    Bundle getArguments();
    P getViewModel();
}
