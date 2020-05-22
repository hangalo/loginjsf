/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.estudo.loginjsf.utils;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author informatica
 */
public class Main {
      public static void main(String[] args) {
        // create a new connection from MySQLJDBCUtil
        try (Connection conn = MYSQLDBConnection.getConnection()) {
            
            // print out a message
            System.out.println(String.format("Connected to database %s "
                    + "successfully.", conn.getCatalog()));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
