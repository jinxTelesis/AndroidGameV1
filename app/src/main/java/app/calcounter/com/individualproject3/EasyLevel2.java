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
import static app.calcounter.com.individualproject3.Constants.Constant.STAGE_2_SCORE;

public class EasyLevel2 extends AppCompatActivity {

    private int startAnimationCounter = 0;
    private boolean firstTime1 = true;
    private boolean firstTime2 = true;
    private boolean firstTime3 = true;
    private boolean firstTime4 = true;
    private int playerScore = 0;
    private Intent restartIntent;

    private MediaPlayer mediaPlayer;
    public static final int MEDIA_RES_ID = R.raw.backgroundmusic;

    private SharedPreferences myPrefs;



    @BindView(R.id.stage2EwokID)ImageView ewok;

    @BindView(R.id.stage2buttonExit)Button exitBtn;
    @BindView(R.id.stage2buttonReplay)Button replayBtn;


    StrtDrgLsntr strtDrgLsntr;
    EndDrgLsntr endDrgLsntr;


    private AnimationSet fullAnimation;

    private TranslateAnimation move1;
    private TranslateAnimation move2;
    private TranslateAnimation move3;
    private TranslateAnimation move4;
    private TranslateAnimation move5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_level2);

        mediaPlayer = new MediaPlayer();
        mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.backgroundmusic);
        mediaPlayer.start();

        ButterKnife.bind(this);
        fullAnimation = new AnimationSet(true);
        restartIntent = getIntent();

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        int moveSize1 = (int) (width / 2.80);
        int moveSize2 = (int) (height / 3.7);
        int moveSize3 = (int) (width / 3.3);
        int moveSize4 = -1*(int) (height / 3.2);
        int moveSize5 = (int) (width / 2.85);

        int moveSize6 = (int) (width / 2.80);
        int moveSize7 = -1 * (int) (height / 2.85);
        int moveSize8 = (int) (width / 3.3);
        int moveSize9 = (int) (height / 3.45);
        int moveSize10 = (int) (width / 2.85);


        strtDrgLsntr = new StrtDrgLsntr();
        endDrgLsntr = new EndDrgLsntr();


        findViewById(R.id.stage2buttonDownID).setOnLongClickListener(strtDrgLsntr);
        findViewById(R.id.stage2buttonUpID).setOnLongClickListener(strtDrgLsntr);
        findViewById(R.id.stage2buttonLeftID).setOnLongClickListener(strtDrgLsntr);
        findViewById(R.id.stage2buttonRightID).setOnLongClickListener(strtDrgLsntr);

        findViewById(R.id.stage2button1).setOnDragListener(endDrgLsntr);
        findViewById(R.id.stage2button2).setOnDragListener(endDrgLsntr);
        findViewById(R.id.stage2button3).setOnDragListener(endDrgLsntr);
        findViewById(R.id.stage2button4).setOnDragListener(endDrgLsntr);


        // other movement
        move1 = new TranslateAnimation(0, moveSize6, 0,0);
        move1.setDuration(5000);
        move1.setFillAfter(true);
        fullAnimation.addAnimation(move1);

        // reverse of other
        move2 = new TranslateAnimation(0,0,0, moveSize7);
        move2.setDuration(5000);
        move2.setFillAfter(true);
        move2.setStartOffset(6000);
        fullAnimation.addAnimation(move2);

        move3 = new TranslateAnimation(0, moveSize8,0,0);
        move3.setDuration(5000);
        move3.setFillAfter(true);
        move3.setStartOffset(12000);
        fullAnimation.addAnimation(move3);

        move4 = new TranslateAnimation(0, 0,0,moveSize9);
        move4.setDuration(5000);
        move4.setFillAfter(true);
        move4.setStartOffset(18000);
        fullAnimation.addAnimation(move4);

        move5 = new TranslateAnimation(0, moveSize10,0,0);
        move5.setDuration(5000);
        move5.setStartOffset(24000);
        fullAnimation.addAnimation(move5);
        move5.setFillAfter(true);
        //ewok.startAnimation(fullAnimation);

    }

    @OnClick(R.id.stage2buttonExit)
    public void exitGame(View view)
    {
        this.finishAffinity();
    }

    // this button replays level without saving score
    @OnClick(R.id.stage2buttonReplay)
    public void restartLevel(View view)
    {
        finish();
        startActivity(restartIntent);
    }





    private class StrtDrgLsntr implements View.OnLongClickListener{


        @Override
        public boolean onLongClick(View v) {
            WithShadow withShadow = new WithShadow(v);

            if(v.getId() == R.id.stage2buttonDownID)
            {
                ClipData data = ClipData.newPlainText("senderdown", "down");
                v.startDrag(data,withShadow,v,0);

            }

            if(v.getId() == R.id.stage2buttonUpID)
            {
                ClipData data = ClipData.newPlainText("senderup", "up");
                v.startDrag(data,withShadow,v,0);
            }

            if(v.getId() == R.id.stage2buttonRightID)
            {
                ClipData data = ClipData.newPlainText("senderright","right");
                v.startDrag(data,withShadow,v,0);
            }

            if(v.getId() == R.id.stage2buttonLeftID)
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



                if(v.getId() == R.id.stage2button1)
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
                    else
                    {
                        if(s1.equals("up"))
                        {
                            if(firstTime1)
                            {
                                ++startAnimationCounter;
                                firstTime1 = false;
                                playerScore+=25;
                            }
                        }
                    }
                }


                if(v.getId() == R.id.stage2button2)
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

                if(v.getId() == R.id.stage2button3)
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
                    else
                    {
                        if(s1.equals("down"))
                        {
                            if(firstTime3)
                            {
                                ++startAnimationCounter;
                                firstTime3 = false;
                                playerScore+=25;
                            }
                        }
                    }
                }

                if(v.getId() == R.id.stage2button4)
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

                        editor.putInt(STAGE_2_SCORE, playerScore);
                        editor.apply();

                        Intent intent = new Intent(EasyLevel2.this,EasyLevel3.class);
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
