package com.demo.dao.Impl;

import com.demo.common.DaoCommonImpl;
import com.demo.dao.ProductDao;
import com.demo.entity.Product;
import com.demo.entity.dto.UserMap;
import com.demo.util.JDBCUtil;
import com.demo.util.SqlConstants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProductDaoImpl implements ProductDao {

    private Connection connection;

    @Override
    public List<Product> selectAll(String desc) {
        connection = JDBCUtil.getConnection();
        PreparedStatement preparedStatement = null;
        List<Product> products = new ArrayList<Product>();
        try {
            String sql;
            if (desc != null && !"".equals(desc)) {
                sql = "SELECT id, description, brand, price, seller_uid,buy_uid, status from public.product WHERE description like ?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, "%" + desc + "%");
            } else {
                sql = "SELECT id, description, brand, price, seller_uid,buy_uid, status from public.product";
                preparedStatement = connection.prepareStatement(sql);
            }
            initProducts(preparedStatement, products);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeResource(connection, preparedStatement);
        }
        return products;
    }

    @Override
    public List<Product> selectProductsByUid(String uid) {
        connection = JDBCUtil.getConnection();
        PreparedStatement preparedStatement = null;
        List<Product> products = new ArrayList<Product>();
        try {
            String sql = "SELECT id, description, brand, price, seller_uid,buy_uid, status from public.product where seller_uid = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, uid);
            initProducts(preparedStatement, products);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeResource(connection, preparedStatement);
        }
        return products;
    }

    private void initProducts(PreparedStatement preparedStatement, List<Product> products) throws SQLException {
        ResultSet resultSet;
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Product product = new Product();
            product.setId(resultSet.getString("id"));
            product.setDescription(resultSet.getString("description"));
            product.setBrand(resultSet.getString("brand"));
            product.setPrice(resultSet.getBigDecimal("price"));
            product.setSellerUid(resultSet.getString("seller_uid"));
            product.setBuyUid(resultSet.getString("buy_uid"));
            product.setStatus(resultSet.getString("status"));
            products.add(product);
        }
    }

    @Override
    public Boolean deleteById(String id, String uid) {
        connection = JDBCUtil.getConnection();
        return DaoCommonImpl.deleteByCondition(connection, SqlConstants.DELETE_PRODUCT_SQL, id, uid);
    }

    @Override
    public Boolean buyProduct(String id, String uid) {
        connection = JDBCUtil.getConnection();
        String sql = "update public.product set status = '2',buy_uid= ? where id=?";
        return DaoCommonImpl.buyProduct(connection, sql,  uid, id);
    }

    @Override
    public Boolean insertOne(Product entity) {
        connection = JDBCUtil.getConnection();
        PreparedStatement preparedStatement = null;
        //status  1 means it is being sold 2 means it has been purchased
        try {
            //id, desc, brand, price, uid, status
            preparedStatement = connection.prepareStatement(SqlConstants.INSERT_PRODUCT_SQL);
            preparedStatement.setString(1, UUID.randomUUID().toString().replaceAll("-", ""));
            preparedStatement.setString(2, entity.getDescription());
            preparedStatement.setString(3, entity.getBrand());
            preparedStatement.setBigDecimal(4, entity.getPrice());
            preparedStatement.setString(5, entity.getSellerUid());
            preparedStatement.setString(6, null);
            preparedStatement.setString(7, "1");
            return !preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            JDBCUtil.closeResource(connection, preparedStatement);
        }
    }
}
