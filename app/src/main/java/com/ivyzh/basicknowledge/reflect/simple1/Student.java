package com.ivyzh.basicknowledge.reflect.simple1;

/**
 * Created by Ivy on 2018/11/8.
 */

public class Student {
    public static String country = "CH";//国籍,类变量

    public static int getSum(int num) {// 静态方法
        return num;
    }

    public int getSum(int[] num) {
        int sum = 0;
        for (int i = 0; i < num.length; i++) {
            sum += num[i];
        }
        return sum;
    }

    public String getSum(String[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]).append(" ");
        }
        return sb.toString();
    }
}
