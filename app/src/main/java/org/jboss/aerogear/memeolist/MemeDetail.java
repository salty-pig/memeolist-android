package org.jboss.aerogear.memeolist;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AlertDialog;
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
    private AlertDialog dialog;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meme_details);
        loadToolbar();

        memeImage = (ImageView) findViewById(R.id.meme_photo);
        meme = getIntent().getParcelableExtra(EXTRA_MEME);
        loadMeme();
        loadCommentsList();
        setupFAB();
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
        Picasso.with(this)
                .load(meme.getFileUrl().toString())
                .noFade()
                .into(memeImage);
    }



}
