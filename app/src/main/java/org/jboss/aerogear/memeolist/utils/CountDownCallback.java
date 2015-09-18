package org.jboss.aerogear.memeolist.utils;

import android.util.Log;

import org.jboss.aerogear.android.core.Callback;

import java.util.concurrent.CountDownLatch;

/**
 * Created by summers on 6/1/15.
 */
public class CountDownCallback<T>  implements Callback<T> {

    private static final String TAG = CountDownCallback.class.getSimpleName();
    private final CountDownLatch latch;

    public CountDownCallback(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void onSuccess(T data) {
        latch.countDown();
    }

    @Override
    public void onFailure(Exception e) {
        latch.countDown();
        Log.e(TAG, e.getMessage(), e);
    }
}