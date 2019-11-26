package Logico;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

public class Main {

	public static void main(String[] args) {
	
		ArrayList<String> esp = new ArrayList<>();
		esp.add("Java");
		esp.add("Python");
		
		
		Empleado pln = new Planificador("456","Pedro","j","hombre",19,"8094848","61468464","cerroalto",156,5);
		Empleado pro = new Programador("789","123","j","hombre",19,"8094848","61468464","cerroalto",156,esp);
		Empleado pro2 = new Programador("101","123","j","hombre",19,"8094848","61468464","cerroalto",156,esp);
		Empleado dsg = new Disegnador("112","jeane","j","hombre",19,"684684","64684","cerro alto",15);
		Empleado chef = new Jefe("456","123","j","hombre",19,"8094848","61468464","cerroalto",156);
		

		Empresa.getInstance().nuevoEmpleado(pro);
		Empresa.getInstance().nuevoEmpleado(pro2);
		Empresa.getInstance().nuevoEmpleado(dsg);
		Empresa.getInstance().nuevoEmpleado(chef);
		Empresa.getInstance().nuevoEmpleado(pln);
		
		ArrayList<Empleado> grupo = new ArrayList<>();
		grupo.add(chef);
		grupo.add(pln);
		grupo.add(pro);
		grupo.add(pro2);
		grupo.add(dsg);
		
		Date fecha = new Date("12/12/2018");
		
		
		Cliente cliente = new Cliente("12334","george","gmail","809","276","cerro alto","Hombre");
		Cliente cliente2 = new Cliente("123","george","gmail","809","276","cerro alto","Hombre");
		
		
		Empresa.getInstance().nuevoCliente(cliente);
		Empresa.getInstance().nuevoCliente(cliente2);;
		
		//System.out.println(cliente.getId());
		//System.out.println(cliente2.getId());
	//	System.out.println(Empresa.getInstance().getClienteById("12334").getGenero());
		
	//	Cliente cliente = new Cliente("123","george","gmail","809","276","cerro alto","Hombre");
		Proyecto proyecto = new Proyecto("prueba",grupo,"Movil","C++");
		//Cliente cliente2 = new Cliente("456","george","gmail","809","276","cerro alto","Hombre");
		//Cliente cliente3 = new Cliente("789","george","gmail","809","276","cerro alto","Hombre");
		
	
		Date fechaEntrega = new Date("12/8/2018");
		Contrato contrato = new Contrato(proyecto,fechaEntrega);

		Empresa.getInstance().nuevoCliente(cliente);
		proyecto.setContrato(contrato);
		proyecto.getContrato().setCliente(cliente);
		Empresa.getInstance().agregarProyecto(proyecto);
		//Empresa.getInstance().nuevoCliente(cliente2);
		///////////////////////////////////////////////////
		
		//Empresa.getInstance().atrasado("1");
		
		Date nuevaFecha = new Date("12/12/2018");
	
		//Empresa.getInstance().cancelarContrato("1");
		//System.out.println(Empresa.getInstance().getClientes().get(0).getContratos().size());
		//System.out.println(Empresa.getInstance().getContratos().size());
		//System.out.println(Empresa.getInstance().getProyectos().get(0).getContrato());
		//Empresa.getInstance().getProyectos().get(0).setAtrasado(true);
		//System.out.println(Empresa.getInstance().getUltimoId());
		System.out.println(Empresa.getInstance().getContratos().get(0).getPrecioFinal());
		//Empresa.getInstance().finalizarProyecto("1");
		//System.out.println(Empresa.getInstance().getGanancias());
		
		
	}

}
