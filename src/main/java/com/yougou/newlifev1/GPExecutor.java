package com.yougou.newlifev1;

public interface GPExecutor {
    <T> T query(String statement, String parameter);
}
