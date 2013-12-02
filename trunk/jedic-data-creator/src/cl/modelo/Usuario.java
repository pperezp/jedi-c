/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.modelo;

/**
 *
 * @author Pato Pérez
 */
public class Usuario {
    private String nombre;
    private String nombreUsuario;
    private String pass;
    private String mail;

    public Usuario(String nombre, String nombreUsuario, String pass, String mail) {
        this.nombre = nombre;
        this.nombreUsuario = nombreUsuario;
        this.pass = pass;
        this.mail = mail;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        return "Usuario{" + "nombre=" + nombre + ", nombreUsuario=" + nombreUsuario + ", pass=" + pass + ", mail=" + mail + '}';
    }
    
    
}
