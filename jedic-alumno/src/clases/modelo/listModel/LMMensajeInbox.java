/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases.modelo.listModel;

import clases.modelo.MensajeInbox;
import java.util.List;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

/**
 *
 * @author Fabiola
 */
public class LMMensajeInbox implements ListModel{
    private List<MensajeInbox> mensajes;

    public LMMensajeInbox(List<MensajeInbox> mensajes) {
        this.mensajes = mensajes;
    }
    
    
    @Override
    public int getSize() {
        return mensajes.size();
    }

    @Override
    public Object getElementAt(int i) {
        return mensajes.get(i);
    }

    @Override
    public void addListDataListener(ListDataListener ll) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void removeListDataListener(ListDataListener ll) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public List<MensajeInbox> actualizarLista(MensajeInbox mi){
        for(MensajeInbox m : mensajes){
            if(m.getCodigo().equalsIgnoreCase(mi.getCodigo())){
                m.setLeido(mi.isLeido());
                break;
            }
        }
        return mensajes;
    }
    
}
