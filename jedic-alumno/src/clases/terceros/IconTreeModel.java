/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package clases.terceros;

import javax.swing.event.EventListenerList;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

/**
 *
 * @author iuga
 */
public class IconTreeModel implements TreeModel {
    private TreeEntry root;
    private TreeEntry rootFalse;
    private TreeEntry codigosFuente ;
    private TreeEntry librerias;
    private TreeEntry aux;
    protected EventListenerList listenerList = new EventListenerList();

    public IconTreeModel(String nombreProyecto, String nombreCodigo, String nombreLibreria) {
        rootFalse = new TreeEntry("root", null);
        root = new TreeEntry(nombreProyecto, "/imagenes/action_back.gif");
        codigosFuente = new TreeEntry("Códigos Fuente","/imagenes/minus.png");
        librerias = new TreeEntry("Librerías","/imagenes/page_new.gif");
        
        if (nombreCodigo != null) {
            aux = new TreeEntry(nombreCodigo + ".cpp", "/imagenes/disks.png");
            codigosFuente.add(aux);
        }
        if (nombreLibreria != null) {
            aux = new TreeEntry(nombreCodigo + ".h", "/imagenes/action_save.gif");
            librerias.add(aux);
        }
        root.add(codigosFuente);
        root.add(librerias);
        rootFalse.add(root);
//        }
    }

    public Object getRoot() {
        return root;
    }

    public Object getChild(Object parent, int index) {
        return ((TreeEntry) parent).get(index);
    }

    public int getChildCount(Object parent) {
        return ((TreeEntry) parent).size();
    }

    public boolean isLeaf(Object node) {
        return ((TreeEntry) node).size() == 0;
    }

    public void valueForPathChanged(TreePath path, Object newValue) {
        throw new UnsupportedOperationException("Not supported");
    }

    public int getIndexOfChild(Object parent, Object child) {
        return ((TreeEntry) parent).indexOf(child);
    }

    public void addTreeModelListener(TreeModelListener l) {
        listenerList.add(TreeModelListener.class, l);
    }

    public void removeTreeModelListener(TreeModelListener l) {
        listenerList.remove(TreeModelListener.class, l);
    }
}
