package Connection;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection implements Serializable{
	
	private static final long serialVersionUID = 1L;
    private Statement sta;
    private static DBConnection connectionToStore;
    private Connection conn;
    
    private DBConnection() throws ClassNotFoundException, SQLException {

    	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    	conn = DriverManager.getConnection("jdbc:sqlserver://ojosdelacara.database.windows.net:1433;database=Luvego;user=JeanGeorge@ojosdelacara;password=Luvego12*45JeanKGeorge;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;");
        sta = conn.createStatement();

    }
    
    public static DBConnection getInstance() throws SQLException, ClassNotFoundException {
        if(connectionToStore == null){
            connectionToStore = new DBConnection();
        }
        return connectionToStore;
    }
    
    public void agregarCliente(String cedula, String nombre, String apellidos, String sexo, String telefono1, String telefono2, String correo, String direccion) throws SQLException {
        CallableStatement cs = conn.prepareCall("{call [dbo].[spAgregarCliente]('"+cedula+"','"+nombre+"','"+apellidos+"','"+sexo+"','"+telefono1+"','"+telefono2+"','"+correo+"','"+direccion+"')}");
        cs.executeUpdate();
    }
    
    public void agregarJefe(String cedula, String nombre, String apellido, String sexo, String telefono1, String telefono2, String correo, String direccion, String salarioHora) throws SQLException {
        CallableStatement cs = conn.prepareCall("{call [dbo].[spAgregarCliente]('"+cedula+"','"+nombre+"','"+apellido+"','"+sexo+"','"+telefono1+"','"+telefono2+"','"+correo+"','"+direccion+"','"+salarioHora+"')}");
        cs.executeUpdate();
    }

	/*public static void main(String[] args) throws ClassNotFoundException, SQLException {
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
			
	}*/

}
