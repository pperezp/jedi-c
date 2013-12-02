/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases.excel;

import clases.excel.modelo.ArchivoExcel;
import clases.excel.modelo.HojaExcel;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 *
 * @author Administrador
 */
public class Excel {
    private static int filas = 0;
    private static int columnas = 0;
    private static Workbook wb;
    private static WritableWorkbook wwb ;
    private static Sheet hoja;
    private static WritableSheet wsh;
    private static Cell celda;
    
    /**
     * Método para obtener el contenido de una hoja en especifico
     * @param rutaExcel Ruta del archivo .xls
     * @param hoja Si este parametro debe ser >= 0 
     * @return Una matriz de Strings con el contenido de ese archivo en dicha hoja, o null si ocurrio algun error
     */
    public static HojaExcel leerArchivo(String rutaExcel, int numeroDeHoja){
        try {
            String[][] info = null ;
            HojaExcel hojaExcel;
            wb = Workbook.getWorkbook(new File(rutaExcel));
            hoja = wb.getSheet(numeroDeHoja);
            
            filas = hoja.getRows();
            columnas = hoja.getColumns();
            
            info = new String[filas][columnas];
            
            for(int i=0; i<filas; i++){
                for(int j=0; j<columnas; j++){
                    // es (columna,fila)
                    celda = hoja.getCell(j,i);
                    info[i][j] = celda.getContents();
                }
            }
            hojaExcel = new HojaExcel(hoja.getName(), info);
            return hojaExcel;
        } catch (IOException ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (BiffException ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     * Método para leer TODAS las hojas de un archivo de Excel
     * @param archivo
     * @return Una lista de HojasExcel o null si hay algun error
     */
    public static List<HojaExcel> leerArchivo(ArchivoExcel archivo){
        try {
            String[][] info = null ;
            List<HojaExcel> hojas = new ArrayList<HojaExcel>();
            
            wb = Workbook.getWorkbook(new File(archivo.getRuta()));
            for(int i=0; i<wb.getNumberOfSheets() ;i++){
                //Obtengo la hoja
                hoja = wb.getSheet(i);
                filas = hoja.getRows();
                columnas = hoja.getColumns();

                info = new String[filas][columnas];

                //Recorro la hoja para guardar el contenido
                for(int j=0; j<filas; j++){
                    for(int k=0; k<columnas; k++){
                        // es (columna,fila)
                        //lo guardo en una celda, y despues en la matriz
                        celda = hoja.getCell(k,j);
                        info[j][k] = celda.getContents();
                    }
                }
                //Agrego una HojaExcel a la lista
                hojas.add(new HojaExcel(hoja.getName(), info));
            }
            
            //Retorno las hojas
            return hojas;
        } catch (IOException ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (BiffException ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    /**
     * 
     * @param informacion Es una matriz de String con la informacion que quiere guardar en un archivo excel
     * @param rutaExcel La ruta para guardar el archivo
     */
//    public static void escribirArchivo(String[][] informacion, String rutaExcel) {
//        try {
//            /*
//             wwb = WritableWorkBook
//             wsh = WritableSheet
//             */
//            wwb = Workbook.createWorkbook(new File(rutaExcel));
//            wsh = wwb.createSheet("Hoja 1", 0);
//            
//            
//            for(int i =0; i<informacion.length; i++){
//                for(int j =0; j<informacion[i].length; j++){
//                    wsh.addCell(new jxl.write.Label(j, i, informacion[i][j]));
//                }
//            }
//            
//            wwb.write();
//            wwb.close();
//        } catch (IOException ex) {
//            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
//        } catch(Exception ex){
//            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    
    /**
     * 
     * @param archivo Un objeto ArchivoExcel para guardarlo
     */
    public static void escribirArchivo(ArchivoExcel archivo) {
        try {
            /*
             wwb = WritableWorkBook
             wsh = WritableSheet
             */
            HojaExcel hojaExcel;
            wwb = Workbook.createWorkbook(new File(archivo.getRuta()));
           
            for (int i = 0; i < archivo.getCantidadDeHojas(); i++) 
            {
                hojaExcel = archivo.getHojas().get(i);
                wsh = wwb.createSheet(hojaExcel.getNombre(), i);
                for (int j = 0; j < hojaExcel.getContenido().length; j++) {
                    for (int k = 0; k < hojaExcel.getContenido()[j].length; k++) {
                        wsh.addCell(new jxl.write.Label(k, j, hojaExcel.getContenido()[j][k]));
                    }
                }
            }
            wwb.write();
            wwb.close();
        } catch (IOException ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
        } catch(Exception ex){
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
