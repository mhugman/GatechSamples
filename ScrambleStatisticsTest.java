package edu.gatech.seclass.sdpscramble;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ScrambleStatisticsTest {

    private ScrambleStatistics scrambleStat;

    @Before
    public void setUp() {
        scrambleStat = new ScrambleStatistics();
    }

    @After
    public void tearDown() {
        scrambleStat = null;
    }


    @Test
    public void basicTest_1(){

        String testPlayer_1 = new String();
        String testPlayer_2 = new String();

        testPlayer_1 = "JohnSmith";
        testPlayer_2 = "AdamSmith";

        List<String> listTestPlayers = new ArrayList<>();
        listTestPlayers.add(testPlayer_1);
        listTestPlayers.add(testPlayer_2);

        scrambleStat.setScrambleId("ser234dferwerg");
        assertEquals("ser234dferwerg", scrambleStat.getScrambleId());

        scrambleStat.setPlayerWhoAdded(testPlayer_1);
        assertEquals("JohnSmith", scrambleStat.getPlayerWhoAdded());

        scrambleStat.setPlayersWhoSolved(listTestPlayers);
        assertEquals(listTestPlayers, scrambleStat.getPlayersWhoSolved());

        Integer x = 4;
        scrambleStat.setTimesSolved(x);
        assertEquals(x, scrambleStat.getTimesSolved());
    }

    // test setting scrambleId

    @Test (expected = IllegalArgumentException.class)
    public void emptyScrambleId() {
        scrambleStat.setScrambleId("");
    }

    @Test (expected = IllegalArgumentException.class)
    public void tooLongScrambleId() {
        scrambleStat.setScrambleId("aaaaaaaaaaaaaaaabbbbbbbbbbbbbbbbbbbbbbbbbbbbccccccccccccccccccccccccccdddddddddddddddddddddddddddd");
    }

    @Test (expected = NullPointerException.class)
    public void nullScrambleId() {
        scrambleStat.setScrambleId(null);
    }

    // test playerWhoAdded

    @Test (expected = IllegalArgumentException.class)
    public void emptyPlayerWhoAdded() {
        scrambleStat.setPlayerWhoAdded("");
    }

    @Test (expected = IllegalArgumentException.class)
    public void tooLongPlayerWhoAdded() {
        scrambleStat.setPlayerWhoAdded("aaaaaaaaaaaaaaaabbbbbbbbbbbbbbbbbbbbbbbbbbbbccccccccccccccccccccccccccdddddddddddddddddddddddddddd");
    }

    @Test (expected = NullPointerException.class)
    public void nullPlayerWhoAdded() {
        scrambleStat.setPlayerWhoAdded(null);
    }

    // test playersWhoSolved

    @Test (expected = IllegalArgumentException.class)
    public void emptyPlayersWhoSolved() {

        List<String> playersWhoSolved = new ArrayList<String>();

        scrambleStat.setPlayersWhoSolved(playersWhoSolved);
    }

    @Test (expected = IllegalArgumentException.class)
    public void tooLongPlayersWhoSolved() {

        List<String> playersWhoSolved = new ArrayList<String>();

        for (int i = 0; i < 120; i++){
            String teststring = new String();
            playersWhoSolved.add(teststring);
        }

        scrambleStat.setPlayersWhoSolved(playersWhoSolved);
    }

    @Test (expected = NullPointerException.class)
    public void nullPlayersWhoSolved() {
        scrambleStat.setPlayersWhoSolved(null);
    }

    // test setting timesSolved

    @Test (expected = IllegalArgumentException.class)
    public void negativeTimesSolved() {
        scrambleStat.setTimesSolved(-5);
    }

    @Test (expected = IllegalArgumentException.class)
    public void tooLargeTimesSolved() {
        scrambleStat.setTimesSolved(1000000000);
    }

    // test getting scrambleId

    @Test (expected = IllegalArgumentException.class)
    public void notInitializedScrambleId(){

        scrambleStat.getScrambleId();
    }





}
