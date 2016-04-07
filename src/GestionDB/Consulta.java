package GestionDB;

import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQPreparedExpression;
import javax.xml.xquery.XQResultSequence;

/**
 * Created by 47767573t on 31/03/16.
 */
public class Consulta {

    /**
     * Realiz una consulta con la query recibida
     * @param query Consulta a realizar
     * @param connection La instancia de la conexion a la base de datos
     * @return resultado de la consulta
     */
    public static String enXPATH (String query, XQConnection connection) {

        String linea = "";
        String resultado = "";
        XQPreparedExpression xqpe = null;
        try {

            xqpe = connection.prepareExpression(query);
            XQResultSequence xqrs = xqpe.executeQuery();
            while (xqrs.next()) {

                linea = xqrs.getItemAsString(null);
                resultado += "\n"+linea;
            }

            System.out.println("Consulta realizada");

            return resultado;

        } catch (XQException e) {
            System.out.println("ERROR en DAO.ConsultaEnXpath/Consulta.enXPATH: " + e);

            return "Consulta no realizada";
        }
    }

    /**
     * Realiza una consulta con la query recibida
     * @param query Consulta a realizar
     * @param connection La instancia de la conexion a la base de datos
     * @return resultado de la consulta
     */
    public static String enXQUERY (String query, XQConnection connection) {

        String linea = "";
        String resultado = "";

        XQPreparedExpression xqpe = null;

        try {
            xqpe = connection.prepareExpression(query);
            XQResultSequence xqrs = xqpe.executeQuery();

            while (xqrs.next()) {

                linea = xqrs.getItemAsString(null);
                resultado += linea;
            }

            System.out.println("Consulta realizada");

            return resultado;


        } catch (XQException e) {
            System.out.println("ERROR en DAO.ConsultaEnXQuery/Consulta.enXQUERY: " + e);;

            return "Consulta no realizada";
        }
    }
}
