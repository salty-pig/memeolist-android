package org.jboss.aerogear.memeolist;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.jboss.aerogear.memeolist.adapter.CommentAdapter;
import org.jboss.aerogear.memeolist.model.Meme;

/**
 * Created by summers on 6/27/15.
 */
public class MemeDetail extends AppCompatActivity {

    public static final String EXTRA_MEME = "MemeDetails.memeId";
    private Meme meme;
    private ImageView memeImage;
    private RecyclerView commentsList;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meme_details);
        loadToolbar();

        memeImage = (ImageView) findViewById(R.id.meme_photo);
        meme = getIntent().getParcelableExtra(EXTRA_MEME);
        loadMeme();
        loadCommentsList();
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

    private void loadMeme() {
        Picasso.with(this)
                .load(meme.getFileUrl().toString())
                .noFade()
                .into(memeImage);
    }



}
