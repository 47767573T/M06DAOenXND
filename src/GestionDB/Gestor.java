package GestionDB;

import net.xqj.exist.ExistXQDataSource;

import javax.xml.xquery.*;

/**
 * Created by 47767573t on 29/03/16.
 */
public class Gestor {

    static XQDataSource xqs;
    static XQConnection xconn;
    static String direccionServer;

    public static boolean iniciarConexion (String ip, String puerto){

        direccionServer = ip+":"+puerto;
        xqs = new ExistXQDataSource();

        try {
            xqs.setProperty("serverName", ip);
            xqs.setProperty("port", puerto);
            xconn = xqs.getConnection();    //Realiza la llamada para conexion

            return true;

        } catch (XQException e) {
            System.out.println("ERROR GestionDB.Gestor.iniciarConexion: "+e );

            return false;
        }
    }

    public static boolean crearColeccion(String login, String password, String nombreCol) {

        return Crear.Coleccion(direccionServer, login, password, nombreCol);

    }



}
