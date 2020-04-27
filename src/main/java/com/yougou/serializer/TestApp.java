package com.yougou.serializer;

public class TestApp {
    public static void main(String[] args) {
        TesrUser tesrUser = new TesrUser();
        tesrUser.setId("11111");
        tesrUser.setName("asdsad");
        ISerializer serializer = new JavaSerializer();
        byte[] serializer1 = serializer.serializer(tesrUser);
        TesrUser tesrUser1 = serializer.deSerializer(serializer1, tesrUser.getClass());
        System.out.println(serializer1);
    }
}
