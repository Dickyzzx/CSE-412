package com.demo.util;

import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

public class InitTable {

    static {
        Connection connection;
        ScriptRunner scriptRunner = null;
        try {
            connection = JDBCUtil.getConnection();
            scriptRunner = new ScriptRunner(connection);
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("druid.properties");
            Properties pros = new Properties();
            pros.load(is);
            scriptRunner.runScript(new FileReader(new File(pros.getProperty("sqlpath"))));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (scriptRunner != null) {
                scriptRunner.closeConnection();
            }
        }
    }
}
