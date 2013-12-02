/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package clases.utilidades;

import clases.BD.mysql.Conexion;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrador
 */
public class Buscar {
    public static boolean isProfesor(String usuPro){
        return buscar("profesor","usu_pro",usuPro);
    }

    private static boolean buscar(String tabla, String campo, String valor){
        try {
            Conexion.sentencia = Conexion.con.createStatement();
            Conexion.rs = Conexion.sentencia.executeQuery("select * from "+tabla+" where "+campo+" = '"+valor+"'");
            if(Conexion.rs.next()){
                return true;
            }else{
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Buscar.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }finally{
            try {
                Conexion.sentencia.close();
            } catch (SQLException ex) {
                Logger.getLogger(Buscar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public static boolean isDirector(String usuDirector){
        return buscar("director","usu_dir",usuDirector);
    }

    public static boolean isAlumno(String usuAlu){
        return buscar("alumno","usu_alu",usuAlu);
    }
    
    public static int getNextContador(String tabla, String campo, String nombre){
        int cont = 0;
        try {
            
            Conexion.sentencia = Conexion.con.createStatement();
            Conexion.rs = Conexion.sentencia.executeQuery("select count(0) as cont from "+tabla+" where "+campo+" LIKE '%"+nombre+"%'");
            if(Conexion.rs.next()){
                cont = Conexion.rs.getInt("cont");
            }
            Conexion.sentencia.close();
        } catch (SQLException ex) {
            Logger.getLogger(Buscar.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            return cont+1;
        }
    }
}
