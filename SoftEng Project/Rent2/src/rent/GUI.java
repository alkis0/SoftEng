/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rent;

import com.sun.corba.se.impl.logging.ORBUtilSystemException;
import java.awt.Panel;
import javax.swing.JOptionPane;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;

     
/**
 *
 * @author user
 */
public class GUI extends javax.swing.JFrame {
	final private String con_host="jdbc:mysql://83.212.104.40:3306/garage";
	final private String con_user="root";
	final private String con_pass="mixaniki1992";
	
	public Connection con;
	public Statement st;
	public ResultSet rs;
	boolean logged_in=false;
	String tokens[]=new String[3];
	boolean brandBased=true;
	public static ArrayList<Object> items_list;
	boolean test=false;
	int test_case=0;
	
	User user;

	
	public GUI() {
		
		initComponents();
		Results.setText("Welcome!");
		feature1.setVisible(false);
		feature2.setVisible(false);
		byname.setSelected(true);
		setVisible(true);
		//setSize(900,610);
	
		
		
	}
	public static ArrayList<String> panel(int in_number,String in_labels[],String title){
		ArrayList<String> ret=new ArrayList<String>(in_number);
		
				
		JTextField txt[]=new JTextField[in_number];
		JPanel myPanel = new JPanel();
		myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));
		
		
		for(int i=0;i<in_number;i++){
			txt[i]=new JTextField(20);
			myPanel.add(new JLabel(in_labels[i]));
			myPanel.add(txt[i]);
		}
		
		

		int result = JOptionPane.showConfirmDialog(null, myPanel, 
				 title, JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			for(int i=0;i<in_number;i++){
				ret.add(txt[i].getText());
			}

		}else return null;
		
		
		return ret;
	}
	
	
	public static ArrayList<String> p_panel(int in_number,String in_labels[],int pass_pos,String title){
		ArrayList<String> ret=new ArrayList<String>(in_number);
		
				
		JTextField txt[]=new JTextField[in_number-1];
		JPasswordField pw=new JPasswordField(20);
		
		JPanel myPanel = new JPanel();
		myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));
		
		int k=0;
		for(int i=0;i<in_number;i++){	
			
			myPanel.add(new JLabel(in_labels[i]));
			if(i==pass_pos) myPanel.add(pw);
			else{ 
				txt[k]=new JTextField(20);
				myPanel.add(txt[k++]);
			}
		}
		
		k=0;

		int result = JOptionPane.showConfirmDialog(null, myPanel, 
				 title, JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			for(int i=0;i<in_number;i++){
				if(i==pass_pos) ret.add(new String(pw.getPassword()));
				else ret.add(txt[k++].getText());
			}

		}else return null;
		
		
		return ret;
	}
	
	
	public void eternal(){
		while(true){
			try{
				Thread.sleep(40);
			}catch(Exception e){}
			
			
			if(quality.isSelected()){
				low_prices.setEnabled(false);
				quality.setEnabled(true);
			}else if(low_prices.isSelected()){
				low_prices.setEnabled(true);
				quality.setEnabled(false);
			}else{
				low_prices.setEnabled(true);
				quality.setEnabled(true);
			}
		}			
		
	}
	
	
	public boolean print(int field_num,String field_title[],String field_name[],ResultSet rs) throws SQLException{
		if(rs.next()==false) return false;
		else{
			Results.setText("");
			for(int i=0;i<field_num;i++){
				if(i==field_num-1)
					Results.append(field_title[i]+"\n");
				
				else
					Results.append(field_title[i]+"\t");
			}
			do{
				for(int i=0;i<field_num;i++){
					if(i==field_num-1)
						Results.append(rs.getString(field_name[i])+"\n");
					else
						Results.append(rs.getString(field_name[i])+"\t");
				
				}
			}while(rs.next());
			
		}
		
		return true;
	}
	

	
	public boolean connect(String host,String username,String password){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(host,username,password);
			st=con.createStatement();
		}catch(Exception e){
			System.err.println(e);return false;
		}
		return true;
	}
	
	


	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
 regenerated by the GUI Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        name_brand = new javax.swing.ButtonGroup();
        jTextField1 = new javax.swing.JTextField();
        Scroll = new javax.swing.JScrollPane();
        Results = new javax.swing.JTextArea();
        search_text = new javax.swing.JTextField();
        SearchButton = new javax.swing.JButton();
        choice = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        Menu = new javax.swing.JTree();
        login = new javax.swing.JButton();
        greeting = new javax.swing.JLabel();
        feature3 = new javax.swing.JButton();
        feature1 = new javax.swing.JButton();
        feature2 = new javax.swing.JButton();
        low_prices = new javax.swing.JCheckBox();
        quality = new javax.swing.JCheckBox();
        byname = new javax.swing.JRadioButton();
        bybrand = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextField1.setText("www.rent.com");

        Results.setColumns(20);
        Results.setRows(5);
        Scroll.setViewportView(Results);

        search_text.setText("Search");

        SearchButton.setLabel("Search");
        SearchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchButtonActionPerformed(evt);
            }
        });

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("Products");
        javax.swing.tree.DefaultMutableTreeNode treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Cars");
        javax.swing.tree.DefaultMutableTreeNode treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("BMW");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Audi");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Mercedes");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Fiat");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Motorcycles");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("BMW");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Kawasaki");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Harley");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Scooter");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        Menu.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        Menu.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                MenuValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(Menu);

        login.setText("Login");
        login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginActionPerformed(evt);
            }
        });

        feature3.setText("rent");
        feature3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                feature3ActionPerformed(evt);
            }
        });

        feature1.setText("feature1");
        feature1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                feature1ActionPerformed(evt);
            }
        });

        feature2.setText("feature2");
        feature2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                feature2ActionPerformed(evt);
            }
        });

        low_prices.setText("low prices");

        quality.setText("high quality");

        name_brand.add(byname);
        byname.setText("ByName");

        name_brand.add(bybrand);
        bybrand.setText("ByBrand");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(choice, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(feature3)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 681, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(greeting, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(login, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(feature1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(feature2, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(396, 396, 396)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(byname))
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(bybrand)))
                                .addGap(32, 32, 32)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(low_prices)
                                    .addComponent(SearchButton, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(quality)
                                    .addComponent(search_text, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(login)
                            .addComponent(feature1)
                            .addComponent(feature2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(greeting, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(search_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(SearchButton)
                            .addComponent(byname))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(low_prices)
                            .addComponent(quality)
                            .addComponent(bybrand))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addComponent(Scroll))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(choice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(feature3))
                .addGap(23, 23, 23))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void MenuValueChanged(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_MenuValueChanged
        // TODO add your handling code here:
		String path;
		//x will have the selection path of the tree
		try{
			path=Menu.getSelectionPath().toString();
		}catch(Exception e){
			System.out.println(e);
			return;
		}
		brandBased=true;
		//split the string with the path between the commas.
		//example:if path=[Products,Cars,BMW] then tokens[0]="[Products";tokens[1]="Cars";tokens[2]="BMW]"
		
		tokens=path.split(",");
		
		if(tokens.length<3) return;
		//fix the '[' ']' characters
		tokens[0]=tokens[0].replace("[","");
		tokens[2]=tokens[2].replace("]","");
		
		//fix spaces
		tokens[0]=tokens[0].replace(" ","");
		tokens[1]=tokens[1].replace(" ","");
		tokens[2]=tokens[2].replace(" ","");
		
		
		//debug text
		System.out.println("tokens[0]: "+tokens[0]);
		System.out.println("tokens[1]: "+tokens[1]);
		System.out.println("tokens[2]: "+tokens[2]);
		
		
		//the tokens[] array is like this: tokens[0]="Products"; tokens[1]="type";tokens[2]="brand";
		//example type=Cars and model=BMW tokens[0]="Products"; tokens[1]="Cars"; tokens[2]="BMW";
		//query needed for selection based on menu choice: query="SELECT * FROM storage WHERE type=tokens[1] AND brand=tokens[2]";
		//if type="Cars" and model="BMW" then query="SELECT * FROM Storage WHERE type='cars' AND brand='BMW'";
		if(con==null) if(!connect(con_host,con_user,con_pass)){JOptionPane.showMessageDialog(this, "Problem with database");return;}
		String type=tokens[1];
		String brand=tokens[2];	
		//fix the type
		if(type.equals("Cars")) type="car";
		else type="cycle";
		
		String query="select * from storage where type='"+type+"' and brand='"+brand+"'";
		
		try{
			//execute the query and print the results.
			rs=st.executeQuery(query);
			String titles[]={"id","type","name","brand","price","rented"};
			boolean found;
			found=print(6,titles,titles,rs);
			if(found==false)Results.setText("no items found\n");
		}catch(Exception e){System.err.println(e);}
		
    }//GEN-LAST:event_MenuValueChanged

	
    private void loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginActionPerformed
        // TODO add your handling code here:
		String uname="";
		String pw="";
		String query;
		int pr;
		int id;
		if(logged_in==false){
			ArrayList<String> ret=new ArrayList<String>();
			String tokens[]={"Username","Password"};
			//if tested
			if(test){
				//test_case=0 => user login
				if(test_case==0){
					uname="alkis";
					pw="alk123";
				}else if(test_case==1){//test_case=1 => employee login
					uname="employee";
					pw="emp";
				}else if(test_case==2){//test_case=2 => admin login
					uname="admin";
					pw="admin";
				}else if(test_case==3){//test_case=3 => wrong login
					uname="something";
					pw="what?";
				}
			}else{
				ret=p_panel(2, tokens,1,"Login");
				if(ret==null) return;
				uname=ret.get(0);
				pw=ret.get(1);
			}
			System.out.println(uname+" "+pw);
			if(con==null) if(!connect(con_host,con_user,con_pass)){JOptionPane.showMessageDialog(this,"Problem with database");return;}
			
			
			query="select * from users where username='"+uname+"' and password='"+pw+"'";
			try{
				rs=st.executeQuery(query);
				if(rs.next()==false)JOptionPane.showMessageDialog(this,"wrong username or password");
				else{
					pr=rs.getInt("permissions");
					id=rs.getInt("id");
					if(pr==1) user=new customer(uname,pw,id);
					else if(pr==2) user=new employee(uname,pw,id);
					else user=new admin(uname,pw,id);
					login.setText("logout");
					logged_in=true;
					greeting.setText("hello "+uname);
					if(user.privileges==1){
						feature1.setText("show rents");
						feature2.setText("clear rents");
						feature3.setText("rent");
					}
					else if(user.privileges==2){
						feature1.setText("insert product");
						feature2.setText("delete product");
						feature3.setText("Show Storage");
					}
					else if(user.privileges==3){
						feature1.setText("create user");
						feature2.setText("delete user");
						feature3.setText("Show Users");
						
					}
					
					feature1.setVisible(true);
					feature2.setVisible(true);
					
				}
				
				
			}catch(Exception e){
				System.err.println(e);
			}
				
		}else{
			user=null;
			logged_in=false;
			login.setText("login");
			greeting.setText("");
			feature1.setVisible(false);
			feature2.setVisible(false);
		}
		
	
    }//GEN-LAST:event_loginActionPerformed

    private void feature3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_feature3ActionPerformed
        // TODO add your handling code here:
		
		//if customer then feature3 will be rent
		if(user==null)return;
		
		if(user.privileges==1){
			int selection_id=-1;
			//get the selected car id for rent
			if(tokens.length<3){
				JOptionPane.showMessageDialog(this,"please select brand");
				return ;
			}
			try{
				if(test) selection_id=1;
				else selection_id=Integer.parseInt(choice.getText());
				
			}catch(Exception e){
				System.err.println(e);
				JOptionPane.showMessageDialog(this, "Enter a valid selection");
				return;
			}
			String type,brand;
			if(tokens[1].equals("Cars")) type="car";
			else type="cycle";

			brand=tokens[2];
		
		
			if(con==null) if(!connect(con_host,con_user, con_pass)){JOptionPane.showMessageDialog(this,"Problem with database");return;}
			
			int ret_code;
			if(test){
				if(test_case<3)ret_code=test_case;
				else ret_code=-1;	
			}
			else ret_code=((customer)user).rent(selection_id,type,brand,con,st,rs,brandBased);
		
			if(ret_code==1){JOptionPane.showMessageDialog(this,"Item does not exist");return;}
			else if(ret_code==2){JOptionPane.showMessageDialog(this,"Item already rented");return;}
			else if(ret_code==-1){JOptionPane.showMessageDialog(this,"Backend problem");return;}
			
			try{
				String query="select * from storage where type='"+type+"' and brand='"+brand+"'";
				String titles[]={"id","type","name","brand","price","rented"};
				rs=st.executeQuery(query);
				boolean found=print(6,titles,titles,rs);
				if(found==false) Results.setText("no items found\n");
			}catch(Exception e){System.err.println(e);}
		
		}else if(user.privileges==2){//if user is an employee then show the storage.
			rs=((employee)user).show_storage(con, st, rs);
			if(rs==null){JOptionPane.showMessageDialog(this, "Backend problem");return;}
			String titles[]={"id","type","name","brand","CC","rented","price"};
			try{
				boolean found=print(7,titles,titles,rs);
			}catch(SQLException e){System.err.println(e);return;}
			
		
		}else if(user.privileges==3){//if user is admin then show the users.
			rs=((admin)user).show_users(con,st,rs);
			if(rs==null){
				JOptionPane.showMessageDialog(this, "Backend problem");
				return;
			}
			try{
				String titles[]={"id","username","permissions"};
				print(3,titles,titles,rs);
			}catch(SQLException e){System.err.println(e);return;}
			
		}
    }//GEN-LAST:event_feature3ActionPerformed

	//first invi button
    private void feature1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_feature1ActionPerformed
        // TODO add your handling code here:
		//if simple user then show rents
		if(con==null)if(!connect(con_host, con_user, con_pass)){JOptionPane.showMessageDialog(this,"Problem with database");return;}
		if(user.privileges==1){
			rs=((customer)user).show_rents(con,st,rs);
			try{
				//type\tname\tbrand\tprice\n
				String titles[]={"type","name","brand","price"};
				if(!print(4,titles,titles,rs))Results.setText("You have no rents");
			}catch(SQLException e){
				System.err.println(e);
			}
//if employee then insert new product in storage		
		}else if(user.privileges==2){
			String in[]={"type","name","brand","CC","price"};
			String type;
			String name;
			String brand;
			int price;
			int CC;
			ArrayList<String> ret=new ArrayList(5);
			if(test){
				type="car";
				name="m3";
				brand="BMW";
				CC=1800;
				price=20000;
			}
			else{ 
				ret=panel(5, in,"Product Information");
	
				if(ret==null) return;
				type=ret.get(0);
				name=ret.get(1);
				brand=ret.get(2);
			

				try{CC=Integer.parseInt(ret.get(3));}catch(NumberFormatException e){System.err.println(e);CC=-1;}

				try{price=Integer.parseInt(ret.get(4));}catch(NumberFormatException e){System.out.println(e);price=-1;}
			}		
			int ret_code;
			if(test) {
				if(test_case<6) ret_code=test_case;
				else ret_code=-1;
			}
			else ret_code=((employee)user).insert_model(con,st,rs,type,name,brand,CC,price);
			
			if(ret_code==-1){JOptionPane.showMessageDialog(this, "Backend Problem");return;}
			else if(ret_code==1){JOptionPane.showMessageDialog(this,"you should enter car or cycle"); return;}
			else if(ret_code==2){JOptionPane.showMessageDialog(this, "You should enter a valid name"); return;}
			else if(ret_code==3){JOptionPane.showMessageDialog(this,"You should enter a valid brand");return;}
			else if(ret_code==4){JOptionPane.showMessageDialog(this, "You should enter valid CC"); return;}
			else if(ret_code==5){JOptionPane.showMessageDialog(this, "You should enter a valid price"); return;}
			JOptionPane.showMessageDialog(this, "success");
			
		}//if admin then insert new user
		else if(user.privileges==3){
			String tokens[]={"Username for new user:","password for new user","privileges(1 for user,2 for employee,3 for admin"};
			ArrayList<String> ret=null;
			String uname;
			String password;
			int privileges;
			if(test){
				uname="something";
				password="something";
				privileges=1;
			}
			else {
				ret=GUI.p_panel(3,tokens,1,"Insert user information");
				if(ret==null) return;

				uname=ret.get(0);
				password=ret.get(1);
		
			
				try{
					privileges=Integer.parseInt(ret.get(2));
				}catch(Exception e){privileges=-1;}
			}
			
			
			int ret_code;
			if(test){
				if(test_case<4)ret_code=test_case;
				else ret_code=-1;
			}
			else ret_code=((admin)user).insert_user(con,st,rs,uname,password,privileges);
			
			if(ret_code==1){JOptionPane.showMessageDialog(this,"Enter a valid username");return;}
			else if(ret_code==2){JOptionPane.showMessageDialog(this, "Enter a valid passowrd");return;}
			else if(ret_code==3){JOptionPane.showMessageDialog(this, "Enter valid privileges (1,2,3)");return;}
			else if(ret_code==-1){JOptionPane.showMessageDialog(this,"Backend Problem");return;}
			String query="select * from users";
			try{
				rs=st.executeQuery(query);
				String titles[]={"id","username","permissions"};
				print(3,titles,titles,rs);
				
			}catch(SQLException e){System.err.println(e);}	
		}
		
    }//GEN-LAST:event_feature1ActionPerformed
	//second invi button
    private void feature2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_feature2ActionPerformed
        // TODO add your handling code here:
		if(con==null)if(!connect(con_host, con_user, con_pass)){JOptionPane.showMessageDialog(this,"Problem with database");return;}
		String query;
		//if simple user delete the current cart of rents.
		if(user.privileges==1){
			int choice;
			if(test) choice=0;
			else choice=JOptionPane.showConfirmDialog(this,"Are you sure?");
			
			if(choice==0){
				int ret_code=0;
				if(test){
					if(test_case==0) ret_code=0;
					else if(test_case==1) ret_code=1;
					else if(test_case==2) ret_code=-1;
				}
				else ret_code=((customer)user).delete_rents(con,st,rs);
				
				if(ret_code==1){JOptionPane.showMessageDialog(this,"You have no rents to delete" );return;}
				else if(ret_code==0){JOptionPane.showMessageDialog(this,"deletion successfull");return;}
				else if(ret_code==-1){JOptionPane.showMessageDialog(this,"Backend error");return;}	
			
			}
		}else if(user.privileges==2){
			int id;
			try{
				if(test) id=1;
				else id=Integer.parseInt(JOptionPane.showInputDialog("id for removal:"));
			}catch(NumberFormatException e){id=-1;}
			int ret_code=0;
			
			if(test){
				if(test_case==0) ret_code=0;
				else if(test_case==1) ret_code=1;
				else if(test_case==2) ret_code=-1;
				else if(test_case==3) ret_code=-2;
			}
			else ret_code=((employee)user).remove_model(con,st,rs,id);
			
			if(ret_code==-1){JOptionPane.showMessageDialog(this, "enter a valid product id");return;}
			else if(ret_code==-2){JOptionPane.showMessageDialog(this, "Backend problem");return;}
			else if(ret_code==1){JOptionPane.showMessageDialog(this, "product does not exist");return;}
			JOptionPane.showMessageDialog(this, "deletion success");
		
		
		}//if administrator delete user
		else if(user.privileges==3){
			try{
				query="select * from users";
				rs=st.executeQuery(query);
				String titles[]={"id","username","permissions"};
				print(3,titles,titles,rs);
				
				int id;
				try{
					if(test) id=1;
					else id=Integer.parseInt(JOptionPane.showInputDialog("id for removal:"));
				}catch(NumberFormatException e){id=-1;}
				
				int ret_code=0;
				if(test){
					if(test_case==0) ret_code=0;
					else if(test_case==1) ret_code=1;
					else if(test_case==2) ret_code=-1;
					else if(test_case==3) ret_code=-2;
				}else ret_code=((admin)user).delete_user(con, st, rs,id);
				
				if(ret_code==-1){JOptionPane.showMessageDialog(this,"Enter a valid numeric id");return;}
				else if(ret_code==-2){JOptionPane.showMessageDialog(this, "Backend problem");return;}
				else if(ret_code==1){JOptionPane.showMessageDialog(this, "User does not exist");return;}
				
				query="select * from users";
				rs=st.executeQuery(query);
				print(3,titles,titles,rs);
			}catch(SQLException e){System.err.println(e);return;}
			JOptionPane.showMessageDialog(this,"deletion success");
		}
		
		
    }//GEN-LAST:event_feature2ActionPerformed

    private void SearchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchButtonActionPerformed
        // TODO add your handling code here:
		if(con==null) if(!connect(con_host, con_user, con_pass)){JOptionPane.showMessageDialog(this,"Problem with database");return;}
		String keyword=search_text.getText();
		boolean low=false;
		boolean high=false;
		boolean by_name=false;
		boolean by_brand=false;
		brandBased=false;
		if(low_prices.isSelected()) low=true;
		else if(quality.isSelected()) high=true;
		
		if(byname.isSelected()) by_name=true;
		else if(bybrand.isSelected()) by_brand=true;
		
		String query="";
		if(by_name)
			query="select * from storage where name like '"+keyword+"%'";
		else if(by_brand)
			query="select * from storage where brand like '"+keyword+"%'";
		if(low)
			query=query+" and price<=2000";
		else if(high)
			query=query+" and price>2000";
	
		//JOptionPane.showMessageDialog(this, query);
		
		
		
		try{
			String titles[]={"id","type","name","brand","price","rented"};
			rs=st.executeQuery(query);	
			items_list=new ArrayList<Object>();
			while(rs.next()) items_list.add(rs.getInt("id"));
			rs=st.executeQuery(query);
			boolean found=print(6,titles,titles,rs);
			if(!found)JOptionPane.showMessageDialog(this,"no matches found");
		}catch(SQLException e){System.err.println(e);return;}
		
    }//GEN-LAST:event_SearchButtonActionPerformed

	
	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
		 * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
        //</editor-fold>

		/* Create and display the form */
		
		/*
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new GUI().eternal();
				
				
				
			}
		});*/
		GUI f=new GUI();
		f.eternal();
		
	
	}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTree Menu;
    public static javax.swing.JTextArea Results;
    private javax.swing.JScrollPane Scroll;
    public javax.swing.JButton SearchButton;
    public javax.swing.JRadioButton bybrand;
    public javax.swing.JRadioButton byname;
    private javax.swing.JTextField choice;
    public javax.swing.JButton feature1;
    public javax.swing.JButton feature2;
    public javax.swing.JButton feature3;
    private javax.swing.JLabel greeting;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    public javax.swing.JButton login;
    public javax.swing.JCheckBox low_prices;
    private javax.swing.ButtonGroup name_brand;
    public javax.swing.JCheckBox quality;
    public javax.swing.JTextField search_text;
    // End of variables declaration//GEN-END:variables
}