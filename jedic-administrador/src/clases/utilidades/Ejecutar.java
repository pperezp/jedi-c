/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases.utilidades;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author Administrador
 */
public final class Ejecutar {

    private static String salida = "";
    private static String error;
    private static Process proceso;

    /**
     * Ejecuta comandos en la linea de comandos del sistema operativo
     * @param comando comando a ejecutar
     * @return la salida de la linea de comandos
     */
    public static String ejecutarComando(String comando) {
        error = "";
        String resultado;
        try {
            if (System.getProperty("os.name").equalsIgnoreCase("linux")) {
                String[] com = {"sh", "-c", comando};
                proceso = Runtime.getRuntime().exec(com);
            } else {
                proceso = Runtime.getRuntime().exec("cmd.exe /C " + comando);
            }
            BufferedReader brStdErr;
            try (BufferedReader brStdOut = new BufferedReader(new InputStreamReader(proceso.getInputStream()))) {
                brStdErr = new BufferedReader(new InputStreamReader(proceso.getErrorStream()));
                String str;
                while ((str = brStdOut.readLine()) != null) {
                    salida = salida + str;
                }
                while ((str = brStdErr.readLine()) != null) {
                    for (int i = str.length() - 1; i >= 0; i--) {
                        if (str.charAt(i) == '\\') {
                            str = str.substring(i + 1);
                            i = -1;
                        }
                    }
                    error = error + str + "\n";
                }
                resultado = error;
                System.out.println(resultado);
            }
            brStdErr.close();
            proceso.destroy();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return salida;
    }
}
