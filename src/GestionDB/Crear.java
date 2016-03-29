package GestionDB;

import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.CollectionManagementService;

/**
 * Created by 47767573t on 29/03/16.
 */
public class Crear {

    static String driver = "org.exist.xmldb.DatabaseImpl";

    public boolean crearColeccion(String direccionServer, String login, String password, String nombre){

        try {

            //Inicial db driver y manager
            Class cl = Class.forName(driver);
            Database db = (Database) cl.newInstance();
            db.setProperty("create-database", "true");

            DatabaseManager.registerDatabase(db);

            //montando direccion de coleccion
            String uri = "xmldb:exist://"+direccionServer+"/exist/xmlrpc";
            Collection col = DatabaseManager.getCollection(uri+"/db", login, password);

            CollectionManagementService colService = (CollectionManagementService) col.getService("CollectionManagementService", "1.0");
            colService.createCollection(nombre);


            return true;

        } catch (ClassNotFoundException e) {
            System.out.println("ERROR GestionDB.Crear.crearColeccion: "+e);
            return false;
        } catch (InstantiationException e) {
            System.out.println("ERROR GestionDB.Crear.crearColeccion: "+e);
            return false;
        } catch (IllegalAccessException e) {
            System.out.println("ERROR GestionDB.Crear.crearColeccion: "+e);
            return false;
        } catch (XMLDBException e) {
            System.out.println("ERROR GestionDB.Crear.crearColeccion: "+e);
            return false;
        }
    }

}
