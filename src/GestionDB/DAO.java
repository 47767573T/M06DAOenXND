package GestionDB;

import net.xqj.exist.ExistXQDataSource;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.CollectionManagementService;

import javax.xml.xquery.*;

/**
 * Created by 47767573t on 29/03/16.
 */
public class DAO {

    static XQDataSource xqs;
    static XQConnection xconn;
    static String driver = "org.exist.xmldb.DatabaseImpl";

    static String uri = "";
    static String admin = "";
    static String password = "";

    static String PUERTO_DB = "";
    static String IP_DB = "";

    /**
     * Realiza la conexion a la base de datos
     * @param ip ip de la data base
     * @param puerto puerto de la database
     * @return true = hay conexion, false = no hay conexion
     */
    public boolean iniciarConexion(String ip, String puerto){

        this.PUERTO_DB = puerto;
        this.IP_DB = ip;

        boolean hayConexion = false;

        String direccionServer = ip+":"+puerto;
        uri = "xmldb:exist://"+direccionServer+"/exist/xmlrpc";
        xqs = new ExistXQDataSource();

        try {
            xqs.setProperty("serverName", ip);
            xqs.setProperty("port", puerto);
            xconn = xqs.getConnection();    //Realiza la llamada para conexion

            hayConexion = true;

        } catch (XQException e) {
            System.out.println("ERROR en DAO.IniciarConexion: "+e );
        }

        return hayConexion;
    }

    /**
     * Crear una coleccion en la base de datos determinada antes
     * @param nombreCol el nombre de la base de datos que se le quiere dar
     * @return booleano true = db creada, false = no creada
     */
    public boolean crearColeccion (this.uri, this.admin, this.password, String nombreCol) {

        try {

            //Inicial db driver y manager
            Class cl = Class.forName(driver);
            Database db = (Database) cl.newInstance();
            db.setProperty("create-database", "true");

            DatabaseManager.registerDatabase(db);

            Collection col = DatabaseManager.getCollection(uri+"/db", login, password);

            //Creando la coleccion
            CollectionManagementService colService = (CollectionManagementService) col.getService("CollectionManagementService", "1.0");
            colService.createCollection(nombre);

            System.out.println("Creada coleccion");

            return true;

        } catch (ClassNotFoundException e) {
            System.out.println("ERROR en DAO.crearColeccion/Coleccion.crear: "+e);
            return false;
        } catch (InstantiationException e) {
            System.out.println("ERROR en DAO.crearColeccion/Coleccion.crear: "+e);
            return false;
        } catch (IllegalAccessException e) {
            System.out.println("ERROR en DAO.crearColeccion/Coleccion.crear: "+e);
            return false;
        } catch (XMLDBException e) {
            System.out.println("ERROR en DAO.crearColeccion/Coleccion.crear: "+e);
            return false;
        }

        return Coleccion.crear(this.uri, this.admin, this.password, nombreCol);
    }

    /**
     * Introducir un recurso en una coleccion dada
     * @param nombreCol Nombre de la coleccion
     * @param pathRecurso Path absoluta del recurso xml a introducir
     * @return boolean true = introducido, false = no introducido
     */
    public boolean introducirRecurso (String nombreCol, String pathRecurso){

        return Recurso.introducir(this.uri, this.admin, this.password, nombreCol, pathRecurso);
    }

    /**
     * Borrar un recurso en una coleccion dada
     * @param nombreCol Nombre de la coleccion
     * @param nombreRecurso nombre del recurso xml a borrar
     * @return boolean true = borrado, false = no borrado
     */
    public boolean borrarRecurso (String nombreCol, String nombreRecurso){

        return Recurso.borrar(this.uri, this.admin, this.password, nombreCol, nombreRecurso);
    }

    /**
     * Crea consultas en una base de datos determinada en XPath
     * @param query La consulta a realizar en la base de datos
     * @return Resultado de la consulta
     */
    public String ConsultaEnXpath (String query){

        return Consulta.enXPATH(query, this.xconn);
    }

    /**
     * Crea consultas en una base de datos determinada en XQuery
     * @param query La consulta a realizar en la base de datos
     * @return Resultado de la consulta
     */
    public String ConsultaEnXquery (String query){

        return Consulta.enXQUERY(query, this.xconn);
    }

    //CONSTRUCTORS

    public DAO() {  }

    public DAO(String admin, String password) {
        this.admin = admin;
        this.password = password;
    }

    //GETTERS
    public static XQDataSource getXqs() {
        return xqs;
    }

    public static XQConnection getXconn() {
        return xconn;
    }

    public static String getUri() {
        return uri;
    }

    public static String getAdmin() {
        return admin;
    }

    public static String getPassword() {
        return password;
    }

    //SETTERS
    public static void setXqs(XQDataSource xqs) {
        DAO.xqs = xqs;
    }

    public static void setXconn(XQConnection xconn) {
        DAO.xconn = xconn;
    }

    public static void setUri(String uri) {
        DAO.uri = uri;
    }

    public static void setAdmin(String admin) {
        DAO.admin = admin;
    }

    public static void setPassword(String password) {
        DAO.password = password;
    }

    public static boolean crearColeccion(String uri, String login, String password, String nombre){


    }

}
