/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases.utilidades;

/**
 *
 * @author Patricio Pérez Pinto
 */
public class HiloCopiar extends Thread{
    private String origen;
    private String destino;
    private boolean isOculto;
    
    public HiloCopiar(String origen, String destino, boolean isOculto){
        this.origen = origen;
        this.destino = destino;
        this.isOculto = isOculto;
    }
    @Override
    public void run(){
        Copiar.copiar(origen, destino, isOculto);
    }
}
