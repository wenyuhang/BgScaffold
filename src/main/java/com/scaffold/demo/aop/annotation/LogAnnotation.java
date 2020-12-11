package com.scaffold.demo.aop.annotation;

import java.lang.annotation.*;

/**
 * author : WYH
 * e-mail : wenyuhang@qinjia001.com
 * date   : 2020/12/8 11:15
 * desc   :
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogAnnotation {
    /** 模块 */
    String title() default "";

    /** 功能 */
    String action() default "";
}
