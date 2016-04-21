package demo.catalog.coursera.org.courserademoapp.view;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;

public abstract class FragmentPresenter<P, T extends P> implements OldPresenter<P,T> {
    public static final String ARG_FRAGMENT_ID = "simple_presenter_base_fragment_id";
    private final String DATA_FRAGMENT_TAG = "simple_presenter_base_data_fragment";
    private final String CLASS_NAME = ((Object) this).getClass().getCanonicalName();
    protected final Bundle mArguments;
    private final T mData;
    private final boolean mIsExisted;

    public FragmentPresenter(FragmentActivity activity, Bundle arguments, T data,
                               boolean isExisted) {
        mIsExisted = isExisted;
        mArguments = arguments;
        DataFragment dataFragment = (DataFragment) activity.getSupportFragmentManager().findFragmentByTag(
                DATA_FRAGMENT_TAG);
        String dataKey = TextUtils.isEmpty(arguments.getString(ARG_FRAGMENT_ID)) ?
                CLASS_NAME : arguments.getString(ARG_FRAGMENT_ID);
        if (dataFragment == null) {
            dataFragment = new DataFragment();
            activity.getSupportFragmentManager().beginTransaction().add(dataFragment, DATA_FRAGMENT_TAG).commit();
            mData = data;
            dataFragment.setData(dataKey, mData);
        } else {
            T value = (T)dataFragment.getData(dataKey);
            if(value == null) {
                mData = data;
                dataFragment.setData(dataKey, mData);
            } else {
                if(mIsExisted) {
                    mData = value;
                } else {
                    mData = data;
                    dataFragment.setData(dataKey, mData);
                }
            }
        }
    }

    public Bundle getArguments() {
        return mArguments;
    }

    public T getData() {
        return mData;
    }

    public P getViewModel() {
        return mData;
    }

    protected boolean isExisted() {
        return mIsExisted;
    }
}
