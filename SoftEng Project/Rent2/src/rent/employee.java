/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author user
 */
public class employee extends User{

	public employee(String username,String password,int id){
		super(username,password,id,2);
		
	}
	
	public int insert_model(Connection con,Statement st,ResultSet rs,String type,String name,String brand,int CC,int price){
		String query;
	
		if(!type.equals("car") && !type.equals("cycle")) return 1;
		if(name.length()<2) return 2;
		if(!brand.equals("BMW") && !brand.equals("Audi") && !brand.equals("Fiat") && !brand.equals("Mercedes")&& !brand.equals("Fiat") 
		&& !brand.equals("Golf") && !brand.equals("Seat") && !brand.equals("Ford") && !brand.equals("Toyota")) 
			return 3;
		if(CC<=0)return 4;
		if(price<=0) return 5;
		try{
			PreparedStatement ps=con.prepareStatement("insert into storage(type,name,brand,CC,rented,price) values(?,?,?,?,?,?)");
			ps.setString(1, type);
			ps.setString(2, name);
			ps.setString(3, brand);
			ps.setInt(4, CC);
			ps.setString(5, "no");
			ps.setInt(6, price);
			ps.addBatch();
			
			ps.execute();
		}catch(SQLException e){System.err.println(e);return -1;}
		return 0;
	}
	
	
	public int remove_model(Connection con,Statement st,ResultSet rs,int id){
		
		String query="delete from storage where id="+id;
		if(id<0) return -1;
		try{
			int ret=st.executeUpdate(query);
			if(ret==0) return 1;
		}catch(Exception e){System.err.println(e);return -2;}
		
		
		return 0;
	}
	
	public ResultSet show_storage(Connection con,Statement st,ResultSet rs){
		String query="select * from storage";
		try{
			rs=st.executeQuery(query);
		}catch(Exception e){System.err.println(e);return null;}
		return rs;
	
	}
	
	
}
