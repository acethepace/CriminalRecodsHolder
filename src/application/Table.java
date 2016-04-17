package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class Table {

	 static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	 static final String DB_URL = "jdbc:mysql://localhost/";
	 static String USER;
	 static String PASS;
	 Connection conn = null;
	 Statement stmt = null;
	 String tableName;
	public Table(String username, String password, String table) throws Exception
	{
		USER=username;
		PASS=password;
		tableName=table;
	    try {
			Class.forName("com.mysql.jdbc.Driver");
		    System.out.println("Connecting to database...");
		    conn = DriverManager.getConnection(DB_URL+tableName,USER,PASS);
		    System.out.println("Creating statement...");
		    stmt = conn.createStatement();
		} catch (ClassNotFoundException e) {
			throw new Exception("Error connecting to the database.");
		}
	}
	public void  insert(String values) throws Exception
	{
		if(conn == null || stmt == null)
		{
			throw new Exception("Connection not established");
		}
		String query="INSERT INTO "+tableName+" "+
					"values "+values;

		System.out.println("Executing statement: \n"+query);
		stmt.executeUpdate(query);
	}
	
	public ResultSet selectQuery(String columns, String where) throws SQLException
	{
		if(columns.equals(""))
			columns="*";
		String q= "SELECT "+columns+
				" FROM "+ tableName;
		if(!where.equals(""))
			q+="WHERE "+where;
		System.out.println("Executing statement: \n"+q);
		ResultSet resultSet = stmt.executeQuery(q);
		return resultSet;
	}
	
	public int deleteQuery(String where) throws SQLException
	{
		String q="DELETE FROM "+ tableName;
		if(!where.equals(""))
			q+="WHERE "+where;
		System.out.println("Executing statement: \n"+q);
		return stmt.executeUpdate(q);
	}
	
	public int updateQuery(String set,String where) throws SQLException
	{
		String q="UPDATE "+tableName+ 
				" SET "+ set+
				" WHERE "+where;
		System.out.println("Executing statement: \n"+q);
		return stmt.executeUpdate(q);
	}
	
	public void destory()
	{
		try{
		    stmt.close();
		    conn.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
