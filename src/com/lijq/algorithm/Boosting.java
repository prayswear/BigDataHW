package com.lijq.algorithm;

import com.lijq.classifier.BayesClassifier;
import com.lijq.data.Point;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by Lee&Qin on 2017/4/3.
 */
public class Boosting {
    static List<Point> myPoints;
    static int samplingNum;
    BayesClassifier bc1 = new BayesClassifier();
    BayesClassifier bc2 = new BayesClassifier();
    BayesClassifier bc3 = new BayesClassifier();


    public Boosting(List<Point> myPoints) {
        this.myPoints = myPoints;
        samplingNum = (int) (myPoints.size() * 0.6);
    }

    public void startTrain(int n1) {
        List<Point> D1 = createNotPutBackSamplingList(myPoints, n1);
        bc1.setPoints(D1);

        //训练bc1
        List<Point> D_rest = new ArrayList<>();
        for (Point p : myPoints) {
            if (!p.selected) {
                D_rest.add(p);
            }
        }

        //训练bc2
        List<Point> D2 = new ArrayList<>();
        List<Point> bc1_right = new ArrayList<>();
        List<Point> bc1_wrong = new ArrayList<>();
        for (Point p : D_rest) {
            if (bc1.doClassifierForBoolean(p.x_pos, p.y_pos) == p.value) {
                bc1_right.add(p);
            } else {
                bc1_wrong.add(p);
            }
        }

        for (int i = 0; i < bc1_wrong.size(); i++) {
            D2.add(bc1_right.get(i));
            D2.add(bc1_wrong.get(i));
        }
        bc2.setPoints(D2);

        //训练bc3
        List<Point> D3 = new ArrayList<>();
        for (Point p : myPoints) {
            if (bc1.doClassifierForBoolean(p.x_pos, p.y_pos) != bc2.doClassifierForBoolean(p.x_pos, p.y_pos)) {
                D3.add(p);
            }
        }
        bc3.setPoints(D3);

    }

    public void testPoint() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入x值：");
        float testPointX = scanner.nextFloat();
        System.out.print("请输入y值：");
        float testPointY = scanner.nextFloat();
        boolean bc1_result = bc1.doClassifierForBoolean(testPointX, testPointY);
        boolean bc2_result = bc2.doClassifierForBoolean(testPointX, testPointY);
        if (bc1_result == bc2_result) {
            System.out.println("该点的值为:" + bc1_result);
        } else {
            System.out.println("该点的值为:" + bc3.doClassifierForBoolean(testPointX, testPointY));
        }

    }

    private static List createNotPutBackSamplingList(List list, int n) {
        Map map = new HashMap();
        List listNew = new ArrayList();
        if (list.size() <= n) {
            return list;
        } else {
            while (map.size() < n) {
                int random = (int) (Math.random() * list.size());
                if (!map.containsKey(random)) {
                    map.put(random, "");
                    Point temp = (Point) list.get(random);
                    temp.selected = true;
                    listNew.add(temp);
                }
            }
            return listNew;
        }
    }


}
