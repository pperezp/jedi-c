/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package clases.BD.mysql;

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
    public static Connection con;
    public static Statement sentencia;
    public static ResultSet rs;
    public static Statement sentenciaAux;
    public static ResultSet rsAux;
    
    public static void conectar(DatosConexion datos) throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://"+datos.getServer()+":3306/"+datos.getBaseDeDatos()+"";
        Conexion.con = DriverManager.getConnection(url, datos.getUser(), datos.getPass());
        
    }

    /**
     *
     * @param tabla
     * @param values
     * "'valor1','valor2'"
     * @return
     * @throws SQLException
     */
//    public static boolean insertar(String tabla, String values) throws SQLException{
//        sentencia = con.createStatement();
//        boolean ok = sentencia.execute("insert into " + tabla + " values (" + values + ")");
//        sentencia.close();
//        return ok;
//    }

    /**
     *
     * @param tabla
     * @param insertarEn
     * "rut, nombre, direccion"
     * @param values
     * "'valor1','valor2'"
     * @return
     * @throws SQLException
     */
//    public static boolean insertar(String tabla, String insertarEn, String values) throws SQLException{
//        sentencia = con.createStatement();
//        boolean ok = sentencia.execute("insert into " + tabla + "("+insertarEn+") values (" + values + ")");
//        sentencia.close();
//        return ok;
//    }

    /**
     *
     * @param queSeleccionar
     * "*" ó "rut, nombre"
     * @param tabla
     * @return
     */
//    public static ResultSet select(String queSeleccionar, String tabla) throws SQLException{
//        sentencia = con.createStatement();
//        String sel = "select "+queSeleccionar+" from "+tabla;
//        rs = sentencia.executeQuery(sel);
//        return rs;
//    }

    /**
     *
     * @param queSeleccionar
     * "*" ó "rut, nombre"
     * @param tabla
     * @return
//     */
//    public static ResultSet select(String queSeleccionar, String tabla, String where) throws SQLException{
//        sentencia = con.createStatement();
//        String sel = "select "+queSeleccionar+" from "+tabla+ " where "+where;
//        rs = sentencia.executeQuery(sel);
//        return rs;
//    }

    public static void insertarArchivo(File a) throws SQLException, FileNotFoundException{
        String sql = "INSERT INTO tabla(archivo) VALUES (?)";
        //Creamos una cadena para después prepararla
        PreparedStatement stmt = con.prepareStatement(sql);
        FileInputStream   fis = new FileInputStream(a);
        //Lo convertimos en un Stream
        stmt.setBinaryStream(1, fis, (int) a.length());
        //Asignamos el Stream al Statement
        stmt.execute();
    }

    public static File recuperarArchivo(String ruta) throws SQLException, FileNotFoundException, IOException {
        String sql = "SELECT * FROM tabla;";
        sentencia = con.createStatement();
        rs = sentencia.executeQuery(sql);
        Blob ar = null;
        while (rs.next()) {
            ar = rs.getBlob("archivo");
        }
        InputStream is = ar.getBinaryStream();
        File f = new File(ruta);
        OutputStream out = new FileOutputStream(f);
        byte buf[] = new byte[1024];
        int len;
        while ((len = is.read(buf)) > 0) {
            out.write(buf, 0, len);
        }
        out.close();
        is.close();
        return f;
    }
}
