package app.calcounter.com.individualproject3;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

import static app.calcounter.com.individualproject3.Constants.Constant.CURPLAYER;
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

public class LoginActivity extends AppCompatActivity {

    /** LoginActivity checks if the user name and password are in the list
     *  of attributes in sqlite, I wouldn't write a login like this i would use firebase
     *  or something else. But we had to use sqlite for class
     *
     */

    private MediaPlayer mediaPlayer;

    @BindView(R.id.loginBtnChild) Button cLBtn;
    @BindView(R.id.loginBtnAdult)Button aLBtn;
    @BindView(R.id.regBtnIDLoginAct)Button regBtnLoginAct;
    @BindView(R.id.eTUnLogin)EditText username;
    @BindView(R.id.eTPaLogin2)EditText password;

    StudentDbHelper mDbHelper;
    private int validChildLogin = 0;
    private int validAdultLogin = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        // instance of StudentDbHelper
        mDbHelper = new StudentDbHelper(this);

        mediaPlayer = new MediaPlayer();
        mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.backgroundmusic);
        mediaPlayer.start();

        cLBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // childs game
                // default pass to dif_selection

                SQLiteDatabase db = mDbHelper.getReadableDatabase();

                // list of account attributes

                String[] prof={RegisterContract.RegistrationEntry._ID, COL_ADULT_FIRST_NAME,COL_ADULT_LAST_NAME,COL_DATE_OF_BIRTH,
                        COL_EMAILADDRESS,COL_ADULT_USERNAME,COL_ADULT_PASSWORD,COL_CHILD_NAME,COL_CHILD_USERNAME,COL_CHILD_PASSWORD};

                Cursor cursor = db.query(TABLE_NAME, prof, null,null,null,null,null);

                String str="";
                String test = "";
                String testUsernameStr= "";
                String testPasswordStr= "";

                testUsernameStr = username.getText().toString();
                testPasswordStr = password.getText().toString();

                // where it reads in the attributes
                // if the attributes match a valid login it increments the values for a valid login
                // if the value equals two later it allows the login to occur

                while(cursor.moveToNext())
                {
                    str+= cursor.getString(cursor.getColumnIndexOrThrow(COL_ADULT_FIRST_NAME)) + "\t\t";

                    str+= cursor.getString(cursor.getColumnIndexOrThrow(COL_ADULT_LAST_NAME))+ "\t\t";

                    str+= cursor.getString(cursor.getColumnIndexOrThrow(COL_DATE_OF_BIRTH)) + "\t\t";
                    str+= cursor.getString(cursor.getColumnIndexOrThrow(COL_EMAILADDRESS))+ "\t\t";

                    str+= cursor.getString(cursor.getColumnIndexOrThrow(COL_ADULT_USERNAME));

                    str+= cursor.getString(cursor.getColumnIndexOrThrow(COL_ADULT_PASSWORD));


                    str+= cursor.getString(cursor.getColumnIndexOrThrow(COL_CHILD_NAME)) + "\t\t";


                    test = cursor.getString(cursor.getColumnIndexOrThrow(COL_CHILD_USERNAME));
                    if(test.equals(testUsernameStr))
                    {
                        validChildLogin++;
                    }
                    test =""; // reset test

                    test = cursor.getString(cursor.getColumnIndexOrThrow(COL_CHILD_PASSWORD));
                    if(test.equals(testPasswordStr))
                    {
                        validChildLogin++;
                    }
                    str+= "/n";

                }

                cursor.close();

                //meTAP.setText(str);

                Context context = getApplicationContext();
                CharSequence text = str;
                int duration = Toast.LENGTH_LONG;
                // return what failed???
                // change that  to red
                Toast toast = Toast.makeText(context,text,duration);
                toast.show();

                /**********************************
                 * if both values entered in are valid usersname and pass
                 * lets user access DifSelection activity
                 */

                if(validChildLogin == 2)
                {
                    Bundle bundle = new Bundle();
                    bundle.putString(CURPLAYER, testUsernameStr);

                    Intent intent = new Intent(LoginActivity.this, DifSelection.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    username.setText("");
                    password.setText("");
                    Context context2 = getApplicationContext();
                    CharSequence text2 = "Invalid login, enter username password correctly or register";
                    int duration2 = Toast.LENGTH_LONG;
                    Toast toast2 = Toast.makeText(context2,text2,duration2);
                    toast.show();

                }


                //ToDO
            }
        });

        aLBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = mDbHelper.getReadableDatabase();

                String[] prof={RegisterContract.RegistrationEntry._ID, COL_ADULT_FIRST_NAME,COL_ADULT_LAST_NAME,COL_DATE_OF_BIRTH,
                        COL_EMAILADDRESS,COL_ADULT_USERNAME,COL_ADULT_PASSWORD,COL_CHILD_NAME,COL_CHILD_USERNAME,COL_CHILD_PASSWORD};

                Cursor cursor = db.query(TABLE_NAME, prof, null,null,null,null,null);

                String str="";
                String test = "";
                String testUsernameStr= "";
                String testPasswordStr= "";

                testUsernameStr = username.getText().toString();
                testPasswordStr = password.getText().toString();

                while(cursor.moveToNext())
                {
                    str+= cursor.getString(cursor.getColumnIndexOrThrow(COL_ADULT_FIRST_NAME)) + "\t\t";

                    str+= cursor.getString(cursor.getColumnIndexOrThrow(COL_ADULT_LAST_NAME))+ "\t\t";

                    str+= cursor.getString(cursor.getColumnIndexOrThrow(COL_DATE_OF_BIRTH)) + "\t\t";
                    str+= cursor.getString(cursor.getColumnIndexOrThrow(COL_EMAILADDRESS))+ "\t\t";

                    test= cursor.getString(cursor.getColumnIndexOrThrow(COL_ADULT_USERNAME));

                    if(test.equals(testUsernameStr))
                    {
                        validAdultLogin++;
                    }

                    test ="";
                    test= cursor.getString(cursor.getColumnIndexOrThrow(COL_ADULT_PASSWORD));

                    if(test.equals(testPasswordStr))
                    {
                        validAdultLogin++;
                    }

                    str+= cursor.getString(cursor.getColumnIndexOrThrow(COL_CHILD_NAME)) + "\t\t";


                    str+= cursor.getString(cursor.getColumnIndexOrThrow(COL_CHILD_USERNAME));



                    str+= cursor.getString(cursor.getColumnIndexOrThrow(COL_CHILD_PASSWORD));

                    str+= "/n";

                }

                cursor.close();


                Context context = getApplicationContext();
                CharSequence text = str;
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(context,text,duration);
                toast.show();

                /**********************************
                 * if both values entered in are valid usersname and pass
                 * lets user access DifSelection activity
                 */

                if(validAdultLogin == 2)
                {

                    Intent intent2 = new Intent(LoginActivity.this, ParentsReport.class);
                    startActivity(intent2);
                    finish();
                }
                else
                {
                    username.setText("");
                    password.setText("");
                    Context context2 = getApplicationContext();
                    CharSequence text2 = "Invalid login, enter username password correctly or register";
                    int duration2 = Toast.LENGTH_LONG;
                    Toast toast2 = Toast.makeText(context2,text2,duration2);
                    toast.show();

                }

            }
        });

        //*************************************
        // button goes to registration activity

        regBtnLoginAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // duel registration page
                Intent intent = new Intent(LoginActivity.this, Registration.class);
                startActivity(intent);
                finish();
            }
        });


    }
}
