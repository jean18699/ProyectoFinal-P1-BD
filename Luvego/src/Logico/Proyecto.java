package Logico;

import java.io.Serializable;
import java.util.ArrayList;

public class Proyecto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5346260584819546609L;
	private String id;
	private static int cont;
	private String nombre;
	private ArrayList<Empleado> grupoTrabajo;
	private Contrato contrato;
	// private float sumaSalarios;
	private String clasificacion;
	private String estado;
	private String lenguaje;
	private boolean atrasado;

	
	public Proyecto(String nombre, ArrayList<Empleado> grupoTrabajo, String clasificacion,String lenguaje) {

		//Proyecto.cont++;
		this.id = Integer.toString(++cont);
		this.nombre = nombre;
		this.grupoTrabajo = grupoTrabajo;
		// this.sumaSalarios = getSumaSalarios();
		this.clasificacion = clasificacion;
		this.lenguaje = lenguaje;
		this.atrasado = false;
		this.estado = "Normal";
		this.realizado = false; // atributo que solo se utilizara para saber si se creo un proyecto o no en
								// regProyecto
	}

	public String getLenguaje() {
		return lenguaje;
	}

	public void setLenguaje(String lenguaje) {
		this.lenguaje = lenguaje;
	}

	public Contrato getContrato() {
		return contrato;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}

	private boolean realizado;

	public String getEstado() {
		return estado;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	
	/*
	 * public float getGanancia() { return }
	 */

	public boolean isRealizado() {
		return realizado;
	}

	public void setRealizado(boolean realizado) {
		this.realizado = realizado;
	}

	public ArrayList<Empleado> getGrupoTrabajo() {
		return grupoTrabajo;
	}

	public void setGrupoTrabajo(ArrayList<Empleado> grupoTrabajo) {
		this.grupoTrabajo = grupoTrabajo;
	}

	public String getClasificacion() {
		return clasificacion;
	}

	public void setClasificacion(String clasificacion) {
		this.clasificacion = clasificacion;
		
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Jefe getJefeProyecto() {
		return (Jefe) grupoTrabajo.get(0);
	}

	public Planificador getPlanificador() {
		return (Planificador) grupoTrabajo.get(1);
	}

	public Programador getProgramador1() {
		return (Programador) grupoTrabajo.get(2);
	}

	public Programador getProgramador2() {
		return (Programador) grupoTrabajo.get(3);
	}

	public Empleado getTrabajadorAdicional() {
		Empleado emp = null;

		if (grupoTrabajo.get(4) instanceof Jefe) {
			emp = (Programador) grupoTrabajo.get(4);
		}
		if (grupoTrabajo.get(4) instanceof Programador) {
			emp = (Programador) grupoTrabajo.get(4);
		}
		if (grupoTrabajo.get(4) instanceof Planificador) {
			emp = (Planificador) grupoTrabajo.get(4);
		}
		if (grupoTrabajo.get(4) instanceof Disegnador) {
			emp = (Disegnador) grupoTrabajo.get(4);
		}

		return emp;
	}

	public float getSumaSalarios() {

		/*float salaJefe = grupoTrabajo.get(0).getSalarioHora();
		float salaPlani = grupoTrabajo.get(1).getSalarioHora();
		float salaP1 = grupoTrabajo.get(2).getSalarioHora();
		float salaP2 = grupoTrabajo.get(3).getSalarioHora();
		*/
		int sumaSalarios = 0;
		
		for(int i = 0; i < grupoTrabajo.size();i++)
		{
			sumaSalarios += grupoTrabajo.get(i).getSalarioHora();
		}
		//float salaTrabaAdicional = grupoTrabajo.get(4).getSalarioHora();

		return sumaSalarios;//(salaJefe + salaPlani + salaP1 + salaP2/* + salaTrabaAdicional*/);
	}

	public String getId() {
		return id;
	}

	public static int getCont() {
		return cont;
	}
	
	public static void setCont(int numero) {
		cont = numero;
	}
	

	public boolean isAtrasado() {
		return atrasado;
	}
	
	/*public String getCompletadoBIT() {
		String isAtrasado = "0";
		
		if(isAtrasado()) {
			isAtrasado = "1";
		}
		
		return isAtrasado;
				
	}*/

	public void setAtrasado(boolean atrasado) {
		this.atrasado = atrasado;
	}
	
	

}
