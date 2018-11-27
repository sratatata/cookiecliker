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

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();

    public static Integer score = 0;
    private Double time = 0.0;
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

        vibrator = (Vibrator)getSystemService(VIBRATOR_SERVICE);
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
        vibrator.vibrate(100);
    }

    public void updateTime() {
        DecimalFormat df = new DecimalFormat("0.0");
        timeView.setText("Time: " + df.format(time));
    }

    public void updateScore(){
        scoreView.setText("Score: " +score.toString());
    }

    public void exitGame(View view) {
        Log.d(TAG, "Exiting - Bye bye!");
        finishAndRemoveTask();
    }

    public void reset(View view) {
       // reset
    }

    public void goToScore(View view) {
        Log.d(TAG, "Go to HighScore View");
        Intent intent = new Intent(MainActivity.this, HighScore.class);
        startActivity(intent);
    }

    public void upgradeBasic(View view) {
        // Obsługa Buttona Basic
    }

    public void upgradeExtra(View view) {
        // Obsługa Buttona Extra
    }

}
