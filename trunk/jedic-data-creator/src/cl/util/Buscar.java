/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.util;

import cl.bd.Conexion;
import cl.bd.DatosConexion;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrador
 */
public class Buscar {
    private static Conexion c;
    public static void conectar(){
        try {
            c = new Conexion(new DatosConexion() {

                    @Override
                    public String getServer() {
                       return "localhost";
                    }

                    @Override
                    public String getUser() {
                        return "root";
                    }

                    @Override
                    public String getPass() {
                        return "07091988";
                    }

                    @Override
                    public String getBaseDeDatos() {
                        return "jedic";
                    }
                });
            c.conectar();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Buscar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Buscar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private static boolean buscar(String tabla, String campo, String valor) {
        try {
//            System.out.println("");
            c.sentencia = c.con.createStatement();
//            System.out.println("select * from " + tabla + " where " + campo + " = '" + valor + "'");
            c.rs = c.sentencia.executeQuery("select * from " + tabla + " where " + campo + " = '" + valor + "'");
            if (c.rs.next()) {
//                System.out.println("true");
                return true;
            } else {
//                System.out.println("false");
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Buscar.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            try {
                c.sentencia.close();
            } catch (SQLException ex) {
                Logger.getLogger(Buscar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static boolean isProfesor(String usuPro){
        return buscar("profesor","usu_pro",usuPro);
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
            
            c.sentencia = c.con.createStatement();
            c.rs = c.sentencia.executeQuery("select count(0) as cont from "+tabla+" where "+campo+" LIKE '%"+nombre+"%'");
            if(c.rs.next()){
                cont = c.rs.getInt("cont");
            }
            c.sentencia.close();
        } catch (SQLException ex) {
            Logger.getLogger(Buscar.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            return cont+1;
        }
    }
}
