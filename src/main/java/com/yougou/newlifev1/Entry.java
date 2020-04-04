package com.yougou.newlifev1;

public class Entry {
    public static void main(String[] args) {
        GPSqlSession sqlSession = new GPSqlSession(new GPConfiguration(), new GPSimpleExecutor());
        TestMapper mapper = sqlSession.getMapper(TestMapper.class);
        mapper.selectByPrimaryKey(1);
    }
}
