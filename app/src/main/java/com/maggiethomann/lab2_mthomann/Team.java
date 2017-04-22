package com.maggiethomann.lab2_mthomann;

import android.util.Log;

import java.io.Serializable;
import android.content.Context;

/**
 * Created by MaggieThomann on 3/1/17.
 */



public class Team implements Serializable {

    // Team Name
    // Logo resource file
    // Date of Game
    // Time of Game
    // Game Location
    // Team Nickname
    // Team Record
    // Team Score


    String teamName;
    String teamLogo;
    String teamDate;
    String teamTime;
    String teamLocation;
    String teamNickname;
    String teamRecord;
    String teamScore;


    public Team (String[] args){
        setTeamName(args[0]);
        setTeamLogo(args[1]);
        setTeamDate(args[2]);
        setTeamTime(args[3]);
        setTeamLocation(args[4]);
        setTeamNickname(args[5]);
        setTeamRecord(args[6]);
        setTeamScore(args[7]);
    }

    public void setTeamName(String team_name) {
        this.teamName = team_name;
    }

    public String getTeamName() {
        return this.teamName;
    }

    public void setTeamLogo(String team_logo){

        this.teamLogo = team_logo;
    }

    public String getTeamlogo(){
        return this.teamLogo;
    }

    public void setTeamDate(String team_date){
        this.teamDate = team_date;
    }

    public String getTeamDate(){
        return this.teamDate;
    }

    public void setTeamTime(String team_time){
        this.teamTime = team_time;
    }

    public String getTeamTime(){
        return this.teamTime;
    }

    public void setTeamLocation(String team_location){
        this.teamLocation = team_location;
    }

    public String getTeamLocation(){
        return this.teamLocation;
    }

    public void setTeamNickname(String team_nickname){
        this.teamNickname = team_nickname;
    }

    public String getTeamNickname(){
        return this.teamNickname;
    }

    public void setTeamRecord(String team_record){
        this.teamRecord = team_record;
    }

    public String getTeamRecord(){
        return this.teamRecord;
    }

    public void setTeamScore(String team_score){
        this.teamScore = team_score;
    }

    public String getTeamScore(){
        return this.teamScore;
    }

}
