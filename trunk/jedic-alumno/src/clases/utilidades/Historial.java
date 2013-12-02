/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases.utilidades;


import clases.BD.mysql.Conexion;
import clases.generator.Alumno;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;
import xml.analizador.dom.JespXML;
import xml.analizador.dom.modelo.Tag;

/**
 *
 * @author Pato Pérez
 */
public class Historial {
    private static String nombreTabla;
    private static boolean isHabilitado;
    
    public static void setNombreTabla(String nombreTabla) {
        try {
            Historial.nombreTabla = nombreTabla;
            if(!new File(Rutas.ARCHIVO_CONFIGURACIONES).exists()){
                crearXML();
            }
            JespXML ar = new JespXML(Rutas.ARCHIVO_CONFIGURACIONES);
            Tag conf = ar.leerXML();
//            Tag conf = DOM.procesarArchivoXMLDom(new File(Rutas.ARCHIVO_CONFIGURACIONES));
            for(Tag t: conf.getTagsHijos()){
                if(t.getNombre().equalsIgnoreCase("historial")){
                    String valorDeAtributo = t.getValorDeAtributo("habilitado");
                    isHabilitado = Boolean.valueOf(valorDeAtributo);
                    break;
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(Historial.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Historial: "+(isHabilitado?"si":"no"));
    }
    
    private static void crearXML() {
        try {
            Tag conf = new Tag("config");
            Tag historial = new Tag("historial");
            historial.addAtributo("habilitado", "true");
            conf.addTagHijo(historial);
            
            JespXML ar = new JespXML(Rutas.ARCHIVO_CONFIGURACIONES);
            ar.escribirXML(conf);
//            DOM.crearArchivoXML(conf, new File(Rutas.ARCHIVO_CONFIGURACIONES));
        } catch (ParserConfigurationException | FileNotFoundException | TransformerException ex) {
            Logger.getLogger(Historial.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void addHistorial(String queHizo){
        if(isHabilitado){
            try {
                Conexion.sentencia = Conexion.con.createStatement();
                Conexion.sentencia.execute("insert into historial(nom_tab, cod_per, det_his, fec_his) "
                        + "values('"+nombreTabla+"','"+Alumno.CODIGO+"','"+queHizo+"',SYSDATE())");
                Conexion.sentencia.close();
            } catch (SQLException ex) {
                Logger.getLogger(Historial.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static void addHistorialGeneral(String queHizo){
        if(isHabilitado){
            try {
                Conexion.sentencia = Conexion.con.createStatement();
                Conexion.sentencia.execute("insert into historial(nom_tab, det_his, fec_his) "
                        + "values('"+nombreTabla+"','"+queHizo+"',SYSDATE())");
                Conexion.sentencia.close();
            } catch (SQLException ex) {
                Logger.getLogger(Historial.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    
}
