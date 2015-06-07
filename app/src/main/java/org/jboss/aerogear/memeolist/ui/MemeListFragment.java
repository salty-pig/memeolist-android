package org.jboss.aerogear.memeolist.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.jboss.aerogear.memeolist.R;

/**
 * Created by summers on 6/7/15.
 */
public class MemeListFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.meme_card, null);
    }
}
