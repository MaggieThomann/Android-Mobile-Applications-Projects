package com.maggiethomann.lab2_mthomann;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.FileProvider;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by MaggieThomann on 4/22/17.
 */

/*
________________Image Gallery Activity - Lab 7 - Part 4abd________________
*/

public class GalleryActivity extends AppCompatActivity{

    private static final int CAMERA_REQUEST = 1;
    private DBHelper dbHelper;
    private long team_id;
    private GridView gridView;
    private String timeStamp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        dbHelper = new DBHelper(this);
        Intent intent = getIntent();
        team_id = intent.getLongExtra("id", -1);

        gridView = (GridView) findViewById(R.id.grid_view);
        populateGridView();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.add_image_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    File storageDirectory = new File(getApplication().getFilesDir(), "images");
                    if (!storageDirectory.exists()) {
                        storageDirectory.mkdirs();
                    }
                    setTimeStamp();
                    File imageFile = new File(storageDirectory, getPictureName());
                    Uri pictureUri = FileProvider.getUriForFile(getApplicationContext(),
                            getPackageName() + ".fileprovider", imageFile);
                    cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, pictureUri);

                    cameraIntent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);

                    if (cameraIntent.resolveActivity(getPackageManager()) != null) {
                        startActivityForResult(cameraIntent, CAMERA_REQUEST);
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    private void setTimeStamp() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        timeStamp = sdf.format(new Date());
    }
    private String getPictureName() {
        return "GameImage" + timeStamp + ".jpg";
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK) {
            if(requestCode == CAMERA_REQUEST) {

                // Retrieve all the info
                File image_path = new File(getApplicationContext().getFilesDir(), "images");
                File image_file = new File(image_path, getPictureName());
                Uri image_uri = FileProvider.getUriForFile(this, getPackageName() + ".fileprovider", image_file);
                String image_uri_string = image_uri.toString();
                byte[] image_bytes = convert_uri_to_bytes(image_uri, 150);

                // Set up all the content values
                ContentValues contentValues = new ContentValues();
                contentValues.put(dbHelper.IMAGE_COL_TEAM_ID, team_id);
                contentValues.put(dbHelper.IMAGE_COL_IMAGE_SRC, image_bytes);
                contentValues.put(dbHelper.IMAGE_COL_URI, image_uri_string);

                //Insert Image
                dbHelper.insertData(dbHelper.TABLE_IMAGES, contentValues);

                // Set up the grid view
                populateGridView();
            }
        }
    }

    private byte[] convert_uri_to_bytes(Uri imageUri, int maxSize) {
        Bitmap bitmap = null;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
        } catch (IOException e) {
            e.printStackTrace();
        }

        int height = bitmap.getHeight();
        int width = bitmap.getWidth();


        float bitmapRatio = (float)width/(float)height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width/bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height*bitmapRatio);
        }

        Bitmap imageBitmap = Bitmap.createScaledBitmap(bitmap, width, height, true);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);

        return stream.toByteArray();
    }

    private void populateGridView() {
        String[] fields = new String[] {DBHelper.IMAGE_COL_ID, DBHelper.IMAGE_COL_IMAGE_SRC};
        String where = "image_team_id = ?";
        String[] args = new String[] {Long.toString(team_id)};
        int[] items = new int[] {R.id.game_image};

        Cursor cursor = dbHelper.getSelectEntries(DBHelper.TABLE_IMAGES, fields, where, args);

        if (cursor != null) {
            SimpleCursorAdapter galleryCursorAdapter = new SimpleCursorAdapter(this, R.layout.activity_gallery_image, cursor, fields, items, 0);

            galleryCursorAdapter.setViewBinder(new SimpleCursorAdapter.ViewBinder() {
                @Override
                public boolean setViewValue(View view, Cursor cursor, int i) {
                    if (view.getId() == R.id.game_image) {
                        ImageView imageView = (ImageView) view;
                        byte[] imageArray = cursor.getBlob(1);
                        Bitmap bitmap = BitmapFactory.decodeByteArray(imageArray, 0, imageArray.length);
                        imageView.setImageBitmap(bitmap);
                        return true;
                    }
                    return false;
                }
            });
            gridView.setAdapter(galleryCursorAdapter);
        }
    }

}
