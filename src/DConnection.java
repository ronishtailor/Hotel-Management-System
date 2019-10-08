import java.sql.*;
public class DConnection {
private Connection conn;
private Statement stmt;
private ResultSet rst;
private int cnt;
	public void connect()
	{
		try {
		Class.forName("com.mysql.jdbc.Driver");
		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/myhotel","root","root");
		stmt=conn.createStatement();
		}
		catch(ClassNotFoundException ce)
		{
			ce.printStackTrace();
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
	}
	public ResultSet executeSelectQuery(String query)
	{
		try {
		connect();
		rst=stmt.executeQuery(query);
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		return rst;
	}
	public int executeOtherQuery(String query)
	{
		try {
		connect();
		cnt=stmt.executeUpdate(query);
		close();
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		return cnt;
	}
	public void close()
	{  try {
		if(rst!=null)
			rst.close();
		if(stmt!=null)
			stmt.close();
		if(conn!=null)
			conn.close();
		}
	catch(SQLException e)
	{
		e.printStackTrace();
	}
	}
}
