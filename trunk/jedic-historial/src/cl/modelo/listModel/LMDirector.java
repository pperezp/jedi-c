/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.modelo.listModel;

import cl.modelo.Director;
import cl.modelo.Profesor;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

/**
 *
 * @author Pato Pérez
 */
public class LMDirector implements ListModel {
    private List<Director> directores;
    
    public LMDirector(){
        directores = new ArrayList<>();
    }
    
    public void addDirector(Director p){
        if(!directores.contains(p)){
            directores.add(p);
        }
    }

    public List<Director> getDirectores() {
        return directores;
    }
    
    
    public void removeDirector(Director p){
        directores.remove(p);
    }
    @Override
    public int getSize() {
        return directores.size();
    }

    @Override
    public Object getElementAt(int index) {
        return directores.get(index);
    }

    @Override
    public void addListDataListener(ListDataListener l) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void removeListDataListener(ListDataListener l) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }
}
