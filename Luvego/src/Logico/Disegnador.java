package Logico;

import java.io.Serializable;

public class Disegnador extends Empleado implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6590451886139742299L;
	private String ocupacion;//Puse esta variable para fines de obtener datos de este empleado de manera facil
	

	public Disegnador(String cedula, String nombre, String apellidos, String sexo, int edad, String telefono1,
			String telefono2, String direccion, float salarioHora) {
		super(cedula, nombre, apellidos, sexo, edad, telefono1, telefono2, direccion, salarioHora);
		this.ocupacion = "Diseñador";
		// TODO Auto-generated constructor stub
	}



	@Override
	public String identificador() {

		return "DSG-";
	}



	@Override
	protected String getOcupacion() {		
		return "Diseñador";
	}

}
