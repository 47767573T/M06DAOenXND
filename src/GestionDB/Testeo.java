package GestionDB;

import org.xmldb.api.base.XMLDBException;

/**
 * Created by 47767573t on 31/03/16.
 */
public class Testeo {

    static String IP = "172.31.101.225";
    static String PUERTO = "8080";

    static String NOMBRE_COLECCION = "Villaverde";
    static String PATH_RECURSO = "/home/47767573t/Gitprojects/M06DAOenXND/src/GestionDB/mondial.xml";
    static String NOMBRE_RECURSO = "mondial.xml";

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, XMLDBException
            , IllegalAccessException {

        //CREAR DAO
        DAO dao = new DAO("admin", "dionis");

        String queryEnXQUERY = "";
        String queryEnXPATH = "";

        queryEnXQUERY = "doc(\"mondial.xml\")" ;

        queryEnXPATH = "//country/name";


        //INICIA CONEXION
        dao.iniciarConexion(IP, PUERTO);

        //CREA COLECCION
        dao.crearColeccion(NOMBRE_COLECCION);

        //INTRODUCE RECURSO
        dao.introducirRecurso(NOMBRE_COLECCION, PATH_RECURSO);

        //CONSULTAS DE PRUEBA
        String resultadoXPATH = dao.ConsultaEnXpath(queryEnXPATH);
        String resultadoXQUERY = dao.ConsultaEnXquery(queryEnXQUERY);

        //MUESTRA DE RESULTADOS DE CONSULTAS
        System.out.println("\n\n"+resultadoXPATH);
        System.out.println("\n\n"+resultadoXQUERY);

        //BORRA RECURSO
        dao.borrarRecurso(NOMBRE_COLECCION, NOMBRE_RECURSO);


    }
}
