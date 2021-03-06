package com.ivyzh.basicknowledge.reflect;

/**
 * Created by Ivy on 2018/11/7.
 */

public class Person {
    //私有属性
    private String name = "Tom";
    //公有属性
    public int age = 18;

    boolean isMan = true;

    protected char sex;

    //默认构造方法
    Person() {
        System.out.println("调用了默认构造方法。。。");
    }

    //无参构造方法
    public Person(String name) {
        this.name = name;

        System.out.println("调用了公有、有一个参数构造方法执行了。。。");
    }


    //有多个参数的构造方法
    private Person(String name, int age) {
        this.name = name;
        this.age = age;
        System.out.println("调用了私有、有两个参数构造方法执行了。。。" + "姓名：" + name + "年龄：" + age);
    }

    //有多个参数的构造方法
    protected Person(String name, int age, boolean isMan) {
        this.name = name;
        this.age = age;
        System.out.println("调用了保护、有三个参数构造方法执行了。。。" + "姓名：" + name + "年龄：" + age + ",性别：" + isMan);
    }


    //私有方法
    private void say() {
        System.out.println("private say()...");
    }

    private void say(String msg) {
        System.out.println("private say()..." + msg);
    }

    //公有方法
    public void work() {
        System.out.println("public work()...");
    }

    public void work(String what) {
        System.out.println("public work()..." + what);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
