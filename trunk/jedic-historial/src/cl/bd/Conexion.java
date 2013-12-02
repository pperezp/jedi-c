/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.bd;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.*;

/**
 *
 * @author Administrador
 */
public class Conexion {
    public Connection con;
    public Statement sentencia;
    public ResultSet rs;
    private DatosConexion dc;
    
    public Conexion(DatosConexion datos){
        dc = datos;
    }
    
    public void conectar() throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://"+dc.getServer()+":3306/"+dc.getBaseDeDatos()+"";
        con = DriverManager.getConnection(url, dc.getUser(), dc.getPass());
    }
    
    public void desconectar() throws SQLException{
        con.close();
    }
}
