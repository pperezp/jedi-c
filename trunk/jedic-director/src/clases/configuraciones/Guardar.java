/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases.configuraciones;


import clases.mensajes.Mensajes;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Patricioz
 */
public class Guardar {

    private static String ruta;
    private static String[] extension;
    private static JFileChooser fileChooser;
    private static FileNameExtensionFilter[] filtros;
    private static File archivo;
    private static String rutaSinExtension;
    private static String ext;

    /**
     * @return the rutaSinExtension
     */
    public static String getRutaSinExtension() {
        return rutaSinExtension;
    }

    public static String getExtension() {
        return ext;
    }

    
    /**
     * @param aRuta the ruta to set
     */
    public static void setRuta(String aRuta) {
        ruta = aRuta;
    }

    /**
     *
     */
    public Guardar() {
        fileChooser = new JFileChooser();
    }

    private static void setExtension(String[] extensiones) {
        extension = new String[extensiones.length];
        extension = extensiones;
        escribirExtensiones();
    }

    private static void escribirExtensiones() {
        filtros = new FileNameExtensionFilter[extension.length];
        for (int i = 0; i < filtros.length; i++) {
            filtros[i] = new FileNameExtensionFilter("*." + extension[i], extension[i]);
        }
        agregarFiltros();
    }

    private static void agregarFiltros() {
        for (int i = 0; i < filtros.length; i++) {
            fileChooser.addChoosableFileFilter(filtros[i]);
        }
    }

    /**
     *
     * @param nombreDeArchivo
     * @param extensiones
     * Las extensiones son los Filtros con los cuales se basará
     * en guardar un archivo.<br>
     * Escriba los filtros de extensiones separados de una coma<br>
     * <p><b>Ejemplo:</b> exe,jpeg,bat</p>
     *
     * Si como parámetro le entregas null a extensiones, se podrán abrir todos los tipos,
     * en otras palabras no habrá filtros
     *
     *
     * @param textoDeBotonDeAprovacion
     * @param rutaDirectorioPorDefecto 
     * @return Retornará TRUE si el usuario guarda algun archivo y
     * Retornará FALSE si el usuario no guarda algún archivo u
     * ocurrio un error
     */
    public static boolean guardarComo(String nombreDeArchivo, String extensiones, String textoDeBotonDeAprovacion, String rutaDirectorioPorDefecto) {
        fileChooser = new JFileChooser();
        if (extensiones != null) {
            String[] ext = extensiones.split(",");
            sacarEspacios(ext);
            setExtension(ext);
        }

        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setSelectedFile(new File(nombreDeArchivo));
        fileChooser.setCurrentDirectory(new File(rutaDirectorioPorDefecto));
        
        return guardar(textoDeBotonDeAprovacion);
    }

    /**
     *
     * @return
     */
    public static String getRuta() {
        return ruta;
    }

    private static void sacarEspacios(String[] ext) {
        for (int i = 0; i < ext.length; i++) {
            ext[i] = ext[i].trim();
        }
    }

    private static boolean guardar(String textoDeBotonDeAprovacion) {
        int seleccion = fileChooser.showDialog(null, textoDeBotonDeAprovacion);
        try {
            
            if (seleccion == JFileChooser.APPROVE_OPTION) {
                archivo = fileChooser.getSelectedFile();
                setRuta(archivo.getPath() + fileChooser.getFileFilter().getDescription().substring(1));
                ext = fileChooser.getFileFilter().getDescription().substring(1);
                rutaSinExtension = archivo.getPath();
                if (new File(ruta).exists()) {//si el archivo existe, llamo a la misma funcion, recursivamente
                    Mensajes.mensajeWarning("Archivo ya Existente", "El archivo ya existe. Cambie el nombre del Archivo");
                    return guardar(textoDeBotonDeAprovacion);
                }
                return true;
            } else {
                return false;
            }
        }catch (NullPointerException e) {
            setRuta(archivo.getPath()+".pdf");
            rutaSinExtension = archivo.getPath();
            ext = "pdf";
            if (new File(ruta).exists()) {//si el archivo existe, llamo a la misma funcion, recursivamente
                Mensajes.mensajeWarning("Archivo ya Existente", "El archivo ya existe. Cambie el nombre del Archivo");
                return guardar(textoDeBotonDeAprovacion);
            }
            return true;
        }
    }

    private static boolean elegirUbicacion(String textoDeBotonDeAprovacion){
        int seleccion = fileChooser.showDialog(null, textoDeBotonDeAprovacion);
        try {
            if (seleccion == JFileChooser.APPROVE_OPTION) {
                archivo = fileChooser.getSelectedFile();
                setRuta(archivo.getPath());
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean abrirUbicacion(String textoDeBotonDeAprovacion){
        fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(javax.swing.JFileChooser.DIRECTORIES_ONLY);
//        fileChooser.setAcceptAllFileFilterUsed(false);
        return elegirUbicacion(textoDeBotonDeAprovacion);
    }
}
