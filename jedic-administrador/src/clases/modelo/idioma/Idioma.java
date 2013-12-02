/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases.modelo.idioma;

import clases.xml.dom.analizador.modelo.Tag;
import clases.xml.dom.analizador.DOM;
import clases.utilidades.Rutas;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author pato
 */
public class Idioma {

    /**
     * Lista de palabras de un idioma determinado
     */
    public static List<String> palabras;
    /**
     * archivo de idioma seleccionado. Es un archivo XML
     */
    public static File idiomaSeleccionado;

    /**
     * Método para cargar la configuracion de idioma ubicado en
     * cofig/idioma/idioma.xml
     */
    public static void cargarConfiguracionDeIdioma() {
        try {
            palabras = new ArrayList();
            Tag idioma = DOM.procesarArchivoXMLDom(new File(Rutas.CARPETA_IDIOMAS + Rutas.ARCHIVO_IDIOMA));
            String rutaArIdioma = idioma.getValorDeAtributo("ruta");

            Idioma.cargarIdioma(new File(rutaArIdioma));
        } catch ( ParserConfigurationException | SAXException | IOException ex) {
        }
    }

    private static void cargarIdioma(File archivoIdioma) {
        try {
            idiomaSeleccionado = archivoIdioma;
            System.out.println("Cargando archivo de idioma: " + archivoIdioma.getPath());
            Tag raiz = DOM.procesarArchivoXMLDom(archivoIdioma);

            for (Tag t : raiz.getTagsHijos()) {
                Idioma.palabras.add(getValue(t));
            }
        } catch (ParserConfigurationException | SAXException | IOException ex) {
        }
    }

    private static String getValue(Tag tag) {
        return tag.getValorDeAtributo("value");
    }
}
