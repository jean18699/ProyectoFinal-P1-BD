package Logico;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class MySQLConexion {

	public static String nombreBD;
	
	public MySQLConexion(String nombreBD)
	{
		this.nombreBD= nombreBD;
	}
	
	public static Connection getConexion() {
			
		Connection con = null;
		
		
		try {
		
			Class.forName("com.mysql.jdbc.Driver"); //Cargando el controlador de la base de datos
			String url = "jdbc:mysql://localhost:3306/"+nombreBD;
			String usuario = "root";
			String password = "mysql";
			con = DriverManager.getConnection(url, usuario, password); //Estableciendo conexion con la BD
			
			System.out.println("Conexion realizada con exito");
			
		} catch (ClassNotFoundException e) {
			System.out.println("Error al cargar el driver");
			e.printStackTrace();
		
		} catch (SQLException e1) {
			System.out.println("Error al conectar a la base de datos");
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		return con; //devolviendo una conexion
		
		
	}
	
}
