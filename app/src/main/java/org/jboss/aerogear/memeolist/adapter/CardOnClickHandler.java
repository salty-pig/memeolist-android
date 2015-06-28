package org.jboss.aerogear.memeolist.adapter;

import android.support.v7.widget.CardView;

import org.jboss.aerogear.memeolist.model.Meme;

/**
 * Created by summers on 6/28/15.
 */
public interface CardOnClickHandler {
    public void onCardClick(Meme meme, MemeAdapter.ViewHolder view);
}
