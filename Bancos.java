package bankRun;


import java.util.ArrayList;

import repast.simphony.space.continuous.ContinuousSpace;

public class Bancos {
	String idBanco;
	double resReq;
	double resExe;
	double resTot = (this.capital *(this.resExe + this.resReq));
	double capital;
	boolean iliquido;
	//public Usuario miBanco;
	public Usuario fondos;
	public Usuario retiro;
	//public Usuario propio;
	ArrayList<Usuario> misUsuarios = new ArrayList<Usuario>();
	
	
	public ContinuousSpace<Object> space;
	

	
	public Bancos (String idB) {
		this.idBanco = idB;
		
		
	}
	
	  public void setCapital(){
		  for (Usuario miAgente: misUsuarios){
			  double c = (miAgente.fondos);
			  this.capital = c++;      // como extarer los fondos de la clase suuario para usar con la clase bancos
		  }
		 //TODO: Corroborar  que el metodo realmente suma los fondos de cada elemento dentro del AL de cada banco
	
	  }
	 	  
	  public void liquidar() {
	  if(this.resTot <= 0) {
			  this.iliquido = true;
		  }
		  //TODO: resolver como hcaer una señalizacion de que el banco ha quebrado y ya no opera para ninguno de sus usuarios
	 }
	  
	  public void cerrar() { //TODO: 
		  for (Usuario miAgente: misUsuarios)
	  {       //cerrar el banco 
		  //cambiar forma imagen o color del banco
	  }
	 }

//TODO: 
 public double getReservas() {
	 return this.resTot;
 }
 }
	  
	  	
	// Método para cerrar el banco
	
	/**
	 * public void () {
	 * 
	 * 	for (iliquido = true){
	 * 		Cambiar el color del banco una vez que haya quebrado}}
	 */
