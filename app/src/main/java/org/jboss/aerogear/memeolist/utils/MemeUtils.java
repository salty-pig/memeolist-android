package org.jboss.aerogear.memeolist.utils;

import android.content.Context;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.jboss.aerogear.memeolist.R;
import org.jboss.aerogear.memeolist.model.Meme;

import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by summers on 6/29/15.
 */
public final class MemeUtils {
    private static List<Meme> memes;

    private MemeUtils(){}

    public synchronized static List<Meme> getMemes(Context context) {
        if (memes == null) {


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

        return new ArrayList<>(memes);
    }

}
