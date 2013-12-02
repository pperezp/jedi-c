/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package clases.mensajes;

/**
 *
 * @author Administrador
 */
public class Mensajes {
    public static final int SI = javax.swing.JOptionPane.YES_OPTION;
    public static final int NO = javax.swing.JOptionPane.NO_OPTION;
    public static final int CANCELAR = javax.swing.JOptionPane.CANCEL_OPTION;
    //public static final int OPCION;

    public static void mensajeWarning(String titulo, String mensaje){
        javax.swing.JOptionPane.showMessageDialog(null, "¡Atención!, "+mensaje,
            titulo, javax.swing.JOptionPane.WARNING_MESSAGE);
    }

    public static void mensajeWarning(String mensaje){
        javax.swing.JOptionPane.showMessageDialog(null, "¡Atención!, "+mensaje,
            "Atención", javax.swing.JOptionPane.WARNING_MESSAGE);
    }

    public static int mensajePreguntaSiNo(String mensaje){
        return javax.swing.JOptionPane.showConfirmDialog
            (null, mensaje, "Pregunta", javax.swing.JOptionPane.YES_NO_OPTION);
    }

    public static int mensajePreguntaSiNoCancelar(String mensaje){
        return javax.swing.JOptionPane.showConfirmDialog
            (null, mensaje, "Pregunta", javax.swing.JOptionPane.YES_NO_CANCEL_OPTION);
    }

    public static void mensajeInformacion(String mensaje){
        javax.swing.JOptionPane.showMessageDialog(null, mensaje, "Información", javax.swing.JOptionPane.INFORMATION_MESSAGE);
    }

    public static void mensajeInformacion(String titulo, String mensaje){
        javax.swing.JOptionPane.showMessageDialog(null, mensaje, titulo, javax.swing.JOptionPane.INFORMATION_MESSAGE);
    }

    public static void mensajeError(String titulo, String mensaje){
        javax.swing.JOptionPane.showMessageDialog(null, mensaje, titulo, javax.swing.JOptionPane.ERROR_MESSAGE);
    }

    public static void mensajeError(String mensaje){
        javax.swing.JOptionPane.showMessageDialog(null, mensaje, "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
    }

    public static String mostrarMensaje(String titulo, String mensaje){
        return javax.swing.JOptionPane.showInputDialog(
        null, mensaje, titulo, javax.swing.JOptionPane.PLAIN_MESSAGE);
    }
}
