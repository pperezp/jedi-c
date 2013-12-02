/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package clases.utilidades;

/**
 *
 * @author Fabiola
 */
public class Redimensionar {

    public static void redimensionarFormulario(javax.swing.JFrame form, boolean redimensionable, String titulo){
        form.setBounds(0,0,(int)form.getPreferredSize().getWidth()+20,(int)form.getPreferredSize().getHeight()+50);
        form.setLocationRelativeTo(null);
        form.setResizable(redimensionable);
        form.setTitle(titulo);
    }
}
