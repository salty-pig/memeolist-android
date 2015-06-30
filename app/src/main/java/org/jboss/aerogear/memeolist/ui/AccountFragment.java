package org.jboss.aerogear.memeolist.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.CursorLoader;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;

import org.jboss.aerogear.android.pipe.loader.LoaderAdapter;
import org.jboss.aerogear.memeolist.R;

/**
 * Created by summers on 6/22/15.
 */
public class AccountFragment extends Fragment {

    private View view;
    private ImageView avatar;
    private AlertDialog dialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.account_fragment, null);
        avatar = (ImageView) view.findViewById(R.id.avatar);
        avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        }
        });
        return view;
    }

}
