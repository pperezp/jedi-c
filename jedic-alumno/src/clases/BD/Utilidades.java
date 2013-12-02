/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package clases.BD;

import clases.BD.mysql.Conexion;
import clases.utilidades.Mensaje;
import java.sql.*;

/**
 *
 * @author Administrador
 */
public class Utilidades implements java.io.Serializable{
    private String[] palabrasReservadas;


    public Utilidades(){
        inicializarPalabrasReservadas(26);
    }

    public boolean contienePalabraReservada(String texto){
        String[] palabras;
        if(texto.contains(",")){
            palabras = texto.split(",");
        }else{
            palabras = texto.split(" ");
        }

        for(int i=0; i<palabras.length ; i++){
            for(int j=0; j<palabrasReservadas.length ; j++){
                if(palabras[i].trim().equals(palabrasReservadas[j])){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     *
     * @param nom String
     *
     * @return Retorna VERDADERO si el String tiene este formato
     * "var1, var2, var3" y retorna FALSO si el String tiene este formato:
     * "var 1, var 2, var 3", osea espacio entre los nombres
     */
    public boolean variableEstaCorrecta(String nom){
        char[] nombre = nom.toCharArray();
        for(int i=1; i<nom.length() ;i++){
            if(nombre[i] == ' ' && nombre[i-1] != ','){
                return false;
            }
        }
        return true;
    }

    private void inicializarPalabrasReservadas(int cantidad){
        palabrasReservadas = new String[cantidad];

        palabrasReservadas[0] = "false";
        palabrasReservadas[1] = "char";
        palabrasReservadas[2] = "case";
        palabrasReservadas[3] = "int";
        palabrasReservadas[4] = "float";
        palabrasReservadas[5] = "double";
        palabrasReservadas[6] = "if";
        palabrasReservadas[7] = "else";
        palabrasReservadas[8] = "do";
        palabrasReservadas[9] = "while";
        palabrasReservadas[10] = "for";
        palabrasReservadas[11] = "switch";
        palabrasReservadas[12] = "short";
        palabrasReservadas[13] = "long";
        palabrasReservadas[14] = "default";
        palabrasReservadas[15] = "continue";
        palabrasReservadas[16] = "break";
        palabrasReservadas[17] = "sizeof";
        palabrasReservadas[18] = "struct";
        palabrasReservadas[19] = "return";
        palabrasReservadas[20] = "void";
        palabrasReservadas[21] = "bool";
        palabrasReservadas[22] = "true";
        palabrasReservadas[23] = "main";
        palabrasReservadas[24] = "printf";
        palabrasReservadas[25] = "scanf";
    }

    public boolean hayErrores(javax.swing.JFrame form, String texto, String nombre){
        if(!this.contienePalabraReservada(texto)){
            if(!texto.trim().equalsIgnoreCase("")){
                return false;
            }else{
                Mensaje.warning(nombre+"No puede dejar el texto en blanco");
                return true;
            }
        }else{
            Mensaje.warning(nombre+"El texto contiene palabras reservadas");
            return true;
        }
    }

    

//    public void conexion(String server, String db, String uid, String pwd){
//        try{
//            parametros = "jdbc:odbc:Driver={SQL Server};" +
//                    "server="+server+";"+
//                    "DataBase="+db+";"+
//                    "Uid="+uid+";"+
//                    "Pwd="+pwd+";";
//            // parametros = "jdbc:odbc:driver={SQL Server};server="+server+";DataBase="+db+";Uid="+uid+";Pwd="+pwd+";";
//            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
//            con = DriverManager.getConnection(parametros);
//        }catch(SQLException e1){
//            javax.swing.JOptionPane.showMessageDialog(null, "Error en la Conexion al Motor SQL","Error Conexion", 0);
//            System.exit(0);
//        }catch(Exception e2){
//            javax.swing.JOptionPane.showMessageDialog(null, "Error en la Conexion al Motor","Error Conexion", 0);
//            System.exit(0);
//        }
//    }

    public void dibujarTabla(javax.swing.JTable grilla, String[] encabezados,  String campos, String nombretabla){
        try{
            Conexion.sentencia = Conexion.con.createStatement();
            Conexion.rs = Conexion.sentencia.executeQuery("select count(0) as total from "+ nombretabla);
            int numero =0;
            if(Conexion.rs.next()){
                numero = Conexion.rs.getInt("total");
            }
            Conexion.sentencia.close();
            grilla.setModel(new javax.swing.table.DefaultTableModel(new Object[numero][encabezados.length], encabezados));
            llenarTabla(grilla, nombretabla, encabezados.length, campos);

        }catch(SQLException e1){
            javax.swing.JOptionPane.showMessageDialog(null, e1.getMessage(),"Error dibujado", 0);

        }
    }

    public void dibujarTabla(javax.swing.JTable grilla, String[] encabezados,  String campos, String nombretabla, String nt){
        try{
            Conexion.sentencia = Conexion.con.createStatement();
            Conexion.rs = Conexion.sentencia.executeQuery("select count(0) as total from "+ nt);
            int numero =0;
            if(Conexion.rs.next()){
                numero = Conexion.rs.getInt("total");
            }
            Conexion.sentencia.close();
            grilla.setModel(new javax.swing.table.DefaultTableModel(new Object[numero][encabezados.length], encabezados));
            llenarTabla(grilla, nombretabla, encabezados.length, campos);

        }catch(SQLException e1){
            javax.swing.JOptionPane.showMessageDialog(null, e1.getMessage(),"Error dibujado", 0);

        }
    }

    private void llenarTabla(javax.swing.JTable grilla, String nombretabla, int numero, String campos){
        try{
            Conexion.sentencia = Conexion.con.createStatement();
            Conexion.rs = Conexion.sentencia.executeQuery("select "+campos+" from "+nombretabla);
            int i=0;
            while(Conexion.rs.next()){
                for(int j=1;j<=numero;j++){
                    grilla.setValueAt(Conexion.rs.getString(j), i, (j-1));
                }
                i++;
            }
            Conexion.sentencia.close();
            grilla.setEnabled(true);
        }catch(SQLException e1){
            //javax.swing.JOptionPane.showMessageDialog(null, e1.getMessage(),"Error dibujado", 0);

        }catch(Exception e2){
            javax.swing.JOptionPane.showMessageDialog(null, "Error en llenado","Error dibujado", 0);

        }
    }

    public void llenarCombo(javax.swing.JComboBox combo, String nomtabla, String nomcampo){
        try{
            Conexion.sentencia = Conexion.con.createStatement();
            Conexion.rs = Conexion.sentencia.executeQuery("select "+nomcampo+" from "+nomtabla+";");
            combo.removeAllItems();
            combo.addItem("Seleccione...");
            while(Conexion.rs.next()){
                combo.addItem(Conexion.rs.getString(1));
            }
            combo.setSelectedIndex(0);
            Conexion.sentencia.close();
        }catch(SQLException e1){
            javax.swing.JOptionPane.showMessageDialog(null, "Error en llenado de combos SQL", "Error", 0);
        }catch(Exception e2){
            javax.swing.JOptionPane.showMessageDialog(null, "Error en llenado de combos", "Error", 0);
        }
    }

    public int obtenerIdentificador(String nomtabla, String campoid, String camponom, String itemseleccionado){
        try{
            Conexion.sentencia = Conexion.con.createStatement();
            String buscar;
            int id;
            buscar = "select "+campoid+" from "+nomtabla+" where "+camponom+" = "+itemseleccionado;
            Conexion.rs = Conexion.sentencia.executeQuery(buscar);
            if(Conexion.rs.next()){//preguntando si hay registros
                id = Conexion.rs.getInt(1);
                Conexion.sentencia.close();
                return id;
            }

        }catch(SQLException e1){
            javax.swing.JOptionPane.showMessageDialog(null, "Error al obtener identificador SQL: "+e1.getMessage(), "error", 0);
        }catch(Exception e2){
            javax.swing.JOptionPane.showMessageDialog(null, "Error al obtener identificador", "error", 0);
        }
        return -1;//por si no lo encontro
    }

}
