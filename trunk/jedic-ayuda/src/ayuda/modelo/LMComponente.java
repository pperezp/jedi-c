/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ayuda.modelo;

import java.util.List;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

/**
 *
 * @author pato
 */
public class LMComponente implements ListModel{
    private List<Componente> componentes;
    
    public LMComponente(List<Componente> componentes){
        this.componentes = componentes;
    }

    @Override
    public int getSize() {
        return componentes.size();
    }

    @Override
    public Object getElementAt(int index) {
        return componentes.get(index);
    }

    @Override
    public void addListDataListener(ListDataListener l) {
        System.out.println("addListDataListener");
    }

    @Override
    public void removeListDataListener(ListDataListener l) {
        System.out.println("removeListDataListener");
    }
    
}
