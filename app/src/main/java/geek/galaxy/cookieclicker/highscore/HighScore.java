package geek.galaxy.cookieclicker.highscore;

import java.io.Serializable;

public interface HighScore extends Serializable {
    boolean  DESCENDING = false;
    boolean ASCENDING = true;

    void addMyScore(int score);
    int[] sortedScores(boolean isAscending);
}
