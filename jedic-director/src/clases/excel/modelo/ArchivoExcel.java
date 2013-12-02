/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases.excel.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Patricio Pérez Pinto
 */
public class ArchivoExcel {
    private List<HojaExcel> hojas;
    private String ruta;

    /**
     * 
     * @param hojas Una lista de hojas de Excel, que se construyen con la clase HojaExcel
     * @param ruta La ruta del Archivo Excel que quieres guardar
     */
    public ArchivoExcel(List<HojaExcel> hojas, String ruta) {
        this.hojas = hojas;
        this.ruta = ruta;
    }
    
    /**
     * 
     * @return Obtienes todas las hojas del archivo xls
     */
    public List<HojaExcel> getHojas() {
        return hojas;
    }

    /**
     * 
     * @param hojas Una lista de hojas (HojaExcel)
     */
    public void setHojas(List<HojaExcel> hojas) {
        this.hojas = hojas;
    }
    
    /**
     * 
     * @return La ruta del archivo xls 
     */
    public String getRuta() {
        return ruta;
    }

    /**
     * 
     * @param ruta La ruta del archivo xls
     */
    public void setRuta(String ruta) {
        this.ruta = ruta;
    }
    
    public int getCantidadDeHojas(){
        return hojas.size();
    }
}
