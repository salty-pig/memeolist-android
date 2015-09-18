package org.jboss.aerogear.memeolist.adapter;

import android.app.Fragment;
import android.app.FragmentManager;


import org.jboss.aerogear.memeolist.Memeolist;
import org.jboss.aerogear.memeolist.R;
import org.jboss.aerogear.memeolist.ui.AccountFragment;
import org.jboss.aerogear.memeolist.ui.FavoritesFragment;
import org.jboss.aerogear.memeolist.ui.FollowingFragment;
import org.jboss.aerogear.memeolist.ui.MemeListFragment;
import org.jboss.aerogear.memeolist.utils.FragmentPagerAdapter;

/**
 * Created by summers on 6/7/15.
 */
public class TabViewAdapter extends FragmentPagerAdapter {
    private final String[] titles;

    public TabViewAdapter(Memeolist memeolist, FragmentManager supportFragmentManager) {
        super(supportFragmentManager);
        titles = memeolist.getResources().getStringArray(R.array.titles);
    }

    @Override
    public int getCount() {
        return 4;
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
                return new FavoritesFragment();
            case 3:
                return new AccountFragment();
            default:
                return null;
        }

    }

}
