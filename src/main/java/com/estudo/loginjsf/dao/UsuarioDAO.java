/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.estudo.loginjsf.dao;

import com.estudo.loginjsf.model.Usuario;
import com.estudo.loginjsf.utils.MYSQLDBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author informatica
 */
public class UsuarioDAO {

    String FINDUSER = "SELECT user_id, user_name, password, nome  FROM user u  WHERE user_name =? AND password = ?";

    public Usuario findUser(String user_name, String password) {

        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        Usuario user = new Usuario();

        try {
            conn = MYSQLDBConnection.getConnection();
            ps = conn.prepareStatement(FINDUSER);
            ps.setString(1, user_name);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (!rs.next()) {
                System.err.println("User not foung: " + user_name);
            }
            fillData(user, rs);
        } catch (SQLException ex) {
            System.err.println("Error:UserDAO:findUser: " + ex.getLocalizedMessage());
        } finally {
            MYSQLDBConnection.closeConnection(conn, ps, rs);
        }

        return user;

    }

    private void fillData(Usuario user, ResultSet rs) {
        if (rs != null) {

            try {
                user.setId(rs.getLong("user_id"));
                user.setLogin(rs.getString("user_name"));
                user.setSenha(rs.getString("password"));
                user.setNome(rs.getString("nome"));

            } catch (SQLException ex) {
                System.err.println("Error:UserDAO:fillData: " + ex.getLocalizedMessage());
            }

        } else {
            System.err.println("FillData: Resulset: Vuoto");
        }

    }

}
