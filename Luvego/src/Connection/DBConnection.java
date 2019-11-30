package Connection;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.table.DefaultTableModel;

import Logico.Empresa;

public class DBConnection implements Serializable{
	
	private static final long serialVersionUID = 1L;
    private Statement sta;
    private static DBConnection connectionToStore;
    private Connection conn;
    
    private DBConnection() throws ClassNotFoundException, SQLException {

    	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    	conn = DriverManager.getConnection("jdbc:sqlserver://ojosdelacara.database.windows.net:1433;database=Luvego;user=JeanGeorge@ojosdelacara;password=Luvego12*45JeanKGeorge;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;");
        sta = conn.createStatement();
        
        setTotales(); //poniendo en las ventanas todos los totales de los objetos registrados

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
    
    public void agregarProgramador(String cedula,String nombre, String apellidos, String sexo, String telefono1, String telefono2, String correo, String direccion,int edad, float salarioHora) throws SQLException {
        CallableStatement cs = conn.prepareCall("{call [dbo].[spAgregarProgramador]('"+cedula+"','"+nombre+"','"+apellidos+"','"+sexo+"','"+direccion+"','"+edad+"','"+correo+"','"+telefono1+"','"+telefono2+"','"+salarioHora+"')}");
        cs.executeUpdate();
    }
    public void agregarDisegnador(String cedula,String nombre, String apellidos, String sexo, String telefono1, String telefono2, String correo, String direccion,int edad, float salarioHora) throws SQLException {
        CallableStatement cs = conn.prepareCall("{call [dbo].[spAgregarDisegnador]('"+cedula+"','"+nombre+"','"+apellidos+"','"+sexo+"','"+direccion+"','"+edad+"','"+correo+"','"+telefono1+"','"+telefono2+"','"+salarioHora+"')}");
        cs.executeUpdate();
    }
    public void agregarPlanificador(String cedula,String nombre, String apellidos, String sexo, String telefono1, String telefono2, String correo, String direccion,int edad, float salarioHora,int frecuencia) throws SQLException {
        CallableStatement cs = conn.prepareCall("{call [dbo].[spAgregarPlanificador]('"+cedula+"','"+nombre+"','"+apellidos+"','"+sexo+"','"+direccion+"','"+edad+"','"+correo+"','"+telefono1+"','"+telefono2+"','"+salarioHora+"','"+frecuencia+"')}");
        cs.executeUpdate();
    }
    
    public void agregarJefe(String cedula,String nombre, String apellidos, String sexo, String telefono1, String telefono2, String correo, String direccion,int edad, float salarioHora) throws SQLException {
        CallableStatement cs = conn.prepareCall("{call [dbo].[spAgregarJefe]('"+cedula+"','"+nombre+"','"+apellidos+"','"+sexo+"','"+direccion+"','"+edad+"','"+correo+"','"+telefono1+"','"+telefono2+"','"+salarioHora+"')}");
        cs.executeUpdate();
    }
    
    public void agregarProyecto(String nombreProyecto, String estadoProyecto, String lenguaje, String atrasado, String especialidad,
    		String cedulaCliente, String fechaEntrega, String precioFinal, String estadoContrato,
    		String codigoJefe, String codigoPlanificador, String codigoProgramadorUno, String codigoProgramadorDos, String codigoAdicional) throws SQLException {
    	
    	CallableStatement cs = conn.prepareCall("{call [dbo].[spAgregarProyectoContrato]('"+nombreProyecto+"','"+estadoProyecto+"','"+lenguaje+"','"+atrasado+"','"+especialidad+"','"+
    																						cedulaCliente+"','"+fechaEntrega+"','"+precioFinal+"','"+estadoContrato+"','"+
																							codigoJefe+"','"+codigoPlanificador+"','"+codigoProgramadorUno+"','"+codigoProgramadorDos+"','"+codigoAdicional+"')}");
    	cs.executeUpdate();
    }

    public void agregarEspecialidadProgramador(String especialidad) throws SQLException
    {
    	CallableStatement cs = conn.prepareCall("{call [dbo].[spAgregarEspecialidad]('"+especialidad+"')}");
        cs.executeUpdate();
    }
    
    
    
  ////////////MOSTRAR ENTIDADES\\\\\\\\\\\\\\\\
    public DefaultTableModel mostrarEmpleados() throws SQLException{

      DefaultTableModel modelo= new DefaultTableModel();
      Statement s = conn.createStatement();
      ResultSet rs = s.executeQuery("select codigo,nombre,apellidos,evaluacion,condicion from infoEmpleado");
      
      modelo.addColumn("Codigo");
      modelo.addColumn("Nombre");
      modelo.addColumn("Apellidos");
      modelo.addColumn("Evaluacion");
      modelo.addColumn("Condicion");
      
   // Bucle para cada resultado en la consulta
      while (rs.next())
      {
         // Se crea un array que será una de las filas de la tabla.
         Object [] fila = new Object[5]; // Hay tres columnas en la tabla

         // Se rellena cada posición del array con una de las columnas de la tabla en base de datos.
         for (int i=0;i<5;i++)
            fila[i] = rs.getObject(i+1); // El primer indice en rs es el 1, no el cero, por eso se suma 1.

         // Se añade al modelo la fila completa.
         modelo.addRow(fila);
      }
      
      return modelo;      
    }   
    
    public DefaultTableModel mostrarContratos() throws SQLException{

        DefaultTableModel modelo= new DefaultTableModel();
        Statement s = conn.createStatement();
        ResultSet rs = s.executeQuery("select * from infoContrato");
        
        modelo.addColumn("ID");
        modelo.addColumn("Cliente");
        modelo.addColumn("FechaInicio");
        modelo.addColumn("FechaEntrega");
        modelo.addColumn("PrecioAcordado");
        
     // Bucle para cada resultado en la consulta
        while (rs.next())
        {
           // Se crea un array que será una de las filas de la tabla.
           Object [] fila = new Object[5]; // Hay tres columnas en la tabla

           // Se rellena cada posición del array con una de las columnas de la tabla en base de datos.
           for (int i=0;i<5;i++)
              fila[i] = rs.getObject(i+1); // El primer indice en rs es el 1, no el cero, por eso se suma 1.

           // Se añade al modelo la fila completa.
           modelo.addRow(fila);
        }
        
        return modelo;      
      }   
    
    public DefaultTableModel mostrarJefes() throws SQLException{

        DefaultTableModel modelo= new DefaultTableModel();
        Statement s = conn.createStatement();
        ResultSet rs = s.executeQuery("select codigo,nombre,salarioHora,totalProyectos,evaluacion from infoEmpleado where codigo like('JFE%')");
        
        modelo.addColumn("Codigo");
        modelo.addColumn("Nombre");
        modelo.addColumn("Salario/Hora");
        modelo.addColumn("Proyectos Totales");
        modelo.addColumn("Evaluacion");
        
     // Bucle para cada resultado en la consulta
        while (rs.next())
        {
           // Se crea un array que será una de las filas de la tabla.
           Object [] fila = new Object[5]; // Hay tres columnas en la tabla

           // Se rellena cada posición del array con una de las columnas de la tabla en base de datos.
           for (int i=0;i<5;i++)
              fila[i] = rs.getObject(i+1); // El primer indice en rs es el 1, no el cero, por eso se suma 1.

           // Se añade al modelo la fila completa.
           modelo.addRow(fila);
        }
        
        return modelo;      
      }   
    
    public DefaultTableModel mostrarDisegnador() throws SQLException{

        DefaultTableModel modelo= new DefaultTableModel();
        Statement s = conn.createStatement();
        ResultSet rs = s.executeQuery("select codigo,nombre,salarioHora,totalProyectos,evaluacion from infoEmpleado where codigo like('DSG%')");
        
        modelo.addColumn("Codigo");
        modelo.addColumn("Nombre");
        modelo.addColumn("Salario/Hora");
        modelo.addColumn("Proyectos Totales");
        modelo.addColumn("Evaluacion");
        
     // Bucle para cada resultado en la consulta
        while (rs.next())
        {
           // Se crea un array que será una de las filas de la tabla.
           Object [] fila = new Object[5]; // Hay tres columnas en la tabla

           // Se rellena cada posición del array con una de las columnas de la tabla en base de datos.
           for (int i=0;i<5;i++)
              fila[i] = rs.getObject(i+1); // El primer indice en rs es el 1, no el cero, por eso se suma 1.

           // Se añade al modelo la fila completa.
           modelo.addRow(fila);
        }
        
        return modelo;      
      }   
    
    public DefaultTableModel mostrarProgramador() throws SQLException{

        DefaultTableModel modelo= new DefaultTableModel();
        Statement s = conn.createStatement();
        ResultSet rs = s.executeQuery("select codigo,nombre,salarioHora,totalProyectos,evaluacion from infoEmpleado where codigo like('PRG%')");
        
        modelo.addColumn("Codigo");
        modelo.addColumn("Nombre");
        modelo.addColumn("Salario/Hora");
        modelo.addColumn("Proyectos Totales");
        modelo.addColumn("Evaluacion");
        
     // Bucle para cada resultado en la consulta
        while (rs.next())
        {
           // Se crea un array que será una de las filas de la tabla.
           Object [] fila = new Object[5]; // Hay tres columnas en la tabla

           // Se rellena cada posición del array con una de las columnas de la tabla en base de datos.
           for (int i=0;i<5;i++)
              fila[i] = rs.getObject(i+1); // El primer indice en rs es el 1, no el cero, por eso se suma 1.

           // Se añade al modelo la fila completa.
           modelo.addRow(fila);
        }
        
        return modelo;      
      }   

    public DefaultTableModel mostrarProgramadorEspecializado(String especialidad) throws SQLException{

        DefaultTableModel modelo= new DefaultTableModel();
        Statement s = conn.createStatement();
        ResultSet rs = s.executeQuery("select infoEmpleado.codigo,infoEmpleado.nombre,salarioHora,totalProyectos,evaluacion from infoEmpleado " + 
        		"  inner join Programador_Especialidad on Programador_Especialidad.codigo = infoEmpleado.codigo" + 
        		"  where infoEmpleado.codigo like('PRG%') and Programador_especialidad.especialidad ="+"'"+especialidad+"'");
        
        modelo.addColumn("Codigo");
        modelo.addColumn("Nombre");
        modelo.addColumn("Salario/Hora");
        modelo.addColumn("Proyectos Totales");
        modelo.addColumn("Evaluacion");
        
     // Bucle para cada resultado en la consulta
        while (rs.next())
        {
           // Se crea un array que será una de las filas de la tabla.
           Object [] fila = new Object[5]; // Hay tres columnas en la tabla

           // Se rellena cada posición del array con una de las columnas de la tabla en base de datos.
           for (int i=0;i<5;i++)
              fila[i] = rs.getObject(i+1); // El primer indice en rs es el 1, no el cero, por eso se suma 1.

           // Se añade al modelo la fila completa.
           modelo.addRow(fila);
        }
        
        return modelo;      
      }   
    
    public DefaultTableModel mostrarPlanificador() throws SQLException{

        DefaultTableModel modelo= new DefaultTableModel();
        Statement s = conn.createStatement();
        ResultSet rs = s.executeQuery("select codigo,nombre,salarioHora,totalProyectos,evaluacion from infoEmpleado where codigo like('PLN%')");
        
        modelo.addColumn("Codigo");
        modelo.addColumn("Nombre");
        modelo.addColumn("Salario/Hora");
        modelo.addColumn("Proyectos Totales");
        modelo.addColumn("Evaluacion");
        
     // Bucle para cada resultado en la consulta
        while (rs.next())
        {
           // Se crea un array que será una de las filas de la tabla.
           Object [] fila = new Object[5]; // Hay tres columnas en la tabla

           // Se rellena cada posición del array con una de las columnas de la tabla en base de datos.
           for (int i=0;i<5;i++)
              fila[i] = rs.getObject(i+1); // El primer indice en rs es el 1, no el cero, por eso se suma 1.

           // Se añade al modelo la fila completa.
           modelo.addRow(fila);
        }
        
        return modelo;      
      }   
    
    public DefaultTableModel mostrarClientes() throws SQLException
    {
    	 DefaultTableModel modelo= new DefaultTableModel();
         Statement s = conn.createStatement();
         ResultSet rs = s.executeQuery("select * from infoRegistroCliente");
         
         modelo.addColumn("Cedula");
         modelo.addColumn("Nombre");
         modelo.addColumn("Contratos Activos");
         modelo.addColumn("Contratos Totales");
         
      // Bucle para cada resultado en la consulta
         while (rs.next())
         {
            // Se crea un array que será una de las filas de la tabla.
            Object [] fila = new Object[4]; // Hay tres columnas en la tabla

            // Se rellena cada posición del array con una de las columnas de la tabla en base de datos.
            for (int i=0;i<4;i++)
               fila[i] = rs.getObject(i+1); // El primer indice en rs es el 1, no el cero, por eso se suma 1.

            // Se añade al modelo la fila completa.
            modelo.addRow(fila);
         }
         
         return modelo;       
    }
    
    public DefaultTableModel mostrarProyectosVentanaPrincipal() throws SQLException
    {
    	 DefaultTableModel modelo= new DefaultTableModel();
         Statement s = conn.createStatement();
         ResultSet rs = s.executeQuery("select idProyecto,nombre,Estado from Proyecto");
         
         modelo.addColumn("ID");
         modelo.addColumn("Nombre");
         modelo.addColumn("Estado");
         
      // Bucle para cada resultado en la consulta
         while (rs.next())
         {
            // Se crea un array que será una de las filas de la tabla.
            Object [] fila = new Object[3]; // Hay tres columnas en la tabla

            // Se rellena cada posición del array con una de las columnas de la tabla en base de datos.
            for (int i=0;i<3;i++)
               fila[i] = rs.getObject(i+1); // El primer indice en rs es el 1, no el cero, por eso se suma 1.

            // Se añade al modelo la fila completa.
            modelo.addRow(fila);
         }
         
         return modelo;       
    }
    
    
  /*  public void getInfoEmpleadoByCodigo(String codigo)
    {
    	  CallableStatement cs = conn.prepareCall("exec spG");
    	  ResultSet rs = cs.executeQuery("exec spInfoEmpleadoByCodigo")
          
    }
    */
    ////////RETORNO DE CANTIDADES\\\\\\\\\\\\
    public int setTotales() throws SQLException
    {
    	
    	Statement st = conn.createStatement();
    	ResultSet rs = st.executeQuery("select count(*) as total from Empleado");
        while(rs.next())
        {
        	Empresa.getInstance().setTotalEmpleados(rs.getInt("total"));	
        }
        
        rs = st.executeQuery("select count(*) as total from Empleado where codigo like('PRG%')");
        while(rs.next())
        {
        	Empresa.getInstance().setTotalProgramadores(rs.getInt("total"));	
        }
        
        rs = st.executeQuery("select count(*) as total from Empleado where codigo like('PLN%')");
        while(rs.next())
        {
        	Empresa.getInstance().setTotalPlanificadores(rs.getInt("total"));	
        }
        
        rs = st.executeQuery("select count(*) as total from Empleado where codigo like('JFE%')");
        while(rs.next())
        {
        	Empresa.getInstance().setTotalJefes(rs.getInt("total"));	
        }
        
        rs = st.executeQuery("select count(*) as total from Empleado where codigo like('DSG%')");
        while(rs.next())
        {
        	Empresa.getInstance().setTotalDisegnadores(rs.getInt("total"));	
        }
		return 0;
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
