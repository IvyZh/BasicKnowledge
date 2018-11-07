package com.ivyzh.basicknowledge.classloader;

/**
 * Created by Ivy on 2018/11/7.
 */

public class Singleton {
    private static Singleton singleton = new Singleton();
    public static int counter1;
    public static int counter2 = 0;

    private Singleton() {
        counter1++;
        counter2++;
    }

    public static Singleton getSingleton() {
        return singleton;
    }
}
