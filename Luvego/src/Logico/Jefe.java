package Logico;

import java.io.Serializable;

public class Jefe extends Empleado implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 655412545224648646L;
	private int cantTrabajadores;
	private int proyectosCompletados;
	
	
	public Jefe(String cedula, String nombre, String apellidos, String sexo, int edad, String telefono1,
			String telefono2, String direccion, float salarioHora) {
		super(cedula, nombre, apellidos, sexo, edad, telefono1, telefono2, direccion, salarioHora);
		// TODO Auto-generated constructor stub
	}


	public int getCantTrabajadores() {
		return cantTrabajadores;
	}

	public void setCantTrabajadores(int cantTrabajadores) {
		this.cantTrabajadores = cantTrabajadores;
	}

	public String getOcupacion() {
		return "Jefe de proyectos";
	}

	@Override
	String identificador() {
		
		return "JFE-";
	}

	
}
