/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.modelo.respuestas;

import java.io.Serializable;
import chat.modelo.Sala;
import chat.modelo.peticiones.PeticionCerrarSala;

/**
 *
 * @author Patricio Pérez Pinto
 */
public class EliminarSala extends Sala implements Serializable{

    public EliminarSala(PeticionCerrarSala pcs) {
        super(pcs.getDueñoSala(), pcs.getNombreSala(), pcs.getPassword());
    }
    
}
