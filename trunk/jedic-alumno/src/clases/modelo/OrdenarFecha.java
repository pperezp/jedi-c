/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases.modelo;

import java.util.Comparator;

/**
 *
 * @author Pato Pérez
 */
public class OrdenarFecha implements Comparator{
    @Override
    /*
Condición	Valor que debe devolver
 o1 < o2        un número menor a cero
 o1 == o        cero
 o1 > o2        un número mayor a cero*/
    public int compare(Object o1, Object o2) {
        MensajeInbox m1, m2;
        m1 = (MensajeInbox)o1;
        m2 = (MensajeInbox)o2;
        if(m1.getFecha().before(m2.getFecha())){
            return 1;
        }else{
            return -1;
        }
    }
}
