package org.jboss.aerogear.memeolist.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.View;

import org.jboss.aerogear.memeolist.R;
import org.jboss.aerogear.memeolist.ui.AccountFragment;
import org.jboss.aerogear.memeolist.ui.FollowingFragment;
import org.jboss.aerogear.memeolist.ui.MemeListFragment;

/**
 * Created by summers on 6/7/15.
 */
public class TabViewAdapter extends FragmentStatePagerAdapter {
    private final String[] titles;

    public TabViewAdapter(Context applicationContext, FragmentManager supportFragmentManager) {
        super(supportFragmentManager);
        titles = applicationContext.getResources().getStringArray(R.array.titles);
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new MemeListFragment();
            case 1:
                return new FollowingFragment();
            case 2:
                return new AccountFragment();
            default:
                return null;
        }

    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return true;
    }
}
