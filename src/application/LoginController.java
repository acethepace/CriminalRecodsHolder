package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;


public class LoginController implements Initializable
{
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost/crime_net?autoReconnect=true&useSSL=false";
	
	//  Database credentials
	static final String USER = "root";
	static final String PASS = "password";
	
	
	@FXML
	private Text wrong;
	
	@FXML
	private TextField uname;
	
	@FXML
	private PasswordField pword;
	
	@FXML
	private Button login;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		login.setOnAction(new EventHandler<ActionEvent>() {
    		public void handle(ActionEvent t)
    		{
    			String username=uname.getText();
    			String password=pword.getText();
    			Boolean access=checkUser(username,password);
    			if(access==true)
    			{
    				wrong.setText("");
	    			AnchorPane root = new AnchorPane();
	    			try
	    			{
						root=FXMLLoader.load(getClass().getResource("mainUI.fxml"));
						Scene scene = new Scene(root);
		        		scene.getStylesheets().add(Main.class.getResource("application.css").toExternalForm());
		        		Main.globalStage.setScene(scene);
		        		Main.globalStage.setTitle("Criminal Records");
		        		Main.globalStage.show();
					}
	    			catch (IOException e)
	    			{
						e.printStackTrace();
					}
    			}
    			else
    			{
    				wrong.setText("Wrong username or password");
    			}
    		}
		});
	}

	protected Boolean checkUser(String username,String password)
	{
		Connection conn = null;
		Statement stmt = null;
		try{
			  Class.forName("com.mysql.jdbc.Driver");
			  conn = DriverManager.getConnection(DB_URL,USER,PASS);
			  stmt = conn.createStatement();
			  String sql;
			  sql = "SELECT * FROM users";
			  ResultSet rs = stmt.executeQuery(sql);
			  while(rs.next())
			  {
				  String u=rs.getString("username");
				  String p=rs.getString("password");
				  if(u.equals(username) && p.equals(password))
				  {
					  return true;
				  }
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
		  }
		  try{
		     if(conn!=null)
		        conn.close();
		  }catch(SQLException se){
		     se.printStackTrace();
		  }//end finally try
		   }//end try
		return false;
	}
}
