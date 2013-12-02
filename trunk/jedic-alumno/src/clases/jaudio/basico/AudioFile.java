/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases.jaudio.basico;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import javax.sound.sampled.*;

/**
 *
 * @author Pato Pérez
 */
public class AudioFile extends File implements Runnable{
    private AudioInputStream source;
    private DataLine.Info info;
    private Clip clip;
    private long miliSegundos;

    public AudioFile(URI uri) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        super(uri);
        construirClip();
    }

    public AudioFile(File parent, String child) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        super(parent, child);
        construirClip();
    }

    public AudioFile(String parent, String child) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        super(parent, child);
        construirClip();
    }

    public AudioFile(String pathname) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        super(pathname);
        construirClip();
    }
    
    public Thread play(boolean playAhora)  {
        
        Thread hilo = new Thread(this);
        if(playAhora) hilo.start();
        return hilo;
    }

    @Override
    public void run() {
       clip.start();
    }
    
    public void stop(){
        clip.stop();
        clip.close();
    }
    
    public Clip getClip(){
        return clip;
    }
    
    public long getMiliSegundos() {
        return miliSegundos;
    }

    public void setMiliSegundos(long miliSegundos) {
        this.miliSegundos = miliSegundos;
    }

    private void construirClip() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        source = AudioSystem.getAudioInputStream(this);
        info = new DataLine.Info(Clip.class, source.getFormat());
        clip = (Clip) AudioSystem.getLine(info);
        clip.open(source);
        this.miliSegundos = clip.getMicrosecondLength() / 1000;
    }
    
    
    
}
