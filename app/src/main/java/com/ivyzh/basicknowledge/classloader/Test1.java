package com.ivyzh.basicknowledge.classloader;

/**
 * Created by Ivy on 2018/11/7.
 */

public class Test1 {
    public static void main(String args[]) {
        System.out.println(FinalTest.x);

    }
}

class FinalTest {
    public static /*final*/ int x = 6 / 3;

    static {
        System.out.println("FinalTest static block");
        FinalTest.x =3;
    }
}
