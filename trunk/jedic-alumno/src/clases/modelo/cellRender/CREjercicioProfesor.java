/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases.modelo.cellRender;

import clases.modelo.EjercicioProfesor;
import clases.modelo.listModel.LMEjercicioProfesor;
import java.awt.Color;
import java.awt.Component;
import javax.swing.*;

/**
 *
 * @author Administrador
 */
public class CREjercicioProfesor implements ListCellRenderer{

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        
        LMEjercicioProfesor model = (LMEjercicioProfesor)list.getModel();
        EjercicioProfesor ejercicio = (EjercicioProfesor)model.getElementAt(index);
        java.awt.GridBagConstraints gridBagConstraints;
        JPanel p1 = new JPanel();
        JLabel lblIcon = new JLabel();
        JLabel lblNomEjer = new JLabel();
        JLabel lblFec = new JLabel();
        JTextArea tDes = new JTextArea();
        JScrollPane scrollTxtDes = new JScrollPane();
        p1.setBackground(new java.awt.Color(255, 255, 255));
        p1.setLayout(new java.awt.GridBagLayout());

        String rutaFoto;
        if(ejercicio.isVisto()){
            rutaFoto = "/imagenes/doc.png";
        }else{
            rutaFoto = "/imagenes/doc_no_visto.png";
        }
        lblIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource(rutaFoto))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        p1.add(lblIcon, gridBagConstraints);

        lblNomEjer.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblNomEjer.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblNomEjer.setText(ejercicio.getNombreEjercicio());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        p1.add(lblNomEjer, gridBagConstraints);

        lblFec.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblFec.setText("FECHA PLAZO: "+ejercicio.getFechaPlazo() + "              HORA PLAZO: "+ejercicio.getHoraPlazo());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        p1.add(lblFec, gridBagConstraints);

        tDes.setColumns(10);
        tDes.setEditable(false);
        tDes.setFont(new java.awt.Font("Verdana", 0, 13)); // NOI18N
        tDes.setRows(4);
        tDes.setText(ejercicio.getDescripcion());
        tDes.setWrapStyleWord(true);
        tDes.setLineWrap(true);
        scrollTxtDes.setViewportView(tDes);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        p1.add(scrollTxtDes, gridBagConstraints);
        
        if(isSelected){
            //new Color(88,179,225) -->BackGraound
            //Color.white -->foreground
            p1.setBackground(new Color(88,179,225));
            lblFec.setForeground(Color.white);
            lblFec.setBackground(new Color(88,179,225));
            lblNomEjer.setBackground(new Color(88,179,225));
            lblNomEjer.setForeground(Color.white);
        }else{
            //white -->BackGraound
            //black -->foreground
            p1.setBackground(Color.white);
            lblFec.setForeground(Color.black);
            lblFec.setBackground(Color.white);
            lblNomEjer.setBackground(Color.white);
            lblNomEjer.setForeground(Color.black);
        }
        if(cellHasFocus){
            //new Color(88,179,225) -->BackGraound
        }
        p1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        return p1;
    }
    
}
