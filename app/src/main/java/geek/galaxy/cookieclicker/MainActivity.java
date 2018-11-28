package geek.galaxy.cookieclicker;

import android.content.Intent;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
    private static final int INITIAL_SCORE = 0;
    private static final double INITIAL_TIME = 0.0;
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0.0");
    private static final int VIBRATION_LENGTH = 100;
    private static Integer score = INITIAL_SCORE;
    private Double time = INITIAL_TIME;

    // GAME STATE
    private HighScore highScore;
    private boolean isPlaying = false;

    // Layout handlers
    private TextView timeView = null;
    private TextView scoreView = null;

    // Mobile device toys
    private Vibrator vibrator = null;

    // Android Message Queue mambo jumbo
    // https://developer.android.com/reference/android/os/Handler
    private Handler handler = new Handler();

    private Runnable timeRunnable = new Runnable() {
        @Override
        public void run() {
            // #11 if-statement example
            if (!isPlaying) {
                return;
            }
            //increment time
            time += SECOND;

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

        // get vibrator handle
        vibrator = (Vibrator)getSystemService(VIBRATOR_SERVICE);

        //TODO #7225 Use Top Ten High Scores implementation
        highScore = new RandomHighScore();
    }

    public void pressCookie(View view) {
        // #12 if-statement example
        if (isPlaying) {
            incrementScore();
            updateScoreLabel();
        } else {
            // Game is currently stopped than start game
            incrementScore();
            updateScoreLabel();
            startGame();
        }

        vibrator.vibrate(VIBRATION_LENGTH);
    }

    private void startGame() {
        isPlaying = true;
        handler.postDelayed(timeRunnable, 100);
    }

    private void incrementScore() {
        this.score += POINT;
    }

    public void updateTimeLabel() {
        timeView.setText(String.format("Time: %s", DECIMAL_FORMAT.format(time)));
    }

    public void updateScoreLabel(){
        scoreView.setText(String.format("Score: %s", score.toString()));
    }

    public void exitGame(View view) {
        Log.d(TAG, "Exiting - Bye bye!");
        finishAndRemoveTask();
    }

    public void stopGame(View view) {
        // stopGame
        isPlaying = false;

        // save current score
        highScore.addMyScore(score);
    }

    public void goToScore(View view) {
        Log.d(TAG, "Go to HighScoreActivity View");
        Intent intent = new Intent(MainActivity.this, HighScoreActivity.class);
        intent.putExtra("HIGH_SCORE", this.highScore);
        startActivity(intent);
    }

    public void upgradeBasicButtonClicked(View view) {
        //TODO #4123 implementation of 1-st stage, smaller bonus
    }

    public void upgradeExtraButtonClicked(View view) {
        //TODO #0321 implementation of 2-st stage, bigger bonus
    }

}
