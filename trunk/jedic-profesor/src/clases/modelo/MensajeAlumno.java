/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases.modelo;

/**
 *
 * @author Administrador
 */
public class MensajeAlumno extends MensajeInbox{
    private String codAlu;

    public MensajeAlumno(String codAlu, String asunto, String cod_pro, String mensaje) {
        super(asunto, cod_pro, mensaje);
        this.codAlu = codAlu;
        
    }

    public String getCodAlu() {
        return codAlu;
    }

    public void setCodAlu(String codAlu) {
        this.codAlu = codAlu;
    }
    
    
}
