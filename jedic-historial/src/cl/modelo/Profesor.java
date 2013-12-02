/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.modelo;

/**
 *
 * @author Pato Pérez
 */
public class Profesor extends UsuarioConMail{
    private String codigoDirector;
    private String estado;

    public Profesor(String codigoDirector, String estado, String mail, String codigo, String nombreReal, String nombreUsuario) {
        super(mail, codigo, nombreReal, nombreUsuario);
        this.codigoDirector = codigoDirector;
        this.estado = estado;
    }

    public String getCodigoDirector() {
        return codigoDirector;
    }

    public void setCodigoDirector(String codigoDirector) {
        this.codigoDirector = codigoDirector;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    
    
}
