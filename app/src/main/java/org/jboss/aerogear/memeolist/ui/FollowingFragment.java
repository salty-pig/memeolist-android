package org.jboss.aerogear.memeolist.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.jboss.aerogear.memeolist.MemeDetail;
import org.jboss.aerogear.memeolist.R;
import org.jboss.aerogear.memeolist.adapter.CardOnClickHandler;
import org.jboss.aerogear.memeolist.adapter.MemeAdapter;
import org.jboss.aerogear.memeolist.model.Meme;

/**
 * Created by summers on 6/22/15.
 */
public class FollowingFragment extends Fragment implements CardOnClickHandler {
    private RecyclerView gridView;
    private View view;
    private MemeAdapter memeAdapter;

    public static MemeListFragment newInstance() {
        return new MemeListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.meme_list_fragment, null);
        gridView = (RecyclerView) view.findViewById(R.id.memes);
        gridView.setLayoutManager(new LinearLayoutManager(getActivity()));
        memeAdapter = new MemeAdapter(getActivity());
        gridView.setAdapter(memeAdapter);
        return view;

    }

    @Override
    public void onPause() {
        super.onPause();
        memeAdapter.setCardOnClickHandler(null);
    }

    @Override
    public void onResume() {
        super.onResume();
        memeAdapter.setCardOnClickHandler(this);
    }

    @Override
    public void onCardClick(Meme meme, MemeAdapter.ViewHolder view) {
        Intent intent = new Intent(getActivity(), MemeDetail.class);

        intent.putExtra(MemeDetail.EXTRA_MEME, meme);

        getActivity().startActivity(intent);
    }
}
