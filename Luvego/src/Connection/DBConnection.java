package Connection;

import java.io.File;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.CachedRowSet;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSetMetaData;
import com.sun.rowset.CachedRowSetImpl;

import Logico.Cliente;
import Logico.Contrato;
import Logico.Disegnador;
import Logico.Empleado;
import Logico.Empresa;
import Logico.Jefe;
import Logico.Planificador;
import Logico.Programador;
import Logico.Proyecto;
import javafx.print.Collation;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;

public class DBConnection implements Serializable{
	
	private static final long serialVersionUID = 1L;
    private Statement sta;
    private Statement sta2;
    private Statement sta3;
    private static DBConnection connectionToStore;
    private Connection conn;
    
    private DBConnection() throws ClassNotFoundException, SQLException {

    	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    	conn = DriverManager.getConnection("jdbc:sqlserver://ojosdelacara.database.windows.net:1433;database=Luvego;user=JeanGeorge@ojosdelacara;password=Luvego12*45JeanKGeorge;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;");
        sta = conn.createStatement();
        sta2 = conn.createStatement();
        sta3 = conn.createStatement();
        
        setTotales(); //poniendo en las ventanas todos los totales de los objetos registrados

    }
    
    public static DBConnection getInstance() throws SQLException, ClassNotFoundException {
        if(connectionToStore == null){
            connectionToStore = new DBConnection();
        }
        return connectionToStore;
    }
    
    public Connection getConnectionn() {
    	return conn;
    }
    
    public void generarReporteContratos() throws JRException {
    	/* JasperReport is the object
		that holds our compiled jrxml file */
		JasperReport jasperReport;


		/* JasperPrint is the object contains
		report after result filling process */
		JasperPrint jasperPrint;
		
		HashMap jasperParameter = new HashMap();
		
		jasperReport = JasperCompileManager.compileReport
				(new File("").getAbsolutePath()+"\\src\\Logico\\ReporteContrato2.jrxml");
		
		jasperPrint = JasperFillManager.fillReport(jasperReport,jasperParameter, getConnectionn()); 
		
		JasperExportManager.exportReportToPdfFile(jasperPrint, new File("").getAbsolutePath()+"\\src\\Logico\\reporteContratos.pdf");
    }
    
    public void agregarCliente(String cedula, String nombre, String apellidos, String sexo, String telefono1, String telefono2, String correo, String direccion) throws SQLException {
        CallableStatement cs = conn.prepareCall("{call [dbo].[spAgregarCliente]('"+cedula+"','"+nombre+"','"+apellidos+"','"+sexo+"','"+telefono1+"','"+telefono2+"','"+correo+"','"+direccion+"')}");
        cs.executeUpdate();
    }
    
    public void agregarProgramador(String codigo,String cedula,String nombre, String apellidos, String sexo, String telefono1, String telefono2, String correo, String direccion,int edad, float salarioHora) throws SQLException {
        CallableStatement cs = conn.prepareCall("{call [dbo].[spAgregarProgramador]('"+codigo+"','"+cedula+"','"+nombre+"','"+apellidos+"','"+sexo+"','"+direccion+"','"+edad+"','"+correo+"','"+telefono1+"','"+telefono2+"','"+salarioHora+"')}");
        cs.executeUpdate();
    }
    public void agregarDisegnador(String codigo,String cedula,String nombre, String apellidos, String sexo, String telefono1, String telefono2, String correo, String direccion,int edad, float salarioHora) throws SQLException {
    	System.out.println("Diseg agregado");
        //CallableStatement cs = conn.prepareCall("{call [dbo].[spAgregarDisegnador]('"+codigo+"',''"+cedula+"','"+nombre+"','"+apellidos+"','"+sexo+"','"+direccion+"','"+edad+"','"+correo+"','"+telefono1+"','"+telefono2+"','"+salarioHora+"')}");
    	CallableStatement cs = conn.prepareCall("{call [dbo].[spAgregarDisegnador]('"+codigo+"','"+cedula+"','"+nombre+"','"+apellidos+"','"+sexo+"','"+direccion+"','"+edad+"','"+correo+"','"+telefono1+"','"+telefono2+"','"+salarioHora+"')}");
        cs.executeUpdate();
    }
    public void agregarPlanificador(String codigo,String cedula,String nombre, String apellidos, String sexo, String telefono1, String telefono2, String correo, String direccion,int edad, float salarioHora,int frecuencia) throws SQLException {
        CallableStatement cs = conn.prepareCall("{call [dbo].[spAgregarPlanificador]('"+codigo+"','"+cedula+"','"+nombre+"','"+apellidos+"','"+sexo+"','"+direccion+"','"+edad+"','"+correo+"','"+telefono1+"','"+telefono2+"','"+salarioHora+"','"+frecuencia+"')}");
        cs.executeUpdate();
    }
    
    public void agregarJefe(String codigo,String cedula,String nombre, String apellidos, String sexo, String telefono1, String telefono2, String correo, String direccion,int edad, float salarioHora) throws SQLException {
        CallableStatement cs = conn.prepareCall("{call [dbo].[spAgregarJefe]('"+codigo+"','"+cedula+"','"+nombre+"','"+apellidos+"','"+sexo+"','"+direccion+"','"+edad+"','"+correo+"','"+telefono1+"','"+telefono2+"','"+salarioHora+"')}");
        cs.executeUpdate();
    }
    
    public void agregarProyectoContrato(String idProyecto, String nombreProyecto, String estadoProyecto, String lenguaje,int completado, String especialidad,
    		String cedulaCliente,
    		String idContrato, String fechaInicio, String fechaEntrega, String precioFinal, String estadoContrato,
    		String codigoJefe, String codigoPlanificador, String codigoProgramadorUno, String codigoProgramadorDos, String codigoAdicional) throws SQLException {
    	
    
    	CallableStatement cs = conn.prepareCall("{call [dbo].[spAgregarProyectoContrato]('"+idProyecto+"','"+nombreProyecto+"','"+estadoProyecto+"','"+lenguaje+"','"+completado+"','"+especialidad+"','"+
    																						cedulaCliente+"','"+
																							idContrato+"','"+fechaInicio+"','"+fechaEntrega+"','"+precioFinal+"','"+estadoContrato+"','"+
																							codigoJefe+"','"+codigoPlanificador+"','"+codigoProgramadorUno+"','"+codigoProgramadorDos+"','"+codigoAdicional+"')}");
    	cs.executeUpdate();
    	
    	/*CallableStatement csJefe = conn.prepareCall("call [dbo].[spIncrementTotalProyectos]('"+codigoJefe+"')}");
    	csJefe.executeUpdate();
    	CallableStatement csProg = conn.prepareCall("call [dbo].[spIncrementTotalProyectos]('"+codigoProgramadorUno+"')}");
    	csJefe.executeUpdate();
    	CallableStatement csProg2 = conn.prepareCall("call [dbo].[spIncrementTotalProyectos]('"+codigoProgramadorDos+"')}");;
    	csJefe.executeUpdate();
    	CallableStatement csPlan = conn.prepareCall("call [dbo].[spIncrementTotalProyectos]('"+codigoPlanificador+"')}");
    	csJefe.executeUpdate();
    	CallableStatement csAdicional = conn.prepareCall("call [dbo].[spIncrementTotalProyectos]('"+codigoAdicional+"')}");
    	csJefe.executeUpdate();*/
    }

    public void agregarEspecialidadProgramador(String codigo,String especialidad) throws SQLException
    {
    	CallableStatement cs = conn.prepareCall("{call [dbo].[spAgregarEspecialidad]('"+codigo+"','"+especialidad+"')}");
        cs.executeUpdate();
    }
    
    public void aplazarContrato(String idContrato, String nuevaFecha) throws SQLException {
    	CallableStatement cs = conn.prepareCall("{call [dbo].[spAplazarContrato]('"+idContrato+"','"+nuevaFecha+"')}");
    	cs.executeUpdate();
    }
    
    
    public void borrarEmpleado(String codigo) throws SQLException
    {
    	CallableStatement cs = conn.prepareCall("{call [dbo].[spEliminarEmpleado]('"+codigo+"')}");
        cs.executeUpdate();
    }
    
    public void finalizarProyecto(String idProyecto) throws SQLException
    {
        CallableStatement cs = conn.prepareCall("{call [dbo].[spFinalizarProyecto]('"+idProyecto+"')}");
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
    
    public void guardarVenta(int idProyecto,String cedulaCliente,String nombreCliente,String nombreProyecto,String fechaInicio, String fechaEntrega, float perdidasTotales, float ganancias) throws SQLException
    {
    	CallableStatement cs = conn.prepareCall("{call [dbo].[spGuardarVenta]('"+idProyecto+"','"+cedulaCliente+"','"+nombreCliente+"','"+nombreProyecto+"','"+fechaInicio+"','"+fechaEntrega+"','"+perdidasTotales+"','"+ganancias+"')}");
        cs.executeUpdate();
        
    
    	/*Statement s = conn.createStatement();
        s.executeUpdate("insert into Venta(idProyecto,idCliente,nombreCliente,nombreProyecto,fecha_inicio,fecha_entrega,perdidasTotales,ganancias)" 
        		+"values(idProyecto,cedulaCliente,nombreCliente,nombreProyecto,fechaInicio,fechaEntrega,perdidasTotales,ganancias)");
        */
    	//new SimpleDateFormat("yyyy-MM-dd").parse(datosContrato.getString(5)
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
    
    public void fijarEstadoProyecto(String idProyecto, String estado) throws SQLException
    {
        CallableStatement cs = conn.prepareCall("{call [dbo].[spFijarEstadoProyecto]('"+idProyecto+"','"+estado+"')}");
        cs.executeUpdate();
    }

    public void fijarEvaluacionAnualEmpleado(String idEmpleado, String evaluacion) throws SQLException
    {
        CallableStatement cs = conn.prepareCall("{call [dbo].[spFijarEvaluacionAnualEmpleado]('"+idEmpleado+"','"+evaluacion+"')}");
        cs.executeUpdate();
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
    
   public void cargarDatos() throws SQLException, ParseException
   {
	   
	   Statement st = conn.createStatement();
	   Statement st2 = conn.createStatement();
	   Statement st3 = conn.createStatement();
	   Statement st4 = conn.createStatement();
	   Statement st5 = conn.createStatement();
	   Statement st6 = conn.createStatement();
	   Statement st7 = conn.createStatement();
	   Statement st8 = conn.createStatement();
	   Statement st9 = conn.createStatement();
	   Statement st10 = conn.createStatement();
	   Statement st11 = conn.createStatement();
	   Statement st12 = conn.createStatement();
	   Statement st13 = conn.createStatement();
	    	  
	   
	   //AREA ESTADISTICA
	   ResultSet ventas = st.executeQuery("select * from Venta");
	   ResultSet ganancias = st2.executeQuery("select sum(ganancias) from Venta");
	   ResultSet perdidas = st3.executeQuery("select sum(perdidas) from Venta");
	   ResultSet totalDesktop = st4.executeQuery("select realizados from Popularidad where especialidad = 'Desktop'");
	   ResultSet totalMovil = st5.executeQuery("select realizados from Popularidad where especialidad = 'Movil'");
	   ResultSet totalWeb = st6.executeQuery("select realizados from Popularidad where especialidad = 'Web'");
     
	   ResultSet totalJava = st8.executeQuery("select lenguaje,count(*) as cantidad from Proyecto\r\n" + 
	   		"where lenguaje = 'Java'\r\n" + 
	   		"group by lenguaje"); 
	   ResultSet totalCpp = st9.executeQuery("select lenguaje,count(*) as cantidad from Proyecto\r\n" + 
		   		"where lenguaje = 'C++'\r\n" + 
		   		"group by lenguaje");
	   ResultSet totalCS = st10.executeQuery("select lenguaje,count(*) as cantidad from Proyecto\r\n" + 
		   		"where lenguaje = 'C#'\r\n" + 
		   		"group by lenguaje");
	   ResultSet totalPython = st11.executeQuery("select lenguaje,count(*) as cantidad from Proyecto\r\n" + 
		   		"where lenguaje = 'Python'\r\n" + 
		   		"group by lenguaje");   
	   ResultSet totalHTML = st13.executeQuery("select lenguaje,count(*) as cantidad from Proyecto\r\n" + 
		   		"where lenguaje like('HTM%')\r\n" + 
		   		"group by lenguaje");
		   
	   
	   //ResultSet totalMovil = st5.executeQuery("select realizados from Popularidad where especialidad = 'Movil'");
	   //ResultSet totalWeb = st6.executeQuery("select realizados from Popularidad where especialidad = 'Web'");
	   
	   while(ganancias.next())
		   Empresa.getInstance().setGanancias(ganancias.getFloat(1));
	   while(perdidas.next())
		   Empresa.getInstance().setPerdidasTotales(perdidas.getFloat(1));
	   while(totalJava.next())
		   Empresa.getInstance().setCantJava(totalJava.getInt(2));
	   while(totalCS.next())
		   Empresa.getInstance().setCantCSharp(totalCS.getInt(2));
	   while(totalCpp.next())
		   Empresa.getInstance().setCantCP(totalCpp.getInt(2));
	   while(totalCpp.next())
		   Empresa.getInstance().setCantPython(totalPython.getInt(2));
	   while(totalCpp.next())
		   Empresa.getInstance().setCantHtml(totalHTML.getInt(2));
	   
	   //Popularidad de proyectos
	   while(totalDesktop.next())
		    Empresa.getInstance().setCantDesktop(totalDesktop.getInt(1));
	   while(totalMovil.next())
	   		Empresa.getInstance().setCantMovil(totalMovil.getInt(1));
	   while(totalWeb.next())
	   		Empresa.getInstance().setCantWeb(totalWeb.getInt(1));
	   
	   while(totalJava.next())
		   Empresa.getInstance().setCantJava(totalJava.getInt(2));
	   
	   while(ventas.next())
	   {
		   Empresa.getInstance().setUltimoId(ventas.getString(1));
		   Empresa.getInstance().setUltimoIdCliente(ventas.getString(2));
		   Empresa.getInstance().setUltimoNombreCliente(ventas.getString(3));
		   Empresa.getInstance().setUltimoNombreProyecto(ventas.getString(4));
		   //Empresa.getInstance().setUltimoFechaSolicitud(ventas.getDate(4));
		  // Empresa.getInstance().setUltimoFechaEntrega(ventas.getDate(5).valueOf(Date));
		   Empresa.getInstance().setUltimasPerdida(ventas.getFloat(7));
		   Empresa.getInstance().setUltimaGanancia(ventas.getFloat(8));
	   }
	   
	   /*ventas = st.executeQuery("select count(*) from Venta");
	   while(ventas.next())
		   Empresa.getInstance().setCantProyectosTerminados(ventas.getInt(1));
	   */
	   //Ingresando Programadores
	   ResultSet datosEmpleado = st.executeQuery("select codigo,nombre,apellidos,sexo,edad,telefono1,telefono2,direccion,salarioHora from infoEmpleado where codigo like('PRG%')");
   	   
	   //especialidades del programador
	   ResultSet especialidad;
	   ArrayList<String> especialidades = new ArrayList<String>();
	      	   
   	   while(datosEmpleado.next())
   	   {
   		   especialidad = st2.executeQuery("select * from Programador_Especialidad where codigo ="+"'"+datosEmpleado.getString(1)+"'");
   		   especialidades = new ArrayList<String>();
   		   
   		   while(especialidad.next())
   		   {
   			   especialidades.add(especialidad.getString(2));
   			
   		   }

   		   Empleado empleado = new Programador(datosEmpleado.getString(1),datosEmpleado.getString(2),
   				   datosEmpleado.getString(3),datosEmpleado.getString(4),datosEmpleado.getInt(5),datosEmpleado.getString(6),datosEmpleado.getString(7),
   				datosEmpleado.getString(8),datosEmpleado.getFloat(9),especialidades);
   		   
   		   //System.out.printf("arr: %s\n", datosEmpleado.getString(1));
   		   empleado.setId(datosEmpleado.getString(1).substring(4));
   		   Empresa.getInstance().nuevoEmpleado(empleado);
   	   }
   	   
   	 
   	   datosEmpleado = st.executeQuery("select codigo,nombre,apellidos,sexo,edad,telefono1,telefono2,direccion,salarioHora,proyectosAtrasados,proyectosActivos,totalProyectos,condicion,evaluacion,correo from infoEmpleado where codigo like('DSG%')");
	   
   	   while(datosEmpleado.next())
   	   {
   		   Empleado empleado = new Disegnador(datosEmpleado.getString(1),datosEmpleado.getString(2),
   				   datosEmpleado.getString(3),datosEmpleado.getString(4),datosEmpleado.getInt(5),datosEmpleado.getString(6),datosEmpleado.getString(7),
   				datosEmpleado.getString(8),datosEmpleado.getFloat(9));
   		   
   		   
   		   
   		empleado.setId(datosEmpleado.getString(1).substring(4));
   		
 
   		Empresa.getInstance().nuevoEmpleado(empleado);
   		empleado.setProyectosAtrasados(datosEmpleado.getInt(10));
   	   }
   	   
   	   datosEmpleado = st.executeQuery("select infoEmpleado.codigo,nombre,apellidos,sexo,edad,telefono1,telefono2,direccion,salarioHora,frecuencia from infoEmpleado inner join Planificador on Planificador.codigo = infoEmpleado.codigo");
	   
	   while(datosEmpleado.next())
	   {
		   Empleado empleado = new Planificador(datosEmpleado.getString(1),datosEmpleado.getString(2),
				   datosEmpleado.getString(3),datosEmpleado.getString(4),datosEmpleado.getInt(5),datosEmpleado.getString(6),datosEmpleado.getString(7),
				datosEmpleado.getString(8),datosEmpleado.getFloat(9),datosEmpleado.getInt(10));
		   
		   empleado.setId(datosEmpleado.getString(1).substring(4));
		   Empresa.getInstance().nuevoEmpleado(empleado);
	   }
	   
	   datosEmpleado = st.executeQuery("select infoEmpleado.codigo,nombre,apellidos,sexo,edad,telefono1,telefono2,direccion,salarioHora from infoEmpleado where codigo like('JFE%')");
	   
	   while(datosEmpleado.next())
	   {
		   Empleado empleado = new Jefe(datosEmpleado.getString(1),datosEmpleado.getString(2),
				   datosEmpleado.getString(3),datosEmpleado.getString(4),datosEmpleado.getInt(5),datosEmpleado.getString(6),datosEmpleado.getString(7),
				datosEmpleado.getString(8),datosEmpleado.getFloat(9));
		   
		   empleado.setId(datosEmpleado.getString(1).substring(4));
		   Empresa.getInstance().nuevoEmpleado(empleado);
	   }
	   
	   ResultSet rsCliente = st.executeQuery("select cedula,nombre,correo,telefono1,telefono2,direccion,sexo from infoRegistroCliente");
	   CachedRowSet datosCliente = new CachedRowSetImpl();
	   datosCliente.populate(rsCliente);
	   
	   while(datosCliente.next())
	   {
		   Cliente cliente = new Cliente(datosCliente.getString(1),datosCliente.getString(2),datosCliente.getString(3),datosCliente.getString(4),datosCliente.getString(5),datosCliente.getString(6),datosCliente.getString(7));
		   Empresa.getInstance().nuevoCliente(cliente);
	   }
	   
	   
	   ResultSet datosProyecto = sta.executeQuery("SELECT idProyecto, nombre, especialidad, estado, lenguaje, completado FROM Proyecto where completado = 0");
	   /*ResultSet rsProyecto = sta.executeQuery("SELECT idProyecto, nombre, especialidad, estado, lenguaje, atrasado FROM Proyecto");
	   CachedRowSet datosProyecto = new CachedRowSetImpl();
	   datosProyecto.populate(rsProyecto);*/
	   
	   ResultSet datosContrato = sta2.executeQuery("SELECT idContrato, idProyecto, cedCliente, fecha_inicio, fecha_entrega, precio_final, estado FROM Contrato");
	   /*ResultSet rsContrato = sta.executeQuery("SELECT idContrato, idProyecto, cedCliente, fecha_inicio, fecha_entrega, precio_final, estado FROM Contrato");
	   CachedRowSet datosContrato = new CachedRowSetImpl();
	   datosContrato.populate(rsContrato);*/
	   
	   
	   ResultSet datosGrupoDeTrabajo = sta3.executeQuery("SELECT idProyecto, codEmpleado FROM Proyecto_Empleado");
	   /*ResultSet rsGrupoDeTrabajo = sta2.executeQuery("SELECT idProyecto, codEmpleado FROM Proyecto_Empleado");
	   CachedRowSet datosGrupoDeTrabajo = new CachedRowSetImpl();
	   datosGrupoDeTrabajo.populate(rsGrupoDeTrabajo);*/
	   
	   
	   ArrayList<Empleado> grupoTrabajo = new ArrayList<>();
	   String codCliente = null;
	   String codContrato = null;
	   
	   while(datosProyecto.next()) {
		   System.out.printf("proyecto %s:%s\n", datosProyecto.getString(1), datosProyecto.getString(2));
		   //System.out.printf("%s\n", datosProyecto.getString(1));
		   
		   while(datosGrupoDeTrabajo.next()) {
			     for (Empleado empleado : Empresa.getInstance().getEmpleados()) {
					    if (empleado.getId().equals(datosGrupoDeTrabajo.getString(2))) {
					    	grupoTrabajo.add(Empresa.getInstance().getEmpleadoById(datosGrupoDeTrabajo.getString(2)));
					    }
					    else {
					    	//System.out.printf("%s != %s\n", datosGrupoDeTrabajo.getString(2), empleado.getId());
					    }
					}
		   }
		   
		   /*for(int i = 0; i < grupoTrabajo.size(); i++) {
			   System.out.printf("%s ", grupoTrabajo.get(i).getCargo());
		   }*/
		   
		   ordenarGrupoDeTrabajo(grupoTrabajo);
		   
		   /*System.out.println();
		   
		   for(int i = 0; i < grupoTrabajo.size(); i++) {
			   System.out.printf("%s ", grupoTrabajo.get(i).getCargo());
		   }*/
		   
		   
		   while (datosContrato.next()) {
			   
			   if(datosContrato.getString(2).equals((datosProyecto.getString(1)))) {
				   
				   codCliente = datosContrato.getString(3);
				   codContrato = datosContrato.getString(1);
				   
				   //System.out.printf("len GT = %d\n", grupoTrabajo.size());
				   Proyecto proyecto = new Proyecto(datosProyecto.getString(2), grupoTrabajo, datosProyecto.getString(3), datosProyecto.getString(5));
				   proyecto.setId(datosProyecto.getString(1));
				   proyecto.setCont(Integer.parseInt(proyecto.getId()));
				   //System.out.printf("un sueldo %d\n", proyecto.getGrupoTrabajo().get(0).getSalarioHora());
				   //System.out.printf("un sueldo = %f", proyecto.getGrupoTrabajo().get(0).getCedula());
				   
				   Contrato contrato = new Contrato(proyecto, new SimpleDateFormat("yyyy-MM-dd").parse(datosContrato.getString(5)));
				   contrato.setId(datosContrato.getString(1));
				   contrato.setCliente(Empresa.getInstance().getClienteById(codCliente));
				   proyecto.setContrato(contrato);
				   Empresa.getInstance().agregarProyecto(proyecto);
				   
				   break; // ????????????????????/
			   }
			
		   }
		   
		   /*while (datosCliente.next()) {
				
			   if(datosCliente.getString(1).equals(codCliente)) {
				   
			   }
			
		   }*/
	   }
	   
	   int cantProyectos = 0;
	   ResultSet rsCantProyectos = sta3.executeQuery("SELECT COUNT(*) FROM [dbo].[Proyecto]");
	   while(rsCantProyectos.next()) {
		   cantProyectos = Integer.valueOf(rsCantProyectos.getString(1));
		   break;
	   }
	   
	   Proyecto.setCont(cantProyectos);
	   System.out.printf("catproyet = %d", Proyecto.getCont());
   }
   
   private void ordenarGrupoDeTrabajo(ArrayList<Empleado> grupoDeTrabajo) throws NumberFormatException, SQLException {
	   	   
	   for(int i = 0; i < grupoDeTrabajo.size(); i++) {
		   for(int j = i; j < grupoDeTrabajo.size(); j++) {
			   if(i == 0) {
				   if(grupoDeTrabajo.get(j).getCargo().equals("Jefe de proyectos")) {
					   Collections.swap(grupoDeTrabajo, i, j);
					   i++;
					   j = i;
				   }
			   }
			   if(i == 1) {
				   if(grupoDeTrabajo.get(j).getCargo().equals("Planificador")) {
					   Collections.swap(grupoDeTrabajo, i, j);
					   i++;
					   j = i;
				   }
			   }
			   if(i == 2 || i == 3) {
				   if(grupoDeTrabajo.get(j).getCargo().equals("Programador")) {
					   Collections.swap(grupoDeTrabajo, i, j);
					   i++;
					   j = i;
				   }
			   }
		   }
	   }
	   
	   /*for(int i = 0; i < grupoDeTrabajo.size(); i++) {
		   System.out.printf("%s ", grupoDeTrabajo.get(i).getCargo());
	   }*/
	   
   }

public void ProyectosActivos(String codigo) throws SQLException {
	
	CallableStatement cs = conn.prepareCall("{call [dbo].[spProyectoActivos]('"+codigo+"')}");
    cs.executeUpdate();
	 
	/*for(int i = 0; i < grupoTrabajo.size();i++)
	{
		 cs = conn.prepareCall("exec spIncrementarProyectosActivos '"+grupoTrabajo.get(i).getId()+"'");
		 cs.executeUpdate();
			 
	}*/
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
