package geek.galaxy.cookieclicker;

import geek.galaxy.cookieclicker.highscore.HighScore;
import geek.galaxy.cookieclicker.highscore.RandomHighScore;

public class GameState{
    private static final int INITIAL_SCORE = 0;
    private static final double INITIAL_TIME = 0.0;
    private static final int BASE_POINT = 1;

    private HighScore highScore;

    private boolean isPlaying = false;
    private java.lang.Integer score;
    private java.lang.Double time;

    private Cage<Upgrade> cookieMonsters;

    public GameState()	{
        score = INITIAL_SCORE;
        time = INITIAL_TIME;
        //TODO #7225 Use Top Ten High Scores implementation
        highScore = new RandomHighScore();
        cookieMonsters = new Cage<>();
    }


    public void addHighScore(int highScore) {
        this.highScore.addMyScore(highScore);
    }

    public Cage<Upgrade> getCookieMonsters() {
        return cookieMonsters;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void incrementTime(double second) {
        time += second;
    }

    public void startGame() {
        this.isPlaying = true;
    }

    public void stopGame() {
        this.isPlaying = false;
    }

    public void incrementScore(int point) {
        score += point;
    }

    public double getCurrentTime() {
        return this.time;
    }

    public Integer getScore() {
        return this.score;
    }

    public void saveCurrentScore() {
        highScore.addMyScore(score);

    }

    public HighScore getHighScore() {
        return highScore;
    }

    public void buyCookieMonster() {
        if(this.score >= CookieMonster.getCrrentPrice()) {
            this.score = this.score - CookieMonster.getCrrentPrice();
            cookieMonsters.add(new CookieMonster());
        }
    }

    public void onCookieClick() {
        incrementScore(BASE_POINT);

        //process upgrades point
        cookieMonsters.onCookieClick(this);
    }
}