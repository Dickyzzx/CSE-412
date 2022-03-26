package com.demo.common;

import com.demo.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * common dao
 */
public class DaoCommonImpl {


    public static Boolean deleteByCondition(Connection connection, String sql, String id, String uid) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, uid);
            return preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return true;
        } finally {
            JDBCUtil.closeResource(connection, preparedStatement);
        }
    }

    public static Boolean buyProduct(Connection connection, String sql,String uid, String id) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, uid);
            preparedStatement.setString(2, id);
            return preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        } finally {
            JDBCUtil.closeResource(connection, preparedStatement);
        }


    }
}
