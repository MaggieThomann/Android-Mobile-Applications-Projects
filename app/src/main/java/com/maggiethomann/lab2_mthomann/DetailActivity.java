package com.maggiethomann.lab2_mthomann;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.security.AccessController.getContext;



public class DetailActivity extends AppCompatActivity {

    public static String PACKAGE_NAME;
    private static final String AUTHORITY=
            BuildConfig.APPLICATION_ID+".provider";
    private static Context mContext;
    private static final int CAMERA_REQUEST = 1888;

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
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);

                startActivity(cameraIntent);

                /*
                File PictureDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                String pictureName = getPictureName();
                File imageFile = new File(PictureDirectory, "duke.png");
                Uri outputUri= FileProvider.getUriForFile(DetailActivity.getContext(), AUTHORITY, imageFile);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputUri);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);*/
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
        Log.d("resource ID", String.valueOf(resID));
        image_team.setImageResource(resID);

        text_date.setText(teamInfo.getTeamDate() + " " + teamInfo.getTeamTime());
        text_location.setText(teamInfo.getTeamLocation());
        Log.d("TEAM LOCATION:   ", teamInfo.getTeamLocation());
        text_nickname.setText(teamInfo.getTeamNickname());
        text_record.setText(teamInfo.getTeamRecord());
        text_score.setText(teamInfo.getTeamScore());

    }

    public static Context getContext(){
        return mContext;
    }

    private String getPictureName() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String timestamp = sdf.format(new Date());
        return "BestMoments" + timestamp + ".jpg";
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == CAMERA_REQUEST) {
                Intent photoGalleryIntent = new Intent(Intent.ACTION_PICK);
                File pictureDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

                String pictureDirectoryPath = pictureDirectory.getPath();
                Uri imageUri = Uri.parse(pictureDirectoryPath);
                InputStream inputStream;
                try {
                    inputStream = getContentResolver().openInputStream(imageUri);

                    Bitmap image = BitmapFactory.decodeStream(inputStream);
                    ImageView imgView = (ImageView) findViewById(R.id.photo_taken);
                    imgView.setImageBitmap(image);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
