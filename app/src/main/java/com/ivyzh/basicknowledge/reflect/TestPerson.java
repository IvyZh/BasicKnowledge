package com.ivyzh.basicknowledge.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Ivy on 2018/11/7.
 */

public class TestPerson {
    public static void main(String[] args) throws Exception {


        //#######################获取Class对象的三种方式#######################

        System.out.println("#######################获取Class对象的三种方式#######################");
        //1、通过对象调用 getClass() 方法来获取,通常应用在：比如你传过来一个 Object
        //  类型的对象，而我不知道你具体是什么类，用这种方法
        Person p1 = new Person();//调用了默认构造方法。。。
        Class<? extends Person> clazz1 = p1.getClass();

        //2、直接通过 类名.class 的方式得到,该方法最为安全可靠，程序性能更高
        //  这说明任何一个类都有一个隐含的静态成员变量 class
        Class<Person> clazz2 = Person.class;

        //3、通过 Class 对象的 forName() 静态方法来获取，用的最多，
        //   但可能抛出 ClassNotFoundException 异常
        Class<?> clazz3 = Class.forName("com.ivyzh.basicknowledge.reflect.Person");


        //#######################通过反射获取构造方法并使用#######################
        System.out.println("#######################通过反射获取构造方法并使用#######################");

        // 1.所有公有构造方法
        Constructor<?>[] constructors = clazz3.getConstructors();
        for (Constructor c : constructors) {
            System.out.println(c);//public com.ivyzh.basicknowledge.reflect.Person(java.lang.String)
        }
        System.out.println("---------------------");
        // 2.所有的构造方法(包括：私有、受保护、默认、公有)
        constructors = clazz3.getDeclaredConstructors();
        for (Constructor c : constructors) {
            System.out.println(c);
            /*
            protected com.ivyzh.basicknowledge.reflect.Person(java.lang.String,int,boolean)
            private com.ivyzh.basicknowledge.reflect.Person(java.lang.String,int)
            public com.ivyzh.basicknowledge.reflect.Person(java.lang.String)
            com.ivyzh.basicknowledge.reflect.Person()
            */
        }
        System.out.println("---------------------");
        // 3. 获取无参数构造器 - 默认修饰符
        Constructor<?> constructor = clazz3.getDeclaredConstructor(null);
        System.out.println(constructor);
        Object obj = constructor.newInstance();
        System.out.println(obj);//Person{name='Tom', age=18}
        System.out.println(obj instanceof Person);//true
        System.out.println("---------------------");
        // 4. 获取一个参数构造器 - public修饰符
        constructor = clazz3.getConstructor(String.class);
        obj = constructor.newInstance("Spring");
        System.out.println(obj);//Person{name='Spring', age=18}
        System.out.println("---------------------");
        // 5. 获取两个参数构造器 - private修饰符
        constructor = clazz3.getDeclaredConstructor(String.class, int.class);// 注意这里不能用Integer.class
        constructor.setAccessible(true);
        obj = constructor.newInstance("Summer", 22);
        System.out.println(obj);//Person{name='Summer', age=22}
        System.out.println("---------------------");
        // 6. 获取三个参数构造器 - protect修饰符
        constructor = clazz3.getDeclaredConstructor(String.class, int.class, boolean.class);// 注意这里不能用Integer.class
        constructor.setAccessible(true);
        obj = constructor.newInstance("Autumn", 24, false);
        System.out.println(obj);//Person{name='Autumn', age=24}


        //#######################获取成员变量并调用#######################
        System.out.println("#######################获取成员变量并调用#######################");

        // 1.获取所有公有的字段
        Field[] fields = clazz3.getFields();
        for (Field f : fields) {
            System.out.println(f);//public int com.ivyzh.basicknowledge.reflect.Person.age
        }
        System.out.println("---------------------");
        //2. 获取所有的字段(包括私有、受保护、默认的)
        fields = clazz3.getDeclaredFields();
        for (Field f : fields) {
            System.out.println(f);
           /* private java.lang.String com.ivyzh.basicknowledge.reflect.Person.name
            public int com.ivyzh.basicknowledge.reflect.Person.age
            boolean com.ivyzh.basicknowledge.reflect.Person.isMan
            protected char com.ivyzh.basicknowledge.reflect.Person.sex*/
        }
        System.out.println("---------------------");
        // 3.获取公有字段并调用
        Field age = clazz3.getField("age");
        System.out.println(age);//public int com.ivyzh.basicknowledge.reflect.Person.age
        constructor = clazz3.getDeclaredConstructor(null);
        obj = constructor.newInstance();
        Person p2 = (Person) obj;
        System.out.println("p2.age:" + p2.age);//p2.age:18
        age.set(p2, 22);
        System.out.println("change p2.age:" + p2.age);//change p2.age:22
        System.out.println("---------------------");
        // 4.获取私有字段并调用
        Field name = clazz3.getDeclaredField("name");
        name.setAccessible(true);
        name.set(p2, "Jane");
        System.out.println("p2." + p2);//p2.Person{name='Jane', age=22}

        //#######################获取成员方法并调用#######################
        System.out.println("#######################获取成员方法并调用#######################");

        //1.获取所有"公有方法"；（包含了父类的方法也包含Object类）
        Method[] methods = clazz3.getMethods();
        for (Method m : methods) {
            System.out.println(m);
            /*public java.lang.String com.ivyzh.basicknowledge.reflect.Person.toString()
            public void com.ivyzh.basicknowledge.reflect.Person.work()
            public void com.ivyzh.basicknowledge.reflect.Person.work(java.lang.String)
            public final void java.lang.Object.wait(long,int) throws java.lang.InterruptedException
            public final native void java.lang.Object.wait(long) throws java.lang.InterruptedException
            public final void java.lang.Object.wait() throws java.lang.InterruptedException
            public boolean java.lang.Object.equals(java.lang.Object)
            public native int java.lang.Object.hashCode()
            public final native java.lang.Class java.lang.Object.getClass()
            public final native void java.lang.Object.notify()
            public final native void java.lang.Object.notifyAll()*/
        }

        System.out.println("---------------------");
        //2.获取所有的成员方法，包括私有的(不包括继承的)
        methods = clazz3.getDeclaredMethods();
        for (Method m : methods) {
            System.out.println(m);
            /*public java.lang.String com.ivyzh.basicknowledge.reflect.Person.toString()
            private void com.ivyzh.basicknowledge.reflect.Person.say()
            private void com.ivyzh.basicknowledge.reflect.Person.say(java.lang.String)
            public void com.ivyzh.basicknowledge.reflect.Person.work()
            public void com.ivyzh.basicknowledge.reflect.Person.work(java.lang.String)*/
        }
        System.out.println("---------------------");

        //3.获取公有的work()方法
        Method work = clazz3.getMethod("work");
        work.invoke(p2); //public work()...
        //4.获取公有的 work(String what)方法
        work = clazz3.getMethod("work", String.class);
        work.invoke(p2, "学习");//public work()...学习

        //5.获取私有的say()方法
        Method say = clazz3.getDeclaredMethod("say");
        say.setAccessible(true);
        say.invoke(p2);//private say()...

        //5.获取私有的say(String msg)方法
        say = clazz3.getDeclaredMethod("say", String.class);
        say.setAccessible(true);
        say.invoke(p2, "唱歌");//private say()...唱歌
    }
}
