/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.grafico.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Pato Pérez
 */
public class DatoGrafico {
    
    private String xLabel;
    private List<Ente> entes;
    

//    public DatoGrafico(int valorY, String xLabel) {
//        super(valorY);
//        this.xLabel = xLabel;
//        entes = new ArrayList<>();
//        
//    }
    public DatoGrafico(String xLabel) {
        this.xLabel = xLabel;
        entes = new ArrayList<>();
    }
    
    public String getxLabel() {
        return xLabel;
    }

    public void setxLabel(String xLabel) {
        this.xLabel = xLabel;
    }
    
    public boolean isNombre(String nombre){
        for (Ente ente : entes) {
            if (ente.getNombre().equalsIgnoreCase(nombre)) {
                return true;
            }
        }
        return false;
    }
    
    public void addNombre(String nombre){
        entes.add(new Ente(nombre));
    }
    
    public void contar(String nombre){
        for (Ente ente : entes) {
            if (ente.getNombre().equalsIgnoreCase(nombre)) {
                ente.contar();
                break;
            }
        }
    }

    public List<Ente> getEntes() {
        return entes;
    }
    
    
}
