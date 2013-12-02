/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases.recuperarClave;


import clases.BD.Conexion;
import clases.recuperarClave.mail.Mail;
import clases.utilidades.Historial;
import clases.vista.mensajes.MensajeHilo;
import java.awt.Color;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

/**
 *
 * @author Patricio Pérez Pinto
 */
public class HiloRecuperarClave extends Thread {
    private JLabel lblMenSesion;
    private JLabel lblMen;
    private String consulta;
    private String campoEmail;
    private String campoNombre;
    private String campoPass;
    private String nombreEnte;
    private String nombreUser;

    /**
     *
     * @param lblMenSesion
     * Label que alberga los mensajes de informacion o error
     * @param lblMen
     * Label que alberga el mensaje de enviando o procesando
     */
    public HiloRecuperarClave(JLabel lblMenSesion, JLabel lblMen) {
        this.lblMenSesion = lblMenSesion;
        this.lblMen = lblMen;
    }

    /**
     *
     * @param consulta
     * "SELECT ema_pro, nom_pro, pas_pro FROM profesor WHERE usu_pro = '" + usu_pro + "'"
     * @param campoEmail
     * @param campoNombre
     * @param campoPass
     * @param nombreEnte
     * PROFESOR, ALUMNO O DIRECTOR
     */
    public void setDatos(String consulta, String campoEmail, String campoNombre, String campoPass, String nombreEnte, String nombreUsuarioDirector){
        this.consulta = consulta;
        this.campoEmail = campoEmail;
        this.campoNombre = campoNombre;
        this.campoPass = campoPass;
        this.nombreEnte = nombreEnte;
        this.nombreUser = nombreUsuarioDirector;
    }

    @Override
    public void run() {
        lblMen.setText("Procesando");
        
        try {
            Conexion.sentencia = Conexion.con.createStatement();
            Conexion.rs = Conexion.sentencia.executeQuery(consulta);
            if (Conexion.rs.next()) {
                lblMen.setText("Enviando");
                String email = Conexion.rs.getString(campoEmail);
                String nom = Conexion.rs.getString(campoNombre);
                String pass = Conexion.rs.getString(campoPass);
                String fecha = Conexion.rs.getString("fecha");
                
                Mail.setAsunto("[JEDIC] RECUPERACIÓN CLAVE "+nombreEnte.toUpperCase());
                Mail.setMailEmisor("generator.noreplay@gmail.com", "07091988", "JEDIC NO-REPLAY");
                Mail.setMensaje("Estimado/a " + nom + ":<br/><br/>"
                        + "Usted ha solicitado la recuperación de su clave con fecha <b>"+fecha+"</b> .<br/><br/>"
                        + "<center>**************************************<br/>"
                        + "<b>CLAVE ACTUAL: " + pass + "</b><br/>"
                        + "**************************************</center>"
                        + "<br/><br/><center><img src='https://dl.dropbox.com/s/1nn1x4flhryyfuv/new_200.png'/><br><b><i>Muchas Gracias por utilizar JEDIC</i></b></center>");
                
                if(Mail.enviarMail(email)){
                    MensajeHilo.setMensaje(lblMenSesion, "La contraseña se ha enviado a su correo, Gracias", Color.BLUE, 7);
                    lblMen.setText("Enviado!");
                    Historial.addHistorialGeneral("Un administrador solicitó la recuperación de su contraseña y "
                    + "se ha enviado un correo con la información. [administrador="+nom+",  email="+email+"]");
                    Thread.sleep(1000);
                }else{
                    MensajeHilo.setMensaje(lblMenSesion, "Error al enviar correo: "+Mail.getMensajeError(), Color.red, 7);
                    lblMen.setText("Error");
                    Thread.sleep(1000);
                }
                
            } else {
                MensajeHilo.setMensaje(lblMenSesion, "Nombre de Usuario no válido", Color.red, 7);
                Historial.addHistorialGeneral("Un administrador solicitó la recuperación de "
                        + "su contraseña pero ha ingresado mal su nombre de usuario. [user="+nombreUser+"]");
            }
            Conexion.sentencia.close();
        } catch (SQLException | InterruptedException ex) {
            if(ex instanceof SQLException){
                Logger.getLogger(HiloRecuperarClave.class.getName()).log(Level.SEVERE, null, ex);
            }else{
                System.out.println("Hilo recuperar clave interrumpido");
            }
        }
        
    }
}
