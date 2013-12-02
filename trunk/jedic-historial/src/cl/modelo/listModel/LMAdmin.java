/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.modelo.listModel;

import cl.modelo.Administrador;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

/**
 *
 * @author Pato Pérez
 */
public class LMAdmin implements ListModel {
   private List<Administrador> admins;
    
    public LMAdmin(){
        admins = new ArrayList<>();
    }

    public List<Administrador> getAdmins() {
        return admins;
    }
    
    public void addAdmin(Administrador p){
        if(!admins.contains(p)){
            admins.add(p);
        }
    }
    
    public void removeAdmin(Administrador p){
        admins.remove(p);
    }
    @Override
    public int getSize() {
        return admins.size();
    }

    @Override
    public Object getElementAt(int index) {
        return admins.get(index);
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
