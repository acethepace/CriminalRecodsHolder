package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class Controller implements Initializable{

	// JDBC driver name and database URL
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://localhost/project?autoReconnect=true&useSSL=false";

   //  Database credentials
   static final String USER = "root";
   static final String PASS = "password";
	
	
    @FXML
    private TextArea crime_description_report;

    @FXML
    private TextField crimeId_new_criminal;

    @FXML
    private ComboBox<Integer> crimeId_new_criminal1;
    
    @FXML
    private ComboBox<String> crime_type_cb_query;

    @FXML
    private TextArea suspect_description_report;

    @FXML
    private RadioButton crime_type_query;

    @FXML
    private ComboBox<String> status_cb_query;

    @FXML
    private RadioButton criminal_query;

    @FXML
    private Button submit_query;

    @FXML
    private TextField category_report;

    @FXML
    private ComboBox<String> area_cb_query;

    @FXML
    private TextField department_new_criminal1;

    @FXML
    private TextArea addn_information_report;

    @FXML
    private Button add_criminal_button;

    @FXML
    private ComboBox<String> criminal_cb_query;

    @FXML
    private TextField name_new_criminal;

    @FXML
    private TextField offr_new_criminal;

    @FXML
    private DatePicker date_report;

    @FXML
    private ComboBox<String> status_new_criminal;

    @FXML
    private RadioButton area_query;

    @FXML
    private RadioButton status_query;

    @FXML
    private TextField location_report;
    
    @FXML
    private Button submit_crime_button;

    @FXML
    private Button cancel_crime_button;
    
    @FXML
    private TabPane tabs;
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1)
    {
    	initializeButtons();
    	initializeRadioButtons();
    	initializeComboBoxes();
    	
    }
    
    
    
    void initializeComboBoxes()
    {
    	//1
    	crimeId_new_criminal1.getItems().clear();
    	ArrayList<Integer> c_id=new ArrayList<Integer>();
    	c_id=getCrimeId();
    	crimeId_new_criminal1.getItems().addAll(c_id);
    	
    	//2
    	status_new_criminal.getItems().clear();
    	status_new_criminal.getItems().addAll("Caught","In Jail");
    	
    	//3
    	ArrayList<String> str=new ArrayList<String>();
    	str=getDistinctArea("area","crime");
    	area_cb_query.getItems().clear();
    	area_cb_query.getItems().addAll(str);
    	str=getDistinctArea("category","crime");
    	crime_type_cb_query.getItems().clear();
    	crime_type_cb_query.getItems().addAll(str);
    	str=getDistinctArea("name","criminal");
    	criminal_cb_query.getItems().clear();
    	criminal_cb_query.getItems().addAll(str);
    	str=getDistinctArea("status","criminal");
    	status_cb_query.getItems().clear();
    	status_cb_query.getItems().addAll(str);
    	
    }
    private ArrayList<String> getDistinctArea(String col_name,String table)
    {
    	ArrayList<String> str=new ArrayList<String>();
    	str=queryExecuter(col_name,table);
		return str;
	}

	private ArrayList<String> queryExecuter(String col_name,String table)
	{
		ArrayList<String> str=new ArrayList<String>();
		Connection conn = null;
		Statement stmt = null;
		try{
			  //STEP 2: Register JDBC driver
			  Class.forName("com.mysql.jdbc.Driver");
			
			  //STEP 3: Open a connection
			  System.out.println("Connecting to database...");
			  conn = DriverManager.getConnection(DB_URL,USER,PASS);
			
			  //STEP 4: Execute a query
			  System.out.println("Creating statement...");
			  stmt = conn.createStatement();
			  String sql;
			  sql = "SELECT distinct "+col_name+" FROM "+table;
			  ResultSet rs = stmt.executeQuery(sql);
			  
			  while(rs.next()){
				     //Retrieve by column name
				 String i=rs.getString(col_name);
				 str.add(i);
				  }
			  rs.close();
		      stmt.close();
		      conn.close();
		   }catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }finally{
		      //finally block used to close resources
		  try{
		     if(stmt!=null)
		        stmt.close();
		  }catch(SQLException se2){
		  }// nothing we can do
		  try{
		     if(conn!=null)
		        conn.close();
		  }catch(SQLException se){
		     se.printStackTrace();
		  }//end finally try
		   }//end try
		   System.out.println("Goodbye!");
		return str;
	}

	private ArrayList<Integer> getCrimeId()
    {
    	ArrayList<Integer> c_id=new ArrayList<Integer>();
    	Connection conn = null;
		Statement stmt = null;
		try{
			  //STEP 2: Register JDBC driver
			  Class.forName("com.mysql.jdbc.Driver");
			
			  //STEP 3: Open a connection
			  System.out.println("Connecting to database...");
			  conn = DriverManager.getConnection(DB_URL,USER,PASS);
			
			  //STEP 4: Execute a query
			  System.out.println("Creating statement...");
			  stmt = conn.createStatement();
			  String sql;
			  sql = "SELECT id FROM crime";
			  ResultSet rs = stmt.executeQuery(sql);
			  
			  while(rs.next()){
				     //Retrieve by column name
				 Integer i=rs.getInt("id");
				 c_id.add(i);
				  }
			  rs.close();
		      stmt.close();
		      conn.close();
		   }catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }finally{
		      //finally block used to close resources
		  try{
		     if(stmt!=null)
		        stmt.close();
		  }catch(SQLException se2){
		  }// nothing we can do
		  try{
		     if(conn!=null)
		        conn.close();
		  }catch(SQLException se){
		     se.printStackTrace();
		  }//end finally try
		   }//end try
		   System.out.println("Goodbye!");
    	return c_id;
    }
    
    void initializeRadioButtons() {
    	area_query.setOnAction(new EventHandler<ActionEvent>() {
    		public void handle(ActionEvent w)
    		{
    			area_cb_query.setDisable(!area_cb_query.isDisabled());
    		}
		});
    
    	crime_type_query.setOnAction(new EventHandler<ActionEvent>() {
    		public void handle(ActionEvent w)
    		{
    			crime_type_cb_query.setDisable(!crime_type_cb_query.isDisabled());
    		}
		});
    	
    	criminal_query.setOnAction(new EventHandler<ActionEvent>() {
    		public void handle(ActionEvent e)
    		{
    			criminal_cb_query.setDisable(!criminal_cb_query.isDisabled());
    		}
    	});
    	
    	status_query.setOnAction(new EventHandler<ActionEvent>() {
    		public void handle(ActionEvent e)
    		{
    			status_cb_query.setDisable(!status_cb_query.isDisabled());	
    		}
    	});
	}

	void initializeButtons(){
		
		tabs.getSelectionModel().selectedItemProperty().addListener(
	    	    new ChangeListener<Tab>() {
	    	        @Override
	    	        public void changed(ObservableValue<? extends Tab> ov, Tab t1, Tab t2) {
	    	            initializeComboBoxes();
	    	            initializeRadioButtons();
	    	        }

	    	    }
	    	);
		
    	submit_query.setOnAction(new EventHandler<ActionEvent>(){
    		public void handle(ActionEvent t)
    		{
    			String area=null;
    			String crime_type=null;
    			String criminal=null;
    			String status=null;
    			if(!area_cb_query.isDisable() && area_cb_query.getValue()!=null)
    			{
    				area=area_cb_query.getValue().toString();
    			}
    			if(!crime_type_cb_query.isDisable() && crime_type_cb_query.getValue()!=null)
    			{
    				crime_type=crime_type_cb_query.getValue().toString();
    			}
    			if(!criminal_cb_query.isDisable() && criminal_cb_query.getValue()!=null)
    			{
    				criminal=criminal_cb_query.getValue().toString();
    			}
    			if(!status_cb_query.isDisable() && status_cb_query.getValue()!=null)
    			{
    				status=status_cb_query.getValue().toString();
    			}
    			//TODO: submit query, create table
    			filterQuery(area,crime_type,criminal,status);
    			
    			
    		}
   		});
    	
    	submit_crime_button.setOnAction(new EventHandler<ActionEvent>() {
    		public void handle(ActionEvent t)
    		{
    			//getting values
    			LocalDate date=date_report.getValue();
    			String location = location_report.getText();
    			String category = category_report.getText();
    			String crime_desc= crime_description_report.getText();
    			String suspect_desc=suspect_description_report.getText();
    			String addn_info= addn_information_report.getText();
    			//setting values to default
    			date_report.setValue(null);
    			location_report.setText("");
    			category_report.setText("");
    			crime_description_report.setText("");
    			suspect_description_report.setText("");
    			addn_information_report.setText("");
    			//Testing input
    			System.out.println(date+" "+location+" "+category+" "+crime_desc+" "+suspect_desc+" "+addn_info);
    			//TODO: ADD CRIME TO SQL DATABASE.
    			addValueToCrime(date,location,category,crime_desc,suspect_desc,addn_info);
    		}
		});
    	
    	cancel_crime_button.setOnAction(new EventHandler<ActionEvent>() {
    		public void handle(ActionEvent t)
    		{
    			date_report.setValue(null);
    			location_report.setText("");
    			category_report.setText("");
    			crime_description_report.setText("");
    			suspect_description_report.setText("");
    			addn_information_report.setText("");
    		}
		});
    	
    	add_criminal_button.setOnAction(new EventHandler<ActionEvent>() {
    		public void handle(ActionEvent t)
    		{
    			String name = name_new_criminal.getText();
//    			String crimeId=crimeId_new_criminal.getText();
    			Integer crimeId=crimeId_new_criminal1.getValue();
    			String officerIncharge=offr_new_criminal.getText();
    			String policeDept= department_new_criminal1.getText();
    			String status= status_new_criminal.getValue();
    			//clearEverything
    			name_new_criminal.setText("");
    			offr_new_criminal.setText("");
    			department_new_criminal1.setText("");
    			status_new_criminal.setValue(null);
    			crimeId_new_criminal1.setValue(null);
    			//TODO: add criminal with these details
    			addValueToCriminal(name,crimeId,officerIncharge,policeDept,status);
    			
    		}
		});
    }
	
	
	protected void filterQuery(String area, String crime_type, String criminal, String status)
	{
		System.out.println(area+" "+crime_type+" "+criminal+" "+status);
		Connection conn = null;
		Statement stmt = null;
		try{
			  //STEP 2: Register JDBC driver
			  Class.forName("com.mysql.jdbc.Driver");
			
			  //STEP 3: Open a connection
			  System.out.println("Connecting to database...");
			  conn = DriverManager.getConnection(DB_URL,USER,PASS);
			
			  //STEP 4: Execute a query
			  System.out.println("Creating statement...");
			  stmt = conn.createStatement();
			  String sql;
			  sql = "SELECT id FROM crime where ";
			  if(area!=null)
				  sql=sql.concat("area=\""+area+"\"");
			  else
				  sql=sql.concat("area is not NULL");
			  sql=sql.concat(" and ");
			  if(crime_type!=null)
				  sql=sql.concat("category=\""+crime_type+"\"");
			  else
				  sql=sql.concat("category is not NULL");
			  String sql2="select id from criminal where ";
			  if(criminal!=null)
				  sql2=sql2.concat("name=\""+criminal+"\"");
			  else
				  sql2=sql2.concat("name is not NULL");
			  sql2=sql2.concat(" and ");
			  if(status!=null)
				  sql2=sql2.concat("status=\""+status+"\"");
			  else
				  sql2=sql2.concat("status is not NULL");
			  if(criminal!=null || status!=null)
				  sql=sql.concat(" and id in("+sql2+")");
			  System.out.println(sql);
			  ResultSet rs = stmt.executeQuery(sql);
			
			  //STEP 5: Extract data from result set
			  while(rs.next()){
			     //Retrieve by column name
			 Integer id=rs.getInt("id");
			 System.out.println("CRIME ID: "+id);
			  }
			  //STEP 6: Clean-up environment
			      rs.close();
			      stmt.close();
			      conn.close();
			   }catch(SQLException se){
			      //Handle errors for JDBC
			      se.printStackTrace();
			   }catch(Exception e){
			      //Handle errors for Class.forName
			      e.printStackTrace();
			   }finally{
			      //finally block used to close resources
			  try{
			     if(stmt!=null)
			        stmt.close();
			  }catch(SQLException se2){
			  }// nothing we can do
			  try{
			     if(conn!=null)
			        conn.close();
			  }catch(SQLException se){
			     se.printStackTrace();
			  }//end finally try
			   }//end try
	}

	protected void addValueToCriminal(String name,Integer crimeId,String officerIncharge,String policeDept,String status)
	{
		Connection conn = null;
		Statement stmt = null;
		try{
			  //STEP 2: Register JDBC driver
			  Class.forName("com.mysql.jdbc.Driver");
			
			  //STEP 3: Open a connection
			  System.out.println("Connecting to database...");
			  conn = DriverManager.getConnection(DB_URL,USER,PASS);
			
			  //STEP 4: Execute a query
			  System.out.println("Creating statement...");
			  stmt = conn.createStatement();
			  String sql;
			  sql = "INSERT INTO criminal(name,id,officer_incharge,department,status)  Values(\""+name+"\",\""+crimeId+"\",\""+officerIncharge+"\",\""+policeDept+"\",\""+status+"\")";
			 
			  System.out.println(sql);
			  int rs = stmt.executeUpdate(sql);
			  //rs.close();
		      stmt.close();
		      conn.close();
		   }catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }finally{
		      //finally block used to close resources
		  try{
		     if(stmt!=null)
		        stmt.close();
		  }catch(SQLException se2){
		  }// nothing we can do
		  try{
		     if(conn!=null)
		        conn.close();
		  }catch(SQLException se){
		     se.printStackTrace();
		  }//end finally try
		   }//end try
		   System.out.println("Goodbye!");
	}

	protected void addValueToCrime(LocalDate date,String location,String category,String crime_desc,String suspect_desc,String addn_info)
	{
		Connection conn = null;
		Statement stmt = null;
		try{
			  //STEP 2: Register JDBC driver
			  Class.forName("com.mysql.jdbc.Driver");
			
			  //STEP 3: Open a connection
			  System.out.println("Connecting to database...");
			  conn = DriverManager.getConnection(DB_URL,USER,PASS);
			
			  //STEP 4: Execute a query
			  System.out.println("Creating statement...");
			  stmt = conn.createStatement();
			  String sql;
			  sql = "INSERT INTO crime ";
			  if(suspect_desc.equals("") && !addn_info.equals(""))
				  sql=sql.concat("(date,area,category,description_crime,additional_info)");
			  else if(addn_info.equals("") && !suspect_desc.equals(""))
				  sql=sql.concat("(date,area,category,description_suspect,description_crime)");
			  else if(addn_info.equals("") && suspect_desc.equals(""))
				  sql=sql.concat("(date,area,category,description_crime)");
			  else
				  sql=sql.concat("(date,area,category,description_suspect,description_crime,additional_info)");
			  sql=sql.concat(" VALUES");
			  sql=sql.concat("(\""+date+"\"");
			  if(!location.equals(""))
				  sql=sql.concat(",\""+location+"\"");
			  if(!category.equals(""))
				  sql=sql.concat(",\""+category+"\"");
			  if(!suspect_desc.equals(""))
				  sql=sql.concat(",\""+suspect_desc+"\"");
			  if(!crime_desc.equals(""))
				  sql=sql.concat(",\""+crime_desc+"\"");
			  if(!addn_info.equals(""))
				  sql=sql.concat(",\""+addn_info+"\"");
			  sql=sql.concat(")");
			  System.out.println(sql);
			  int rs = stmt.executeUpdate(sql);
			  //rs.close();
		      stmt.close();
		      conn.close();
		   }catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }finally{
		      //finally block used to close resources
		  try{
		     if(stmt!=null)
		        stmt.close();
		  }catch(SQLException se2){
		  }// nothing we can do
		  try{
		     if(conn!=null)
		        conn.close();
		  }catch(SQLException se){
		     se.printStackTrace();
		  }//end finally try
		   }//end try
		   System.out.println("Goodbye!");
			  
			  
	}
}
