package com.yougou.rmi;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Server {

    public static void main(String[] args) {
        try {
            HelloService helloService = new HelloService();
            LocateRegistry.createRegistry(1099);
            Naming.rebind("rmi://127.0.0.1/hello", helloService);
            System.out.println("服务启动成功");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
