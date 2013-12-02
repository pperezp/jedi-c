/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.modelo.listModel;

import cl.modelo.Profesor;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

/**
 *
 * @author Pato Pérez
 */
public class LMProfesor implements ListModel{
    private List<Profesor> profesores;
    
    public LMProfesor(){
        profesores = new ArrayList<>();
    }

    public List<Profesor> getProfesores() {
        return profesores;
    }
    
    public void addProfe(Profesor p){
        if(!profesores.contains(p)){
            profesores.add(p);
            System.out.println("agregado");
        }
    }
    public void removeProfe(Profesor p){
        profesores.remove(p);
    }
    @Override
    public int getSize() {
        return profesores.size();
    }

    @Override
    public Object getElementAt(int index) {
        return profesores.get(index);
    }

    @Override
    public void addListDataListener(ListDataListener l) {
        
    }

    @Override
    public void removeListDataListener(ListDataListener l) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
