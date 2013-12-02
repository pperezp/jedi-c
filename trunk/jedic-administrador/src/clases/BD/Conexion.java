/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases.BD;

import java.sql.*;

/**
 *
 * @author Administrador
 */
public final class Conexion {

    /**
     * 
     */
    public static Connection con;
    /**
     * 
     */
    public static Statement sentencia;
    /**
     * 
     */
    public static ResultSet rs;

    /**
     * Método para conectar a una base de datos MySQL
     *
     * @param datos un objeto del tipo DatosConexion (es una interface)
     * @throws ClassNotFoundException
     * @throws SQLException
     *
     * @see clases.BD.mysql.DatosConexion
     */
    public static void conectar(DatosConexion datos) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://" + datos.getServer() + ":3306/" + datos.getBaseDeDatos() + "";
        Conexion.con = DriverManager.getConnection(url, datos.getUser(), datos.getPass());
    }
}
