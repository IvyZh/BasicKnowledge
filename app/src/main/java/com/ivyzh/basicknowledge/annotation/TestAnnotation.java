package com.ivyzh.basicknowledge.annotation;

import android.os.SystemClock;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Random;

/**
 * Created by Ivy on 2018/11/8.
 */

@ClassAnnotation(id = 23, name = "Spring")
public class TestAnnotation {
    @FieldAnnotation(isMan = false)
    String person1 = "Jane";

    @FieldAnnotation(isMan = true)
    String person2 = "Jakie";

    @FieldAnnotation()
    String person3 = "Make";

    String person4 = "Rose";


    public static void main(String[] args) throws Exception {
        getClassAnnotation();

        getFieldAnnotation();

        getMethodAnnotation();
    }

    // 给方法进行注解
    private static void getMethodAnnotation() throws Exception {

        Method method = TestAnnotation.class.getDeclaredMethod("openCamera");
        MethodAnnotation annotation = method.getAnnotation(MethodAnnotation.class);
        if (annotation != null) {
            String errorMsg = annotation.errorMsg();
            String okMsg = annotation.okMsg();

            TestAnnotation testAnnotation = TestAnnotation.class.newInstance();

            boolean isOk = (boolean) method.invoke(testAnnotation, null);

            if (isOk)
                System.out.println("openCamera okMsg:" + okMsg);
            else {
                System.out.println("openCamera errorMsg:" + errorMsg);
            }
        } else {
            System.out.println("openCamera is Not Annotation.");
        }


    }

    //给属性进行注解
    private static void getFieldAnnotation() throws Exception {
        Field person1 = TestAnnotation.class.getDeclaredField("person1");
        Field person2 = TestAnnotation.class.getDeclaredField("person2");
        Field person3 = TestAnnotation.class.getDeclaredField("person3");
        Field person4 = TestAnnotation.class.getDeclaredField("person4");

        person1.setAccessible(true);
        person2.setAccessible(true);
        person3.setAccessible(true);
        person4.setAccessible(true);

        FieldAnnotation annotation1 = person1.getAnnotation(FieldAnnotation.class);
        FieldAnnotation annotation2 = person2.getAnnotation(FieldAnnotation.class);
        FieldAnnotation annotation3 = person3.getAnnotation(FieldAnnotation.class);
        FieldAnnotation annotation4 = person4.getAnnotation(FieldAnnotation.class);

        if (annotation1 != null) {
            boolean man = annotation1.isMan();
            System.out.println("p1 isMan:" + man);
        }
        if (annotation2 != null) {
            boolean man = annotation2.isMan();
            System.out.println("p2 isMan:" + man);
        }
        if (annotation3 != null) {
            boolean man = annotation3.isMan();
            System.out.println("p3 isMan:" + man);
        }
        if (annotation4 != null) {
            boolean man = annotation4.isMan();
            System.out.println("p4 isMan:" + man);
        } else {
            System.out.println("p4 is Not Annotation.");
        }

    }


    //给一个类型进行注解，比如类、接口、枚举
    private static void getClassAnnotation() {
        boolean isAnnotation = TestAnnotation.class.isAnnotationPresent(ClassAnnotation.class);
        if (isAnnotation) {
            ClassAnnotation annotation = TestAnnotation.class.getAnnotation(ClassAnnotation.class);
            int id = annotation.id();
            String name = annotation.name();
            System.out.println(name + "---" + id);//Spring---23
        }
    }

    @MethodAnnotation()
    public boolean openCamera() {
        System.out.println("打开相机操作...");
        return new Random().nextInt(100) % 2 == 0 ? true : false;
    }
}
