package org.jboss.aerogear.memeolist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import org.jboss.aerogear.memeolist.adapter.CardOnClickHandler;
import org.jboss.aerogear.memeolist.adapter.ImagesAdapter;
import org.jboss.aerogear.memeolist.adapter.MemeAdapter;

public class ImagePickerActivity extends AppCompatActivity implements CardOnClickHandler<String> {

    private RecyclerView recycler;
    private ImagesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meme_picker);
        recycler = (RecyclerView) findViewById(R.id.images);
        recycler.setLayoutManager(new GridLayoutManager(this, 2));
        recycler.setAdapter(adapter = new ImagesAdapter(this));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_meme_picker, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.setCardOnClickHandler(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        adapter.setCardOnClickHandler(null);
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

    @Override
    public void onCardClick(String post, MemeAdapter.ViewHolder view) {
        Intent resultIntent = new Intent();
        resultIntent.putExtra(CreateMemeActivity.IMAGE_FILE, post);
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }

}
