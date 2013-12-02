/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.historial;

import cl.bd.Conexion;
import cl.bd.DatosConexion;
import cl.modelo.*;
import cl.principal.Aplicacion;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pato Pérez
 */
public class Historial {

    private static Conexion c;

    public static void conectar() {
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
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Aplicacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static List<RegistroHistorial> getHistorial(Object obj, String fechaInicio, String fechaFinal) throws ClassNotFoundException, SQLException {
        List<RegistroHistorial> rh = new ArrayList<>();
        if(obj instanceof List){
            List<Object>listaOb = (List)obj;
            List<RegistroHistorial> historial = new ArrayList<>();
            for(Object o : listaOb){
                List<RegistroHistorial> listaHistorial = getHistorial(o, fechaInicio,fechaFinal);
                for(RegistroHistorial registroHistorial: listaHistorial){
                    historial.add(registroHistorial);
                }
            }
            return historial;//hacer
        }else{
            String query = null;
            String nombreResponsable = null;
            if (obj instanceof Administrador) {
                Administrador ad = (Administrador) obj;
                query = "select administrador.nom_adm, historial.det_his, historial.fec_his from historial, administrador "
                        + "where fec_his between '" + fechaInicio + "' "
                        + "and '" + fechaFinal + "' and "
                        + "nom_tab = 'administrador' and "
                        + "cod_per = '" + ad.getCodigo() + "' and administrador.cod_adm = historial.cod_per "
                        + "order by fec_his desc;";
                nombreResponsable = "nom_adm";
            } else if (obj instanceof Director) {
                Director d = (Director) obj;
                query = "select director.nom_dir, historial.det_his, historial.fec_his from historial, director "
                        + "where historial.fec_his between '" + fechaInicio + "' "
                        + "and '" + fechaFinal + "' and "
                        + "historial.nom_tab = 'director' and "
                        + "historial.cod_per = '" + d.getCodigo() + "' and director.cod_dir = historial.cod_per "
                        + "order by fec_his desc;";
                nombreResponsable = "nom_dir";
            } else if (obj instanceof Profesor) {
                Profesor p = (Profesor) obj;
                query = "select profesor.nom_pro, historial.det_his, historial.fec_his from historial, profesor "
                        + "where historial.fec_his between '" + fechaInicio + "' "
                        + "and '" + fechaFinal + "' and "
                        + "historial.nom_tab = 'profesor' and "
                        + "historial.cod_per = '" + p.getCodigo() + "' and profesor.cod_pro = historial.cod_per "
                        + "order by fec_his desc;";
                nombreResponsable = "nom_pro";
            } else if (obj instanceof Alumno) {
                Alumno a = (Alumno) obj;
                query = "select alumno.nom_alu, historial.det_his, historial.fec_his from historial, alumno "
                        + "where historial.fec_his between '" + fechaInicio + "' "
                        + "and '" + fechaFinal + "' and "
                        + "historial.nom_tab = 'alumno' and "
                        + "historial.cod_per = '" + a.getCodigo() + "' and alumno.cod_alu = historial.cod_per "
                        + "order by fec_his desc;";
                nombreResponsable = "nom_alu";
            } else if (obj.toString().equalsIgnoreCase("Todos los administradores")) {
                query = "select administrador.nom_adm, historial.det_his, historial.fec_his from historial, administrador "
                        + "where fec_his between '" + fechaInicio + "' "
                        + "and '" + fechaFinal + "' and "
                        + "nom_tab = 'administrador' "
                        + "and administrador.cod_adm = historial.cod_per order by fec_his desc;";
                nombreResponsable = "nom_adm";
            } else if (obj.toString().equalsIgnoreCase("Historial JEDI-C administrador")) {
                query = "select historial.det_his, historial.fec_his from historial "
                        + "where fec_his between '" + fechaInicio + "' "
                        + "and '" + fechaFinal + "' and "
                        + "nom_tab = 'administrador' "
                        + "and historial.cod_per is null order by fec_his desc;";
                nombreResponsable = null;
            }else if (obj.toString().equalsIgnoreCase("Todos los directores")) {
                query = "select director.nom_dir, historial.det_his, historial.fec_his from historial, director "
                        + "where fec_his between '" + fechaInicio + "' "
                        + "and '" + fechaFinal + "' and "
                        + "nom_tab = 'director' "
                        + "and director.cod_dir = historial.cod_per order by fec_his desc;";
                nombreResponsable = "nom_dir";
            }else if (obj.toString().equalsIgnoreCase("Todos los docentes")) {
                query = "select profesor.nom_pro, historial.det_his, historial.fec_his from historial, profesor "
                        + "where fec_his between '" + fechaInicio + "' "
                        + "and '" + fechaFinal + "' and "
                        + "nom_tab = 'profesor' "
                        + "and profesor.cod_pro = historial.cod_per order by fec_his desc;";
                nombreResponsable = "nom_pro";
            }else if (obj.toString().equalsIgnoreCase("Todos los alumnos")) {
                query = "select alumno.nom_alu, historial.det_his, historial.fec_his from historial, alumno "
                        + "where fec_his between '" + fechaInicio + "' "
                        + "and '" + fechaFinal + "' and "
                        + "nom_tab = 'alumno' "
                        + "and alumno.cod_alu = historial.cod_per order by fec_his desc;";
                nombreResponsable = "nom_alu";
            }

            c.sentencia = c.con.createStatement();

            for (int i = 0; i < 20; i++) {
                System.out.println();
            }

            System.out.println(query);
            c.rs = c.sentencia.executeQuery(query);



            while (c.rs.next()) {
                rh.add(new RegistroHistorial((nombreResponsable != null?c.rs.getString(nombreResponsable):" - "), c.rs.getString("det_his"), c.rs.getTimestamp("fec_his")));
    //            System.out.print(c.rs.getString(nombreResponsable)+"\t\t\t");
    //            System.out.print(c.rs.getString("det_his")+"\t\t\t");
    //            System.out.println(formatoFechaLinda.format(c.rs.getTimestamp("fec_his")));
            }

            c.sentencia.close();
    //        c.desconectar();
            return rh;
        }
    }
}
