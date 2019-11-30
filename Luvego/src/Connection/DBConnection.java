package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

        //Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=GroceryStore;instance=MSSQLSERVER;integratedSecurity=true");
        Connection conn = DriverManager.getConnection("jdbc:sqlserver://ojosdelacara.database.windows.net:1433;database=Luvego;user=JeanGeorge@ojosdelacara;password=Luvego12*45JeanKGeorge;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;");

        Statement sta = conn.createStatement();
		String Sql = "SELECT * FROM Persona";
		ResultSet rs = sta.executeQuery(Sql);
		while (rs.next()) {
			System.out.println(rs.getString(1) + " " + rs.getString(2));
		}
			
	}

}
