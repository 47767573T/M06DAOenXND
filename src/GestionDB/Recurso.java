package GestionDB;

import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.XMLDBException;

import java.io.File;

/**
 * Created by 47767573t on 31/03/16.
 */
public class Recurso {

    public static boolean introducir (String uri, String login, String password, String nombreCol
            , String pathRecurso) {

        //Crear fichero con la ruta del recurso
        File fichero = new File(pathRecurso);

        //Invocar la coleccion donde añadir el recurso
        Collection col = null;

        try {

            col = DatabaseManager.getCollection(uri + "/db/"+nombreCol, login, password);

            //Añade el recuro
            Resource recurso = col.createResource(pathRecurso,"XMLResource");
            recurso.setContent(fichero);
            col.storeResource(recurso);

            return true;

        } catch (XMLDBException e) {
            System.out.println("ERROR DAO.Recurso.Introducir: "+e);

            return false;

        }
    }
}
