package geek.galaxy.cookieclicker.highscore;

public class RandomHighScore implements HighScore {
    private static final int UPPER = Integer.MAX_VALUE;
    private static final int LOWER = 0;
    private int[] highScores;

    public RandomHighScore() {
        this.highScores = new int[10];
        initializeWithRandomScores();
    }

    private void initializeWithRandomScores(){
        // #1 For loop example
        for(int i=0; i<this.highScores.length; i++){
            highScores[i] = getRandomScore();
        }
    }

    private int getRandomScore(){
        return (int) (Math.random() * (UPPER - LOWER)) + LOWER;
    }

    @Override
    public void addMyScore(int score) {
        //TODO @Darek, I've left this method for you to play as you asked.
    }

    @Override
    public int[] sortedScores(boolean isAscending) {
        //TODO #2312 your task is to implement bubble sort algorithm
        return highScores;
    }
}