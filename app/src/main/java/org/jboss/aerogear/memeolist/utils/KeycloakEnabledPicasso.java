package org.jboss.aerogear.memeolist.utils;

import android.content.Context;
import android.util.Pair;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import org.jboss.aerogear.android.authorization.AuthorizationManager;
import org.jboss.aerogear.android.pipe.module.ModuleFields;

import java.io.IOException;

/**
 * Created by secondsun on 9/17/15.
 */
public final class KeycloakEnabledPicasso {
    public static Picasso getPicasso(Context appContext) {
        appContext = appContext.getApplicationContext();
        OkHttpClient picassoClient = new OkHttpClient();

        picassoClient.interceptors().add(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                ModuleFields fields = AuthorizationManager.getModule("KeyCloakAuthz").loadModule(null, null, null);
                Pair<String, String> header = fields.getHeaders().get(0);
                Request newRequest = chain.request().newBuilder()
                        .addHeader(header.first, header.second)
                        .build();
                return chain.proceed(newRequest);
            }
        });

        Picasso picasso = new Picasso.Builder(appContext).downloader(new OkHttpDownloader(picassoClient)).build();

        picasso.setIndicatorsEnabled(true);
        picasso.setLoggingEnabled(true);
        return picasso;
    }
}
