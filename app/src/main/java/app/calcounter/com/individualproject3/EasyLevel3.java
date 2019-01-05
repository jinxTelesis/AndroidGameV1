package app.calcounter.com.individualproject3;

import android.content.ClipData;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.DragEvent;
import android.view.View;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static app.calcounter.com.individualproject3.Constants.Constant.CURPLAYER;
import static app.calcounter.com.individualproject3.Constants.Constant.STAGE_3_SCORE;

public class EasyLevel3 extends AppCompatActivity {

    private MediaPlayer mediaPlayer;

    StrtDrgLsntr strtDrgLsntr;
    EndDrgLsntr endDrgLsntr;

    private SharedPreferences myPrefs;

    private int startAnimationCounter = 0;
    private boolean firstTime1 = true;
    private boolean firstTime2 = true;
    private boolean firstTime3 = true;
    private boolean firstTime4 = true;
    private int playerScore = 0;
    private Intent restartIntent;

    @BindView(R.id.stage3EwokID)ImageView ewok;
    @BindView(R.id.stage3buttonExit)Button exitBtn;
    @BindView(R.id.stage3buttonReplay)Button replayBtn;

    private AnimationSet fullAnimation;

    private TranslateAnimation move1;
    private TranslateAnimation move2;
    private TranslateAnimation move3;
    private TranslateAnimation move4;
    private TranslateAnimation move5;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_level3);

        mediaPlayer = new MediaPlayer();
        mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.backgroundmusic);
        mediaPlayer.start();

        strtDrgLsntr = new StrtDrgLsntr();
        endDrgLsntr = new EndDrgLsntr();

        ButterKnife.bind(this);
        fullAnimation = new AnimationSet(true);
        restartIntent = getIntent();

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        int moveSize1 = (int) (height / 2.5);
        int moveSize2 = (int) (width / 1.15);
        int moveSize3 = -1*(int) (height / 1.6);
        int moveSize4 = -1*(int) (width / 1.2);
        int moveSize5 = (int) (height / 2.85);

        findViewById(R.id.stage3buttonDownID).setOnLongClickListener(strtDrgLsntr);
        findViewById(R.id.stage3buttonUpID).setOnLongClickListener(strtDrgLsntr);
        findViewById(R.id.stage3buttonRightID).setOnLongClickListener(strtDrgLsntr);
        findViewById(R.id.stage3buttonLeftID).setOnLongClickListener(strtDrgLsntr);

        findViewById(R.id.stage3button1).setOnDragListener(endDrgLsntr);
        findViewById(R.id.stage3button2).setOnDragListener(endDrgLsntr);
        findViewById(R.id.stage3button3).setOnDragListener(endDrgLsntr);
        findViewById(R.id.stage3button4).setOnDragListener(endDrgLsntr);

        move1 = new TranslateAnimation(0, 0, 0,moveSize1);
        move1.setDuration(5000);
        move1.setFillAfter(true);
        fullAnimation.addAnimation(move1);

        // reverse of other
        move2 = new TranslateAnimation(0,moveSize2,0, 0);
        move2.setDuration(5000);
        move2.setFillAfter(true);
        move2.setStartOffset(6000);
        fullAnimation.addAnimation(move2);

        move3 = new TranslateAnimation(0, 0,0,moveSize3);
        move3.setDuration(5000);
        move3.setFillAfter(true);
        move3.setStartOffset(12000);
        fullAnimation.addAnimation(move3);

        move4 = new TranslateAnimation(0,moveSize4,0,0);
        move4.setDuration(5000);
        move4.setFillAfter(true);
        move4.setStartOffset(18000);
        fullAnimation.addAnimation(move4);

        move5 = new TranslateAnimation(0, 0,0,moveSize5);
        move5.setDuration(5000);
        move5.setStartOffset(24000);
        fullAnimation.addAnimation(move5);
        move5.setFillAfter(true);
        //ewok.startAnimation(fullAnimation);


    }

    @OnClick(R.id.stage3buttonExit)
    public void exitGame(View view)
    {
        this.finishAffinity();
    }

    // this button replays level without saving score
    @OnClick(R.id.stage3buttonReplay)
    public void restartLevel(View view)
    {
        finish();
        startActivity(restartIntent);
    }


    private class StrtDrgLsntr implements View.OnLongClickListener{


        @Override
        public boolean onLongClick(View v) {
            WithShadow withShadow = new WithShadow(v);


            if(v.getId() == R.id.stage3buttonDownID)
            {
                ClipData data = ClipData.newPlainText("senderdown", "down");
                v.startDrag(data,withShadow,v,0);

            }

            if(v.getId() == R.id.stage3buttonUpID)
            {
                ClipData data = ClipData.newPlainText("senderup", "up");
                v.startDrag(data,withShadow,v,0);
            }

            if(v.getId() == R.id.stage3buttonRightID)
            {
                ClipData data = ClipData.newPlainText("senderright","right");
                v.startDrag(data,withShadow,v,0);
            }

            if(v.getId() == R.id.stage3buttonLeftID)
            {
                ClipData data = ClipData.newPlainText("senderleft","left");

                v.startDrag(data,withShadow,v,0);
            }

            return false;
        }
    }


    private class EndDrgLsntr implements View.OnDragListener{

        @Override
        public boolean onDrag(View v, DragEvent event) {
            if(event.getAction() == event.ACTION_DROP){
                v.setBackground(((Button)event.getLocalState()).getBackground());
                if(v.getId() == R.id.stage3button1)
                {
                    ClipData s = event.getClipData();
                    String s1 = (String) s.getItemAt(0).getText();

                    if(s1.equals("right"))
                    {
                        if(firstTime1) // prevents cheating
                        {
                            ++startAnimationCounter;
                            firstTime1 = false;
                            playerScore+= 25;
                        }

                    }
                }


                if(v.getId() == R.id.stage3button2)
                {
                    ClipData s = event.getClipData();
                    String s1 = (String) s.getItemAt(0).getText();

                    if(s1.equals("up"))
                    {
                        if(firstTime2) // prevents cheating
                        {
                            ++startAnimationCounter;
                            firstTime2 = false;
                            playerScore+= 25;
                        }
                    }
                }

                if(v.getId() == R.id.stage3button3)
                {
                    ClipData s = event.getClipData();
                    String s1 = (String) s.getItemAt(0).getText();

                    if(s1.equals("left"))
                    {
                        if(firstTime3) // prevents cheating
                        {
                            ++startAnimationCounter;
                            firstTime3 = false;
                            playerScore+= 25;
                        }
                    }
                }

                if(v.getId() == R.id.stage3button4)
                {
                    ClipData s = event.getClipData();
                    String s1 = (String) s.getItemAt(0).getText();

                    if(s1.equals("down"))
                    {
                        if(firstTime4) // prevents cheating
                        {
                            ++startAnimationCounter;
                            firstTime4 = false;
                            playerScore+=25;
                        }
                    }
                }
            }

            if(startAnimationCounter == 4)
            {
                ewok.startAnimation(fullAnimation);
                Handler handler = new Handler();

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent previous = getIntent();
                        Bundle userbundle = previous.getExtras();

                        myPrefs = getSharedPreferences(userbundle.getString(CURPLAYER),0);
                        SharedPreferences.Editor editor = myPrefs.edit();

                        editor.putInt(STAGE_3_SCORE, playerScore);
                        editor.apply();

                        Intent intent = new Intent(EasyLevel3.this,ChildScore.class);
                        intent.putExtras(userbundle);
                        startActivity(intent);
                        finish();
                    }
                },28000L);
            }

            return true;
        }
    }

    private class WithShadow extends View.DragShadowBuilder{
        public WithShadow(View v){
            super(v);
        }

        @Override
        public void onProvideShadowMetrics(Point outShadowSize, Point outShadowTouchPoint) {
            super.onProvideShadowMetrics(outShadowSize, outShadowTouchPoint);
        }

        @Override
        public void onDrawShadow(Canvas canvas) {
            super.onDrawShadow(canvas);
        }
    }
}
