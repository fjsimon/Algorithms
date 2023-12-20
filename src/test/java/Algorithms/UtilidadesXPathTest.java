package Algorithms;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import Utils.UtilidadesXPath;


public class UtilidadesXPathTest {

	@Test
	public void testXmlquery() {
    	String expression1 = "/bookstore/bookList/book[contains(title,\"The Game\")]";    	
    	String expression2 = "/bookstore/bookList/book[author=\"Neil Strauss\"]";
    	String expression3 = "/bookstore[location=\"Frankfurt Airport\"]";
    	
    	assertEquals(1, UtilidadesXPath.xmlquery(expression1), "This expression must return 1");
    	assertEquals(1, UtilidadesXPath.xmlquery(expression2), "This expression must return 1");
        assertEquals(1, UtilidadesXPath.xmlquery(expression3), "This expression must return 1");
	}


}
