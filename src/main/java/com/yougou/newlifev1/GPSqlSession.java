package com.yougou.newlifev1;

public class GPSqlSession {

    private GPConfiguration configuration;

    private GPExecutor executor;

    public GPSqlSession(GPConfiguration configuration, GPExecutor executor) {
        this.configuration = configuration;
        this.executor = executor;
    }

    /**
     *
     * @param clazz
     */
    public <T> T getMapper(Class<T> clazz){
       return configuration.getMapper(clazz, this);
    }

    /**
     *
     * @param statement sql
     * @param parameter 参数
     * @param <T>
     * @return
     */
    public <T> T selectOne(String statement, String parameter){
        return executor.query(statement, parameter);
    }
}
