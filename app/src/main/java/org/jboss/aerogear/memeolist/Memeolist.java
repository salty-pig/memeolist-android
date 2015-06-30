package org.jboss.aerogear.memeolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import org.jboss.aerogear.android.core.Callback;
import org.jboss.aerogear.memeolist.adapter.TabViewAdapter;
import org.jboss.aerogear.memeolist.auth.KeycloakHelper;


public class Memeolist extends AppCompatActivity {

    private TabLayout tabs;
    private ViewPager pager;
    private AppBarLayout appBar;

    private FloatingActionButton fab;
    private FloatingActionButton save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        pager = (ViewPager) findViewById(R.id.pager);
        tabs = (TabLayout) findViewById(R.id.tablayout);
        appBar = (AppBarLayout) findViewById(R.id.appbar);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        save = (FloatingActionButton) findViewById(R.id.save);
        setSupportActionBar(toolbar);

        TabViewAdapter viewAdapter = new TabViewAdapter(this, getSupportFragmentManager());
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
                if (position == 3) {
                    showSave();
                } else {
                    showFab();
                }
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
        fab.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getApplicationContext(), CreateMemeActivity.class));
                    }
                }
        );

        save.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), "Saving", Toast.LENGTH_LONG).show();
                    }
                }
        );



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

    public void showFab() {
        save.setVisibility(View.GONE);
        fab.setVisibility(View.VISIBLE);
    }

    public void showSave() {
        save.setVisibility(View.VISIBLE);
        fab.setVisibility(View.GONE);
    }
}
