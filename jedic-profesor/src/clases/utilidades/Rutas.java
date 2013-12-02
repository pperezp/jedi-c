/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases.utilidades;

import java.io.File;

/**
 *
 * @author pato
 */
public class Rutas {
    public static final String CARPETA_CONFIGURACIONES = "config";
    public static final String CARPETA_IDIOMAS = CARPETA_CONFIGURACIONES+File.separator+"idioma"+File.separator;
    public static final String ARCHIVO_IDIOMA = "idioma.xml";
    public static final String ARCHIVO_CONFIGURACIONES = CARPETA_CONFIGURACIONES+File.separator+"config.xml";
    
    public static final String RUTA_CONFIG_BD = CARPETA_CONFIGURACIONES+File.separator+"conexion.xml";
    public static final String RUTA_ARCHIVO_TEMP = "temp"+File.separator+"temp.temp";
    public static final String ICONO_ARRIBA = "/imagenes/iconoArriba.png";
    
}
