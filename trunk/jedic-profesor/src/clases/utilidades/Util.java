/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases.utilidades;

/**
 *
 * @author Administrador
 */
public class Util {
    public static final int DIA = 2;
    public static final int MES = 1;
    public static final int ANO = 0;
    public static String getHora(String fecha){
        int HORA = 1;
        String[] fec = fecha.split(" ");
        int h = Integer.parseInt(fec[HORA].substring(0, 2));
        int mi = Integer.parseInt(fec[HORA].substring(3, 5));
        
        return (h == 0?"00":h)+":"+(mi == 0 ?"00":mi);
    }
    
    public static int[] getFecha(String fecha){
        int FECHA = 0;
        int[] f = new int[3];
        String[] fec = fecha.split(" ");
        f[ANO] = Integer.parseInt(fec[FECHA].substring(0, 4));
        f[MES] = Integer.parseInt(fec[FECHA].substring(5, 7));
        f[DIA] = Integer.parseInt(fec[FECHA].substring(8, 10));
        
        return f;
    }
    
    public static String getFechaBonita(String fechaConHora){
        int[]f = getFecha(fechaConHora);
        switch(f[MES]){
            case 1: {
                return f[2]+" de enero de "+f[0];
            }
            case 2: {
                return f[2]+" de febrero de "+f[0];
            }
            case 3: {
                return f[2]+" de marzo de "+f[0];
            }
            case 4: {
                return f[2]+" de abril de "+f[0];
            }
            case 5: {
                return f[2]+" de mayo de "+f[0];
            }
            case 6: {
                return f[2]+" de junio de "+f[0];
            }
            case 7: {
                return f[2]+" de julio de "+f[0];
            }
            case 8: {
                return f[2]+" de agosto de "+f[0];
            }
            case 9: {
                return f[2]+" de septiembre de "+f[0];
            }
            case 10: {
                return f[2]+" de octubre de "+f[0];
            }
            case 11: {
                return f[2]+" de noviembre de "+f[0];
            }
            case 12: {
                return f[2]+" de diciembre de "+f[0];
            }
        }
        return null;
    }
}
