package Logico;

import java.io.Serializable;
import java.util.ArrayList;

public class Cliente implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1232268027012908569L;
	private String id;
	private String cedula;
	private String telefono;
	private String telefono2;
	private String correo;
	private static int cont = 0;
	private String nombre;
	private String direccion;
	private ArrayList<Contrato> contratos;
	private int totalContratos;
	private String genero;
	
	
	public Cliente(String cedula, String nombre,String correo,String telefono,String telefono2, String direccion,String genero) {
		super();
		
		cont++;
		this.id = Integer.toString(cont);
		this.nombre = nombre;
		this.genero = genero;
		this.telefono = telefono;
		this.telefono2 = telefono2;
		this.cedula = cedula;
		this.direccion = direccion;
		contratos = new ArrayList<>(5);
		totalContratos = 0;
	}


	public String getCedula() {
		return cedula;
	}


	public String getTelefono() {
		return telefono;
	}


	public String getTelefono2() {
		return telefono2;
	}


	public String getCorreo() {
		return correo;
	}


	public String getGenero() {
		return genero;
	}


	public void setCedula(String cedula) {
		this.cedula = cedula;
	}


	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}


	public void setTelefono2(String telefono2) {
		this.telefono2 = telefono2;
	}


	public void setCorreo(String correo) {
		this.correo = correo;
	}


	public void setGenero(String genero) {
		this.genero = genero;
	}


	public int getTotalContratos() {
		return totalContratos;
	}


	public void setContratos(ArrayList<Contrato> contratos) {
		this.contratos = contratos;
	}


	public void setTotalContratos(int totalContratos) {
		this.totalContratos = totalContratos;
	}


	public String getId() {
		return id;
	}


	public String getNombre() {
		return nombre;
	}


	public String getDireccion() {
		return direccion;
	}



	public ArrayList<Contrato> getContratos() {
		return contratos;
	}

	public void terminarContrato(Contrato contrato)
	{
		for(int i = 0; i < contratos.size();i++)
		{
			if(contrato.equals(contratos.get(i)))
			{
				contratos.remove(i);
				break;
			}
		}
	}

	public boolean setContrato(Contrato contrato) {
		if(contratos.size() <= 5)
		{
			contratos.add(contrato);
			totalContratos++;
			return true;
		}
		return false;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	public Cliente() {
		
	}

}
