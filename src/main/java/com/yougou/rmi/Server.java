package com.yougou.rmi;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Server {

    public static void main(String[] args) {
        try {
            HelloService helloService = new HelloService(); // 已经发布了远程对象helloservice_stub
            LocateRegistry.createRegistry(1099); // 发布Resgister_stub
            Naming.rebind("rmi://127.0.0.1/hello", helloService); // 绑定，跟注册中心类似
            System.out.println("服务启动成功");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
