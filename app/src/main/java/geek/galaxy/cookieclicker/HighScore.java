package geek.galaxy.cookieclicker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class HighScore extends AppCompatActivity {

    private static final String TAG = HighScore.class.getName();
    private ArrayList<Integer> scores = null;
    private TextView highScore = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);

        highScore = findViewById(R.id.highscore_list);

        add_scores_to_highscore();
    }

    public void add_scores_to_highscore(){
        String text = mock_highscore();

        highScore.setText(text);
    }

    public String mock_highscore(){
        scores = new ArrayList<>();
        for (int i=0 ; i < 10; i++){
            scores.add(i * 10);
        }
        Collections.shuffle(scores, new Random());

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("HIGH SCORE\n");

        for( Integer each : scores){
            stringBuilder.append(each);
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

}
