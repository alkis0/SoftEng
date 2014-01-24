/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.tree.TreePath;
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
public class GUITest {
	final private String con_host="jdbc:mysql://83.212.104.40:3306/garage";
	final private String con_user="root";
	final private String con_pass="mixaniki1992";
	boolean br=false;
	Connection con;
	Statement st;
	ResultSet rs;
	
	GUI x;
	
	public GUITest() {
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
		
		x=new GUI();
	}
	
	@After
	public void tearDown() {
	}

	/**
	 * Test of panel method, of class GUI.
	 */
	
	@Test
	public void test_loginActionPerformed(){
		
		
		//set test property true and login with a user
		x.test=true;
		x.test_case=0;
		x.login.doClick();//login
		x.login.doClick();//logout
		
		//login as employee		
		x.test_case=1;
		x.login.doClick();//login
		x.login.doClick();//logout
		//login as admin
		x.test_case=2;
		x.login.doClick();//login
		x.login.doClick();//logout
		//login as not exist
		x.test_case=3;
		x.login.doClick();//login
		test_Menu();
		test_Feature3();
	
	}
	@Test
	public void test_Menu(){
		TreePath path=new TreePath("Products,Cars,BMW");
		x.Menu.setSelectionPath(path);
		x.Menu.action(null, null);
	}
	
	
	
	public void test_Feature3(){
		//login as simple user
		x.test_case=0;
		x.login.doClick();

		//test rent's return codes.
		x.test_case=0;
		x.feature3.doClick();
		
		x.test_case=1;
		x.feature3.doClick();
		
		x.test_case=2;
		x.feature3.doClick();
		
		x.test_case=3;
		x.feature3.doClick();
		//-------------------
		
		//relog as employee
		x.login.doClick();
		x.test_case=1;
		x.login.doClick();
		
		x.feature3.doClick();
		
		//login as admin
		x.login.doClick();
		x.test_case=2;
		x.login.doClick();
		
		x.feature3.doClick();
		
		x.login.doClick();//logout
		
		test_Feature2();
		
		
	}
	
	public void test_Feature2(){
		//login as user
		x.test_case=0;
		x.login.doClick();
		
		//delete rents with test_case=0 => deletion succesfull
		x.feature2.doClick();
		
		//delete rents with test_case=1 => no rents to delete
		x.test_case=1;
		x.feature2.doClick();
		
		
		//delete rents with test_case=2 => backend error
		x.test_case=2;
		x.feature2.doClick();
		
		
		
		x.login.doClick();//logout
		x.test_case=1;
		x.login.doClick();//login as employee
		
		//remove model with test_case=0 => deletion success
		x.test_case=0;
		x.feature2.doClick();
		
		//remove model with test_case=1 => product does not exist
		x.test_case=1;
		x.feature2.doClick();
		
		//remove model with test_case=2 => invalid product id
		x.test_case=2;
		x.feature2.doClick();
		
		//remove model with test_case=3 => backend problem
		x.test_case=3;
		x.feature2.doClick();
		
		x.login.doClick();//logout
		x.test_case=2;
		x.login.doClick();//login as admin
		
		//remove user with test_case=0 => successful deletion
		x.test_case=0;
		x.feature2.doClick();
		
		//test_case=1 => user does not exist
		x.test_case=1;
		x.feature2.doClick();
		//test_case=2 => invalid id 
		x.test_case=2;
		x.feature2.doClick();
		
		//test_case=3 => backend
		x.test_case=3;
		x.feature2.doClick();
		
		x.login.doClick();//logout
		test_Feature1();
	
	}
	
	
	public void test_Feature1(){
		//login as user
		x.test_case=0;
		x.login.doClick();
		
		//show rents for user
		x.feature2.doClick();
		
		x.login.doClick();//logout and login as employee
		x.test_case=1;
		x.login.doClick();
		
		//test_case=0 => successful insert
		x.test_case=0;
		x.feature1.doClick();
		
		//test_case=1 => enter car or cycle
		x.test_case=1;
		x.feature1.doClick();
		
		//test_case=2 => valid name
		x.test_case=2;
		x.feature1.doClick();
		
		//test_case=3 => valid brand
		x.test_case=3;
		x.feature1.doClick();
		
		//test_case=4 => valid cc
		x.test_case=4;
		x.feature1.doClick();
		
		//test_case=5 => valid price
		x.test_case=5;
		x.feature1.doClick();
		
		
		//test_case=6 => backend
		x.test_case=6;
		x.feature1.doClick();
		
		//relog as admin
		x.login.doClick();
		x.test_case=2;
		x.login.doClick();
		
		
		//test_case=0 => successful insert
		x.test_case=0;
		x.feature1.doClick();
		
		//test_case=1 => valid username
		x.test_case=1;
		x.feature1.doClick();
		
		//test_case=2 => valid password
		x.test_case=2;
		x.feature1.doClick();
		
		//test_case=3 => valid privilleges
		x.test_case=3;
		x.feature1.doClick();
		
		//test_case=4 => backend
		x.test_case=4;
		x.feature1.doClick();
		
	
		x.login.doClick();
		test_Search();
	}
	
	public void test_Search(){
		//search by brand without price restrictions
		x.byname.setSelected(true);
		x.search_text.setText("M3");//brand!=name
		x.SearchButton.doClick();
		
		x.search_text.setText("BMW");//brand
		x.SearchButton.doClick();
		
		//search byname without price restrictions
		x.byname.setSelected(false);
		x.bybrand.setSelected(true);
		
		x.search_text.setText("M3");//name
		x.SearchButton.doClick();
		
		x.search_text.setText("BMW");//Brand not equals name
		x.SearchButton.doClick();
		
		//search by name with low price restriction
		x.byname.setSelected(true);
		x.bybrand.setSelected(false);
		x.low_prices.setSelected(true);
		
		
		x.search_text.setText("M3");//name and high price
		x.SearchButton.doClick();
		
		x.search_text.setText("BMW");//brand and high price
		x.SearchButton.doClick();
		
		x.search_text.setText("punto");//name and low price
		
		//search by brand with low price restriction
		
		
		
		
		
		
		
		
		
		
	}
	
	
}
