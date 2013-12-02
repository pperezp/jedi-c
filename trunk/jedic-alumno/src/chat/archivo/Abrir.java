/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.archivo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Patricio Pérez Pinto
 */
public class Abrir {

    private static String ruta;
    private static String[] extension;
    private static JFileChooser fileChooser;
    private static FileNameExtensionFilter[] filtros;
    private static File archivo;
    private static BufferedReader reader;

    /**
     * Método para guardar las extensiones en la clase estática
     * @param extensiones Es un arreglo de extensiones
     */
    private static void setExtension(String[] extensiones) {
        extension = extensiones;
        escribirExtensiones();
    }

    /**
     * En este método, se crean los filtros ()arreglo del tipo "FileNameExtensionFilter" a partir de las extensiones
     */
    private static void escribirExtensiones() {
        filtros = new FileNameExtensionFilter[extension.length];
        for (int i = 0; i < filtros.length; i++) {
            filtros[i] = new FileNameExtensionFilter("*." + extension[i], extension[i]);
        }
        agregarFiltros();
    }

    /**
     * Método para agregar los filtros al JFileCbooser
     */
    private static void agregarFiltros() {
        for (int i = 0; i < filtros.length; i++) {
            fileChooser.addChoosableFileFilter(filtros[i]);
        }
    }

    /**
     *
     * @param extensiones
     * Las extensiones son los Filtros con los cuales se basará 
     * al abrir un archivo.<br>
     * Escriba los filtros de extensiones separados de una coma<br>
     * <p><b>Ejemplo:</b> exe,jpeg,bat</p>
     *
     * Si como parámetro le entregas null, se podrán abrir todos los tipos,
     * en otras palabras no habrá filtros
     *
     * @return Retornará TRUE si el usuario abre algun archivo y
     * Retornará FALSE si el usuario no abre algún archivo o
     * ocurrio un error
     */
    public static boolean abrir(String extensiones, String textoDeBotonDeAprovacion, String rutaDirectorioPorDefecto) {
        fileChooser = new JFileChooser();
        if (extensiones != null) {
            String[] ext = extensiones.split(",");
            sacarEspacios(ext);
            setExtension(ext);
        }

        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setCurrentDirectory(new File(rutaDirectorioPorDefecto));
        int seleccion = fileChooser.showDialog(null, textoDeBotonDeAprovacion);
        try {
            if (seleccion == JFileChooser.APPROVE_OPTION) {
                archivo = fileChooser.getSelectedFile();
                ruta = archivo.getPath();
                reader = new BufferedReader(new FileReader(archivo));
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * 
     * @return La ruta del archivo seleccionado por el usuario 
     */
    public static String getRuta() {
        return ruta;
    }

    /**
     * Método para sacar los espacios de las extensiones
     * @param ext Un arreglo de extensiones
     */
    private static void sacarEspacios(String[] ext) {
        for (int i = 0; i < ext.length; i++) {
            ext[i] = ext[i].trim();
        }
    }

    /**
     * Método para retornar el archivo abierto, como String
     * @return
     * Retornará NULL si hay algún Error o el archivo esta Vacío
     */
    public static String getArchivoComoString() {
        String archiv = "";
        try {
            reader = new BufferedReader(new FileReader(archivo));
            String linea = reader.readLine();
            while (linea != null) {
                archiv += linea + "\n";
                linea = reader.readLine();
            }

            if (archiv.equalsIgnoreCase("")) {
                return null;
            } else {
                return archiv;
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Abrir.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (IOException ex) {
            Logger.getLogger(Abrir.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
