/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases.utilidades;

import java.util.List;
import javax.swing.text.JTextComponent;


/**
 *
 * @author Patricio Pérez Pinto
 */
public class Validar {
    /**
     * Método para Validar si una casilla esta vacia
     * @param componente Componente a validar
     * @return True si esta Correcto y false si la casilla esta vacía
     */
    public static boolean isJTextComponentVacio(JTextComponent componente){
        if(componente.getText().trim().equalsIgnoreCase("")){
            componente.setText("");
            componente.requestFocus();
            return true;
        }else{
            return false;
        }
    }
    
    /**
     * Método para Validar una lista de JTextComponent
     * @param listaComponentes Lista de Componentes a validar
     * @return True si esta Correcto y false si alguna casilla esta vacía
     */
    public static boolean isJTextComponentVacio(List<JTextComponent> listaComponentes){
        JTextComponent comp;
        for(int i =0; i<listaComponentes.size(); i++){
            comp = listaComponentes.get(i);
            if(comp.getText().trim().equalsIgnoreCase("")){
                comp.setText("");
                comp.requestFocus();
                return true;
            }
        }
        return false;
    }
    
    public static boolean isMailCorrecto(String mail){
        try{
            if(!mail.contains("@") || !mail.contains(".") || mail.contains(" ")){
                return false;
            }else {
                String[] partes = mail.split("@");
                if(partes.length>2){
                    return false;
                }else if(partes[0].equalsIgnoreCase("") || partes[1].equalsIgnoreCase("")){
                    return false;
                }else{
                    String[] parte2 = partes[1].split("\\.");
                    if(parte2.length > 2){
                        return false;
                    }else if(parte2[0].equalsIgnoreCase("") || parte2[1].equalsIgnoreCase("")){
                        return false;
                    }else{
                        return true;
                    }
                }
            }
        }catch(Exception e){
            return false;
        }
    }
    
    public static boolean isNombreDeUsuarioCorrecto(String nombre){
        if(nombre.length() <= 1) return false;
        else return true;
    }
    
    public static boolean isJTextComponentConSimbolos(JTextComponent componente){
        int caracter, contSimbolo = 0;
        for (int i = 0; i < componente.getText().length(); i++) {
            caracter = (int) componente.getText().charAt(i);
            if (caracter >= 65 && caracter <= 90) {//si es letra mayuscula
            } else if (caracter >= 97 && caracter <= 122) {//si es letra minuscula
            } else if(componente.getText().charAt(i) == ' ' || componente.getText().charAt(i) == 'á' || 
                    componente.getText().charAt(i) == 'é' || componente.getText().charAt(i) == 'í' || 
                    componente.getText().charAt(i) == 'ó' || componente.getText().charAt(i) == 'ú' || 
                    componente.getText().charAt(i) == 'Á' || componente.getText().charAt(i) == 'É' || 
                    componente.getText().charAt(i) == 'Í' || componente.getText().charAt(i) == 'Ó' || 
                    componente.getText().charAt(i) == 'Ú' || componente.getText().charAt(i) == 'ñ' || 
                    componente.getText().charAt(i) == 'Ñ'){
            }else contSimbolo++;
        }
        if(contSimbolo != 0){
            componente.selectAll();
            componente.requestFocus();
        }
        return !(contSimbolo == 0);
    }
    
    public static boolean isJTextComponentConSimbolos(List<JTextComponent> listaComponentes){
        JTextComponent comp;
        int caracter, contSimbolo = 0;
        for(int i =0; i<listaComponentes.size(); i++){
            comp = listaComponentes.get(i);
            for (int j = 0; j < comp.getText().length(); j++) {
                caracter = (int) comp.getText().charAt(j);
                if (caracter >= 65 && caracter <= 90) {//si es letra mayuscula
                } else if (caracter >= 97 && caracter <= 122) {//si es letra minuscula
                } else if(comp.getText().charAt(i) == ' ' || comp.getText().charAt(i) == 'á' || 
                        comp.getText().charAt(i) == 'é' || comp.getText().charAt(i) == 'í' || 
                        comp.getText().charAt(i) == 'ó' || comp.getText().charAt(i) == 'ú' || 
                        comp.getText().charAt(i) == 'Á' || comp.getText().charAt(i) == 'É' || 
                        comp.getText().charAt(i) == 'Í' || comp.getText().charAt(i) == 'Ó' || 
                        comp.getText().charAt(i) == 'Ú' || comp.getText().charAt(i) == 'ñ' || 
                        comp.getText().charAt(i) == 'Ñ'){
                } else{
                    contSimbolo++;
                }
            }
            if(contSimbolo != 0){
                comp.selectAll();
                comp.requestFocus();
                return true;
            }
            contSimbolo = 0;
        }
        return false;
    }
    
    public static boolean isFechaCorrecta(java.util.Calendar fechaInicio, java.util.Calendar fechaFinal){
        return !fechaInicio.after(fechaFinal);
    }
}
