package geek.galaxy.cookieclicker;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();

    private Integer score = 0;
    private Double time = 0.0;
    private boolean isPlaying = false;
    private TextView timeView = null;
    private TextView scoreView = null;

    private Button cookieButton;
    private Button upgrade;
    private Button upgradeExtra;
    private Button resetButton;
    private Button exitButton;

    private CookieMonster cookieMonster = null;
    private ExtraCookieMonster extraCookieMonster = null;

    private Handler handler = new Handler();

    private Runnable timeRunnable = new Runnable() {
        @Override
        public void run() {
            if (!isPlaying) {
                return;
            }
            if (time > 1d) {
                score += 1;
            }
            if (time % 2 == 0) {
                score += cookieMonster.getEatedCookies();
            }
            if (time % 4 == 0) {
                score += extraCookieMonster.getEatedCookies();
            }
            time += 1d;
            updateTime();
            updateScore();
            handler.postDelayed(timeRunnable, 1000);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scoreView = findViewById(R.id.scoreView);
        timeView = findViewById(R.id.timeView);

        cookieButton = findViewById(R.id.cookie);
        upgrade = findViewById(R.id.upgrade);
        upgradeExtra = findViewById(R.id.upgrade_extra);

        resetButton = findViewById(R.id.reset);
        exitButton = findViewById(R.id.exit);

        cookieMonster = new CookieMonster((ImageView) findViewById(R.id.cookieEater1), (TextView) findViewById(R.id.cookieEaterText1));
        extraCookieMonster = new ExtraCookieMonster((ImageView) findViewById(R.id.cookieEater2), (TextView) findViewById(R.id.cookieEaterText2));


    }

    public void updateTime() {
        DecimalFormat df = new DecimalFormat("0.0");
        timeView.setText("Time: " + df.format(time));
    }

    public void updateScore() {
        scoreView.setText("Score: " + score.toString());
    }

    public void pressCookie(View view) {
        if (isPlaying) {
            score += 1;
            updateScore();
        } else {
            handler.postDelayed(timeRunnable, 100);
            score = 1;
            updateScore();
            isPlaying = true;
        }
    }

    public void reset(View view) {
        Log.d(TAG, "Reset");
        isPlaying = false;
        score = 0;
        time = 0.0;
        cookieMonster.reset();
        extraCookieMonster.reset();
        updateTime();
        updateScore();
    }

    public void upgradeExtra(View view) {
        Log.d(TAG, "Extra upgrade");
        score = extraCookieMonster.buyUpgrade(score);
        updateScore();
    }

    public void upgradeBasic(View view) {
        Log.d(TAG, "Basic upgrade");
        score = cookieMonster.buyUpgrade(score);
        updateScore();
    }

    public void exitGame(View view) {
        Log.d(TAG, "Exiting - Bye bye!");
        finishAndRemoveTask();
    }
}
