/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases.utilidades;

import clases.generator.Ejecutar;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author Patricio Pérez Pinto
 */
public class Copiar {

    public static void copiar(String origen, String destino, boolean isOculto) {
        try {
            File inFile = new File(origen);
            File outFile = new File(destino);

            FileInputStream in = new FileInputStream(inFile);
            FileOutputStream out = new FileOutputStream(outFile);

            int c;
            while ((c = in.read()) != -1) {
                out.write(c);
            }

            in.close();
            out.close();
            if(isOculto){
                Ejecutar.ejecutarComando("attrib +h \""+destino+"\"");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
