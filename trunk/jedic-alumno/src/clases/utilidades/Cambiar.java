package clases.utilidades;

import java.awt.Window;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Pato
 */
public class Cambiar {

    /**
     *
     * @param form
     * @param rutaIcono
     */
    public static void iconoDeFormulario(Window form, String rutaIcono) {
        form.setIconImage(new javax.swing.ImageIcon(form.getClass().getResource(rutaIcono)).getImage());
    }

    /**
     *
     * @param form
     * @param redimensionable
     * @param titulo
     */
    public static void tamañoDeFormulario(javax.swing.JFrame form, boolean redimensionable, String titulo) {
        form.setBounds(0, 0, (int) form.getPreferredSize().getWidth() + 20, (int) form.getPreferredSize().getHeight()+30);
        form.setLocationRelativeTo(null);
        form.setResizable(redimensionable);
        form.setTitle(titulo);
//        form.setAlwaysOnTop(true);
    }

    public static void fondoDeLabel(javax.swing.JFrame form, javax.swing.JLabel lbl, String rutaImagen){
        lbl.setIcon(new javax.swing.ImageIcon(form.getClass().getResource(rutaImagen)));
        lbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
//        lbl.setBounds(5, 5, new javax.swing.ImageIcon(form.getClass().getResource(rutaImagen)).getIconWidth(), new javax.swing.ImageIcon(form.getClass().getResource(rutaImagen)).getIconHeight());
    }

    public static void fondoDeLabel(javax.swing.JLabel lbl, java.awt.Color color){
        lbl.setBackground(color);
    }
    
    public static void cambiarIconoLabel(javax.swing.JLabel lbl, String rutaIcono){
        lbl.setIcon(new javax.swing.ImageIcon(lbl.getClass().getResource(rutaIcono)));
    }

    public static String comillasAdoble(String texto){
        return texto.replaceAll("'", "''");
    }

    public static String doblesAcomillas(String texto){
        return texto.replaceAll("''", "'");
    }

public static void aparienciaFormulario(){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(Aplicacion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
//            Logger.getLogger(Aplicacion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
//            Logger.getLogger(Aplicacion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
//            Logger.getLogger(Aplicacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
