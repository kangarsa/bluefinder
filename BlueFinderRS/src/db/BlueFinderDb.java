/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mkaminose
 */
public class BlueFinderDb {
    
    public static int saveU_page(String page) {
        int status = 0;
        String strQuery = "INSERT INTO U_page (page) VALUES (\"" + page + "\")";
        try {
            Connection conn = WikipediaConnector.getResultsConnection();
            Statement stmt = conn.createStatement();
            status = stmt.executeUpdate(strQuery);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BlueFinderDb.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BlueFinderDb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
    }
    
    public static int saveU_page(String from, String to) {
        String page = "(" + from + ", " + to + ")";
        return saveU_page(page);
    }
    
    public static int getU_pageId(String page) {
        int result = 0;
        try {
            Connection c = WikipediaConnector.getResultsConnection();
            Statement st = c.createStatement();
            String query_text = "SELECT id FROM U_page where page=\"" + page + "\"";

            ResultSet rs = st.executeQuery(query_text);
            if (rs.next()) {
                result = rs.getInt("id");
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BlueFinderDb.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BlueFinderDb.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return result;
        }
    }

    public static int saveNFPC(String from, String to) {
        int status = 0;
        try {            
            Connection c = WikipediaConnector.getResultsConnection();
            Statement st = c.createStatement();
            String strQuery = "INSERT INTO NFPC (v_from, u_to) VALUES (\"" + from + "\",\"" + to + "\")";
            status = st.executeUpdate(strQuery);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BlueFinderDb.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BlueFinderDb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
    }
    
    public static int removeNFPC(int id) {
        int status = 0;
        try {
            Connection c = WikipediaConnector.getResultsConnection();
            Statement st = c.createStatement();
            String query_text = "DELETE FROM NFPC where id = " + id;
            status = st.executeUpdate(query_text);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BlueFinderDb.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BlueFinderDb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
    }
    
    public static int saveUxV(int pageId, int pathId) {
        int status = 0;
        try {
            Connection c = WikipediaConnector.getResultsConnection();
            Statement st = c.createStatement();
            String query_text = "INSERT INTO UxV (u_from, v_to, description) VALUES (" + pageId + "," + pathId + ",\" \")";
            status = st.executeUpdate(query_text);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BlueFinderDb.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BlueFinderDb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
    }

    public static int saveV_Normalized(String normalizedPath) {
        int status = 0;
        try {
            Connection conn = WikipediaConnector.getResultsConnection();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO V_Normalized (path) VALUES (?)");
            stmt.setString(1, normalizedPath);
            status = stmt.executeUpdate();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BlueFinderDb.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BlueFinderDb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
    }

    public static int getV_NormalizedId(String normalizedPath) {
        int id = 0;
        try {            
            Connection c = WikipediaConnector.getResultsConnection();
            Statement st = c.createStatement();
            String query_text = "SELECT id FROM V_Normalized where path=\"" + normalizedPath + "\"";
            ResultSet rs = st.executeQuery(query_text);
            if (rs.next()) {
                id = rs.getInt("id");
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BlueFinderDb.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BlueFinderDb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
}
