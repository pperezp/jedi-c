/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases.modelo;

/**
 *
 * @author Pato
 */
public class Alumno {
    private String nombre;
    private String email;
    private String codigo;
    private Seccion seccion;

    public Alumno(String nombre, String email, String codigo, String codigoSeccion, String seccion) {
        this.nombre = nombre;
        this.email = email;
        this.codigo = codigo;
        this.seccion = new Seccion(seccion, codigoSeccion);
    }

    public Alumno(String nombre, String codigo) {
        this.nombre = nombre;
        this.codigo = codigo;
    }

    
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Seccion getSeccion() {
        return seccion;
    }

    public void setSeccion(Seccion seccion) {
        this.seccion = seccion;
    }
    
    @Override
    public String toString(){
        return nombre;
    }
}
