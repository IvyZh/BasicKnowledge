package com.ivyzh.basicknowledge.reflect;

/**
 * Created by Ivy on 2018/11/7.
 */

public class TestPerson {
    public static void main(String[] args) throws ClassNotFoundException {
        //1、通过对象调用 getClass() 方法来获取,通常应用在：比如你传过来一个 Object
        //  类型的对象，而我不知道你具体是什么类，用这种方法
        Person p1 = new Person();
        Class<? extends Person> clazz1 = p1.getClass();

        //2、直接通过 类名.class 的方式得到,该方法最为安全可靠，程序性能更高
        //  这说明任何一个类都有一个隐含的静态成员变量 class
        Class<Person> clazz2 = Person.class;

        //3、通过 Class 对象的 forName() 静态方法来获取，用的最多，
        //   但可能抛出 ClassNotFoundException 异常
        Class<?> clazz3 = Class.forName("com.ivyzh.basicknowledge.reflect.Person");
    }
}
