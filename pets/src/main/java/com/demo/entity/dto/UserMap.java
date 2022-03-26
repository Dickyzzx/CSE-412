package com.demo.entity.dto;

/**
 * auth Used to store login user information
 */
public class UserMap {

    private static String uid;
    private static String name;
    private static String password;

    public static String getUid() {
        return uid;
    }

    public static void setUid(String uid) {
        UserMap.uid = uid;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        UserMap.name = name;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        UserMap.password = password;
    }


}
