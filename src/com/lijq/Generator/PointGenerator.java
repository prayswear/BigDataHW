package com.lijq.Generator;

import com.lijq.data.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lee&Qin on 2017/3/30.
 */
public class PointGenerator {
    public static List<Point> myPoints = new ArrayList<>();

    public List generatepoints(int pointNum, float x_min, float x_max, float y_min, float y_max) {
        System.out.println("正在生成随机点...");
        int flag = 0;
        while (flag < pointNum) {
            float x_pos = (float) (Math.random() * 100);
            float y_pos = (float) (Math.random() * 100);
            boolean isTrue = false;
            if ((x_pos > x_min && x_pos < x_max) && (y_pos > y_min && y_pos < y_max)) {
                isTrue = true;
            }

            Point temp = new Point(x_pos, y_pos, isTrue);
            myPoints.add(temp);
            flag++;
        }

        for (Point p : myPoints) {
            p.printSelf();

        }
        System.out.println("随机点生成完毕！");
        return this.myPoints;


    }
}
