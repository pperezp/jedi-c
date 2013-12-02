/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.modelo.peticiones;

import java.io.Serializable;
import chat.modelo.Cliente;
import chat.modelo.Sala;

/**
 *
 * @author Patricio Pérez Pinto
 */
public class PeticionEntrarSala extends Sala implements Serializable{
    private Cliente cliente;

    public PeticionEntrarSala(Sala salaSeleccionada, Cliente cliente, String pass) {
        super(salaSeleccionada.getDueñoSala(), salaSeleccionada.getNombreSala(), pass);
        this.cliente = new Cliente(cliente.getCodigo(), cliente.getNombre());
    }
    
    public String getNombreClienteQueQuiereEntrarASala(){
        return cliente.getNombre();
    }
}
