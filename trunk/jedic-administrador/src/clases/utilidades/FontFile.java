/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases.utilidades;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;
/**
 *
 * @author Pato Pérez
 */
public class FontFile {

    public static Font getFont(String nombreFont, float tamano) {
        Font font;
        String fName = "/fonts/" + nombreFont;
        try {
            InputStream is = FontFile.class.getResourceAsStream(fName);
            font = Font.createFont(Font.TRUETYPE_FONT, is);
            font = font.deriveFont(tamano);
        } catch (FontFormatException | IOException ex) {
            System.err.println(fName + " no fue cargada. Se usará sans serif.");
            font = new Font("serif", Font.PLAIN, 24);
        }
        return font;
    }
}
