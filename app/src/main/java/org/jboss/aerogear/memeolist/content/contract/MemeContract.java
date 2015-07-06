package org.jboss.aerogear.memeolist.content.contract;

import android.net.Uri;

import com.google.gson.Gson;

import org.jboss.aerogear.memeolist.utils.GsonUtils;

public final class MemeContract {
    public static final Uri URI = Uri.parse("content://org.jboss.aerogear.memeolist/Meme");

    public static final String DATA = "DATA";
    public static final String ID = "ID";
    public static final Integer DATA_IDX = 0;
    public static final String NOTIFY = "NOTIFY";
    public static final Integer NOTIFY_IDX = 1;

    public static final String[] COLUMNS = {DATA, NOTIFY};


    public static final String POSTED = "POSTED";
    public static final String COMMENT = "COMMENT";
    public static final String OWNER_ID = "OWNER_ID";


}
