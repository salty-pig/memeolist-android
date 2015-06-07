package org.jboss.aerogear.memeolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import org.jboss.aerogear.android.core.Callback;
import org.jboss.aerogear.memeolist.adapter.TabViewAdapter;
import org.jboss.aerogear.memeolist.auth.KeycloakHelper;


public class Memeolist extends AppCompatActivity {

    private TabLayout tabs;
    private ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        pager = (ViewPager) findViewById(R.id.pager);
        tabs = (TabLayout) findViewById(R.id.tablayout);

        setSupportActionBar(toolbar);

        TabViewAdapter viewAdapter = new TabViewAdapter(
                getApplicationContext(), getSupportFragmentManager());
        pager.setAdapter(viewAdapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!KeycloakHelper.isConnected()) {
            KeycloakHelper.connect(this, new Callback() {
                @Override
                public void onSuccess(Object o) {
                    tabs.setupWithViewPager(pager);

                }

                @Override
                public void onFailure(Exception e) {

                }
            });
        }
    }
}
