/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.estadistica;

import cl.modelo.RegistroHistorial;
import cl.grafico.modelo.DatoGrafico;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Pato Pérez
 */
public class Estadistica {
    private List<DatoGrafico> datos; 
    private List<RegistroHistorial> historial;

    public Estadistica(List<RegistroHistorial> historial) {
        this.historial = historial;
        datos = new ArrayList<>();
    }

    /*por meses*/
    public List<DatoGrafico> getDatos(String filtro) {
        Date fec;
        Calendar calendar;
        
        DatoGrafico enero = new DatoGrafico("Ene");
        DatoGrafico febrero = new DatoGrafico("Feb");
        DatoGrafico marzo = new DatoGrafico("Mar");
        DatoGrafico abril = new DatoGrafico("Abr");
        DatoGrafico mayo = new DatoGrafico("May");
        DatoGrafico junio = new DatoGrafico("Jun");
        DatoGrafico julio = new DatoGrafico("Jul");
        DatoGrafico agosto = new DatoGrafico("Ago");
        DatoGrafico septiembre = new DatoGrafico("Sep");
        DatoGrafico octubre = new DatoGrafico("Oct");
        DatoGrafico noviembre = new DatoGrafico("Nov");
        DatoGrafico diciembre = new DatoGrafico("Dic");
        
        for(RegistroHistorial rh: historial){
            if(rh.getAccion().contains(filtro)){
                fec = rh.getFechaAsDate();
                calendar = Calendar.getInstance();
                calendar.setTime(fec);
                
                switch(calendar.get(Calendar.MONTH)){
                    case Calendar.JANUARY:{
                        contar(enero, rh);
                        break;
                    }case Calendar.FEBRUARY:{
                        contar(febrero, rh);
                        break;
                    }case Calendar.MARCH:{
                        contar(marzo, rh);
                        break;
                    }case Calendar.APRIL:{
                        contar(abril, rh);
                        break;
                    }case Calendar.MAY:{
                        contar(mayo, rh);
                        break;
                    }case Calendar.JUNE:{
                        contar(junio, rh);
                        break;
                    }case Calendar.JULY:{
                        contar(julio, rh);
                        break;
                    }case Calendar.AUGUST:{
                        contar(agosto, rh);
                        break;
                    }case Calendar.SEPTEMBER:{
                        contar(septiembre, rh);
                        break;
                    }case Calendar.OCTOBER:{
                        contar(octubre, rh);
                        break;
                    }case Calendar.NOVEMBER:{
                        contar(noviembre, rh);
                        break;
                    }case Calendar.DECEMBER:{
                        contar(diciembre, rh);
                        break;
                    }
                }
            }
        }
        
        datos.add(enero);
        datos.add(febrero);
        datos.add(marzo);
        datos.add(abril);
        datos.add(mayo);
        datos.add(junio);
        datos.add(julio);
        datos.add(agosto);
        datos.add(septiembre);
        datos.add(octubre);
        datos.add(noviembre);
        datos.add(diciembre);
        return datos;
    }
    public List<DatoGrafico> getDatos2(String filtro) {
        Date fec;
        Calendar calendar;
        for (RegistroHistorial rh : historial) {
            if (rh.getAccion().contains(filtro)) {
                fec = rh.getFechaAsDate();
                calendar = Calendar.getInstance();
                calendar.setTime(fec);

                String xlabel;
                switch (calendar.get(Calendar.MONTH)) {
                    case Calendar.JANUARY: {
                        xlabel = "Ene " + calendar.get(Calendar.YEAR);
                        if (!isDato(xlabel)) {
                            datos.add(new DatoGrafico(xlabel));
                        }
                        contar(getDato(xlabel), rh);
                        break;
                    }
                    case Calendar.FEBRUARY: {
                        xlabel = "Feb " + calendar.get(Calendar.YEAR);
                        if (!isDato(xlabel)) {
                            datos.add(new DatoGrafico(xlabel));
                        }
                        contar(getDato(xlabel), rh);
                        break;
                    }
                    case Calendar.MARCH: {
                        xlabel = "Mar " + calendar.get(Calendar.YEAR);
                        if (!isDato(xlabel)) {
                            datos.add(new DatoGrafico(xlabel));
                        }
                        contar(getDato(xlabel), rh);;
                        break;
                    }
                    case Calendar.APRIL: {
                        xlabel = "Abr " + calendar.get(Calendar.YEAR);
                        if (!isDato(xlabel)) {
                            datos.add(new DatoGrafico(xlabel));
                        }
                        contar(getDato(xlabel), rh);
                        break;
                    }
                    case Calendar.MAY: {
                        xlabel = "May " + calendar.get(Calendar.YEAR);
                        if (!isDato(xlabel)) {
                            datos.add(new DatoGrafico(xlabel));
                        }
                        contar(getDato(xlabel), rh);
                        break;
                    }
                    case Calendar.JUNE: {
                        xlabel = "Jun " + calendar.get(Calendar.YEAR);
                        if (!isDato(xlabel)) {
                            datos.add(new DatoGrafico(xlabel));
                        }
                        contar(getDato(xlabel), rh);
                        break;
                    }
                    case Calendar.JULY: {
                        xlabel = "Jul " + calendar.get(Calendar.YEAR);
                        if (!isDato(xlabel)) {
                            datos.add(new DatoGrafico(xlabel));
                        }
                        contar(getDato(xlabel), rh);
                        break;
                    }
                    case Calendar.AUGUST: {
                        xlabel = "Ago " + calendar.get(Calendar.YEAR);
                        if (!isDato(xlabel)) {
                            datos.add(new DatoGrafico(xlabel));
                        }
                        contar(getDato(xlabel), rh);
                        break;
                    }
                    case Calendar.SEPTEMBER: {
                        xlabel = "Sep " + calendar.get(Calendar.YEAR);
                        if (!isDato(xlabel)) {
                            datos.add(new DatoGrafico(xlabel));
                        }
                        contar(getDato(xlabel), rh);
                        break;
                    }
                    case Calendar.OCTOBER: {
                        xlabel = "Oct " + calendar.get(Calendar.YEAR);
                        if (!isDato(xlabel)) {
                            datos.add(new DatoGrafico(xlabel));
                        }
                        contar(getDato(xlabel), rh);
                        break;
                    }
                    case Calendar.NOVEMBER: {
                        xlabel = "Nov " + calendar.get(Calendar.YEAR);
                        if (!isDato(xlabel)) {
                            datos.add(new DatoGrafico(xlabel));
                        }
                        contar(getDato(xlabel), rh);
                        break;
                    }
                    case Calendar.DECEMBER: {
                        xlabel = "Dic " + calendar.get(Calendar.YEAR);
                        if (!isDato(xlabel)) {
                            datos.add(new DatoGrafico(xlabel));
                        }
                        contar(getDato(xlabel), rh);
                        break;
                    }
                }
            }
        }
        //como el poto
        List<DatoGrafico> d = new ArrayList<>();
        for(int i=datos.size()-1; i>=0; i--){
            d.add(datos.get(i));
        }
        return d;
    }
        
    private void contar(DatoGrafico dg, RegistroHistorial rh){
        /*si el nombre no esta, lo agrego y despues cuento para eso nombre*/
        if(!dg.isNombre(rh.getNombreResponsable())){
            dg.addNombre(rh.getNombreResponsable());
        }
        dg.contar(rh.getNombreResponsable());
    }
    
    private boolean isDato(String xlabel){
        for(DatoGrafico dg: datos){
            if(dg.getxLabel().equalsIgnoreCase(xlabel)){
                return true;
            }
        }
        return false;
    }
    
    private DatoGrafico getDato(String xlabel){
        for(DatoGrafico dg: datos){
            if(dg.getxLabel().equalsIgnoreCase(xlabel)){
                return dg;
            }
        }
        return null;
    }
}
