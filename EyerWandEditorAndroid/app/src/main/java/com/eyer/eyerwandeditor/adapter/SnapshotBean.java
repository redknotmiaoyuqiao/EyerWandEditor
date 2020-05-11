package com.eyer.eyerwandeditor.adapter;

public class SnapshotBean {
    private double time = 0.0;

    public SnapshotBean(){

    }

    public SnapshotBean(double time){
        this.time = time;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }
}
