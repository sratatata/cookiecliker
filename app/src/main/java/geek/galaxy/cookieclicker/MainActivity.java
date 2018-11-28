package geek.galaxy.cookieclicker;

import android.content.Intent;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();
    public static final double SECOND = 1d;
    public static final int POINT = 1;
    public static final int LENGTH_OF_SECOND = 1000;
    public static final int COOKIE_MONSTER_FREQUENCY = 2;
    public static final int EXTRA_COOKIE_MONSTER_FREQENCY = 4;
    public static final int INITIAL_SCORE = 0;
    public static final double INITIAL_TIME = 0.0;

    public static Integer score = INITIAL_SCORE;
    private Double time = INITIAL_TIME;
    private boolean isPlaying = false;
    private TextView timeView = null;
    private TextView scoreView = null;

    private static ArrayList<Integer> highScore;

    private CookieMonster cookieMonster = null;
    private ExtraCookieMonster extraCookieMonster = null;

    private Vibrator vibrator = null;

    private Handler handler = new Handler();

    private Runnable timeRunnable = new Runnable() {
        @Override
        public void run() {
            if (!isPlaying) {
                return;
            }
            // every second
            if (time > SECOND) {
                score += POINT;
            }

            // every 2 seconds
            if (time % COOKIE_MONSTER_FREQUENCY == 0) {
                score += cookieMonster.getEatedCookies();
            }

            // every 4 seconds
            if (time % EXTRA_COOKIE_MONSTER_FREQENCY == 0) {
                score += extraCookieMonster.getEatedCookies();
            }
            // increment counter timer
            time += SECOND;

            updateTimeLabel();
            updateScoreLabel();
            handler.postDelayed(timeRunnable, LENGTH_OF_SECOND);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scoreView = findViewById(R.id.scoreView);
        timeView = findViewById(R.id.timeView);

        cookieMonster = new CookieMonster((ImageView) findViewById(R.id.cookieEater1), (TextView) findViewById(R.id.cookieEaterText1));
        extraCookieMonster = new ExtraCookieMonster((ImageView) findViewById(R.id.cookieEater2), (TextView) findViewById(R.id.cookieEaterText2));

        vibrator = (Vibrator)getSystemService(VIBRATOR_SERVICE);

        highScore = new ArrayList<>();

    }

    public void updateTimeLabel() {
        DecimalFormat df = new DecimalFormat("0.0");
        timeView.setText(String.format("Time: %s", df.format(time)));
    }

    public void updateScoreLabel() {
        scoreView.setText(String.format("Score: %s", score.toString()));
    }

    public void pressCookie(View view) {
        if (isPlaying) {
            score += POINT;
            updateScoreLabel();
        } else {
            handler.postDelayed(timeRunnable, 100);
            score = POINT;
            updateScoreLabel();
            isPlaying = true;
        }
        vibrate();
    }

    public void vibrate(){
        vibrator.vibrate(100);
    }

    public static ArrayList<Integer> getHighScore() {
        return highScore;
    }

    public void reset(View view) {
        Log.d(TAG, "Reset");
        isPlaying = false;
        highScore.add(score);
        score = INITIAL_SCORE;
        time = INITIAL_TIME;
        cookieMonster.reset();
        extraCookieMonster.reset();
        updateTimeLabel();
        updateScoreLabel();
    }

    public void upgradeExtra(View view) {
        Log.d(TAG, "Extra upgrade");
        score = extraCookieMonster.buyUpgrade(score);
        updateScoreLabel();
    }

    public void upgradeBasic(View view) {
        Log.d(TAG, "Basic upgrade");
        score = cookieMonster.buyUpgrade(score);
        updateScoreLabel();
    }

    public void exitGame(View view) {
        Log.d(TAG, "Exiting - Bye bye!");
        finishAndRemoveTask();
    }

    public void goToScore(View view) {
        Log.d(TAG, "Go to HighScore View");
        Intent intent = new Intent(MainActivity.this, HighScore.class);
        startActivity(intent);
    }
}
