/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases.modelo;

/**
 *
 * @author Patricio Pérez Pinto
 */
public class Profesor {
    private String codigo;
    private String nombre;
    private String usuario;
    private String pass;
    private String codDir;
    private String email;
    private String estado;

    public Profesor(String codigo, String nombre, String usuario, String pass, String codDir, String email, String estado) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.usuario = usuario;
        this.pass = pass;
        this.codDir = codDir;
        this.email = email;
        this.estado = estado;
    }

    public Profesor(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }
    
    public Profesor(){
    }
    
    public String getCodDir() {
        return codDir;
    }

    public void setCodDir(String codDir) {
        this.codDir = codDir;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "[profesor=" + nombre + ", usuario=" + usuario + ']';
    }
    
    
}
