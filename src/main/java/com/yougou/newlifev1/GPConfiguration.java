package com.yougou.newlifev1;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class GPConfiguration {
    public <T> T getMapper(Class<T> clazz, GPSqlSession sqlSession) {
        return (T)Proxy.newProxyInstance(this.getClass().getClassLoader(),
                new Class[]{clazz},
                new GPMapperProxy(sqlSession));
    }

    // 这里解决了xml解析
    static class TestMapperXml {
        public static final String namespace = "com.yougou.newlifev1.TestMapper";

        public static final Map<String, String> methodSqlMapper = new HashMap<>();

        static {
            methodSqlMapper.put("selectByPrimaryKey", "select * from test where id =%d");
        }

    }
}
