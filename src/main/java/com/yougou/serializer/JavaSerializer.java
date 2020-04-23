package com.yougou.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class JavaSerializer implements ISerializer {

    @Override
    public <T> byte[] serializer(T obj) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = null;
        try {
             objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(obj);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (objectOutputStream != null){
                    objectOutputStream.close();
                }
                if (byteArrayOutputStream != null){byteArrayOutputStream.close();}
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new byte[0];
    }

    @Override
    public <T> T deSerializer(byte[] data, Class<T> clazz) {
        return null;
    }
}
