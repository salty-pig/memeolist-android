package org.jboss.aerogear.memeolist.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import org.apache.commons.io.IOUtils;
import org.jboss.aerogear.memeolist.R;
import org.jboss.aerogear.memeolist.utils.KeycloakEnabledPicasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by summers on 6/29/15.
 */
public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.Holder> {
    private final List<String> imageUrls;
    private final Context appContext;
    private CardOnClickHandler<String> cardOnClickHandler;
    private final Picasso picasso;

    public ImagesAdapter(Context applicationContext) {
        this.appContext = applicationContext;
        try {
            imageUrls = new ArrayList<>(
                    IOUtils.readLines(applicationContext.getResources().openRawResource(R.raw.meme))
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        picasso = KeycloakEnabledPicasso.getPicasso(appContext);

    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.meme_photo, parent, false);

        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        final String imageUrl = imageUrls.get(position);
        try {
            picasso
                    .load(imageUrl)
                    .into(holder.view);

            holder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (cardOnClickHandler != null) {
                        cardOnClickHandler.onCardClick(imageUrl, null);
                    }
                }
            });

        } catch (Exception e) {
            throw new RuntimeException((e));
        }
    }

    @Override
    public int getItemCount() {
        return imageUrls.size();
    }

    public static class Holder extends RecyclerView.ViewHolder {

        public ImageView view;

        public Holder(View itemView) {
            super(itemView);
            view = (ImageView) itemView.findViewById(R.id.image);
        }
    }

    public CardOnClickHandler<String> getCardOnClickHandler() {
        return cardOnClickHandler;
    }

    public void setCardOnClickHandler(CardOnClickHandler<String> cardOnClickHandler) {
        this.cardOnClickHandler = cardOnClickHandler;
    }
}
