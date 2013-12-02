/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package clases.generator;


import clases.utilidades.Hora;
import clases.utilidades.Mensaje;
import clases.BD.Utilidades;
import clases.principal.JedicAlumno;
import clases.utilidades.Fecha;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

/**
 *
 * @author Patricio Pérez Pinto
 */
public class Codigo implements java.io.Serializable{
    private int id;
    private String nombre;
    private String codigo;
    private String[] ruta;
    private String firma;
    private boolean guardado;
    private Utilidades u;
    private int indice;
    private int iTab;

    private String[] funciones = new String[100];
    public static final String COMIEZO_MAIN = "\n\nint main(void){\n";
    public static final String FIN_MAIN = "\n    return 0;\n}\n\n";
    public static final String IFS = "    if(){\n\n    }\n";
    private String fors = "    for(;;){\n\n    }\n";
    private String forsx2 = "    for(;;){\n        for(;;){\n\n        "
            + "}\n    }";
    public static final String WHILES = "    while(){\n\n    }\n";
    public static final String DO_WHILES = "    do{\n\n    }while();\n";
    public static final String IF_ELSES = "    if(){\n\n    }else{\n\n    }\n";
    public static final String SWITCHES = "    switch(){\n";
    public static final String END_SWITCHES = "    }\n";
    public String variables;
    public static final String PRINTF = "    printf(\"\\n\");\n";
    public static final String SYSTEM_PAUSE = "    system(\"pause\");\n";
    public static final String SYSTEM_PAUSE_SLEEP = "    system(\"sleep 5\");\n";
    public static final  String SYSTEM_CLS = "    system(\"cls\");\n";
    public static final String GETCHE = "    getche();\n";
    public static final String SCANF = "    scanf(\""+(char)37+"\",&);\n";
    public static final String INICIO_ESTRUCTURA = "typedef struct ";
    public static final String FIN_ESTRUCTURA = "};\n";
    private String librerias;

    private int cfun;
    private int clib;
    private final int RUTA_CON_EXTENSION = 0;
    private final int RUTA_SIN_EXTENSION = 1;

    /**
     * Constructor que permite construir una Codificación nueva
     * @param nombre
     * Nombre que recibira el Código creado
     *
     * @param version
     * Version del Programa
     *
     * @param mes
     * Mes de Modificación del Programa
     *
     * @param m_ejecutar
     * Botón del menú ejecutar, para dejarlo deshabilitado 
     * al momento de construir un Código nuevo
     * 
     * @param btn_eje
     * Botón ejecutar, para dejarlo deshabilitado al momento
     * de construir un Código nuevo
     */
    public Codigo(int id, String nombre, boolean isLibreria){
        String firmaLibreria = null;
        this.id = id;

        if(id == -1){
            this.nombre = nombre;
        }else{
            this.nombre = nombre + id;
        }

        if(isLibreria){
            firmaLibreria = "\n#ifndef _"+nombre.toUpperCase()+"_H_\n#define _"+nombre.toUpperCase()+"_H_\n\n//ESCRIBA ACÁ SUS FUNCIONES\n\n#endif";
        }

        firma = "//JEDI C\n//Fecha: "+Fecha.getFecha(false, false)+"\n\n";
        codigo = (isLibreria ? firma+firmaLibreria : firma);

        if(!isLibreria){
            codigo +=   "#include <stdio.h>\n"
                       +"#include <stdlib.h>\n"
                       +"#include <conio.h>"
                +COMIEZO_MAIN+"\n"+GETCHE+FIN_MAIN.replaceFirst("\n", "");
        }

        guardado = true;
        librerias = "";
        ruta = new String[2];
        ruta[RUTA_CON_EXTENSION] = null;
        ruta[RUTA_SIN_EXTENSION] = null;
        u = new Utilidades();

        cfun = 0;
        clib = 0;
        indice = -1;
    }

    public Codigo(){

    }

    //Comenzado SETS
   /**
    *
    * @param rutaConExtension
    * Será el nuevo Nombre del Código y esta constituído
    * por la ruta del Archivo del código mas la Extensión
    *
    */
    public void setNombre(String rutaConExtension, boolean ruta){
        if(ruta){
            for(int i=rutaConExtension.length()-1;i>=0;i--){
                if(rutaConExtension.charAt(i) == '\\' || rutaConExtension.charAt(i) == '/'){
                    rutaConExtension = rutaConExtension.substring(i+1);
                    break;
                }
            }
        }
        nombre = rutaConExtension;
    }

    /**
     * Este método permite crear una nueva Firma para el Código,
     * la cual será visualizada como comentario en la codificación C
     * <br><br>
     * Ejemplo:
     * <blockquote><pre>
     * //Autocodificacion v1.0
     * //Programado en Java - Agosto 2010
     * //Fecha: dd/mm/aa
     * </pre></blockquote>
     *
     * @param version
     * Versión del Programa
     * @param mes
     * Mes de Modificación del Programa
     * @param año
     * Año de Moficicación del Programa
     *
     */
//    public void setNuevaFirma(String version, String mes, String año){
//        firma = "//Autocodificacion v"+version+"\n//Programado en Java - "
//                + ""+mes+" "+año+"\n//Fecha: "+fecha.getFecha()+"\n\n";
//    }

    /**
     * El código existente, será reemplazado por el parámetro "codigoNuevo"
     * @param codigoNuevo
     * Nuevo código que reemplazará al código existente
     */
    public void setCodigo(String codigoNuevo){
        codigo = codigoNuevo;
    }

    /*
    private void setPath(String[] newPath){
        path[0] = newPath[0];
        path[1] = newPath[1];
    }*/

    /*public void setPathNull(String newPath){
        path[0] = newPath;
        path[1] = newPath;
    }*/

    /**
     * Método que permite darle un nuevo valor al contador de Funciones.
     * El contador de Funciones, permite posteriormente, darle cuerpo a las 
     * Mismas, sabiendo cuantas funciones son, gracias al contador.
     * @param nuevoValor
     * Nuevo valor del contador de funciones
     */
    public void setContadorFunciones(int nuevoValor){
        cfun = nuevoValor;
    }

    /**
     * Método que permite guardar los nombres de las variables de un mismo
     * tipo.
     * @param nuevasVariables
     * Nombre de las Nuevas variables
     */
    public void setVariables(String nuevasVariables){
        variables = nuevasVariables;
    }

    /**
     * Método que permite guardar la estructura de una función
     * @param indice
     * Indica la posición en la cual se quiere guardar dicha Función
     * @param funcion
     * Estructura de una Función
     */
    public void setFuncion(int indice, String funcion){
        funciones[indice] = funcion;
    }

    /**
     * Método que permite Borrar las librerías ingresadas en el Código
     */
    public void borrarLibrerias(){
        librerias = "";
    }

    public void setContadorLibrerias(int nuevoValor){
        clib = nuevoValor;
    }

    public void setFor(For f){
        fors = "    for( "+f.getVar()+" = "+f.getValorInicial()+" ; "
                + ""+f.getVar()+" "+f.getIntermedio()+" "+f.getValorLlegada()+
                " ; "+ ""+f.getVar()+" = "+f.getVar()+" "+f.getIncremento()
                +" ){\n\n    }\n";
    }

    public void setForsx2(For f1, For f2) {
        forsx2 = "    for( "+f1.getVar()+" = "+f1.getValorInicial()+" ; "
                + ""+f1.getVar()+" "+f1.getIntermedio()+" "+f1.getValorLlegada()
                +" ; "+ ""+f1.getVar()+" = "+f1.getVar()+" "+f1.getIncremento()
                +" ){\n        "+ "for( "+f2.getVar()+" = "+f2.getValorInicial()
                +" ; "+ ""+f2.getVar()+" "+f2.getIntermedio()+" "
                +f2.getValorLlegada()+" ; "+ ""+f2.getVar()+" = "+f2.getVar()
                +" "+f2.getIncremento()+" ){\n\n        }\n    }";
    }

    //Comenzando GETS
    public String getVariables(){
        return variables;
    }

    public String getFirma(){
        return firma;
    }

    public int getContadorFunciones(){
        return cfun;
    }

    public String getPathConExtension(){
        return ruta[RUTA_CON_EXTENSION];
    }

    public String getPathSinExtension(){
        return ruta[RUTA_SIN_EXTENSION];
    }

    public String getFuncion(int indice){
        return funciones[indice];
    }

    public String getCodigo(){
        return codigo;
    }

    public String getLibrerias(){
        return librerias;
    }

    public String getNombre(){
        return nombre;
    }

    //Comenzando otros Métodos
    public void insertarEnCodigo(
            String cod,
            RSyntaxTextArea areaDeTexto
            , javax.swing.JMenuItem m_ejecutar
            , javax.swing.JMenuItem m_guardar
            , javax.swing.JTabbedPane tabconte
        )
    {

        if(!tabconte.getSelectedComponent().getName().equalsIgnoreCase("Página de Inicio")){
            if(getIndice() != -1){
                String ant, media, fin;

                ant = codigo.substring(0, getIndice());
                media = cod;
                fin = codigo.substring( getIndice(), codigo.length());

                codigo = ant + media + fin;
                areaDeTexto.setText(codigo);
                guardado = false;
                m_ejecutar.setEnabled(false);
                if(ruta[RUTA_CON_EXTENSION] != null){
                    m_guardar.setEnabled(true);
                }
                indice = getIndice() + cod.length();
            }else{
                codigo = codigo + cod;
                areaDeTexto.setText(codigo);
                guardado = false;
                m_ejecutar.setEnabled(false);
                if(ruta[RUTA_CON_EXTENSION] != null){
                    m_guardar.setEnabled(true);
                }
            }


            String nom = getNombre();
                if(!nom.contains("*")){
                    setNombreString("*"+getNombre());
                    tabconte.setTitleAt(tabconte.getSelectedIndex(), getNombre());
                }
            areaDeTexto.setSelectionStart(getIndice());
            areaDeTexto.setSelectionEnd(getIndice());
        }
    }

    /*public void insertarEnCodigoConCursor(
            String cod,
            int indice,
            RSyntaxTextArea areaDeTexto
            , javax.swing.JMenuItem m_ejecutar
            , javax.swing.JMenuItem m_guardar
            , javax.swing.JButton btn_eje
        )
    {

        String ant, media, fin;

        ant = codigo.substring(0, indice);
        media = cod;
        fin = codigo.substring(indice, codigo.length());

        codigo = ant + media + fin;
        areaDeTexto.setText(codigo);
        guardado = false;
        m_ejecutar.setEnabled(false);
        btn_eje.setEnabled(false);
        if(path[0] != null){
            m_guardar.setEnabled(true);
        }
    }*/

    public void insertarVariablesDeMenu(
            String tipo,
            RSyntaxTextArea areaDeTexto,
            javax.swing.JMenuItem m_ejecutar, 
            javax.swing.JMenuItem m_guardar,
            javax.swing.JTabbedPane tabconte
        )
    {
        variables = Mensaje.mostrarMensaje("Variables "+tipo,
                "Ingrese el nombre de la variable:");
        if(!u.contienePalabraReservada(variables)){
            if(u.variableEstaCorrecta(variables)){
                if(!variables.equalsIgnoreCase("")){
                    variables = "    "+tipo+" " + variables + ";\n";
                    insertarEnCodigo(variables, areaDeTexto, m_ejecutar,
                            m_guardar,tabconte);
                }else{
                    Mensaje.warning("Ingrese alguna Variable");
                    insertarVariablesDeMenu(tipo, areaDeTexto, m_ejecutar,
                            m_guardar,tabconte);
                }
            }else{
                Mensaje.warning("Las variables no pueden tener espacios");
                insertarVariablesDeMenu(tipo, areaDeTexto, m_ejecutar,
                       m_guardar , tabconte);
            }

        }else{
            Mensaje.warning("Las Variables contienen palabras reservadas");
            insertarVariablesDeMenu(tipo, areaDeTexto, m_ejecutar,
                    m_guardar, tabconte);
        }
    }

    public void insertarSwitchCase(
            int numeroDeCases,
            boolean esDoWhilex2,
            javax.swing.JTextField txt_cases,
            RSyntaxTextArea areaDeTexto,
            javax.swing.JMenuItem m_ejecutar,
            javax.swing.JMenuItem m_guardar,
            javax.swing.JTabbedPane tabconte){

        if(esDoWhilex2){
            insertarEnCodigo("        switch(op){\n", areaDeTexto, m_ejecutar,
                    m_guardar, tabconte);
            for(int i=0;i<numeroDeCases;i++){
                insertarEnCodigo("            case "+(i+1)+":{\n\n          "
                        + "      break;\n            }", areaDeTexto,
                        m_ejecutar, m_guardar,  tabconte);
            }
            insertarEnCodigo("\n        }\n", areaDeTexto, m_ejecutar,
                    m_guardar, tabconte);
            txt_cases.setText("");
            txt_cases.requestFocus();
        }else{
            insertarEnCodigo(SWITCHES,areaDeTexto, m_ejecutar,
                    m_guardar, tabconte);
            for(int i=0;i<numeroDeCases;i++){
                insertarEnCodigo("        case "+(i+1)+":{\n\n       "
                        + "     break;\n        }", areaDeTexto, m_ejecutar,
                        m_guardar, tabconte);
            }
            insertarEnCodigo(END_SWITCHES,areaDeTexto, m_ejecutar,
                    m_guardar,  tabconte);
            txt_cases.setText("");
            txt_cases.requestFocus();
        }
    }

    public void insertarMenu(
        javax.swing.JTextField txt_cases,
        RSyntaxTextArea areaDeTexto,
        javax.swing.JMenuItem m_ejecutar,
        javax.swing.JMenuItem m_guardar,
            javax.swing.JTabbedPane tabconte
            )
    {
        int num_op = 0;
        try{
            num_op = Integer.parseInt(Mensaje.mostrarMensaje("Estructura de Menu",
                    "Ingrese la cantidad de opciones de su Menú:"));
            insertarEnCodigo("   int op;\n", areaDeTexto, m_ejecutar,
                    m_guardar, tabconte);
            insertarEnCodigo("   do{\n", areaDeTexto, m_ejecutar,
                    m_guardar,  tabconte);
            insertarEnCodigo("        do{\n", areaDeTexto, m_ejecutar,
                    m_guardar,  tabconte);
            insertarEnCodigo("            system(\"cls\");\n", areaDeTexto,
                    m_ejecutar, m_guardar,  tabconte);
            insertarEnCodigo("            "
                    + "printf(\"\\n  MENU PRINCIPAL \");\n", areaDeTexto,
                    m_ejecutar, m_guardar,  tabconte);
            insertarEnCodigo("            "
                    + "printf(\"\\n================== \");\n", areaDeTexto,
                    m_ejecutar, m_guardar,  tabconte);
            
            for(int i=0;i<num_op-1;i++){
                insertarEnCodigo("            "
                        + "printf(\"\\n"+(i+1)+". \");\n", areaDeTexto,
                        m_ejecutar, m_guardar,  tabconte);
            }
            
            insertarEnCodigo("            printf(\"\\n"+num_op+". Salir \");\n"
                    , areaDeTexto, m_ejecutar, m_guardar,  tabconte);
            insertarEnCodigo("            printf(\"\\nOPC: \");\n"
                    , areaDeTexto, m_ejecutar, m_guardar,  tabconte);
            insertarEnCodigo("            scanf(\"%d\", &op);\n"
                    , areaDeTexto, m_ejecutar, m_guardar,  tabconte);
            insertarEnCodigo("        }while(op < 1 || op > "+num_op+");\n",
                    areaDeTexto, m_ejecutar, m_guardar,  tabconte);
            
            insertarSwitchCase(num_op, true, txt_cases,
                    areaDeTexto, m_ejecutar, m_guardar,  tabconte);
            insertarEnCodigo("    }while(op != "+num_op+");\n\n",
                    areaDeTexto, m_ejecutar, m_guardar,  tabconte);
        }catch(NumberFormatException e){
            Mensaje.warning("Solo debe Ingresar Números");
            insertarMenu(txt_cases, areaDeTexto, m_ejecutar,
                    m_guardar,  tabconte);
        }
    }

    public void insertarLibreria(String lib){
        if(clib != 0){
            librerias = librerias + "#include <"+lib+">\n";
        }else{
            librerias = "#include <"+lib+">\n";
            clib++;
        }
    }


    public void guardarComo(javax.swing.JMenuItem m_guardar){
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter
                ("Archivo .c ", "c");
        FileNameExtensionFilter filter2 = new FileNameExtensionFilter
                ("Archivo .cpp  ", "cpp");
        fileChooser.addChoosableFileFilter(filter);
        fileChooser.addChoosableFileFilter(filter2);
        fileChooser.setApproveButtonText("Guardar Codificación");
        fileChooser.setAcceptAllFileFilterUsed(false);
        int seleccion = fileChooser.showSaveDialog(null);
        try {
            if (seleccion == JFileChooser.APPROVE_OPTION) {
                File fichero = fileChooser.getSelectedFile();

                ruta[RUTA_CON_EXTENSION] = fichero.getPath()+ fileChooser.getFileFilter().
                        getDescription().substring(8, 12).trim();
                ruta[RUTA_SIN_EXTENSION] = fichero.getPath();
                FileWriter fw = new FileWriter(ruta[0]);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter salida = new PrintWriter(bw);
                salida.println(codigo);
                salida.close();
                guardado = true;
                m_guardar.setEnabled(false);
                this.setNombre(ruta[RUTA_SIN_EXTENSION], true);
            }else{
                Mensaje.OPCION = Mensaje.CANCELAR;
            }
        } catch(java.io.IOException ioex) {
            System.out.println("Error: "+ioex.toString());
        }
    }

    public void guardar(javax.swing.JMenuItem m_guardar) {
        try{
            FileWriter fw = new FileWriter(ruta[RUTA_CON_EXTENSION]);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter salida = new PrintWriter(bw);
            salida.println(codigo);
            salida.close();
            this.modificarCodigoGuardado(true);
            this.setNombreString(this.getNombre().substring(1));
        }catch(IOException e){
        }
        m_guardar.setEnabled(false);
    }

    public void abrirArchivo(
            RSyntaxTextArea cod,
            javax.swing.JMenuItem m_ejecutar,
            javax.swing.JMenuItem m_guardar,
            javax.swing.JTabbedPane tabconte
            )
    {

        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter
                ("Archivo .c ", "c");
        FileNameExtensionFilter filter2 = new FileNameExtensionFilter
                ("Archivo .cpp  ", "cpp");
        fileChooser.addChoosableFileFilter(filter);
        fileChooser.addChoosableFileFilter(filter2);
        fileChooser.setApproveButtonText("Abrir Código");
        fileChooser.setAcceptAllFileFilterUsed(false);
        int seleccion = fileChooser.showOpenDialog(null);
        try{
            if (seleccion == JFileChooser.APPROVE_OPTION){
               guardado = true;
               borrarCodigo(false, cod);
               File fichero = fileChooser.getSelectedFile();
               ruta[RUTA_CON_EXTENSION] = fichero.getPath();
               ruta[RUTA_SIN_EXTENSION] = fichero.getPath();

               BufferedReader reader = new BufferedReader(new
                       FileReader(fichero));
               String linea = reader.readLine();
               while (linea != null){
                   insertarEnCodigo(linea+"\n", cod, m_ejecutar,
                           m_guardar,tabconte);
                   linea = reader.readLine();
               }
               setNombre(this.getPathConExtension(), true);
               
               /*itemSeleccionado = cbo_codigos.getSelectedIndex();
               cbo_codigos.removeItemAt(itemSeleccionado);

               cbo_codigos.insertItemAt(getNombre(), itemSeleccionado);
               cbo_codigos.setSelectedIndex(itemSeleccionado);*/
            }
        }catch(IOException e){

        }
    }

    public void borrarCodigo(){
        codigo = null;
    }

    public void borrarCodigo(boolean codigoConFirma, RSyntaxTextArea cod){
        if(codigoConFirma){
            codigo = firma;
        }else{
            codigo = "";
        }
        cod.setText(codigo);
        ruta[RUTA_CON_EXTENSION] = null;
        ruta[RUTA_SIN_EXTENSION] = null;
        indice = -1;
    }

    public void modificarCodigoGuardado(boolean estadoGuardado){
        guardado = estadoGuardado;
    }

    public boolean codigoGuardado(){
        return guardado;
    }

    public void compilar(
            javax.swing.JMenuItem m_ejecutar,
            javax.swing.JMenuItem m_guardar,
            boolean hayQueEjecutarElArchivo, javax.swing.JTextArea sali)
    {
        System.out.println("COMPILAR");
        String res;
        if(getPathConExtension() != null){
            if(!guardado){
                FileWriter fw;
                try {
                    fw = new FileWriter(getPathConExtension());
                    BufferedWriter bw = new BufferedWriter(fw);
                    PrintWriter salida = new PrintWriter(bw);
                    salida.println(getCodigo());
                    salida.close();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
            res = Ejecutar.ejecutarComandoGcc(getPathConExtension(),
                    getPathSinExtension());
        }else{
            guardarComo(m_guardar);
            res = Ejecutar.ejecutarComandoGcc(getPathConExtension(),
                    getPathSinExtension());
        }


        if(res.equalsIgnoreCase("")){

            for(int i=ruta[RUTA_SIN_EXTENSION].length()-1;i>=0;i--){
                if(ruta[RUTA_SIN_EXTENSION].charAt(i) == '.'){
                    ruta[RUTA_SIN_EXTENSION] = ruta[RUTA_SIN_EXTENSION].substring(0, i);
                }
            }

            sali.setForeground(Color.blue);
            sali.setText(Hora.getHoraActual()+" - La compliación de Realizó con Éxito\nArchivo: "+
                    ruta[RUTA_SIN_EXTENSION]+(Ejecutar.nombreSO.contains("windows") || Ejecutar.nombreSO.contains("Windows")?".exe":".out"));
            if(hayQueEjecutarElArchivo){
                JedicAlumno.dialogCompilacion.setLocationRelativeTo(null);
                JedicAlumno.textoDialogo.setText("Ejecutando...");
                JedicAlumno.dialogCompilacion.setVisible(true);
                Ejecutar.abrirExplorador(ruta[RUTA_SIN_EXTENSION]);
                JedicAlumno.dialogCompilacion.setVisible(false);
            }
            m_ejecutar.setEnabled(true);
            guardado = true;
        }else{
            sali.setForeground(Color.red);
            sali.setText(Hora.getHoraActual()+" - "+res);
        }
    }

    public void mostrarCodigoEnJTextArea(RSyntaxTextArea cod){
        cod.setText(codigo);
    }

    /**
     * @return the indice
     */

    /**
     * @param indice the indice to set
     */
    public void setIndice(int indice) {
        this.indice = indice;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the iTab
     */
    public int getiTab() {
        return iTab;
    }

    /**
     * @param iTab the iTab to set
     */
    public void setiTab(int iTab) {
        this.iTab = iTab;
    }

    public void setPath(String rutaConExtension, String rutaSinExtension){
        ruta[RUTA_CON_EXTENSION] = rutaConExtension;
        ruta[RUTA_SIN_EXTENSION] = rutaSinExtension;
    }

    public void setNombreString(String nombre){
        this.nombre = nombre;
    }

    /**
     * @return the fors
     */
    public String getFor() {
        return fors;
    }

    /**
     * @return the forsx2
     */
    public String getForsx2() {
        return forsx2;
    }

    /**
     * @return the indice
     */
    public int getIndice() {
        return indice;
    }
}
