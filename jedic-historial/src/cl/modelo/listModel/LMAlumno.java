/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.modelo.listModel;

import cl.modelo.Alumno;
import cl.modelo.Profesor;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

/**
 *
 * @author Pato Pérez
 */
public class LMAlumno implements ListModel {
   private List<Alumno> alumnos;
    
    public LMAlumno(){
        alumnos = new ArrayList<>();
    }
    
    public void addAlumno(Alumno p){
        if(!alumnos.contains(p)){
            alumnos.add(p);
        }
    }

    public List<Alumno> getAlumnos() {
        return alumnos;
    }
    
    public void removeAlumno(Alumno p){
        alumnos.remove(p);
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
