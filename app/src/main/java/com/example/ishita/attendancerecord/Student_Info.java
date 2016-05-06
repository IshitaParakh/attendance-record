package com.example.ishita.attendancerecord;

/**
 * Created by ishita on 16/4/16.
 */
public class Student_Info {

    private int _rfid;
    private String _sname;
    private int _year;
    private int _batch;

    public Student_Info(){

    }

    public Student_Info(int rfid, String sname, int year, int batch) {
        this._rfid = rfid;
        this._sname = sname;
        this._year = year;
        this._batch = batch;
    }

    public void set_rfid(int rfid) {
        this._rfid = rfid;
    }

    public void set_sname(String sname) {
        this._sname = sname;
    }

    public void set_year(int year) {
        this._year = year;
    }

    public void set_batch(int batch) {
        this._batch = batch;
    }

    public int get_batch() {
        return _batch;
    }

    public int get_rfid() {
        return _rfid;
    }

    public String get_sname() {
        return _sname;
    }

    public int get_year() {
        return _year;
    }
}
