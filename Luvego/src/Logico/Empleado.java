package Logico;

import java.io.Serializable;
import java.util.ArrayList;

 public abstract class Empleado implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2167295889165009446L;
	//para generar el id
	private static int cont;
	private String ceros;

	protected String cedula;
	protected String telefono1;
	protected String telefono2;
	protected String id;
	protected final String nombre;
	protected final String apellidos;
	protected final String sexo;
	protected final int edad;
	protected final float salarioHora;
	protected final String direccion;
	protected ArrayList<Proyecto> proyectos;
	protected String evaluacionAnual;
	private String ocupacion;
	protected int totalProyectos;
	protected String condicion;
	protected int proyectosAtrasados;
	
	
	
	public int getProyectosAtrasados() {
		return proyectosAtrasados;
	}

	public void setTotalProyectos(int totalProyectos) {
		this.totalProyectos = totalProyectos;
	}

	public void setProyectosAtrasados(int proyectosAtrasados) {
		this.proyectosAtrasados = proyectosAtrasados;
	}

	public void eliminarProyecto(String idProyecto) {
		int index = getProyectoIndex(idProyecto);
		if(index != -1) {
			proyectos.remove(index);
		}
	}
	
	public int getProyectoIndex(String idProyecto)
	{
		int index = -1;
		for(int i = 0 ; i < proyectos.size();i++)
		{
			if(proyectos.get(i).getId().equalsIgnoreCase(idProyecto))
			{
				index = i;
				break;
			}
		}
		
		return index;
	
	}
	
	
	public int getTotalProyectos() {
		return totalProyectos;
	}



	public Empleado(String cedula,String nombre, String apellidos, String sexo, int edad,String telefono1,String telefono2,String direccion, float salarioHora) {

		
		ceros = "";
		cont++;
		id = Integer.toString(cont);
		generarCeros();
		id = ceros+=id;
		
		this.evaluacionAnual = "Excelente";
		this.cedula = cedula;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.sexo = sexo;
		this.edad = edad;
		this.direccion = direccion;
		this.salarioHora = salarioHora;
		proyectos = new ArrayList<>();
		totalProyectos = 0;
		this.telefono1 = telefono1;
		this.telefono1 = telefono2;
		this.condicion = "Disponible";
	}

	
	public String getCondicion() {
		return condicion;
	}

	public void setCondicion(String condicion) {
		this.condicion = condicion;
	}

	public String getCedula() {
		return cedula;
	}


	public String getTelefono1() {
		return telefono1;
	}


	public String getTelefono2() {
		return telefono2;
	}


	public void setCedula(String cedula) {
		this.cedula = cedula;
	}


	public void setTelefono1(String telefono1) {
		this.telefono1 = telefono1;
	}


	public void setTelefono2(String telefono2) {
		this.telefono2 = telefono2;
	}


	public float getSalarioDia()
	{
		return salarioHora * 8;
	}
	
	
	public String getApellidos() {
		return apellidos;
	}


	public String getDireccion() {
		return direccion;
	}

	

	public String getEvaluacionAnual() {
		return evaluacionAnual;
	}


	public void setEvaluacionAnual(String evaluacionAnual) {
		this.evaluacionAnual = evaluacionAnual;
	}


	private void generarCeros()
	{
		for (int i = 0; i < 7 - id.length(); i++) {
			ceros = ceros + "0";
		}	
	}
	
	public String getId() {
		return identificador() + id;
	}

	
	abstract String identificador();

	
	public static int getCont() {
		return cont;
	}

	public String getCeros() {
		return ceros;
	}

	public String getNombre() {
		return nombre;
	}

	public String getSexo() {
		return sexo;
	}

	public int getEdad() {
		return edad;
	}

	public float getSalarioHora() {
		return salarioHora;
	}

	public ArrayList<Proyecto> getProyectos() {
		return proyectos;
	}

	public void setId(String id) {
		this.id = id;
	}

	public static void setCont(int cont) {
		Empleado.cont = cont;
	}

	public void setCeros(String ceros) {
		this.ceros = ceros;
	}

	
	public void setProyecto(Proyecto proyecto) {
		this.proyectos.add(proyecto);
		totalProyectos++;
	}
	
	public String getCargo()
	{
		return getOcupacion();
	}
	
	protected abstract String getOcupacion();


	
	public void setProyectos(ArrayList<Proyecto> proyectos) {
		this.proyectos = proyectos;
	}


	public void setOcupacion(String ocupacion) {
		this.ocupacion = ocupacion;
	}

	
}
