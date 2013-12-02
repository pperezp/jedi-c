/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases.modelo.listModel;

import clases.modelo.Alumno;
import java.util.List;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

/**
 *
 * @author Pato
 */
public class LMAlumno implements ListModel{
    private List<Alumno> alumnos;

    public LMAlumno(List<Alumno> alumnos) {
        this.alumnos = alumnos;
    }
    
    @Override
    public int getSize() {
        return alumnos.size();
    }

    @Override
    public Object getElementAt(int index) {
        return alumnos.get(index);
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
