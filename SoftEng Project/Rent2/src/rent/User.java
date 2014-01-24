/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rent;

/**
 *
 * @author user
 */
public class User {
	String username;
	String password;
	int id;
	int privileges;
	
	public User(String username,String password,int id,int priv){
		this.username=username;
		this.password=password;
		this.id=id;
		privileges=priv;
	}
	
}
