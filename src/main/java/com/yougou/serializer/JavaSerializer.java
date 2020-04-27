package com.yougou.serializer;

import java.io.*;

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
        ByteArrayInputStream byteArrayOutputStream = new ByteArrayInputStream(data);
        ObjectInputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectInputStream(byteArrayOutputStream);
            return (T)objectOutputStream.readObject();
        } catch (Exception e) {
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
        return null;
    }
}
