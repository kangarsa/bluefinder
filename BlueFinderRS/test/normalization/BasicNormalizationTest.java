/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package normalization;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mkaminose
 */
public class BasicNormalizationTest {
    
    private BasicNormalization normalizator;
    
    public BasicNormalizationTest() {
    }
    
    @Before
    public void setUp() {
        this.normalizator = new BasicNormalization();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of normalizeCategory method, of class BasicNormalization.
     */
    @Test
    public void testNormalizeCategory() {
        System.out.println("normalizeCategory");
        String subCategoryName = "People_from_Liverpool";
        String fromName = "Liverpool";
        String toName = "Chris_Lawler";
        String expResult = "People_from_" + this.normalizator.getFrom();
        String result = this.normalizator.normalizeCategory(subCategoryName, fromName, toName);
        assertEquals(expResult, result);
    }

    /**
     * Test of normalizePage method, of class BasicNormalization.
     */
    @Test
    public void testNormalizePage() {
        System.out.println("normalizePage");
        String pageName = "";
        String fromName = "";
        String toName = "";
        BasicNormalization instance = new BasicNormalization();
        String expResult = "";
        String result = instance.normalizePage(pageName, fromName, toName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}