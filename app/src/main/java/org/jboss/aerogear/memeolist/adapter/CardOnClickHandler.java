package org.jboss.aerogear.memeolist.adapter;

/**
 * Created by summers on 6/28/15.
 */
public interface CardOnClickHandler<T> {
    public void onCardClick(T post, MemeAdapter.ViewHolder view);
}
