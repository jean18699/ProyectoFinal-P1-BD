package Logico;

import java.io.Serializable;

public class Planificador extends Empleado implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7389099020093531489L;
	private int frecuencia;
	
	public Planificador(String cedula, String nombre, String apellidos, String sexo, int edad, String telefono1,
			String telefono2, String direccion, float salarioHora,int frecuencia) {
		super(cedula, nombre, apellidos, sexo, edad, telefono1, telefono2, direccion, salarioHora);
		this.frecuencia = frecuencia;
		// TODO Auto-generated constructor stub
	}

	public int getFrecuencia() {
		return frecuencia;
	}

	public void setFrecuencia(int frecuencia) {
		this.frecuencia = frecuencia;
	}

	@Override
	String identificador() {
		return "PLN-";
	}

	@Override
	protected String getOcupacion() {
		return "Planificador";
	}

}
