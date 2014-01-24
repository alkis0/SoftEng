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
import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public class admin extends User{
	
	public admin(String username,String password,int id){
		super(username,password,id,3);
	}
	
	
	public int insert_user(Connection con,Statement st,ResultSet rs,String uname,String password,int privileges){
			
				
			if(uname.length()<3 || uname==null) return 1;
			if(password.length()<3 || password==null) return 2;
			if(privileges>3 || privileges<1) return 3;
			
			try{
				PreparedStatement ps=con.prepareStatement("insert into users(username,permissions,password) values(?,?,?)");
				ps.setString(1, uname);
				ps.setInt(2, privileges);
				ps.setString(3, password);
				ps.addBatch();
				ps.execute();
			
			}catch(Exception e){System.err.println(e);return -1;}
			
			return 0;
			
	
	}
	
	public int delete_user(Connection con,Statement st,ResultSet rs,int id){
		if(id<0) return -1;
		try{
			String query="delete from users where id="+id;
			int ret=st.executeUpdate(query);
			if(ret==0) return 1;
		}catch(Exception e){System.err.println(e);return -2;}
		return 0;
	}
	
	public ResultSet show_users(Connection con,Statement st,ResultSet rs){
		String query="select * from users";
		try{
			rs=st.executeQuery(query);
		}catch(Exception e){System.err.println(e);return null;}
		return rs;
	}
	
}
