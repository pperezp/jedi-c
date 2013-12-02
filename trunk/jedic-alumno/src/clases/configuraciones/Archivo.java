/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases.configuraciones;

import clases.generator.Ejecutar;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fabiola
 */
public class Archivo {

    private static File archivo;

    /**
     *
     * @param nombreArchivo
     * @param texto
     * @param sobreEscribir
     * @param archivoSoloLectura
     * @throws IOException
     */
    public static void escribirEnAchivo(String nombreArchivo, String texto, boolean sobreEscribir, boolean archivoSoloLectura){
        archivo = new File(nombreArchivo);
        try {
            archivo.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(Archivo.class.getName()).log(Level.SEVERE, null, ex);
        }
        PrintWriter archivoWriter = null;
        try {
            archivoWriter = new PrintWriter(new FileWriter(archivo, !sobreEscribir));
        } catch (IOException ex) {
            Logger.getLogger(Archivo.class.getName()).log(Level.SEVERE, null, ex);
        }
        archivoWriter.println(texto);
        if (archivoWriter != null) {
            archivoWriter.close();
        }
        if (archivoSoloLectura) {
            archivo.setReadOnly();
        }
    }

    public static void escribirEnAchivo(String nombreArchivo, String texto, boolean sobreEscribir, boolean archivoSoloLectura, boolean isOculto){
        archivo = new File(nombreArchivo);
        try {
            archivo.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(Archivo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        PrintWriter archivoWriter = null;
        try {
            archivoWriter = new PrintWriter(new FileWriter(archivo, !sobreEscribir));
        } catch (IOException ex) {
            Logger.getLogger(Archivo.class.getName()).log(Level.SEVERE, null, ex);
        }
        archivoWriter.println(texto);
        if (archivoWriter != null) {
            archivoWriter.close();
        }
        if(isOculto){
            Ejecutar.ejecutarComando("attrib +s +h \""+nombreArchivo+"\"");
        }
//        if (archivoSoloLectura) {
//            archivo.setReadOnly();
//        }
    }
    
    /**
     *
     * @param ruta
     * @return
     */
    public static boolean existeArchivo(String ruta) {
        archivo = new File(ruta);
        return archivo.isFile();
    }

    /**
     *
     * @param ruta
     * @return
     */
    public static long getFileSize(String ruta) {
        archivo = new File(ruta);
        if (archivo.isFile()) {
            return archivo.length();
        }
        return 0;
    }

    /**
     * deve permanecer en la mismas ruta del jar
     * @param nombreArchivo
     * @return
     * @throws IOException
     */
    public static String leerArchivo(String nombreArchivo) throws IOException {
        String texto = "";
        FileInputStream fis = null;
        BufferedReader reader = null;
        try {
            fis = new FileInputStream(nombreArchivo);
            reader = new BufferedReader(new InputStreamReader(fis));
            String linea;
            while ((linea = reader.readLine()) != null) {
                texto += linea + "\n";
            }
            return texto;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            fis.close();
            reader.close();
        }
        return texto;
    }
}
