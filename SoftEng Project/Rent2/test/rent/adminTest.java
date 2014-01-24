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
public class adminTest {
	
	final private String con_host="jdbc:mysql://83.212.104.40:3306/garage";
	final private String con_user="root";
	final private String con_pass="mixaniki1992";
	
	Connection con = null;
	Statement st = null;
	ResultSet rs = null;
	
	User instance;
	
	public adminTest() {
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
		
		instance = new admin("admin","admin",45);
		
	}
	
	@After
	public void tearDown() {
	}

	
	
	
	/**
	 * Test of insert_user method, of class admin.
	 */
	@Test
	public void testInsert_user() {
		
		System.out.println("==============adminTest Begin=======================");
		System.out.println("Begin test 1 for admin.insert_user() .Parameters:Username='test',password='test',privileges=1\nThe test should return 0 (successfull insert)");
		
		String name="test";
		String password="test";
		int privileges=1;
		
		int res=((admin)instance).insert_user(con, st, rs, name, password, privileges);
		assertEquals(0,res);
		System.out.println("Test passed\n");
	
		System.out.println("Begin test 2 for admin.insert_user() .Parameters:Username='a',password='test',privileges=1\nThe test should return 1 (short username)");
		name="a";
		
		
		
		res=((admin)instance).insert_user(con, st, rs, name,password,privileges);
		assertEquals(1,res);
		System.out.println("Test passed\n");
		
		
		
		System.out.println("Begin test 3 for admin.insert_user() .Parameters:Username='test',password='a',privileges=1\nThe test should return 2 (short password)");
		name="test";
		password="a";
		privileges=1;
	
		res=((admin)instance).insert_user(con, st, rs, name,password,privileges);
		assertEquals(2,res);
		System.out.println("Test passed\n");
		
		
		System.out.println("Begin test 4 for admin.insert_user() .Parameters:Username='test',password='test',privileges=100\nThe test should return 3 (wrong privileges(1-3))");
		name="test";
		password="test";
		privileges=100;
		res=((admin)instance).insert_user(con, st, rs,name,password,privileges);
		assertEquals(3,res);
		System.out.println("Test passed\n");
		
		
		System.out.println("Begin test 5 for admin.insert_model() .Parameters:Username='test',password='test',privileges=1,st=null.\nThe test should return -1 (backend)");
		name="test";
		password="test";
		privileges=1;
	
		res=((admin)instance).insert_user(null, st, rs,name,password,privileges);
		assertEquals(-1,res);
		System.out.println("Test passed\n");
		
		System.out.println("all test passed for admin.insert_user()");
		testShow_users();
		
	}


	public void testShow_users() {
		
		System.out.println("Begin test 1 for admin.show_users() .parameters:con,st,rs\nThe test should not return null (successful resultset)\n");
		assertNotNull(((admin)instance).show_users(con, st, rs));
		System.out.println("passed: resultset not null\n");
		
		System.out.println("Begin test 2 for employee.show_storage() .parameters:con null rs\nThe test should return null (backend error)\n");
		
		assertNull(((admin)instance).show_users(con, null, rs));
		System.out.println("passed: resultset is null\nAll tests for show_users() passed\n\n");
		testDelete_user();
	
	}
	
	
	
	public void testDelete_user() {
		
		System.out.println("Begin test 1 for admin.delete_user() .Parameters:id=last_insert_id()\nThe test should return 0 (successful)\n");
		int id=-1;
		try{
			rs=st.executeQuery("select last_insert_id()");
			rs.next();
			id=rs.getInt("last_insert_id()");
		}catch(SQLException e){System.err.println(e);return ;}
		
		if(id==-1)return;
		assertEquals(0,((admin)instance).delete_user(con, st, rs, id));
		System.out.println("passed\n");
		
		System.out.println("Begin test 2 for admin.delete_user() .Parameters:id=4000\nThe test should return 1 (no such user)");
		id=4000;
		assertEquals(1,((admin)instance).delete_user(con, st, rs, id));
		System.out.println("passed\n");
		
		
		System.out.println("Begin test 3 for admin.delete_user() .Parameters:id=-1\nThe test should return -1 (invalid id)");
		id=-1;
		assertEquals(-1,((admin)instance).delete_user(con, st, rs, id));
		System.out.println("passed\n");
		
		System.out.println("Begin test 4 for admin.delete_user() .Parameters:id=1,con=null\nThe test should return -2 (backend error)");
		id=10000;
		assertEquals(-2,((admin)instance).delete_user(con, null, rs, id));
		System.out.println("passed\n");
		
		System.out.println("===========================all tests passed=============================");

	}

	
	
}
