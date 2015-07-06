package org.jboss.aerogear.memeolist;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import org.jboss.aerogear.android.authorization.AuthorizationManager;
import org.jboss.aerogear.android.pipe.module.ModuleFields;
import org.jboss.aerogear.memeolist.adapter.CommentAdapter;
import org.jboss.aerogear.memeolist.content.vo.Post;

import java.io.IOException;


/**
 * Created by summers on 6/27/15.
 */
public class MemeDetail extends AppCompatActivity {

    public static final String EXTRA_MEME = "MemeDetails.memeId";
    private Post post;
    private ImageView memeImage;
    private RecyclerView commentsList;
    private AlertDialog dialog;
    private Picasso picasso;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meme_details);
        loadToolbar();

        memeImage = (ImageView) findViewById(R.id.meme_photo);
        post = getIntent().getParcelableExtra(EXTRA_MEME);
        setupPicasso();
        loadMeme();
        loadCommentsList();
        setupFAB();
    }

    private void setupPicasso() {
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

        picasso = new Picasso.Builder(this).downloader(new OkHttpDownloader(picassoClient)).build();

        picasso.setIndicatorsEnabled(true);
        picasso.setLoggingEnabled(true);

    }

    private void setupFAB() {
        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new AlertDialog.Builder(MemeDetail.this)
                        .setTitle("Add Comment")

                        .setPositiveButton("Submit",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        finish();
                                    }
                                }
                        )
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        dialog.dismiss();
                                    }
                                }
                        ).setView(R.layout.add_comment_dialog)
                        .create();


                dialog.show();
            }
        });
    }

    private void loadCommentsList() {
        commentsList = (RecyclerView) findViewById(R.id.comments);
        commentsList.setLayoutManager(new LinearLayoutManager(this));
        commentsList.setAdapter(new CommentAdapter());
    }

    private void loadToolbar() {
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle("Memeolist");


    }

    @Override
    protected void onPause() {
        super.onPause();
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
    }

    private void loadMeme() {
        picasso
                .load(post.getFileUrl().toString())
                .noFade()
                .into(memeImage);
    }



}
