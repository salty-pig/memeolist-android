package org.jboss.aerogear.memeolist.content;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.google.gson.Gson;

import org.jboss.aerogear.android.store.generator.DefaultIdGenerator;
import org.jboss.aerogear.android.store.sql.SQLStore;
import org.jboss.aerogear.memeolist.content.contract.PostContract;
import org.jboss.aerogear.memeolist.content.contract.RedHatUserContract;
import org.jboss.aerogear.memeolist.content.vo.Post;
import org.jboss.aerogear.memeolist.content.vo.RedHatUser;
import org.jboss.aerogear.memeolist.utils.CountDownCallback;
import org.jboss.aerogear.memeolist.utils.GsonUtils;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class MemeolistContentProvider extends ContentProvider {

    private static final String TAG = MemeolistContentProvider.class.getSimpleName();

    private static final Gson GSON = GsonUtils.GSON;

    private static ContentResolver resolver;
    private static Context context;

    private SQLStore<Post> postStore;
    private SQLStore<RedHatUser> userStore;
    private final CountDownLatch createdLatch = new CountDownLatch(2);

    public MemeolistContentProvider() {
    }

    @Override
    public boolean onCreate() {
        resolver = getContext().getContentResolver();
        context = getContext();

        postStore = new SQLStore<>(Post.class, getContext(), GsonUtils.builder(), new DefaultIdGenerator());
        postStore.open(new CountDownCallback<SQLStore<Post>>(createdLatch));
        userStore = new SQLStore<>(RedHatUser.class, getContext(), GsonUtils.builder(), new DefaultIdGenerator());
        userStore.open(new CountDownCallback<SQLStore<RedHatUser>>(createdLatch));
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        if (uri.equals(PostContract.URI)) {
            return execute(uri, null, selection, selectionArgs, new PostQuery());
        } else if (uri.equals(RedHatUserContract.URI)) {
            return execute(uri, null, selection, selectionArgs, new RedHatUserQuery());
        } else
            throw new IllegalArgumentException(String.format("%s not supported", uri.toString()));
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        if (uri.equals(PostContract.URI)) {
            return uri.toString();
        } else if (uri.equals(RedHatUserContract.URI)) {
            return uri.toString();
        } else
            throw new IllegalArgumentException(String.format("%s not supported", uri.toString()));
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private <T> T execute(final Uri uri, final ContentValues[] values, final String selection, final String[] selectionArgs, final Operation<T> op) {
        final AtomicReference<T> returnRef = new AtomicReference<T>();

        try {
            createdLatch.await(20, TimeUnit.SECONDS);//make sure the databases were created.
        } catch (InterruptedException e) {
            Log.e(TAG, e.getMessage(), e);
        }

        SQLStore tempStore;
        if (uri.equals(PostContract.URI)) {
            tempStore = postStore;
        } else if (uri.equals(RedHatUserContract.URI)) {
            tempStore = userStore;
        } else {
            throw new IllegalArgumentException(String.format("%s not supported", uri.toString()));
        }

        final SQLStore store = tempStore;


        synchronized (TAG) {
            returnRef.set(op.exec(GSON, store, uri, values, selection, selectionArgs));
        }
        return returnRef.get();
    }

    private interface Operation<T> {
        T exec(Gson gson, SQLStore calendarStore, Uri uri, ContentValues[] values, String selection, String[] selectionArgs);
    }


    private static class PostQuery implements Operation<Cursor> {

        @Override
        public SingleColumnJsonArrayList exec(Gson gson, SQLStore postStore, Uri uri, ContentValues[] values, String selection, String[] selectionArgs) {

            return new SingleColumnJsonArrayList(new ArrayList<Post>(postStore.readAll()));
        }
    }


    private static class RedHatUserQuery implements Operation<Cursor> {

        @Override
        public SingleColumnJsonArrayList exec(Gson gson, SQLStore userStore, Uri uri, ContentValues[] values, String selection, String[] selectionArgs) {

            return new SingleColumnJsonArrayList(new ArrayList<RedHatUserQuery>(userStore.readAll()));
        }
    }

}
