package com.dyl.springaop;

public class MyPointCut {

    public void myBefore() {
        System.out.println("before");
    }

    public void myAfter() {
        System.out.println("after");
    }
}
