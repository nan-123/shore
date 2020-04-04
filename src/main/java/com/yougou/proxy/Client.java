package com.yougou.proxy;

public class Client {

    public static void main(String[] args) {
        Subject proxy = new JDKDynamicProxy(new SubjectImpl()).getProxy();
        proxy.soSomething();
    }
}
