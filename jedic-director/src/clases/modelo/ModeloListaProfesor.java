/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases.modelo;

import java.util.List;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

/**
 *
 * @author Administrador
 */
public class ModeloListaProfesor implements ListModel{
    private List<Profesor> profesores;

    public ModeloListaProfesor(List<Profesor> profesores) {
        this.profesores = profesores;
    }
    
    public int getSize() {
        return profesores.size();
    }

    public Object getElementAt(int index) {
        Profesor p = profesores.get(index);
        return p.getNombre();
    }

    public void addListDataListener(ListDataListener l) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void removeListDataListener(ListDataListener l) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public Profesor getProfesorAt(int index){
        return profesores.get(index);
    }
    
    public void addDirector(Profesor p){
        profesores.add(p);
    }
    
    public void removeProfesor(String codigo){
        for(Profesor p : profesores){
            if(p.getCodigo().equalsIgnoreCase(codigo)){
                profesores.remove(p);
                break;
            }
        }
    }
}
