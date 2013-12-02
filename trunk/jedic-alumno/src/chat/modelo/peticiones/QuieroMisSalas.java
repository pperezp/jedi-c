/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.modelo.peticiones;

import java.io.Serializable;
import chat.modelo.Cliente;

/**
 *
 * @author Patricio Pérez Pinto
 */
public class QuieroMisSalas extends Cliente implements Serializable{

    public QuieroMisSalas(Cliente cliente) {
        super(cliente.getCodigo(), cliente.getNombre());
    }
    
}
