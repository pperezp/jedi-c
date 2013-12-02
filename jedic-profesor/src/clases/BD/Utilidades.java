package clases.BD;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author Administrador
 */
import java.sql.*;
import clases.BD.mysql.Conexion;
import clases.BD.mysql.DatosConexion;

public class Utilidades {
    public javax.swing.JOptionPane m;

    public static void dibujarTabla(javax.swing.JTable grilla, String[] encabezados,  String campos, String nombretabla){
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

    public static void dibujarTabla(javax.swing.JTable grilla, String[] encabezados,  String campos, String nombretabla, String nt){
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

    private static void llenarTabla(javax.swing.JTable grilla, String nombretabla, int numero, String campos){
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

    public static void llenarCombo(javax.swing.JComboBox combo, String nomtabla, String nomcampo){
        try{
            Conexion.sentencia = Conexion.con.createStatement();
            Conexion.rs = Conexion.sentencia.executeQuery("select "+nomcampo+" from "+nomtabla+";");
            combo.removeAllItems();
//            combo.addItem("Seleccione...");
            while(Conexion.rs.next()){
                combo.addItem(Conexion.rs.getString(1));
            }
            combo.setSelectedIndex(0);
            Conexion.sentencia.close();
        }catch(SQLException e1){
//            javax.swing.JOptionPane.showMessageDialog(null, "Error en llenado de combos SQL", "Error", 0);
        }catch(Exception e2){
//            javax.swing.JOptionPane.showMessageDialog(null, "Error en llenado de combos", "Error", 0);
        }
    }

    public static int obtenerIdentificador(String nomtabla, String campoid, String camponom, String itemseleccionado){
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
