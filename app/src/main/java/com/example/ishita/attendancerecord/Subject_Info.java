package com.example.ishita.attendancerecord;

/**
 * Created by ishita on 16/4/16.
 */
public class Subject_Info {

    private String _subCode;
    private String _subName;
    private int _LHall;
    private int _subTime;
    private double _count;
    private String _fid;

    public Subject_Info(){

    }

    public Subject_Info(String subCode, String subName, int LHall, int subTime, String fid) {
        this._subCode = subCode;
        this._subName = subName;
        this._LHall = LHall;
        this._subTime = subTime;
        this._count = 1;
        this._fid = fid;
    }

    public void set_subCode(String subCode) {
        this._subCode = subCode;
    }

    public void set_subName(String subName) {
        this._subName = subName;
    }

    public void set_LHall(int LHall) {
        this._LHall = LHall;
    }

    public void set_subTime(int subTime) {
        this._subTime = subTime;
    }

    public void set_count(double count){
        this._count = count;
    }

    public void set_count(String _subCode){
        _count=_count+1;
        this._count= _count;
    }

    public String get_subCode() {
        return _subCode;
    }

    public String get_subName() {
        return _subName;
    }

    public int get_LHall() {
        return _LHall;
    }

    public int get_subTime() {
        return _subTime;
    }

    public double get_count(){
        return _count;
    }
}
