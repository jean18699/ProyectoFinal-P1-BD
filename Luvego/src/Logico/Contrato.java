package Logico;

import java.io.Serializable;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Contrato implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6827733299844793709L;
	private String id;
	private static int cont = 0;
	private Proyecto proyecto;//SOLO CON FINES DE CALCULO, NO USAR PARA ACCEDER A NADA
	private Date fechaInicio;
	private Date fechaEntrega;
	private float precioContrato;
	private float precioFinal;
	private Cliente cliente;
	private char estado; // F = finalizado, A = Atrasado, I = iniciado 
	private float sumaSalarios;
	private boolean aplazado;
	private float precioAntes;
	private int cantDias;
	
	public void setId(String id) {
		this.id = id;
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}

	
	public Contrato(Proyecto proyecto, Date fechaEntrega) {
		
		Contrato.cont+=1;
		this.id = Integer.toString(cont);
		this.proyecto = proyecto;
		this.fechaInicio = new Date();
		this.fechaEntrega = fechaEntrega;
		sumaSalarios = proyecto.getSumaSalarios();
		this.aplazado = false;
		this.cantDias = getDiasRestantes();
	}
	

	public boolean isAplazado() {
		
		return aplazado;
	}

	public void setAplazado(boolean aplazado) {
		this.aplazado = aplazado;
	}

	public Cliente getCliente() {
		return cliente;
	}

/*
	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}
*/

	public int getCantDias() {
		return cantDias;
	}

	public void setCantDias(int cantDias) {
		this.cantDias = cantDias;
	}

	public float getPrecioAntes() {
		return precioAntes;
	}

	public void setPrecioAntes(float precioAntes) {
		this.precioAntes = precioAntes;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public int getDiasRestantes()
	{
		Date fecha = new Date();
		
		long diff = fechaEntrega.getTime() - fechaInicio.getTime();
		
		return (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
		
		//return Days.daysBetween(date1, date2).getDays();
		
		//return Math.abs(fechaInicio.getDate() - fechaEntrega.getDate());
	}

	public void aplazar(Date fecha)
	{
		precioAntes = (8 * (cantDias) * sumaSalarios);
		this.fechaEntrega = fecha;
		cantDias = getDiasRestantes();
		this.setAplazado(true);
	}
	
	public String getId() {
		return id;
	}
	
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public Date getFechaEntrega() {
		return fechaEntrega;
	}

	
	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}
	
	public float getPrecioFinal() {
			return (8 * (cantDias) * sumaSalarios);//proyecto.getSumaSalarios());
	}
	public void setPrecioFinal(float precioFinal) {
		this.precioFinal = precioFinal;
	}

	public char getEstado() {
		return estado;
	}

	public void setEstado(char estado) {
		this.estado = estado;
	}
	
}
