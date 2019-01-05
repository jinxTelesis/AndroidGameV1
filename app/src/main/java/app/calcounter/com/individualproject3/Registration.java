package app.calcounter.com.individualproject3;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static app.calcounter.com.individualproject3.RegisterContract.RegistrationEntry.COL_ADULT_FIRST_NAME;
import static app.calcounter.com.individualproject3.RegisterContract.RegistrationEntry.COL_ADULT_LAST_NAME;
import static app.calcounter.com.individualproject3.RegisterContract.RegistrationEntry.COL_ADULT_PASSWORD;
import static app.calcounter.com.individualproject3.RegisterContract.RegistrationEntry.COL_ADULT_USERNAME;
import static app.calcounter.com.individualproject3.RegisterContract.RegistrationEntry.COL_CHILD_NAME;
import static app.calcounter.com.individualproject3.RegisterContract.RegistrationEntry.COL_CHILD_PASSWORD;
import static app.calcounter.com.individualproject3.RegisterContract.RegistrationEntry.COL_CHILD_USERNAME;
import static app.calcounter.com.individualproject3.RegisterContract.RegistrationEntry.COL_DATE_OF_BIRTH;
import static app.calcounter.com.individualproject3.RegisterContract.RegistrationEntry.COL_EMAILADDRESS;
import static app.calcounter.com.individualproject3.RegisterContract.TABLE_NAME;


// this view makes sure data has valid entries
// lights up in red if not
// also will catch the Exceptions here rather than propagating them up

/** Registration class fills in the attributes from
 *  a number of text fields validation is removed
 *  from previous projects uses sqlite
 *  not a huge fan rather shared preferences or cloud storage
 *
 *
 */

public class Registration extends AppCompatActivity {



    @BindView(R.id.regidbtn) Button mregBtn;
    private MediaPlayer mediaPlayer;


    @BindView(R.id.eTFN) EditText meTAFN;
    @BindView(R.id.eTLN) EditText meTALN;
    @BindView(R.id.eTDOB)EditText meTDOB;
    @BindView(R.id.eTEM) EditText meTEm;
    @BindView(R.id.eTAUN) EditText meTAUN;
    @BindView(R.id.eTAP) EditText meTAP;
    @BindView(R.id.eTCFN) EditText meCFN;
    @BindView(R.id.eTCUN) EditText meTCUN;
    @BindView(R.id.eTCP) EditText meTCP;

    ///////////////////////////////////////////
    StudentDbHelper mDbHelper; // database code


    // test button
    //
    //@BindView(R.id.btntest1ID)Button btnTest1Check;
    //@BindView(R.id.btntest2ID)Button btnTest2Submit;



    private Toast etyFormToast;

    private String mAdultFirstNameReturn ="";
    private String mAdultLastNameReturn ="";
    private String mDOfBirthReturn="";
    private String mEmailReturn="";

    private String mAdultUserNameReturn="";
    private String mAdultPasswordReturn="";


    private String mChildFirstNameReturn ="";
    private String mChildUsernameReturn = "";
    private String mChildPasswordReturn = ""; //ToDo


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        ButterKnife.bind(this);

        mediaPlayer = new MediaPlayer();
        mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.backgroundmusic);
        mediaPlayer.start();



        //////////////////database code///////////////////
        /////////////////////////////////////////////////
        mDbHelper = new StudentDbHelper(this);


        /** this entire setOnClickListener check validates that there is data
         *  in the text fields
         *
         */


        mregBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //ToDO set all text colors to grey
                // for all the different EditTexts

                try{
                    if(!meTAFN.equals(""))
                    {
                        mAdultFirstNameReturn = String.valueOf(meTAFN);
                    }
                    else
                    {
                        mAdultFirstNameReturn = "";
                    }

                }catch (Exception e){};

                try{
                    if(!meTALN.equals(""))
                    {
                        mAdultLastNameReturn = String.valueOf(meTALN);
                    }
                    else
                    {
                        //ToDo
                    }

                }catch (Exception e){};

                try{
                    mDOfBirthReturn = String.valueOf(meTDOB);
                }catch (Exception e){};

                try{
                    mEmailReturn = String.valueOf(meTEm);
                }catch (Exception e){};

                try{
                    mAdultUserNameReturn = String.valueOf(meTAUN);
                }catch (Exception e){};

                try{
                    mAdultPasswordReturn = String.valueOf(meTAP);
                }catch (Exception e){};

                // child credentials

                try{
                    mChildFirstNameReturn = String.valueOf(meCFN);
                }catch (Exception e){};

                try{
                    mChildUsernameReturn = String.valueOf(meTCUN);
                }catch (Exception e){};

                try{
                    mChildUsernameReturn = String.valueOf(meTCP);
                }catch (Exception e){};

                if(true) // not blank
                {
                    if(true) // passes validation
                    {
                        submitRecord(); // writes with no validation
                        Intent intent = new Intent(Registration.this, LoginActivity.class);
                        startActivity(intent);
                        finish();

                    }
                    else if(true) // login matches existing
                    {
                        Context context = getApplicationContext();
                        CharSequence text = "Account already exists!";
                        int duration = Toast.LENGTH_LONG;
                        // return what failed???
                        // change that red
                        Toast toast = Toast.makeText(context,text,duration);
                    }
                    else // invalid login
                    {
                        Context context = getApplicationContext();
                        CharSequence text = "invalid information!";
                        int duration = Toast.LENGTH_LONG;
                        // return what failed???
                        // change that red
                        Toast toast = Toast.makeText(context,text,duration);

                    }

                }
                else // a field was left blank
                {
                    Context context = getApplicationContext();
                    CharSequence text = "One or more fields required!";
                    int duration = Toast.LENGTH_LONG;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();


                    if(mAdultFirstNameReturn.equals(""))
                    {
                        meTAFN.setHintTextColor(Color.RED);
                        meTAFN.setHint("Invalid first name");

                    }

                    if(mAdultLastNameReturn.equals(""))
                    {
                        meTALN.setHintTextColor(Color.RED);
                        meTALN.setHint("Invalid last name");

                    }

                    if(mDOfBirthReturn.equals(""))
                    {
                        meTDOB.setHintTextColor(Color.RED);
                        meTDOB.setHint("Invalid date of birth");
                    }

                    if(mEmailReturn.equals(""))
                    {
                        meTEm.setHintTextColor(Color.RED);
                        meTEm.setHint("Invalid email");

                    }

                    if(mAdultUserNameReturn.equals(""))
                    {
                        meTAUN.setHintTextColor(Color.RED);
                        meTAUN.setHint("Invalid username");

                    }

                    if(mAdultPasswordReturn.equals(""))
                    {
                        meTAP.setHintTextColor(Color.RED);
                        meTAP.setHint("Invalid password");

                    }

                    if(mChildFirstNameReturn.equals(""))
                    {
                        meCFN.setHintTextColor(Color.RED);
                        meCFN.setHint("Invalid child first name");

                    }

                    if(mChildUsernameReturn.equals(""))
                    {
                        meTCUN.setHintTextColor(Color.RED);
                        meTCUN.setHint("Invalid username");

                    }

                }

            }
        });

    }

    //*****************************
    // submitRecord creates and instance of dbHelper then puts the values from the various text fields
    // it is called from within the listener above


    public void submitRecord()
    {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_ADULT_FIRST_NAME, meTAFN.getText().toString());
        values.put(COL_ADULT_LAST_NAME, meTALN.getText().toString());
        values.put(COL_DATE_OF_BIRTH,meTDOB.getText().toString());
        values.put(COL_EMAILADDRESS,meTEm.getText().toString());
        values.put(COL_ADULT_USERNAME,meTAUN.getText().toString());
        values.put(COL_ADULT_PASSWORD,meTAP.getText().toString());
        values.put(COL_CHILD_NAME,meCFN.getText().toString()); // child name?
        values.put(COL_CHILD_USERNAME,meTCUN.getText().toString());
        values.put(COL_CHILD_PASSWORD,meTCP.getText().toString());


        long i = db.insert(TABLE_NAME, null,values);

    }


}


