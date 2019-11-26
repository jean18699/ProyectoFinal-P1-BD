package Logico;

import java.io.Serializable;
import java.util.ArrayList;

public class Programador extends Empleado implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3863844485627270214L;
	private ArrayList<String> especialidades;
	

		public Programador(String cedula, String nombre, String apellidos, String sexo, int edad, String telefono1,
			String telefono2, String direccion, float salarioHora, ArrayList<String> especialidades) {
		
			super(cedula, nombre, apellidos, sexo, edad, telefono1, telefono2, direccion, salarioHora);
				this.especialidades = especialidades;
	}

	public ArrayList<String> getEspecialidades() {
		return especialidades;
	}

	public String getOcupacion() {
		return "Programador";
	}
	
	

	public void setEspecialidades(ArrayList<String> especialidades) {
		this.especialidades = especialidades;
	}

		@Override
		String identificador() {
			
			return "PRG-";
		}



}
