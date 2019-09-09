package edu.gatech.seclass.sdpscramble;

import android.support.annotation.NonNull;

import java.util.List;
import java.util.ArrayList;

/*
created by Michael Hugman on 10/10/2017

This class represents scramble statistics that will be displayed
*/

public class ScrambleStatistics implements Comparable<ScrambleStatistics>{

    private String scrambleId;
    private String playerWhoAdded;
    private List<String> playersWhoSolved;
    private Integer timesSolved;

    // Constructor
    public ScrambleStatistics(String scrambleId, String playerWhoAdded, List<String> playersWhoSolved, int timesSolved){

        this.scrambleId = scrambleId;
        this.playerWhoAdded = playerWhoAdded;
        this.playersWhoSolved = playersWhoSolved;
        this.timesSolved = timesSolved;

    }

    // no-arg constructor
    public ScrambleStatistics(){

        String testPlayer = new String();
        List<String> testListPlayers = new ArrayList<String>();

        this.scrambleId = "";
        this.playerWhoAdded = testPlayer;
        this.playersWhoSolved = testListPlayers;
        this.timesSolved = 0;

    }

    // getters and setters

    public void setScrambleId(String scrambleId){

        int strLength = scrambleId.length();

        if (strLength == 0){
            throw new IllegalArgumentException();
        }
        else if(strLength > 50){
            throw new IllegalArgumentException();
        }
        else if(scrambleId == null){
            throw new NullPointerException();
        }
        else{
            this.scrambleId = scrambleId;
        }

    }

    public void setPlayerWhoAdded(String playerWhoAdded){

        int strLength = playerWhoAdded.length();

        if (strLength == 0){
            throw new IllegalArgumentException();
        }
        else if(strLength > 50){
            throw new IllegalArgumentException();
        }
        else if(playerWhoAdded == null){
            throw new NullPointerException();
        }
        else{
            this.playerWhoAdded = playerWhoAdded;
        }


    }

    public void setPlayersWhoSolved(List<String> playersWhoSolved){

        int listLength = playersWhoSolved.size();

        if (listLength == 0){
            throw new IllegalArgumentException();
        }
        else if(listLength > 100){
            throw new IllegalArgumentException();
        }
        else if(playersWhoSolved == null){
            throw new NullPointerException();
        }
        else{
            this.playersWhoSolved = playersWhoSolved;
        }

    }

    public void setTimesSolved(int timesSolved){

        if (timesSolved < 0){
            throw new IllegalArgumentException();
        }
        else if (timesSolved > 100000000){
            throw new IllegalArgumentException();
        }
        else {
            this.timesSolved = timesSolved;
        }

    }

    public String getScrambleId(){

        if (this.scrambleId == "" ){
            throw new IllegalArgumentException();
        }
        else if (this.scrambleId == null){
            throw new NullPointerException();
        }
        else{
            return this.scrambleId;
        }


    }

    public String getPlayerWhoAdded(){

        if (this.playerWhoAdded == "" ){
            throw new IllegalArgumentException();
        }
        else if (this.playerWhoAdded == null){
            throw new NullPointerException();
        }
        else{
            return this.playerWhoAdded;
        }


    }

    public List<String> getPlayersWhoSolved(){

        if (this.playersWhoSolved.size() == 0 ){
            throw new IllegalArgumentException();
        }
        else if (this.playersWhoSolved == null){
            throw new NullPointerException();
        }
        else{
            return this.playersWhoSolved;
        }


    }

    public Integer getTimesSolved(){
        return this.timesSolved;
    }

    // methods for checking whether player was the one who added the scramble, or among those who solved the scramble

    public boolean playerAddedScramble(Player player) {
        if (player.getUserName() == playerWhoAdded){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean playerSolvedScramble(Player player) {
        if (playersWhoSolved.contains(player.getUserName())){
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public int compareTo(ScrambleStatistics  other) {
        return this.getTimesSolved().compareTo(other.getTimesSolved());
    }
}
