package com.example.ishita.attendancerecord;

/**
 * Created by ishita on 16/4/16.
 */
public class ARecord {
    private int _rfid;
    private int _subCode;
    private double _attd;
    boolean attendance;
    public ARecord(){

    }

    public ARecord(int rfid, int subCode) {
        this._rfid = rfid;
        this._subCode = subCode;
    }

    public void set_rfid(int rfid) {
        this._rfid = rfid;
    }

    public void set_subCode(int subCode) {
        this._subCode = subCode;
    }

    public void set_attd(double attd) {
        this._attd = attd;
    }

    public void set_attd(int rfid, boolean attendance ){
        this._rfid=rfid;
        if(attendance==true){
            _attd=_attd+1;
        }
        this._attd =_attd;
    }

    public int get_rfid() {
        return _rfid;
    }

    public int get_subCode() {
        return _subCode;
    }

    public double get_attd() {
        return _attd;
    }
}
