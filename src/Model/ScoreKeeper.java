/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2016 *
 * Name: Patrick Newhart, Levi Adair, Sam Greenberg, Tom Ficcadenti
 * Date: Apr 30, 2016
 * Time: 7:35:16 PM *
 * Project: csci205FinalProject
 * Package: Model
 * File: ScoreKeeper
 * Description:
 *
 * **************************************** */
package Model;

/**
 *
 * @author tjf010
 */
public class ScoreKeeper {

    private int currentScore;

    private final int MAX_NUM_CHARACTERS = 7;
    private final int MAX_SCORE = (int) Math.pow(10, MAX_NUM_CHARACTERS) - 1;

    public ScoreKeeper() {
        currentScore = 0;
    }

    public int getCurrentScore() {
        return this.currentScore;
    }

    public String getScoreAsString() {
        return Integer.toString(this.currentScore);
    }

    public void addToScore(int scoreToAdd) {
        if (currentScore + scoreToAdd > MAX_SCORE) {
            currentScore = MAX_SCORE;
        } else if (currentScore + scoreToAdd < 0) {
            currentScore = 0;
        } else {
            currentScore += scoreToAdd;
        }
    }
}
