package com.yougou.serializer;

public interface ISerializer {
    <T> byte[] serializer(T obj);

    <T> T deSerializer(byte[] data, Class<T> clazz);
}
