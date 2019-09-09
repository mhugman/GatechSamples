package edu.gatech.seclass.sdpscramble;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/*
Author: Michael Hugman (10/12/2017)

 */

public class PlayerStatisticsTest {

    private PlayerStatistics playerStat;

    @Before
    public void setUp() {
        playerStat = new PlayerStatistics();
    }

    @After
    public void tearDown() {
        playerStat = null;
    }

    @Test
    public void basicTest_1() {

        // default test

        playerStat.setFirstName("Michael");
        assertEquals("Michael", playerStat.getFirstName());

        playerStat.setLastName("Hugman");
        assertEquals("Hugman", playerStat.getLastName());

        playerStat.setScramblesSolved(5);
        assertEquals(5, playerStat.getScramblesSolved());

        playerStat.setScramblesAdded(14);
        assertEquals(14, playerStat.getScramblesAdded());

        playerStat.setAverageCreatedWereSolved(3.0);
        assertEquals(3.0, playerStat.getAverageCreatedWereSolved(), 0.01);

    }

    // Input Unit Tests

    // First Name

    @Test (expected = IllegalArgumentException.class)
    public void emptyFirstName() {
        playerStat.setFirstName("");
    }

    @Test (expected = IllegalArgumentException.class)
    public void tooLongFirstName() {
        playerStat.setFirstName("aaaaaaaaaaaaaaaabbbbbbbbbbbbbbbbbbbbbbbbbbbbccccccccccccccccccccccccccdddddddddddddddddddddddddddd");
    }

    @Test (expected = NullPointerException.class)
    public void nullFirstName() {
        playerStat.setFirstName(null);
    }

    // Last Name

    @Test (expected = IllegalArgumentException.class)
    public void emptyLastName() {
        playerStat.setLastName("");
    }

    @Test (expected = IllegalArgumentException.class)
    public void tooLongLastName() {
        playerStat.setLastName("aaaaaaaaaaaaaaaabbbbbbbbbbbbbbbbbbbbbbbbbbbbccccccccccccccccccccccccccdddddddddddddddddddddddddddd");
    }

    @Test (expected = NullPointerException.class)
    public void nullLastName() {
        playerStat.setLastName(null);
    }

    // scrambles Solved

    @Test (expected = IllegalArgumentException.class)
    public void negativeScramblesSolved() {
        playerStat.setScramblesSolved(-5);
    }

    @Test (expected = IllegalArgumentException.class)
    public void tooLargeScramblesSolved() {
        playerStat.setScramblesSolved(1000000000);
    }

    // scrambles Added

    @Test (expected = IllegalArgumentException.class)
    public void negativeScramblesAdded() {
        playerStat.setScramblesAdded(-5);
    }

    @Test (expected = IllegalArgumentException.class)
    public void tooLargeScramblesAdded() {
        playerStat.setScramblesAdded(1000000000);
    }

    // averageCreatedWereSolved

    @Test (expected = IllegalArgumentException.class)
    public void negativeAverageCreatedWereSolved() {
        playerStat.setAverageCreatedWereSolved(-5);
    }

    @Test (expected = IllegalArgumentException.class)
    public void tooLargeAverageCreatedWereSolved() {
        playerStat.setAverageCreatedWereSolved(1000000000);
    }

    // getter Tests

    // first Name
    @Test (expected = IllegalArgumentException.class)
    public void notInitializedFirstName(){

        playerStat.getFirstName();
    }

    // last Name
    @Test (expected = IllegalArgumentException.class)
    public void notInitializedLastName(){

        playerStat.getLastName();
    }





}
