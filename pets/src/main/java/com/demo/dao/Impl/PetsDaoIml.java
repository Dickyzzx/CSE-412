package com.demo.dao.Impl;

import com.demo.common.DaoCommonImpl;
import com.demo.dao.PetsDao;
import com.demo.entity.Pets;
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

public class PetsDaoIml implements PetsDao {

    private Connection connection;

    public Boolean insertOne(Pets entity) {
        connection = JDBCUtil.getConnection();
        PreparedStatement preparedStatement = null;
        //status  1 means it is being sold 2 means it has been purchased
        try {
            //id, name, age, location, species, breed, price
            preparedStatement = connection.prepareStatement(SqlConstants.INSERT_PETS_SQL);
            //generate id automatically
            preparedStatement.setString(1, UUID.randomUUID().toString().replaceAll("-", ""));
            preparedStatement.setString(2, entity.getName());
            preparedStatement.setInt(3, entity.getAge());
            preparedStatement.setString(4, entity.getLocation());
            preparedStatement.setString(5, entity.getSpecies());
            preparedStatement.setString(6, entity.getBreed());
            preparedStatement.setBigDecimal(7, entity.getPrice());
            preparedStatement.setString(8, entity.getSellerUid());
            preparedStatement.setString(9, null);
            preparedStatement.setString(10, "1");
            return !preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            JDBCUtil.closeResource(connection, preparedStatement);
        }


    }

    public List<Pets> selectAll(String name) {
        connection = JDBCUtil.getConnection();
        PreparedStatement preparedStatement = null;
        List<Pets> pets = new ArrayList<Pets>();
        ResultSet resultSet = null;
        try {
            String sql;
            if (name != null && !"".equals(name)) {
                sql = "SELECT id,name,age,location,species,breed,price,seller_uid,buy_uid,status from public.pets WHERE name like ?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, "%" + name + "%");
            } else {
                sql = "SELECT id,name,age,location,species,breed,price,seller_uid,buy_uid,status from public.pets";
                preparedStatement = connection.prepareStatement(sql);
            }
            initListPets(preparedStatement, pets);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeResource(connection, preparedStatement);
        }
        return pets;
    }

    public List<Pets> selectPetsByUid(String uid) {
        connection = JDBCUtil.getConnection();
        PreparedStatement preparedStatement = null;
        List<Pets> pets = new ArrayList<Pets>();
        ResultSet resultSet = null;
        try {
            String sql = "SELECT id,name,age,location,species,breed,price,seller_uid,buy_uid,status from public.pets where seller_uid=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, UserMap.getUid());
            initListPets(preparedStatement, pets);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeResource(connection, preparedStatement);
        }
        return pets;
    }

    public Boolean deleteById(String id, String uid) {
        connection = JDBCUtil.getConnection();
        return DaoCommonImpl.deleteByCondition(connection, SqlConstants.DELETE_PETS_SQL, id, uid);
    }

    @Override
    public Boolean buyPets(String id, String uid) {
        connection = JDBCUtil.getConnection();
        String sql = "update public.pets set status = '2',buy_uid= ? where id=?";
        return DaoCommonImpl.buyProduct(connection, sql,  uid, id);
    }


    /**
     * init pets list
     *
     * @param preparedStatement
     * @param pets
     * @throws SQLException
     */
    private void initListPets(PreparedStatement preparedStatement, List<Pets> pets) throws SQLException {
        ResultSet resultSet;
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Pets p = new Pets();
            p.setId(resultSet.getString("id"));
            p.setSellerUid(resultSet.getString("seller_uid"));
            p.setBuyUid(resultSet.getString("buy_uid"));
            p.setAge(resultSet.getInt("age"));
            p.setLocation(resultSet.getString("location"));
            p.setBreed(resultSet.getString("breed"));
            p.setPrice(resultSet.getBigDecimal("price"));
            p.setSpecies(resultSet.getString("species"));
            p.setName(resultSet.getString("name"));
            p.setStatus(resultSet.getString("status"));
            pets.add(p);
        }
    }


}
