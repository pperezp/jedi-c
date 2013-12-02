/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases.modelo.listas.listModel;

import clases.modelo.Director;
import java.util.List;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

/**
 * la clase ModeloListaDirector es el Modelo para la lista de Directores o sea,
 * hay un lista de directores, de objetos
 *
 * @author Administrador
 */
public class ModeloListaDirector implements ListModel {

    private List<Director> directores;

    /**
     *
     * @param directores una lista con Directores
     * @see clases.modelo.Director
     */
    public ModeloListaDirector(List<Director> directores) {
        this.directores = directores;
    }

    @Override
    public int getSize() {
        return directores.size();
    }

    @Override
    public Object getElementAt(int index) {
        Director d = directores.get(index);
        return d.getNombre() + " <" + d.getSede() + ">";
    }

    @Override
    public void addListDataListener(ListDataListener l) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void removeListDataListener(ListDataListener l) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * obtiene el codigo del director en una posicion determinada
     *
     * @param index indice
     * @return el codigo del Director
     * @see clases.modelo.Director
     */
    public String getCodigoDirectorAt(int index) {
        return directores.get(index).getCodigo();
    }

    /**
     * obtiene el director en una posicion determinada
     *
     * @param index posicion
     * @return el Director
     * @see clases.modelo.Director
     */
    public Director getDirectorAt(int index) {
        return directores.get(index);
    }

    /**
     * Agrega un Director a la lista
     *
     * @param director el director
     * @see modelo.clases.Director
     */
    public void addDirector(Director director) {
        directores.add(director);
    }

    /**
     * Elimina un director a partir de su codigo
     *
     * @param codigo el codigo del director
     */
    public void removeDirector(String codigo) {
        for (Director d : directores) {
            if (d.getCodigo().equalsIgnoreCase(codigo)) {
                directores.remove(d);
                break;
            }
        }
    }
}
