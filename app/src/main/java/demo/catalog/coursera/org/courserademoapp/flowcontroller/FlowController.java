package demo.catalog.coursera.org.courserademoapp.flowcontroller;

import android.content.Context;
import android.content.Intent;

import demo.catalog.coursera.org.courserademoapp.view.CatalogActivity;

public class FlowController {

    public static void launchCatalogActivity(Context context) {
        Intent intent = new Intent(context, CatalogActivity.class);
        context.startActivity(intent);
    }
}
