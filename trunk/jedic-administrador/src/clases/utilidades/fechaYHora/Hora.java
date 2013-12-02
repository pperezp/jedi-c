/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package clases.utilidades.fechaYHora;

import java.util.Calendar;

/**
 *
 * @author Administrador
 */
public class Hora {
    private static Calendar hora;
    private static int h, m, s;

    /**
     * @return the h
     */
    private static void construirHora(){
        hora = Calendar.getInstance();
        h = hora.get(Calendar.HOUR_OF_DAY);
        m = hora.get(Calendar.MINUTE);
        s = hora.get(Calendar.SECOND);
    }

    /**
     * 
     * @return
     */
    public static String getHora() {
        construirHora();
        return cambiar(h);
    }

    /**
     * @return the m
     */
    public static String getMinuto() {
        construirHora();
        return cambiar(m);
    }

    /**
     * @return the s
     */
    public static String getSegundo() {
        construirHora();
        return cambiar(s);
    }

    /**
     * 
     * @return
     */
    public static String getHoraActual(){
        construirHora();
        String minuto, segundo;
        minuto = cambiar(m);
        segundo = cambiar(s);
        
        
        String horaActual = Integer.toString(h)+":"
        +minuto+(":")
        +segundo;
        
        return horaActual;
    }

    /**
     * 
     * @param valor
     * @return
     */
    public static String cambiar(int valor){
        switch(valor){
		case 0:{
                return "00";
            }
            case 1:{
                return "01";
            }
            case 2:{
                return "02";
            }
            case 3:{
                return "03";
            }
            case 4:{
                return "04";
            }
            case 5:{
                return "05";
            }
            case 6:{
                return "06";
            }
            case 7:{
                return "07";
            }
            case 8:{
                return "08";
            }
            case 9:{
                return "09";
            }

            default:{
                return Integer.toString(valor);
            }
        }
    }
}
