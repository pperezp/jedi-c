package cl.archivo;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Fabiola
 */
public class Archivo extends File {

    public Archivo(String rutaArchivo) {
        super(rutaArchivo);
    }

    public void escribirEnAchivo(String texto, boolean sobreEscribir, boolean archivoSoloLectura) throws IOException {
        this.createNewFile();
        if (archivoSoloLectura) {
            this.setReadOnly();
        }
        PrintWriter archivoWriter;
        archivoWriter = new PrintWriter(new FileWriter(this, !sobreEscribir));
        archivoWriter.println(texto);
        if (archivoWriter != null) {
            archivoWriter.close();
        }
    }

    @Override
    public long length() {
        return sizeOf(this);
    }
    
    private long sizeOf(File archivo) {
        if (archivo.isFile()) {
            return archivo.length();
        } else {
            long contador = 0;
            for (File a : archivo.listFiles()) {
                contador += sizeOf(a);
            }
            return contador;
        }
    }
    
    public List<String> getArchivoComoString() throws IOException {
        List<String> palabras = new ArrayList<>();
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader reader = null;
        try {
            fis = new FileInputStream(this);
            isr = new InputStreamReader(fis);
            reader = new BufferedReader(isr);
            String linea;
            while ((linea = reader.readLine()) != null) {
                palabras.add(linea);
            }
        }finally {
            reader.close();
            isr.close();
            fis.close();
            return palabras;
        }
        
        
//        StringBuilder texto = new StringBuilder();
//        Scanner entrada = new Scanner(this);
//        
//        while(entrada.hasNext()){
//            texto.append(entrada.nextLine()).append("\n");
//        }
//        return texto.toString();
    }
    
//    public static void main(String[] args){
//        try {
//            Date d = new Date();
//            long time = d.getTime();
//            Archivo a = new Archivo("manifest.mf");
//            System.out.println(a.getArchivoComoString());
//            d = new Date();
//            long time1 = d.getTime();
//            System.out.println(time1-time);
//        } catch (IOException ex) {
//            Logger.getLogger(Archivo.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
}
