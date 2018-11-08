package com.ivyzh.basicknowledge.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Ivy on 2018/11/8.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface MethodAnnotation {
    String errorMsg() default "请检查网络";

    String okMsg() default "操作成功";
}
