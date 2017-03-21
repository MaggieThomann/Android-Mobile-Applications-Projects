package com.maggiethomann.lab2_mthomann;

import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.ArrayList;
import android.content.Context;


public class MainActivity extends AppCompatActivity {

    public static ArrayList<Team> teams = new ArrayList<>();
    public static ArrayList<String> list = new ArrayList<String>();
    public static MenuItem settingsMenuItem = null;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        settingsMenuItem = menu.findItem(R.id.settings);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int res_id = item.getItemId();

        if (res_id == R.id.share) {
            Intent shareIntent = new Intent();
            shareIntent.setAction(android.content.Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra("android.content.Intent.EXTRA_SUBJECT", "BasketBall Matches");
            shareIntent.putExtra("android.content.Intent.EXTRA_TEXT", gameSchedule());
            startActivity(Intent.createChooser(shareIntent, "Share via"));
        }

        else if (res_id == R.id.sync) {
            final CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinator_layout);

            final Snackbar snackbar = Snackbar.make(coordinatorLayout, "Sync is not yet implemented", Snackbar.LENGTH_LONG);

            snackbar.setAction("Try Again", new View.OnClickListener() {

                @Override

                public void onClick(View v) {

                    Log.d("snackbar has:  ", " been Clicked");

                    snackbar.make(coordinatorLayout, "Wait for the next few labs. Thank you for your patience", Snackbar.LENGTH_LONG).show();

                }

            });

            snackbar.show();
        }

        else if (res_id == R.id.settings) {
            // Only works if it's not contained in the three dots
            // registerForContextMenu((View) findViewById(R.id.settings));
            // openContextMenu(settingsMenuItem);
        }
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.context_menu, menu);
    }

    @Override

    public boolean onContextItemSelected(MenuItem item) {

        int item_id = item.getItemId();

        if (item_id == R.id.women) {

            // to be implemented later

        }

        return false;

    }


    public String gameSchedule(){
        StringBuilder sb = new StringBuilder();
        Log.d("List:  ", String.valueOf(list));
        for(int  i =0;i<list.size();i++)
        {
            String prefix = "";
            for (String str : list)
            {
                sb.append(prefix);
                prefix = ",";
                Log.d("Appending:  ", str);
                sb.append(str);
            }
        }
        Log.d("String Builder:  ", String.valueOf(sb));
        return String.valueOf(sb);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MenuItem settingsMenuItem = (MenuItem) findViewById(R.id.settings);
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

        // registerForContextMenu((View) findViewById(R.id.settings));




        MyCsvFileReader scheduleCSV = new MyCsvFileReader(getApplicationContext());
        int resID = getApplicationContext().getResources().getIdentifier("schedule", "raw", getApplicationContext().getPackageName());
        ArrayList<String[]> teamArrayList = scheduleCSV.readCsvFile(resID);


        for (int i = 0; i < teamArrayList.size(); i++){
            Team teamObj = new Team(teamArrayList.get(i));
            teams.add(i, teamObj);
            list.add(teamObj.getTeamName());
            list.add(teamObj.getTeamLocation());
            list.add(teamObj.getTeamDate());
        }

        ScheduleAdapter scheduleAdapter = new ScheduleAdapter(this, teams);
        ListView scheduleListView = (ListView) findViewById(R.id.scheduleListView);
        scheduleListView.setAdapter(scheduleAdapter);
        // this will automatically attach the listener to each item of the listview.
        scheduleListView.setOnItemClickListener(clickListener);

        // Toolbar Functionality-----------------------------------------------------------
        Toolbar customToolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(customToolBar);
        customToolBar.setTitle("ND Athletics");
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
