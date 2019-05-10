package com.springcloud.designPattern.singleton;

/**
 * Created by yantao.chen on 2018-12-07
 * 单例模式
 */
public class Singleton {
//    饿汉式
//    private final static Singleton singleton = new Singleton();
//
//    private Singleton(){
//
//    }
//
//    public static Singleton getInstance(){
//        return singleton;
//    }

//    懒汉式 双重检查
    private static volatile Singleton singleton;

    private Singleton() {}

    public static Singleton getInstance() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }
}
