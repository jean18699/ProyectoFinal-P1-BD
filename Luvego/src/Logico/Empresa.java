package Logico;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Empresa implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6921637127266475472L;
	private ArrayList<Cliente> clientes;
	private static User loginUser;
	private ArrayList<Contrato> contratos;
	private ArrayList<Empleado> empleados;
	private Proyecto ultimoProyecto;
	private int cantDesktop;
	private int cantMovil;
	private int cantWeb;
	private int cantJava;
	private int cantCP;
	private int cantPython;
	private int cantHtml;
	private int cantCSharp;
	private int cantProyectosTerminados;
	private int cantProyectosCancelados;
	private int totalProyectos;
	private ArrayList<Proyecto> proyectos;
	private static Empresa empresa;
	private float ganancias;
	private float ultimaGanancia;
	private float perdidasTotales;
	private float UltimasPerdida;
	private String ultimoId;
	private String ultimoIdCliente;
	private String ultimoNombreCliente;
	private String ultimoNombreProyecto;
	private Date ultimoFechaSolicitud;
	private Date ultimoFechaEntrega;
	public int totalEmpleados;
	private int totalDisegnadores;
	private int totalProgramadores;
	private int totalJefes;
	private int totalPlanificadores;
	

	private Empresa() {
		//
		clientes = new ArrayList<>();
		contratos = new ArrayList<>();
		empleados = new ArrayList<>();
		proyectos = new ArrayList<>();
		ultimoProyecto = null;
		loginUser = new User();
		ganancias = 0;
		cantProyectosTerminados = 0;
		cantProyectosCancelados = 0;
		// loginUser = new Cliente();
		loginUser.setTipo("Admin");
		loginUser.setPass("0000");
	}

	public void RESET()
	{
		clientes = new ArrayList<>();
		contratos = new ArrayList<>();
		empleados = new ArrayList<>();
		proyectos = new ArrayList<>();
		ultimoProyecto = null;
		loginUser = new User();
		ganancias = 0;
		cantProyectosTerminados = 0;
		cantProyectosCancelados = 0;
	}
	
	public int getTotalProyectos() {
		return totalProyectos;
	}

	public void setTotalProyectos(int totalProyectos) {
		this.totalProyectos = totalProyectos;
	}

	public int getCantProyectosCancelados() {
		return cantProyectosCancelados;
	}

	public void setCantProyectosCancelados(int cantProyectosCancelados) {
		this.cantProyectosCancelados = cantProyectosCancelados;
	}

	public int getCantProyectosTerminados() {
		return cantProyectosTerminados;
	}

	public void setCantProyectosTerminados(int cantProyectosTerminados) {
		this.cantProyectosTerminados = cantProyectosTerminados;
	}

	public int getCantJava() {
		return cantJava;
	}

	public int getCantCP() {
		return cantCP;
	}

	public int getCantPython() {
		return cantPython;
	}

	public int getCantHtml() {
		return cantHtml;
	}

	public int getCantCSharp() {
		return cantCSharp;
	}

	public Proyecto getUltimoProyecto() {
		return ultimoProyecto;
	}

	public void setUltimoProyecto(Proyecto ultimoProyecto) {
		this.ultimoProyecto = ultimoProyecto;
	}

	public float getGanancias() {
		return ganancias;
	}

	public void setGanancias(long ganancias) {
		this.ganancias = ganancias;
	}

	public static User getLoginUser() {
		return loginUser;
	}

	public static void setLoginUser(User loginUser) {
		Empresa.loginUser = loginUser;
	}

	public static Empresa getInstance() {
		if (empresa == null) {
			empresa = new Empresa();
		}
		return empresa;
	}

	public int getCantJefes() {
		int cont = 0;
		for (int i = 0; i < empleados.size(); i++) {
			if (empleados.get(i) instanceof Jefe)
				cont++;
		}
		return cont;
	}

	public int getCantProgramadores() {
		int cont = 0;
		for (int i = 0; i < empleados.size(); i++) {
			if (empleados.get(i) instanceof Programador)
				cont++;
		}
		return cont;
	}

	public int getCantPlanificadores() {
		int cont = 0;
		for (int i = 0; i < empleados.size(); i++) {
			if (empleados.get(i) instanceof Planificador)
				cont++;
		}
		return cont;
	}

	public int getCantDisegnadores() {
		int cont = 0;
		for (int i = 0; i < empleados.size(); i++) {
			if (empleados.get(i) instanceof Disegnador)
				cont++;
		}
		return cont;
	}

	public void nuevoProyecto(Proyecto proyecto) {
		proyectos.add(proyecto);
	}
	/*
	 * public void obtenerGanancia(String idContrato) { for(int i = 0; i <
	 * contratos.size();i++) {
	 * if(contratos.get(i).getId().equalsIgnoreCase(idContrato)) {
	 * if(contratos.get(i).getEstado().equalsIgnoreCase("Finalizado")) { ganancias
	 * += contratos.get(i).getPrecioFinal() * 0.10; } } } }
	 */

	public void nuevoCliente(Cliente cliente) {
		clientes.add(cliente);
	}

	public void nuevoEmpleado(Empleado empleado) {
		empleados.add(empleado);
	}

	/*
	 * public Cliente getClienteByIdProyecto(String id) { Cliente cliente = null;
	 * boolean encontrado = false; for(int i = 0; i < clientes.size() &&
	 * !encontrado;i++) { for(int j = 0; j <
	 * clientes.get(i).getContratos().size();j++) {
	 * if(clientes.get(i).getContratos().get(j).getProyecto().getId().
	 * equalsIgnoreCase(id)) { cliente = clientes.get(i); encontrado = true; } } }
	 * return cliente; }
	 * 
	 */
	public int getCantDesktop() {
		return cantDesktop;
	}

	public void setCantJava(int cantJava) {
		this.cantJava = cantJava;
	}

	public void setCantCP(int cantCP) {
		this.cantCP = cantCP;
	}

	public void setCantPython(int cantPython) {
		this.cantPython = cantPython;
	}

	public void setCantHtml(int cantHtml) {
		this.cantHtml = cantHtml;
	}

	public void setCantCSharp(int cantCSharp) {
		this.cantCSharp = cantCSharp;
	}

	public int getCantWeb() {
		return cantWeb;
	}

	public int getCantMovil() {
		return cantMovil;
	}

	public String getMejorCategoria() {
		if (getCantDesktop() > getCantMovil() && getCantDesktop() > getCantWeb()) {
			return "Escritorio";
		}
		if (getCantWeb() > getCantDesktop() && getCantWeb() > getCantMovil()) {
			return "Web";
		}
		if (getCantMovil() > getCantWeb() && getCantMovil() > getCantDesktop()) {
			return "Movil";
		}

		return null;
	}

	public void setCantDesktop(int cantDesktop) {
		this.cantDesktop = cantDesktop;
	}

	public void setCantMovil(int cantMovil) {
		this.cantMovil = cantMovil;
	}

	public void setCantWeb(int cantWeb) {
		this.cantWeb = cantWeb;
	}

	public int getClienteIndex(String idCliente) {
		int index = -1;
		for (int i = 0; i < clientes.size(); i++) {
			if (clientes.get(i).getId().equalsIgnoreCase(idCliente)) {
				index = i;
			}
		}
		return index;
	}

	public void eliminarContratoCliente(String idCliente, String idContrato) {
		Cliente cliente = getClienteById(idCliente);
		for (int i = 0; i < cliente.getContratos().size(); i++) {
			if (cliente.getContratos().get(i).getId().equalsIgnoreCase(idContrato)) {
				cliente.getContratos().remove(i);
			}
		}
	}

	public void agregarContratoCliente(String idCliente, Contrato contrato) {
		Cliente cliente = getClienteById(idCliente);
		cliente.setContrato(contrato);
		// contratos.add(contrato);
	}

	public void agregarProyecto(Proyecto proyecto) {
		proyectos.add(proyecto);
		contratos.add(proyecto.getContrato());
		agregarContratoCliente(proyecto.getContrato().getCliente().getCedula(), proyecto.getContrato());

		for (int i = 0; i < proyecto.getGrupoTrabajo().size(); i++) {
			proyecto.getGrupoTrabajo().get(i).setProyecto(proyecto);
		}
		totalProyectos++;
	}

	public void cancelarContrato(String idContrato) {

		for (int i = 0; i < contratos.size(); i++) {
			if (contratos.get(i).getId().equalsIgnoreCase(idContrato)) {
				contratos.remove(i);
			}
		}

		for (int i = 0; i < clientes.size(); i++) {
			for (int j = 0; j < clientes.get(i).getContratos().size(); j++) {
				if (clientes.get(i).getContratos().get(j).getId().equalsIgnoreCase(idContrato)) {
					clientes.get(i).getContratos().remove(j);
				}
			}
		}

		for (int i = 0; i < proyectos.size(); i++) {
			if (proyectos.get(i).getContrato().getId().equalsIgnoreCase(idContrato)) {

				eliminarProyecto(proyectos.get(i).getId());
			}
		}
	}

	public void eliminarProyecto(String idProyecto) {

		Proyecto proyecto = getProyectoById(idProyecto);

		// Eliminando el grupo encargado del proyecto
		for (int i = 0; i < proyecto.getGrupoTrabajo().size(); i++) {
			proyecto.getGrupoTrabajo().get(i).eliminarProyecto(idProyecto);
		}

		// Eliminandolo del cliente
		eliminarContratoCliente(proyecto.getContrato().getCliente().getCedula(), proyecto.getContrato().getId());

		// Eliminando el proyecto
		for (int i = 0; i < proyectos.size(); i++) {
			proyectos.remove(i);
		}

		/*
		 * for(int i = 0; i <
		 * proyectos.get(getProyectoIndex(idProyecto)).getGrupoTrabajo().size(); i++) {
		 * proyectos.get(getProyectoIndex(idProyecto)).getGrupoTrabajo().get(i).
		 * setProyectosActivos(
		 * proyectos.get(getProyectoIndex(idProyecto)).getGrupoTrabajo().get(i).
		 * getProyectosActivos()-1); }
		 * 
		 * clientes.get(getClienteIndex(proyectos.get(getProyectoIndex(idProyecto)).
		 * getContrato().getCliente().getId())).terminarContrato(
		 * proyectos.get(getProyectoIndex(idProyecto)).getContrato());
		 * 
		 * eliminarContrato(idProyecto); proyectos.remove(getProyectoIndex(idProyecto));
		 */
	}

	
	
	public Proyecto getProyectoById(String id) {
		Proyecto proyecto = null;
		boolean encontrado = false;
		for (int i = 0; i < proyectos.size() && !encontrado; i++) {
			if (proyectos.get(i).getId().equalsIgnoreCase(id)) {
				proyecto = proyectos.get(i);
				encontrado = true;
			}
		}
		return proyecto;
	}

	// mod
	public int getProyectoIndex(String idProyecto) {
		int index = -1;
		for (int i = 0; i < proyectos.size(); i++) {
			if (proyectos.get(i).getId().equalsIgnoreCase(idProyecto)) {
				index = i;
			}
		}
		return index;
	}

	/*
	 * public boolean nuevoContrato(String idCliente, Contrato contrato) {
	 * 
	 * if(existeCliente(idCliente)) { for(int i = 0; i < clientes.size();i++) {
	 * if(clientes.get(i).getId().equalsIgnoreCase(idCliente)) {
	 * if(clientes.get(i).setContrato(contrato)) {
	 * contrato.setCliente(clientes.get(i)); contratos.add(contrato);
	 * nuevoProyecto(contrato.getProyecto());
	 * asignarProyecto(contrato.getProyecto().getGrupoTrabajo(),
	 * contrato.getProyecto()); return true; }else { return false;
	 * 
	 * } } } }else { return false; }
	 * 
	 * return false; }
	 */

	/*
	 * public boolean cancelarContrato(String idContrato) { String idProyect =
	 * getContratoByIdProyecto(idContrato).getProyecto().getId(); for(int i = 0; i <
	 * contratos.size(); i++) {
	 * 
	 * if(contratos.get(i).getId().equalsIgnoreCase(idContrato)) { for(i = 0; i <
	 * getContratoByIdProyecto(idContrato).getProyecto().getGrupoTrabajo().size();
	 * i++) {
	 * getContratoByIdProyecto(idContrato).getProyecto().getGrupoTrabajo().get(i).
	 * eliminarDelProyecto(idProyect); }
	 * 
	 * //eliminarProyecto(contratos.get(i).getProyecto().getId());
	 * contratos.remove(i); return true; } } return false; }
	 */
	public Empresa getEmpresa() {
		return empresa;
	}

	public void eliminarEmpleado(String id) {
		for (int i = 0; i < empleados.size(); i++) {
			if (empleados.get(i).getId().equalsIgnoreCase(id)) {
				empleados.remove(i);
			}
		}
	}

	private boolean existeCliente(String id) {
		for (int i = 0; i < clientes.size(); i++) {
			if (clientes.get(i).getId().equalsIgnoreCase(id)) {
				return true;
			}
		}
		return false;
	}

	public Proyecto getProyectoByIdContrato(String idContrato) {
		Proyecto proyecto = null;
		for (int i = 0; i < proyectos.size(); i++) {
			if (proyectos.get(i).getContrato().getId().equalsIgnoreCase(idContrato)) {
				proyecto = proyectos.get(i);
			}
		}
		return proyecto;
	}

	/*
	 * public Contrato getContratoByIdProyecto(String idProyecto) { Contrato
	 * contrato = null; boolean encontrado = false; for(int i = 0; i <
	 * contratos.size() && !encontrado ;i++) {
	 * if(contratos.get(i).getProyecto().getId().equalsIgnoreCase(idProyecto)) {
	 * contrato = contratos.get(i); encontrado = true; } } return contrato; }
	 */
	public Cliente getClienteById(String id) {
		Cliente cliente = null;
		boolean encontrado = false;
		for (int i = 0; i < clientes.size() && !encontrado; i++) {
			if (clientes.get(i).getCedula().equalsIgnoreCase(id)) {
				cliente = clientes.get(i);
				encontrado = true;
			}
		}

		return cliente;
	}

	public Empleado getEmpleadoById(String id) {
		Empleado emp = null;
		boolean encontrado = false;
		for (int i = 0; i < empleados.size() && !encontrado; i++) {
			if (empleados.get(i).getId().equalsIgnoreCase(id)) {
				emp = empleados.get(i);
				encontrado = true;
			}
		}
		return emp;
	}

	/*
	 * //Funcion que recibira una lista de empleados para asignarles el mismo
	 * proyecto public void asignarProyecto(ArrayList<Empleado> empleado, Proyecto
	 * proyecto) { for(int i = 0; i < empleado.size();i++) {
	 * 
	 * if(empleado.get(i).getProyectos().size() <= 3) {
	 * 
	 * empleado.get(i).setProyecto(proyecto);
	 * 
	 * } } }
	 */

	public void atrazado(Proyecto proyecto) {
		float perdidas = 0;

		for (int i = 0; i < proyecto.getContrato().getDiasRestantes(); i++) {
			perdidas += Math.abs(proyecto.getContrato().getPrecioFinal() * 0.01);
		}

		this.perdidasTotales += perdidas;
		this.ultimaGanancia = (float) (Math.abs((proyecto.getContrato().getPrecioFinal() - perdidas) * 0.15));
		this.ganancias += ultimaGanancia;
		UltimasPerdida = perdidas;

	}

	
	public void aplazado(String idProyecto) {

		Proyecto proyecto = getProyectoById(idProyecto);
		float perdidas = Math.abs(proyecto.getContrato().getPrecioAntes() - proyecto.getContrato().getPrecioFinal());
		// this.perdidasTotales+=perdidas;
		float ganancia = (float) (proyecto.getContrato().getPrecioAntes() * 0.15);
		ultimaGanancia = ganancia;
		ganancias += ultimaGanancia;
		perdidasTotales += perdidas;
		UltimasPerdida = perdidas;

	}

	public void finalizarProyecto(String idProyecto) {

		float perdidas = 0;
		float ganancia = 0;
		Proyecto proyecto = getProyectoById(idProyecto);
		
		ultimoId = proyecto.getId();
		ultimoNombreCliente = proyecto.getContrato().getCliente().getNombre();
		ultimoIdCliente = proyecto.getContrato().getCliente().getId();
		ultimoNombreProyecto = proyecto.getNombre();
		ultimoFechaSolicitud = proyecto.getContrato().getFechaInicio();
		ultimoFechaEntrega = proyecto.getContrato().getFechaEntrega();
		
		if (proyecto.getContrato().isAplazado() && proyecto.getContrato().isAplazado()) {

			for (int i = 0; i < proyecto.getContrato().getDiasRestantes(); i++) {
				perdidas += Math.abs(proyecto.getContrato().getPrecioFinal() * 0.01);
			}
			this.perdidasTotales += perdidas;
			this.ultimaGanancia = (float) (Math.abs((proyecto.getContrato().getPrecioFinal() - perdidas) * 0.15));
			ganancias += ultimaGanancia;
			UltimasPerdida = perdidas;
			
			
		} else if (proyecto.getContrato().isAplazado() || proyecto.isAtrasado()) {

			if (proyecto.getContrato().isAplazado()) {
				aplazado(proyecto.getId());
			} else

			if (proyecto.isAtrasado()) {
				atrazado(proyecto);
			}
		}else
		{
			ganancia = (float) (proyecto.getContrato().getPrecioFinal() * 0.15); 
			ganancias+= ganancia;
			ultimaGanancia = ganancia;
			UltimasPerdida = 0;
		}

			cancelarContrato(proyecto.getContrato().getId());
		/*
		 * for (int i = 0; i < contratos.size(); i++) { if
		 * (contratos.get(i).getId().equalsIgnoreCase(proyecto.getContrato().getId())) {
		 * contratos.remove(i); } }
		 * 
		 * for (int i = 0; i < clientes.size(); i++) { for (int j = 0; j <
		 * clientes.get(i).getContratos().size(); j++) { if
		 * (clientes.get(i).getContratos().get(j).getId().equalsIgnoreCase(proyecto.
		 * getContrato().getId())) { clientes.get(i).getContratos().remove(j); } } }
		 * 
		 * for (int i = 0; i < proyectos.size(); i++) { if
		 * (proyectos.get(i).getContrato().getId().equalsIgnoreCase(proyecto.getContrato
		 * ().getId())) {
		 * 
		 * eliminarProyecto(proyectos.get(i).getId()); } }
		 */
		// System.out.println(proyecto);
		/*
		 * if (proyecto.getEstado().equalsIgnoreCase("Finalizado")) { this.ganancias +=
		 * proyecto.getContrato().getPrecioFinal() * 0.15; ultimoProyecto = proyecto; }
		 */

		cantProyectosTerminados++;
	}

	public Date getUltimoFechaEntrega() {
		return ultimoFechaEntrega;
	}

	public void setUltimoFechaEntrega(Date ultimoFechaEntrega) {
		this.ultimoFechaEntrega = ultimoFechaEntrega;
	}

	public Date getUltimoFechaSolicitud() {
		return ultimoFechaSolicitud;
	}

	public void setUltimoFechaSolicitud(Date ultimoFechaSolicitud) {
		this.ultimoFechaSolicitud = ultimoFechaSolicitud;
	}

	public String getUltimoNombreProyecto() {
		return ultimoNombreProyecto;
	}

	public void setUltimoNombreProyecto(String ultimoNombreProyecto) {
		this.ultimoNombreProyecto = ultimoNombreProyecto;
	}

	public String getUltimoId() {
		return ultimoId;
	}

	public String getUltimoIdCliente() {
		return ultimoIdCliente;
	}

	public String getUltimoNombreCliente() {
		return ultimoNombreCliente;
	}

	public void setUltimoId(String ultimoId) {
		this.ultimoId = ultimoId;
	}

	public void setUltimoIdCliente(String ultimoIdCliente) {
		this.ultimoIdCliente = ultimoIdCliente;
	}

	public void setUltimoNombreCliente(String ultimoNombreCliente) {
		this.ultimoNombreCliente = ultimoNombreCliente;
	}

	public float getUltimaGanancia() {
		return ultimaGanancia;
	}

	public float getPerdidasTotales() {
		return perdidasTotales;
	}

	public float getUltimasPerdida() {
		return UltimasPerdida;
	}

	public void setGanancias(float ganancias) {
		this.ganancias = ganancias;
	}

	public void setUltimaGanancia(float ultimaGanancia) {
		this.ultimaGanancia = ultimaGanancia;
	}

	public void setPerdidasTotales(float perdidasTotales) {
		this.perdidasTotales = perdidasTotales;
	}

	public void setTotalEmpleados(int total)
	{
		totalEmpleados = total;
	}
	public int getTotalEmpleados()
	{
		return totalEmpleados;
	}
	
	public void setUltimasPerdida(float ultimasPerdida) {
		UltimasPerdida = ultimasPerdida;
	}

	public ArrayList<Cliente> getClientes() {
		return clientes;
	}

	public ArrayList<Contrato> getContratos() {
		return contratos;
	}

	public ArrayList<Empleado> getEmpleados() {
		return empleados;
	}

	public void setClientes(ArrayList<Cliente> clientes) {
		this.clientes = clientes;
	}

	public void setContratos(ArrayList<Contrato> contratos) {
		this.contratos = contratos;
	}

	public void setEmpleados(ArrayList<Empleado> empleados) {
		this.empleados = empleados;
	}

	public ArrayList<Proyecto> getProyectos() {
		return proyectos;
	}

	public void setProyectos(ArrayList<Proyecto> proyectos) {
		this.proyectos = proyectos;
	}

	public void setEmpresa(Empresa empresa) {
		Empresa.empresa = empresa;
	}

	public boolean ValidadorNombre(String str) {
		boolean verdad = true;

		int i = 0;
		if (str.equalsIgnoreCase("")/* || Integer.valueOf(str.charAt(0)) == 32 */) {
			return false;
		}

		while (i < str.length() && verdad != false) {
			if (!((Integer.valueOf(str.charAt(i)) >= 65 && Integer.valueOf(str.charAt(i)) <= 90)
					|| (Integer.valueOf(str.charAt(i)) >= 97
							&& Integer.valueOf(str.charAt(i)) <= 122)/* || Integer.valueOf(str.charAt(i)) == 32 */)) {
				verdad = false;
				return verdad;
			}

			i++;
		}
		return verdad;
	}

	public boolean ValidadorCaracteres(String str) {
		boolean verdad = true;

		int i = 0;
		if (str.equalsIgnoreCase("") || Integer.valueOf(str.charAt(0)) == 32) {
			return false;
		}

		while (i < str.length() && verdad != false) {
			if (!((Integer.valueOf(str.charAt(i)) >= 65 && Integer.valueOf(str.charAt(i)) <= 90)
					|| (Integer.valueOf(str.charAt(i)) >= 97 && Integer.valueOf(str.charAt(i)) <= 122)
					|| Integer.valueOf(str.charAt(i)) == 32)) {
				verdad = false;
				return verdad;
			}

			i++;
		}
		return verdad;
	}

	public boolean ValidadorNumeros(String str) {
		boolean verdad = true;

		int i = 0;
		if (str.equalsIgnoreCase("")) {
			verdad = false;
		}
		while (i < str.length() && verdad != false) {
			if (Integer.valueOf(str.charAt(i)) < 46 || Integer.valueOf(str.charAt(i)) > 57) {
				verdad = false;
				return verdad;
			}
			i++;

		}
		return verdad;
	}

	public boolean ValidadorCedula(String str) {
		boolean verdad = true;

		int i = 0;
		int cantGuiones = 0;
		if (str.equalsIgnoreCase("")) {
			return false;
		}
		while (i < str.length() && verdad != false) {
			if (Integer.valueOf(str.charAt(i)) == 45) {
				cantGuiones++;
			}
			if ((Integer.valueOf(str.charAt(i)) != 45 && Integer.valueOf(str.charAt(i)) < 48
					|| Integer.valueOf(str.charAt(i)) > 57) || cantGuiones > 2) {
				verdad = false;
				return verdad;
			}
			i++;

		}
		return verdad;
	}

	public boolean ValidadorFlotantes(String str) {
		boolean verdad = true;

		int i = 0;
		int cantPuntosDecimales = 0;
		if (str.equalsIgnoreCase("")) {
			verdad = false;
		}
		while (i < str.length() && verdad != false) {
			if (Integer.valueOf(str.charAt(i)) == 46) {
				cantPuntosDecimales++;
			}
			if (Integer.valueOf(str.charAt(i)) < 46 || Integer.valueOf(str.charAt(i)) > 57
					|| Integer.valueOf(str.charAt(i)) == 47 || cantPuntosDecimales > 1) {
				verdad = false;
				return verdad;
			}
			i++;

		}
		return verdad;
	}

	public void setTotalDisegnadores(int total) {
		// TODO Auto-generated method stub
		totalDisegnadores = total;
	}

	public int getTotalDisegnadores()
	{
		return totalDisegnadores;
	}
	
	public void setTotalProgramadores(int total) {
		// TODO Auto-generated method stub
		totalProgramadores = total;
	}

	public int getTotalProgramadores()
	{
		return totalProgramadores;
	}
	
	public void setTotalJefes(int total) {
		// TODO Auto-generated method stub
		totalJefes = total;
	}

	public int getTotalJefes()
	{
		return totalJefes;
	}
	
	public void setTotalPlanificadores(int total) {
		// TODO Auto-generated method stub
		totalPlanificadores = total;
	}

	public int getTotalPlanificadores()
	{
		return totalPlanificadores;
	}
	
	/*
	 * public boolean confirmLogin(String Id, String Contrasena) { boolean login =
	 * false;
	 * 
	 * if(loginUser.getId().equals("Admin") && loginUser.getPass().equals("0000")) {
	 * login = true; }
	 * 
	 * return login; }
	 */
}