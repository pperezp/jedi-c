/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Pato Pérez
 */
public class Historial {
    private List<RegistroHistorial> registros;
    
    public Historial(){
        registros = new ArrayList<>();
    }
    
    public void addRegistro(RegistroHistorial rh){
        registros.add(rh);
    }
    
    public List<RegistroHistorial> getRegistros(){
        return registros;
    }
}
