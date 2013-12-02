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
    private String codigo;
    private String nombreReal;
    private String nombreUsuario;

    public Usuario(String codigo, String nombreReal, String nombreUsuario) {
        this.codigo = codigo;
        this.nombreReal = nombreReal;
        this.nombreUsuario = nombreUsuario;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombreReal() {
        return nombreReal;
    }

    public void setNombreReal(String nombreReal) {
        this.nombreReal = nombreReal;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
    
    
    
}
