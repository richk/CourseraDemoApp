package demo.catalog.coursera.org.courserademoapp.flowcontroller;

import android.content.Context;
import android.content.Intent;

import demo.catalog.coursera.org.courserademoapp.view.CatalogActivity;

public class FlowController {
    private static FlowController mFlowController;

    private FlowController() {

    }

    public static FlowController getInstance() {
        if (mFlowController == null) {
            mFlowController = new FlowController();
        }

        return mFlowController;
    }

    public void launchCatalogActivity(Context context) {
        Intent intent = new Intent(context, CatalogActivity.class);
        context.startActivity(intent);
    }
}
