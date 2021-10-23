package com.swufe.stu.finaltestnew;

public class AccItem {
    private int id;
    private String curType;
    private String curCate;
    private String curTime;
    private String curRmb;
    private String curNote;
    public AccItem() {
        super();

        curType = "";
        curCate = "";
        curTime = "";

        curNote = "";
    }


    public AccItem(String curType, String curCate, String curTime, String curRmb, String curNote) {
        super();

        this.curType = curType;
        this.curCate = curCate;
        this.curTime = curTime;
        this.curRmb = curRmb;
        this.curNote = curNote;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCurType() {
        return curType;
    }

    public void setCurType(String curType) {
        this.curType = curType;
    }

    public String getCurCate() {
        return curCate;
    }

    public void setCurCate(String curCate) {
        this.curCate = curCate;
    }

    public String getCurTime() {
        return curTime;
    }

    public void setCurTime(String curTime) {
        this.curTime = curTime;
    }

    public String getCurRmb() {
        return curRmb;
    }

    public void setCurRmb(String curRmb) {
        this.curRmb = curRmb;
    }

    public String getCurNote() {
        return curNote;
    }

    public void setCurNote(String curNote) {
        this.curNote = curNote;
    }
}
