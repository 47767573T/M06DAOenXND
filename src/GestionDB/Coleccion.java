package GestionDB;

import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.CollectionManagementService;

/**
 * Created by 47767573t on 29/03/16.
 */
public class Coleccion {

    static String driver = "org.exist.xmldb.DatabaseImpl";

    public static boolean crear(String uri, String login, String password, String nombre){

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

            return true;

        } catch (ClassNotFoundException e) {
            System.out.println("ERROR DAO.Coleccion.crear: "+e);
            return false;
        } catch (InstantiationException e) {
            System.out.println("ERROR DAO.Coleccion.crear: "+e);
            return false;
        } catch (IllegalAccessException e) {
            System.out.println("ERROR DAO.Coleccion.crear: "+e);
            return false;
        } catch (XMLDBException e) {
            System.out.println("ERROR DAO.Coleccion.crear: "+e);
            return false;
        }
    }

}
