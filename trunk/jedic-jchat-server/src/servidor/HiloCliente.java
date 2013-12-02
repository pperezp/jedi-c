/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import chat.modelo.*;
import chat.modelo.cliente.*;
import chat.modelo.mensajes.MensajeCliente;
import chat.modelo.mensajes.MensajeConexion;
import chat.modelo.peticiones.*;
import chat.modelo.respuestas.*;
import clases.BD.Conexion;
import clases.BD.DatosConexion;
import clases.xml.dom.analizador.DOM;
import clases.xml.dom.analizador.modelo.Atributo;
import clases.xml.dom.analizador.modelo.Tag;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author Patricio Pérez Pinto
 */
public class HiloCliente extends Thread {

    private InetAddress direccionCliente;
    private Socket socket;
    private ObjectOutputStream salida;
    private ObjectInputStream entrada;
    private ClienteConSocket yo;
    private final String MENSAJE_CONEXION;
    private String salaActual;
    private long timeIni, timeFin;
    private Conexion conexion;
    private final String RUTA_CONFIG_BD = "config"+File.separator+"conexion.xml";

    public HiloCliente(Socket socket) {
        this.socket = socket;
        direccionCliente = socket.getInetAddress();
        MENSAJE_CONEXION = "SE HA CONECTADO";
    }

    @Override
    public void run() {
        try {
            /*mientras la conexion no halla sido cerrada*/
            while (!socket.isClosed()) {
                /*Se espera cualquier objeto*/
                entrada = new ObjectInputStream(socket.getInputStream());
                /*Se guarda el objeto que viene llegando del CLIENTE*/
                Object objetoEntrante = entrada.readObject();
                /*y se pregunto por el objeto*/
                /*Si es un MENSAJE*/
                if (objetoEntrante instanceof MensajeCliente) {
                    MensajeCliente men = (MensajeCliente) objetoEntrante;
                    
                    if(men.getNombreSala().equalsIgnoreCase("lobi")){
                        for (ClienteConSocket c : Servidor.getListaClientesConSocket()) {
                            /*Se envía el mensaje, si es para TODOS LOS CLIENTES, o si el nombre del CLIENTE DESTINO
                            receptor coincide con el CLIENTE del SERVIDOR*/
                            if (men.isIsParaTodos() || c.getNombre().equalsIgnoreCase(men.getClienteDestino().getNombre())) {
                                salida = new ObjectOutputStream(c.getSocket().getOutputStream());
                                salida.writeObject(men);
                            }
                        }
                        /*si no es lobi, esta en cualquier sala*/
                    }else{
                        SalaServidor sala = Servidor.getSala(men.getNombreSala());
                        if(!men.isIsParaTodos()){
                            Socket s = sala.getSocketDe(men.getClienteDestino().getNombre());
                            this.enviarObjeto(s, men);
                        }else{
                            for(Socket s:sala.getSocketsDeClientes()){
                                this.enviarObjeto(s, men);
                            }
                        }
                    }
//                    /*Se recorren los CLIENTES del SERVIDOR que tengan las misma SECCION DEL EMISOR DEL MENSAJE*/
//                    for (ClienteConSocket c : Servidor.getListaClientesConSocket()) {
//                        /*Se envía el mensaje, si es para TODOS LOS CLIENTES, o si el nombre del CLIENTE DESTINO
//                        receptor coincide con el CLIENTE del SERVIDOR*/
//                        if (men.isIsParaTodos() || c.getNombre().equalsIgnoreCase(men.getClienteDestino().getNombre())) {
//                            salida = new ObjectOutputStream(c.getSocket().getOutputStream());
//                            salida.writeObject(men);
//                        }
//                    }
                    /*Si se ha desconectado el CLIENTE*/
                } else if (objetoEntrante instanceof ClienteDesconectado) {
                    ClienteDesconectado clienteDesconectado = (ClienteDesconectado) objetoEntrante;
                    if(clienteDesconectado.getSalaActual().equalsIgnoreCase("lobi")){
                        /*se elimina de la lista de CLIENTES del SERVIDOR*/
                        Servidor.eliminarCliente(yo);
                        socket.close();
                        guardarEstadisticasChat();
                        
                        for (ClienteConSocket c : Servidor.getListaClientesConSocket()) {
                            salida = new ObjectOutputStream(c.getSocket().getOutputStream());
                            salida.writeObject(clienteDesconectado);
                        }
                    }else{
                        Servidor.removeClienteDeSala(clienteDesconectado.getNombre(), clienteDesconectado.getSalaActual());
                        Servidor.eliminarCliente(yo);
                        
                        socket.close();

                       
                        for (Socket c : Servidor.getListaDeSocketsDeSala(clienteDesconectado.getSalaActual())) {
                            salida = new ObjectOutputStream(c.getOutputStream());
                            salida.writeObject(clienteDesconectado);
                        }
                    }
                    desconectarBD();
                    /*Si el CLIENTE pide Actualizar su IMAGEN*/
                } else if (objetoEntrante instanceof PeticionActualizarImagen) {
                    PeticionActualizarImagen pai = (PeticionActualizarImagen) objetoEntrante;
                    Servidor.actualizarImagen(pai);
                    for (ClienteConSocket c : Servidor.getListaClientesConSocket()) {
//                        if (!c.getNombre().equalsIgnoreCase(pai.getNombre())) {
                            salida = new ObjectOutputStream(c.getSocket().getOutputStream());
                            salida.writeObject(new RespuestaPeticionActualizarImagen(pai));
//                        }
                    }
                } else if (objetoEntrante instanceof InicioSesion) {
                    
                    salaActual = "lobi";
                    InicioSesion clienteNuevo = (InicioSesion) objetoEntrante;
                    this.setName(clienteNuevo.getNombre());
                    yo = new ClienteConSocket(clienteNuevo, socket);
                    desplegarInformacionCliente();
                    Servidor.addCliente(yo);
                    salida = new ObjectOutputStream(socket.getOutputStream());
                    
                    salida.writeObject(new ConexionExitosa(Servidor.getListaClientes(), Servidor.getListaDeSalas()));

                    /**/
                    /**/
                    conectarBD();
                    if(isPrimeraVez()){
                        registrarPrimeraConexion();
                    }else{
                        actualizarUltimaConexion();
                    }
                    for (ClienteConSocket c : Servidor.getListaClientesConSocket()) {
                        if (!yo.getNombre().equals(c.getNombre())) {
                            /*Se le envía a cada uno el nuevo CLIENTE*/
                            salida = new ObjectOutputStream(c.getSocket().getOutputStream());
                            salida.writeObject(new ClienteNuevo(yo));
                            /*Se envía el mensaje "CLIENTE X se ha conectado.
                             *Se envía a todos, menos al CLIENTE que se acaba de conectar"*/
                            salida = new ObjectOutputStream(c.getSocket().getOutputStream());
                            salida.writeObject(new MensajeConexion(yo.getCodigo(), yo.getNick(), MENSAJE_CONEXION));
                        }
                    }
                    
                }else if(objetoEntrante instanceof ClienteEscribiendo){
                    ClienteEscribiendo ce = (ClienteEscribiendo)objetoEntrante;
                    Cliente clienteAquienEscribe = ce.getClienteAquienEscribe();
                    Socket s;
                    
                    if(ce.getSalaActual().equalsIgnoreCase("lobi")){
                        s = Servidor.getSocketDe(clienteAquienEscribe.getNombre());
                        
//                        for (ClienteConSocket c : Servidor.getListaClientesConSocket()) {
//                            if(c.getNombre().equalsIgnoreCase(clienteAquienEscribe.getNombre())){
//                                salida = new ObjectOutputStream(c.getSocket().getOutputStream());
//                                salida.writeObject(ce);
//                                break;
//                            }
//                        }
                    }else{
                        SalaServidor sala = Servidor.getSala(ce.getSalaActual());
                        s = sala.getSocketDe(clienteAquienEscribe.getNombre());
                    }
                    this.enviarObjeto(s, ce);
                    
                    
                }else if(objetoEntrante instanceof ClienteDejoDeEscribir){
                    ClienteDejoDeEscribir cdde = (ClienteDejoDeEscribir)objetoEntrante;
                    Cliente clienteAquienEscribe = cdde.getClienteAquienEscribe();
                    Socket s;
                    
                    if(cdde.getSalaActual().equalsIgnoreCase("lobi")){
                        s = Servidor.getSocketDe(clienteAquienEscribe.getNombre());
                    }else{
                        SalaServidor sala = Servidor.getSala(cdde.getSalaActual());
                        s = sala.getSocketDe(clienteAquienEscribe.getNombre());
                    }
                    this.enviarObjeto(s, cdde);
//                    for (ClienteConSocket c : Servidor.getListaClientesConSocket()) {
//                        if(c.getNombre().equalsIgnoreCase(clienteAquienEscribe.getNombre())){
//                            salida = new ObjectOutputStream(c.getSocket().getOutputStream());
//                            salida.writeObject(cdde);
//                            break;
//                        }
//                    }
                }else if(objetoEntrante instanceof PeticionEntrarSala){
                    System.out.println("PETICION DE ENTRAR A SALA");
                    System.out.println("==================================================");
                    PeticionEntrarSala pes = (PeticionEntrarSala)objetoEntrante;
                    SalaServidor sala = Servidor.getSala(pes.getNombreSala(), pes.getPassword());
                    Socket s = Servidor.getSocketDe(pes.getNombreClienteQueQuiereEntrarASala());
                    /*si sala == null , es que la pass esta mala*/
                    if(sala == null){
                        enviarObjeto(s, new PassSalaIncorrecta());
                    }else{
                        salaActual = sala.getNombreSala();
                        /*aca tengo que enviar el clientenuevo a todos los usuarios de la sala*/
                        System.out.println("Enviando el ClienteNuevo a los usuarios de la sala...");
                        for(Socket sockClienteSala : sala.getSocketsDeClientes()){
                            System.out.println("\tENV..");
                            enviarObjeto(sockClienteSala, new ClienteNuevoSala(Servidor.getCliente(pes.getNombreClienteQueQuiereEntrarASala())));
                        }
                        System.out.println("Hecho!");
                        
                        System.out.println("Agregando el cliente nuevo a la sala del server...");
                        Servidor.addClienteASala(yo, sala.getNombreSala());
                        System.out.println("Hecho!");
//                        sala = Servidor.getSala(pes.getNombreSala());
                        
                        /*ahora envio la lista de clientes del lobi y la lista de salas a TODOS*/
                        System.out.println("Enviando la lista de clientes del lobi y la lista de salas al LOBI...");
                        for(ClienteConSocket c : Servidor.getListaClientesConSocket()){
                            System.out.println("\tENV...");
                            enviarObjeto(c.getSocket(), new EnvioListas(Servidor.getListaClientes(), Servidor.getListaDeSalas()));
                        }
                        System.out.println("Hecho!");
                        
                        /*aca tengo que enviar un mensaje a todos los cleintes que el cliente entro en la sala x*/
                        
                        
                        
                        /*aca envio la lista de clientes de dicha sala al cliente que pidio entrar*/
                        System.out.println("Enviando la lista de clientes de la SALA NUEVA al cliente que pidio entrar...");
                        enviarObjeto(s, new ListaClientesSala(sala.getClientes(), sala.getNombreSala()));
                        System.out.println("Hecho!");
                        System.out.println("==================================================");
                    }
                }else if(objetoEntrante instanceof PeticionCerrarSala){
                    System.out.println("PETICION CERRAR SALA");
                    System.out.println("==================================================");
                    PeticionCerrarSala pcs = (PeticionCerrarSala)objetoEntrante;
                    SalaServidor sala = Servidor.getSala(pcs.getNombreSala());
                    
                    
                    /*con esto, envio a todos los clientes que estaban en el lobi, 
                     * los clientes "nuevos" de la sala*/
//                    for(Cliente c:sala.getClientes()){
//                        enviarObjeto(new ClienteNuevo(c));
//                    }
                    
                    /*remuevo la sala dela lista de salas del server
                     y agrego a los clientes de es sala en el lobi*/
                    Servidor.removeSala(sala);
                    
                    /*ahora envio la lista de clientes del lobi y la lista de salas a TODOS*/
                    for(ClienteConSocket c : Servidor.getListaClientesConSocket()){
                        enviarObjeto(c.getSocket(), new EnvioListas(Servidor.getListaClientes(), Servidor.getListaDeSalas()));
                    }
                    
                    for(Socket s :Servidor.getTodosLosSocketsDeClientesDeSalas()){
                        enviarObjeto(s,new EliminarSala(pcs));
                    }
                    Socket socketDe = Servidor.getSocketDe(pcs.getNombre());
                    ListaDeMisSalas ldms = new ListaDeMisSalas(Servidor.getSalasDe(pcs));
                    enviarObjeto(socketDe, ldms);
                    System.out.println("==================================================");
                    salaActual = "lobi";
                }else if(objetoEntrante instanceof Sala){
                    
                    Sala salaNueva = (Sala)objetoEntrante;
                    salaNueva.aumentarClientesEnUno();
                    
                    if(!Servidor.existeSala(salaNueva.getNombreSala())){
                        salaActual = salaNueva.getNombreSala();
                        System.out.println("Sala nueva enviada a todos");
                        enviarObjeto(salaNueva);

                        for(Socket s :Servidor.getTodosLosSocketsDeClientesDeSalas()){
                            enviarObjeto(s,salaNueva);
                        }

                        SalaServidor sala = new SalaServidor(salaNueva);
                        System.out.println();
                        System.out.println("Sala \""+sala.getNombreSala()+"\" agregada al server");
                        Servidor.addSala(sala);
                        Servidor.addClienteASala(yo, sala.getNombreSala());
                        sala = Servidor.getSala(sala.getNombreSala());
                        System.out.println("Clientes en sala: "+sala.getCantidadDeClientes());
                        System.out.println("cliente dueño ---> "+yo.getNombre()+" agregado a la sala");

                        enviarObjeto(socket, new ListaClientesSala(sala.getClientes(), sala.getNombreSala()));
                        System.out.println("Lista de Clientes de la sala nueva, enviada al dueño");
                        System.out.println();

                        /*ACA DESPUES TENGO QUE CREAR UN OBJETO QEU SEA CLIENTESE CAMBIO DE SALA
                         NO CLIENTE DESONECTADO*/
                        enviarObjeto(new ClienteCambioSala(yo.getCodigo(), yo.getNombre(), yo.getNick(), salaNueva.getNombreSala()));
                    }else{
                        enviarObjeto(socket, new SalaYaExiste(salaNueva.getNombreSala()));
                    }
                }else if(objetoEntrante instanceof AbandonarSala){
                    AbandonarSala as = (AbandonarSala)objetoEntrante;
                    Servidor.removeClienteDeSala(as.getNombre(), as.getNombreSala());
                    
                    for(Socket s: Servidor.getListaDeSocketsDeSala(as.getNombreSala())){
                        enviarObjeto(s, new RemoverUsuarioDeSala(as));
                    }
                    
                    /*ahora envio la lista de clientes del lobi y la lista de salas a TODOS*/
                    for(ClienteConSocket c : Servidor.getListaClientesConSocket()){
                        enviarObjeto(c.getSocket(), new EnvioListas(Servidor.getListaClientes(), Servidor.getListaDeSalas()));
                    }
                    salaActual = "lobi";
                }else if(objetoEntrante instanceof QuieroMisSalas){
                    QuieroMisSalas qms = (QuieroMisSalas)objetoEntrante;
                    Socket socketDe = Servidor.getSocketDe(qms.getNombre());
                    ListaDeMisSalas ldms = new ListaDeMisSalas(Servidor.getSalasDe(qms));
                    enviarObjeto(socketDe, ldms);
                }
            }
            System.out.println("Cliente " + this.getName() + " desconectado");
        } catch(java.net.SocketException e){
            try {
                System.out.println("SOCKET EXCEPTION, EL USUARIO CERRO LA CONEXION ABRUPTAMENTE");
                System.out.println("SALA ACTUAL DEL USER: " + salaActual);
                ClienteDesconectado clienteDesconectado = new ClienteDesconectado(yo.getCodigo(), yo.getNombre(), yo.getNick(), salaActual);
                if (clienteDesconectado.getSalaActual().equalsIgnoreCase("lobi")) {
                    /*se elimina de la lista de CLIENTES del SERVIDOR*/
                    Servidor.eliminarCliente(yo);
                    socket.close();
                    guardarEstadisticasChat();

                    for (ClienteConSocket c : Servidor.getListaClientesConSocket()) {
                        salida = new ObjectOutputStream(c.getSocket().getOutputStream());
                        salida.writeObject(clienteDesconectado);
                    }
                } else {
                    Servidor.removeClienteDeSala(clienteDesconectado.getNombre(), clienteDesconectado.getSalaActual());
                    Servidor.eliminarCliente(yo);

                    socket.close();


                    for (Socket c : Servidor.getListaDeSocketsDeSala(clienteDesconectado.getSalaActual())) {
                        salida = new ObjectOutputStream(c.getOutputStream());
                        salida.writeObject(clienteDesconectado);
                    }
                }
            } catch (IOException ex) {
            }
            System.out.println("Cliente " + this.getName() + " desconectado");
            guardarEstadisticasChat();
        } catch (IOException ex) {
            Logger.getLogger(HiloCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HiloCliente.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void desplegarInformacionCliente() {
        System.out.println("-----------------------------------------------");
        System.out.println("Nombre Host: " + direccionCliente.getHostName());
        System.out.println("IP: " + direccionCliente.getHostAddress());
        System.out.println("CLIENTE " + yo.getNombre());
        System.out.println("-----------------------------------------------");
    }

    /**
     * Método para enviar un objeto a todos
     * @param objeto 
     */
    private void enviarObjeto(Object objeto) {
        try {
            for (Socket s : Servidor.getListaDeSockets()) {
                salida = new ObjectOutputStream(s.getOutputStream());
                salida.writeObject(objeto);
            }
        } catch (IOException ex) {
            Logger.getLogger(HiloCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void enviarObjeto(Socket socket, Object objeto) {
        try {
            salida = new ObjectOutputStream(socket.getOutputStream());
            salida.writeObject(objeto);
        } catch (IOException ex) {
            Logger.getLogger(HiloCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static long getTime(){
        return Calendar.getInstance().getTimeInMillis();
    }
    
    private boolean isPrimeraVez(){
        boolean primeraVez = false;
        try {
            conexion.sentencia = conexion.con.createStatement();
            conexion.rs = conexion.sentencia.executeQuery("select * from tiempo_chat where cod_alu = '"+yo.getCodigo()+"'");
            if(!conexion.rs.next())
                primeraVez = true;
            conexion.sentencia.close();
        } catch (SQLException ex) {
            Logger.getLogger(HiloCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            return primeraVez;
        }
    }
    
    private  DatosConexion getDatosConexion() throws ParserConfigurationException, SAXException, IOException {
        
        final Tag con = DOM.procesarArchivoXMLDom(new File(RUTA_CONFIG_BD));
        
        return new DatosConexion() {

            @Override
            public String getServer() {
                Tag server = getTag("server");
                return server.getContenido();
            }

            @Override
            public String getUser() {
                return getAtributo("user", con).getValor();
            }

            @Override
            public String getPass() {
                return getAtributo("pass", con).getValor();
            }

            @Override
            public String getBaseDeDatos() {
                Tag bd = getTag("baseDeDatos");
                return bd.getContenido();
            }
            
            private Tag getTag(String nomTag){
                for(Tag t: con.getTagsHijos()){
                    if(t.getNombre().equalsIgnoreCase(nomTag)){
                        return t;
                    }
                }
                return null;
            }
            
            private Atributo getAtributo(String nomAtributo, Tag tag){
                for(Atributo a : tag.getAtributos()){
                    if(a.getNombre().equalsIgnoreCase(nomAtributo)){
                        return a;
                    }
                }
                return null;
            }
        };
    }
    
    private void conectarBD(){
        try {
            System.out.println("conectando a bd: user: "+yo.getNombre());
            conexion = new Conexion(getDatosConexion());
            System.out.println("conectado");
        } catch (ClassNotFoundException | SQLException | ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(HiloCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void desconectarBD(){
        try {
            System.out.println("desconectando de la BD: user: "+yo.getNombre());
            conexion.con.close();
        } catch (SQLException ex) {
            Logger.getLogger(HiloCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void registrarPrimeraConexion() {
        try {
            System.out.println("Registrando primera conexion en la BD");
            System.out.println("codigo alumno a registrar: "+yo.getCodigo());
            conexion.sentencia = conexion.con.createStatement();
            conexion.sentencia.execute("insert into tiempo_chat(cod_alu, pri_con, ult_con, dur_ult, dur_tot) "
           + "values('"+yo.getCodigo()+"',NOW(),NOW(),null,null)");
            conexion.sentencia.close();
            System.out.println("OK, codigo alumno: "+yo.getCodigo());
            this.timeIni = getTime();
        } catch (SQLException ex) {
            Logger.getLogger(HiloCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void guardarEstadisticasChat() {
        try {
            this.timeFin = getTime();
            long duracionTotal = 0;
            System.out.println("Guardando estadisticas del usuario en la BD...");
            
            
            conexion.sentencia = conexion.con.createStatement();
            conexion.rs = conexion.sentencia.executeQuery("select dur_tot from tiempo_chat where cod_alu = '"+yo.getCodigo()+"'");
            
            if(conexion.rs.next()){
                duracionTotal = conexion.rs.getLong("dur_tot");
            }
            conexion.sentencia.close();
            duracionTotal += (this.timeFin - this.timeIni);
            
            conexion.sentencia = conexion.con.createStatement();
            conexion.sentencia.execute("update tiempo_chat set dur_ult = '"+(this.timeFin - this.timeIni)+"', dur_tot = '"+duracionTotal+"' where cod_alu = '"+yo.getCodigo()+"'");
            conexion.sentencia.close();
            System.out.println("OK, codigo alumno: "+yo.getCodigo());
        } catch (SQLException ex) {
            Logger.getLogger(HiloCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void actualizarUltimaConexion() {
        try {
            System.out.println("Actualizando ultima conexion...");
            this.timeIni = getTime();
            conexion.sentencia = conexion.con.createStatement();
            conexion.sentencia.execute("update tiempo_chat set ult_con = NOW() where cod_alu = '"+yo.getCodigo()+"'");
            conexion.sentencia.close();
            System.out.println("OK, codigo alumno: "+yo.getCodigo());
        } catch (SQLException ex) {
            Logger.getLogger(HiloCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
