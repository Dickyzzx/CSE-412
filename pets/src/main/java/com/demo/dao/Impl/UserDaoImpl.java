package com.demo.dao.Impl;

import com.demo.dao.UserDao;
import com.demo.entity.User;
import com.demo.util.JDBCUtil;
import com.demo.util.SqlConstants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private Connection connection;

    public Boolean insertOne(User entity) {
        connection = JDBCUtil.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SqlConstants.INSERT_USER_SQL);
            preparedStatement.setString(1, entity.getUid());
            preparedStatement.setString(2, entity.getName());
            preparedStatement.setString(3, "123456");
            preparedStatement.setString(4, entity.getPhone());
            preparedStatement.setString(5, entity.getEmail());
            preparedStatement.setString(6, entity.getZipCode());
            return !preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            JDBCUtil.closeResource(connection, preparedStatement);
        }
    }

    public long selectCountByUid(String uid) {
        connection = JDBCUtil.getConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(SqlConstants.SELECT_COUNT_USER_BY_UID_SQL);
            preparedStatement.setString(1, uid);
            ResultSet resultSet = preparedStatement.executeQuery();
            long total = 0;
            while (resultSet.next()) {
                total = resultSet.getLong("count");
            }
            return total;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            JDBCUtil.closeResource(connection, preparedStatement);
        }
    }

    public User selectUserByUidAndPassword(String uid, String password) {
        connection = JDBCUtil.getConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(SqlConstants.LOGIN_SQL);
            preparedStatement.setString(1, uid);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            User user = null;
            while (resultSet.next()) {
                user = new User();
                user.setUid(resultSet.getString("uid"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
            }
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            JDBCUtil.closeResource(connection, preparedStatement);
        }
    }

    @Override
    public Boolean editUser(User user) {
        connection = JDBCUtil.getConnection();
        PreparedStatement preparedStatement = null;

        try {
            String sql = "update public.\"User\" set phone=?, name=?, email=?, zip_code=? where uid =?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getPhone());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getZipCode());
            preparedStatement.setString(5, user.getUid());
            return preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        } finally {
            JDBCUtil.closeResource(connection, preparedStatement);
        }
    }

    @Override
    public List<User> selectAll(String name) {
        connection = JDBCUtil.getConnection();
        PreparedStatement preparedStatement = null;
        List<User> users = new ArrayList<User>();
        ResultSet resultSet = null;

        try {
            String sql;
            if (name != null && !"".equals(name)) {
                sql = "SELECT uid,name,phone,email,zip_code from public.\"User\" WHERE name like ?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, "%" + name + "%");
            } else {
                sql = "SELECT uid,name,phone,email,zip_code from public.\"User\"";
                preparedStatement = connection.prepareStatement(sql);
            }
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setUid(resultSet.getString("uid"));
                user.setName(resultSet.getString("name"));
                user.setZipCode(resultSet.getString("zip_code"));
                user.setPhone(resultSet.getString("phone"));
                user.setEmail(resultSet.getString("email"));
                users.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeResource(connection, preparedStatement);
        }
        return users;
    }
}
