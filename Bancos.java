package bankRuns;


import java.util.ArrayList;

import repast.simphony.space.continuous.ContinuousSpace;

public class Bancos {
	String idBanco;
	double resReq;
	double resExe;
	double resTot = (this.capital *(this.resExe + this.resReq));
	double capital;
	boolean iliquido;
	public Usuario miBanco;
	public Usuario fondos;
	public Usuario propio;
	
	
	public ContinuousSpace<Object> space;
	

	
	public Bancos (String idB) {
		this.idBanco = idB;
		
		
	}
	
	ArrayList<Usuario> misUsuarios = new ArrayList<Usuario>();
	  public void set.capital(){
		  if (this.propio == true){
			  misUsuarios.add(this.fondos); // como extarer los fondos de la clase suuario para usar con la clase bancos
		  for(double c:misUsuarios){
		      this.capital += c;}
		  }
		  
	
	  }
	 
	
	//Banco establecer el nexo entre usuarios y bancos
	  
	  public void setReservas(double retiro){
	  	if (this.propio = true) {
	  		if(this.resTot < this.fondos){
	  			iliquido=true;
	  			this.resTot = this.resTot - this.fondos;
	  			}
	  		else {this.resTot = this.resTot - this.fondos;}
	  	} 
	  }
	  
	  public void cerrar() {
		  if (this.iliquido = true) {       //cerrar el banco 
			  //cambiar forma imagen o color del banco
		  }
	  }
}

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
