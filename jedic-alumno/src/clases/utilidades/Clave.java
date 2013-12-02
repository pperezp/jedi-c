/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package clases.utilidades;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Patricio Pérez Pinto
 */
public class Clave {
    private static List err ;

    public static String isClaveCorrecta(String clave) {
        err = new ArrayList();
        int c_letras_mayus = 0, c_letras_minus = 0, c_numeros = 0;
        int caracter;
        for (int i = 0; i < clave.length(); i++) {
            caracter = (int) clave.charAt(i);
            if (caracter >= 48 && caracter <= 57) {//si el caratcer es numérico
                c_numeros++;
            } else if (caracter >= 65 && caracter <= 90) {//si es letra mayuscula
                c_letras_mayus++;
            } else if (caracter >= 97 && caracter <= 122) {//si es letra minuscula
                c_letras_minus++;
            }
        }
        if (clave.length() < 6) {
            err.add("Un mínimo de 6 caracteres");
        }

        if(clave.length() > 12){
            err.add("Un largo máximo de 12 carácteres");
        }

        if(c_numeros == 0){
            err.add("Números");
        }
        if(c_letras_mayus == 0){
            err.add("Letras mayúsculas");
        }
        if(c_letras_minus == 0){
            err.add("Letras minúsculas");
        }

        String e = "";
        if(!err.isEmpty()){
            
            for(int i=0;i<err.size();i++){
                e += "  -"+err.get(i).toString()+"\n";
            }
        }
        return e;
    }
}
