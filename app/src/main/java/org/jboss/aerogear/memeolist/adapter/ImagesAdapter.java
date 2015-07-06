package org.jboss.aerogear.memeolist.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import org.jboss.aerogear.android.authorization.AuthorizationManager;
import org.jboss.aerogear.android.pipe.module.ModuleFields;
import org.jboss.aerogear.memeolist.R;
import org.jboss.aerogear.memeolist.content.vo.Post;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by summers on 6/29/15.
 */
public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.Holder> {
    private final List<Post> posts;
    private final Context appContext;
    private CardOnClickHandler cardOnClickHandler;
    private final Picasso picasso;

    public ImagesAdapter(Context applicationContext) {
        this.appContext = applicationContext;
        posts = new ArrayList<>();
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

        picasso = new Picasso.Builder(appContext).downloader(new OkHttpDownloader(picassoClient)).build();

        picasso.setIndicatorsEnabled(true);
        picasso.setLoggingEnabled(true);

    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.meme_photo, parent, false);

        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        final Post post = posts.get(position);
        try {
            picasso
                    .load(post.getFileUrl())
                    .into(holder.view);

            holder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (cardOnClickHandler != null) {
                        cardOnClickHandler.onCardClick(post, null);
                    }
                }
            });

        } catch (Exception e) {
            throw new RuntimeException((e));
        }
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public static class Holder extends RecyclerView.ViewHolder {

        public ImageView view;

        public Holder(View itemView) {
            super(itemView);
            view = (ImageView) itemView.findViewById(R.id.image);
        }
    }

    public CardOnClickHandler getCardOnClickHandler() {
        return cardOnClickHandler;
    }

    public void setCardOnClickHandler(CardOnClickHandler cardOnClickHandler) {
        this.cardOnClickHandler = cardOnClickHandler;
    }
}
