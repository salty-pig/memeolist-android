package org.jboss.aerogear.memeolist.ui;

import android.content.Context;
import android.support.v7.app.AppCompatDialog;
import android.support.v7.widget.RecyclerView;

import org.jboss.aerogear.memeolist.R;

/**
 * Created by summers on 6/30/15.
 */
public class ImagePickerDialog extends AppCompatDialog {

    private RecyclerView listView;

    public ImagePickerDialog(Context context) {
        super(context);
        setContentView(R.layout.meme_list_fragment);
    }



}
