package com.lijq.classifier;

import com.lijq.data.Point;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Lee&Qin on 2017/3/30.
 */
public class BayesClassifier {
    public List<Point> myPoints = new ArrayList<>();


    public void choosePoints(List<Point> pointList, int n) {
        this.myPoints = createPutBackSamplingList(pointList, n);
    }

    public float doClassifier(float x_pos, float y_pos) {
        float result = 0;
        int true_num = 0;
        int same_X_range_num = 0;
        int same_Y_range_num = 0;
        int same_X_range_true_num = 0;
        int same_Y_range_true_num = 0;
        int scale = myPoints.size();
        float p_x_true = 0;
        float p_y_true = 0;
        float p_true = 0;
        float p_x = 0;
        float p_y = 0;


        for (Point p : myPoints) {
            if (p.value == true) {
                true_num++;
                if (p.x_pos > (int) x_pos && p.x_pos < ((int) x_pos + 1)) {
                    same_X_range_true_num++;

                }
                if (p.y_pos > (int) y_pos && p.y_pos < ((int) y_pos + 1)) {
                    same_Y_range_true_num++;
                }
            }
            if (p.x_pos > (int) x_pos && p.x_pos < ((int) x_pos + 1)) {
                same_X_range_num++;

            }
            if (p.y_pos > (int) y_pos && p.y_pos < ((int) y_pos + 1)) {
                same_Y_range_num++;
            }


        }
        System.out.println("scale:" + scale + "\ntrue_num:" + true_num + "\nsame x range num:" + same_X_range_num + "\nsame y range num:" + same_Y_range_num + "\nsame x range true num:" + same_X_range_true_num + "\nsame y range true num:" + same_X_range_true_num);
        if (true_num == 0) {
            return 0;
        }
        if (same_X_range_num == 0 || same_Y_range_num == 0) {
            result = 1 / 2;
        } else {
            p_x_true = (float) same_X_range_true_num / true_num;
            p_y_true = (float) same_Y_range_true_num / true_num;
            p_true = (float) true_num / scale;
            p_x = (float) same_X_range_num / scale;
            p_y = (float) same_Y_range_num / scale;
            result = (p_x_true * p_y_true * p_true) / (p_x * p_y);
        }
        System.out.println(p_x_true);
        System.out.println(p_y_true);
        System.out.println(p_true);
        System.out.println(p_x);
        System.out.println(p_x);

        System.out.println("result:" + result);
        return result;
    }

    private static List createPutBackSamplingList(List list, int n) {
        List listNew = new ArrayList();
        if (list.size() <= n) {
            return list;
        } else {
            while (listNew.size() < n) {
                int random = (int) (Math.random() * list.size());
                listNew.add(list.get(random));
            }
            return listNew;
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
                    listNew.add(list.get(random));
                }
            }
            return listNew;
        }
    }

}
