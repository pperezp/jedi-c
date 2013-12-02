/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package clases.utilidades;

import javax.swing.JTextField;

/**
 *
 * @author Patricio Pérez Pinto
 */
public class Controlar {
    public static void largoDeTexto(JTextField textField, int maxLength) {
        String text = textField.getText();
        if (text.length() > maxLength) {
            text = text.substring(0, maxLength);
            textField.setText(text);
        }
    }
}
