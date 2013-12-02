/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.modelo;

/**
 *
 * @author Pato Pérez
 */
public class Director extends UsuarioConMail{
    private String codigoSede;
    private String estado;

    public Director(String codigoSede, String estado, String mail, String codigo, String nombreReal, String nombreUsuario) {
        super(mail, codigo, nombreReal, nombreUsuario);
        this.codigoSede = codigoSede;
        this.estado = estado;
    }

    public String getCodigoSede() {
        return codigoSede;
    }

    public void setCodigoSede(String codigoSede) {
        this.codigoSede = codigoSede;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    
    
    
}
