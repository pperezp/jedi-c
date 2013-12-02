/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases.jaudio.basico;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pato Pérez
 */
public class Reproductor extends Thread{
    private AudioFile[] archivosAudio;

    public Reproductor(AudioFile... listaArchivosAudio) {
        this.archivosAudio = listaArchivosAudio;
    }
    
    @Override
    public void run(){
        try {
            for(AudioFile af : archivosAudio){
                af.play(true);
                Thread.sleep(af.getMiliSegundos());
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Reproductor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void play(){
        this.start();
    }
    
    public long getMiliSegundos(){
        long ml = 0;
        for(AudioFile af : archivosAudio){
            ml += af.getMiliSegundos();
        }
        return ml;
    }
}
