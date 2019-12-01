package Connection;

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
import java.util.Collection;
import java.util.Collections;

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
    
    public void agregarProgramador(String codigo,String cedula,String nombre, String apellidos, String sexo, String telefono1, String telefono2, String correo, String direccion,int edad, float salarioHora) throws SQLException {
        CallableStatement cs = conn.prepareCall("{call [dbo].[spAgregarProgramador]('"+codigo+"','"+cedula+"','"+nombre+"','"+apellidos+"','"+sexo+"','"+direccion+"','"+edad+"','"+correo+"','"+telefono1+"','"+telefono2+"','"+salarioHora+"')}");
        cs.executeUpdate();
    }
    public void agregarDisegnador(String codigo,String cedula,String nombre, String apellidos, String sexo, String telefono1, String telefono2, String correo, String direccion,int edad, float salarioHora) throws SQLException {
        CallableStatement cs = conn.prepareCall("{call [dbo].[spAgregarDisegnador]('"+codigo+"',''"+cedula+"','"+nombre+"','"+apellidos+"','"+sexo+"','"+direccion+"','"+edad+"','"+correo+"','"+telefono1+"','"+telefono2+"','"+salarioHora+"')}");
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
    
    public void agregarProyecto(String nombreProyecto, String estadoProyecto, String lenguaje, String atrasado, String especialidad,
    		String cedulaCliente, String fechaEntrega, String precioFinal, String estadoContrato,
    		String codigoJefe, String codigoPlanificador, String codigoProgramadorUno, String codigoProgramadorDos, String codigoAdicional) throws SQLException {
    	
    	CallableStatement cs = conn.prepareCall("{call [dbo].[spAgregarProyectoContrato]('"+nombreProyecto+"','"+estadoProyecto+"','"+lenguaje+"','"+atrasado+"','"+especialidad+"','"+
    																						cedulaCliente+"','"+fechaEntrega+"','"+precioFinal+"','"+estadoContrato+"','"+
																							codigoJefe+"','"+codigoPlanificador+"','"+codigoProgramadorUno+"','"+codigoProgramadorDos+"','"+codigoAdicional+"')}");
    	cs.executeUpdate();
    }

    public void agregarEspecialidadProgramador(String codigo,String especialidad) throws SQLException
    {
    	CallableStatement cs = conn.prepareCall("{call [dbo].[spAgregarEspecialidad]('"+codigo+"','"+especialidad+"')}");
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
    
   public void cargarDatos() throws SQLException, ParseException
   {
	   
	   Statement st = conn.createStatement();
	   Statement st2 = conn.createStatement();
   	  
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
   		   
   		   System.out.printf("arr: %s\n", datosEmpleado.getString(1));
   		   empleado.setId(datosEmpleado.getString(1).substring(4));
   		   Empresa.getInstance().nuevoEmpleado(empleado);
   	   }
   	   
   	   datosEmpleado = st.executeQuery("select codigo,nombre,apellidos,sexo,edad,telefono1,telefono2,direccion,salarioHora from infoEmpleado where codigo like('DSG%')");
	   
   	   while(datosEmpleado.next())
   	   {
   		   Empleado empleado = new Disegnador(datosEmpleado.getString(1),datosEmpleado.getString(2),
   				   datosEmpleado.getString(3),datosEmpleado.getString(4),datosEmpleado.getInt(5),datosEmpleado.getString(6),datosEmpleado.getString(7),
   				datosEmpleado.getString(8),datosEmpleado.getFloat(9));
   		   
   		empleado.setId(datosEmpleado.getString(1).substring(4));
   		   Empresa.getInstance().nuevoEmpleado(empleado);
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
	   
	   
	   
	   ResultSet rsProyecto = sta.executeQuery("SELECT idProyecto, nombre, especialidad, estado, lenguaje, atrasado FROM Proyecto");
	   CachedRowSet datosProyecto = new CachedRowSetImpl();
	   datosProyecto.populate(rsProyecto);
	   
	   ResultSet rsContrato = sta.executeQuery("SELECT idContrato, idProyecto, cedCliente, fecha_inicio, fecha_entrega, precio_final, estado FROM Contrato");
	   CachedRowSet datosContrato = new CachedRowSetImpl();
	   datosContrato.populate(rsContrato);
	   
	   ResultSet rsGrupoDeTrabajo = sta.executeQuery("SELECT idProyecto, codEmpleado FROM Proyecto_Empleado");
	   CachedRowSet datosGrupoDeTrabajo = new CachedRowSetImpl();
	   datosGrupoDeTrabajo.populate(rsGrupoDeTrabajo);
	   
	   //System.out.printf("un sueldo = %f\n", Empresa.getInstance().getEmpleadoById("JFE-0000002").getSalarioHora());
	   
	   /*for(int i = 0; i < Empresa.getInstance().getEmpleados().size(); i++) {
		   System.out.printf("%s = JFE-0000005: %b\n", Empresa.getInstance().getEmpleados().get(i).getId(), Empresa.getInstance().getEmpleados().get(i).getId().equals("JFE-0000005"));
	   }*/
	   
	   for(int i = 0; i < Empresa.getInstance().getEmpleados().size(); i++) {
		   System.out.printf("%s\n", Empresa.getInstance().getEmpleados().get(i).getId());
	   }
	   System.out.println();
	   
	   
	   ArrayList<Empleado> grupoTrabajo = new ArrayList<>();
	   String codCliente = null;
	   String codContrato = null;
	   
	   while(datosProyecto.next()) {
		   
		   
		   
		   while(datosGrupoDeTrabajo.next()) {
			   //System.out.printf("%s\n", datosGrupoDeTrabajo.getString(2));
			   //if(datosGrupoDeTrabajo.getString(1).equals((datosProyecto.getString(1)))) {
				   //System.out.printf("%s %s\n", datosGrupoDeTrabajo.getString(1), datosGrupoDeTrabajo.getString(2));
				   for (Empleado empleado : Empresa.getInstance().getEmpleados()) {
					   //System.out.printf("%s\n", empleado.getId());
					    if (empleado.getId().equals(datosGrupoDeTrabajo.getString(2))) {
					    	grupoTrabajo.add(Empresa.getInstance().getEmpleadoById(datosGrupoDeTrabajo.getString(2)));
					        System.out.printf("%s = %s\n", datosGrupoDeTrabajo.getString(2), empleado.getId());
					    }
					    else {
					    	System.out.printf("%s != %s\n", datosGrupoDeTrabajo.getString(2), empleado.getId());
					    }
					}
		   }
		   
		   for(int i = 0; i < grupoTrabajo.size(); i++) {
			   System.out.printf("%s ", grupoTrabajo.get(i).getCargo());
		   }
		   
		   ordenarGrupoDeTrabajo(grupoTrabajo);
		   
		   System.out.println();
		   
		   for(int i = 0; i < grupoTrabajo.size(); i++) {
			   System.out.printf("%s ", grupoTrabajo.get(i).getCargo());
		   }
		   
		   
		   while (datosContrato.next()) {
			   
			   if(datosContrato.getString(2).equals((datosProyecto.getString(1)))) {
				   
				   codCliente = datosContrato.getString(3);
				   codContrato = datosContrato.getString(1);
				   
				   System.out.printf("Contrato");
				   
				   //System.out.printf("len GT = %d\n", grupoTrabajo.size());
				   Proyecto proyecto = new Proyecto(datosProyecto.getString(2), grupoTrabajo, datosProyecto.getString(3), datosProyecto.getString(5));
				   proyecto.setId(datosProyecto.getString(1)); // ?????????????
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
   }
   
   private void ordenarGrupoDeTrabajo(ArrayList<Empleado> grupoDeTrabajo) {
	   	   
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
