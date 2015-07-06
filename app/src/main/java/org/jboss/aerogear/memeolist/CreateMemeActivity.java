package org.jboss.aerogear.memeolist;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.jboss.aerogear.memeolist.adapter.CardOnClickHandler;
import org.jboss.aerogear.memeolist.adapter.ImagesAdapter;
import org.jboss.aerogear.memeolist.adapter.MemeAdapter;
import org.jboss.aerogear.memeolist.content.vo.Post;


public class CreateMemeActivity extends AppCompatActivity implements CardOnClickHandler {

    private RecyclerView images;
    private ImagesAdapter adapter;
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_meme);
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.setCardOnClickHandler(this);

    }

    @Override
    protected void onPause() {
        super.onPause();
        adapter.setCardOnClickHandler(null);
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
    }

    @Override
    public void onCardClick(final Post post, MemeAdapter.ViewHolder ignore) {
         dialog = new AlertDialog.Builder(this)
                .setTitle("Set Caption")

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
                ).setView(R.layout.create_meme_dialog)
                .create();


         dialog.show();
    }


}
