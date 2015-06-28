package org.jboss.aerogear.memeolist.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.squareup.picasso.Picasso;

import org.jboss.aerogear.memeolist.MemeDetail;
import org.jboss.aerogear.memeolist.R;
import org.jboss.aerogear.memeolist.model.Meme;
import org.jboss.aerogear.memeolist.utils.GsonUtils;
import org.jboss.aerogear.memeolist.utils.UIUtils;

import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by summers on 6/18/15.
 */
public class MemeAdapter extends RecyclerView.Adapter<MemeAdapter.ViewHolder> {

    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    private final List<Meme> memes;
    private final Context appContext;
    private CardOnClickHandler cardOnClickHandler;

    public MemeAdapter(Context context) {
        this.appContext = context.getApplicationContext();
        memes = new ArrayList<Meme>();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
        JsonParser parser = new JsonParser();
        InputStreamReader reader = new InputStreamReader(context.getResources().openRawResource(R.raw.meme));
        JsonElement json = parser.parse(reader);
        JsonArray memesArray = json.getAsJsonObject().get("memes").getAsJsonArray();
        for (int i = 0; i < memesArray.size(); i++) {
            JsonObject meme = memesArray.get(i).getAsJsonObject();
            long memeId = memes.size();
            String posted = meme.get("posted").getAsString();
            String fileUrl = meme.get("fileUrl").getAsString();
            String comment = meme.get("comment").getAsString();
            JsonObject owner = meme.get("owner").getAsJsonObject();
            long ownerId = owner.get("id").getAsLong();
            Meme memeObject = new Meme();
            memeObject.setComment(comment);
            try {
                memeObject.setFileUrl(new URL(fileUrl));
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
            memeObject.setId(memeId);
            memeObject.setOwnerId(ownerId);
            try {
                memeObject.setPosted(format.parse(posted));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            memes.add(memeObject);

        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.meme_card, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Meme meme = memes.get(position);
        holder.favoriteCount.setText("");
        holder.feedbackCount.setText("");

        try {
            Picasso.with(appContext)
                    .load(meme.getFileUrl().toString())
                    .into(holder.memePhoto);

        } catch (Exception e) {
            throw new RuntimeException((e));
        }
        holder.postedDate.setText(FORMAT.format(meme.getPosted()));
        UIUtils.setTextWithUnderline(holder.creator, "secondsun");

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cardOnClickHandler != null) {
                    cardOnClickHandler.onCardClick(meme, holder);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return memes.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView postedDate;
        public final ImageView memePhoto;
        public final TextView creator;
        final TextView feedbackCount;
        final TextView favoriteCount;
        public final CardView cardView;


        public ViewHolder(View itemView) {
            super(itemView);
            View view = itemView;
            postedDate = (TextView) view.findViewById(R.id.posted_date);
            memePhoto = (ImageView) view.findViewById(R.id.meme_photo);
            feedbackCount = (TextView) view.findViewById(R.id.feedback_count);
            favoriteCount = (TextView) view.findViewById(R.id.favorite_count);
            creator = (TextView) view.findViewById(R.id.creator);
            cardView= (CardView) view.findViewById(R.id.card_view);

        }


    }

    public CardOnClickHandler getCardOnClickHandler() {
        return cardOnClickHandler;
    }

    public void setCardOnClickHandler(CardOnClickHandler cardOnClickHandler) {
        this.cardOnClickHandler = cardOnClickHandler;
    }
}
