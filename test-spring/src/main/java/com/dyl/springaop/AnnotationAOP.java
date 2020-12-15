package com.dyl.springaop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AnnotationAOP {

    @Pointcut("execution(* com.dyl.springaop.UserService.*(..))")
    public void myPointCut() {}

    @Before(value = "myPointCut()")
    public void mybefore() {
        System.out.println("annotation mybefore");
    }

    @After(value = "myPointCut()")
    public void after() {
        System.out.println("annotation myafter");
    }
}
