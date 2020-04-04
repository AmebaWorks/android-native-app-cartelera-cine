package com.example.cristina.a5listview1.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Image;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cristina.a5listview1.R;

import static android.content.Intent.ACTION_CALL;
import static android.content.Intent.ACTION_DIAL;

public class A3_Contact extends AppCompatActivity {

    private TextView description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a3__contact);

        description = (TextView) findViewById(R.id.description_Contact);
        description.setText("Hello! I'm Cristina and I do this App, this is my data of contact");
    }

    public void call(View view) {
        String message = "695550687";
        Uri uri = Uri.parse("tel:"+ message);
        Intent intent = new Intent(Intent.ACTION_DIAL, uri);
        startActivity(intent);
    }

    public void sendEmail(View view)
    {
        String message = "cristinaberrocal@gmail.com";
        Uri uri = Uri.parse("mailto:"+ message);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    public void goURL(View view)
    {
        String message = "google.es";
        Uri uri = Uri.parse("http:"+message);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}
