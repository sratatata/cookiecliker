package geek.galaxy.cookieclicker.highscore;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import geek.galaxy.cookieclicker.R;

public class HighScoreActivity extends AppCompatActivity {

    public static final String EXTRA_HIGH_SCORE = "HIGH_SCORE";
    private static final String TAG = HighScoreActivity.class.getName();
    private static final HighScore EMPTY_HIGH_SCORE = new TopTenHighScore();
    private static final String NEW_LINE = "\n";

    private ArrayList<Integer> scores = null;
    private TextView highScoreText = null;
    private HighScore highScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);

        // gets reference for text view (label) so it could be updated later
        highScoreText = findViewById(R.id.highscore_list);

        // we need to check if extra contains given object, because Activities could also
        // ran in other contexts. So it's crucial to handle empty extra values gently.
        // More reading:
        // https://developer.android.com/reference/android/app/Activity
        // https://developer.android.com/reference/android/content/Intent
        if( getIntent().hasExtra(EXTRA_HIGH_SCORE)){
            this.highScore = (HighScore) getIntent().getSerializableExtra(EXTRA_HIGH_SCORE);
        } else {
            this.highScore = EMPTY_HIGH_SCORE;
        }

        renderOn(highScoreText);
    }

    private void renderOn(TextView highScoreText) {
        int[] scores = highScore.sortedScores(HighScore.DESCENDING );

        StringBuilder sb = new StringBuilder();
        sb.append("High Scores:").append(NEW_LINE);
        sb.append("-----------").append(NEW_LINE);

        // #1 For loop example
        for(int i=1; i<scores.length+1; i++){
            sb.append(String.format("%s - %s", i, scores[i-1])).append(NEW_LINE);
        }

        highScoreText.append(sb.toString());
    }


}
