package com.demo.util;

public class SqlConstants {

    public static String INSERT_USER_SQL = "INSERT INTO public.\"User\"(uid, name, password, phone, email, zip_code) VALUES (?, ?, ?, ?, ?, ?);";
    public static String SELECT_COUNT_USER_BY_UID_SQL = "select Count(*) as count  from public.\"User\" where uid = ?";
    public static String LOGIN_SQL = "select uid,name,password  from public.\"User\" where uid = ? and password = ?";

    public static String INSERT_PETS_SQL = "INSERT INTO public.pets(id, name, age, location, species, breed, price, seller_uid, buy_uid, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    public static String DELETE_PETS_SQL = "DELETE FROM public.pets WHERE id = ? and  seller_uid = ?";


    public static String INSERT_PRODUCT_SQL = "INSERT INTO public.product(id, description, brand, price, seller_uid, buy_uid, status) VALUES (?, ?, ?, ?, ?, ?, ?);";
    public static String DELETE_PRODUCT_SQL = "DELETE FROM public.product WHERE id = ? and seller_uid = ?";

}
