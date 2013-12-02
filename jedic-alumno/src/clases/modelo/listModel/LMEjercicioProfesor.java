/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases.modelo.listModel;

import clases.modelo.EjercicioProfesor;
import java.util.List;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

/**
 *
 * @author Administrador
 */
public class LMEjercicioProfesor implements ListModel{
    List<EjercicioProfesor> ejer;

    public LMEjercicioProfesor(List<EjercicioProfesor> ejer) {
        this.ejer = ejer;
    }
    
    
    @Override
    public int getSize() {
        return ejer.size();
    }

    @Override
    public Object getElementAt(int index) {
        return ejer.get(index);
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
