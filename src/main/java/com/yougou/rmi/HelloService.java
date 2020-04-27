package com.yougou.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class HelloService extends UnicastRemoteObject implements IHelloService  {

    protected HelloService() throws RemoteException{
        super();
    }

    @Override
    public String sayHello(String msg)throws RemoteException {
        System.out.println("hello：" + msg);
        return "hello：" + msg;
    }


}
