package com.maggiethomann.lab2_mthomann;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String[]> teams = new ArrayList<String[]>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<String[]> teams = new ArrayList<String[]>();
        // "Ohio State", "Florida State", "Wake Forest", "Boston College", "North Carolina State", "Georgia Tech","North Virginia", "Chicago Sate
        // Team Name
        // Logo resource file
        // Date of Game
        // Time of Game
        // Game Location
        // Team Nickname
        // Team Record
        // Team Score


        teams.add(new String[]{"Virginia", "logoh", "January 24, 2017", "6:30 PM E.T.", "Notre Dame, Ind (Purcell Pavilion)", "Trojans", "(21-5)", "71 - 54"});
        teams.add(new String[]{"Georgia Tech", "logof", "January 28, 2017", "8:00 PM E.T.", "Atlanta, Ga (McCamish Pavilion)", "Ramblin' Wrecks", "(17-8)", "62 - 60"});
        teams.add(new String[]{"Duke", "duke", "January 30, 2017", "6:30 PM E.T.", "Notre Dame, Ind (Purcell Pavilion)", "Blue Devils", "(25-3)", "84 - 74"});
        teams.add(new String[]{"North Carolina", "logoe", "February 5, 2017", "7:00 PM E.T.", "Greensboro, N.C. (Greensboro Coliseum)", "Tar Heels", "(22-4)", "83 - 76"});
        teams.add(new String[]{"Wake Forest", "logod", "February 7, 2017", "6:30 PM E.T.", "Notre Dame, Ind (Purcell Pavilion)", "Demon Deacons", "(15-10)", "88 - 81"});
        teams.add(new String[]{"Florida State", "logoc", "February 11, 2017", "6:30 PM E.T.", "Notre Dame, Ind (Purcell Pavilion)", "Seminoles", "(18-4)", "84 - 76"});
        teams.add(new String[]{"Boston College", "logoa", "February 14, 2017", "8:30 PM E.T.", "Chestnut Hill, Mass (Conte Forum)", "Eagles", "(23-5)", "81 - 72"});


        ScheduleAdapter scheduleAdapter = new ScheduleAdapter(this, teams);
        ListView scheduleListView = (ListView) findViewById(R.id.scheduleListView);
        scheduleListView.setAdapter(scheduleAdapter);
        // this will automatically attach the listener to each item of the listview.
        scheduleListView.setOnItemClickListener(clickListener);
    }

    AdapterView.OnItemClickListener clickListener = new AdapterView.OnItemClickListener() {


        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            teams.add(new String[]{"Virginia", "logoh", "January 24, 2017", "6:30 PM E.T.", "Notre Dame, Ind (Purcell Pavilion)", "Trojans", "(21-5)", "71 - 54"});
            teams.add(new String[]{"Georgia Tech", "logof", "January 28, 2017", "8:00 PM E.T.", "Atlanta, Ga (McCamish Pavilion)", "Ramblin' Wrecks", "(17-8)", "62 - 60"});
            teams.add(new String[]{"Duke", "duke", "January 30, 2017", "6:30 PM E.T.", "Notre Dame, Ind (Purcell Pavilion)", "Blue Devils", "(25-3)", "84 - 74"});
            teams.add(new String[]{"North Carolina", "logoe", "February 5, 2017", "7:00 PM E.T.", "Greensboro, N.C. (Greensboro Coliseum)", "Tar Heels", "(22-4)", "83 - 76"});
            teams.add(new String[]{"Wake Forest", "logod", "February 7, 2017", "6:30 PM E.T.", "Notre Dame, Ind (Purcell Pavilion)", "Demon Deacons", "(15-10)", "88 - 81"});
            teams.add(new String[]{"Florida State", "logoc", "February 11, 2017", "6:30 PM E.T.", "Notre Dame, Ind (Purcell Pavilion)", "Seminoles", "(18-4)", "84 - 76"});
            teams.add(new String[]{"Boston College", "logoa", "February 14, 2017", "8:30 PM E.T.", "Chestnut Hill, Mass (Conte Forum)", "Eagles", "(23-5)", "81 - 72"});

            //Write code here to open the activity that will show details of the game event,i.e. if //you click on Florida State, you should see details of the match between Florida State //and Notre Dame. You need to do the following three steps.

            //create the intent to start DetailActivity
            Intent detailActivityIntent = new Intent(MainActivity.this, DetailActivity.class);

            //create a bundle object using the following
            detailActivityIntent.putExtra("team", teams.get(position)); // where al is your ArrayList holding team information.

            //start the activity using the intent with the bundle you just created.
            startActivity(detailActivityIntent);
        }

    };



}
