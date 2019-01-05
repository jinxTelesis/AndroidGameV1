package app.calcounter.com.individualproject3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static app.calcounter.com.individualproject3.Constants.Constant.CURPLAYER;
import static app.calcounter.com.individualproject3.Constants.Constant.STAGE_1_SCORE;
import static app.calcounter.com.individualproject3.Constants.Constant.STAGE_2_SCORE;
import static app.calcounter.com.individualproject3.Constants.Constant.STAGE_3_SCORE;

/** this activity just reads in scores
 *
 */

public class ChildScore extends AppCompatActivity {



    private MediaPlayer mediaPlayer;
    @BindView(R.id.textViewChildScore1)TextView score1TV;
    @BindView(R.id.textViewChildScore2)TextView score2TV;
    @BindView(R.id.textViewChildScore3)TextView score3TV;
    @BindView(R.id.childscoreReplayID) Button replayBtn;
    @BindView(R.id.child_score_exitid) Button exit;

    private SharedPreferences myPrefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_score);

        mediaPlayer = new MediaPlayer();
        mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.backgroundmusic);
        mediaPlayer.start();


        Intent previous = getIntent();
        Bundle userbundle = previous.getExtras();
        ButterKnife.bind(this);

        myPrefs = getSharedPreferences(userbundle.getString(CURPLAYER),0);
        //Log.e("currentplay value",userbundle.getString("curplayer"));
        SharedPreferences.Editor editor = myPrefs.edit();

        //Log.e("score value", Integer.toString(myPrefs.getInt("stage1score",0)));

        //String tempscore1 = Integer.toString(myPrefs.getInt("waffle",0));

        String tempscore1 = Integer.toString(myPrefs.getInt(STAGE_1_SCORE,0));


        String tempscore2 = Integer.toString(myPrefs.getInt(STAGE_2_SCORE,0));
        String tempscore3 = Integer.toString(myPrefs.getInt(STAGE_3_SCORE,0));

        if(tempscore1 != null)
        {
            score1TV.setText("Stage 1 score: " + tempscore1);
        }
        else
        {
            score1TV.setText("zero");
        }

        if(tempscore2 != null)
        {
            score2TV.setText("Stage 2 score: " + tempscore2);
        }
        else
        {
            score1TV.setText("zero");
        }

        if(tempscore3 != null)
        {
            score3TV.setText("Stage 3 score: " + tempscore3);
        }
        else
        {
            score1TV.setText("zero");
        }
    }

    @OnClick(R.id.child_score_exitid)
    public void exitGame(View view)
    {
        this.finishAffinity();
    }

    // this button replays level without saving score
    @OnClick(R.id.childscoreReplayID)
    public void restartLevel(View view)
    {
        Intent intent = new Intent(ChildScore.this, DifSelection.class);
        Intent previous = getIntent();
        Bundle userbundle = previous.getExtras();
        intent.putExtras(userbundle);
        finish();
        startActivity(intent);

    }
}
