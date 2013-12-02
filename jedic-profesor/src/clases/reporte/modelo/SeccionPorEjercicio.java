/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases.reporte.modelo;

/**
 *
 * @author Patricio Pérez Pinto
 */
public class SeccionPorEjercicio {
    private String numSec;
    private String alumno;

    public SeccionPorEjercicio(String numSec, String alumno) {
        this.numSec = numSec;
        this.alumno = alumno;
    }

    public String getAlumno() {
        return alumno;
    }

    public void setAlumno(String alumno) {
        this.alumno = alumno;
    }

    public String getNumSec() {
        return numSec;
    }

    public void setNumSec(String numSec) {
        this.numSec = numSec;
    }

    
    
    
}
