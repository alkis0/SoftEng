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
import java.util.logging.MemoryHandler;
import javax.swing.JOptionPane;

/**
 *
 * @author user
 */

//first type of user.
public class customer extends User {

	public customer(String username,String password,int id){
		super(username,password,id,1);
	}
	
	
	/**
	 * public ResultSet show_rents.Returns the result of selection query for success and null 
	 * for fail.
	 *
	 */
	public ResultSet show_rents(Connection con,Statement st,ResultSet rs){
		String query="select * from rent where user_id="+this.id;
		try{
			rs=st.executeQuery(query);
		}catch(Exception e){System.err.println("Error in show_rents "+e);return null;}
		return rs;
	
	}
	/***
	 *	public int delete_rents.Clears the user rent car.Returns 0 for success
	 *  and other error codes:1 for empty cart,-1 for backend error.
	 * 
	 * 
	 */
	public int delete_rents(Connection con,Statement st,ResultSet rs){
		
		String query;			
		try{
			
			query="select * from rent where user_id="+this.id;
			rs=st.executeQuery(query);
			if(rs.next()==false){
				return 1;
			}else{
				//ArrayList that contains the product id of all rented items of user.
				ArrayList<Object> product_id=new ArrayList<Object>();
				int p;
				int i=0;
				do{
					p=rs.getInt("product_id");
					product_id.add(p);
				}while(rs.next());	
				int j=product_id.size();
				for(i=0;i<j;i++){
					//foreach product id update the storage table and set rented=no.
					//System.out.println(product_id.get(i));
					query="update storage set rented='no' where id="+product_id.get(i);
					st.executeUpdate(query);
				}
				//finally delete the rent table records for user.
				query="delete from rent where user_id="+this.id;
				st.executeUpdate(query);
				
			}
		}catch(Exception e){System.err.println(e);return -1;};
		
		return 0;
	
	}
	
	
	/**
	 *	Public int rent.User rents a product.Return 0 for success and other error codes:
	 *	1 for product not found,2 for product already rented,-1 for backend error.
	 * 
	 * 
	 */
	
	public int rent(int selection_id,String type,String brand,Connection con,Statement st,ResultSet rs,boolean brandBased){
		
					String rented;
					String query;
					
					//if the rent action is brandBased (from menu selection) then the customer can rent from the product list that
					//appears in screen.
					//else 
					if(brandBased) query="select * from storage where type='"+type+"' and brand='"+brand+"' and id="+selection_id;
					else{
						ArrayList<Object> items=GUI.items_list;
						boolean ok=false;
						for(int i=0;i<items.size();i++)
							if(selection_id==(int)items.get(i)) ok=true;
						if(ok) query="select * from storage where id="+selection_id;
						else return 1;
					}
					try{
						rs=st.executeQuery(query);
						if(rs.next()==false){
							//model not found error code=1
							return 1;
							
						}
						else{
							int product_id=rs.getInt("id");
							type=rs.getString("type");
							String name=rs.getString("name");
							brand=rs.getString("brand");
							int price=rs.getInt("price");
							rented=rs.getString("rented");

							if(rented.equals("no"))	{
								query="update storage set rented='yes' where id="+product_id;
								st.executeUpdate(query);
								PreparedStatement ps=con.prepareStatement("insert into rent values(?,?,?,?,?,?)");
								ps.setInt(1,this.id);
								ps.setInt(2,product_id);
								ps.setString(3,type);
								ps.setString(4,name);
								ps.setString(5,brand);
								ps.setInt(6,price);
								ps.addBatch();

								ps.execute();
							}else{
								//product already rented error code=2
								return 2;
							}

							
						}
					}catch(Exception e){
						System.err.println(e);
						return -1;
					}

					//succes code = 0
					return 0;
				
	
	}
	
	
	
	
}
