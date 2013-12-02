/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases.modelo;

/**
 *
 * @author Fabiola
 */
public class MensajeInbox {
    private String asunto;
    private String cod_pro;
    private String mensaje;

    public MensajeInbox(String asunto, String cod_pro, String mensaje) {
        this.asunto = asunto;
        this.cod_pro = cod_pro;
        this.mensaje = mensaje;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getCod_pro() {
        return cod_pro;
    }

    public void setCod_pro(String cod_pro) {
        this.cod_pro = cod_pro;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    
}
