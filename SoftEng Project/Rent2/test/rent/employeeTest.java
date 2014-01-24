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
import javax.swing.JOptionPane;
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
public class employeeTest {
	
	final private String con_host="jdbc:mysql://83.212.104.40:3306/garage";
	final private String con_user="root";
	final private String con_pass="mixaniki1992";
		
	Connection con = null;
	Statement st = null;
	ResultSet rs = null;
		
	User instance;	
	
	public employeeTest() {
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
		
		instance = new employee("employee","emp",50);
		
		
	}
	
	@After
	public void tearDown() {
	}

	
	@Test
	public void testInsert_model() {
		System.out.println("==============employeeTest Begin=======================");
		System.out.println("Begin test 1 for employee.insert_model() .Parameters:type='car'\nname='M3'\nbrand='BMW'\nCC=2000\nprice=10000\nThe test should return 0 (successfull insert)");
		
		String name="M3";
		String brand="BMW";
		String type="car";
		int cc=2000;
		int price=10000;
		
		int expResult=0;
		int res=((employee)instance).insert_model(con, st, rs, type, name, brand, cc, price);
		assertEquals(expResult,res);
		System.out.println("Test passed\n");
	
		System.out.println("Begin test 2 for employee.insert_model() .Parameters:type='plane'\nname='M3'\nbrand='BMW'\nCC=2000\nprice=10000\nThe test should return 1 (wrong type)");
		name="M3";
		brand="BMW";
		type="plane";
		cc=2000;
		price=10000;
		
		expResult=1;
		res=((employee)instance).insert_model(con, st, rs, type, name, brand, cc, price);
		if(res!=expResult) fail("that was bad..");
		assertEquals(expResult,res);
		System.out.println("Test passed\n");
		
		System.out.println("Begin test 3 for employee.insert_model() .Parameters:type='car'\nname='M'\nbrand='BMW'\nCC=2000\nprice=10000\nThe test should return 2 (name length)");
		name="M";
		brand="BMW";
		type="car";
		cc=2000;
		price=10000;
		
		expResult=2;
		res=((employee)instance).insert_model(con, st, rs, type, name, brand, cc, price);
		assertEquals(expResult,res);
		System.out.println("Test passed\n");
		
		
		System.out.println("Begin test 4 for employee.insert_model() .Parameters:type='car'\nname='M3'\nbrand='Something'\nCC=2000\nprice=10000\nThe test should return 3 (Brand)");
		name="M3";
		brand="Something";
		type="car";
		cc=2000;
		price=10000;
		
		expResult=3;
		res=((employee)instance).insert_model(con, st, rs, type, name, brand, cc, price);
		assertEquals(expResult,res);
		System.out.println("Test passed\n");
		
		
		System.out.println("Begin test 5 for employee.insert_model() .Parameters:type='car'\nname='M3'\nbrand='BMW'\nCC=-1\nprice=10000\nThe test should return 4 (invalid CC number)");
		name="M3";
		brand="BMW";
		type="car";
		cc=-1;
		price=10000;
		
		expResult=4;
		res=((employee)instance).insert_model(con, st, rs, type, name, brand, cc, price);
		assertEquals(expResult,res);
		System.out.println("Test passed\n");
		
		
		
		System.out.println("Begin test 6 for employee.insert_model() .Parameters:type='car'\nname='M3'\nbrand='BMW'\nCC=2000\nprice=-1\nThe test should return 5 (ivnalid price number)");
		name="M3";
		brand="BMW";
		type="car";
		cc=2000;
		price=-1;
		
		expResult=5;
		res=((employee)instance).insert_model(con, st, rs, type, name, brand, cc, price);
		assertEquals(expResult,res);
		System.out.println("All Tests for insert_model passed\n\n");
		testShow_storage();
		
		
		
		
	}
	
	
	
	

	public void testShow_storage() {
		System.out.println("Begin test 1 for employee.show_storage() .parameters:con,st,rs\nThe test should not return null (successful resultset)\n");
		assertNotNull(((employee)instance).show_storage(con, st, rs));
		System.out.println("passed: resultset not null\n");
		
		System.out.println("Begin test 2 for employee.show_storage() .parameters:null st rs\nThe test should return null (backend error)\n");
		Statement s=null;
		assertNull(((employee)instance).show_storage(con, s, rs));
		System.out.println("passed: resultset is null\nAll tests for show_storage() passed\n\n");
		
		testRemove_model();
		
	}
	


	public void testRemove_model() {
		System.out.println("Begin test 1 for employee.remove_model() .Parameters:id=last_insert_id()\nThe test should return 0 (successful)\n");
		int id=-1;
		try{
			rs=st.executeQuery("select last_insert_id()");
			rs.next();
			id=rs.getInt("last_insert_id()");
		}catch(SQLException e){System.err.println(e);return ;}
		
		if(id==-1)return;
		assertEquals(0,((employee)instance).remove_model(con, st, rs, id));
		System.out.println("passed\n");
		
		System.out.println("Begin test 2 for employee.remove_model() .Parameters:id=4000\nThe test should return 1 (no such product)");
		id=4000;
		assertEquals(1,((employee)instance).remove_model(con, st, rs, id));
		System.out.println("passed\n");
		
		
		System.out.println("Begin test 3 for employee.remove_model() .Parameters:id=-1\nThe test should return -1 (invalid id)");
		id=-1;
		assertEquals(-1,((employee)instance).remove_model(con, st, rs, id));
		System.out.println("passed\n");
		
		System.out.println("Begin test 4 for employee.remove_model() .Parameters:id=1,con=null\nThe test should return -2 (backend error)");
		id=10000;
		Statement s=null;
		assertEquals(-2,((employee)instance).remove_model(con, s, rs, id));
		System.out.println("passed\n");
		
		System.out.println("===========================all tests passed=============================");
		
		
		
		
		
		
		
		
	}

	
}
