package org.jboss.aerogear.memeolist.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.jboss.aerogear.android.core.Callback;
import org.jboss.aerogear.android.pipe.PipeManager;
import org.jboss.aerogear.memeolist.AccountDetail;
import org.jboss.aerogear.memeolist.MemeDetail;
import org.jboss.aerogear.memeolist.R;
import org.jboss.aerogear.memeolist.adapter.CardOnClickHandler;
import org.jboss.aerogear.memeolist.adapter.MemeAdapter;
import org.jboss.aerogear.memeolist.content.vo.Post;

import java.util.List;


/**
 * Created by summers on 6/7/15.
 */
public class MemeListFragment extends Fragment {

    private RecyclerView gridView;
    private View view;
    private MemeAdapter memeAdapter;

    private final CardOnClickHandler<Post> favoriteOnClickHandler = new CardOnClickHandler<Post>(){
        @Override
        public void onCardClick(Post post, MemeAdapter.ViewHolder view) {
            onFavoriteClicked(post, view);
        }
    };

    private final CardOnClickHandler<Post> feedbackOnClickHandler = new CardOnClickHandler<Post>(){
        @Override
        public void onCardClick(Post post, MemeAdapter.ViewHolder view) {
            onFeedbackClicked(post, view);
        }
    };

    private final CardOnClickHandler<String> authorOnClickHandler = new CardOnClickHandler<String>(){
        @Override
        public void onCardClick(String username, MemeAdapter.ViewHolder view) {
            onAuthorClicked(username, view);
        }
    };


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
        memeAdapter.setFeedbackOnClickHandler(null);
        memeAdapter.setFavoriteOnClickHandler(null);
        memeAdapter.setAuthorOnClickHandler(null);
    }

    @Override
    public void onResume() {
        super.onResume();
        memeAdapter.setFeedbackOnClickHandler(feedbackOnClickHandler);
        memeAdapter.setFavoriteOnClickHandler(favoriteOnClickHandler);
        memeAdapter.setAuthorOnClickHandler(authorOnClickHandler);
        loadPosts();
    }

    private void loadPosts() {
        PipeManager.getPipe("kc-post", this.getActivity()).read(new Callback<List>() {
            @Override
            public void onSuccess(List list) {
                memeAdapter.setPosts(list);
            }

            @Override
            public void onFailure(Exception e) {
                Log.e("ERROR", e.getMessage(), e);
                Toast.makeText(MemeListFragment.this.getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void onFavoriteClicked(Post post, MemeAdapter.ViewHolder view) {
        Toast.makeText(this.getActivity(), "Favorited", Toast.LENGTH_LONG).show();
    }

    private void onFeedbackClicked(Post post, MemeAdapter.ViewHolder view) {
        Intent intent = new Intent(getActivity(), MemeDetail.class);

        intent.putExtra(MemeDetail.EXTRA_MEME, post);

        getActivity().startActivity(intent);
    }

    private void onAuthorClicked(String username, MemeAdapter.ViewHolder view) {
        Intent intent = new Intent(getActivity(), AccountDetail.class);

        intent.putExtra(AccountDetail.EXTRA_USERNAME, username);

        getActivity().startActivity(intent);
    }

}
