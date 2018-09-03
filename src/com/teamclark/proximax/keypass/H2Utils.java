/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamclark.proximax.keypass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jsalinga
 */
public class H2Utils {

    public boolean initDB(Connection sqlConnection) {
        boolean retVal = false;
        String sqlCmd = "CREATE TABLE IF NOT EXISTS "
                + "xpx_keepass (nem_address VARCHAR(400) PRIMARY KEY, "
                + "private_key VARCHAR(400), public_key VARCHAR(400),"
                + "nem_hash VARCHAR(400));";
        retVal = suWrite(sqlCmd, sqlConnection);
        return retVal;
    }

    public Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.
                    getConnection("jdbc:h2:~/keepass", "admin", "admin");
        } catch (SQLException ex) {
            Logger.getLogger(H2Utils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }

    public ResultSet suQuery(String sqlCmd, Connection sqlConnection) {
        ResultSet res = null;
        if (sqlConnection != null) {
            try {
                Statement statement = sqlConnection.createStatement(
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY);
                statement.setFetchSize(1024);
                res = statement.executeQuery(sqlCmd);
            } catch (SQLException ex) {
                System.out.println(sqlCmd.toUpperCase());
                Logger.getLogger(H2Utils.class.getName()).log(Level.INFO, null, ex);
            }
        }
        return res;
    }

    public boolean suWrite(String sqlCmd, Connection sqlConnection) {
        boolean retVal = false;
        if (sqlConnection != null) {
            try {
                try (Statement statement = sqlConnection.createStatement()) {
                    statement.executeUpdate(sqlCmd);
                }
                retVal = true;
            } catch (SQLException ex) {
                Logger.getLogger(H2Utils.class.getName()).log(Level.INFO, null, ex);
            }
        }
        return retVal;
    }
}
