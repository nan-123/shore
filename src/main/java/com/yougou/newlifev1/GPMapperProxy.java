package com.yougou.newlifev1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class GPMapperProxy implements InvocationHandler {

    private GPSqlSession sqlSession;

    public GPMapperProxy(GPSqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    // 用来找到sql
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // method.getDeclaringClass() 方法所在的对象
        if (method.getDeclaringClass().getName().equals(GPConfiguration.TestMapperXml.namespace)){
            String sql = GPConfiguration.TestMapperXml.methodSqlMapper.get(method.getName());
            return sqlSession.selectOne(sql, String.valueOf(args[0]));
        }
        return method.invoke(this, args);
    }
}
