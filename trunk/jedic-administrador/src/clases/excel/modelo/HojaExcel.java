/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases.excel.modelo;

/**
 *
 * @author Patricio Pérez Pinto
 * 
 */

public class HojaExcel {
    private String nombre;
    private String[][] contenido;

    /**
     * 
     * @param nombre Es el nombre de la hoja del archivo xls, que no es lo mismo que el nombre del archivo xls.
     * Las hojas son las que aparecen en la parte inferior de la pantalla de excel, con el nombre "Hoja 1" por defecto
     * @param contenido Es una matriz de String con el contenido del archivo excel
     */
    public HojaExcel(String nombre, String[][] contenido) {
        this.nombre = nombre;
        this.contenido = contenido;
    }

    /**
     * 
     * @return Una matriz con el contenido de esta hoja 
     */
    public String[][] getContenido() {
        return contenido;
    }

    /**
     * 
     * @param contenido Es una matriz de String con el contenido del archivo excel
     */
    public void setContenido(String[][] contenido) {
        this.contenido = contenido;
    }

    /**
     * 
     * @return El nombre de esta hoja de Excel 
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * 
     * @param nombre Nombre de la hoja de Excel 
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
}
