package org.jboss.aerogear.memeolist.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
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

import org.jboss.aerogear.memeolist.R;
import org.jboss.aerogear.memeolist.model.Meme;
import org.jboss.aerogear.memeolist.utils.GsonUtils;

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

    private final List<Meme> memes;

    public MemeAdapter(Context context) {
        memes = new ArrayList<>();
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
    public void onBindViewHolder(ViewHolder holder, int position) {
        Meme meme = memes.get(position);
        holder.favoriteCount.setText("1024");
        holder.feedbackCount.setText(position + "");
        try {
            holder.memePhoto.setImageURI(Uri.parse(meme.getFileUrl().toString()));
        } catch (Exception e) {
            throw new RuntimeException((e));
        }
        holder.postedDate.setText(meme.getPosted().toString());

    }

    @Override
    public int getItemCount() {
        return memes.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        final TextView postedDate;
        final ImageView memePhoto;
        final TextView feedbackCount;
        final TextView favoriteCount;

        public ViewHolder(View itemView) {
            super(itemView);
            View view = itemView;
            postedDate = (TextView) view.findViewById(R.id.posted_date);
            memePhoto = (ImageView) view.findViewById(R.id.meme_photo);
            feedbackCount = (TextView) view.findViewById(R.id.feedback_count);
            favoriteCount = (TextView) view.findViewById(R.id.favorite_count);
        }


    }
}