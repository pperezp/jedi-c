/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.util;

import cl.archivo.Archivo;
import cl.gui.Apli;
import cl.modelo.Usuario;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrador
 */
public class Generar {

    /**
     *
     * @param nombreCompleto
     * @return
     */
    public static String nombreUsuario(String nombreCompleto) {
        String nombre;
        if (!nombreCompleto.trim().equalsIgnoreCase("")) {
            String[] nom;
            nom = nombreCompleto.split(" ");
            try {
                nombre = (nom[0].substring(0, 1) + nom[1].substring(0, nom[1].length())).toLowerCase();

                return reemplazarLetras(nombre);
            } catch (ArrayIndexOutOfBoundsException e) {
                nombre = (nom[0].substring(0, 1)).toLowerCase();
                return reemplazarLetras(nombre);
            }
        } else {
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

    /**
     * 
     * @param largoDeCodigo
     * @return
     */
    public static String codigoRandom(int largoDeCodigo) {
        Random rand = new Random();
        String codigo;
        codigo = "";
        int r;
        for (int i = 0; i < largoDeCodigo; i++) {
            do {
                r = rand.nextInt(122);
            } while (!(r >= 48 && r <= 57 || r >= 65 && r <= 90 || r >= 97 && r <= 122));
            codigo += (char) r;
        }
        return codigo;
    }
    
    public static List<Usuario> usuarios(int cantidad){
        List<Usuario> u = new ArrayList<>();
        try {
            Archivo n = new Archivo("nombres.txt");
            Archivo a = new Archivo("apellidos.txt");
            
            List<String> nombres = n.getArchivoComoString();
            List<String> apellidos = a.getArchivoComoString();
            
            Random rand = new Random();
            
            int i=0;
            
            String nombre, ape1, ape2, nombreCompleto;
            while(i<cantidad){
                nombre = nombres.get(rand.nextInt(nombres.size()));
                ape1 = apellidos.get(rand.nextInt(apellidos.size()));
                ape2 = apellidos.get(rand.nextInt(apellidos.size()));
                
                nombreCompleto = nombre + " " + ape1 + " " + ape2;
                u.add(new Usuario(
                        nombreCompleto, 
                        Generar.nombreUsuario(nombreCompleto), 
                        Generar.codigoRandom(12), 
                        Generar.nombreUsuario(nombreCompleto)+"@inacapmail.cl")
                        );
                i++;
            }
        } catch (IOException ex) {
            Logger.getLogger(Apli.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
    }
}
