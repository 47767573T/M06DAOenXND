package GestionDB;

import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.XMLDBException;

import java.io.File;

/**
 * Created by 47767573t on 31/03/16.
 */
public class Recurso {

    private static String driver = "org.exist.xmldb.DatabaseImpl";

    /**
     * Introduce un recurso en la base de datos dada
     *
     * @param uri         localizacion de directorios donde aloja la coleccion
     * @param login       clave de login
     * @param password    clave de password
     * @param nombreCol   nombre de la coleccion donde se desea introducir el recurso
     * @param pathRecurso path absoluta del archivo que se desee introducir
     * @return boolean true = introducido, false no introducido
     */
    public static boolean introducir(String uri, String login, String password, String nombreCol
            , String pathRecurso) {

        boolean esIntroducido = false;

        Class cl = null;
        try {
            cl = Class.forName(driver);
            Database database = null;
            database = (Database) cl.newInstance();
            database.setProperty("create-database", "true");

            // crear el Manager del DB
            DatabaseManager.registerDatabase(database);

            //Crear fichero con la ruta del recurso
            File fichero = new File(pathRecurso);

            //Invocar la coleccion donde añadir el recurso
            Collection col = null;
            col = DatabaseManager.getCollection(uri + "/db/" + nombreCol, login, password);

            //Añade el recuro
            Resource recurso = col.createResource(pathRecurso, "XMLResource");
            recurso.setContent(fichero);
            col.storeResource(recurso);

            System.out.println("Recurso introducido");

            esIntroducido = true;

        } catch (ClassNotFoundException e) {
            System.out.println("ERROR en DAO.introducirRecursos/Recurso.introducir: " + e);
        } catch (InstantiationException e) {
            System.out.println("ERROR en DAO.introducirRecursos/Recurso.introducir: " + e);
        } catch (IllegalAccessException e) {
            System.out.println("ERROR en DAO.introducirRecursos/Recurso.introducir: " + e);
        } catch (XMLDBException e) {
            System.out.println("ERROR en DAO.introducirRecursos/Recurso.introducir: " + e);
        }

        return esIntroducido;

    }

    public static boolean borrar(String uri, String login, String password, String nombreCol
            , String nombreRecurso) {

        boolean esIntroducido = false;

        Class cl = null;
        try {
            cl = Class.forName(driver);
            Database database = null;
            database = (Database) cl.newInstance();
            database.setProperty("create-database", "true");

            // crear el Manager del DB
            DatabaseManager.registerDatabase(database);

            //Invocar la coleccion donde añadir el recurso
            Collection col = null;
            col = DatabaseManager.getCollection(uri + "/db/" + nombreCol, login, password);

            //Añade el recurso
            Resource recurso = col.createResource(nombreRecurso, "XMLResource");
            col.removeResource(recurso);

            System.out.println("El recurso esta borrado.");

            esIntroducido = true;

        } catch (ClassNotFoundException e) {
            System.out.println("ERROR en DAO.introducirRecursos/Recurso.borrar: " + e);
        } catch (InstantiationException e) {
            System.out.println("ERROR en DAO.introducirRecursos/Recurso.borrar: " + e);
        } catch (IllegalAccessException e) {
            System.out.println("ERROR en DAO.introducirRecursos/Recurso.borrar: " + e);
        } catch (XMLDBException e) {
            System.out.println("ERROR en DAO.introducirRecursos/Recurso.borrar: " + e);
        }

        return esIntroducido;

    }
}
