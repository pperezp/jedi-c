/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package clases.recuperarClave.mail;

import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author pato
 */
public class Mail {
    private static String mensaje = "";
    private static String asunto = "(sin asunto)";
    private static String mailEmisor;
    private static String claveMailEmisor;
    private static String nomEmisor = "";
    private static String MENSAJE_ERROR;


    public static boolean enviarMail(String mailReceptor){
        try {
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.setProperty("mail.smtp.starttls.enable", "true");
            props.setProperty("mail.smtp.port","25");//587

            
            Session conexionCorreo = Session.getDefaultInstance(props);
            Message mens = new MimeMessage(conexionCorreo);

            // los objetos Adress contienen informacion de recipientes de correo
            Address desde = new InternetAddress(mailEmisor,nomEmisor);
            Address para = new InternetAddress(mailReceptor);

            // asignamos el contenido. En este caso usamos 'text/plain' pero
            // es posible usar 'text/html' para enviar correos con contenido
            // HTML
            mens.setContent(mensaje,"text/html");
            // asignar el origen del correo
            mens.setFrom(desde);
            // asignar un destinatario
            mens.setRecipient(Message.RecipientType.TO, para);
            // asignar el asunto del correo
            mens.setSubject(asunto);
//            mens.setText(mensaje);

            Transport t = conexionCorreo.getTransport("smtp");
            t.connect(mailEmisor,claveMailEmisor);
            t.sendMessage(mens,mens.getAllRecipients());
            t.close();
            return true;
        } catch (UnsupportedEncodingException | MessagingException ex) {
            setMensajeError(ex.getMessage());
            return false;
        }
    }

    /**
     * @return the mensaje
     */
    public static String getMensaje() {
        return mensaje;
    }

    /**
     * @param aMensaje the mensaje to set
     */
    public static void setMensaje(String aMensaje) {
        mensaje = aMensaje;
    }

    /**
     * @return the asunto
     */
    public static String getAsunto() {
        return asunto;
    }

    /**
     * @param aAsunto the asunto to set
     */
    public static void setAsunto(String aAsunto) {
        asunto = aAsunto;
    }


    public static void setMailEmisor(String mail, String contraseña, String nombreEmisor){
        mailEmisor = mail;
        claveMailEmisor = contraseña;
        nomEmisor = nombreEmisor;
    }

    private static void setMensajeError(String message) {
        MENSAJE_ERROR = message;
    }

    /**
     * @return the MENSAJE_ERROR
     */
    public static String getMensajeError() {
        return MENSAJE_ERROR;
    }
}
