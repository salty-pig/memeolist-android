package org.jboss.aerogear.memeolist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import org.jboss.aerogear.android.core.Callback;
import org.jboss.aerogear.memeolist.auth.KeycloakHelper;


public class Memeolist extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!KeycloakHelper.isConnected()) {
            KeycloakHelper.connect(this, new Callback() {
                @Override
                public void onSuccess(Object o) {
                    Toast.makeText(getApplicationContext(),"Connected", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onFailure(Exception e) {
                    Toast.makeText(getApplicationContext(),"Connection Error", Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
