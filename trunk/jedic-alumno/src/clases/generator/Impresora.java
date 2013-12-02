/*
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases.generator;

import java.awt.*;

public class Impresora {

    Font fuente = new Font("Dialog", Font.PLAIN, 10);
    PrintJob pj;
    Graphics[] pagina = new Graphics[500];
    int j = 0;
    int c2 = 0;
    int cont = 0;
    int cont2 = 0;

    public Impresora() {
        pj = Toolkit.getDefaultToolkit().getPrintJob(new Frame(), "SCAT", null);
    }

    public void imprimir(javax.swing.JTable tab) {
        try {
            pagina[cont] = pj.getGraphics();
            pagina[cont].setFont(fuente);
            pagina[cont].setColor(Color.black);

            int y = 50;
            int x = 50;
            //linea
            pagina[cont].drawLine(50, y, 700, y);
            y += 15;

            //titulos
            for (int i = 0; i < tab.getColumnCount(); i++) {
                pagina[cont].drawString(tab.getColumnName(i), x, y);
                if (tab.getColumnName(i).equalsIgnoreCase("articulo")) {
                    x += 350;
                } else if (tab.getColumnName(i).equalsIgnoreCase("nombre")) {
                    x += 200;
                } else {
                    x += 90;
                }
            }//end titulos
            y += 15;

            //linea
            pagina[cont].drawLine(50, y, 700, y);
            y += 15;

            //llenando tabla
            for (int i = 0; i < tab.getRowCount(); i++) {
                cont2++;
                if (cont2 != 30) {
                    x = 50;
                    for (int j = 0; j < tab.getColumnCount(); j++) {
                        pagina[cont].drawString(tab.getValueAt(i, j).toString(), x, y);
                        if (tab.getColumnName(j).equalsIgnoreCase("articulo")) {
                            x += 350;
                        } else if (tab.getColumnName(j).equalsIgnoreCase("nombre")) {
                            x += 200;
                        } else {
                            x += 90;
                        }
                    }
                    y += 15;
                } else {
                    cont2 = 0;
                    pagina[cont].dispose();
                    cont++;
                    pagina[cont] = pj.getGraphics();
                    pagina[cont].setFont(fuente);
                    pagina[cont].setColor(Color.black);
                    x = 50;
                    y = 50;

                    //linea
                    pagina[cont].drawLine(50, y, 700, y);
                    y += 15;

                    //titulos
                    for (int k = 0; k < tab.getColumnCount(); k++) {
                        pagina[cont].drawString(tab.getColumnName(k), x, y);
                        if (tab.getColumnName(k).equalsIgnoreCase("articulo")) {
                            x += 350;
                        } else if (tab.getColumnName(k).equalsIgnoreCase("nombre")) {
                            x += 200;
                        } else {
                            x += 90;
                        }
                    }//end titulos
                    y += 15;

                    //linea
                    pagina[cont].drawLine(50, y, 700, y);
                    y += 15;


                    x = 50;
                    for (int j = 0; j < tab.getColumnCount(); j++) {
                        pagina[cont].drawString(tab.getValueAt(i, j).toString(), x, y);
                        if (tab.getColumnName(j).equalsIgnoreCase("articulo")) {
                            x += 350;
                        } else if (tab.getColumnName(j).equalsIgnoreCase("nombre")) {
                            x += 200;
                        } else {
                            x += 90;
                        }
                    }
                    y += 15;
                }

            }//end llenando tablas

            pagina[cont].dispose();

            pj.end();

        } catch (Exception e) {
            System.out.println("LA IMPRESION HA SIDO CANCELADA...");
        }
    }


    public void imprimir(String cod) {
        String[] c = cod.split("\n");
        try {
            pagina[j] = pj.getGraphics();
            pagina[j].setFont(fuente);
            pagina[j].setColor(Color.black);

            int y = 50;
            int x = 50;


            for (int i = 0; i < c.length; i++) {
                c2++;
                if(c2 != 61){
                    pagina[j].drawString(c[i], x, y);
                    y += 12;
                }else{
                    c2 = 0;
                    pagina[j].dispose();
                    j++;

                    pagina[j] = pj.getGraphics();
                    pagina[j].setFont(fuente);
                    pagina[j].setColor(Color.black);
                    y = 50;
                    x = 50;

                    pagina[j].drawString(c[i], x, y);
                    y += 12;

                }
                
            }
            pagina[j].dispose();
            pj.end();
        } catch (Exception e) {
            System.out.println("LA IMPRESION HA SIDO CANCELADA...");
        }
    }
}

