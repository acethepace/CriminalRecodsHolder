package application;
	
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.ResultSetMetaData;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	
	// JDBC driver name and database URL
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://localhost/crime_net?autoReconnect=true&useSSL=false";

   //  Database credentials
   static final String USER = "root";
   static final String PASS = "password";
   static Stage globalStage=new Stage();
	
	@Override
	public void start(Stage primaryStage) {
		
		try {
			AnchorPane pane = (AnchorPane) FXMLLoader.load(Main.class.getResource("Login.fxml"));
			Scene scene = new Scene(pane);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Login");
			primaryStage.show();
			globalStage=primaryStage;
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	  JTable table = new JTable(buildTableModel(resultSet));
	  JOptionPane.showMessageDialog(null,new JScrollPane(table));
	  
	 */
	public static DefaultTableModel buildTableModel(ResultSet rs)
	          throws SQLException {

	      ResultSetMetaData metaData = (ResultSetMetaData) rs.getMetaData();

	      // names of columns
	      Vector<String> columnNames = new Vector<String>();
	      int columnCount = metaData.getColumnCount();
	      for (int column = 1; column <= columnCount; column++) {
	          columnNames.add(metaData.getColumnName(column));
	      }

	      // data of the table
	      Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	      while (rs.next()) {
	          Vector<Object> vector = new Vector<Object>();
	          for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
	              vector.add(rs.getObject(columnIndex));
	          }
	          data.add(vector);
	      }

	      return new DefaultTableModel(data, columnNames);
	}
	
	public static void main(String[] args)
	{
		launch(args);
	}
}
