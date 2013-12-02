/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.modelo;

import java.util.ArrayList;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

/**
 *
 * @author Patricio Pérez Pinto
 */
public class ModeloListaSalas implements ListModel{
    private ArrayList<Sala> salas;

    public ModeloListaSalas(ArrayList<Sala> salas) {
        this.salas = salas;
    }
    
    public ModeloListaSalas(){
        salas = new ArrayList<Sala>();
    }
    
    @Override
    public int getSize() {
        return salas.size();
    }

    @Override
    public Object getElementAt(int index) {
        Sala s = salas.get(index);
        return s.getNombreSala();
    }

    @Override
    public void addListDataListener(ListDataListener l) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void removeListDataListener(ListDataListener l) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public Sala getSalaAt(int index){
        return salas.get(index);
    }
    
    public void addSala(Sala sala){
        salas.add(sala);
    }
    
    public void removeSala(String nombreSala){
        for(Sala s:salas){
            if(s.getNombreSala().equalsIgnoreCase(nombreSala)){
                salas.remove(s);
                break;
            }
        }
    }
    
    public ArrayList<Sala> getListaDeSalas(){
        return salas;
    }
}
