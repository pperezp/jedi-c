/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases.vista.mensajes;

/**
 *
 * @author Administrador
 */
public final class Mensajes {

    /**
     * constante para ser usada como reemplado de
     * javax.swing.JOptionPane.YES_OPTION
     *
     * @see javax.swing.JOptionPane
     */
    public static final int SI = javax.swing.JOptionPane.YES_OPTION;
    /**
     * constante para ser usada como reemplado de
     * javax.swing.JOptionPane.NO_OPTION
     *
     * @see javax.swing.JOptionPane
     */
    public static final int NO = javax.swing.JOptionPane.NO_OPTION;
    /**
     * constante para ser usada como reemplado de
     * javax.swing.JOptionPane.CANCEL_OPTION
     *
     * @see javax.swing.JOptionPane
     */
    public static final int CANCELAR = javax.swing.JOptionPane.CANCEL_OPTION;
    //public static final int OPCION;

    /**
     * mostrar por pantalla un mensaje del tipo warning (advertencia)
     *
     * @param titulo el titulo del mensaje
     * @param mensaje el mensaje
     */
    public static void mensajeWarning(String titulo, String mensaje) {
        javax.swing.JOptionPane.showMessageDialog(null, "¡Atención!, " + mensaje,
                titulo, javax.swing.JOptionPane.WARNING_MESSAGE);
    }

    /**
     * mostrar por pantalla un mensaje del tipo warning (advertencia)
     *
     * @param mensaje el mensaje
     */
    public static void mensajeWarning(String mensaje) {
        javax.swing.JOptionPane.showMessageDialog(null, "¡Atención!, " + mensaje,
                "Atención", javax.swing.JOptionPane.WARNING_MESSAGE);
    }

    /**
     * mostrar un mensaje que tenga un si y un no
     *
     * @param mensaje el mensaje de pregunta
     * @return la respuesta
     */
    public static int mensajePreguntaSiNo(String mensaje) {
        return javax.swing.JOptionPane.showConfirmDialog(null, mensaje, "Pregunta", javax.swing.JOptionPane.YES_NO_OPTION);
    }

    /**
     * muestra por pantalla un mensaje de pregunta con opciones si, no y
     * cancelar
     *
     * @param mensaje el mensaje
     * @return la respuesta
     */
    public static int mensajePreguntaSiNoCancelar(String mensaje) {
        return javax.swing.JOptionPane.showConfirmDialog(null, mensaje, "Pregunta", javax.swing.JOptionPane.YES_NO_CANCEL_OPTION);
    }

    /**
     * muestra un mensaje de información por pantalla
     *
     * @param mensaje el mensaje
     */
    public static void mensajeInformacion(String mensaje) {
        javax.swing.JOptionPane.showMessageDialog(null, mensaje, "Información", javax.swing.JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * muestra un mensaje de información por pantalla
     *
     * @param titulo el titulo del mensaje
     * @param mensaje el mensaje
     */
    public static void mensajeInformacion(String titulo, String mensaje) {
        javax.swing.JOptionPane.showMessageDialog(null, mensaje, titulo, javax.swing.JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * muestra un mensaje de error por pantalla
     *
     * @param titulo el titulo del mensaje
     * @param mensaje el mensaje
     */
    public static void mensajeError(String titulo, String mensaje) {
        javax.swing.JOptionPane.showMessageDialog(null, mensaje, titulo, javax.swing.JOptionPane.ERROR_MESSAGE);
    }

    /**
     * muestra un mensaje de error por pantalla
     *
     * @param mensaje el mensaje
     */
    public static void mensajeError(String mensaje) {
        javax.swing.JOptionPane.showMessageDialog(null, mensaje, "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
    }

    /**
     * muestra un mensaje de texto plano, por pantalla, para pedir información
     * al usuario
     *
     * @param titulo el titulo del mensaje
     * @param mensaje el mensaje
     * @return un String con lo que el usuario escribió
     */
    public static String mostrarMensaje(String titulo, String mensaje) {
        return javax.swing.JOptionPane.showInputDialog(
                null, mensaje, titulo, javax.swing.JOptionPane.PLAIN_MESSAGE);
    }
}
