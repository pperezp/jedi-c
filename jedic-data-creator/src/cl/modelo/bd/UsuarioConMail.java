/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.modelo.bd;

/**
 *
 * @author Pato Pérez
 */
public class UsuarioConMail extends Usuario {
    private String mail;

    public UsuarioConMail(String mail, String codigo, String nombreReal, String nombreUsuario) {
        super(codigo, nombreReal, nombreUsuario);
        this.mail = mail;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
    
    @Override
    public String toString() {
        return this.getNombreReal() + " ("+this.getNombreUsuario()+")";
    }
}
