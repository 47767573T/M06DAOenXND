package GestionDB;

import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQPreparedExpression;
import javax.xml.xquery.XQResultSequence;

/**
 * Created by 47767573t on 31/03/16.
 */
public class Consulta {

    public static String enXPATH(String query, XQConnection connection) {

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

            return resultado;

        } catch (XQException e) {
            System.out.println("ERROR DAO.Consulta.enXPATH: " + e);

            return "Consulta no realizada";
        }
    }

    public static String enXQUERY(String query, XQConnection connection) {

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
            return resultado;


        } catch (XQException e) {
            System.out.println("ERROR DAO.Consulta.enXPATH: " + e);

            return "Consulta no realizada";
        }
    }
}
