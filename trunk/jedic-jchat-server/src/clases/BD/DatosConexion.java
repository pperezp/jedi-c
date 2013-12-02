/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases.BD;

/**
 *
 * @author Administrador
 */
public interface DatosConexion {

    /**
     *
     * @return el servidor del motor de Base de datos
     */
    String getServer();

    /**
     *
     * @return el usuario de la conexión a la base de datos
     */
    String getUser();

    /**
     *
     * @return la password de la conexión a la base de datos
     */
    String getPass();

    /**
     *
     * @return el nombre de la base de datos
     */
    String getBaseDeDatos();
}
