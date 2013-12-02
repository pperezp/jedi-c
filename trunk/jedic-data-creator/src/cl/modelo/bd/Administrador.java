/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.modelo.bd;

/**
 *
 * @author Pato Pérez
 */
public class Administrador extends Usuario{

    public Administrador(String codigo, String nombreReal, String nombreUsuario) {
        super(codigo, nombreReal, nombreUsuario);
    }
    
    
    @Override
    public String toString(){
        return super.getNombreReal();
    }
}
