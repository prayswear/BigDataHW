package com.lijq.data;

/**
 * Created by Lee&Qin on 2017/3/29.
 */
public class Point {
    public float x_pos;
    public float y_pos;
    public boolean value;

    public Point(float x_pos, float y_pos, boolean value) {
        this.x_pos = x_pos;
        this.y_pos = y_pos;
        this.value = value;
    }

    public void printSelf(){
        System.out.println("( "+this.x_pos+" , "+this.y_pos+" ) is "+value);
    }
}
