package geek.galaxy.cookieclicker.highscore;

import java.util.Random;

public class TopTenHighScore implements HighScore {
    public static final int NUMBER_OF_SCORES = 10;  // TOP TEN

    private int[] highScores;
    private int maxRandScore;
    private int minRandScore;

    public TopTenHighScore() {
        this.highScores = new int[NUMBER_OF_SCORES];
        this.minRandScore = 1;
        this.maxRandScore = Integer.MAX_VALUE - this.minRandScore;
        initializeRandomScoreValue();
        //TODO #3763 (Optional) Change implementation to use Java collections instead of plain int array
    }

    private void initializeRandomScoreValue()
    {
        for (int i = 0; i < NUMBER_OF_SCORES; i++)
        {
            this.highScores[i] = generateRandomScore();
        }

        addMyScore(2000022);
        return;
    }

    private int generateRandomScore()
    {
        int generatedScore = 0;
        Random rand = new Random();

        generatedScore = rand.nextInt(this.maxRandScore) + this.minRandScore;

        return generatedScore;
    }

    @Override
    public void addMyScore(int score) {
        //TODO #1352 your task is to put score into right position to scores array

        int i = 0;
        int lastLowerIdx = 0;
        boolean isLower = false;
        sortedScores(true);

        while(i < NUMBER_OF_SCORES)
        {
            // 2. Instrukcje do wykonania
            if (score <  this.highScores[i])
            {
                if (isLower)
                    ;
                else
                {
                    isLower = true;
                    lastLowerIdx = i;
                }
            }
            else
            {
                for (int j = this.highScores.length - 1; j > i; j--){
                    this.highScores[j] = this.highScores[j - 1];
                }
                this.highScores[i] = score;
                break;
            }
            i++;
        }
    }

    @Override
    public int[] sortedScores(boolean isAscending) {
        //TODO #2312 your task is to implement bubble sort algorithm
        int temp = 0;
        for (int i = 0; i < this.highScores.length - 1; i++)
        {
            for (int j = 0; j < this.highScores.length - 1; j++){
                if(this.highScores[j] > this.highScores[j + 1]){
                    temp = this.highScores[j];
                    this.highScores[j] = this.highScores[j + 1];
                    this.highScores[j + 1] = temp;
                }
            }
        }

        return this.highScores;
    }
}