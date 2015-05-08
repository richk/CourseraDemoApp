package demo.catalog.coursera.org.courserademoapp.view;

import android.os.Bundle;
import android.os.Parcelable;

public abstract class ParcelablePresenterImpl<P, T extends P>
        implements ParcelablePresenter<P, T> {
    private final String mDataKey = ((Object)this).getClass().getCanonicalName();

    protected final Bundle mArguments;
    private boolean mExisted;
    private T mData;


    public ParcelablePresenterImpl(Bundle arguments, Bundle savedInstanceState, T data) {
        mArguments = arguments;
        mExisted = savedInstanceState != null;
        mData = mExisted ? (T)savedInstanceState.getParcelable(mDataKey) : data ;
    }

    public boolean isExisted() {
        return mExisted;
    }

    public Bundle getArguments() {
        return mArguments;
    }

    public T getData() {
        return mData;
    }

    public P getViewModel() {
        return mData != null ? (P)mData : null;
    }

    public void onSave(Bundle savedInstanceState) {
        if(mData != null && mData instanceof Parcelable) {
            savedInstanceState.putParcelable(mDataKey, (Parcelable)mData);
        }
    }
}

