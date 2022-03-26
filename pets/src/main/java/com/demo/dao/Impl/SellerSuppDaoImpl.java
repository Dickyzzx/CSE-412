package com.demo.dao.Impl;

import com.demo.dao.SellerSuppDao;
import com.demo.entity.SellerSupp;
import com.demo.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SellerSuppDaoImpl implements SellerSuppDao {

    private Connection connection;

    @Override
    public List<SellerSupp> selectByUid(String uid) {
        connection = JDBCUtil.getConnection();
        PreparedStatement preparedStatement = null;
        List<SellerSupp> sellerSupps = new ArrayList<SellerSupp>();
        try {

            String sql = "select uid,pid,identifier,price from public.seller_supp where uid = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, uid);
            initSellerSupps(preparedStatement, sellerSupps);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeResource(connection, preparedStatement);
        }

        return sellerSupps;

    }

    private void initSellerSupps(PreparedStatement preparedStatement, List<SellerSupp> sellerSupps) throws SQLException {
        ResultSet resultSet;
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            SellerSupp s = new SellerSupp();
            s.setUid(resultSet.getString("uid"));
            s.setPid(resultSet.getString("pid"));
            s.setIdentifier(resultSet.getInt("identifier"));
            s.setPrice(resultSet.getBigDecimal("price"));
            sellerSupps.add(s);
        }
    }

    @Override
    public List<SellerSupp> selectAll() {
        connection = JDBCUtil.getConnection();
        PreparedStatement preparedStatement = null;
        List<SellerSupp> sellerSupps = new ArrayList<SellerSupp>();
        try {

            String sql = "select uid,pid,identifier,price from public.seller_supp";
            preparedStatement = connection.prepareStatement(sql);
            initSellerSupps(preparedStatement, sellerSupps);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeResource(connection, preparedStatement);
        }

        return sellerSupps;
    }
}
