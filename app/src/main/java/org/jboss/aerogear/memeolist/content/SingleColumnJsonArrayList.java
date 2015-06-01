package org.jboss.aerogear.memeolist.content;


import android.database.AbstractCursor;

import com.google.gson.Gson;

import org.jboss.aerogear.memeolist.utils.GsonUtils;

import java.util.List;

/**
 * Created by summers on 2/8/14.
 */
public class SingleColumnJsonArrayList<T> extends AbstractCursor {

    private static final String[] COLUMNS = {"DATA", "NOTIFY"};
    private final List<T> cursorList;

    private static final Gson GSON = GsonUtils.GSON;

    public SingleColumnJsonArrayList(List<T> cursorList) {
        this.cursorList = cursorList;
    }

    @Override
    public int getCount() {
        return cursorList.size();
    }

    @Override
    public String[] getColumnNames() {
        return COLUMNS;
    }

    @Override
    public String getString(int column) {
        return GSON.toJson(cursorList.get(super.getPosition()));
    }

    @Override
    public short getShort(int column) {
        return 0;
    }

    @Override
    public int getInt(int column) {
        return 0;
    }

    @Override
    public long getLong(int column) {
        return 0;
    }

    @Override
    public float getFloat(int column) {
        return 0;
    }

    @Override
    public double getDouble(int column) {
        return 0;
    }

    @Override
    public boolean isNull(int column) {
        return false;
    }

}