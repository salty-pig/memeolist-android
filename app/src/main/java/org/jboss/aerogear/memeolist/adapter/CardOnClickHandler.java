package org.jboss.aerogear.memeolist.adapter;

import org.jboss.aerogear.memeolist.content.vo.Meme;
import org.jboss.aerogear.memeolist.content.vo.Post;

/**
 * Created by summers on 6/28/15.
 */
public interface CardOnClickHandler {
    public void onCardClick(Post post, MemeAdapter.ViewHolder view);
}
