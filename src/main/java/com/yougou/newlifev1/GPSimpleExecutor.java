package com.yougou.newlifev1;

import java.sql.*;

public class GPSimpleExecutor implements GPExecutor {

    private static final String url = "jdbc:mysql://localhost:3306/gupao?useUnicode=true&" + "characterEncoding=UTF8";
    private static final String username = "root";
    private static final String password = "123456";

    @Override
    public <T> T query(String statement, String parameter) {
        Connection conn = null;
        ResultSet rs = null;
        test test = null;
        try {

            // 1.加载mysql驱动程序
            Class.forName("com.mysql.jdbc.Driver");
            // 2.获取连接对象
            conn = DriverManager.getConnection(url, username, password);

            PreparedStatement preStatement = conn.prepareStatement(String.format(statement, parameter));
            rs = preStatement.executeQuery();
            // 4.遍历结果集
            while (rs.next()) {
                test = new test();
                test.setId(rs.getInt(1));
                test.setNums(rs.getInt(2));
                test.setName(rs.getString(3));
            }
        }catch (Exception e){

        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return (T) test;
    }
}
