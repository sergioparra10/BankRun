package bankRuns;

/**
 * Clase en la cual se definen las acciones y caracteristicas de los bancos. 
 */

import java.util.ArrayList;

import repast.simphony.space.continuous.ContinuousSpace;

public class Bancos {
	/** Variable (String) para tener un identificador de cada uno de los bancos*/
	String idBanco;
	/** Variable (double) la tasa de reservas requeridas de los bancos*/
	double resReq;
	/** Variable (double) para el exedente de reservas */
	double resExe;
	/** Variable (double) es la suma de las reservas requeridas y el exedente */
	double resTot = (this.capital *(this.resExe + this.resReq));
	/** Variable (double) para el capital de cada banco (depositos que hacen los usuarios) */
	double capital;
	/** Variable (boolean) que indica si el banco tiene o no dinero */
	boolean iliquido;
	/** Variable de la clase Usuarios para la dotación inicial del agente que posteriormente depositara en su banco */
	public Usuario fondos;
	/** Variable de la clase Usuario que indica si el agente ya ha retirado sus depositos del banco (retiro = true)  y que posteriormente determinara la señal que tranamitida a su red */
	public Usuario retiro;
	ArrayList<Usuario> misUsuarios = new ArrayList<Usuario>();
	
	
	public ContinuousSpace<Object> space;
	
	public Bancos (String idB) {
		this.idBanco = idB;
		
	}
	/** 
	 * Metodo que recauda el dinero de los usuarios  
	 */
	  public void setCapital(){
		  this.capital =0;
		  for (Usuario miAgente: misUsuarios){
			  double c = (miAgente.fondos);
	/**  
	 * Extarer los fondos de la clase Usuario para usar con la clase Bancos
	 */
			  this.capital += c;   
		  }
		 //TODO: Corroborar  que el metodo realmente suma los fondos de cada elemento dentro del AL de cada banco
	
	  }
	  /**
	   * Metodo para que los agentes se informen si su banco tiene o no dinero 
	   */
	  public void liquidar() { //ambos dentro de un schedule method
			  if(this.resTot <= 0) {
				  this.iliquido = true;
				  for (Usuario miAgente: misUsuarios){
					  miAgente.alarm = true;
					  miAgente.fondos =0;
					  miAgente.retiro = true;
		  }
			  }
		  //TODO: resolver como hcaer una señalizacion de que el banco ha quebrado y ya no opera para ninguno de sus usuarios
	 }
	 /** 
	  * Metodo para cerrar un banco si se queda sin dinero
	  */
	  public void cerrar() { 
		  for (Usuario miAgente: misUsuarios)
	  {       //cerrar el banco 
		  //cambiar forma imagen o color del banco
	  }
	 }
//TODO checar si son necesarios estos metodos
 public double getReservas() {
	 return this.resTot;
 }
 
 public String getName() {
	 return this.idBanco;
 }
 
 }
	  
	  	
// Método para cerrar el banco
	
	/**
	 * public void () {
	 * 
	 * 	for (iliquido = true){
	 * 		Cambiar el color del banco una vez que haya quebrado}}
	 */
