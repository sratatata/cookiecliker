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

import geek.galaxy.cookieclicker.highscore.HighScoreActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();
    public static final int POINT = 1;
    public static final double SECOND = 1d;
    public static final int SECOND_LENGTH = 1000;
    public static final int INITIAL_SCORE = 0;
    public static final double INITIAL_TIME = 0.0;
    public static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0.0");
    public static final int VIBRATION_LENGTH = 100;

    // GAME STATE
    public static Integer score = INITIAL_SCORE;
    private Double time = INITIAL_TIME;


    private boolean isPlaying = false;
    private TextView timeView = null;
    private TextView scoreView = null;

    private Vibrator vibrator = null;

    private Handler handler = new Handler();

    private Runnable timeRunnable = new Runnable() {
        @Override
        public void run() {
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
    }

    public void pressCookie(View view) {
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
    }

    public void goToScore(View view) {
        Log.d(TAG, "Go to HighScoreActivity View");
        Intent intent = new Intent(MainActivity.this, HighScoreActivity.class);
        startActivity(intent);
    }

    public void upgradeBasic(View view) {
        // Obsługa Buttona Basic
    }

    public void upgradeExtra(View view) {
        // Obsługa Buttona Extra
    }

}
