package demo.catalog.coursera.org.courserademoapp.flowcontroller;

import android.content.Context;
import android.content.Intent;

import demo.catalog.coursera.org.courserademoapp.view.CatalogActivity;

public class CatalogFlowController {
    private static CatalogFlowController mFlowController;

    private CatalogFlowController() {

    }

    public static CatalogFlowController getInstance() {
        if (mFlowController == null) {
            mFlowController = new CatalogFlowController();
        }

        return mFlowController;
    }

    public void launchCatalogActivity(Context context) {
        Intent intent = new Intent(context, CatalogActivity.class);
        context.startActivity(intent);
    }
}
