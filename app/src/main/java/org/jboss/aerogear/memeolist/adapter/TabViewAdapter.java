package org.jboss.aerogear.memeolist.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.View;

import org.jboss.aerogear.memeolist.ui.MemeListFragment;

/**
 * Created by summers on 6/7/15.
 */
public class TabViewAdapter extends FragmentStatePagerAdapter {
    public TabViewAdapter(Context applicationContext, FragmentManager supportFragmentManager) {
        super(supportFragmentManager);
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Fragment getItem(int position) {
        return new MemeListFragment();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return true;
    }
}
