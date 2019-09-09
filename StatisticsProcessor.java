package edu.gatech.seclass.sdpscramble;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import edu.gatech.seclass.utilities.ExternalWebService;

/*
created by Michael Hugman on 10/10/2017

This class is for obtaining scramble and player data and generating lists of ScrambleStatistics and PlayerStatistics objects
*/

public class StatisticsProcessor {

    // create our list of ScrambleStatistics by querying the EWS
    public static List<ScrambleStatistics> getScrambleStatistics(){

        int timesSolved;

        // instantiate stuff
        List<ScrambleStatistics> scrambleStatList = new ArrayList<ScrambleStatistics>();
        List<List<String>> listOfScrambles = new ArrayList<List<String>>();
        List<List<String>> listOfPlayers = new ArrayList<List<String>>();

        ExternalWebService EWS = ExternalWebService.getInstance();
        listOfScrambles = EWS.retrieveScrambleService();
        listOfPlayers = EWS.retrievePlayerListService();

        // itereate through list of scrambles
        for (int i=0; i < listOfScrambles.size(); i++){

            // instantiate stuff
            String scrambleId = new String();
            String creatorUsername = new String();
            String username = new String();
            ScrambleStatistics scrambleStat = new ScrambleStatistics();
            List<String> playerInfo = new ArrayList<String>();
            List<String> idsOfSolvedScrambles = new ArrayList<String>();
            String playerWhoAdded = new String();
            String playerHasSolved = new String();
            List<String> playersWhoSolved = new ArrayList<String>();
            List<String> scrambleInfo = new ArrayList<String>();

            scrambleInfo = listOfScrambles.get(i);

            scrambleId = scrambleInfo.get(0);
            creatorUsername = scrambleInfo.get(4);
            playerWhoAdded = creatorUsername;

            timesSolved = 0;

            // iterate through other players to see how many times this scramble has been solved
            for (int j=0; j < listOfPlayers.size(); j++){

                playerInfo = listOfPlayers.get(j);

                username = playerInfo.get(0);
                idsOfSolvedScrambles = playerInfo.subList(4, playerInfo.size());

                // if this player has solved our scramble, increment the number of times it has been solved
                // and add them to our list of players who have solved this scramble
                if (idsOfSolvedScrambles.contains(scrambleId)){
                    timesSolved += 1;

                    playerHasSolved = username;
                    playersWhoSolved.add(playerHasSolved);
                }

            }

            // set scrambleStat values according to what we just figured out
            scrambleStat.setScrambleId(scrambleId);
            scrambleStat.setPlayerWhoAdded(playerWhoAdded);
            scrambleStat.setPlayersWhoSolved(playersWhoSolved);
            scrambleStat.setTimesSolved(timesSolved);
            scrambleStatList.add(scrambleStat);
        }

        // sort the list by decreasing number of solutions, per requirements
        // insertion sort https://en.wikipedia.org/wiki/Insertion_sort
        int k = 1;
        while (k < scrambleStatList.size()){
            int m = k;

            while ( m > 0 && (scrambleStatList.get(m-1).getTimesSolved() > scrambleStatList.get(m).getTimesSolved()) ){
                Collections.swap(scrambleStatList, m, m-1);
                m = m - 1;
            }
            k += 1;
        }

        Collections.reverse(scrambleStatList);

        return scrambleStatList;
    }

    // create our list of PlayerStatistics by querying the EWS
    public static List<PlayerStatistics> getPlayerStatistics(){

        int scramblesAdded;
        int solvedByOtherPlayers;

        // instantiate stuff
        List<PlayerStatistics> playerStatList = new ArrayList<PlayerStatistics>();
        List<List<String>> listOfScrambles = new ArrayList<List<String>>();
        List<List<String>> listOfPlayers = new ArrayList<List<String>>();

        ExternalWebService EWS = ExternalWebService.getInstance();
        listOfScrambles = EWS.retrieveScrambleService();
        listOfPlayers = EWS.retrievePlayerListService();

        for (int j=0; j < listOfPlayers.size(); j++){

            scramblesAdded = 0;
            solvedByOtherPlayers = 0;

            // instantiate stuff
            String scrambleId = new String();
            String creatorUsername = new String();
            String username = new String();
            String firstname = new String();
            String lastname = new String();
            String otherUsername = new String();
            String idOfScrambleAdded = new String();

            PlayerStatistics playerStat = new PlayerStatistics();
            List<String> playerInfo = new ArrayList<String>();
            List<String> otherPlayerInfo = new ArrayList<String>();
            List<String> idsOfSolvedScrambles = new ArrayList<String>();
            List<String> listOfScramblesAdded = new ArrayList<String>();
            List<String> otherIdsOfSolvedScrambles = new ArrayList<String>();
            List<String> scrambleInfo = new ArrayList<String>();

            playerInfo = listOfPlayers.get(j);

            username = playerInfo.get(0);
            firstname = playerInfo.get(1);
            lastname = playerInfo.get(2);
            idsOfSolvedScrambles = playerInfo.subList(4, playerInfo.size());

            // itereate through list of all scrambles to create list of scrambles added by this player, and get count
            // of how many scrambles were added by this player
            for (int i=0; i < listOfScrambles.size(); i++){

                scrambleInfo = listOfScrambles.get(i);

                scrambleId = scrambleInfo.get(0);
                creatorUsername = scrambleInfo.get(4);

                // if the player created this scramble, increment the number of scrambles they've added
                if (username.equals(creatorUsername)){
                    scramblesAdded += 1;
                    listOfScramblesAdded.add(scrambleId);
                }
            }

            // iterate through other players to see how many have solved this player's scrambles
            for (int k = 0; k < listOfPlayers.size(); k++) {

                otherPlayerInfo = listOfPlayers.get(k);

                otherUsername = otherPlayerInfo.get(0);
                otherIdsOfSolvedScrambles = otherPlayerInfo.subList(4, otherPlayerInfo.size());

                // only compare if players are different
                if (!username.equals(otherUsername)){

                    // iterate through scrambles this player has added and compare it to scrambles other player has solved
                    for (int m = 0; m < listOfScramblesAdded.size(); m++){
                        idOfScrambleAdded = listOfScramblesAdded.get(m);

                        // increment our count of how many times other players have solved this player's scrambles
                        if (otherIdsOfSolvedScrambles.contains(idOfScrambleAdded)){
                            solvedByOtherPlayers += 1;
                        }
                    }
                }
            }

            // set playerStat values according to what we just figured out
            playerStat.setFirstName(firstname);
            playerStat.setLastName(lastname);
            playerStat.setScramblesAdded(scramblesAdded);
            playerStat.setScramblesSolved(idsOfSolvedScrambles.size());
            if (listOfScramblesAdded.size() > 0){
                playerStat.setAverageCreatedWereSolved(solvedByOtherPlayers / listOfScramblesAdded.size());
            }
            else{
                playerStat.setAverageCreatedWereSolved(0);
            }


            playerStatList.add(playerStat);

        }

        // sort the list by decreasing number of scrambles player has solved, per requirements
        // insertion sort https://en.wikipedia.org/wiki/Insertion_sort
        int k = 1;
        while (k < playerStatList.size()){
            int m = k;

            while ( m > 0 && (playerStatList.get(m-1).getScramblesSolved() > playerStatList.get(m).getScramblesSolved()) ){
                Collections.swap(playerStatList, m, m-1);
                m = m - 1;
            }
            k += 1;
        }

        Collections.reverse(playerStatList);

        return playerStatList;
    }
}
