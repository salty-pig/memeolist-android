package org.jboss.aerogear.memeolist.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import org.jboss.aerogear.memeolist.R;
import org.jboss.aerogear.memeolist.model.Meme;
import org.jboss.aerogear.memeolist.utils.MemeUtils;

import java.util.List;

/**
 * Created by summers on 6/29/15.
 */
public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.Holder> {
    private final List<Meme> memes;
    private final Context appContext;
    private CardOnClickHandler cardOnClickHandler;

    public ImagesAdapter(Context applicationContext) {
        this.appContext = applicationContext;
        memes = MemeUtils.getMemes(applicationContext);
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.meme_photo, parent, false);

        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        final Meme meme = memes.get(position);
        try {
            Picasso.with(appContext)
                    .load(meme.getFileUrl().toString())
                    .into(holder.view);

            holder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (cardOnClickHandler != null) {
                        cardOnClickHandler.onCardClick(meme, null);
                    }
                }
            });

        } catch (Exception e) {
            throw new RuntimeException((e));
        }
    }

    @Override
    public int getItemCount() {
        return memes.size();
    }

    public static class Holder extends RecyclerView.ViewHolder {

        public ImageView view;

        public Holder(View itemView) {
            super(itemView);
            view = (ImageView) itemView.findViewById(R.id.image);
        }
    }

    public CardOnClickHandler getCardOnClickHandler() {
        return cardOnClickHandler;
    }

    public void setCardOnClickHandler(CardOnClickHandler cardOnClickHandler) {
        this.cardOnClickHandler = cardOnClickHandler;
    }
}
