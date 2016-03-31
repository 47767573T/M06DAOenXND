package GestionDB;

import net.xqj.exist.ExistXQDataSource;

import javax.xml.xquery.*;

/**
 * Created by 47767573t on 29/03/16.
 */
public class DAO {

    static XQDataSource xqs;
    static XQConnection xconn;

    static String uri = "";
    static String admin = "";
    static String password = "";

    
    public static boolean iniciarConexion (String ip, String puerto){

        String direccionServer = ip+":"+puerto;
        uri = "xmldb:exist://"+direccionServer+"/exist/xmlrpc";
        xqs = new ExistXQDataSource();

        try {
            xqs.setProperty("serverName", ip);
            xqs.setProperty("port", puerto);
            xconn = xqs.getConnection();    //Realiza la llamada para conexion

            return true;

        } catch (XQException e) {
            System.out.println("ERROR DAO.IniciarConexion: "+e );

            return false;
        }
    }

    public boolean crearColeccion(String nombreCol) {

        return Coleccion.crear(this.uri, this.admin, this.password, nombreCol);
    }

    public boolean introducirRecurso(String nombreCol, String pathRecurso){

        return Recurso.introducir(this.uri, this.admin, this.password, nombreCol, pathRecurso);
    }

    public String ConsultaEnXpath(String query){

        return Consulta.enXPATH(query, this.xconn);
    }

    public String ConsultaEnXquery(String query){

        return Consulta.enXQUERY(query, this.xconn);
    }

    //CONSTRUCTORS
    public DAO() {
    }

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
}
