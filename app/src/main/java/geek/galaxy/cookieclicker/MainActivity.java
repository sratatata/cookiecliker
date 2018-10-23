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

    private Integer score = 0;
    private Double time = 0.0;
    private boolean isPlaying =false;
    private TextView timeView = null;
    private TextView scoreView = null;

    private CookieMonster cookieMonster = null;
    private ExtraCookieMonster extraCookieMonster = null;

    private Handler handler = new Handler();

    private Runnable timeRunnable = new Runnable() {
        @Override
        public void run() {
            if (!isPlaying){
                return;
            }
            if (time > 1d){
                score += 1;
            }
            if (time % 2 == 0){
               score += cookieMonster.getEatedCookies();
            }
            if (time % 4 == 0){
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

        scoreView = (TextView) findViewById(R.id.scoreView);
        timeView = (TextView) findViewById(R.id.timeView);

        final Button cookieButton = (Button)findViewById(R.id.cookie);
        final Button upgrade = (Button)findViewById(R.id.upgrade);
        final Button upgradeExtra = (Button)findViewById(R.id.upgrade_extra);

        final Button resetButton = (Button)findViewById(R.id.reset);
        final Button exitButton = (Button)findViewById(R.id.exit);

        cookieMonster = new CookieMonster((ImageView)findViewById(R.id.cookieEater1), (TextView)findViewById(R.id.cookieEaterText1) );
        extraCookieMonster = new ExtraCookieMonster((ImageView)findViewById(R.id.cookieEater2), (TextView)findViewById(R.id.cookieEaterText2));

        cookieButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPlaying){
                    score += 1;
                    updateScore();
                }else{
                    handler.postDelayed(timeRunnable, 100);
                    score = 1;
                    updateScore();
                    isPlaying = true;
                }
            }
        });

        upgrade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score = cookieMonster.buyUpgrade(score);
                updateScore();
            }
        });

        upgradeExtra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score = extraCookieMonster.buyUpgrade(score);
                updateScore();
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.d("reset", "RESET");
                isPlaying = false;
                score = 0;
                time = 0.0;
                cookieMonster.reset();
                extraCookieMonster.reset();
                updateTime();
                updateScore();
            }
        });

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAndRemoveTask();
            }
        });
    }

    public void updateTime(){
        DecimalFormat df = new DecimalFormat("0.0");
        timeView.setText("Time: " + df.format(time));
    }

    public void updateScore(){
        scoreView.setText("Score: " + score.toString());
    }
}
