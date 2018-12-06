package geek.galaxy.cookieclicker;

import android.content.Intent;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;

import geek.galaxy.cookieclicker.highscore.HighScore;
import geek.galaxy.cookieclicker.highscore.HighScoreActivity;
import geek.galaxy.cookieclicker.highscore.RandomHighScore;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();
    private static final int POINT = 1;
    private static final double SECOND = 1d;
    private static final int SECOND_LENGTH = 1000;
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0.0");
    private static final int VIBRATION_LENGTH = 100;

    // GAME STATE
    private GameState gameState;

    // Layout handlers
    private TextView timeView = null;
    private TextView scoreView = null;
    private Button upgradeButton = null;
    private ImageView imgView = null;
    private TextView textView = null;

    // Mobile device toys
    private Vibrator vibrator = null;

    // Android Message Queue mambo jumbo
    private Handler handler = new Handler();
    // https://developer.android.com/reference/android/os/Handler

    private Runnable timeRunnable = new Runnable() {
        @Override
        public void run() {
            //EX#11 if-statement example
            if (!gameState.isPlaying()) {
                return;
            }
            //increment time
            gameState.incrementTime(SECOND);

            //update labels
            updateTimeLabel();
            updateScoreLabel();

            //schedule next time incrementation so it would create a loop
            handler.postDelayed(timeRunnable, SECOND_LENGTH);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //set main activity layout
        setContentView(R.layout.activity_main);

        // get textviews (labels) to update in future
        scoreView = findViewById(R.id.scoreView);
        timeView = findViewById(R.id.timeView);
        upgradeButton = findViewById(R.id.upgrade);
        textView = findViewById(R.id.cookieEaterText1);
        imgView = findViewById(R.id.cookieEater1);

        // get vibrator handle
        vibrator = (Vibrator)getSystemService(VIBRATOR_SERVICE);

        gameState = new GameState();

        updateAll();
    }

    public void pressCookie(View view) {
        //EX#12 if-statement example
        if (gameState.isPlaying()) {
//            gameState.incrementScore(POINT);
            gameState.onCookieClick();
            updateScoreLabel();
        } else {
            // Game is currently stopped than start game
            gameState.onCookieClick();
            updateScoreLabel();
            startGame();
        }

        vibrator.vibrate(VIBRATION_LENGTH);
    }

    private void startGame() {
        gameState.startGame();
        handler.postDelayed(timeRunnable, 100);
    }

    public void updateTimeLabel() {
        timeView.setText(String.format("Time: %s", DECIMAL_FORMAT.format(gameState.getCurrentTime())));
    }

    public void updateScoreLabel(){
        scoreView.setText(String.format("Score: %s", gameState.getScore().toString()));
    }

    public void exitGame(View view) {
        Log.d(TAG, "Exiting - Bye bye!");
        finishAndRemoveTask();
    }

    public void stopGame(View view) {
        // stopGame
        gameState.stopGame();
        // save current score
        gameState.saveCurrentScore();
        reset();
    }

    private void reset() {
        gameState = new GameState();
        updateAll();
        hideCookieMonster();
    }

    public void goToScore(View view) {
        Log.d(TAG, "Go to HighScoreActivity View");
        Intent intent = new Intent(MainActivity.this, HighScoreActivity.class);
        intent.putExtra("HIGH_SCORE", gameState.getHighScore());
        startActivity(intent);
    }

    public void upgradeBasicButtonClicked(View view) {
        boolean isSucces = gameState.buyCookieMonster();
        if(isSucces){
            updateUpgradePriceLabel();
            showCookieMonster();
        }
    }

    private void updateUpgradePriceLabel() {
        upgradeButton.setText(String.format("%s -%d$", getString(R.string.upgrade_btn_txt), CookieMonster.getCrrentPrice()));
    }

    private void updateAll(){
        updateUpgradePriceLabel();
        updateTimeLabel();
        updateScoreLabel();
    }

    public void showCookieMonster(){
        imgView.setVisibility(View.VISIBLE);
        textView.setVisibility(View.VISIBLE);
        textView.setText("x"+gameState.getCookieMonsters().size());
    }

    public void hideCookieMonster(){
        imgView.setVisibility(View.INVISIBLE);
        textView.setVisibility(View.INVISIBLE);
    }

    public void upgradeExtraButtonClicked(View view) {
        //TODO #0321 implementation of 2-st stage, bigger bonus
    }

}
