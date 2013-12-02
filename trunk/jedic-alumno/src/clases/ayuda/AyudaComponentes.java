/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases.ayuda;


/**
 *
 * @author Pato Pérez
 */
public class AyudaComponentes {
    /**
     * 
     * @param tipo
     * @return null si no se encuentra el tipo
     */
    public static String getAyuda(TipoComponente tipo){
        switch(tipo){
            case WHILE:{
                return "El bucle while es una estructura "
                        + "cuyo propósito es repetir un bloque de código mientras "
                        + "una condición se mantenga verdadera.";
            }
            case DO_WHILE: {
                return "Es un bucle ientras la condición sea verdadera, "
                        + "se ejecutarán las sentencias del bloque.";
            }
            case IF: {
            }
            case IF_ELSE: {
            }
            case FOR: {
            }
            case FORX2: {
            }
            case SWITCH_CASE: {
            }
            case PRINTF: {
            }
            case SCANF: {
            }
            case GETCHE: {
            }
            case SYS_CLS: {
            }
            case SYS_PAUSE: {
            }
            case LONG: {
            }
            case FLOAT: {
            }
            case BOOL: {
            }
            case INT: {
            }
            case DOUBLE: {
            }
            case CHAR: {
            }
            case SHORT: {
            }
            case VARIABLE: {
            }
            case FUNCION: {
            }
            case ESTRUCTURA_DE_DATOS: {
            }
            case LIBRERIA: {
            }
            default:{
                return null;
            }
        }
        
    }
    
   
    
}
