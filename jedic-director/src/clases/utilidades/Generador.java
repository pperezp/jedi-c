/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package clases.utilidades;
import java.util.Random;
/**
 *
 * @author Administrador
 */
public class Generador {
	private static Random rand = new Random();
    	private static String codigo;

    public static String nombreUsuario(String nombreCompleto){
        String nombre;
        if(!nombreCompleto.trim().equalsIgnoreCase("")){
            String[] nom;
            nom = nombreCompleto.split(" ");
            try{
                nombre = (nom[0].substring(0, 1)+ nom[1].substring(0, nom[1].length())).toLowerCase();

                return reemplazarLetras(nombre);
            }catch(ArrayIndexOutOfBoundsException e){
                nombre = (nom[0].substring(0, 1)).toLowerCase();
                return reemplazarLetras(nombre);
            }
        }else{
            return "";
        }
    }

    private static String reemplazarLetras(String nombre) {
        nombre = nombre.replaceAll("ñ", "n");
        nombre = nombre.replaceAll("á", "a");
        nombre = nombre.replaceAll("é", "e");
        nombre = nombre.replaceAll("í", "i");
        nombre = nombre.replaceAll("ó", "o");
        nombre = nombre.replaceAll("ú", "u");
        return nombre;
    }

    public static String codigoRandom(int largoDeCodigo) {
        codigo = "";
        int r;
        for (int i = 0; i < largoDeCodigo; i++) {
            do{
                r = rand.nextInt(122);
            }while(!(r>=48 && r<=57 || r>=65 && r<=90 || r>=97 && r<=122));
            codigo += (char)r;
        }
        return codigo;
    }

}
