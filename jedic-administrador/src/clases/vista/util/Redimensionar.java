/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package clases.vista.util;

/**
 *
 * @author Fabiola
 */
public class Redimensionar {

    /**
     * 
     * @param form
     * @param redimensionable
     * @param titulo
     */
    public static void redimensionarFormulario(javax.swing.JFrame form, boolean redimensionable, String titulo){
        form.setBounds(0,0,(int)form.getPreferredSize().getWidth(),(int)form.getPreferredSize().getHeight());
        form.setLocationRelativeTo(null);
        form.setResizable(redimensionable);
        form.setTitle(titulo);
    }
}
