/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases.modelo;

/**
 *
 * @author Pato Pérez
 */
public class EjercicioAlumno {
    private String codigo;
    private String codigoAlumno;
    private String alumno;
    private String seccion;
    private String ejercicio;
    private Atraso atraso;

    public EjercicioAlumno(String codigo, String codigoAlumno, String alumno, String seccion, String ejercicio, Atraso atraso) {
        this.codigo = codigo;
        this.codigoAlumno = codigoAlumno;
        this.alumno = alumno;
        this.seccion = seccion;
        this.ejercicio = ejercicio;
        this.atraso = atraso;
    }

    public String getAlumno() {
        return alumno;
    }

    public void setAlumno(String alumno) {
        this.alumno = alumno;
    }

    public Atraso getAtraso() {
        return atraso;
    }

    public void setAtraso(Atraso atraso) {
        this.atraso = atraso;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getEjercicio() {
        return ejercicio;
    }

    public void setEjercicio(String ejercicio) {
        this.ejercicio = ejercicio;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public String getCodigoAlumno() {
        return codigoAlumno;
    }

    public void setCodigoAlumno(String codigoAlumno) {
        this.codigoAlumno = codigoAlumno;
    }
    
    
    
}
