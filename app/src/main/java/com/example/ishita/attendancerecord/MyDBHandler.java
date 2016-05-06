package com.example.ishita.attendancerecord;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;

public class MyDBHandler extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Attd_Record.db";

    private static final String TABLE_STUDENT = "student";
    private static final String COLUMN_RFID = "rfid";
    private static final String COLUMN_SNAME = "sname";
    private static final String COLUMN_YEAR = "year";
    private static final String COLUMN_BATCH = "batch";

    private static final String TABLE_RECORD = "arecord";
    private static final String COLUMN_RFID_RECORD = "rfid_record";
    private static final String COLUMN_SUBCODE_RECORD = "subcode_record";
    private static final String COLUMN_ATTD = "attd";

    private static final String TABLE_SUBJECT = "subject";
    private static final String COLUMN_SUBCODE = "subcode";
    private static final String COLUMN_LHALL = "lhall";
    private static final String COLUMN_SUBNAME = "subname";
    private static final String COLUMN_SUBTIME = "subtime";
    private static final String COLUMN_COUNT = "count";

    private static final String TABLE_FACULTY = "faculty";
    private static final String COLUMN_FID = "fid";
    private static final String COLUMN_FNAME = "fname";
    private static final String COLUMN_SUBCODE_FACULTY = "subcode_faculty";
    private static final String COLUMN_PASS = "pass";

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query_table_student = " CREATE TABLE IF NOT EXISTS " + TABLE_STUDENT + " (" +
                COLUMN_RFID + " INTEGER PRIMARY KEY "+ "," +
                COLUMN_SNAME + " TEXT " + "," +
                COLUMN_YEAR + " INTEGER " + "," +
                COLUMN_BATCH + " TEXT " +
                ");" ;
        db.execSQL(query_table_student);

        String query_table_subject = " CREATE TABLE IF NOT EXISTS " + TABLE_SUBJECT + " (" +
                COLUMN_SUBCODE + " INTEGER PRIMARY KEY " + "," +
                COLUMN_SUBNAME + " TEXT " + "," +
                COLUMN_LHALL + " TEXT " + "," +
                COLUMN_SUBTIME + " TEXT " + "," +
                COLUMN_COUNT + " INTEGER " +
                ");" ;
        db.execSQL(query_table_subject);

        String query_table_record = " CREATE TABLE IF NOT EXISTS " + TABLE_RECORD + " (" +
                COLUMN_RFID_RECORD + " INTEGER " + "," +
                COLUMN_SUBCODE_RECORD + " INTEGER " + "," +
                COLUMN_YEAR + " INTEGER " + "," +
                COLUMN_BATCH + " TEXT " + "," +
                " FOREIGN KEY( " + COLUMN_RFID_RECORD + ") REFERENCES " +
                TABLE_STUDENT + "(" + COLUMN_RFID + ")" + "," +
                " FOREIGN KEY( " + COLUMN_SUBCODE_RECORD + ") REFERENCES " +
                TABLE_SUBJECT + "(" + COLUMN_SUBCODE + ")" + "," +
                " PRIMARY KEY( " + COLUMN_RFID_RECORD + "," + COLUMN_SUBCODE_RECORD + ")" +
                ");" ;
        db.execSQL(query_table_record);

        String query_table_faculty = " CREATE TABLE IF NOT EXISTS " + TABLE_FACULTY + " (" +
                COLUMN_FID + " INTEGER PRIMARY KEY " + "," +
                COLUMN_FNAME + " TEXT " + "," +
                COLUMN_SUBCODE_FACULTY + " INTEGER " + "," +
                COLUMN_PASS + " TEXT " + "," +
                " FOREIGN KEY( " + COLUMN_SUBCODE_FACULTY + ") REFERENCES " +
                TABLE_SUBJECT + "(" + COLUMN_SUBCODE + ")" + "," +
                ");" ;
        db.execSQL(query_table_faculty);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_RECORD);
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_SUBJECT);
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_FACULTY);
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_STUDENT);
        onCreate(db);
    }

    //Adding new row to database
    public void addStudent(Student_Info Stud){
        ContentValues values = new ContentValues();
        values.put(COLUMN_RFID, Stud.get_rfid());
        values.put(COLUMN_SNAME, Stud.get_sname());
        values.put(COLUMN_YEAR, Stud.get_year());
        values.put(COLUMN_BATCH, Stud.get_batch());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_STUDENT, null, values);
    }

    public void addSubject(Subject_Info Sub){
        ContentValues values = new ContentValues();
        values.put(COLUMN_SUBCODE, Sub.get_subCode());
        values.put(COLUMN_SUBNAME, Sub.get_subName());
        values.put(COLUMN_LHALL, Sub.get_LHall());
        values.put(COLUMN_SUBTIME, Sub.get_subTime());
        values.put(COLUMN_COUNT, Sub.get_count());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_SUBJECT, null, values);
    }

    public void addFaculty(Faculty_Info Fac){
        ContentValues values = new ContentValues();
        values.put(COLUMN_RFID, Fac.get_fid());
        values.put(COLUMN_SNAME, Fac.get_fname());
        values.put(COLUMN_YEAR, Fac.get_subCode());
        values.put(COLUMN_BATCH, Fac.get_pass());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_STUDENT, null, values);
    }

    public void addRecord(ARecord ar){
        ContentValues values = new ContentValues();
        values.put(COLUMN_RFID_RECORD, ar.get_rfid());
        values.put(COLUMN_SUBCODE_RECORD, ar.get_subCode());
        values.put(COLUMN_ATTD, ar.get_attd());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_STUDENT, null, values);
    }

    public Login getFid(String id){
        Login log = new Login();
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT fid, pass FROM " + TABLE_FACULTY + " WHERE fid = " + id +";" ;

        Cursor c = db.rawQuery(query, null);
        if(c != null){
            c.moveToFirst();
            //log. = c.getString(0);
           // log.pass = c.getString(1);
        }
        return log;
    }

    public String getFname(String fid){
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT fname FROM " + TABLE_FACULTY + " WHERE fid = " + fid +";" ;
        String name = "";

        Cursor c = db.rawQuery(query, null);
        if(c != null){
            c.moveToFirst();
            name = c.getString(0);
        }
        return name;
    }
    public String getSubCode(String fid){
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT subcode_faculty FROM " + TABLE_FACULTY + " WHERE fid = " + fid +";" ;

        String code = "";

        Cursor c = db.rawQuery(query, null);
        if(c != null){
            c.moveToFirst();
            code = c.getString(0);
        }
        return code;
    }

    public String getSubName(String subCode){
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT subname FROM " + TABLE_SUBJECT + " WHERE subcode = " + subCode +";" ;

        String subname = "";

        Cursor c = db.rawQuery(query, null);
        if(c != null){
            c.moveToFirst();
            subname = c.getString(0);
        }
        return subname;
    }

    /*public String[] getSchedule(String subCode){
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT subname FROM " + TABLE_SUBJECT + " WHERE subcode = " + subCode +";" ;
        String count_query = "SELECT count(*) FROM " + TABLE_SUBJECT + " GROUP BY subcode ;" ;

        String schedule[] = {};
        Cursor b = db.rawQuery(count_query, null);
        if(b != null)
        {

        }
        Cursor c = db.rawQuery(query, null);
        if(c != null){
            c.moveToFirst();
            for (String every:)
            {

                schedule[every] = c.getString(0);
            }
        }
        return schedule;
    }*/

    public String getRfid(String subCode){
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT rfid FROM " + TABLE_RECORD + " WHERE subcode = " + subCode +";" ;

        String rfid = "";

        Cursor c = db.rawQuery(query, null);
        if(c != null){
            c.moveToFirst();
            rfid = c.getString(0);
        }
        return rfid;
    }

    public String getSname(String rfid){
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT sname FROM " + TABLE_STUDENT + " WHERE rfid = " + rfid +";" ;

        String rfid1 = "";

        Cursor c = db.rawQuery(query, null);
        if(c != null){
            c.moveToFirst();
            rfid1 = c.getString(0);
        }
        return rfid1;
    }

    public void setAttendance(String rfid, boolean attd){
        SQLiteDatabase db = getReadableDatabase();
        if(attd == true){
            String query = "UPDATE attd " + TABLE_RECORD + " SET attd = attd + 1 " +
                    " WHERE rfid_record = " + rfid +";" ;

            Cursor c = db.rawQuery(query, null);
            if(c != null){
                c.moveToFirst();
            }
        }
    }

    public void setCount(String subcode){
        SQLiteDatabase db = getReadableDatabase();
        String query = "UPDATE count " + TABLE_SUBJECT + " SET count = count + 1 " +
                " WHERE subcode = " + subcode +";" ;

        Cursor c = db.rawQuery(query, null);
        if(c != null){
            c.moveToFirst();
        }
    }

    public double getAggregate(String subCode){
        SQLiteDatabase db = getReadableDatabase();
        String agg_query = "SELECT count FROM " + TABLE_SUBJECT + " WHERE subcode = " + subCode + ";" ;
        String attd_query = "SELECT rfid FROM " + TABLE_RECORD + " WHERE subcode = " +subCode + ";" ;

        double agg;
        Cursor c = db.rawQuery(agg_query, null);
        if(c != null){
            c.moveToFirst();
        }
        double attd = 0;
        double count = 1;
        agg = attd/count;
        return agg;
     }
}
