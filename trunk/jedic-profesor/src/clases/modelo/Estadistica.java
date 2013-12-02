/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases.modelo;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Pato
 */
public class Estadistica {
    private Alumno alumno;
    private Date primeraConexion;
    private Date ultimaConexion;
    private float duracionUltimaConexion;
    private float duracionTotal;
    private boolean poseeEstadistica;

    public Estadistica(Alumno alumno, Date primeraConexion, Date ultimaConexion, float duracionUltimaConexion, float duracionTotal) {
        this.alumno = alumno;
        this.primeraConexion = primeraConexion;
        this.ultimaConexion = ultimaConexion;
        this.duracionUltimaConexion = duracionUltimaConexion;
        this.duracionTotal = duracionTotal;
        poseeEstadistica = true;
    }

    public Estadistica(Alumno alumno) {
        this.alumno = alumno;
        poseeEstadistica = false;
    }

    public boolean isPoseeEstadistica() {
        return poseeEstadistica;
    }
    
    

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public String getDuracionTotal() {
        float d =  duracionTotal / 1000 / 60;
        return new DecimalFormat("#.##").format(d);
    }

    public void setDuracionTotal(long duracionTotal) {
        this.duracionTotal = duracionTotal;
    }

    public String  getDuracionUltimaConexion() {
        float d = duracionUltimaConexion / 1000 / 60;
        return new DecimalFormat("#.##").format(d);
    }

    public void setDuracionUltimaConexion(long duracionUltimaConexion) {
        this.duracionUltimaConexion = duracionUltimaConexion;
    }

    public Date getPrimeraConexion() {
        return primeraConexion;
    }

    public void setPrimeraConexion(Date primeraConexion) {
        this.primeraConexion = primeraConexion;
    }

    public Date getUltimaConexion() {
        return ultimaConexion;
    }

    public void setUltimaConexion(Date ultimaConexion) {
        this.ultimaConexion = ultimaConexion;
    }
    
    public String getUltimaConexionComoString(){
        DateFormat formato = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return formato.format(ultimaConexion);
    }
    
    public String getPrimeraConexionComoString(){
        DateFormat formato = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return formato.format(primeraConexion);
    }

    
}
