/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases.generator;

import clases.configuraciones.Archivo;
import clases.utilidades.P;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author pato
 */
public class Proyecto implements java.io.Serializable{

    private int id;
    private String nombre;
    private String ruta;
    private List codigos = new ArrayList();
    private List librerias = new ArrayList();
    public static final int NO_ENCONTRADO = -1;
    private DefaultMutableTreeNode acodigosFuente;
    private DefaultMutableTreeNode alibrerias;
    private DefaultMutableTreeNode root;
    private DefaultMutableTreeNode aux;
    private boolean codigosExpandible;
    private boolean libreriasExpandible;
    private boolean proyectoExpandible;
//    public static List nombresProyectos = new ArrayList();

    public Proyecto(int id, String nombre, String ruta, String nombreCodigo, String nombreLibreria){
//        nombresProyectos.add(nombre);
        proyectoExpandible = true;
        root = new DefaultMutableTreeNode(nombre);

        acodigosFuente = new DefaultMutableTreeNode("Codigos Fuente");
        alibrerias = new DefaultMutableTreeNode("Librerias");
        acodigosFuente.add(alibrerias);

        this.id = id;
        try{
            new File(ruta).mkdir();
            new File(ruta+"/Codigos Fuente").mkdir();
            new File(ruta+"/Codigos Fuente/Librerias").mkdir();
//            new File(ruta+"/configuracion.dat").createNewFile();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

        this.nombre = nombre;
        this.ruta = ruta;

        if (!nombreCodigo.trim().equalsIgnoreCase("") ) {
            codigosExpandible = true;
            codigos.add(new Codigo(-1,nombreCodigo, false));
            Archivo.escribirEnAchivo(ruta+P.SEPARADOR+"Codigos Fuente"+P.SEPARADOR+nombreCodigo+".cpp", ((Codigo)codigos.get(codigos.size()-1)).getCodigo(), true, false);

            ((Codigo)codigos.get(codigos.size()-1)).setPath(ruta+P.SEPARADOR+"Codigos Fuente"+P.SEPARADOR+nombreCodigo+".cpp", ruta+P.SEPARADOR+"Codigos Fuente"+P.SEPARADOR+nombreCodigo);
            ((Codigo)codigos.get(codigos.size()-1)).modificarCodigoGuardado(true);
            ((Codigo)codigos.get(codigos.size()-1)).setNombre(ruta+P.SEPARADOR+"Codigos Fuente"+P.SEPARADOR+nombreCodigo+".cpp", true);
        }

        if (!nombreLibreria.trim().equalsIgnoreCase("") ) {
            libreriasExpandible = true;
            librerias.add(new Codigo(-1,nombreLibreria, true));
            Archivo.escribirEnAchivo(ruta+P.SEPARADOR+"Codigos Fuente"+P.SEPARADOR+"Librerias"+P.SEPARADOR+nombreLibreria+".h", ((Codigo)librerias.get(librerias.size()-1)).getCodigo(), true, false);

            ((Codigo)librerias.get(librerias.size()-1)).setPath(ruta+P.SEPARADOR+"Codigos Fuente"+P.SEPARADOR+"Librerias"+P.SEPARADOR+nombreLibreria+".h", ruta+P.SEPARADOR+"Codigos Fuente"+P.SEPARADOR+"Librerias"+P.SEPARADOR+nombreLibreria);
            ((Codigo)librerias.get(librerias.size()-1)).modificarCodigoGuardado(true);
            ((Codigo)librerias.get(librerias.size()-1)).setNombre(nombreLibreria+".h", false);
        }
    }

    public String getNombre() {
        return nombre;
    }

    public String getRuta() {
        return ruta;
    }


    public Codigo getCodigo(int indice){
        return (Codigo)codigos.get(indice);
    }

    public Codigo getLibreria(int indice){
        return (Codigo)librerias.get(indice);
    }

    public List getCodigos() {
        return codigos;
    }

    public List getLibrerias() {
        return librerias;
    }


    public static int getIndiceDeProyecto(String nombreDelProyecto, List proyectos){
        for(int i =0; i<proyectos.size(); i++){
            if(((Proyecto)proyectos.get(i)).getNombre().equalsIgnoreCase(nombreDelProyecto)){
                return i;
            }
        }
        return NO_ENCONTRADO;
    }

    public static int getIndiceDeCodigo(String nombreDelCodigo, List codigos){
//        System.out.println(codigos.size());
        for(int i =0; i<codigos.size(); i++){
            System.out.println("nmbre codigo lista: "+((Codigo)codigos.get(i)).getNombre());
            System.out.println("nombre parametro: "+nombreDelCodigo);
            if(((Codigo)codigos.get(i)).getNombre().equalsIgnoreCase(nombreDelCodigo)){
                return i;
            }
        }
        return NO_ENCONTRADO;
    }

    public static int getIndiceDeLibreria(String nombreDeLibreria, List librerias){
//        System.out.println(codigos.size());
        for(int i =0; i<librerias.size(); i++){
            System.out.println("nmbre libreria lista: "+((Codigo)librerias.get(i)).getNombre());
            System.out.println("nombre parametro: "+nombreDeLibreria);
            if(((Codigo)librerias.get(i)).getNombre().equalsIgnoreCase(nombreDeLibreria)){
                return i;
            }
        }
        return NO_ENCONTRADO;
    }

    public static int getId(String nombreDelProyecto, List proyectos) {
        for(int i =0; i<proyectos.size(); i++){
            if(((Proyecto)proyectos.get(i)).getNombre().equalsIgnoreCase(nombreDelProyecto)){
                return ((Proyecto)proyectos.get(i)).getId();
            }
        }
        return NO_ENCONTRADO;
    }

    public void crearNuevoCodigo(String rutaCompletaDelCodigoNuevo, String nombreCodigoSinExtension){
        codigos.add(new Codigo(-1,nombreCodigoSinExtension, false));
        
        Archivo.escribirEnAchivo(rutaCompletaDelCodigoNuevo, ((Codigo)codigos.get(codigos.size()-1)).getCodigo(), true, false);
        ((Codigo)codigos.get(codigos.size()-1)).setPath(rutaCompletaDelCodigoNuevo, rutaCompletaDelCodigoNuevo.substring(0, rutaCompletaDelCodigoNuevo.length()-3));
        ((Codigo)codigos.get(codigos.size()-1)).modificarCodigoGuardado(true);
        ((Codigo)codigos.get(codigos.size()-1)).setNombre(rutaCompletaDelCodigoNuevo, true);
    }

    public void crearNuevaLibreria(String rutaCompletaDeLaLibreriaNueva, String nombreLibreriaSinExtension){
        librerias.add(new Codigo(-1,nombreLibreriaSinExtension, true));

        Archivo.escribirEnAchivo(rutaCompletaDeLaLibreriaNueva, ((Codigo)librerias.get(librerias.size()-1)).getCodigo(), true, false);
        ((Codigo)librerias.get(librerias.size()-1)).setPath(rutaCompletaDeLaLibreriaNueva, rutaCompletaDeLaLibreriaNueva.substring(0, rutaCompletaDeLaLibreriaNueva.length()-1));
        ((Codigo)librerias.get(librerias.size()-1)).modificarCodigoGuardado(true);
        ((Codigo)librerias.get(librerias.size()-1)).setNombre(nombreLibreriaSinExtension+".h", false);
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    public DefaultMutableTreeNode getArbolDeProyecto(){
        root.add(acodigosFuente);
        return root;
    }

    public void añadirCodigoAlArbol(String nombreCodigoSinExtension){
        aux = new DefaultMutableTreeNode(nombreCodigoSinExtension+".cpp");
        acodigosFuente.add(aux);
    }

    public void añadirLibreriaAlArbol(String nombreLibreriaSinExtension){
        aux = new DefaultMutableTreeNode(nombreLibreriaSinExtension+".h");
        alibrerias.add(aux);
    }

//    public void setNombreDeProyectoAlArbol(String nombre){
//        root = new DefaultMutableTreeNode(nombre);
//        acodigosFuente = new DefaultMutableTreeNode("Códigos Fuente");
//        alibrerias = new DefaultMutableTreeNode("Librerías");
//    }

    public static boolean isProyecto(String nombreDelProyecto, List proyectos){
        for(int i =0; i<proyectos.size(); i++){
            if(((Proyecto)proyectos.get(i)).getNombre().equalsIgnoreCase(nombreDelProyecto)){
                return true;
            }
        }
        return false;
    }

    /**
     * @return the codigosExpandible
     */
    public boolean isCodigosExpandible() {
        return codigosExpandible;
    }

    /**
     * @param codigosExpandible the codigosExpandible to set
     */
    public void setCodigosExpandible(boolean codigosExpandible) {
        this.codigosExpandible = codigosExpandible;
    }

    /**
     * @return the libreriasExpandible
     */
    public boolean isLibreriasExpandible() {
        return libreriasExpandible;
    }

    /**
     * @param libreriasExpandible the libreriasExpandible to set
     */
    public void setLibreriasExpandible(boolean libreriasExpandible) {
        this.libreriasExpandible = libreriasExpandible;
    }

    /**
     * @return the proyectoExpandible
     */
    public boolean isProyectoExpandible() {
        return proyectoExpandible;
    }

    /**
     * @param proyectoExpandible the proyectoExpandible to set
     */
    public void setProyectoExpandible(boolean proyectoExpandible) {
        this.proyectoExpandible = proyectoExpandible;
    }
}
