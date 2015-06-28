package org.jboss.aerogear.memeolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.widget.Toast;

import org.jboss.aerogear.android.core.Callback;
import org.jboss.aerogear.memeolist.adapter.TabViewAdapter;
import org.jboss.aerogear.memeolist.auth.KeycloakHelper;


public class Memeolist extends AppCompatActivity {

    private TabLayout tabs;
    private ViewPager pager;
    private AppBarLayout appBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        pager = (ViewPager) findViewById(R.id.pager);
        tabs = (TabLayout) findViewById(R.id.tablayout);
        appBar = (AppBarLayout) findViewById(R.id.appbar);
        setSupportActionBar(toolbar);

        TabViewAdapter viewAdapter = new TabViewAdapter(
                getApplicationContext(), getSupportFragmentManager());
        pager.setAdapter(viewAdapter);

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                CoordinatorLayout coordinator = (CoordinatorLayout) findViewById(R.id.coordinator);
                AppBarLayout appbar = (AppBarLayout) findViewById(R.id.appbar);
                CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) appbar.getLayoutParams();
                AppBarLayout.Behavior behavior = (AppBarLayout.Behavior) params.getBehavior();
                behavior.onNestedFling(coordinator, appbar, null, 0, -1000, true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        tabs.setupWithViewPager(pager);

//        if (!KeycloakHelper.isConnected()) {
//            KeycloakHelper.connect(this, new Callback() {
//                @Override
//                public void onSuccess(Object o) {
//
//                }
//
//                @Override
//                public void onFailure(Exception e) {
//
//                }
//            });
//        }
    }
}
