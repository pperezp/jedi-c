/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases.modelo;

/**
 *
 * @author Administrador
 */
public class Director {
    
    private String codigo;
    private String nombre;
    private String usuario;
    private String pass;
    private String sede;
    private String email;
    private String estado;
    private String codSede;

    /**
     * Constructor para construir un Director
     * @param codigo
     * @param nombre
     * @param usuario
     * @param pass
     * @param codSede código de la sede a la cual pertenece
     * @param sede 
     * @param email
     * @param estado vigente o no-vigente
     */
    public Director(String codigo, String nombre, String usuario, String pass, String codSede, String sede, String email, String estado) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.usuario = usuario;
        this.pass = pass;
        this.sede = sede;
        this.email = email;
        this.estado = estado;
        this.codSede = codSede;
    }

    public Director(){
    }
    /**
     * 
     * @return
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * 
     * @param codigo
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * 
     * @return
     */
    public String getSede() {
        return sede;
    }

    /**
     * 
     * @param codigoSede
     */
    public void setSede(String codigoSede) {
        this.sede = codigoSede;
    }

    /**
     * 
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     * 
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 
     * @return
     */
    public String getEstado() {
        return estado;
    }

    /**
     * 
     * @param estado
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * 
     * @return
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * 
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * 
     * @return
     */
    public String getPass() {
        return pass;
    }

    /**
     * 
     * @param pass
     */
    public void setPass(String pass) {
        this.pass = pass;
    }

    /**
     * 
     * @return
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * 
     * @param usuario
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * 
     * @return
     */
    public String getCodSede() {
        return codSede;
    }

    /**
     * 
     * @param codSede
     */
    public void setCodSede(String codSede) {
        this.codSede = codSede;
    }

    @Override
    public String toString() {
        return "[director=" + nombre + ", usuario=" + usuario + ", sede=" + sede +']' ;
    }
    
    
}
