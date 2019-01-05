package app.calcounter.com.individualproject3;

import android.provider.BaseColumns;

public class RegisterContract {
    /** google recommended contract
     *  more or less constants that fill in a sqlLite
     *  create table list, works similar to mySql
     *
     */

        private RegisterContract() {}

        public static class RegistrationEntry implements BaseColumns {
            public static final String COL_ADULT_FIRST_NAME = "adultfirstname";
            public static final String COL_ADULT_LAST_NAME = "adultlastname";
            public static final String COL_DATE_OF_BIRTH = "dateofbirth";
            public static final String COL_EMAILADDRESS = "emailaddress";
            public static final String COL_ADULT_USERNAME = "adultusername";
            public static final String COL_ADULT_PASSWORD = "adultpassword";
            public static final String COL_CHILD_NAME = "childname";
            public static final String COL_CHILD_USERNAME = "childusername";
            public static final String COL_CHILD_PASSWORD = "childpassword";
        }


        public static final String TABLE_NAME = "student_table";


        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        RegistrationEntry._ID + " INTEGER PRIMARY KEY," +
                        RegistrationEntry.COL_ADULT_FIRST_NAME + " TEXT," +
                        RegistrationEntry.COL_ADULT_LAST_NAME + " TEXT," +
                        RegistrationEntry.COL_DATE_OF_BIRTH + " INTEGER," +
                        RegistrationEntry.COL_EMAILADDRESS + " TEXT," +
                        RegistrationEntry.COL_ADULT_USERNAME + " TEXT," +
                        RegistrationEntry.COL_ADULT_PASSWORD + " TEXT," +
                        RegistrationEntry.COL_CHILD_NAME + " TEXT," +
                        RegistrationEntry.COL_CHILD_USERNAME + " TEXT," +
                        RegistrationEntry.COL_CHILD_PASSWORD + " TEXT)";

        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + TABLE_NAME;


}
