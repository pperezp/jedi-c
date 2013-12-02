/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

/**
 *
 * @author Patricio Pérez Pinto
 */
public class ModeloListaContactos implements ListModel, Serializable{
    ArrayList<Cliente> listaContactos;

    public ModeloListaContactos() {
        this.listaContactos = new ArrayList<Cliente>();
    }
    
    public ModeloListaContactos(ArrayList<Cliente> listaContactos){
        this.listaContactos = listaContactos;
    }

    @Override
    public int getSize() {
        return listaContactos.size();
    }

    @Override
    public Object getElementAt(int index) {
        return listaContactos.get(index).getNick();
    }

    @Override
    public void addListDataListener(ListDataListener l) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void removeListDataListener(ListDataListener l) {
    }
    
    public Cliente getClienteAt(int index){
        return listaContactos.get(index);
    }
    
    public ArrayList<Cliente> getClientes(){
        return listaContactos;
    }
    
    public void addCliente(Cliente cliente){
        listaContactos.add(cliente);
    }
    
    public synchronized void removeCliente(Cliente cliente){
        for(Cliente c:listaContactos){
            if(c.getNombre().equalsIgnoreCase(cliente.getNombre())){
                listaContactos.remove(c);
            }
        }
    }
}
