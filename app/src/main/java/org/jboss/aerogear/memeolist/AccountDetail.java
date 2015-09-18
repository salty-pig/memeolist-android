package org.jboss.aerogear.memeolist;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import org.jboss.aerogear.android.pipe.PipeManager;
import org.jboss.aerogear.android.pipe.callback.AbstractActivityCallback;
import org.jboss.aerogear.memeolist.content.vo.RedHatUser;
import org.jboss.aerogear.memeolist.utils.KeycloakEnabledPicasso;

public class AccountDetail extends AppCompatActivity {

    public static final String EXTRA_USERNAME = "AccountDetail.USERNAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_detail);
        loadToolbar();
        loadAccount();
    }

    private void loadAccount() {
        PipeManager.getPipe("kc-user", this).read(new AccountHandlerCallback());
    }

    @Override
    protected void onResume() {
        super.onResume();
        View view = findViewById(R.id.layout);
        Rect windowRect = new Rect();

        view.getWindowVisibleDisplayFrame(windowRect);

        view.setMinimumHeight(windowRect.bottom - windowRect.top);
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_account_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void loadToolbar() {
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle("Memeolist");


    }


    private class AccountHandlerCallback extends AbstractActivityCallback<RedHatUser> {
        @Override
        public void onSuccess(RedHatUser redHatUser) {

        }

        @Override
        public void onFailure(Exception e) {

        }
    }
}
