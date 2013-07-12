/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mkaminose
 */
public class PiaDb {
    
    public static int getCategoryPageId(String categoryTitle) {
        int id = getPageId(categoryTitle, 14);
        return id;
    }
    
    public static int getPageId(String pageTitle) {
        int id = getPageId(pageTitle, 0);
        return id;
    }
    
    private static int getPageId(String pageTitle, int namespace) {
        int id = 0;
        try {
            Connection c = WikipediaConnector.getConnection();
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery("Select page_id from page where page_namespace="+namespace+" and page_title=\"" + pageTitle + "\"");
            if (rs.next()) {
                id = rs.getInt("page_id");
            }
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(PiaDb.class.getName()).log(Level.SEVERE, pageTitle, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PiaDb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
}
