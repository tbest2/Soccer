package cs301.Soccer;

import android.util.Log;
import cs301.Soccer.soccerPlayer.SoccerPlayer;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;
import java.util.function.BiConsumer;

/**
 * Soccer player database -- presently, all dummied up
 *
 * @author *** put your name here ***
 * @version *** put date of completion here ***
 *
 */
public class SoccerDatabase implements SoccerDB {

    // dummied up variable; you will need to change this
    private Hashtable<String, SoccerPlayer> database = new Hashtable<String, SoccerPlayer>();

    /**
     * add a player
     *
     * @see SoccerDB#addPlayer(String, String, int, String)
     */
    @Override
    public boolean addPlayer(String firstName, String lastName,
                             int uniformNumber, String teamName) {
        if(database.containsKey(firstName + " ## " + lastName)){
            return false;
        } else {
            database.put(firstName + " ## " + lastName, new SoccerPlayer(firstName, lastName, uniformNumber, teamName));
            return true;
        }

    }

    /**
     * remove a player
     *
     * @see SoccerDB#removePlayer(String, String)
     */
    @Override
    public boolean removePlayer(String firstName, String lastName) {
        if(database.containsKey(firstName + " ## " + lastName)){
            database.remove(firstName + " ## " + lastName);
            return true;
        }

        return false;
    }

    /**
     * look up a player
     *
     * @see SoccerDB#getPlayer(String, String)
     */
    @Override
    public SoccerPlayer getPlayer(String firstName, String lastName) {
        if(!database.containsKey(firstName + " ## " + lastName)){
            return null;
        } else {
           return database.get(firstName + " ## " + lastName);
        }

    }

    /**
     * increment a player's goals
     *
     * @see SoccerDB#bumpGoals(String, String)
     */
    @Override
    public boolean bumpGoals(String firstName, String lastName) {
        if(database.containsKey(firstName + " ## " + lastName)){
            database.get(firstName + " ## " + lastName).bumpGoals();
            return true;
        }
        return false;
    }

    /**
     * increment a player's yellow cards
     *
     * @see SoccerDB#bumpYellowCards(String, String)
     */
    @Override
    public boolean bumpYellowCards(String firstName, String lastName) {
        if(database.containsKey(firstName + " ## " + lastName)){
            database.get(firstName + " ## " + lastName).bumpYellowCards();
            return true;
        }
        return false;
    }

    /**
     * increment a player's red cards
     *
     * @see SoccerDB#bumpRedCards(String, String)
     */
    @Override
    public boolean bumpRedCards(String firstName, String lastName) {
        if(database.containsKey(firstName + " ## " + lastName)){
            database.get(firstName + " ## " + lastName).bumpRedCards();
            return true;
        }
        return false;
    }

    /**
     * tells the number of players on a given team
     *
     * @see SoccerDB#numPlayers(String)
     */
    @Override
    // report number of players on a given team (or all players, if null)
    public int numPlayers(String teamName) {
        if(teamName == null){
            return database.size();
        }

        int num = 0;
        SoccerPlayer[] a = database.values().toArray(new SoccerPlayer[database.size()]);
        for(int i = 0; i < a.length; i++){

            if (a[i].getTeamName().equals(teamName)){
                num++;
            }
        }
            return num;

    }

    /**
     * gives the nth player on a the given team
     *
     * @see SoccerDB#playerIndex(int, String)
     */
    // get the nTH player
    @Override
    public SoccerPlayer playerIndex(int idx, String teamName) {
        SoccerPlayer[] a = database.values().toArray(new SoccerPlayer[database.size()]);
        if(teamName == null){
            if(idx >= a.length){ return null;}
            return a[idx];
        } else {

            int num = 0;
            //gets number of plauyers on team
            for(int i = 0; i < a.length; i++){
                if(a[i].getTeamName().equals(teamName)){
                    num++;
                }
            }
            if(idx >= num){ return null;}
            //gets soccerplayer of index in the team
            SoccerPlayer[] b = new SoccerPlayer[num];
            int x = 0;
            for(int i = 0; i < a.length; i++){
                if(a[i].getTeamName().equals(teamName)){
                    b[x] = a[i];
                    x++;
                }
            }
            return b[idx];
        }
    }

    /**
     * reads database data from a file
     *
     * @see SoccerDB#readData(java.io.File)
     */
    // read data from file
    @Override
    public boolean readData(File file) {
        return file.exists();
    }

    /**
     * write database data to a file
     *
     * @see SoccerDB#writeData(java.io.File)
     */
    // write data to file
    @Override
    public boolean writeData(File file) {
        SoccerPlayer[] a = database.values().toArray(new SoccerPlayer[database.size()]);

        try {
            PrintWriter myWritter = new PrintWriter("banannananabatman");
            for(int i = 0; i < a.length; i++) {
                myWritter.write("Player first name: " + a[i].getFirstName() + "\n");
                myWritter.write("Player last name: " + a[i].getLastName() + "\n");
                myWritter.write("Player team name: " + a[i].getTeamName() + "\n");
                myWritter.write("Player uniform number: " + a[i].getUniform() + "\n");
                myWritter.write("Player goal number: " + a[i].getGoals() + "\n");
                myWritter.write("Player yellow card number: " + a[i].getYellowCards() + "\n");
                myWritter.write("Player red card number: " + a[i].getRedCards() + "\n");
                myWritter.close();
            }
            return true;
        } catch (FileNotFoundException e) {
            return false;
        }

    }

    /**
     * helper method that logcat-logs a string, and then returns the string.
     * @param s the string to log
     * @return the string s, unchanged
     */
    private String logString(String s) {
        Log.i("write string", s);
        return s;
    }

    /**
     * returns the list of team names in the database
     *
     * @see cs301.Soccer.SoccerDB#getTeams()
     */
    // return list of teams
    @Override
    public HashSet<String> getTeams() {
        return new HashSet<String>();
    }

    /**
     * Helper method to empty the database and the list of teams in the spinner;
     * this is faster than restarting the app
     */
    public boolean clear() {
        if(database != null) {
            database.clear();
            return true;
        }
        return false;
    }
}
