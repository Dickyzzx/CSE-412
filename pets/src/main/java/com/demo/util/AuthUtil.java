package com.demo.util;

import com.demo.entity.dto.UserMap;

public class AuthUtil {

    public static Boolean isLogin() {
        return (UserMap.getUid() == null || "".equals(UserMap.getUid()));
    }
}
