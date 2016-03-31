package GestionDB;

/**
 * Created by 47767573t on 31/03/16.
 */
public class Testeo {

    static String IP = "172.31.101.225";
    static String PUERTO = "8080";

    static String NOMBRE_COLECCION = "Villaverde";
    static String PATH_RECURSO = "mondial.xml";

    public static void main(String[] args) {

        DAO dao = new DAO("admin", "admin");

        String queryEnXQUERY = "";
        String queryEnXPATH = "";


        queryEnXQUERY = "\"for $country in /mondial//country[count(.//city) > 2]\\n\" +\n"
                + "\"let $cities_pops := (\\n\" +\n"
                + "\"\\tfor $c in $country//city[population]\\n\" +\n"
                +"\"\\tlet $pnum := number($c/population[1])\\n\" +\n"
                +"\"\\torder by $pnum descending\\n\" +\n"
                +"\"\\treturn $c/population[1]\\n\" +\n"
                +"\")\\n\" +\n"
                +"\"return\\n\" +\n"
                +"\"<result>\\n\" +\n"
                +"\"\\t{$country/name}\\n\" +\n"
                +"\"\\t<three-cities>\\n\" +\n"
                +"\"\\t\\t{sum($cities_pops[position()<=3])}\\n\" +\n"
                +"\"\\t</three-cities>\\n\" +\n"
                +"\"</result>\";";

        queryEnXPATH = "doc(\"" + PATH_RECURSO + "\")/COUNTRY/";



        dao.iniciarConexion(IP, PUERTO);
        dao.crearColeccion(NOMBRE_COLECCION);
        dao.introducirRecurso(NOMBRE_COLECCION, PATH_RECURSO);
        String resultadoXPATH = dao.ConsultaEnXpath(queryEnXPATH);
        String resultadoXQUERY = dao.ConsultaEnXquery(queryEnXQUERY);

        System.out.println("\n\n"+resultadoXPATH);
        System.out.println("\n\n"+resultadoXQUERY);

    }
}
