/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ayuda.modelo;

import xml.analizador.dom.modelo.Tag;

/**
 *
 * @author pato
 */
public class Componente {
    private String nombre;
    private String definicion;
    private String ejemplo;

    public Componente(Tag tagComponente){
        nombre = tagComponente.getValorDeAtributo("id");
        Tag def = tagComponente.getTagHijo("definicion", Tag.Cantidad.primeraOcurrencia).get(0);
        definicion = def.getContenido();
        Tag eje = tagComponente.getTagHijo("ejemplo", Tag.Cantidad.primeraOcurrencia).get(0);
        ejemplo = eje.getContenido();
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDefinicion() {
        return definicion;
    }

    public void setDefinicion(String definicion) {
        this.definicion = definicion;
    }

    public String getEjemplo() {
        return ejemplo;
    }

    public void setEjemplo(String ejemplo) {
        this.ejemplo = ejemplo;
    }
    
    @Override
    public String toString(){
        return this.nombre;
    }
}
