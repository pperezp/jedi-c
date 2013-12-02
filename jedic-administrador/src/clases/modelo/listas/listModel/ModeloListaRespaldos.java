/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases.modelo.listas.listModel;

import clases.utilidades.fechaYHora.Fecha;
import java.io.File;
import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

/**
 * la clase ModeloListaRespaldos es el Modelo para la lista de Respaldos o sea,
 * hay una lista de respaldos, de objetos
 *
 * @author Patricio Pérez Pinto
 */
public class ModeloListaRespaldos implements ListModel {

    private CopyOnWriteArrayList<File> respaldos;

    /**
     * Constructor
     *
     * @param listaRespaldos recibo una lista de archivos de los respaldos
     */
    public ModeloListaRespaldos(File[] listaRespaldos) {
        respaldos = new CopyOnWriteArrayList<>();
        this.respaldos.addAll(Arrays.asList(listaRespaldos));

        for (File f : respaldos) {
            if (!f.getName().contains("mysqlback")) {
                respaldos.remove(f);
            }
        }
    }

    /**
     * agrega un respaldo a la lista
     *
     * @param respaldo archivo de respaldo
     */
    public void addRespaldo(File respaldo) {
        respaldos.add(respaldo);
    }

    @Override
    public int getSize() {
        return respaldos.size();
    }

    @Override
    public Object getElementAt(int index) {
        return getNombreRespaldoFormateado(respaldos.get(index).getName());
    }

    @Override
    public void addListDataListener(ListDataListener l) {
//        l.
    }

    @Override
    public void removeListDataListener(ListDataListener l) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * obtiene el nombre del respaldo, bien formado, o sea, de la siguiente
     * forma: "Respaldo del "+dia +" de "+mes + " de "+ano + "
     * ("+hora+":"+minutos+" hrs.)";
     *
     * @param nombreArchivo
     * @return el nombre del respaldo formateado
     */
    private String getNombreRespaldoFormateado(String nombreArchivo) {
        String[] st = nombreArchivo.split("_");
        String st2 = st[0];
        String hora, minutos, ano, mes, dia;
        //el substring es con indices del array
        //pones el indice que quieres que se corte lo incluye, y el otro parametros
        //es el indice final mas 1
        ano = st2.substring(0, 4);
        mes = st2.substring(4, 6);
        dia = st2.substring(6, 8);
        hora = st2.substring(8, 10);
        minutos = st2.substring(10, 12);
        return "Respaldo del " + dia + " de " + Fecha.getNombreDeMes(Integer.parseInt(mes)) + " de " + ano + " (" + hora + ":" + minutos + " hrs.)";
    }

    /**
     * obtiene la ruta del respaldo en una posicion determinada
     *
     * @param index posicion
     * @return la ruta del respaldo
     */
    public String getRutaDeRespaldo(int index) {
        return respaldos.get(index).getAbsolutePath();
    }
}
