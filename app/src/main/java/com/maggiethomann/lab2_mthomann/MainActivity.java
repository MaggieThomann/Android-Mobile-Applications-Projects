package com.maggiethomann.lab2_mthomann;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import android.content.Context;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<Team> teams = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // "Ohio State", "Florida State", "Wake Forest", "Boston College", "North Carolina State", "Georgia Tech","North Virginia", "Chicago Sate
        // Team Name
        // Logo resource file
        // Date of Game
        // Time of Game
        // Game Location
        // Team Nickname
        // Team Record
        // Team Score

        // Lab 4 comment

        MyCsvFileReader scheduleCSV = new MyCsvFileReader(getApplicationContext());
        int resID = getApplicationContext().getResources().getIdentifier("schedule", "raw", getApplicationContext().getPackageName());
        ArrayList<String[]> teamArrayList = scheduleCSV.readCsvFile(resID);


        for (int i = 0; i < teamArrayList.size(); i++){
            Team teamObj = new Team(teamArrayList.get(i));
            teams.add(i, teamObj);
        }

        ScheduleAdapter scheduleAdapter = new ScheduleAdapter(this, teams);
        ListView scheduleListView = (ListView) findViewById(R.id.scheduleListView);
        scheduleListView.setAdapter(scheduleAdapter);
        // this will automatically attach the listener to each item of the listview.
        scheduleListView.setOnItemClickListener(clickListener);
    }

    AdapterView.OnItemClickListener clickListener = new AdapterView.OnItemClickListener() {


        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


            //Write code here to open the activity that will show details of the game event,i.e. if //you click on Florida State, you should see details of the match between Florida State //and Notre Dame. You need to do the following three steps.

            //create the intent to start DetailActivity
            Intent detailActivityIntent = new Intent(MainActivity.this, DetailActivity.class);

            //create a bundle object using the following
            detailActivityIntent.putExtra("Team", teams.get(position)); // where al is your ArrayList holding team information.


            //start the activity using the intent with the bundle you just created.
            startActivity(detailActivityIntent);
        }

    };



}
