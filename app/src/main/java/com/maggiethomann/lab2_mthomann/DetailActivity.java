package com.maggiethomann.lab2_mthomann;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import static java.security.AccessController.getContext;



public class DetailActivity extends AppCompatActivity {

    public static String PACKAGE_NAME;
    private static Context mContext;

    @Override

    public void onCreate (Bundle bundle) {

        super.onCreate(bundle);

        PACKAGE_NAME = getApplicationContext().getPackageName();

        mContext = this;

        setContentView(R.layout.activity_detail);

        //initialize all the widgets of your layout file here.
        TextView text_date = (TextView) findViewById(R.id.date);
        TextView text_location = (TextView) findViewById(R.id.location);
        TextView text_team = (TextView) findViewById(R.id.team);
        TextView text_nickname = (TextView) findViewById(R.id.nickname);
        TextView text_record = (TextView) findViewById(R.id.record);
        TextView text_score = (TextView) findViewById(R.id.score);
        ImageView image_team = (ImageView) findViewById(R.id.image);

        Button cameraButton = (Button) findViewById(R.id.button);
        cameraButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivity(cameraIntent);
            }
        });




        // Team Name
        // Logo resource file
        // Date of Game
        // Time of Game
        // Game Location
        // Team Nickname
        // Team Record
        // Team Score

        // startActivity(cameraIntent);

        String[] stringInfo = getIntent().getStringArrayExtra("team");
        text_team.setText(stringInfo[0]);


        String mDrawableName = stringInfo[1];

        Log.d("myTag", mDrawableName);

        // int resID = getResources().getIdentifier(mDrawableName, "drawable", getPackageName());
        int resID = getApplicationContext().getResources().getIdentifier(mDrawableName, "drawable", getApplicationContext().getPackageName());
        Log.d("resource ID", String.valueOf(resID));
        image_team.setImageResource(resID);

        text_date.setText(stringInfo[2] + " " + stringInfo[3]);
        text_location.setText(stringInfo[4]);
        text_nickname.setText(stringInfo[5]);
        text_record.setText(stringInfo[6]);
        text_score.setText(stringInfo[7]);


    }

    public static Context getContext(){
        return mContext;
    }
}
