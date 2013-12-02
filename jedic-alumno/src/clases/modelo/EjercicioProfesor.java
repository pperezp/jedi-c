/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases.modelo;

/**
 *
 * @author Pato
 */
public class EjercicioProfesor {
    private String nombreEjercicio;
    private String codigo;
    private String descripcion;
    private String fechaPlazo;
    private String horaPlazo;
    private boolean visto;

    public EjercicioProfesor(String nombreEjercicio, String codigo, String descripcion, String fechaPlazo, String horaPlazo) {
        this.nombreEjercicio = nombreEjercicio;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.fechaPlazo = fechaPlazo;
        this.horaPlazo = horaPlazo;
    }

    public boolean isVisto() {
        return visto;
    }

    public void setVisto(boolean visto) {
        this.visto = visto;
    }
    
    

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFechaPlazo() {
        return fechaPlazo;
    }

    public void setFechaPlazo(String fechaPlazo) {
        this.fechaPlazo = fechaPlazo;
    }

    public String getHoraPlazo() {
        return horaPlazo;
    }

    public void setHoraPlazo(String horaPlazo) {
        this.horaPlazo = horaPlazo;
    }
    
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombreEjercicio() {
        return nombreEjercicio;
    }

    public void setNombreEjercicio(String nombreEjercicio) {
        this.nombreEjercicio = nombreEjercicio;
    }
    
//    @Override
//    public String toString(){
//        return this.getNombreEjercicio();
//    }

    @Override
    public String toString() {
        return "EjercicioProfesor{" + "nombreEjercicio=" + nombreEjercicio + ", codigo=" + codigo + ", descripcion=" + descripcion + ", fechaPlazo=" + fechaPlazo + ", horaPlazo=" + horaPlazo + '}';
    }
    
}
