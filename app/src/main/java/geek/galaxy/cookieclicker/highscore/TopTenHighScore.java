package geek.galaxy.cookieclicker.highscore;

public class TopTenHighScore implements HighScore {
    public static final int NUMBER_OF_SCORES = 10;  // TOP TEN
    private int[] highScores;

    public TopTenHighScore() {
        this.highScores = new int[NUMBER_OF_SCORES];
        //TODO #3763 (Optional) Change implementation to use Java collections instead of plain int array
    }

    @Override
    public void addMyScore(int score) {
        //TODO #1352 your task is to put score into right position to scores array
    }

    @Override
    public int[] sortedScores(boolean isAscending) {
        //TODO #2312 your task is to implement bubble sort algorithm
        return highScores;
    }
}