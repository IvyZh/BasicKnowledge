package com.ivyzh.basicknowledge.classloader;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * Created by Ivy on 2018/11/7.
 */

public class ClassLoaderTree {
    public static void main(String[] args) {
        String arch = System.getProperty("sun.arch.data.model");
        System.out.println(arch + "-bit");

        ClassLoader loader = ClassLoaderTree.class.getClassLoader();
        while (loader != null) {
            System.out.println(loader.toString());
            loader = loader.getParent();
        }

    }

}
