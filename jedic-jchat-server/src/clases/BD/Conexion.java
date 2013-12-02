/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases.BD;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrador
 */
public final class Conexion {

    /**
     * 
     */
    public Connection con;
    /**
     * 
     */
    public Statement sentencia;
    /**
     * 
     */
    public ResultSet rs;

    /**
     * Método para conectar a una base de datos MySQL
     *
     * @param datos un objeto del tipo DatosConexion (es una interface)
     * @throws ClassNotFoundException
     * @throws SQLException
     *
     * @see clases.BD.mysql.DatosConexion
     */
    public Conexion(DatosConexion datos) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://" + datos.getServer() + ":3306/" + datos.getBaseDeDatos() + "";
        con = DriverManager.getConnection(url, datos.getUser(), datos.getPass());
    }
    
    
}
