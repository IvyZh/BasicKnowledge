package com.ivyzh.basicknowledge.reflect.simple1;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by Ivy on 2018/11/8.
 */

public class TestStudent {
    public static void main(String[] args) throws Exception {
        Class<?> clazz = Class.forName("com.ivyzh.basicknowledge.reflect.simple1.Student");

        //1. 使用反射调用静态方法
        Method method = clazz.getDeclaredMethod("getSum", int.class);
        Object sum = method.invoke(null, 20);
        System.out.println("sum:" + (int) sum);//sum:20

        //2.使用反射调用类变量

        Field country = clazz.getField("country");
        String name = country.getName();
        Object value = country.get(clazz);
        System.out.println("name:" + name + ",value:" + value);//name:country,value:CH


        // 3. 使用反射调用带数组参数的方法
        int[] arr = {1, 2, 3, 4, 5};
        method = clazz.getMethod("getSum", int[].class);
        Object obj = clazz.newInstance();
        sum = method.invoke(obj, arr);
        System.out.println("sum:" + (int) sum);//sum:15


        String[] arrStr = {"a", "b", "c", "d", "e"};
        method = clazz.getMethod("getSum", String[].class);
        //obj = method.invoke(obj, arrStr);//error:java.lang.IllegalArgumentException: wrong number of arguments
        //sum = method.invoke(obj, new String[]{"a", "b", "c", "d", "e"});//error:java.lang.IllegalArgumentException: wrong number of arguments
        sum = method.invoke(obj, new Object[]{new String[]{"a", "b", "c", "d", "e"}});//sum:a b c d e

        System.out.println("sum:" + sum);

        // 王道:调用方法的时候把实际参数统统作为Object数组的元素即可.
        // Method对象.invoke(方法底层所属对象,new Object[]{  所有实参   });
    }
}
