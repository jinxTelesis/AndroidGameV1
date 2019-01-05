package app.calcounter.com.individualproject3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static app.calcounter.com.individualproject3.Constants.Constant.CURPLAYER;
import static app.calcounter.com.individualproject3.Constants.Constant.STAGE_4_SCORE;
import static app.calcounter.com.individualproject3.Constants.Constant.STAGE_5_SCORE;
import static app.calcounter.com.individualproject3.Constants.Constant.STAGE_6_SCORE;

/** this activity just reads in scores
 *
 */

public class ChildScoreHard extends AppCompatActivity {

    private MediaPlayer mediaPlayer;

    @BindView(R.id.stage4scoreTV)TextView score4;
    @BindView(R.id.stage5scoreTV)TextView score5;
    @BindView(R.id.stage6scoreTV)TextView score6;
    @BindView(R.id.childscorehardReplayID) Button replay;
    @BindView(R.id.child_scorehard_exitid) Button exit;

    private SharedPreferences myPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_score_hard);
        ButterKnife.bind(this);

        mediaPlayer = new MediaPlayer();
        mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.backgroundmusic);
        mediaPlayer.start();


        Intent previous = getIntent();
        Bundle userbundle = previous.getExtras();

        myPrefs = getSharedPreferences(userbundle.getString(CURPLAYER),0);
        SharedPreferences.Editor editor = myPrefs.edit();

        score4.setText("stage 4 score: " + myPrefs.getInt(STAGE_4_SCORE,0));
        score5.setText("stage 5 score: " + myPrefs.getInt(STAGE_5_SCORE,0));
        score6.setText("stage 6 score: " + myPrefs.getInt(STAGE_6_SCORE,0));


    }

    @OnClick(R.id.child_scorehard_exitid)
    public void exitGame(View view)
    {
        this.finishAffinity();
    }

    // this button replays level without saving score
    @OnClick(R.id.childscorehardReplayID)
    public void restartLevel(View view)
    {
        Intent intent = new Intent(ChildScoreHard.this, DifSelection.class);
        Intent previous = getIntent();
        Bundle userbundle = previous.getExtras();
        intent.putExtras(userbundle);
        finish();
        startActivity(intent);
    }
}
