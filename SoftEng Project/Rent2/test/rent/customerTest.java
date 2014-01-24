/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author user
 */
public class customerTest {
	
	final private String con_host="jdbc:mysql://83.212.104.40:3306/garage";
	final private String con_user="root";
	final private String con_pass="mixaniki1992";
		
	Connection con = null;
	Statement st = null;
	ResultSet rs = null;
		
	User instance;	
	
	
	public customerTest() {
	}
	
	@BeforeClass
	public static void setUpClass() {
	}
	
	@AfterClass
	public static void tearDownClass() {
	}
	
	@Before
	public void setUp() {
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(con_host,con_user,con_pass);
			st=con.createStatement();
		}catch(Exception e){
			System.err.println(e);
		}
		
		instance = new customer("alkis","alk123",1);
	}
	
	@After
	public void tearDown() {
	}

	
	
	
	
	
		/**
	 * Test of rent method, of class customer.
	 */
	@Test
	public void testRent() {
		System.out.println("==============customerTest Begin=======================");
		System.out.println("Begin test for customer.rent .Parameters:\ntype='car',brand='BMW',selection_id=1\nThe test should return 0 (successfull rent)");
		int selection_id = 1;
		String type = "car";
		String brand = "BMW";
		
		boolean brandBased = true;
		
		int expResult = 0;
		int result = ((customer)instance).rent(selection_id, type, brand, con, st, rs, brandBased);
		System.out.println("Expected Result:"+expResult+" Actual Result:"+result);
		assertEquals(expResult, result);
		System.out.println("First test success\n\n");
		
		System.out.println("Begin second test for customer.rent .Parameters:\ntype='car',brand='bmw',selection_id=-1\nThe test should return 1(product not found)");
		selection_id=-1;
		type="car";
		brand="BMW";
		brandBased=true;
		expResult=1;
		result=((customer)instance).rent(selection_id, type, brand, con, st, rs, brandBased);
		System.out.println("Expected Result:"+expResult+" Actual Result:"+result);
		assertEquals(expResult, result);
		System.out.println("Second test success\n\n");
		
		System.out.println("Begin third test for customer.rent .Parameters:\ntype='car',brand='bmw',selection_id=1\nThe test should return 2(product already rented)");
		selection_id=1;
		type="car";
		brand="BMW";
		brandBased=true;
		expResult=2;
		result=((customer)instance).rent(selection_id, type, brand, con, st, rs, brandBased);
		System.out.println("Expected Result:"+expResult+" Actual Result:"+result);
		assertEquals(expResult, result);
		System.out.println("Third test success\n\n");
		
		
		System.out.println("Begin fourth test for customer.rent .Parameters:\ntype='car',brand='bmw',selection_id=1 and null Statement\nThe test should return -1(backend problem)");
		selection_id=1;
		type="car";
		brand="BMW";
		brandBased=true;
		expResult=-1;
		Statement s=null;
		result=((customer)instance).rent(selection_id, type, brand, con, s, rs, brandBased);
		System.out.println("Expected Result:"+expResult+" Actual Result:"+result);
		assertEquals(expResult, result);
		System.out.println("fourth test success\n\n");
		
		
		testShow_rents();
		System.out.println("===================End for customerTest.====================");
		// TODO review the generated test code and remove the default call to fail.

	}
	
	
	
	/**
	 * Test of show_rents method, of class customer.
	 */
	//@Test
	public void testShow_rents() {
		System.out.println("Begin test for customer.showRents .Return value should not be null since parameters are ok(no backend error)");
	
		ResultSet result = ((customer)instance).show_rents(con, st, rs);
		assertNotNull(result);
		System.out.println("First test successful.Results not null.\n\n");
		System.out.println("second test for customer.showRents .Return value should be null since there is a null statement that triggers a backend error");
		result = ((customer)instance).show_rents(con, null, rs);
		assertNull(result);
		System.out.println("Second test successful.Results are null.\n\n");
		
		testDelete_rents();
		// TODO review the generated test code and remove the default call to fail.
	}

	/**
	 * Test of delete_rents method, of class customer.
	 */
	//@Test
	public void testDelete_rents() {
		System.out.println("Begin test for customer.delete_rents .Cart should not be\nempty=>return value should be 0(success)");
		int expResult = 0;
		int result = ((customer)instance).delete_rents(con, st, rs);
		System.out.println("Expected result:"+expResult+" Actual Result:"+result);
		assertEquals(expResult, result);
		System.out.println("First test successful\n\n");
		
		
		System.out.println("Second test for customer.delete_rents .Cart should be\nempty=>return value should be 1(empty cart)");
		result=((customer)instance).delete_rents(con, st, rs);
		expResult=1;
		System.out.println("Expected result:"+expResult+" Actual Result:"+result);
		assertEquals(expResult, result);
		System.out.println("Second test successful");
		// TODO review the generated test code and remove the default call to fail.
	}


	
}
