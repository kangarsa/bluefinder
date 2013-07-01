
package pia;

import db.WikipediaConnector;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 *
 * @author dtorres
 * Main class to execute the Pia Index Algorithm. This class receives from console the basic params to initialize the indexing.
 * The PIA Index is represented by means of three Mysql tables: U_Page: pairs of Wikipedia pages, V_Normalized: path queries and
 * UxV: the edges set. This main class invokes the BipartiteGraphGenerator class.
 */
public class BipartiteGraphPathGenerator {
    
    private static final String DBPEDIA_PREFIX = "http://dbpedia.org/resource/";
        
    public static void resetTables() throws ClassNotFoundException, SQLException, UnsupportedEncodingException{
        Connection conReserarch = WikipediaConnector.getResultsConnection();
        Statement st = conReserarch.createStatement();
        st.executeUpdate("truncate table NFPC");
        st.executeUpdate("truncate table UxV");
        st.executeUpdate("truncate table V_Normalized");
        st.executeUpdate("truncate table U_page");
    }

    public static void main(String[] args) throws UnsupportedEncodingException, ClassNotFoundException, SQLException {
        Connection conReserarch = WikipediaConnector.getResultsConnection();
        Statement st = conReserarch.createStatement();
        int counter = 0;

        if (args.length < 4 || args[0].equalsIgnoreCase("help")) {
            System.out.println("Usage: <inf_limit> <max_limit> <iterations_limit> <from_to_table>");
            System.out.println("Where:");
            System.out.println("\t\t<inf_limit> is a number which represents the min row in from_to_table\n\t\t<max_limit> is a number\n\t\t <iterations_limit> is a number\n\t\t<from_to_table> name of the sources table.");
            return;
        }
        
        int inf_limit = Integer.parseInt(args[0]);
        int max_limjt = Integer.parseInt(args[1]);
        int iterations = Integer.parseInt(args[2]);
        String from_to_table = args[3];
        String dbpediaPrefix = DBPEDIA_PREFIX;
        if (args.length >= 4) {
            dbpediaPrefix = args[4];
        }

        long start = System.nanoTime();
        BipartiteGraphGenerator bgg = new BipartiteGraphGenerator(iterations);
        resetTables();

        ResultSet resultSet = st.executeQuery("SELECT * FROM " + from_to_table + " limit " + inf_limit + " ," + max_limjt);
        while (resultSet.next()) {
            String to = resultSet.getString("to");
            to = URLDecoder.decode(to, "UTF-8");
            String from = resultSet.getString("from");
            from = URLDecoder.decode(from, "UTF-8");
            from = from.replace(dbpediaPrefix, "");
            to = to.replace(dbpediaPrefix, "");
            System.out.println("Processing paths from " + from + " to " + to + "CASE: " + counter++);
            bgg.generateBiGraph(from, to);
        }

        long elapsedTimeMillis = System.nanoTime() - start;
        
        System.out.println("Regular generated paths = " + bgg.getRegularGeneratedPaths());
        System.out.println("Elapsed time in nanoseconds" + elapsedTimeMillis);

        System.out.println("Finalized !!!!");
        st.close();
        conReserarch.close();
    }
}
