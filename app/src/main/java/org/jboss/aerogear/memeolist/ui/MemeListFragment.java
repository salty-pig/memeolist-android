package org.jboss.aerogear.memeolist.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import org.jboss.aerogear.memeolist.R;
import org.jboss.aerogear.memeolist.adapter.MemeAdapter;

/**
 * Created by summers on 6/7/15.
 */
public class MemeListFragment extends Fragment {
    
    RecyclerView gridView;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.meme_list_fragment, null);
        gridView = (RecyclerView) view.findViewById(R.id.memes);
        gridView.setLayoutManager(new LinearLayoutManager(getActivity()));
        gridView.setAdapter(new MemeAdapter(getActivity()));
        return view;
    }
}
