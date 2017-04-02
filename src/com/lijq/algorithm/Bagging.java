package com.lijq.algorithm;

import com.lijq.classifier.BayesClassifier;
import com.lijq.data.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Lee&Qin on 2017/3/29.
 */
public class Bagging {
    static List<Point> myPoints;
    static int classifierNum;
    static int samplingNum;
    static List<BayesClassifier> myBayesClassifiers = new ArrayList<>();

    public Bagging(int classifierNum, List<Point> myPoints) {
        this.classifierNum = classifierNum;
        this.myPoints = myPoints;
        samplingNum = (int) (myPoints.size() * 0.6);
        System.out.println(samplingNum);
        //训练分类器组
        int i = 0;
        while (i < classifierNum) {
            BayesClassifier temp = new BayesClassifier();
            temp.choosePoints(myPoints, samplingNum);
            myBayesClassifiers.add(temp);
            i++;
        }
        System.out.println(myBayesClassifiers.get(0).myPoints.size());
    }


    public void testPoint() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入x值：");
        float testPointX = scanner.nextFloat();
        System.out.print("请输入y值：");
        float testPointY = scanner.nextFloat();
        System.out.print("该点有" + 100 * doClassifier(testPointX, testPointY) + "%的概率为True.");

    }

    private static float doClassifier(float testPointX, float testPointY) {
        float p_true_aver = 0;
        for (BayesClassifier bc : myBayesClassifiers) {
            p_true_aver += bc.doClassifier(testPointX, testPointY);
        }
        p_true_aver /= myBayesClassifiers.size();
        return p_true_aver;
    }
}
