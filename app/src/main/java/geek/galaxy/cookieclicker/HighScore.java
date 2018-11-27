package geek.galaxy.cookieclicker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.ArrayList;

public class HighScore extends AppCompatActivity {

    private static final String TAG = HighScore.class.getName();
    private TextView highScore = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);

        highScore = findViewById(R.id.highscore_list);
        updateHighScore();
    }

    public void updateHighScore(){
        ArrayList<Integer> highScoreList = MainActivity.getHighScore();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("High score: \n");

        for (Integer each : highScoreList){
            stringBuilder.append(Integer.toString(each));
            stringBuilder.append("\n");
        }
        highScore.setText(stringBuilder.toString());
    }

}
