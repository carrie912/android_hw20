package com.example.hw20;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.net.URL;

public class MainActivity extends AppCompatActivity {

ImageView imageView;

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.imageView);

        final Button button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                new GetImage().execute("https://png.pngtree.com/png_detail/20181017/small-fresh-flowers-hand-painted-border-png-clipart_1705840.png");

            }
        });

    }
    private class GetImage extends AsyncTask<String , Integer , Bitmap> {

        ProgressDialogUtil progressDialogUtil;

        @Override

        protected void onPreExecute() {

            super.onPreExecute();

            progressDialogUtil = new ProgressDialogUtil();

            progressDialogUtil.showProgressDialog(MainActivity.this,"稍等~");
        }
        @Override
        protected Bitmap doInBackground(String... params) {

            String urlStr = params[0];

            try {

                URL url = new URL(urlStr);

                return BitmapFactory.decodeStream(url.openConnection().getInputStream());

            } catch (Exception e) {

                e.printStackTrace();

                return null;

            }

        }
        @Override

        protected void onProgressUpdate(Integer... values) {

            super.onProgressUpdate(values);

        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {


            super.onPostExecute(bitmap);

            progressDialogUtil.dismiss();

            imageView.setImageBitmap(bitmap);

        }
    }

    }
