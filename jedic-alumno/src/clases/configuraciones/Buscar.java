/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package clases.configuraciones;

import clases.BD.mysql.Conexion;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrador
 */
public class Buscar {
    private static int DIRECTOR = 1;
    private static int PROFESOR = 2;
    private static int ALUMNO = 3;

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

    public static int getNextContadorProfesor(){
        return getContador(PROFESOR)+1;
    }

    public static int getNextContadorAlumno(){
        return getContador(ALUMNO)+1;
    }

    public static int getNextContadorDirector(){
        return getContador(DIRECTOR)+1;
    }

    private static int getContador(int cod_con) {
        try {
            Conexion.sentencia = Conexion.con.createStatement();
            Conexion.rs = Conexion.sentencia.executeQuery(
            "select con_con from contador where cod_con = "+cod_con+"");
            if(Conexion.rs.next()){
                return Conexion.rs.getInt("con_con");
            }else{
                return -1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Buscar.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }finally{
            try {
                Conexion.sentencia.close();
            } catch (SQLException ex) {
                Logger.getLogger(Buscar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
