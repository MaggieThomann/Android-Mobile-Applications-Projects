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

        Team teamInfo = (Team) getIntent().getSerializableExtra("Team");

        text_team.setText(teamInfo.getTeamName());


        String mDrawableName = teamInfo.getTeamlogo();
        int resID = DetailActivity.this.getResources().getIdentifier(mDrawableName, "drawable", DetailActivity.this.getPackageName());
        //int resID = getResources().getIdentifier(mDrawableName, "drawable", getPackageName());
        //int resID = getApplicationContext().getResources().getIdentifier(mDrawableName, "drawable", getApplicationContext().getPackageName());
        Log.d("resource ID", String.valueOf(resID));
        image_team.setImageResource(resID);

        text_date.setText(teamInfo.getTeamDate() + " " + teamInfo.getTeamTime());
        text_location.setText(teamInfo.getTeamLocation());
        text_nickname.setText(teamInfo.getTeamNickname());
        text_record.setText(teamInfo.getTeamRecord());
        text_score.setText(teamInfo.getTeamScore());


    }

    public static Context getContext(){
        return mContext;
    }
}
