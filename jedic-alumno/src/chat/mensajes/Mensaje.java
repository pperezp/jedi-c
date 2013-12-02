/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.mensajes;

/**
 *
 * @author Administrador
 */
public class Mensaje implements java.io.Serializable{

    /**
     *
     */
    public static final int SI = javax.swing.JOptionPane.YES_OPTION;
    /**
     *
     */
    public static final int NO = javax.swing.JOptionPane.NO_OPTION;
    /**
     *
     */
    public static final int CANCELAR = javax.swing.JOptionPane.CANCEL_OPTION;

    public static int OPCION;

    /**
     *
     * @param titulo
     * @param mensaje
     */
    public static void warning(String titulo, String mensaje) {
        javax.swing.JOptionPane.showMessageDialog(null, "¡Atención!, " + mensaje,
                titulo, javax.swing.JOptionPane.WARNING_MESSAGE);
    }

    /**
     *
     * @param mensaje
     */
    public static void warning(String mensaje) {
        javax.swing.JOptionPane.showMessageDialog(null, "¡Atención!, " + mensaje,
                "Atención", javax.swing.JOptionPane.WARNING_MESSAGE);
    }

    /**
     *
     * @param mensaje
     * @return
     */
    public static int preguntaSiNo(String mensaje) {
        return javax.swing.JOptionPane.showConfirmDialog(null, mensaje, "Pregunta", javax.swing.JOptionPane.YES_NO_OPTION);
    }

    /**
     *
     * @param mensaje
     * @return
     */
    public static int preguntaSiNoCancelar(String mensaje) {
        return javax.swing.JOptionPane.showConfirmDialog(null, mensaje, "Pregunta", javax.swing.JOptionPane.YES_NO_CANCEL_OPTION);
    }

    /**
     *
     * @param mensaje
     */
    public static void informacion(String mensaje) {
        javax.swing.JOptionPane.showMessageDialog(null, mensaje, "Información", javax.swing.JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     *
     * @param titulo
     * @param mensaje
     */
    public static void informacion(String titulo, String mensaje) {
        javax.swing.JOptionPane.showMessageDialog(null, mensaje, titulo, javax.swing.JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     *
     * @param titulo
     * @param mensaje
     */
    public static void error(String titulo, String mensaje) {
        javax.swing.JOptionPane.showMessageDialog(null, mensaje, titulo, javax.swing.JOptionPane.ERROR_MESSAGE);
    }

    /**
     *
     * @param mensaje
     */
    public static void error(String mensaje) {
        javax.swing.JOptionPane.showMessageDialog(null, mensaje, "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
    }

    /**
     *
     * @param titulo
     * @param mensaje
     * @return
     */
    public static String mostrarMensaje(String titulo, String mensaje) {
        return javax.swing.JOptionPane.showInputDialog(
                null, mensaje, titulo, javax.swing.JOptionPane.PLAIN_MESSAGE);
    }
}
