package app.calcounter.com.individualproject3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.charts.Pie;
import com.anychart.core.ui.table.Column;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static app.calcounter.com.individualproject3.Constants.Constant.STAGE_1_SCORE;
import static app.calcounter.com.individualproject3.Constants.Constant.STAGE_2_SCORE;
import static app.calcounter.com.individualproject3.Constants.Constant.STAGE_3_SCORE;
import static app.calcounter.com.individualproject3.Constants.Constant.STAGE_4_SCORE;
import static app.calcounter.com.individualproject3.Constants.Constant.STAGE_5_SCORE;
import static app.calcounter.com.individualproject3.Constants.Constant.STAGE_6_SCORE;

/** ParentsReport reads files to make a pie chart using the any chart api
 *  the report in my other project individualProject4 makes more sense and
 *  is a better formatted bar graph, this class was rushed to get the last 10/10
 *  checklist done for a class and is not coded very well
 *
 */

public class ParentsReport extends AppCompatActivity {



    private SharedPreferences myPrefs;
    private String userKey;
    private MediaPlayer mediaPlayer;

    @BindView(R.id.childusernameEditText)EditText childUsernameInput;
    @BindView(R.id.submitchildusername)Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parents_report);
        ButterKnife.bind(this);

        mediaPlayer = new MediaPlayer();
        mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.backgroundmusic);
        mediaPlayer.start();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                userKey = childUsernameInput.getText().toString();
                myPrefs = getSharedPreferences(userKey,0);
                //Log.e("currentplay value",userbundle.getString("curplayer"));
                SharedPreferences.Editor editor = myPrefs.edit();

                int tempscore1 = (myPrefs.getInt(STAGE_1_SCORE,0));
                Log.e("value you want", Integer.toString(myPrefs.getInt(STAGE_1_SCORE,0)));
                int tempscore2 = (myPrefs.getInt(STAGE_2_SCORE,0));
                int tempscore3 = (myPrefs.getInt(STAGE_3_SCORE,0));

                int tempscore4 = (myPrefs.getInt(STAGE_4_SCORE,0));
                int tempscore5 = (myPrefs.getInt(STAGE_5_SCORE, 0));
                int tempscore6 = (myPrefs.getInt(STAGE_6_SCORE,0));

                Pie pie = AnyChart.pie();

                List<DataEntry> data = new ArrayList<>();
                data.add(new ValueDataEntry("Stage1",tempscore1));
                data.add(new ValueDataEntry("Stage2", tempscore2));
                data.add(new ValueDataEntry( "Stage3", tempscore3));
                data.add(new ValueDataEntry("Stage4", tempscore4));
                data.add(new ValueDataEntry("Stage5", tempscore5));
                data.add(new ValueDataEntry("Stage6", tempscore6));

                pie.data(data);

                AnyChartView anyChartView = (AnyChartView) findViewById(R.id.any_chart_view);
                anyChartView.setChart(pie);
            }
        });

        if(userKey !=null)
        {

        }

    }
}
