package com.filipe.malachias.implicitintents2_3;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    // Instance Vatiables
    private EditText mWWebSiteET;
    private EditText mLocationET;
    private EditText mShareEditTextET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWWebSiteET = findViewById(R.id.website_editText);
        mLocationET = findViewById(R.id.location_edittext);
        mShareEditTextET = findViewById(R.id.share_edittext);
    }

    public void openWebsite(View view) {
        // 1. get the text from the EditText above
        String urlString = mWWebSiteET.getText().toString();

        // 2. we need create an Intent Object (Implicit Intent)
        // - Parse the URI
        Uri webUri = Uri.parse(urlString);
        Intent intent = new Intent(Intent.ACTION_VIEW, webUri);

        // 3. start the activity
        // (Check if there's an activity that can handle this intent)
        if (intent.resolveActivity(getPackageManager()) != null)
        {
            startActivity(intent);
        } else {
            // Can't handle this Intent
        }
    }

    public void openLocation(View view) {
        // 1. get the location text from the EditText
        String location = mLocationET.getText().toString();
        // 2. parse the string into a Uri object with geolocation search query.
        // - latitude, longitude
        Uri locUri = Uri.parse("geo:0,0?q=" + location);
        Intent intent = new Intent(Intent.ACTION_VIEW, locUri);

        if (intent.resolveActivity(getPackageManager()) != null)
        {
            startActivity(intent);
        } else {
            // Can't handle this Intent
        }
    }

    public void shareText(View view) {
        String textString = mShareEditTextET.getText().toString();

        String type = "text/plain";
        ShareCompat.IntentBuilder
                .from(this)
                .setType(type)
                .setChooserTitle("Share this text with: ")
                .setText(textString)
                .startChooser();
    }
}