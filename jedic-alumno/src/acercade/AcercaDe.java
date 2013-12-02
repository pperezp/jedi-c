/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package acercade;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;

/**
 *
 * @author Pato
 */
public class AcercaDe extends javax.swing.JDialog implements java.awt.event.MouseListener, java.awt.event.ActionListener{
    private String nombreSW;
    private String autor;
    private String ano;
    private String descripcionDelPrograma;
    private String emailDeContacto;
    private GPL.Idioma idioma;
    private JLabel tituloAcercaDe;
    private JLabel lblHechoEnNetbeans;
    private JLabel panelLateral;
    private javax.swing.JTextArea licencia;
    private javax.swing.JScrollPane jScrollPane3;
    public static Rectangle PREFERED_SIZE = new Rectangle(590,430);
    
    /*
     Comentario
     */
    public AcercaDe(DatosAcercaDe dad){
        super();
        nombreSW = dad.getNombreDelPrograma();
        this.autor = dad.getAutor();
        this.descripcionDelPrograma = dad.getdescripcionCortaDelPrograma();
        this.ano = dad.getAno();
        this.emailDeContacto = dad.getEmailDeContacto();
        this.setTitle("Acerca de "+this.nombreSW);
        idioma = dad.getIdiomaLicencia();
        
        tituloAcercaDe = new JLabel();
        tituloAcercaDe.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        tituloAcercaDe.setText("Acerca de "+this.nombreSW);
        
        lblHechoEnNetbeans = new javax.swing.JLabel();
        lblHechoEnNetbeans.setIcon(new javax.swing.ImageIcon(getClass().getResource("/acercade/images/hechoEnNetbeans.png")));
        
        panelLateral = new javax.swing.JLabel();
        panelLateral.setIcon(new javax.swing.ImageIcon(getClass().getResource("/acercade/images/lateral.png")));
        
        licencia = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        
        licencia.setColumns(20);
        licencia.setLineWrap(true);
        licencia.setWrapStyleWord(true);
        licencia.setRows(5);
        licencia.setEditable(false);
        jScrollPane3.setViewportView(licencia);
        
         javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog1Layout.createSequentialGroup()
                .addComponent(panelLateral, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jDialog1Layout.createSequentialGroup()
                        .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(tituloAcercaDe, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE))
                        .addContainerGap())
                    .addComponent(lblHechoEnNetbeans)))
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelLateral, javax.swing.GroupLayout.PREFERRED_SIZE, 388, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialog1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tituloAcercaDe)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblHechoEnNetbeans))
        );
        redimensionarVentana();
        construirLicencia();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }
    
    public AcercaDe(DatosAcercaDe dad, int anchoVentana, int altoVentana){
        this(dad);
        PREFERED_SIZE = new Rectangle(anchoVentana, altoVentana);
        redimensionarVentana();
    }

    public void addComponent(javax.swing.JComponent c){
        c.addMouseListener(this);
        if(c instanceof javax.swing.JMenuItem){
            //poner f1 para presionar si es un jmenuitem
            javax.swing.JMenuItem mi = (javax.swing.JMenuItem)c;
            mi.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
            mi.addActionListener(this);
        }
    }

    private void redimensionarVentana() {
        this.setBounds(PREFERED_SIZE);
    }

    private void construirLicencia() {
        licencia.append("<"+this.nombreSW+" - "+this.descripcionDelPrograma+">\n");
        licencia.append("Copyright (C) <"+ano+"> <"+autor+">\n\n");
        String lic ;
        
        if(idioma == GPL.Idioma.ESPANOL){
            lic = GPL.ESPANOL;
            lic += "Si tienes alguna duda, solo envía un correo electronico a: "+this.emailDeContacto;
        }else if(idioma == GPL.Idioma.INGLES){
            lic = GPL.INGLES;
            lic += "If you have any questions, just send an email to: "+this.emailDeContacto;
        }else{
            lic = "ESPAÑOL: \n"+GPL.ESPANOL + "Si tiene alguna duda, solo envíe un correo electronico a:"+this.emailDeContacto+
                  "\n\nENGLISH: \n"+ GPL.INGLES + "If you have any questions, just send an email to: "+this.emailDeContacto;
        }
        licencia.append(lic);
        licencia.setCaretPosition(0);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mousePressed(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        this.setVisible(true);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseExited(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.setVisible(true);
    }
}
