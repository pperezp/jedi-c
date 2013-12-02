/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases.configuraciones;

import java.awt.Color;
import org.fife.ui.rsyntaxtextarea.SyntaxScheme;
import org.fife.ui.rsyntaxtextarea.Token;

/**
 *
 * @author zombiaco
 */
public class Colores {
    public static final int COMENTARIO = 1;
    public static final int PALABRA_RESERVADA = 2;
    public static final int FUNCIONES = 3;
    public static final int CADENAS_Y_NUMEROS = 4;
    public static final int TIPOS_DE_DATOS = 5;

    public static Color COMENTARIO_COLOR;
    public static Color PALABRA_RESERVADA_COLOR;
    public static Color FUNCIONES_COLOR;
    public static Color CADENAS_Y_NUMEROS_COLOR;
    public static Color TIPOS_DE_DATOS_COLOR;
    public static Color FONDO_COLOR;
    public static Color CODIGO_COLOR;
    public static Color LINEA_COLOR;

    
    public static void establecerColor(int tipo, SyntaxScheme scheme, Color color) {
        switch(tipo){
            case COMENTARIO:{
                COMENTARIO_COLOR = color;
                scheme.styles[Token.COMMENT_EOL].foreground = color;
                scheme.styles[Token.COMMENT_MULTILINE].foreground = color;
                scheme.styles[Token.COMMENT_DOCUMENTATION ].foreground = color;
                break;
            }
            case PALABRA_RESERVADA:{
                PALABRA_RESERVADA_COLOR = color;
                scheme.styles[Token.RESERVED_WORD ].foreground = color;
                break;
            }
            case FUNCIONES:{
                FUNCIONES_COLOR = color;
                scheme.styles[Token.FUNCTION].foreground = color;
                break;
            }
            case CADENAS_Y_NUMEROS:{
                CADENAS_Y_NUMEROS_COLOR = color;
                scheme.styles[Token.LITERAL_BOOLEAN].foreground = color;
                scheme.styles[Token.LITERAL_NUMBER_DECIMAL_INT ].foreground = color;
                scheme.styles[Token.LITERAL_NUMBER_FLOAT ].foreground = color;
                scheme.styles[Token.LITERAL_NUMBER_HEXADECIMAL].foreground = color;
                scheme.styles[Token.LITERAL_STRING_DOUBLE_QUOTE ].foreground = color;
                scheme.styles[Token.LITERAL_CHAR ].foreground = color;
                scheme.styles[Token.LITERAL_BACKQUOTE ].foreground = color;
                break;
            }
            case TIPOS_DE_DATOS:{
                TIPOS_DE_DATOS_COLOR = color;
                scheme.styles[Token.DATA_TYPE ].foreground = color;
                break;
            }
        }
    }

    public static void establecerColor(int tipo, Color color) {
        switch(tipo){
            case COMENTARIO:{
                COMENTARIO_COLOR = color;
                break;
            }
            case PALABRA_RESERVADA:{
                PALABRA_RESERVADA_COLOR = color;
                break;
            }
            case FUNCIONES:{
                FUNCIONES_COLOR = color;
                break;
            }
            case CADENAS_Y_NUMEROS:{
                CADENAS_Y_NUMEROS_COLOR = color;
                break;
            }
            case TIPOS_DE_DATOS:{
                TIPOS_DE_DATOS_COLOR = color;
                break;
            }
        }
    }
}
