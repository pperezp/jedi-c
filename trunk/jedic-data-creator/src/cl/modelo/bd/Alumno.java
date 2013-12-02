/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.modelo.bd;

/**
 *
 * @author Pato Pérez
 */
public class Alumno extends UsuarioConMail{
    private Seccion seccion;

    public Alumno(Seccion seccion, String mail, String codigo, String nombreReal, String nombreUsuario) {
        super(mail, codigo, nombreReal, nombreUsuario);
        this.seccion = seccion;
    }

    public Seccion getSeccion() {
        return seccion;
    }

    public void setSeccion(Seccion seccion) {
        this.seccion = seccion;
    }

    @Override
    public String toString() {
        return this.getNombreReal() + " ("+this.getNombreUsuario()+")";
    }
    
    
}
