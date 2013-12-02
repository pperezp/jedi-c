/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import chat.modelo.Cliente;
import chat.modelo.Sala;
import chat.modelo.SalaServidor;
import chat.modelo.cliente.ClienteConSocket;

/**
 *
 * @author Patricio Pérez Pinto
 */
public class Servidor {

    private ServerSocket server;
    private int puerto;
    public static List<ClienteConSocket> clientes;
    public static List<SalaServidor> salas;

    public Servidor(int puerto) {
        this.puerto = puerto;
        clientes = new ArrayList<>();
        salas = new ArrayList<>();
    }

    public static synchronized void eliminarCliente(ClienteConSocket cliente) {
        System.out.println("Cliente Eliminado del Server: "+clientes.remove(cliente));
    }

    public static synchronized void addCliente(ClienteConSocket cliente) {
        clientes.add(cliente);
    }
    
    private static synchronized void eliminarClientePorNombre(String nombre){
        for(ClienteConSocket c : clientes){
            if(c.getNombre().equalsIgnoreCase(nombre)){
                clientes.remove(c);
                break;
            }
        }
    }

    public static synchronized void actualizarImagen(Cliente cliente) {
        for (ClienteConSocket c : clientes) {
            if (c.getNombre().equalsIgnoreCase(cliente.getNombre())) {
                c.setImagen(cliente.getImagen());
                break;
            }
        }
    }

    public static synchronized List<Socket> getListaDeSockets() {
        List<Socket> lista = new ArrayList();
        for (ClienteConSocket c : clientes) {
            lista.add(c.getSocket());
        }
        return lista;
    }
    
    public static synchronized List<Socket> getListaDeSocketsDeSala(String nombreSala){
        List<Socket> lista = new ArrayList();
        for(SalaServidor sala :salas){
            if(sala.getNombreSala().equalsIgnoreCase(nombreSala)){
                for(Socket s: sala.getSocketsDeClientes()){
                    lista.add(s);
                }
                break;
            }
        }
        return lista;
    }

    public static synchronized ArrayList<Cliente> getListaClientes() {
        ArrayList<Cliente> lista = new ArrayList<>();
        for (ClienteConSocket c : clientes) {
            lista.add(new Cliente(c.getCodigo(), c.getNombre(), c.getNick(), c.getImagen(), c.getEstilo()));
        }
        return lista;
    }
    
    public static synchronized ArrayList<ClienteConSocket> getListaClientesConSocket() {
        ArrayList<ClienteConSocket> lista = new ArrayList<>();
        for (ClienteConSocket c : clientes) {
            lista.add(c);
        }
        return lista;
    }
    
//    public static synchronized ArrayList<Cliente> getListaClientes(String seccion) {
//        ArrayList<Cliente> lista = new ArrayList<Cliente>();
//        for (ClienteConSocket c : clientes) {
//            if(c.getSeccion().equals(seccion)){
//                lista.add(new Cliente(c.getNombre(), c.getSeccion(), c.getNick(), c.getImagen(), c.getEstilo()));
//            }
//        }
//        return lista;
//    }
    
//    public static synchronized ArrayList<ClienteConSocket> getListaClientesConSocket(String seccion) {
//        ArrayList<ClienteConSocket> lista = new ArrayList<ClienteConSocket>();
//        for (ClienteConSocket c : clientes) {
//            if(c.getSeccion().equals(seccion)){
//                lista.add(c);
//            }
//        }
//        return lista;
//    }

    /**
     * obtiene al cliente ENTERO, CON FOTO Y TODO
     * @param nombre
     * @return 
     */
    public static synchronized Cliente getCliente(String nombre){
        Cliente cliente = null;
        for(ClienteConSocket c : clientes){
            if(c.getNombre().equalsIgnoreCase(nombre)){
                cliente = new Cliente(c.getCodigo(), c.getNombre(), c.getNick(), c.getImagen(), c.getEstilo());
                break;
            }
        }
        return cliente;
    }
    
    /**
     * Busca en TODOS LOS CLIENTES, TODOS INCLUYENDO LOS DE LAS SALAS
     * @param nombrecliente
     * @return 
     */
    public static synchronized Socket getSocketDe(String nombrecliente){
        for(ClienteConSocket c : clientes){
            if(c.getNombre().equalsIgnoreCase(nombrecliente)){
                return c.getSocket();
            }
        }
        for(SalaServidor s : salas){
            for(ClienteConSocket c : s.getClientesConSocket()){
                if(c.getNombre().equalsIgnoreCase(nombrecliente)){
                    return c.getSocket();
                }
            }
        }
        return null;
    }
    
    public static synchronized ArrayList<Sala> getListaDeSalas(){
        ArrayList<Sala> listaSalas = new ArrayList<>();
        for(SalaServidor s : salas){
            listaSalas.add(s.getSala());
        }
        return listaSalas;
    }
    
    public static synchronized void addSala(SalaServidor salaNueva){
        salas.add(salaNueva);
    }
    
    /*aca tengo que dejar a todos los clientes de esa sala, en la lista
     clientesConSockets*/
    public static synchronized void removeSala(SalaServidor sala){
        System.out.println("Lammando a remover sala");
        for(ClienteConSocket c: sala.getClientesConSocket()){
            clientes.add(c);
            System.out.println("Usuario "+c.getNombre()+" agregado a la lista de Clientes con Sockets");
        }
        boolean remove = salas.remove(sala);
        System.out.println("Sala "+sala.getNombreSala()+" removida de la lista de salas del Servidor");
    }
    
    public static synchronized SalaServidor getSala(String nombreSala, String pass){
        for(SalaServidor s : salas){
            if(s.getNombreSala().equalsIgnoreCase(nombreSala) && s.getPassword().equalsIgnoreCase(pass)){
                return s;
            }
        }
        return null;
    }
    
    /*metodo para obtener la sala cuando ya el usuario ha entrado*/
    public static synchronized SalaServidor getSala(String nombreSala){
        for(SalaServidor s : salas){
            if(s.getNombreSala().equalsIgnoreCase(nombreSala)){
                return s;
            }
        }
        return null;
    }
    
    public static synchronized ArrayList<Sala> getSalasDe(Cliente dueño){
        ArrayList<Sala> lista = new ArrayList<>();
        for(SalaServidor s : salas){
            if(s.getDueñoSala().getNombre().equalsIgnoreCase(dueño.getNombre())){
                lista.add(s.getSala());
            }
        }
        return lista;
    }
    
    /**
     * este método elimina automáticamente al cliente de la lista general
     * @param clienteNuevo
     * @param nombreSala 
     */
    public static synchronized void addClienteASala(ClienteConSocket clienteNuevo, String nombreSala){
        
        for(SalaServidor sala : salas){
            if(sala.getNombreSala().equalsIgnoreCase(nombreSala)){
                sala.addCliente(clienteNuevo);
                sala.aumentarClientesEnUno();
                eliminarClientePorNombre(clienteNuevo.getNombre());
                break;
            }
        }
    }

    public static synchronized List<SalaServidor> getSalas() {
        return salas;
    }
    
    
    public static synchronized ArrayList<Socket> getTodosLosSocketsDeClientesDeSalas(){
        ArrayList<Socket> lista = new ArrayList<>();
        for(SalaServidor sala :salas){
            for(Socket s: sala.getSocketsDeClientes()){
                lista.add(s);
            }
        }
        return lista;
    }
    
    public static synchronized boolean existeSala(String nombreSala) {
        for(SalaServidor sala:salas){
            if(sala.getNombreSala().equalsIgnoreCase(nombreSala)){
                return true;
            }
        }
        return false;
    }
    
    /**
     * Este metodo saca al cliente de la sala y lo pone en el lobi
     * @param nombreCliente
     * @param nombreSala 
     */
    public static synchronized void removeClienteDeSala(String nombreCliente, String nombreSala){
        for(SalaServidor sala: salas){
            if(sala.getNombreSala().equalsIgnoreCase(nombreSala)){
                ClienteConSocket clienteNuevo = sala.removeCliente(nombreCliente);
                System.out.println("===================================");
                System.out.println("Sacando a Cliente "+nombreCliente+" de sala: "+nombreSala);
                addCliente(clienteNuevo);
                System.out.println(nombreCliente+" agregado al Lobi");
                System.out.println("===================================");
                break;
            }
        }
    }
    
    public void iniciarServidor() {
        try {
            server = new ServerSocket(puerto);
            System.out.println("===================================");
            System.out.println("Servidor Iniciado");
            System.out.println("Servidor: "+InetAddress.getLocalHost().getHostAddress()+" ó "+InetAddress.getLocalHost().getCanonicalHostName());
            System.out.println("Puerto: "+puerto);
            System.out.println("===================================");
            while (true) {
                Socket cliente;
                cliente = server.accept();
                HiloCliente h = new HiloCliente(cliente);
                h.start();
            }
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void detenerServidor() {
        try {
            server.close();
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
