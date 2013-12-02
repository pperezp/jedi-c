/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases.modelo;

import java.util.Date;

/**
 *
 * @author Fabiola
 */
public class MensajeInbox{
    private String asunto;
    private String profesor;
    private String mensaje;
    private String codigo;
    private boolean leido;
    private Date fecha;

    public MensajeInbox(String codigo, String asunto, String profesor, String mensaje, Date fecha) {
        this.asunto = asunto;
        this.profesor = profesor;
        this.mensaje = mensaje;
        this.codigo = codigo;
        this.fecha = fecha;
    }

    

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String cod_pro) {
        this.profesor = cod_pro;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public boolean isLeido() {
        return leido;
    }

    public void setLeido(boolean leido) {
        this.leido = leido;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    @Override
    public String toString(){
        return this.asunto;
    }


}
