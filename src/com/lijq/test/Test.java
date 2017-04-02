package com.lijq.test;

import com.lijq.Generator.PointGenerator;
import com.lijq.algorithm.Bagging;
import com.lijq.data.Point;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lee&Qin on 2017/3/29.
 */
public class Test {
    static List<Point> myPoints = new ArrayList<>();

    //True range
    static float x_min = 22.5f;
    static float x_max = 44.5f;
    static float y_min = 66.5f;
    static float y_max = 88.5f;

    public static void main(String[] args) throws IOException {

        /**数据
         Scanner sc = new Scanner(new File("src/myData.txt"));
         while (sc.hasNextLine()) {
         Point temp = new Point(sc.nextFloat(), sc.nextFloat(), sc.nextBoolean());
         myPoints.add(temp);
         }
         System.out.println("数据读入成功！");
         **/

        //生成随机点
        PointGenerator mypg = new PointGenerator();
        myPoints = mypg.generatepoints(100000, x_min, x_max, y_min, y_max);

        //调用Bagging算法
        Bagging mybagging = new Bagging(9, myPoints);

        //测试单个点
        mybagging.testPoint();

        //测试多个点（从文件读入）


    }


}
