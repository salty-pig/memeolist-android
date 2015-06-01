package org.jboss.aerogear.memeolist.content.contract;

import android.net.Uri;

/**
 * Created by summers on 6/1/15.
 */
public class RedHatUserContract {
    public static final Uri URI = Uri.parse("content://org.jboss.aerogear.memeolist/RedHatUserContract");

    public static final String DATA = "DATA";
    public static final String ID = "ID";
    public static final Integer DATA_IDX = 0;
    public static final String NOTIFY = "NOTIFY";
    public static final Integer NOTIFY_IDX = 1;

    public static final String[] COLUMNS = {DATA, NOTIFY};


    public static final String USER_NAME = "USER_NAME";
    public static final String DISPLAY_NAME = "DISPLAY_NAME";


}
