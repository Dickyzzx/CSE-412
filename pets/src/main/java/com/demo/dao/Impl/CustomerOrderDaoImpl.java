package com.demo.dao.Impl;

import com.demo.dao.CustomerOrderDao;
import com.demo.entity.CustomerOrder;
import com.demo.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerOrderDaoImpl implements CustomerOrderDao {

    private Connection connection;

    @Override
    public List<CustomerOrder> selectByUid(String uid) {

        connection = JDBCUtil.getConnection();
        PreparedStatement preparedStatement = null;
        List<CustomerOrder> customerOrders = new ArrayList<CustomerOrder>();
        ResultSet resultSet = null;
        try {

            String sql = "select uid,pid,identifier,price from public.customer_order where uid = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, uid);
            initCustomerOrders(preparedStatement, customerOrders);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeResource(connection, preparedStatement);
        }

        return customerOrders;
    }

    @Override
    public List<CustomerOrder> selectAll() {
        connection = JDBCUtil.getConnection();
        PreparedStatement preparedStatement = null;
        List<CustomerOrder> customerOrders = new ArrayList<CustomerOrder>();
        ResultSet resultSet = null;
        try {

            String sql = "select uid,pid,identifier,price from public.customer_order";
            preparedStatement = connection.prepareStatement(sql);
            initCustomerOrders(preparedStatement, customerOrders);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeResource(connection, preparedStatement);
        }

        return customerOrders;
    }


    private void initCustomerOrders(PreparedStatement preparedStatement, List<CustomerOrder> customerOrders) throws SQLException {
        ResultSet resultSet;
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            CustomerOrder s = new CustomerOrder();
            s.setUid(resultSet.getString("uid"));
            s.setPid(resultSet.getString("pid"));
            s.setIdentifier(resultSet.getInt("identifier"));
            s.setPrice(resultSet.getBigDecimal("price"));
            customerOrders.add(s);
        }
    }
}
