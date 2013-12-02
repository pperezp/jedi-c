/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package clases.utilidades;

import clases.modelo.idioma.Idioma;
import clases.modelo.idioma.K;
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
            err.add(Idioma.palabras.get(K.UN_MINIMO_DE_6));
        }

        if(clave.length() > 12){
            err.add(Idioma.palabras.get(K.UN_LARGO_MAX_12));
        }

        if(c_numeros == 0){
            err.add(Idioma.palabras.get(K.NUMEROS));
        }
        if(c_letras_mayus == 0){
            err.add(Idioma.palabras.get(K.LETRAS_MAYUSCULAS));
        }
        if(c_letras_minus == 0){
            err.add(Idioma.palabras.get(K.LETRAS_MINUSCULAS));
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
