package org.jboss.aerogear.memeolist;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.apache.commons.io.IOUtils;
import org.jboss.aerogear.android.core.Callback;
import org.jboss.aerogear.android.pipe.PipeManager;
import org.jboss.aerogear.memeolist.content.vo.Meme;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;


public class CreateMemeActivity extends AppCompatActivity  {

    public static final String IMAGE_FILE = "CreateMemeActivity.ImageFile";
    private ImageView image;
    private Button submitButton;
    private EditText topComment;
    private EditText bottomComment;
    private String imageUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_meme);
        image = (ImageView) findViewById(R.id.image);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(CreateMemeActivity.this, ImagePickerActivity.class), 1);
            }
        });


        bottomComment = (EditText) findViewById(R.id.bottomComment);
        topComment= (EditText) findViewById(R.id.topComment);

        submitButton = (Button) findViewById(R.id.submit_button);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        Meme meme = new Meme();
                        meme.setTopComment(topComment.getText().toString());
                        meme.setBottomComment(bottomComment.getText().toString());

                        URL url = null;
                        InputStream imageStream = null;
                        try {
                            if (imageUrl == null) {
                                return;
                            }

                            url = new URL(imageUrl);
                            imageStream = url.openStream();

                            Bitmap bm = BitmapFactory.decodeStream(imageStream);

                            ByteArrayOutputStream stream = new ByteArrayOutputStream();
                            bm.compress(Bitmap.CompressFormat.PNG, 100, stream);
                            byte[] byteArray = stream.toByteArray();

                            meme.setImage(byteArray);
                            PipeManager.getPipe("kc-upload", CreateMemeActivity.this).save(meme, new Callback() {
                                @Override
                                public void onSuccess(Object o) {
                                    Log.d("TAG", new Gson().toJson(o));
                                    Toast.makeText(CreateMemeActivity.this, "Uploaded", Toast.LENGTH_LONG).show();
                                    finish();
                                }

                                @Override
                                public void onFailure(Exception e) {
                                    Log.e("TAG", e.getMessage(), e);
                                    Toast.makeText(CreateMemeActivity.this, "Upload Failed", Toast.LENGTH_LONG).show();
                                }
                            });
                        } catch (IOException e) {
                            e.printStackTrace();

                            throw new RuntimeException(e);
                        } finally {
                            if (imageStream != null) {
                                try {
                                    imageStream.close();
                                } catch (IOException e1) {
                                    e1.printStackTrace();
                                }
                            }
                        }
                    }
                });


            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 1) {
            final String url = data.getStringExtra(IMAGE_FILE);
            this.imageUrl = url;
            Picasso.with(this).load(url)
                    .into(image);
        }
    }
}
