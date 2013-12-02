/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases.modelo;

/**
 *
 * @author Administrador
 */
public class MensajeSeccion extends MensajeInbox{
    private Seccion s;

    public MensajeSeccion(Seccion s, String asunto, String cod_pro, String mensaje) {
        super(asunto, cod_pro, mensaje);
        this.s = s;
    }

    public Seccion getSeccion() {
        return s;
    }

    public void setSeccion(Seccion s) {
        this.s = s;
    }
    
    
}
