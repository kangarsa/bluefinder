package pia;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Test;

public class PathFinderTest {
    
    private PathFinder pathFinder;
    private static final String FROM = "[from]";
    private static final String TO = "[to]";
        
    @Before
    public void setUp() {
        this.pathFinder = new PathFinder();
    }
    
    @After
    public void tearDown() {
    }
	/*public void test() throws UnsupportedEncodingException, ClassNotFoundException, SQLException {
		BipartiteGraphGenerator bgg = PIAConfigurationBuilder.interlanguageWikipedia(5);
		BipartiteGraphPathGenerator.resetTables();
		bgg.generateBiGraph("Abeja", "Queen");
		bgg.generateBiGraph("Abeja", "Charles_Darwin");
		bgg.generateBiGraph("Abeja", "Atanasio");
	}
	
	public static void main(String[] args) throws UnsupportedEncodingException, ClassNotFoundException, SQLException {
		System.out.println("Running...");
		BipartiteGraphGenerator bgg = PIAConfigurationBuilder.interlanguageWikipedia(5);
		BipartiteGraphPathGenerator.resetTables();
		//bgg.generateBiGraph("Abeja", "Queen");
		//bgg.generateBiGraph("Abeja", "Charles_Darwin");
		//bgg.generateBiGraph("Abeja", "Atanasio");
		//bgg.generateBiGraph("Abeja", "Mayo_franc�s");
		bgg.generateBiGraph("Mayo_franc�s", "Fran�ois_Mitterrand");
		}
*/ 

    /**
     * Test of incrementRegularGeneratedPaths method, of class PathFinder.
     */
    @Test
    public void testIncrementRegularGeneratedPaths() {
        System.out.println("incrementRegularGeneratedPaths");
        int expectedResult = this.pathFinder.getRegularGeneratedPaths() + 1;
        assertEquals(expectedResult, this.pathFinder.getRegularGeneratedPaths());
    }

    /**
     * Test of areDirectLinked method, of class PathFinder.
     */
    @Test
    public void testAreDirectLinked() {
        String from = "Rosario,_Santa_Fe";
        String to = "Lionel_Messi";
        try {
            boolean result = this.pathFinder.areDirectLinked(from, to);
            assertTrue("These should be linked.", result);
            from = "Liverpool";
            to = "Chris_Lawler";
            result = this.pathFinder.areDirectLinked(from, to);
            assertFalse("These should not be linked.", result);
        } catch (ClassNotFoundException ex) {
            fail("ClassNotFoundException");
            Logger.getLogger(PathFinderTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            fail("SQLException");
            Logger.getLogger(PathFinderTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of findPathBFS method, of class PathFinder.
     */
    @Test
    public void testFindPathBFS() throws Exception {
        System.out.println("findPathBFS");
        String from = "Liverpool";
        String to = "Chris_Lawler";
        boolean result = this.pathFinder.findPathBFS(from, to);
        assertTrue(result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPathsUsingCategories method, of class PathFinder.
     */
    @Test
    public void testGetPathsUsingCategories() {
        String from = "Liverpool";
        String to = "Chris_Lawler";
        try {
            List<List<String>> expectedResult = new ArrayList<List<String>>();
            
            List<String> tmpPath = new ArrayList<String>();
            tmpPath.add(FROM);
            tmpPath.add("Category:Sportspeople_from_" + FROM);
            tmpPath.add(TO);
            expectedResult.add(tmpPath);
            tmpPath.clear();
                    
            List<List<String>> result = this.pathFinder.getPathsUsingCategories(from, to);
            assertEquals(expectedResult + " expected,\ngot " + result, expectedResult, result);
        } catch (ClassNotFoundException ex) {
            fail("ClassNotFoundException");
            Logger.getLogger(PathFinderTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            fail("SQLException");
            Logger.getLogger(PathFinderTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            fail("UnsupportedEncodingException");
            Logger.getLogger(PathFinderTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        fail("Prototype");
    }
    
    @Test
    public void testGetCategoriesFromPage() throws ClassNotFoundException, SQLException, UnsupportedEncodingException {
        String pageName = "Rosario,_Santa_Fe";
        List<String> expectedResult = new ArrayList<String>();
        expectedResult.add("Rosario,_Santa_Fe");
        List<String> result = this.pathFinder.getCategoriesFromPage(pageName);
        assertEquals("Expected " + expectedResult + " got " + result, expectedResult, result);
    }    
    
    @Test
    public void testGetCategoriesFromPageWithoutCategories() throws ClassNotFoundException, SQLException, UnsupportedEncodingException {
        String pageName = "List_of_VIP";
        List<String> result = this.pathFinder.getCategoriesFromPage(pageName);
        assertTrue(result.isEmpty());
    }
    /**
     * Test of protected normalizeCategory method, of class PathFinder.
     */
    @Test
    public void testNormalizeCategory() {
        System.out.println("normalizeCategory");
        String subCategoryName = "";
        String fromPage = "Liverpool";
        String toPage = "Chris_Lawler";
        String expResult = "";
        String result = this.pathFinder.normalizeCategory(subCategoryName, fromPage, toPage);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRelevantDocuments method, of class PathFinder.
     */
    @Test
    public void testGetRelevantDocuments() throws Exception {
        System.out.println("getRelevantDocuments");
        String pathQuery = "";
        int expResult = 0;
        int result = this.pathFinder.getRelevantDocuments(pathQuery);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isReachablePath method, of class PathFinder.
     */
    @Test
    public void testIsReachablePath() throws Exception {
        System.out.println("isReachablePath");
        String pathQuery = "";
        String from = "";
        String to = "";
        boolean expResult = false;
        boolean result = this.pathFinder.isReachablePath(pathQuery, from, to);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}