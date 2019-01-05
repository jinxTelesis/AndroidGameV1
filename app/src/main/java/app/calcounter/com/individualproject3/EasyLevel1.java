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
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.view.DragEvent;
import android.widget.ImageView;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static app.calcounter.com.individualproject3.Constants.Constant.CURPLAYER;
import static app.calcounter.com.individualproject3.Constants.Constant.STAGE_1_SCORE;

public class EasyLevel1 extends AppCompatActivity {


    /** EasyLevel1 is the first stage on easy mode it has 4 drag and drop buttons
     *  if the player drags the correct buttons onto the blank button fields
     *  then a traversal starts which is stored as an animation set
     *  the animations are done as a percentage of the screen
     *  this should be measured in a professional class but was roughly done for
     *  class work the drag listeners take in the event info to check if the player
     *  dragged the correct button symbol over
     *
     *  if the correct selections are made this activity will pass the score
     *  to the next activity EasyLevel2
     *
     *
     */

    private SharedPreferences myPrefs;
    private MediaPlayer mediaPlayer;

    private int startAnimationCounter = 0;
    private boolean firstTime1 = true;
    private boolean firstTime2 = true;
    private boolean firstTime3 = true;
    private boolean firstTime4 = true;
    private int playerScore = 0;



    StrtDrgLsntr strtDrgLsntr;
    EndDrgLsntr endDrgLsntr;
    @BindView(R.id.stage1ewokID)ImageView ewok;
    @BindView(R.id.stage1buttonExit)Button exit;

    private AnimationSet fullAnimation;

    private TranslateAnimation move1;
    private TranslateAnimation move2;
    private TranslateAnimation move3;
    private TranslateAnimation move4;
    private TranslateAnimation move5;

    private Intent restartIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // restart intent

        mediaPlayer = new MediaPlayer();
        mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.backgroundmusic);
        mediaPlayer.start();

        restartIntent = getIntent();

        setContentView(R.layout.activity_easy_level1);
        ButterKnife.bind(this);
        fullAnimation = new AnimationSet(true);

        mediaPlayer = new MediaPlayer();


        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        int moveSize1 = (int) (width / 2.80);
        int moveSize2 = (int) (height / 3.7);
        int moveSize3 = (int) (width / 3.3);
        int moveSize4 = -1*(int) (height / 1.7);
        int moveSize5 = (int) (width / 2.85);

        strtDrgLsntr = new StrtDrgLsntr();
        endDrgLsntr = new EndDrgLsntr();

        findViewById(R.id.stage1buttonRightID).setOnLongClickListener(strtDrgLsntr);
        findViewById(R.id.stage1buttonLeftID).setOnLongClickListener(strtDrgLsntr);
        findViewById(R.id.stage1buttonDownID).setOnLongClickListener(strtDrgLsntr);
        findViewById(R.id.stage1buttonUpID).setOnLongClickListener(strtDrgLsntr);

        findViewById(R.id.stage1button1).setOnDragListener(endDrgLsntr);
        findViewById(R.id.stage1button2).setOnDragListener(endDrgLsntr);
        findViewById(R.id.stage1button3).setOnDragListener(endDrgLsntr);
        findViewById(R.id.stage1button4).setOnDragListener(endDrgLsntr);

        //ToDO need to put this in control logic also
        move1 = new TranslateAnimation(0, moveSize1, 0,0);
        move1.setDuration(5000);
        move1.setFillAfter(true);
        fullAnimation.addAnimation(move1);

        // move two
        move2 = new TranslateAnimation(0,0,0,moveSize2);
        move2.setDuration(5000);
        move2.setFillAfter(true);
        move2.setStartOffset(6000);
        fullAnimation.addAnimation(move2);

        move3 = new TranslateAnimation(0, moveSize3,0,0);
        move3.setDuration(5000);
        move3.setFillAfter(true);
        move3.setStartOffset(12000);
        fullAnimation.addAnimation(move3);


        move4 = new TranslateAnimation(0, 0,0,moveSize4);
        move4.setDuration(5000);
        move4.setFillAfter(true);
        move4.setStartOffset(18000);
        fullAnimation.addAnimation(move4);


        move5 = new TranslateAnimation(0, moveSize5,0,0);
        move5.setDuration(5000);
        move5.setStartOffset(24000);
        fullAnimation.addAnimation(move5);
        move5.setFillAfter(true);




    }

    // this button exits the game without saving score
    // might change that
    @OnClick(R.id.stage1buttonExit)
    public void exitGame(View view)
    {
        this.finishAffinity();
    }

    // this button replays level without saving score
    @OnClick(R.id.stage1buttonReplay)
    public void restartLevel(View view)
    {
        finish();
        startActivity(restartIntent);
    }

    private class StrtDrgLsntr implements View.OnLongClickListener{


        @Override
        public boolean onLongClick(View v) {
            WithShadow withShadow = new WithShadow(v);

            if(v.getId() == R.id.stage1buttonDownID)
            {
                ClipData data = ClipData.newPlainText("senderdown", "down");
                v.startDrag(data,withShadow,v,0);

            }

            if(v.getId() == R.id.stage1buttonUpID)
            {
                ClipData data = ClipData.newPlainText("senderup", "up");
                v.startDrag(data,withShadow,v,0);
            }

            if(v.getId() == R.id.stage1buttonRightID)
            {
                ClipData data = ClipData.newPlainText("senderright","right");
                v.startDrag(data,withShadow,v,0);
            }

            if(v.getId() == R.id.stage1buttonLeftID)
            {
                ClipData data = ClipData.newPlainText("senderleft","left");

                v.startDrag(data,withShadow,v,0);
            }



            //Log.e("output",data.toString());
            return false;
        }
    }


    private class EndDrgLsntr implements View.OnDragListener{

        @Override
        public boolean onDrag(View v, DragEvent event) {
            if(event.getAction() == event.ACTION_DROP){
                v.setBackground(((Button)event.getLocalState()).getBackground());

                if(v.getId() == R.id.stage1button1)
                {
                    ClipData s = event.getClipData();
                    String s1 = (String) s.getItemAt(0).getText();

                    if(s1.equals("down"))
                    {
                        if(firstTime1) // prevents cheating
                        {
                            ++startAnimationCounter;
                            firstTime1 = false;
                            playerScore+= 25;
                        }

                    }
                }


                if(v.getId() == R.id.stage1button2)
                {
                    ClipData s = event.getClipData();
                    String s1 = (String) s.getItemAt(0).getText();

                    if(s1.equals("right"))
                    {
                        if(firstTime2) // prevents cheating
                        {
                            ++startAnimationCounter;
                            firstTime2 = false;
                            playerScore+= 25;
                        }
                    }
                }

                if(v.getId() == R.id.stage1button3)
                {
                    ClipData s = event.getClipData();
                    String s1 = (String) s.getItemAt(0).getText();

                    if(s1.equals("up"))
                    {
                        if(firstTime3) // prevents cheating
                        {
                            ++startAnimationCounter;
                            firstTime3 = false;
                            playerScore+= 25;
                        }
                    }
                }

                if(v.getId() == R.id.stage1button4)
                {
                    ClipData s = event.getClipData();
                    String s1 = (String) s.getItemAt(0).getText();

                    if(s1.equals("right"))
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

                        editor.putInt(STAGE_1_SCORE, playerScore);
                        editor.apply();



                        Intent intent = new Intent(EasyLevel1.this,EasyLevel2.class);
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
