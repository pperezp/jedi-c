/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.modelo;

import javax.swing.ImageIcon;
import javax.swing.text.SimpleAttributeSet;

/**
 *
 * @author Patricio Pérez Pinto
 */
public class Cliente implements java.io.Serializable{
    private String nombre;
    private String nick;
    private String nombreParaVentana;
    private int codigo;
    private SimpleAttributeSet estilo;
    private javax.swing.ImageIcon imagen;
    
    public Cliente(int codigo, String nombre, String nick, ImageIcon imagen, SimpleAttributeSet estilo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.nick = nick;
        this.imagen = imagen;
        nombreParaVentana = nick + " <"+nombre+">";
        this.estilo = estilo;
    }

    public Cliente(){
    
    }
    
    /*Usado para crear ClienteDesconectado*/
    public Cliente(int codigo, String nombre, String nick){
        this.codigo = codigo;
        this.nombre = nombre;
        this.nick = nick;
        nombreParaVentana = nick + " <"+nombre+">";
    }
    
    public Cliente(int codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    /*Usado Para construir MensajeCliente*/
    public Cliente(int codigo, String nombre, String nick, SimpleAttributeSet estilo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.nick = nick;
        this.estilo = estilo;
        nombreParaVentana = nick + " <"+nombre+">";
    }


    public String getNombreParaVentana() {
        return nombreParaVentana;
    }

    public SimpleAttributeSet getEstilo() {
        return estilo;
    }

    public void setEstilo(SimpleAttributeSet estilo) {
        this.estilo = estilo;
    }

    
    
    public void setNombreParaVentana(String nombreParaVentana) {
        this.nombreParaVentana = nombreParaVentana;
    }

    
    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public ImageIcon getImagen() {
        return imagen;
    }

    public void setImagen(ImageIcon imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
    
}
