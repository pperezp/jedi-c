/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * JedicAlumno.java
 *
 * Created on 03-nov-2011, 16:45:40
 */
package chat.aplicacion;
import javax.swing.event.ChangeEvent;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;
import chat.modelo.JPanelFondo;
import chat.archivo.Abrir;
import chat.archivo.InputOutputObject;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import chat.mensajes.Mensaje;
import chat.modelo.CellRendererConIcono;
import chat.modelo.CellRendererListaFonts;
import chat.modelo.CellRendererSalas;
import chat.modelo.cliente.ClienteDesconectado;
import chat.modelo.cliente.ClienteNuevo;
import chat.modelo.ConexionExitosa;
import chat.modelo.mensajes.MensajeCliente;
import chat.modelo.mensajes.MensajeConexion;
import chat.modelo.peticiones.PeticionActualizarImagen;
import chat.modelo.respuestas.RespuestaPeticionActualizarImagen;
import chat.modelo.Cliente;
import chat.modelo.Estilo;
import chat.modelo.InicioSesion;
import chat.modelo.ModeloListaContactos;
import chat.modelo.ModeloListaFonts;
import chat.modelo.ModeloListaSalas;
import chat.modelo.Sala;
import chat.modelo.cliente.ClienteDejoDeEscribir;
import chat.modelo.cliente.ClienteDestino;
import chat.modelo.cliente.ClienteEscribiendo;
import chat.modelo.cliente.ClienteNuevoSala;
import chat.modelo.cliente.ClienteOrigen;
import chat.modelo.peticiones.AbandonarSala;
import chat.modelo.peticiones.PeticionCerrarSala;
import chat.modelo.peticiones.PeticionEntrarSala;
import chat.modelo.peticiones.QuieroMisSalas;
import chat.modelo.respuestas.ClienteCambioSala;
import chat.modelo.respuestas.EliminarSala;
import chat.modelo.respuestas.EnvioListas;
import chat.modelo.respuestas.ListaClientesSala;
import chat.modelo.respuestas.ListaDeMisSalas;
import chat.modelo.respuestas.PassSalaIncorrecta;
import chat.modelo.respuestas.RemoverUsuarioDeSala;
import chat.modelo.respuestas.SalaYaExiste;
import clases.utilidades.Cambiar;
import clases.utilidades.Historial;
import java.awt.*;
import javax.swing.*;
        
/**
 *
 * @author Patricio Pérez Pinto
 */
public class AplicacionCliente extends javax.swing.JFrame {
    private Socket socket;
    private List<Ventana> ventanas;
    private Cliente yo;
    private final String RUTA_AVATAR_POR_DEFECTO = "/chat/imagenes/cliente.png";
    private final String RUTA_CONF = "config"+File.separator+"chat"+File.separator+"clientes"+File.separator;
//    private List<Sala> misSalas;
    private String salaActual;
    
    private static String SERVIDOR;
    private static String PUERTO;
    private static String NOMBRE;
    private static String NICK;
    private static int CODIGO_ALU;
    /** Creates new form JedicAlumno */
    public AplicacionCliente() {
        System.out.println("entro al constructor");
        initComponents();
        System.out.println("paso el initComponents");
        yo = new Cliente();
        listaDeContactos.setName("lista");
//        pf = new JPanelFondo(null);
        yo.setImagen(new ImageIcon(getClass().getResource(RUTA_AVATAR_POR_DEFECTO)));
//        pf.setImage(imagenLocal.getImage());
//        pf.repaint();
        
        panelFoto.add(new JPanelFondo(yo.getImagen().getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
        panelFoto.getComponent(0).repaint();
        ventanas = new ArrayList<>();
//        clientes = new ArrayList<Cliente>();
        
        formChat.setBounds(0, 0, 500, 400);
        formChat.setLocationRelativeTo(null);
        tabContenedor.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        listaDeContactos.setCellRenderer(new CellRendererConIcono());
        listaDeContactos.setModel(new ModeloListaContactos());
        listaSalas.setModel(new ModeloListaSalas());
        this.setBounds(0,0,800,500);
//        this.setBounds(0,0,500,300);
        this.setLocationRelativeTo(null);
//        java.awt.Font[] allFonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts();
//        
//        for(Font f: allFonts){
//            System.out.println("Familia: "+f.getFamily());
//            System.out.println("Font Name: "+f.getFontName());
//        }
        listaFonts.setModel(new ModeloListaFonts());
//        listaFonts.setCellRenderer(new CellRendererListaFonts());
        listaSalas.setCellRenderer(new CellRendererSalas());
        formFonts.setBounds(0, 0, 850, 600);
        formFonts.setLocationRelativeTo(null);
//        formFonts.setVisible(true);
        

        color.setColor(Color.black);
        
        //        listaFonts.setSelectedIndex(3);
//        previsualizarFont();
        color.getSelectionModel().addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
               previsualizarFont();
            }
        });
        lblInicio.setVisible(false);
        salaActual = "lobi";
        listaDeContactos.setBorder(javax.swing.BorderFactory.createTitledBorder("Sala Actual: "+salaActual.toUpperCase()));
        System.out.println("setvisible true");
        this.setVisible(true);
        System.out.println("entrando al btn");
        btConectarActionPerformed(null);
        Cambiar.iconoDeFormulario(this, "/imagenes/iconoJCHAT.png");
//        pnlFoto.setBounds(0, 0, 200, 200);
//        ponerFondoAJtextArea();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        formModelo = new javax.swing.JFrame();
        splitPrincipal = new javax.swing.JSplitPane();
        pnlFotos = new javax.swing.JPanel();
        foto1 = new javax.swing.JPanel();
        foto2 = new javax.swing.JPanel();
        lblEscribiendo = new javax.swing.JLabel();
        jSplitPane1 = new javax.swing.JSplitPane();
        splitMensajes = new javax.swing.JSplitPane();
        pnlMsn = new javax.swing.JPanel();
        scrollMsn = new javax.swing.JScrollPane();
        msn = new javax.swing.JTextArea();
        scrollChat = new javax.swing.JScrollPane();
        chating = new javax.swing.JTextArea();
        nombreRemoto = new javax.swing.JLabel();
        formChat = new javax.swing.JFrame();
        tabContenedor = new javax.swing.JTabbedPane();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        menuFoto1 = new javax.swing.JMenuItem();
        menuFont1 = new javax.swing.JMenuItem();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        formFonts = new javax.swing.JFrame();
        jSplitPane6 = new javax.swing.JSplitPane();
        jSplitPane7 = new javax.swing.JSplitPane();
        jSplitPane9 = new javax.swing.JSplitPane();
        jSplitPane8 = new javax.swing.JSplitPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        textoEjemplo = new javax.swing.JTextPane();
        jButton2 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        negrita = new javax.swing.JCheckBox();
        cursiva = new javax.swing.JCheckBox();
        jLabel3 = new javax.swing.JLabel();
        tamanio = new javax.swing.JSpinner();
        color = new javax.swing.JColorChooser();
        jScrollPane3 = new javax.swing.JScrollPane();
        listaFonts = new javax.swing.JList();
        formMisSalas = new javax.swing.JFrame();
        jScrollPane6 = new javax.swing.JScrollPane();
        listaMisSalas = new javax.swing.JList();
        jButton1 = new javax.swing.JButton();
        jSplitPane2 = new javax.swing.JSplitPane();
        jSplitPane3 = new javax.swing.JSplitPane();
        jSplitPane4 = new javax.swing.JSplitPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        chat = new javax.swing.JTextArea();
        mensajeParaTodos = new javax.swing.JTextField();
        jSplitPane5 = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        panelFoto = new javax.swing.JPanel();
        cboOpciones = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        btConectar = new javax.swing.JButton();
        lblInicio = new javax.swing.JLabel();
        lblLogo = new javax.swing.JLabel();
        jSplitPane10 = new javax.swing.JSplitPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        listaDeContactos = new javax.swing.JList();
        jScrollPane5 = new javax.swing.JScrollPane();
        listaSalas = new javax.swing.JList();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuAcciones = new javax.swing.JMenu();
        menuFoto = new javax.swing.JMenuItem();
        menuFont = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        menuCrearSala = new javax.swing.JMenuItem();
        menuCerrarSala = new javax.swing.JMenuItem();
        menuAbandonar = new javax.swing.JMenuItem();

        formModelo.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formModeloComponentResized(evt);
            }
        });
        formModelo.getContentPane().setLayout(new java.awt.BorderLayout(0, 3));

        splitPrincipal.setDividerLocation(100);
        splitPrincipal.setEnabled(false);

        pnlFotos.setBackground(new java.awt.Color(255, 255, 255));

        foto1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        foto1.setLayout(new java.awt.BorderLayout());

        foto2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        foto2.setLayout(new java.awt.BorderLayout());

        lblEscribiendo.setFont(new java.awt.Font("Tahoma", 3, 12));
        lblEscribiendo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEscribiendo.setText("Escribiendo...");
        lblEscribiendo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblEscribiendo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        javax.swing.GroupLayout pnlFotosLayout = new javax.swing.GroupLayout(pnlFotos);
        pnlFotos.setLayout(pnlFotosLayout);
        pnlFotosLayout.setHorizontalGroup(
            pnlFotosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFotosLayout.createSequentialGroup()
                .addGroup(pnlFotosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lblEscribiendo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(foto1, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                    .addComponent(foto2, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlFotosLayout.setVerticalGroup(
            pnlFotosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFotosLayout.createSequentialGroup()
                .addComponent(foto1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblEscribiendo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 183, Short.MAX_VALUE)
                .addComponent(foto2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        splitPrincipal.setLeftComponent(pnlFotos);

        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        splitMensajes.setDividerLocation(260);
        splitMensajes.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        pnlMsn.setLayout(new java.awt.BorderLayout());

        msn.setColumns(20);
        msn.setRows(5);
        scrollMsn.setViewportView(msn);

        pnlMsn.add(scrollMsn, java.awt.BorderLayout.CENTER);

        splitMensajes.setBottomComponent(pnlMsn);

        chating.setColumns(20);
        chating.setRows(5);
        scrollChat.setViewportView(chating);

        splitMensajes.setLeftComponent(scrollChat);

        jSplitPane1.setRightComponent(splitMensajes);

        nombreRemoto.setBackground(new java.awt.Color(255, 255, 255));
        nombreRemoto.setFont(new java.awt.Font("Tahoma", 1, 18));
        nombreRemoto.setText("Nombre");
        nombreRemoto.setOpaque(true);
        jSplitPane1.setLeftComponent(nombreRemoto);

        splitPrincipal.setRightComponent(jSplitPane1);

        formModelo.getContentPane().add(splitPrincipal, java.awt.BorderLayout.CENTER);

        formChat.setTitle("Chat");
        formChat.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formChatWindowActivated(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formChatWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formChatWindowOpened(evt);
            }
        });
        formChat.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formChatComponentResized(evt);
            }
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formChatComponentShown(evt);
            }
        });
        formChat.getContentPane().add(tabContenedor, java.awt.BorderLayout.CENTER);

        jMenu1.setText("Acciones");

        menuFoto1.setText("Cambiar Foto");
        menuFoto1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuFoto1ActionPerformed(evt);
            }
        });
        jMenu1.add(menuFoto1);

        menuFont1.setText("Cambiar Estilo de Letra");
        menuFont1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuFont1ActionPerformed(evt);
            }
        });
        jMenu1.add(menuFont1);

        jMenuBar2.add(jMenu1);

        formChat.setJMenuBar(jMenuBar2);

        formFonts.setTitle("Escoge el Estilo de Letra");
        formFonts.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formFontsComponentResized(evt);
            }
        });

        jSplitPane6.setDividerLocation(200);

        jSplitPane7.setDividerLocation(330);
        jSplitPane7.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        jSplitPane9.setDividerLocation(45);
        jSplitPane9.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        jSplitPane8.setDividerLocation(31);
        jSplitPane8.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        textoEjemplo.setFont(new java.awt.Font("Tahoma", 0, 14));
        textoEjemplo.setText("Escriba aquí para probar");
        jScrollPane4.setViewportView(textoEjemplo);

        jSplitPane8.setLeftComponent(jScrollPane4);

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 18));
        jButton2.setText("Aplicar Estilo");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jSplitPane8.setRightComponent(jButton2);

        jSplitPane9.setRightComponent(jSplitPane8);

        negrita.setFont(new java.awt.Font("Tahoma", 1, 24));
        negrita.setText("Negrita");
        negrita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                negritaActionPerformed(evt);
            }
        });
        jPanel3.add(negrita);

        cursiva.setFont(new java.awt.Font("Tahoma", 2, 24));
        cursiva.setText("Cursiva");
        cursiva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cursivaActionPerformed(evt);
            }
        });
        jPanel3.add(cursiva);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24));
        jLabel3.setText("Tamaño:");
        jPanel3.add(jLabel3);

        tamanio.setModel(new javax.swing.SpinnerNumberModel(14, 8, 78, 1));
        tamanio.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tamanioStateChanged(evt);
            }
        });
        jPanel3.add(tamanio);

        jSplitPane9.setTopComponent(jPanel3);

        jSplitPane7.setRightComponent(jSplitPane9);
        jSplitPane7.setLeftComponent(color);

        jSplitPane6.setRightComponent(jSplitPane7);

        listaFonts.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        listaFonts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                listaFontsMouseReleased(evt);
            }
        });
        jScrollPane3.setViewportView(listaFonts);

        jSplitPane6.setLeftComponent(jScrollPane3);

        formFonts.getContentPane().add(jSplitPane6, java.awt.BorderLayout.CENTER);

        formMisSalas.setTitle("Cerrar Salas");

        listaMisSalas.setBorder(javax.swing.BorderFactory.createTitledBorder("Mis Salas"));
        listaMisSalas.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane6.setViewportView(listaMisSalas);

        formMisSalas.getContentPane().add(jScrollPane6, java.awt.BorderLayout.CENTER);

        jButton1.setText("Cerra Sala Seleccionada");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        formMisSalas.getContentPane().add(jButton1, java.awt.BorderLayout.PAGE_END);

        setTitle("JChat");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });

        jSplitPane2.setDividerLocation(330);

        jSplitPane3.setDividerLocation(160);
        jSplitPane3.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane3.setEnabled(false);

        jSplitPane4.setDividerLocation(180);
        jSplitPane4.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        chat.setColumns(20);
        chat.setEditable(false);
        chat.setLineWrap(true);
        chat.setRows(5);
        jScrollPane2.setViewportView(chat);

        jSplitPane4.setTopComponent(jScrollPane2);

        mensajeParaTodos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                mensajeParaTodosKeyReleased(evt);
            }
        });
        jSplitPane4.setRightComponent(mensajeParaTodos);

        jSplitPane3.setRightComponent(jSplitPane4);

        jSplitPane5.setDividerLocation(140);
        jSplitPane5.setEnabled(false);

        jPanel1.setLayout(new java.awt.BorderLayout());

        panelFoto.setBackground(new java.awt.Color(255, 255, 255));
        panelFoto.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelFoto.setLayout(new java.awt.BorderLayout());
        jPanel1.add(panelFoto, java.awt.BorderLayout.CENTER);

        cboOpciones.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Opciones", "Cambiar Imagen", "Cambiar Estilo de Letra", "----------SALAS------------", "Crear Sala", "Mis Salas", "Abandonar Sala Actual", "------------------------------", "Cerrar Sesión" }));
        cboOpciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboOpcionesActionPerformed(evt);
            }
        });
        jPanel1.add(cboOpciones, java.awt.BorderLayout.PAGE_END);

        jSplitPane5.setLeftComponent(jPanel1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "JChat", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        btConectar.setBackground(new java.awt.Color(255, 255, 255));
        btConectar.setText("Conectar!");
        btConectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btConectarActionPerformed(evt);
            }
        });
        jToolBar1.add(btConectar);

        jPanel2.add(jToolBar1);

        lblInicio.setBackground(new java.awt.Color(255, 255, 255));
        lblInicio.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblInicio.setForeground(new java.awt.Color(0, 0, 255));
        lblInicio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblInicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/chat/imagenes/inicio.gif"))); // NOI18N
        lblInicio.setText("CONECTANDO, TENGA PACIENCIA");
        lblInicio.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblInicio.setOpaque(true);
        lblInicio.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel2.add(lblInicio);

        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/logo jchat.png"))); // NOI18N
        jPanel2.add(lblLogo);

        jSplitPane5.setRightComponent(jPanel2);

        jSplitPane3.setLeftComponent(jSplitPane5);

        jSplitPane2.setLeftComponent(jSplitPane3);

        jSplitPane10.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        listaDeContactos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        listaDeContactos.setToolTipText("");
        listaDeContactos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                listaDeContactosMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(listaDeContactos);

        jSplitPane10.setRightComponent(jScrollPane1);

        listaSalas.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        listaSalas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                listaSalasMouseReleased(evt);
            }
        });
        jScrollPane5.setViewportView(listaSalas);

        jSplitPane10.setLeftComponent(jScrollPane5);

        jSplitPane2.setRightComponent(jSplitPane10);

        getContentPane().add(jSplitPane2, java.awt.BorderLayout.CENTER);

        menuAcciones.setText("Acciones");

        menuFoto.setText("Cambiar Foto");
        menuFoto.setEnabled(false);
        menuFoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuFotoActionPerformed(evt);
            }
        });
        menuAcciones.add(menuFoto);

        menuFont.setText("Cambiar Estilo de Letra");
        menuFont.setEnabled(false);
        menuFont.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuFontActionPerformed(evt);
            }
        });
        menuAcciones.add(menuFont);

        jMenuBar1.add(menuAcciones);

        jMenu2.setText("Salas");

        menuCrearSala.setText("Crear Sala");
        menuCrearSala.setEnabled(false);
        menuCrearSala.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCrearSalaActionPerformed(evt);
            }
        });
        jMenu2.add(menuCrearSala);

        menuCerrarSala.setText("Mis Salas...");
        menuCerrarSala.setEnabled(false);
        menuCerrarSala.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCerrarSalaActionPerformed(evt);
            }
        });
        jMenu2.add(menuCerrarSala);

        menuAbandonar.setText("Abandonar Sala Actual");
        menuAbandonar.setEnabled(false);
        menuAbandonar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuAbandonarActionPerformed(evt);
            }
        });
        jMenu2.add(menuAbandonar);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void btConectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btConectarActionPerformed
    if(btConectar.getText().equalsIgnoreCase("Conectar!")){
//        try {
                try {
                    /*Estilo: aca es cuando en un futuro tengo que leer de un archivo para 
                     * rescatar el objeto de estilo
                     * 
                     */
                    yo = (Cliente)InputOutputObject.leerObjetoDesde(RUTA_CONF+NOMBRE+".cliente");
                    cargarImagen();
                    System.out.println("leido el objeto");
                    yo.setNick(NICK);
                    yo.setNombreParaVentana(yo.getNick()+" <"+yo.getNombre()+">");
                } catch (FileNotFoundException ex) {
                    //Si no encuentra el archivo de conf, es porque nunca habia iniciado sesion
                    //asi que se cargan los valores por defecto del cliente
                    SimpleAttributeSet at = new SimpleAttributeSet();
                    StyleConstants.setFontFamily(at, Font.SANS_SERIF);
                    StyleConstants.setFontSize(at, 14);
                    StyleConstants.setForeground(at, Color.black);
                    StyleConstants.setItalic(at, true);
                    StyleConstants.setBold(at, true);
                    /*-----------------------------------*/
                    yo.setEstilo(at);
                    yo.setNombre(NOMBRE);
                    yo.setNick(NICK);
                    yo.setNombreParaVentana(yo.getNick()+" <"+yo.getNombre()+">");
                    yo.setCodigo(CODIGO_ALU);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(AplicacionCliente.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex){
                    Logger.getLogger(AplicacionCliente.class.getName()).log(Level.SEVERE, null, ex);
                }
            
            
            /*Iniciando el hilo de conexion*/
            new Thread(new Runnable() {

                    @Override
                    public void run() {
                        try {
                            System.out.println("iniciado el hilo conexion");
                            lblLogo.setVisible(false);
                            lblInicio.setForeground(Color.blue);
                            lblInicio.setText("CONECTANDO, TENGA PACIENCIA");
                            lblInicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/chat/imagenes/inicio.gif")));
                            lblInicio.setVisible(true);
                            System.out.println("iniciando el socket");
                            socket = new Socket(SERVIDOR, Integer.parseInt(PUERTO));
                            System.out.println("Servidor encontrado!");
                            new HiloRecibirObjeto().start();
                            System.out.println("Hilo Iniciado!");
                            Historial.addHistorial("Ha entrado a JCHAT");
                            ObjectOutputStream o = new ObjectOutputStream(socket.getOutputStream());
                            o.writeObject(new InicioSesion(yo));
                            System.out.println("yo enviado!");
                            mensajeParaTodos.requestFocus();
                            lblLogo.setVisible(true);
                        } catch(ConnectException e){
                            btConectar.setText("Conectar!");
                            lblInicio.setForeground(Color.red);
                            lblInicio.setText("ERROR AL CONECTAR");
                            lblInicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/chat/imagenes/error2.gif")));
                        } catch (UnknownHostException ex) {
                            Logger.getLogger(AplicacionCliente.class.getName()).log(Level.SEVERE, null, ex);
                            btConectar.setText("Conectar!");
                            lblInicio.setForeground(Color.red);
                            lblInicio.setText("ERROR AL CONECTAR");
                            lblInicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/chat/imagenes/error2.gif")));
                        } catch (IOException ex) {
                            Logger.getLogger(AplicacionCliente.class.getName()).log(Level.SEVERE, null, ex);
                            btConectar.setText("Conectar!");
                            lblInicio.setForeground(Color.red);
                            lblInicio.setText("ERROR AL CONECTAR");
                            lblInicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/chat/imagenes/error2.gif")));
                        }
                        
                    }
                }).start();
            
            btConectar.setText("Cancelar");
//            btConectar.setText("Desconectar");
            
            this.setTitle("Chat de: "+yo.getNombreParaVentana());
//        } catch (SocketException ex) {
//            System.out.println("1.- "+ex.getMessage());
//        } catch (UnknownHostException ex) {
//            System.out.println("2.- "+ex.getMessage());
//        } catch (IOException ex) {
//            System.out.println("3.- "+ex.getMessage());
//        }
    }else{
        try {
            Historial.addHistorial("Ha salido de JCHAT");
            lblInicio.setVisible(false);
            formFonts.setVisible(false);
            cboOpciones.setEnabled(false);
            menuFoto.setEnabled(false);
            menuFont.setEnabled(false);
            menuCrearSala.setEnabled(false);
            menuCerrarSala.setEnabled(false);
            menuAbandonar.setEnabled(false);
            
            removerUsuarios(listaDeContactos);
            removerUsuarios(listaSalas);
            cerrarVentanas();
            chat.setText("");
            InputOutputObject.escribirObjetoEn(yo, RUTA_CONF+yo.getNombre()+".cliente");
            ObjectOutputStream o = new ObjectOutputStream(socket.getOutputStream());
            o.writeObject(new ClienteDesconectado(yo.getCodigo(), yo.getNombre(), yo.getNick(), salaActual));
            this.setTitle("JChat");
        } catch (IOException ex) {
            Logger.getLogger(AplicacionCliente.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                socket.close();
            } catch (IOException ex) {
//                Logger.getLogger(AplicacionCliente.class.getName()).log(Level.SEVERE, null, ex);
                
            }
        }
        btConectar.setText("Conectar!");
    }
}//GEN-LAST:event_btConectarActionPerformed

private void mensajeParaTodosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_mensajeParaTodosKeyReleased
    if(evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER){
         ObjectOutputStream o  = null;
        try {
            MensajeCliente men;
            ClienteOrigen cliOrigen = new ClienteOrigen(yo.getCodigo(), yo.getNombre(), yo.getNick(), yo.getEstilo());
            men = new MensajeCliente(mensajeParaTodos.getText(), cliOrigen, true, salaActual);
            o = new ObjectOutputStream(socket.getOutputStream());
            o.writeObject(men);
            mensajeParaTodos.setText("");
            mensajeParaTodos.requestFocus();
        } catch (IOException ex) {
            Logger.getLogger(AplicacionCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch(NullPointerException e){
            mensajeParaTodos.setText("Pinche un usuario");
            mensajeParaTodos.selectAll();
            mensajeParaTodos.requestFocus();
        }
    }
}//GEN-LAST:event_mensajeParaTodosKeyReleased

private void listaDeContactosMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaDeContactosMouseReleased
    
    if(evt.getClickCount() == 2){
        ModeloListaContactos model = (ModeloListaContactos)listaDeContactos.getModel(); 
        Cliente contactoSeleccionado = model.getClienteAt(listaDeContactos.getSelectedIndex());
        String nombreContactoSeleccionado = contactoSeleccionado.getNombre();
        if(!nombreContactoSeleccionado.equals(yo.getNombre())){
            if(!isClienteAbierto(contactoSeleccionado.getNombreParaVentana())){
                System.out.println(contactoSeleccionado.getNombreParaVentana());
                    ClienteOrigen clienteOrigen = new ClienteOrigen(yo.getCodigo(), yo.getNombre(), yo.getNick(), yo.getEstilo());
                    ClienteDestino clienteDestino = new ClienteDestino(contactoSeleccionado.getCodigo(), contactoSeleccionado.getNombre(), contactoSeleccionado.getNick(), contactoSeleccionado.getEstilo());
                    Ventana v = new Ventana(clienteOrigen, clienteDestino);
                    ventanas.add(v);
                    javax.swing.JPanel p = new JPanel(new java.awt.BorderLayout());
                    p.add(v.splitPrincipal);
                    tabContenedor.add(v.getNombreRemoto(),p);
                    Pestana pestana = new Pestana(tabContenedor);
                    tabContenedor.setTabComponentAt(tabContenedor.getTabCount()-1,pestana);
                    ponerImagenRemotaEnVentanaChat(contactoSeleccionado);
                    formChat.setTitle("Chat de "+yo.getNick());
                    formChat.setVisible(true);
                    
                    v.msn.requestFocus();
            }
        }
    }
}//GEN-LAST:event_listaDeContactosMouseReleased

private void formModeloComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formModeloComponentResized
splitPrincipal.setDividerLocation(100);
    splitMensajes.setDividerLocation(formModelo.getHeight()-100);
    splitMensajes.repaint();
}//GEN-LAST:event_formModeloComponentResized

private void formChatComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formChatComponentResized
    for(Ventana f: ventanas){
        f.splitPrincipal.setDividerLocation(100);
        f.splitMensajes.setDividerLocation(evt.getComponent().getHeight()-200);
        f.splitMensajes.repaint();
    }
}//GEN-LAST:event_formChatComponentResized

private void formChatWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formChatWindowClosing
    
    
    tabContenedor.removeAll();
    while(!ventanas.isEmpty()){
        ventanas.remove(0);
    }
    
}//GEN-LAST:event_formChatWindowClosing

private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        try {
            InputOutputObject.escribirObjetoEn(yo, RUTA_CONF+yo.getNombre()+".cliente");
            ObjectOutputStream o = new ObjectOutputStream(socket.getOutputStream());
            o.writeObject(new ClienteDesconectado(yo.getCodigo(), yo.getNombre(), yo.getNick(), salaActual));
            socket.close();
        } catch (Exception ex) {
//            Logger.getLogger(AplicacionCliente.class.getName()).log(Level.SEVERE, null, ex);
//            System.exit(0);
        }finally{
            clases.principal.JedicAlumno.conectadoAJchat = false;
        }
}//GEN-LAST:event_formWindowClosing

private void formChatWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formChatWindowOpened
    
}//GEN-LAST:event_formChatWindowOpened

private void formChatWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formChatWindowActivated

}//GEN-LAST:event_formChatWindowActivated

private void formChatComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formChatComponentShown

    for (Ventana f : ventanas) {
        f.splitPrincipal.setDividerLocation(100);
        f.splitMensajes.setDividerLocation(formChat.getHeight() - 200);
        f.splitMensajes.repaint();
    }
}//GEN-LAST:event_formChatComponentShown

private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
    jSplitPane4.setDividerLocation(evt.getComponent().getHeight()-260);
    jSplitPane2.setDividerLocation(evt.getComponent().getWidth()-200);
}//GEN-LAST:event_formComponentResized

private void listaFontsMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaFontsMouseReleased
        previsualizarFont();
        
}//GEN-LAST:event_listaFontsMouseReleased

private void negritaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_negritaActionPerformed
previsualizarFont();
}//GEN-LAST:event_negritaActionPerformed

private void cursivaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cursivaActionPerformed
previsualizarFont();
}//GEN-LAST:event_cursivaActionPerformed

private void tamanioStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tamanioStateChanged
previsualizarFont();
}//GEN-LAST:event_tamanioStateChanged

private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
    
        String font = listaFonts.getSelectedValue().toString();
        SimpleAttributeSet estilo = new SimpleAttributeSet();
        StyleConstants.setFontFamily(estilo, font);
        StyleConstants.setFontSize(estilo, Integer.parseInt(tamanio.getValue().toString()));
        StyleConstants.setBold(estilo, negrita.isSelected());
        StyleConstants.setItalic(estilo, cursiva.isSelected());
        StyleConstants.setForeground(estilo, color.getColor());
        StyleConstants.setForeground(estilo, color.getColor());
        yo.setEstilo(estilo);
        formFonts.setVisible(false);
        
        for(Ventana v : ventanas){
            String texto = v.msn.getText();
            v.msn.setText("");
            v.msn.setCharacterAttributes(yo.getEstilo(), true);
            v.msn.setText(texto);
        }
}//GEN-LAST:event_jButton2ActionPerformed

private void formFontsComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formFontsComponentResized
jSplitPane8.setDividerLocation(evt.getComponent().getHeight()-450);
}//GEN-LAST:event_formFontsComponentResized

private void menuFotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuFotoActionPerformed
    cambiarImagen();
}//GEN-LAST:event_menuFotoActionPerformed

private void menuFontActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuFontActionPerformed
    formFonts.setVisible(true);
}//GEN-LAST:event_menuFontActionPerformed

private void menuFoto1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuFoto1ActionPerformed
    cambiarImagen();
}//GEN-LAST:event_menuFoto1ActionPerformed

private void menuFont1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuFont1ActionPerformed
    formFonts.setVisible(true);
}//GEN-LAST:event_menuFont1ActionPerformed

private void menuCrearSalaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCrearSalaActionPerformed
    crearSala();
}//GEN-LAST:event_menuCrearSalaActionPerformed

private void listaSalasMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaSalasMouseReleased
    if(evt.getClickCount() == 2){
        ModeloListaSalas model = (ModeloListaSalas)listaSalas.getModel();
        Sala salaSeleccionada = model.getSalaAt(listaSalas.getSelectedIndex());
        
        if(!salaSeleccionada.getNombreSala().equalsIgnoreCase(salaActual)){
            JPasswordField pass = new JPasswordField();
            pass.requestFocus();
            pass.setEchoChar('*');
            Object[] obj = {"Password de la Sala:\n", pass};
            Object stringArray[] = {"Entrar a Sala","Cancelar"};
            int op;
            op = JOptionPane.showOptionDialog(null, obj, "Entrar a Sala",
            JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, 
            null, stringArray, obj);

            if(op == JOptionPane.YES_OPTION){
                enviarObjeto(new PeticionEntrarSala(salaSeleccionada, yo, pass.getText().trim()));
            }
        }else{
            System.out.println("Click en sala que estoy ahora!");
        }
    }
}//GEN-LAST:event_listaSalasMouseReleased

private void menuCerrarSalaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCerrarSalaActionPerformed
    enviarObjeto(new QuieroMisSalas(yo));
    formMisSalas.setBounds(0,0,300,300);
    formMisSalas.setLocationRelativeTo(null);
    formMisSalas.setVisible(true);
}//GEN-LAST:event_menuCerrarSalaActionPerformed

private void menuAbandonarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuAbandonarActionPerformed
    if(!salaActual.equalsIgnoreCase("lobi")){
        this.enviarObjeto(new AbandonarSala(salaActual, yo));
    }
}//GEN-LAST:event_menuAbandonarActionPerformed

private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    try{
        ModeloListaSalas model = (ModeloListaSalas)listaMisSalas.getModel();
        Sala salaSeleccionada = model.getSalaAt(listaMisSalas.getSelectedIndex());
        enviarObjeto(new PeticionCerrarSala(salaSeleccionada));
    }catch(Exception e){
    }
}//GEN-LAST:event_jButton1ActionPerformed

private void cboOpcionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboOpcionesActionPerformed
    /*Opciones
    Cambiar Imagen
    Cambiar Estilo de Letra
    ----------SALAS------------
    Crear Sala
    Abandonar Sala Actual
    Mis Salas*/
    String op = cboOpciones.getSelectedItem().toString();
    if(op.equalsIgnoreCase("Cambiar Imagen")){
        cambiarImagen();
    }else if(op.equalsIgnoreCase("Cambiar Estilo de Letra")){
        formFonts.setVisible(true);
    }else if(op.equalsIgnoreCase("Crear Sala")){
        crearSala();
    }else if(op.equalsIgnoreCase("Abandonar Sala Actual")){
        if(!salaActual.equalsIgnoreCase("lobi")){
            this.enviarObjeto(new AbandonarSala(salaActual, yo));
        }
    }else if(op.equalsIgnoreCase("Mis Salas")){
        enviarObjeto(new QuieroMisSalas(yo));
        formMisSalas.setBounds(0,0,300,300);
        formMisSalas.setLocationRelativeTo(null);
        formMisSalas.setVisible(true);
    }else if(op.equalsIgnoreCase("Cerrar Sesión")){
        try {
            lblInicio.setVisible(false);
            formFonts.setVisible(false);
            cboOpciones.setEnabled(false);
            menuFoto.setEnabled(false);
            menuFont.setEnabled(false);
            menuCrearSala.setEnabled(false);
            menuCerrarSala.setEnabled(false);
            menuAbandonar.setEnabled(false);
            
            removerUsuarios(listaDeContactos);
            removerUsuarios(listaSalas);
            cerrarVentanas();
            chat.setText("");
            InputOutputObject.escribirObjetoEn(yo, RUTA_CONF+yo.getNombre()+".cliente");
            ObjectOutputStream o = new ObjectOutputStream(socket.getOutputStream());
            o.writeObject(new ClienteDesconectado(yo.getCodigo(), yo.getNombre(), yo.getNick(), salaActual));
            this.setTitle("JChat");
        } catch (IOException ex) {
            Logger.getLogger(AplicacionCliente.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                socket.close();
            } catch (IOException ex) {
//                Logger.getLogger(AplicacionCliente.class.getName()).log(Level.SEVERE, null, ex);
                
            }
        }
        btConectar.setText("Conectar!");
    }
    cboOpciones.setSelectedIndex(0);
}//GEN-LAST:event_cboOpcionesActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        SERVIDOR = args[0];
        PUERTO = args[1];
        NOMBRE = args[2];
        NICK = args[3];
        CODIGO_ALU = Integer.parseInt(args[4]);
        System.out.println("Codigo alumno en la main del chat: "+CODIGO_ALU);
        try {
            /* Set the Nimbus look and feel */
            //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
            /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
             * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
             */
    //        try {
    //            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
    //                if ("Nimbus".equals(info.getName())) {
    //                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
    //                    break;
    //                }
    //            }
    //        } catch (ClassNotFoundException ex) {
    //            java.util.logging.Logger.getLogger(AplicacionCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    //        } catch (InstantiationException ex) {
    //            java.util.logging.Logger.getLogger(AplicacionCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    //        } catch (IllegalAccessException ex) {
    //            java.util.logging.Logger.getLogger(AplicacionCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    //        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
    //            java.util.logging.Logger.getLogger(AplicacionCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    //        //</editor-fold>
    //        //</editor-fold>

            /* Create and display the form */
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            java.awt.EventQueue.invokeLater(new Runnable() {

                @Override
                public void run() {
                    new AplicacionCliente().setVisible(true);
                }
            });
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(AplicacionCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btConectar;
    private javax.swing.JComboBox cboOpciones;
    private javax.swing.JTextArea chat;
    private javax.swing.JTextArea chating;
    private javax.swing.JColorChooser color;
    private javax.swing.JCheckBox cursiva;
    private javax.swing.JFrame formChat;
    private javax.swing.JFrame formFonts;
    private javax.swing.JFrame formMisSalas;
    private javax.swing.JFrame formModelo;
    private javax.swing.JPanel foto1;
    private javax.swing.JPanel foto2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane10;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JSplitPane jSplitPane3;
    private javax.swing.JSplitPane jSplitPane4;
    private javax.swing.JSplitPane jSplitPane5;
    private javax.swing.JSplitPane jSplitPane6;
    private javax.swing.JSplitPane jSplitPane7;
    private javax.swing.JSplitPane jSplitPane8;
    private javax.swing.JSplitPane jSplitPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lblEscribiendo;
    private javax.swing.JLabel lblInicio;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JList listaDeContactos;
    private javax.swing.JList listaFonts;
    private javax.swing.JList listaMisSalas;
    private javax.swing.JList listaSalas;
    private javax.swing.JTextField mensajeParaTodos;
    private javax.swing.JMenuItem menuAbandonar;
    private javax.swing.JMenu menuAcciones;
    private javax.swing.JMenuItem menuCerrarSala;
    private javax.swing.JMenuItem menuCrearSala;
    private javax.swing.JMenuItem menuFont;
    private javax.swing.JMenuItem menuFont1;
    private javax.swing.JMenuItem menuFoto;
    private javax.swing.JMenuItem menuFoto1;
    private javax.swing.JTextArea msn;
    private javax.swing.JCheckBox negrita;
    private javax.swing.JLabel nombreRemoto;
    private javax.swing.JPanel panelFoto;
    private javax.swing.JPanel pnlFotos;
    private javax.swing.JPanel pnlMsn;
    private javax.swing.JScrollPane scrollChat;
    private javax.swing.JScrollPane scrollMsn;
    private javax.swing.JSplitPane splitMensajes;
    private javax.swing.JSplitPane splitPrincipal;
    private javax.swing.JTabbedPane tabContenedor;
    private javax.swing.JSpinner tamanio;
    private javax.swing.JTextPane textoEjemplo;
    // End of variables declaration//GEN-END:variables

    private void removerUsuarios(javax.swing.JList list) {
        list.setModel(new javax.swing.DefaultListModel());
    }

//    private boolean isImagen(String nombreClienteRemoto) {
//        for(Cliente c : clientes){
//            if(c.getNombre().equalsIgnoreCase(nombreClienteRemoto)){
//                return true;
//            }
//        }
//        return false;
//    }

//    private ImageIcon getImagen(String nombreClienteRemoto) {
//        for(Cliente c : clientes){
//            if(c.getNombre().equalsIgnoreCase(nombreClienteRemoto)){
//                return c.getImagen();
//            }
//        }
//        return null;
//    }

    private void ponerImagenRemotaEnVentanaChat(Cliente cliente) {
        ModeloListaContactos modelo = (ModeloListaContactos)listaDeContactos.getModel();
        for(Cliente c : modelo.getClientes()){
            if(c.getNombre().equalsIgnoreCase(cliente.getNombre())){
                for(Ventana v: ventanas){
                    if(v.getClienteDestino().getNombre().equalsIgnoreCase(cliente.getNombre())){
                        v.setImagenRemota(c.getImagen());
                        v.setImagenLocal(yo.getImagen());
                        break;
                    }
                }
                break;
            }
        }
        
    }

    private boolean isClienteAbierto(String nombreVentana) {
//        for(int j=0;j<tabContenedor.getTabCount(); j++){
//            if(tabContenedor.getTitleAt(j).equalsIgnoreCase(nombreVentana)){
//                return true;    
//            }
//        }
//        return false;
        for(Ventana v: ventanas){
            if(v.getNombreRemoto().equalsIgnoreCase(nombreVentana)){
                return true;
            }
        }
        return false;
    }

    
    private void cerrarVentanas() {
        formChat.setVisible(false);
        /*removiendo ventanas*/
        while(!ventanas.isEmpty()){
            ventanas.remove(0);
        }
        /*removiendo tabs*/
        while(tabContenedor.getTabCount() != 0){
            tabContenedor.remove(0);
        }
    }

    private void previsualizarFont() {
        try {
            String font = listaFonts.getSelectedValue().toString();
            SimpleAttributeSet estilo = new SimpleAttributeSet();
            StyleConstants.setFontFamily(estilo, font);
            StyleConstants.setFontSize(estilo, Integer.parseInt(tamanio.getValue().toString()));
            StyleConstants.setBold(estilo, negrita.isSelected());
            StyleConstants.setItalic(estilo, cursiva.isSelected());
            StyleConstants.setForeground(estilo, color.getColor());
            String texto = textoEjemplo.getText();
            textoEjemplo.setText("");
            textoEjemplo.getStyledDocument().insertString(0, texto, estilo);
        } catch (BadLocationException ex) {
            Logger.getLogger(AplicacionCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch(Exception e){
        
        }
    }

    private void cargarImagen() {
        ((JPanelFondo)panelFoto.getComponent(0)).setImage(yo.getImagen().getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
        panelFoto.getComponent(0).repaint();
    }

    private void cambiarImagen() {
        if(Abrir.abrir("jpg, jpeg, png", "Abrir Imágen", "/")){
            try {
                yo.setImagen(new ImageIcon(Abrir.getRuta()));
                cargarImagen();
                for(Ventana v : ventanas){
                   v.setImagenLocal(yo.getImagen());
                }
                ObjectOutputStream o = new ObjectOutputStream(socket.getOutputStream());
                o.writeObject(new PeticionActualizarImagen(yo));
            } catch (IOException ex) {
                Logger.getLogger(AplicacionCliente.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
    }

    private void enviarObjeto(Object objeto) {
        try {
            ObjectOutputStream o = new ObjectOutputStream(socket.getOutputStream());
            o.writeObject(objeto);
        } catch (IOException ex) {
            Logger.getLogger(AplicacionCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    private boolean isSalaMia(Sala salaSeleccionada) {
//        for(Sala s: misSalas){
//            if(s.getNombreSala().equalsIgnoreCase(salaSeleccionada.getNombreSala())){
//                return true;
//            }
//        }
//        return false;
//    }

    private void crearSala() {
        JTextField nombreSala = new JTextField("Sala de "+yo.getNick());
    nombreSala.selectAll();
    JPasswordField pass = new JPasswordField();
    pass.requestFocus();
    pass.setEchoChar('*');
    Object[] obj = {"Nombre de la Sala:\n", nombreSala,"Password de la Sala:\n", pass};
    Object stringArray[] = {"Crear Sala","Cancelar"};
    int op;
    
    do{
        op = JOptionPane.showOptionDialog(null, obj, "Crear Sala",
            JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, 
            null, stringArray, obj);
        System.out.println(op);
        if (op == JOptionPane.YES_OPTION){
            if(!nombreSala.getText().trim().equalsIgnoreCase("")){
                Sala nuevaSala = new Sala(yo, nombreSala.getText().trim(), pass.getText().trim());
                
                if(!salaActual.equalsIgnoreCase("lobi")){
                    enviarObjeto(new AbandonarSala(salaActual, yo));
                }
                
                enviarObjeto(nuevaSala);
//                salaActual = nuevaSala.getNombreSala();
            }else{
                Mensaje.warning("Escriba un nombre a la sala");
                op = JOptionPane.CANCEL_OPTION;
            }
        }else{
            op = JOptionPane.YES_OPTION;
        }
    }while(op != JOptionPane.YES_OPTION);
    }

    private void ponerFondoAJtextArea() {
        chat = new JTextArea() {

            
//            Image grayImage = GrayFilter.createDisabledImage(image);

            

            @Override
            public void paint(Graphics g) {
                ImageIcon imageIcon = new ImageIcon(getClass().getResource("/imagenes/LOGO.png"));
                Image image = imageIcon.getImage();
                setOpaque(false);
                g.drawImage(image, 0, 0, this);
                super.paint(g);
            }
        };
        
    }





    private class HiloRecibirObjeto extends Thread{
        private ObjectInputStream i;
        @Override
        public void run(){
            while(!socket.isClosed()){
                try {
                    i = new ObjectInputStream(socket.getInputStream());
                    Object obj = i.readObject();
                    if(obj instanceof ClienteNuevoSala){
                        ClienteNuevoSala cns = (ClienteNuevoSala)obj;
                        
                        chat.setText(chat.getText()+"\n"+cns.getNick().toUpperCase()+" SE HA UNIDO");
                        chat.setSelectionStart(chat.getText().length()-1);
                        chat.setSelectionEnd(chat.getText().length()-1);
                        
                        ModeloListaContactos model = (ModeloListaContactos)listaDeContactos.getModel();
                        model.addCliente(cns);
                        listaDeContactos.setModel(new ModeloListaContactos(model.getClientes()));
                        
//                        /*esto que hago ahora es para aumentar la cantidad de clientes de la sala*/
                        ModeloListaSalas m = (ModeloListaSalas)listaSalas.getModel();
                        ArrayList<Sala> listaDeSalas = m.getListaDeSalas();
                        for(Sala s: listaDeSalas){
                            if(s.getNombreSala().equalsIgnoreCase(salaActual)){
                                s.aumentarClientesEnUno();
                                break;
                            }
                        }
                        listaSalas.setModel(new ModeloListaSalas(m.getListaDeSalas()));
                    }else if(obj instanceof ClienteNuevo){
                        ClienteNuevo clienteNuevo = (ClienteNuevo)obj;
//                        System.out.println("Cliente nuevo: "+clienteNuevo.getEstilo());
                        ModeloListaContactos modelo = (ModeloListaContactos)listaDeContactos.getModel();
                        modelo.addCliente(clienteNuevo);
//                        ArrayList<Cliente> clientes = modelo.getClientes();
//                        listaDeContactos.setCellRenderer(new CellRendererConIcono(clientes));
                        listaDeContactos.setModel(new ModeloListaContactos(modelo.getClientes()));
                        /*SI HAY VENTANAS ABIERTAS, TENGO QUE VER SI ESTA EL QUE esta ellegando/
                        /*Y SI ESTA, LE PONGO AL LADO DEL NOMBRE (disponible)*/
                        for(Ventana v : ventanas){
                            if(v.getNombreRemoto().equalsIgnoreCase(clienteNuevo.getNombreParaVentana() + " (desconectado)")){
                                v.setNombreRemoto(clienteNuevo.getNombreParaVentana()+ " (disponible)");
                                
                                v.chating.getStyledDocument().insertString(v.chating.getStyledDocument().getLength(), "\n"+clienteNuevo.getNick()+" SE HA CONECTADO", Estilo.getEstiloMensajeConexion());
                                v.chating.setCaretPosition(v.chating.getStyledDocument().getLength());
                            }
                        }
                    }else if(obj instanceof MensajeCliente){
                        MensajeCliente men = (MensajeCliente)obj;
                        
                        if(men.isIsParaTodos()){
                            chat.append("\n"+men.getClienteEmisor().getNick()+" dice: \n    "+men.getMensaje());
                            chat.setSelectionStart(chat.getText().length()-1);
                            chat.setSelectionEnd(chat.getText().length()-1);
                        }else{
                            if(!formChat.isVisible()){

                                Ventana v = new Ventana(men.getClienteDestino(), men.getClienteEmisor());
                                ventanas.add(v);
                                javax.swing.JPanel p = new JPanel(new java.awt.BorderLayout());
                                p.add(v.splitPrincipal);
                                tabContenedor.addTab(v.getNombreRemoto(),p);
                                Pestana pestana = new Pestana(tabContenedor);
                                tabContenedor.setTabComponentAt(tabContenedor.getTabCount()-1,pestana);
                                
//                                v.chating.setText(v.chating.getText()+"\n"+men.getClienteEmisor().getNick()+" dice:\n    "+men.getMensaje());
//                                v.chating.setSelectionStart(v.chating.getText().length()-1);
//                                v.chating.setSelectionEnd(v.chating.getText().length()-1);
                                /*Poner mi mensaje en el chat con mi estilo*/
                                v.chating.getStyledDocument().insertString(v.chating.getStyledDocument().getLength(), "\n"+men.getClienteEmisor().getNick()+" dice:\n    ", null);
                                v.chating.getStyledDocument().insertString(v.chating.getStyledDocument().getLength(), men.getMensaje(), men.getClienteEmisor().getEstilo());
                                v.chating.setCaretPosition(v.chating.getStyledDocument().getLength());
                                /**/
                                formChat.setTitle("Chat de "+yo.getNick());
                                ponerImagenRemotaEnVentanaChat(men);
                                formChat.setVisible(true);
                                v.msn.requestFocus();
                            }else{
                                boolean existeLaPestaña = false;
//                                for(int j=0; j<tabContenedor.getTabCount(); j++){
//                                    if(tabContenedor.getTitleAt(j).equalsIgnoreCase(men.getClienteEmisor().getNombreParaVentana())){
//                                        Ventana v = ventanas.get(j);
//                                        
//                                        v.chating.setText(v.chating.getText()+"\n"+men.getEmisor()+" dice:\n    "+men.getMensaje());
//                                        v.chating.setSelectionStart(v.chating.getText().length()-1);
//                                        v.chating.setSelectionEnd(v.chating.getText().length()-1);
//                                        tabContenedor.setSelectedIndex(j);
//                                        v.msn.requestFocus();
//                                        existeLaPestaña = true;
//                                        break;
//                                    }
//                                }
                                for(Ventana v: ventanas){
                                    if(v.getClienteDestino().getNombre().equalsIgnoreCase(men.getClienteEmisor().getNombre())){
//                                        v.chating.setText(v.chating.getText()+"\n"+men.getClienteEmisor().getNick()+" dice:\n    "+men.getMensaje());
//                                        v.chating.setSelectionStart(v.chating.getText().length()-1);
//                                        v.chating.setSelectionEnd(v.chating.getText().length()-1);
                                        /*Poner mi mensaje en el chat con mi estilo*/
                                        v.chating.getStyledDocument().insertString(v.chating.getStyledDocument().getLength(), "\n"+men.getClienteEmisor().getNick()+" dice:\n    ", null);
                                        v.chating.getStyledDocument().insertString(v.chating.getStyledDocument().getLength(), men.getMensaje(), men.getClienteEmisor().getEstilo());
                                        v.chating.setCaretPosition(v.chating.getStyledDocument().getLength());
                                        /**/
                                        v.msn.requestFocus();
                                        existeLaPestaña = true;
                                        break;
                                    }
                                }
                                if(!existeLaPestaña){
                                    Ventana v = new Ventana(men.getClienteDestino(), men.getClienteEmisor());
                                    ventanas.add(v);
                                    javax.swing.JPanel p = new JPanel(new java.awt.BorderLayout());
                                    p.add(v.splitPrincipal);
                                    tabContenedor.add(men.getClienteEmisor().getNombreParaVentana(),p);
                                    Pestana pestana = new Pestana(tabContenedor);
                                    tabContenedor.setTabComponentAt(tabContenedor.getTabCount()-1,pestana);
                                    /*Poner mi mensaje en el chat con mi estilo*/
                                    v.chating.getStyledDocument().insertString(v.chating.getStyledDocument().getLength(), "\n"+men.getClienteEmisor().getNick()+" dice:\n    ", null);
                                    v.chating.getStyledDocument().insertString(v.chating.getStyledDocument().getLength(), men.getMensaje(), men.getClienteEmisor().getEstilo());
                                    v.chating.setCaretPosition(v.chating.getStyledDocument().getLength());
                                    /**/
//                                    v.chating.setText(v.chating.getText()+"\n"+men.getClienteEmisor().getNick()+" dice:\n    "+men.getMensaje());
//                                    v.chating.setSelectionStart(v.chating.getText().length()-1);
//                                    v.chating.setSelectionEnd(v.chating.getText().length()-1);
                                    
//                                    if(!isImagen(men.getEmisor())){
//                                        ObjectOutputStream o = new ObjectOutputStream(socket.getOutputStream());
//                                        o.writeObject(new PeticionImagen(men.getEmisor(), nombreLocal));
//                                    }else{
                                    ponerImagenRemotaEnVentanaChat(men);
//                                    }
                                    v.msn.requestFocus();
                                }
                            
                            }
                        
                        }
                        
                    }else if(obj instanceof MensajeConexion){
                        MensajeConexion men = (MensajeConexion)obj;
                        chat.setText(chat.getText()+"\n"+men.getMensaje());
                        chat.setSelectionStart(chat.getText().length()-1);
                        chat.setSelectionEnd(chat.getText().length()-1);
                    }else if(obj instanceof ConexionExitosa){
                        salaActual = "lobi";
                        listaDeContactos.setBorder(javax.swing.BorderFactory.createTitledBorder("Sala Actual: "+salaActual.toUpperCase()));
                        lblInicio.setVisible(false);
                        ConexionExitosa conExi = (ConexionExitosa)obj;
                        listaDeContactos.setModel(new ModeloListaContactos(conExi.getListaClientes()));
                        listaSalas.setModel(new ModeloListaSalas(conExi.getListaSalas()));
                        btConectar.setText("Desconectar");
                        cboOpciones.setEnabled(true);
                        menuFoto.setEnabled(true);
                        menuFont.setEnabled(true);
                        menuCrearSala.setEnabled(true);
                        menuCerrarSala.setEnabled(true);
                        menuAbandonar.setEnabled(true);
                    }else if(obj instanceof EnvioListas){
                        EnvioListas el = (EnvioListas)obj;
                        listaDeContactos.setModel(new ModeloListaContactos(el.getClientes()));
                        listaSalas.setModel(new ModeloListaSalas(el.getSalas()));
                        
                        btConectar.setText("Desconectar");
                        cboOpciones.setEnabled(true);
                        menuFoto.setEnabled(true);
                        menuFont.setEnabled(true);
                        menuCrearSala.setEnabled(true);
                        menuCerrarSala.setEnabled(true);
                        menuAbandonar.setEnabled(true);
                        salaActual = "lobi";
                        listaDeContactos.setBorder(javax.swing.BorderFactory.createTitledBorder("Sala Actual: "+salaActual.toUpperCase()));
                    }else if(obj instanceof RespuestaPeticionActualizarImagen){
                        RespuestaPeticionActualizarImagen rpai = (RespuestaPeticionActualizarImagen)obj;
                        actualizarListaDeClientes(rpai);
                        if(formChat.isVisible()){
                            for(Ventana v: ventanas){
                                if(v.getClienteDestino().getNombre().equalsIgnoreCase(rpai.getNombre())){
                                    v.setImagenRemota(rpai.getImagen());
                                    break;
                                }
                            }
                        }
                    }else if(obj instanceof ClienteDesconectado){
                        ClienteDesconectado clienteDesconectado = (ClienteDesconectado)obj;
                        ModeloListaContactos modelo = (ModeloListaContactos)listaDeContactos.getModel();
                        ArrayList<Cliente> clientes = modelo.getClientes();
                        for(Cliente c: clientes){
                            if(c.getNombre().equalsIgnoreCase(clienteDesconectado.getNombre())){
                                clientes.remove(c);
                                break;
                            }
                        }
                        listaDeContactos.setModel(new ModeloListaContactos(clientes));
                        chat.setText(chat.getText()+"\n"+clienteDesconectado.getNick().toUpperCase()+" SE HA DESCONECTADO");
                        chat.setSelectionStart(chat.getText().length()-1);
                        chat.setSelectionEnd(chat.getText().length()-1);
                        
                        /*SI HAY VENTANAS ABIERTAS, TENGO QUE VER SI ESTA EL QUE SE FUE*/
                        /*Y SI ESTA, LE PONGO AL LADO DEL NOMBRE (desconectado)*/
                        for(Ventana v : ventanas){
                            if(v.getNombreRemoto().equalsIgnoreCase(clienteDesconectado.getNombreParaVentana()+" (disponible)")){
                                v.setNombreRemoto(clienteDesconectado.getNombreParaVentana()+ " (desconectado)");
                                v.chating.getStyledDocument().insertString(v.chating.getStyledDocument().getLength(), "\n"+clienteDesconectado.getNick()+" SE HA DESCONECTADO", Estilo.getEstiloMensajeDesconexion());
                                v.chating.setCaretPosition(v.chating.getStyledDocument().getLength());
                            }
                        }
                    }else if(obj instanceof ClienteEscribiendo){
                        ClienteEscribiendo ce = (ClienteEscribiendo)obj;
                        for(Ventana v: ventanas){
                            if(v.getNombreRemoto().contains(ce.getNombreParaVentana())){
                                v.lblEscribiendo.setVisible(true);
                                break;
                            }
                        }
                    }else if(obj instanceof ClienteDejoDeEscribir){
                        ClienteDejoDeEscribir cdde = (ClienteDejoDeEscribir)obj;
                        for(Ventana v: ventanas){
                            if(v.getNombreRemoto().contains(cdde.getNombreParaVentana())){
                                v.lblEscribiendo.setVisible(false);
                                break;
                            }
                        }
                    }else if(obj instanceof ListaClientesSala){
                        ListaClientesSala lcs = (ListaClientesSala)obj;
                        
                        listaDeContactos.setModel(new ModeloListaContactos(lcs.getClientes()));
                        salaActual = lcs.getNombreSala();
                        listaDeContactos.setBorder(javax.swing.BorderFactory.createTitledBorder("Sala Actual: "+salaActual.toUpperCase()));
                       
                        //esto lo hago pa actualizar la cantridad de clientes que se muestra en la lista de salas
                        ModeloListaSalas m = (ModeloListaSalas)listaSalas.getModel();
                        ArrayList<Sala> listaDeSalas = m.getListaDeSalas();
                        for(Sala s: listaDeSalas){
                            if(s.getNombreSala().equalsIgnoreCase(salaActual)){
                                s.setCantidadDeClientes(lcs.getClientes().size());
                                break;
                            }
                        }
                        listaSalas.setModel(new ModeloListaSalas(m.getListaDeSalas()));
                    }else if(obj instanceof PassSalaIncorrecta){
                        javax.swing.JOptionPane.showMessageDialog(null, "Pass Incorrecta", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                    }else if(obj instanceof EliminarSala){
                        EliminarSala es = (EliminarSala)obj;
                        ModeloListaSalas model = (ModeloListaSalas)listaSalas.getModel();
                        model.removeSala(es.getNombreSala());
                        listaSalas.setModel(new ModeloListaSalas(model.getListaDeSalas()));
                    }else if(obj instanceof Sala){
                        Sala salaNueva = (Sala)obj;
                        ModeloListaSalas model = (ModeloListaSalas)listaSalas.getModel();
                        model.addSala(salaNueva);
                        listaSalas.setModel(new ModeloListaSalas(model.getListaDeSalas()));
                    }else if(obj instanceof SalaYaExiste){
                        Mensaje.error("Sala ya existente","La sala \""+((SalaYaExiste)obj).getNombreSala()+"\" ya existe. Por favor cambiar el nombre");
                        menuCrearSalaActionPerformed(null);
                    }else if(obj instanceof RemoverUsuarioDeSala){
                        RemoverUsuarioDeSala rus = (RemoverUsuarioDeSala)obj;
                        ModeloListaContactos model = (ModeloListaContactos)listaDeContactos.getModel();
//                        model.removeCliente(new Cliente(rus.getNombreCliente()));
                        //de la forma de arriba caga
                        ArrayList<Cliente> clientes = model.getClientes();
                        for(Cliente c: clientes){
                            if(c.getNombre().equalsIgnoreCase(rus.getNombre())){
                                clientes.remove(c);
                                break;
                            }
                        }
                        listaDeContactos.setModel(new ModeloListaContactos(model.getClientes()));
                        
                        chat.setText(chat.getText()+"\n"+rus.getNick().toUpperCase()+" SE HA RETIRADO DE LA SALA");
                        chat.setSelectionStart(chat.getText().length()-1);
                        chat.setSelectionEnd(chat.getText().length()-1);
                    }else if(obj instanceof ClienteCambioSala){
                        ClienteCambioSala ccs = (ClienteCambioSala)obj;
                        ModeloListaContactos modelo = (ModeloListaContactos)listaDeContactos.getModel();
                        ArrayList<Cliente> clientes = modelo.getClientes();
                        for(Cliente c: clientes){
                            if(c.getNombre().equalsIgnoreCase(ccs.getNombre())){
                                clientes.remove(c);
                                break;
                            }
                        }
                        listaDeContactos.setModel(new ModeloListaContactos(clientes));
                        chat.setText(chat.getText()+"\n"+ccs.getNick().toUpperCase()+" SE HA IDO A LA SALA \""+ccs.getSala()+"\"");
                        chat.setSelectionStart(chat.getText().length()-1);
                        chat.setSelectionEnd(chat.getText().length()-1);
                        
                        /*SI HAY VENTANAS ABIERTAS, TENGO QUE VER SI ESTA EL QUE SE FUE*/
                        /*Y SI ESTA, LE PONGO AL LADO DEL NOMBRE (desconectado)*/
                        for(Ventana v : ventanas){
                            if(v.getNombreRemoto().equalsIgnoreCase(ccs.getNombreParaVentana()+" (disponible)")){
                                v.setNombreRemoto(ccs.getNombreParaVentana()+ " (desconectado)");
                                v.chating.getStyledDocument().insertString(v.chating.getStyledDocument().getLength(), "\n"+ccs.getNick()+" SE HA DESCONECTADO", Estilo.getEstiloMensajeDesconexion());
                                v.chating.setCaretPosition(v.chating.getStyledDocument().getLength());
                            }
                        }
                    }else if(obj instanceof ListaDeMisSalas){
                        ListaDeMisSalas ldms = (ListaDeMisSalas)obj;
                        listaMisSalas.setModel(new ModeloListaSalas(ldms.getSalas()));
                    }
                } catch (BadLocationException ex) {
                    Logger.getLogger(AplicacionCliente.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(AplicacionCliente.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
//                    Logger.getLogger(AplicacionCliente.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        private void actualizarListaDeClientes(RespuestaPeticionActualizarImagen cliente) {
            ModeloListaContactos modelo = (ModeloListaContactos)listaDeContactos.getModel();
            for(Cliente c: modelo.getClientes()){
                if(c.getNombre().equalsIgnoreCase(cliente.getNombre())){
                    c.setImagen(cliente.getImagen());
                }
            }
            listaDeContactos.repaint();
        }

        

        
    }
    private class Ventana extends javax.swing.JFrame{
//    private javax.swing.JFrame formModelo;
    public javax.swing.JSplitPane splitPrincipal;
    public javax.swing.JSplitPane jSplitPane1;
    public javax.swing.JLabel nombreRemoto;
    public javax.swing.JPanel pnlFotos;
    public javax.swing.JPanel foto1;
    public javax.swing.JPanel foto2;
    public javax.swing.JSplitPane splitMensajes;
    public javax.swing.JPanel pnlMsn;
    public javax.swing.JScrollPane scrollMsn;
    public javax.swing.JTextPane msn;
    public javax.swing.JScrollPane scrollChat;
    public javax.swing.JTextPane chating;
    public JPanelFondo p1;
    public JPanelFondo p2;
    public  ClienteOrigen cliOrigen;
    private ClienteDestino cliDestino;
    public JLabel lblEscribiendo;
    
    public Ventana(ClienteOrigen clienteOrigen, ClienteDestino clienteDestino){
        
        
        super(clienteDestino.getNombreParaVentana());
        this.cliDestino = clienteDestino;
        this.cliOrigen = clienteOrigen;
        this.splitPrincipal = new javax.swing.JSplitPane();
        this.jSplitPane1 = new javax.swing.JSplitPane(); 
        this.nombreRemoto = new javax.swing.JLabel();
        this.pnlFotos = new javax.swing.JPanel();
        this.foto1 = new javax.swing.JPanel();
        this.foto2 = new javax.swing.JPanel();
        this.splitMensajes = new javax.swing.JSplitPane();
        this.pnlMsn = new javax.swing.JPanel();
        this.scrollMsn = new javax.swing.JScrollPane();
        this.msn = new javax.swing.JTextPane();
        this.scrollChat = new javax.swing.JScrollPane();
        this.chating = new javax.swing.JTextPane();
        this.p1 = new JPanelFondo(null);
        this.p2 = new JPanelFondo(null);
        this.lblEscribiendo = new JLabel();
        
        
        foto2.addMouseListener(new MouseListener() {

                @Override
                public void mouseClicked(MouseEvent e) {
//                    throw new UnsupportedOperationException("Not supported yet.");
                }

                @Override
                public void mousePressed(MouseEvent e) {
//                    throw new UnsupportedOperationException("Not supported yet.");
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    if(e.getClickCount() == 2){
                        if(Abrir.abrir("jpg, jpeg, png", "Abrir Imágen", "/")){
                            try {
                                yo.setImagen(new ImageIcon(Abrir.getRuta()));
                                ((JPanelFondo)panelFoto.getComponent(0)).setImage(yo.getImagen().getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
                                panelFoto.getComponent(0).repaint();
                                for(Ventana v : ventanas){
                                   v.setImagenLocal(yo.getImagen());
                                }
                                ObjectOutputStream o = new ObjectOutputStream(socket.getOutputStream());
                                o.writeObject(new PeticionActualizarImagen(yo));
                            } catch (IOException ex) {
                                Logger.getLogger(AplicacionCliente.class.getName()).log(Level.SEVERE, null, ex);
                            } 
                        }
                    }
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                }

                @Override
                public void mouseExited(MouseEvent e) {
                }
            });
        foto2.setToolTipText("Haga doble CLICK en la imagen para cambiarla");
        
        this.lblEscribiendo.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        this.lblEscribiendo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
//        this.lblEscribiendo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/escribiendo.gif"))); // NOI18N
        this.lblEscribiendo.setText("Escribiendo...");
        this.lblEscribiendo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        this.lblEscribiendo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        this.lblEscribiendo.setVisible(false);
        
        
        this.nombreRemoto.setText(this.cliDestino.getNombreParaVentana() + " (disponible)");
        this.addComponentListener(new java.awt.event.ComponentAdapter() {
                @Override
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formModeloComponentResized(evt);
            }

            private void formModeloComponentResized(ComponentEvent evt) {
                splitPrincipal.setDividerLocation(100);
                splitMensajes.setDividerLocation(evt.getComponent().getHeight()-100);
                splitMensajes.repaint();
            }
        });
        
        this.getContentPane().setLayout(new java.awt.BorderLayout(0, 3));

        this.splitPrincipal.setDividerLocation(100);
        this.splitPrincipal.setEnabled(false);

        this.foto1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        this.foto1.setLayout(new java.awt.BorderLayout());

        this.foto2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        this.foto2.setLayout(new java.awt.BorderLayout());

        this.foto1.setOpaque(true);
        this.foto1.setBackground(Color.white);
        
        this.foto2.setOpaque(true);
        this.foto2.setBackground(Color.white);
        
        javax.swing.GroupLayout pnlFotosLayout = new javax.swing.GroupLayout(this.pnlFotos);
        this.pnlFotos.setLayout(pnlFotosLayout);
        this.pnlFotos.setBackground(Color.white);
//        pnlFotosLayout.setHorizontalGroup(
//            pnlFotosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//            .addGroup(pnlFotosLayout.createSequentialGroup()
//                .addGroup(pnlFotosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                    .addComponent(this.foto1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
//                    .addComponent(this.foto2, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
//                .addContainerGap())
//        );
//        pnlFotosLayout.setVerticalGroup(
//            pnlFotosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//            .addGroup(pnlFotosLayout.createSequentialGroup()
//                .addComponent(this.foto1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
//                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 199, Short.MAX_VALUE)
//                .addComponent(this.foto2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
//        );

        pnlFotosLayout.setHorizontalGroup(
            pnlFotosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFotosLayout.createSequentialGroup()
                .addGroup(pnlFotosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(this.lblEscribiendo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(this.foto1, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                    .addComponent(this.foto2, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlFotosLayout.setVerticalGroup(
            pnlFotosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFotosLayout.createSequentialGroup()
                .addComponent(this.foto1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(this.lblEscribiendo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 144, Short.MAX_VALUE)
                .addComponent(this.foto2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        this.splitPrincipal.setLeftComponent(this.pnlFotos);
        this.jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        this.splitMensajes.setDividerLocation(260);
        this.splitMensajes.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        this.pnlMsn.setLayout(new java.awt.BorderLayout());

//        this.msn.setColumns(20);
//        this.msn.setRows(5);
        this.scrollMsn.setViewportView(this.msn);
        
        this.msn.addKeyListener(new KeyListener() {
            boolean primeraTecla = true;
            Thread h;
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if(primeraTecla){
//                    System.out.println("Escribiendo");
                    ClienteEscribiendo ce = new ClienteEscribiendo(cliOrigen, salaActual);
                    ce.setAquienEscribe(cliDestino);
                    
                    try {
                        ObjectOutputStream o = new ObjectOutputStream(socket.getOutputStream());
                        o.writeObject(ce);
                    } catch (IOException ex) {
                        Logger.getLogger(AplicacionCliente.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    primeraTecla = false;
                    /*hilo para ver cuando escribe y cuando no*/
                    h = new Thread(new Runnable() {
                            int letras;
                            int letrasAnterior;
                            
                            
                            @Override
                            public void run() {
                                try {
                                    letras = msn.getText().length();
                                    letrasAnterior = letras;
                                    Thread.sleep(1000);
                                    letras = msn.getText().length();
                                    while(letras != letrasAnterior){
                                        letrasAnterior = letras;
                                        Thread.sleep(1000);
                                        letras = msn.getText().length();
                                    }
                                    
                                    
                                    
                                    /*SI EL SERVIDOR SE PONE WEON Y ME MANDA UN ERROR BORRO ESTO*/
                                    ClienteDejoDeEscribir cdde = new ClienteDejoDeEscribir(cliOrigen, salaActual);
                                    cdde.setAquienEscribe(cliDestino);

                                    try {
                                        ObjectOutputStream o = new ObjectOutputStream(socket.getOutputStream());
                                        o.writeObject(cdde);
                                    } catch (IOException ex) {
                                        Logger.getLogger(AplicacionCliente.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    /*SI EL SERVIDOR SE PONE WEON Y ME MANDA UN ERROR BORRO ESTO*/
                                    
                                    
                                } catch (InterruptedException ex) {
                                } finally{
//                                    System.out.println("Paro de Escribir");
                                    
                                    /*esto lo hago, porque, como paro de escribir, tienes que otra vez crearse el hilo*/
                                    primeraTecla = true;
                                }
                            }
                    });
                    h.start();
                }
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    h.interrupt();
                    ClienteDejoDeEscribir cdde = new ClienteDejoDeEscribir(cliOrigen, salaActual);
                    cdde.setAquienEscribe(cliDestino);

                    try {
                        ObjectOutputStream o = new ObjectOutputStream(socket.getOutputStream());
                        o.writeObject(cdde);
                    } catch (IOException ex) {
                        Logger.getLogger(AplicacionCliente.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if(!cliDestino.getNombreParaVentana().contains("desconectado")){
                        try {
                            MensajeCliente men;
                            StyledDocument sd = chating.getStyledDocument();
                            men = new MensajeCliente(msn.getText(), cliDestino, cliOrigen, salaActual);
                            msn.setText("");
                            msn.requestFocus();
                            /*Poner mi mensaje en el chat con mi estilo*/
                            sd.insertString(chating.getStyledDocument().getLength(), "\n"+men.getClienteEmisor().getNick()+" dice:\n    ", null);
                            sd.insertString(chating.getStyledDocument().getLength(), men.getMensaje(), yo.getEstilo());/*men.getClienteEmisor().getEstilo()*/
                            chating.setCaretPosition(sd.getLength());
                            men.setEstilo(yo.getEstilo());
                            /**/
//                            chating.setText(chating.getText()+"\n"+men.getClienteEmisor().getNick()+" dice:\n    "+men.getMensaje());
//                            chating.setSelectionStart(chating.getText().length()-1);
//                            chating.setSelectionEnd(chating.getText().length()-1);
                            ObjectOutputStream o = new ObjectOutputStream(socket.getOutputStream());
                            o.writeObject(men);

                        }   catch (BadLocationException ex) {
                                Logger.getLogger(AplicacionCliente.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (IOException ex) {
                            Logger.getLogger(AplicacionCliente.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }else{
                        try {
                            
//                            chating.append("\n"+yo.getNick()+" dice:\n    "+msn.getText());
//                            chating.append("\nEL USUARIO "+cliDestino.getNick().toUpperCase()+" SE ENCUENTRA DESCONECTADO");
//                            chating.setSelectionStart(chating.getText().length()-1);
//                            chating.setSelectionEnd(chating.getText().length()-1);
                            chating.getStyledDocument().insertString(chating.getStyledDocument().getLength(), "\n"+yo.getNick()+" dice:\n    "+msn.getText(), null);
                            chating.getStyledDocument().insertString(chating.getStyledDocument().getLength(), "\nEL USUARIO "+cliDestino.getNick().toUpperCase()+" SE ENCUENTRA DESCONECTADO", null);
                            chating.setCaretPosition(chating.getStyledDocument().getLength());
                            msn.setText("");
                            msn.requestFocus();
                        } catch (BadLocationException ex) {
                            Logger.getLogger(AplicacionCliente.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        });

        this.pnlMsn.add(this.scrollMsn, java.awt.BorderLayout.CENTER);

        this.splitMensajes.setBottomComponent(this.pnlMsn);

//        this.chating.setColumns(20);
//        this.chating.setRows(5);
        this.chating.setEditable(false);
        this.chating.setFont(new java.awt.Font("Tahoma", 0, 14));
        this.scrollChat.setViewportView(this.chating);

        this.splitMensajes.setLeftComponent(this.scrollChat);
        
        this.jSplitPane1.setRightComponent(this.splitMensajes);

        this.nombreRemoto.setBackground(new java.awt.Color(255, 255, 255));
        this.nombreRemoto.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        this.nombreRemoto.setOpaque(true);
        this.jSplitPane1.setLeftComponent(this.nombreRemoto);

        this.splitPrincipal.setRightComponent(this.jSplitPane1);

//        splitPrincipal.setRightComponent(splitMensajes);

        this.getContentPane().add(this.splitPrincipal, java.awt.BorderLayout.CENTER);
        this.foto1.setBounds(0, 0, 100, 100);
        this.foto1.setBounds(0, 0, 100, 100);
        this.splitPrincipal.setDividerLocation(100);
        this.splitMensajes.setDividerLocation(330);
        this.setBounds(0,0,400,400);
        this.setLocationRelativeTo(null);
        
        this.p1.setImage(new ImageIcon(getClass().getResource("/chat/imagenes/cliente.png")).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
        this.p1.repaint();
        this.p2.setImage(new ImageIcon(getClass().getResource("/chat/imagenes/cliente.png")).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
        this.p2.repaint();
        this.foto1.add(p1);
        this.foto2.add(p2);
        
        /*Estilo*/
        this.msn.setCharacterAttributes(this.cliOrigen.getEstilo(), true);
        
//        this.chating.setCharacterAttributes(this.cliDestino.getEstilo(), true);
    }

    /*CONSTRUCTOR PARA CUANDO ME LLEGAN MENSAJES, ES INVERSO :D*/
    /*cliOrigen soy yo*/
    private Ventana(ClienteDestino clienteDestino, ClienteOrigen clienteEmisor) {
            super(clienteDestino.getNombreParaVentana());
            this.cliOrigen = new ClienteOrigen(clienteDestino);
            this.cliDestino = new ClienteDestino(clienteEmisor);
            this.splitPrincipal = new javax.swing.JSplitPane();
            this.jSplitPane1 = new javax.swing.JSplitPane(); 
            this.nombreRemoto = new javax.swing.JLabel();
            this.pnlFotos = new javax.swing.JPanel();
            this.foto1 = new javax.swing.JPanel();
            this.foto2 = new javax.swing.JPanel();
            this.splitMensajes = new javax.swing.JSplitPane();
            this.pnlMsn = new javax.swing.JPanel();
            this.scrollMsn = new javax.swing.JScrollPane();
            this.msn = new javax.swing.JTextPane();
            this.scrollChat = new javax.swing.JScrollPane();
            this.chating = new javax.swing.JTextPane();
            this.p1 = new JPanelFondo(null);
            this.p2 = new JPanelFondo(null);
            this.lblEscribiendo = new JLabel();

            foto2.addMouseListener(new MouseListener() {

                @Override
                public void mouseClicked(MouseEvent e) {
//                    throw new UnsupportedOperationException("Not supported yet.");
                }

                @Override
                public void mousePressed(MouseEvent e) {
//                    throw new UnsupportedOperationException("Not supported yet.");
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    if(e.getClickCount() == 2){
                        if(Abrir.abrir("jpg, jpeg, png", "Abrir Imágen", "/")){
                            try {
                                yo.setImagen(new ImageIcon(Abrir.getRuta()));
                                ((JPanelFondo)panelFoto.getComponent(0)).setImage(yo.getImagen().getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
                                panelFoto.getComponent(0).repaint();
                                for(Ventana v : ventanas){
                                   v.setImagenLocal(yo.getImagen());
                                }
                                ObjectOutputStream o = new ObjectOutputStream(socket.getOutputStream());
                                o.writeObject(new PeticionActualizarImagen(yo));
                            } catch (IOException ex) {
                                Logger.getLogger(AplicacionCliente.class.getName()).log(Level.SEVERE, null, ex);
                            } 
                        }
                    }
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                }

                @Override
                public void mouseExited(MouseEvent e) {
                }
            });
            foto2.setToolTipText("Haga doble CLICK en la imagen para cambiarla");
            
            this.lblEscribiendo.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
            this.lblEscribiendo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
//            this.lblEscribiendo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/escribiendo.gif"))); // NOI18N
            this.lblEscribiendo.setText("Escribiendo...");
            this.lblEscribiendo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            this.lblEscribiendo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
            this.lblEscribiendo.setVisible(false);
            
            this.nombreRemoto.setText(this.cliDestino.getNombreParaVentana()+ " (disponible)");
            this.addComponentListener(new java.awt.event.ComponentAdapter() {
                    @Override
                public void componentResized(java.awt.event.ComponentEvent evt) {
                    formModeloComponentResized(evt);
                }

                private void formModeloComponentResized(ComponentEvent evt) {
                    splitPrincipal.setDividerLocation(100);
                    splitMensajes.setDividerLocation(evt.getComponent().getHeight()-100);
                    splitMensajes.repaint();
                }
            });

            this.getContentPane().setLayout(new java.awt.BorderLayout(0, 3));

            this.splitPrincipal.setDividerLocation(100);
            this.splitPrincipal.setEnabled(false);

            this.foto1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
            this.foto1.setLayout(new java.awt.BorderLayout());

            this.foto2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
            this.foto2.setLayout(new java.awt.BorderLayout());

            this.foto1.setOpaque(true);
            this.foto1.setBackground(Color.white);

            this.foto2.setOpaque(true);
            this.foto2.setBackground(Color.white);

            javax.swing.GroupLayout pnlFotosLayout = new javax.swing.GroupLayout(this.pnlFotos);
            this.pnlFotos.setLayout(pnlFotosLayout);
            this.pnlFotos.setBackground(Color.white);
//            pnlFotosLayout.setHorizontalGroup(
//                pnlFotosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                .addGroup(pnlFotosLayout.createSequentialGroup()
//                    .addGroup(pnlFotosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                        .addComponent(this.foto1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
//                        .addComponent(this.foto2, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
//                    .addContainerGap())
//            );
//            pnlFotosLayout.setVerticalGroup(
//                pnlFotosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                .addGroup(pnlFotosLayout.createSequentialGroup()
//                    .addComponent(this.foto1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
//                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 199, Short.MAX_VALUE)
//                    .addComponent(this.foto2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
//            );
            pnlFotosLayout.setHorizontalGroup(
                pnlFotosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlFotosLayout.createSequentialGroup()
                    .addGroup(pnlFotosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(this.lblEscribiendo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(this.foto1, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                        .addComponent(this.foto2, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE))
                    .addContainerGap())
            );
            pnlFotosLayout.setVerticalGroup(
                pnlFotosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlFotosLayout.createSequentialGroup()
                    .addComponent(this.foto1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(this.lblEscribiendo)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 144, Short.MAX_VALUE)
                    .addComponent(this.foto2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
            );
            this.splitPrincipal.setLeftComponent(this.pnlFotos);
            this.jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
            this.splitMensajes.setDividerLocation(260);
            this.splitMensajes.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

            this.pnlMsn.setLayout(new java.awt.BorderLayout());

//            this.msn.setColumns(20);
//            this.msn.setRows(5);
            this.scrollMsn.setViewportView(this.msn);
            this.msn.addKeyListener(new KeyListener() {
                boolean primeraTecla = true;
                Thread h;
                @Override
                public void keyTyped(KeyEvent e) {

                }

                @Override
                public void keyPressed(KeyEvent e) {

                }

                @Override
                public void keyReleased(KeyEvent e) {
                    if(primeraTecla){
//                    System.out.println("Escribiendo");
                    ClienteEscribiendo ce = new ClienteEscribiendo(cliOrigen, salaActual);
                    ce.setAquienEscribe(cliDestino);
                    
                    try {
                        ObjectOutputStream o = new ObjectOutputStream(socket.getOutputStream());
                        o.writeObject(ce);
                    } catch (IOException ex) {
                        Logger.getLogger(AplicacionCliente.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    primeraTecla = false;
                    /*hilo para ver cuando escribe y cuando no*/
                    h = new Thread(new Runnable() {
                            int letras;
                            int letrasAnterior;
                            
                            
                            @Override
                            public void run() {
                                try {
                                    letras = msn.getText().length();
                                    letrasAnterior = letras;
                                    Thread.sleep(1000);
                                    letras = msn.getText().length();
                                    while(letras != letrasAnterior){
                                        letrasAnterior = letras;
                                        Thread.sleep(1000);
                                        letras = msn.getText().length();
                                    }
                                    
                                    
                                    
                                    /*SI EL SERVIDOR SE PONE WEON Y ME MANDA UN ERROR BORRO ESTO*/
                                    ClienteDejoDeEscribir cdde = new ClienteDejoDeEscribir(cliOrigen, salaActual);
                                    cdde.setAquienEscribe(cliDestino);

                                    try {
                                        ObjectOutputStream o = new ObjectOutputStream(socket.getOutputStream());
                                        o.writeObject(cdde);
                                    } catch (IOException ex) {
                                        Logger.getLogger(AplicacionCliente.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    /*SI EL SERVIDOR SE PONE WEON Y ME MANDA UN ERROR BORRO ESTO*/
                                    
                                    
                                } catch (InterruptedException ex) {
                                } finally{
//                                    System.out.println("Paro de Escribir");
                                    
                                    /*esto lo hago, porque, como paro de escribir, tienes que otra vez crearse el hilo*/
                                    primeraTecla = true;
                                }
                            }
                    });
                    h.start();
                }
                    if(e.getKeyCode() == KeyEvent.VK_ENTER){
                        h.interrupt();
                        ClienteDejoDeEscribir cdde = new ClienteDejoDeEscribir(cliOrigen, salaActual);
                        cdde.setAquienEscribe(cliDestino);

                        try {
                            ObjectOutputStream o = new ObjectOutputStream(socket.getOutputStream());
                            o.writeObject(cdde);
                        } catch (IOException ex) {
                            Logger.getLogger(AplicacionCliente.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                        
                        
                        
                        if(!cliDestino.getNombreParaVentana().contains("desconectado")){
                            try {
                                MensajeCliente men;

                                men = new MensajeCliente(msn.getText(), cliDestino, cliOrigen,salaActual);
                                msn.setText("");
                                msn.requestFocus();
//                                chating.setText(chating.getText()+"\n"+men.getClienteEmisor().getNick()+" dice:\n    "+men.getMensaje());
//                                chating.setSelectionStart(chating.getText().length()-1);
//                                chating.setSelectionEnd(chating.getText().length()-1);
                                /*Poner mi mensaje en el chat con mi estilo*/
                                chating.getStyledDocument().insertString(chating.getStyledDocument().getLength(), "\n"+men.getClienteEmisor().getNick()+" dice:\n    ", null);
                                chating.getStyledDocument().insertString(chating.getStyledDocument().getLength(), men.getMensaje(), yo.getEstilo());
                                chating.setCaretPosition(chating.getStyledDocument().getLength());
                                men.setEstilo(yo.getEstilo());
                                /**/
                                ObjectOutputStream o = new ObjectOutputStream(socket.getOutputStream());
                                o.writeObject(men);

                            } catch (BadLocationException ex) {
                                Logger.getLogger(AplicacionCliente.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (IOException ex) {
                                Logger.getLogger(AplicacionCliente.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }else{
                            try {
    //                            chating.append("\n"+yo.getNick()+" dice:\n    "+msn.getText());
    //                            chating.append("\nEL USUARIO "+cliDestino.getNick().toUpperCase()+" SE ENCUENTRA DESCONECTADO");
    //                            chating.setSelectionStart(chating.getText().length()-1);
    //                            chating.setSelectionEnd(chating.getText().length()-1);
                                chating.getStyledDocument().insertString(chating.getStyledDocument().getLength(), "\n"+yo.getNick()+" dice:\n    "+msn.getText(), null);
                                chating.getStyledDocument().insertString(chating.getStyledDocument().getLength(), "\nEL USUARIO "+cliDestino.getNick().toUpperCase()+" SE ENCUENTRA DESCONECTADO", null);
                                chating.setCaretPosition(chating.getStyledDocument().getLength());
                                msn.setText("");
                                msn.requestFocus();
                            } catch (BadLocationException ex) {
                                Logger.getLogger(AplicacionCliente.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                }
            });


            this.pnlMsn.add(this.scrollMsn, java.awt.BorderLayout.CENTER);

            this.splitMensajes.setBottomComponent(this.pnlMsn);

//            this.chating.setColumns(20);
//            this.chating.setRows(5);
            this.chating.setEditable(false);
            this.chating.setFont(new java.awt.Font("Tahoma", 0, 14));
            this.scrollChat.setViewportView(this.chating);

            this.splitMensajes.setLeftComponent(this.scrollChat);

            this.jSplitPane1.setRightComponent(this.splitMensajes);

            this.nombreRemoto.setBackground(new java.awt.Color(255, 255, 255));
            this.nombreRemoto.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
            this.nombreRemoto.setOpaque(true);
            this.jSplitPane1.setLeftComponent(this.nombreRemoto);

            this.splitPrincipal.setRightComponent(this.jSplitPane1);

    //        splitPrincipal.setRightComponent(splitMensajes);

            this.getContentPane().add(this.splitPrincipal, java.awt.BorderLayout.CENTER);
            this.foto1.setBounds(0, 0, 100, 100);
            this.foto1.setBounds(0, 0, 100, 100);
            this.splitPrincipal.setDividerLocation(100);
            this.splitMensajes.setDividerLocation(330);
            this.setBounds(0,0,400,400);
            this.setLocationRelativeTo(null);

            this.p1.setImage(new ImageIcon(getClass().getResource("/chat/imagenes/cliente.png")).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
            this.p1.repaint();
            this.p2.setImage(new ImageIcon(getClass().getResource("/chat/imagenes/cliente.png")).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
            this.p2.repaint();
            this.foto1.add(p1);
            this.foto2.add(p2);
            
            /*Estilos*/
//            this.msn.setCharacterAttributes(this.cliOrigen.getEstilo(), true);
            this.msn.setCharacterAttributes(this.cliOrigen.getEstilo(), true);
        }

       
    void setImagenRemota(ImageIcon imagenRemota) {
        this.p1.setImage(imagenRemota.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
        this.p1.repaint();
    }
    
    void setImagenLocal(ImageIcon imagenLocal) {
        this.p2.setImage(imagenLocal.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
        this.p2.repaint();
    }
    
    void setNombreRemoto(String nombre){
        this.nombreRemoto.setText(nombre);
        cliDestino.setNombreParaVentana(nombre);
    }

        public String getNombreRemoto() {
            return this.nombreRemoto.getText();
        }

        public ClienteDestino getClienteDestino() {
            return this.cliDestino;
        }

        public ClienteOrigen getClienteOrigen() {
            return this.cliOrigen;
        }
        
        
    
}
    public class Pestana extends JPanel implements ActionListener {

     /*
     * * * * * * * * * * * * * * * * * * * * *
     *               ATRIBUTES               *
     * * * * * * * * * * * * * * * * * * * * *
     */
    
    private final JTabbedPane      panelContenedor; //referencia al coponente contenedor, una vez asigado no cambia (final)
    private JLabel                       contenido; //texto de la pestaña
    private JButton                    botonCerrar; //el famoso botonCerrar ;)
    
    
     /*
     * * * * * * * * * * * * * * * * * * * * *
     *             CONSTRUCTORS              *
     * * * * * * * * * * * * * * * * * * * * *
     */
    public Pestana(final JTabbedPane panelContenedor) {
        
        super(new FlowLayout(FlowLayout.LEFT, 0, 0));
        
        //obtengo la referencia del tabbedPane contenedor (el puntero a..)
        this.panelContenedor=panelContenedor;
        setOpaque(false);
        
//        labelIcono = new JLabel();
//        labelIcono.setIcon(
//                new ImageIcon(getClass().getResource("/resource/documento_mini.png")));
  
        //obtengo el indice del JPanel al que fue agregado "esta" pestaña
        int i = panelContenedor.indexOfTabComponent(this);
        
        //Obtengo el titulo que le fue asignado al JPanel
        contenido = new JLabel() {
                @Override
            public String getText() {
                //necesito el indice de nuevo porque este es el espacio de la nueva clase
                //para ello hago referencia a la clase contenedora de la que estoy ahora |
                int i = panelContenedor.indexOfTabComponent(Pestana.this); //  <---------|
                if (i != -1) {
                    return panelContenedor.getTitleAt(i);
                }
                return null;
            }
        };
        
        //agregamos el icono y establecemos un borde, para crear una distancia
        //por default entre este y otros coponentes cercanos a el
//        add(labelIcono);
//        labelIcono.setBorder(BorderFactory.createEmptyBorder(0,0,0,5));
        
        //agregamos el texto de la pestaña y establecemos una
        //distancia por default entre este componente y los demas
        add(contenido);
        contenido.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));

        //el boton close
        botonCerrar = new JButton();
        botonCerrar.setText("x");
        int size = 20;
        botonCerrar.setPreferredSize(new Dimension(size, size));
        botonCerrar.setToolTipText("Cerrar esta pestaña");
        botonCerrar.setUI(new BasicButtonUI());
        //hacemos transparente su relleno
        botonCerrar.setContentAreaFilled(false);
        //No necesita ser enfocable
        botonCerrar.setFocusable(false);
        botonCerrar.setBorder(BorderFactory.createEtchedBorder());
        botonCerrar.setBorderPainted(false);
        //Efecto de enmarcado cuando el mouse pasa sobre el
        botonCerrar.addMouseListener(eventosRaton);
        botonCerrar.setRolloverEnabled(true);
        //Listener para obtener controlar los eventos del boton
        botonCerrar.addActionListener(this);
        //Nuestro cursor para el componente ;)
//        botonCerrar.setCursor(MyHandCursor.getDefault());
        add(botonCerrar);
        //de nuevo un borde predeterminado
        setBorder(BorderFactory.createEmptyBorder(2, 0, 0, 0));
    }
    
     /*
     * * * * * * * * * * * * * * * * * * * * *
     *                ACTIONS                *
     * * * * * * * * * * * * * * * * * * * * *
     */ 
    
        @Override
    public void actionPerformed(ActionEvent ev){
       Object o = ev.getSource();
       if (o==botonCerrar){
            //de nuevo el indice de ficha a la que pertenece "esta" pestaña
            int i = panelContenedor.indexOfTabComponent(this);
            if (i != -1) {
                //quitando el JPanel al que pertenece esta pestaña
                panelContenedor.remove(i);
                ventanas.remove(i);
                if(tabContenedor.getTabCount() == 0){
                    formChat.setVisible(false);
                }
            }
       }
   }
    

//static final, ahorrando un poquito de RAM ;)
    private MouseListener eventosRaton= new MouseAdapter() {
            @Override
        public void mouseEntered(MouseEvent e) {
            Component component = e.getComponent();
            //es un boton?
            if (component instanceof JButton) {
                //casting
                JButton button = (JButton) component;
                //el mouse lo señala, pintemos el marco del boton
                button.setBorderPainted(true);
            }
        }

            @Override
        public void mouseExited(MouseEvent e) {
            Component component = e.getComponent();
            //es un boton?
            if (component instanceof JButton) {
                //casting
                JButton button = (JButton) component;
                //el mouse ya no lo señala, quite el marco del boton
                button.setBorderPainted(false);
            }
        }
    };
    
}
}
