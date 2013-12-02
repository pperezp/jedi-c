/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases.modelo.idioma;

import clases.utilidades.Rutas;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import clases.xml.dom.analizador.*;
import clases.xml.dom.analizador.modelo.*;

/**
 *
 * @author pato
 */
public class Idioma {
    public static List<String> palabras;
    public static File idiomaSeleccionado;
    
    public static void cargarConfiguracionDeIdioma() {
        try {
            palabras = new ArrayList();
            Tag idioma = DOM.procesarArchivoXMLDom(new File(Rutas.CARPETA_IDIOMAS+Rutas.ARCHIVO_IDIOMA));
            String rutaArIdioma = idioma.getValorDeAtributo("ruta");
            
            Idioma.cargarIdioma(new File(rutaArIdioma));
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            
        }
    }
    
    private static void cargarIdioma(File arIdioma) {
        try {
            idiomaSeleccionado = arIdioma;
            System.out.println("Cargando archivo de idioma: "+arIdioma.getPath());
            Tag raiz = DOM.procesarArchivoXMLDom(arIdioma);
            
            for(Tag t: raiz.getTagsHijos()){
                Idioma.palabras.add(getValue(t));
//                System.out.println("Palabra: "+getValue(t));
            }
        } catch (ParserConfigurationException | SAXException | IOException ex) {
        }
    }
    
    private static String getValue(Tag t){
        return t.getValorDeAtributo("value");
    }
}
