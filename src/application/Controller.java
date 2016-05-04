package application;

import java.awt.Dimension;
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

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.mysql.jdbc.PreparedStatement;

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
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class Controller implements Initializable{

	// JDBC driver name and database URL
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://localhost/crime_net?autoReconnect=true&useSSL=false";

   //  Database credentials
   static final String USER = "root";
   static final String PASS = "password";
	
   @FXML
   private ResourceBundle resources;

   @FXML
   private URL location;
   
    @FXML
    private TextArea crime_description_report;

    @FXML
    private TextField crimeId_new_criminal;

    @FXML
    private ComboBox<Integer> crimeId_new_criminal1;
    
    @FXML
    private ComboBox<String> crime_type_cb_query;

    @FXML
    private ComboBox<String> officer_cb_id;
    
    @FXML
    private TextArea suspect_description_report;

    @FXML
    private RadioButton crime_type_query;

    @FXML
    private CheckBox criminal_name;

    @FXML 
    private CheckBox crime_id;
    
    @FXML 
    private CheckBox policeman_details;
    
    @FXML 
    private CheckBox area;
    
    @FXML 
    private CheckBox criminal_status;

    @FXML 
    private CheckBox addn_info;
    
    @FXML 
    private CheckBox susp_desc;
    
    @FXML 
    private CheckBox crime_desc;
    
    @FXML 
    private CheckBox crime_type;
    
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
    private TextField ZIP;
    
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
    private Label message_report_crime;

    @FXML
    private Label message_new_criminal;
    
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
    
    @FXML
    void initialize() {
        assert crime_description_report != null : "fx:id=\"crime_description_report\" was not injected: check your FXML file 'mainUI.fxml'.";
        assert crime_type_cb_query != null : "fx:id=\"crime_type_cb_query\" was not injected: check your FXML file 'mainUI.fxml'.";
        assert suspect_description_report != null : "fx:id=\"suspect_description_report\" was not injected: check your FXML file 'mainUI.fxml'.";
        assert submit_crime_button != null : "fx:id=\"submit_crime_button\" was not injected: check your FXML file 'mainUI.fxml'.";
        assert status_cb_query != null : "fx:id=\"status_cb_query\" was not injected: check your FXML file 'mainUI.fxml'.";
        assert cancel_crime_button != null : "fx:id=\"cancel_crime_button\" was not injected: check your FXML file 'mainUI.fxml'.";
        assert criminal_query != null : "fx:id=\"criminal_query\" was not injected: check your FXML file 'mainUI.fxml'.";
        assert category_report != null : "fx:id=\"category_report\" was not injected: check your FXML file 'mainUI.fxml'.";
        assert addn_information_report != null : "fx:id=\"addn_information_report\" was not injected: check your FXML file 'mainUI.fxml'.";
        assert add_criminal_button != null : "fx:id=\"add_criminal_button\" was not injected: check your FXML file 'mainUI.fxml'.";
        assert offr_new_criminal != null : "fx:id=\"offr_new_criminal\" was not injected: check your FXML file 'mainUI.fxml'.";
        assert date_report != null : "fx:id=\"date_report\" was not injected: check your FXML file 'mainUI.fxml'.";
        assert status_query != null : "fx:id=\"status_query\" was not injected: check your FXML file 'mainUI.fxml'.";
        assert location_report != null : "fx:id=\"location_report\" was not injected: check your FXML file 'mainUI.fxml'.";
        assert crimeId_new_criminal != null : "fx:id=\"crimeId_new_criminal\" was not injected: check your FXML file 'mainUI.fxml'.";
        assert crime_type_query != null : "fx:id=\"crime_type_query\" was not injected: check your FXML file 'mainUI.fxml'.";
        assert message_new_criminal != null : "fx:id=\"message_new_criminal\" was not injected: check your FXML file 'mainUI.fxml'.";
        assert submit_query != null : "fx:id=\"submit_query\" was not injected: check your FXML file 'mainUI.fxml'.";
        assert area_cb_query != null : "fx:id=\"area_cb_query\" was not injected: check your FXML file 'mainUI.fxml'.";
        assert department_new_criminal1 != null : "fx:id=\"department_new_criminal1\" was not injected: check your FXML file 'mainUI.fxml'.";
        assert criminal_cb_query != null : "fx:id=\"criminal_cb_query\" was not injected: check your FXML file 'mainUI.fxml'.";
        assert name_new_criminal != null : "fx:id=\"name_new_criminal\" was not injected: check your FXML file 'mainUI.fxml'.";
        assert message_report_crime != null : "fx:id=\"message_report_crime\" was not injected: check your FXML file 'mainUI.fxml'.";
        assert status_new_criminal != null : "fx:id=\"status_new_criminal\" was not injected: check your FXML file 'mainUI.fxml'.";
        assert area_query != null : "fx:id=\"area_query\" was not injected: check your FXML file 'mainUI.fxml'.";

    }
    
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
    	ArrayList<Integer> c_id=new ArrayList<Integer>();
    	c_id=getCrimeId();
    	crimeId_new_criminal1.getItems().clear();
    	crimeId_new_criminal1.getItems().addAll(c_id);
    	
    	//1.5
    	ArrayList<String> o_id=new ArrayList<String>();
    	o_id=getOfficerId();
    	officer_cb_id.getItems().clear();
    	officer_cb_id.getItems().addAll(o_id);
    	//2
    	status_new_criminal.getItems().clear();
    	status_new_criminal.getItems().addAll("Wanted","Held for Trial","In Jail","Out on Bail","Out on Probation","Confirmed Dead");
    	
    	//3
    	ArrayList<String> str=new ArrayList<String>();
    	str=getDistinctArea("area_name","area");
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
			  conn = DriverManager.getConnection(DB_URL,USER,PASS);
			
			  //STEP 4: Execute a query
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
			  conn = DriverManager.getConnection(DB_URL,USER,PASS);
			
			  //STEP 4: Execute a query
			  stmt = conn.createStatement();
			  String sql;
			  sql = "SELECT crime_id FROM crime where criminal_id is null";
			  ResultSet rs = stmt.executeQuery(sql);
			  
			  while(rs.next()){
				     //Retrieve by column name
				 Integer i=rs.getInt("crime_id");
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
    	return c_id;
    }
    
	private ArrayList<String> getOfficerId()
    {
    	ArrayList<String> o_id=new ArrayList<String>();
    	Connection conn = null;
		Statement stmt = null;
		try{
			  //STEP 2: Register JDBC driver
			  Class.forName("com.mysql.jdbc.Driver");
			
			  //STEP 3: Open a connection
			  conn = DriverManager.getConnection(DB_URL,USER,PASS);
			
			  //STEP 4: Execute a query
			  stmt = conn.createStatement();
			  String sql;
			  sql = "SELECT badge_id FROM policemen";
			  ResultSet rs = stmt.executeQuery(sql);
			  
			  while(rs.next()){
				     //Retrieve by column name
				 String i=rs.getString("badge_id");
				 o_id.add(i);
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
    	return o_id;
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
    			String ZIP_code=ZIP.getText();
    			String category = category_report.getText();
    			String crime_desc= crime_description_report.getText();
    			String suspect_desc=suspect_description_report.getText();
    			String addn_info= addn_information_report.getText();
    			//setting values to default
    			String myDate="";
    			myDate=""+date;
    			if(!sanity(location,category,crime_desc,suspect_desc,addn_info,ZIP_code))
    			{
    				//flash error
    				message_report_crime.setText("Special characters not allowed");
    				message_report_crime.setVisible(true);
    				return;
    			}else if(!checkNull(location,category,crime_desc,ZIP_code) || myDate.equals("null"))
    			{
    				//flash error
    				message_report_crime.setVisible(true);
    				message_report_crime.setText("Please fill all required fields");
    				return;
    			}else
                {
                    message_report_crime.setText("");
                    addValueToCrime(date,location,ZIP_code,category,crime_desc,suspect_desc,addn_info);
                }
    			date_report.setValue(null);
    			location_report.setText("");
    			category_report.setText("");
    			crime_description_report.setText("");
    			suspect_description_report.setText("");
    			addn_information_report.setText("");
    			ZIP.setText("");
    			//Testing input
//    			System.out.println(date+" "+location+" "+category+" "+crime_desc+" "+suspect_desc+" "+addn_info);
    			//TODO: ADD CRIME TO SQL DATABASE.
    			
    			
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
    			ZIP.setText("");
    		}
		});
    	
    	add_criminal_button.setOnAction(new EventHandler<ActionEvent>() {
    		public void handle(ActionEvent t)
    		{
    			String name = name_new_criminal.getText();
    			Integer crimeId=crimeId_new_criminal1.getValue();
    			String officerIncharge=officer_cb_id.getValue();
    			String status= status_new_criminal.getValue();
    			//clearEverything
    			//TODO: add criminal with these details
    			try{
    				if(!sanity(name,officerIncharge,status))
        			{
        				//flash error
        				message_new_criminal.setText("Don't include special characters");
        				return;
        			}else if(!checkNull(name,crimeId.toString(),officerIncharge,status))
        			{
        				//flash error
        				message_new_criminal.setText("Please fill all required fields");
        				return;
        			}else
        			{
//        				System.out.println("I'm here");
        				message_new_criminal.setText("");
            			addValueToCriminal(name,crimeId,officerIncharge,status);
        			}

        			name_new_criminal.setText("");
        			officer_cb_id.setValue(null);
        			status_new_criminal.setValue(null);
        			crimeId_new_criminal1.setValue(null);
    			}catch(Exception e)
    			{
    				message_new_criminal.setText("Please fill all required fields");

        			
    			}
    			
    		}
		});
    }

	public boolean sanity(String... args)
	{
		for(String string : args)
		{
			try{
				if(string.matches(".*[;()'\"].*"))
					return false;
			}catch(Exception e)
			{
//				e.printStackTrace();
			}
		}
		return true;
	}
	
	public boolean checkNull(String...strings)
	{
//		System.out.println(strings.length);
		for(String string : strings)
		{
			if(string==null || string.equals(""))
			{
				return false;
			}else
			{
				//System.out.println(string+" is good. ");
			}
		}
		return true;
	}
	
	protected void filterQuery(String area, String crime_type, String criminal, String status)
	{
//		System.out.println(area+" "+crime_type+" "+criminal+" "+status);
		Connection conn = null;
		Statement stmt = null;
		try{
			  //STEP 2: Register JDBC driver
			  Class.forName("com.mysql.jdbc.Driver");
			
			  //STEP 3: Open a connection
			  conn = DriverManager.getConnection(DB_URL,USER,PASS);
			
			  //STEP 4: Execute a query
			  stmt = conn.createStatement();
			  String sql;
			  String select=getSelect();
			  sql = "SELECT " + select
			  		+ " FROM crime LEFT JOIN criminal ON criminal.criminal_id = crime.criminal_id "
			  		+ "LEFT JOIN area ON area.zip_code=crime.zipcode "
			  		+ "LEFT JOIN policemen ON criminal.badge_id=policemen.badge_id "
			  		+ "WHERE ";
			  if(area!=null)
				  sql=sql.concat("area.area_name=\""+area+"\" and ");
			  
			  if(crime_type!=null)
				  sql=sql.concat("crime.category=\""+crime_type+"\" and ");
			  
			  if(criminal!=null)
				  sql=sql.concat("criminal.name=\""+criminal+"\" and ");
			  
			  if(status!=null)
				  sql=sql.concat("criminal.status=\""+status+"\" and ");
			  
			  sql=sql.concat("'1' = '1'");
			  
			  System.out.println(sql);
			  ResultSet rs = stmt.executeQuery(sql);
			
			  //STEP 5: Extract data from result set
//			  while(rs.next()){
//			     //Retrieve by column name
//				  
//			 Integer id=rs.getInt("id");
//			 System.out.println("CRIME ID: "+id);
//			  }
			  JTable table = new JTable(Main.buildTableModel(rs));
			  JScrollPane pane= new JScrollPane(table);

			  pane.setPreferredSize(new Dimension(1024, 420));
			  JOptionPane.showMessageDialog(null,pane);
			  
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

	private String getSelect() {
		String select="";
		if(criminal_name.isSelected())
			select+="criminal.name, ";
		if(crime_id.isSelected())
			select+="crime.crime_id, ";
		if(policeman_details.isSelected())
			select+="policemen.name, policemen.department, ";
		if(area.isSelected())
			select+="area.area_name, area.zip_code, ";
		if(criminal_status.isSelected())
			select+="criminal.status, ";
		if(addn_info.isSelected())
			select+="crime.additional_info, ";
		if(susp_desc.isSelected())
			select+="crime.description_suspect, ";
		if(crime_desc.isSelected())
			select+="crime.description_crime, ";
		if(crime_type.isSelected())
			select+="crime.category, ";
		if(select.equals(""))
			select="*";
		else
			select=select.substring(0, select.length()-2);
		return select;
	}
	
	
	
	protected void addValueToCriminal(String name,Integer crimeId,String officerIncharge,String status)
	{
		Connection conn = null;
		Statement stmt = null;
		try{
			  //STEP 2: Register JDBC driver
			  Class.forName("com.mysql.jdbc.Driver");
			
			  //STEP 3: Open a connection
			  conn = DriverManager.getConnection(DB_URL,USER,PASS);
			
			  //STEP 4: Execute a query
			  stmt = conn.createStatement();
			  String sql,sql1;
			  String small_sql;
			  
			  sql = "INSERT INTO criminal(name,badge_id,status)  Values(\""+name+"\",\""+officerIncharge+"\",\""+status+"\")";
			  sql1="SELECT max(criminal_id) from criminal";
			  System.out.println(sql);
			  int rs = stmt.executeUpdate(sql);
			  //rs.close();
			  ResultSet rSet=stmt.executeQuery(sql1);
			  Integer i = null;
			  while(rSet.next())
			  {
				  i=rSet.getInt("max(criminal_id)");
			  }
			  small_sql="update crime set criminal_id="+i+" where crime_id= "+crimeId;
			  rs = stmt.executeUpdate(small_sql);
			  rSet.close();
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

	protected void addValueToCrime(LocalDate date,String location,String ZIP_code,String category,String crime_desc,String suspect_desc,String addn_info)
	{
		Connection conn = null;
		Statement stmt = null;
		try{
			  //STEP 2: Register JDBC driver
			  Class.forName("com.mysql.jdbc.Driver");
			
			  //STEP 3: Open a connection
			  conn = DriverManager.getConnection(DB_URL,USER,PASS);
			
			  //STEP 4: Execute a query
			  stmt = conn.createStatement();
			  String small_sql;
			  small_sql="SELECT * FROM area where zip_code =\""+ZIP_code+"\"";
			  ResultSet rs1=stmt.executeQuery(small_sql);
			  if(rs1.next()==true)
			  {
				  small_sql="UPDATE area SET area_name=\""+location+"\" where zip_code=\""+ZIP_code+"\"";
			  }
			  else
				  small_sql="INSERT INTO area (zip_code,area_name) values (\""+ZIP_code+"\",\""+location+"\")";
			  int rs = stmt.executeUpdate(small_sql);
			  
			  String sql;
			  sql = "INSERT INTO crime ";
			  
			  if(suspect_desc.equals("") && !addn_info.equals(""))
				  sql=sql.concat("(date,zipcode,category,description_crime,additional_info)");
			  else if(addn_info.equals("") && !suspect_desc.equals(""))
				  sql=sql.concat("(date,zipcode,category,description_suspect,description_crime)");
			  else if(addn_info.equals("") && suspect_desc.equals(""))
				  sql=sql.concat("(date,zipcode,category,description_crime)");
			  else
				  sql=sql.concat("(date,zipcode,category,description_suspect,description_crime,additional_info)");
			  sql=sql.concat(" VALUES");
			  sql=sql.concat("(\""+date+"\"");
			  if(!ZIP_code.equals(""))
				  sql=sql.concat(",\""+ZIP_code+"\"");
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
			  rs = stmt.executeUpdate(sql);
			  rs1.close();
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
}
