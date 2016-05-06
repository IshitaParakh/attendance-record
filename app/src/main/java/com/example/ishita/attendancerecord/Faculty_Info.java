package com.example.ishita.attendancerecord;

/**
 * Created by ishita on 16/4/16.
 */
public class Faculty_Info {

    private String _fid;
    private String _fname;
    private int _subCode;
    private int _pass;

    public Faculty_Info(){

    }

    public Faculty_Info(String fid, String fname, int subCode, int pass) {
        this._fid = fid;
        this._fname = fname;
        this._subCode = subCode;
        this._pass = pass;
    }

    public void set_fid(String fid) {
        this._fid = fid;
    }

    public void set_fname(String fname) {
        this._fname = fname;
    }

    public void set_subCode(int subCode) {
        this._subCode = subCode;
    }

    public void set_pass(int pass) {
        this._pass = pass;
    }

    public String get_fid() {
        return _fid;
    }

    public String get_fname() {
        return _fname;
    }

    public int get_pass() {
        return _pass;
    }

    public int get_subCode() {
        return _subCode;
    }
}
