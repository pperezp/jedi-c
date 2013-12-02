/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package clases.utilidades;

import clases.utilidades.P;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
/**
 *
 * @author Administrador
 */
public class Ejecutar {
    private static String nombreSO = System.getProperty("os.name");
    private static String salida = "";
    private static String error;
    private static Process proceso;

    public static String ejecutarComandoGcc(String ruta, String rutaSinExtension){
        error = "";

        for(int i=rutaSinExtension.length()-1;i>=0;i--){
            if(rutaSinExtension.charAt(i) == '.'){
                rutaSinExtension = rutaSinExtension.substring(0, i);
            }
        }
        
        String resultado = new String();
        try{
            proceso = Runtime.getRuntime().exec("cmd.exe /C gcc"+P.SEPARADOR+"bin"+P.SEPARADOR+"gcc \""+ruta+"\" -o \""+rutaSinExtension+"\".exe");
            System.out.println(ruta);
            BufferedReader brStdOut = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
            BufferedReader brStdErr = new BufferedReader(new InputStreamReader(proceso.getErrorStream()));
            String str=null;

            while ((str = brStdOut.readLine())!=null){
                salida = salida + str + "\n";
            }
            while ((str = brStdErr.readLine())!=null){
                for(int i=str.length()-1;i>=0;i--){
                    if(str.charAt(i) == '\\'){
                        str = str.substring(i+1);
                        i=-1;
                    }
                }
                error = error + str + "\n";
            }

            resultado = error;

            brStdOut.close();
            brStdErr.close();
            proceso.destroy();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return resultado;
    }

    public static void abrirExplorador(String ruta){
        error = "";

        String resultado = new String();
        try{
            if(nombreSO.equalsIgnoreCase("windows xp") || nombreSO.equalsIgnoreCase("windows 7") || nombreSO.equalsIgnoreCase("windows seven")){
                proceso = Runtime.getRuntime().exec("cmd.exe /C explorer "+ruta+".exe");
            }else{
                proceso = Runtime.getRuntime().exec("nautilus "+ruta+".out");
            }


            BufferedReader brStdOut = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
            BufferedReader brStdErr = new BufferedReader(new InputStreamReader(proceso.getErrorStream()));
            String str=null;

            while ((str = brStdOut.readLine())!=null){
                salida = salida + str + "\n";
            }
            while ((str = brStdErr.readLine())!=null){
                error = error + str + "\n";
            }


            resultado = error;

            brStdOut.close();
            brStdErr.close();
            proceso.destroy();
        }catch(Exception e){
        }
    }
    public static String ejecutarComando(String comando){
        error = "";

        
        String resultado = new String();
        try{
            if(System.getProperty("os.name").equalsIgnoreCase("linux")){
                proceso = Runtime.getRuntime().exec(comando);
            }else{
                proceso = Runtime.getRuntime().exec("cmd.exe /C "+comando);
            }
            
            
            BufferedReader brStdOut = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
            BufferedReader brStdErr = new BufferedReader(new InputStreamReader(proceso.getErrorStream()));
            String str=null;

            while ((str = brStdOut.readLine())!=null){
                salida = salida + str ;
            }
            while ((str = brStdErr.readLine())!=null){
                for(int i=str.length()-1;i>=0;i--){
                    if(str.charAt(i) == '\\'){
                        str = str.substring(i+1);
                        i=-1;
                    }
                }
                error = error + str + "\n";
            }

            resultado = error;
System.out.println(resultado);
            brStdOut.close();
            brStdErr.close();
            proceso.destroy();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return salida;
    }

    public void crearCarpeta(String ruta){
        File f = new File(ruta);
        f.mkdir();
//        try{
//            proceso = Runtime.getRuntime().exec("cmd.exe /C mkdir "+ruta);
//
//            BufferedReader brStdOut = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
//            BufferedReader brStdErr = new BufferedReader(new InputStreamReader(proceso.getErrorStream()));
//            String str=null;
//
//            while ((str = brStdOut.readLine())!=null){
//                salida = salida + str + "\n";
//            }
//            while ((str = brStdErr.readLine())!=null){
//                error = error + str + "\n";
//            }
//
//
//            brStdOut.close();
//            brStdErr.close();
//
//            proceso.destroy();
//        }catch(Exception e){
//            System.out.println(e.getMessage());
//        }
    }
}
