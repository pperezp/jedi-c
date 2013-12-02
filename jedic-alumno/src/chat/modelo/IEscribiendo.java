/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.modelo;

/**
 *
 * @author Patricio Pérez Pinto
 */
public interface IEscribiendo {
    /**
     * Metodo que lo usará el CLIENTE que esta ESCRIBIENDO
     * @param cli 
     */
    public void setAquienEscribe(Cliente cliente);
    
    /**
     * Metodo que lo usara el SERVIDOR
     * @return 
     */
    public Cliente getClienteAquienEscribe();
    
    /*Metodo que lo usara el cliente al cual
      tendre que poner, CLiente escribiendo...*/
    public Cliente getClienteQueEstaEscribiendo();
}
