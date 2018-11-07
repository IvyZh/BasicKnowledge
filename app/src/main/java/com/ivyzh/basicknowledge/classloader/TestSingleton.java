package com.ivyzh.basicknowledge.classloader;

/**
 * Created by Ivy on 2018/11/7.
 */

public class TestSingleton {
    public static void main(String args[]) {
        Singleton singleton = Singleton.getSingleton();
        System.out.println("counter1=" + singleton.counter1);
        System.out.println("counter2=" + singleton.counter2);
    }
}